#include "utility/trajectory_iterator_cartesian_velocity.h"
#include "trajectory/linear_trajectory.h"
#include "trajectory/curve_trajectory.h"
#include "trajectory/smooth_curve_trajectory.h"
#include "trajectory/bezier_trajectory.h"

#include <franka/exception.h>
#include <franka/robot.h>
#include <string>
#include <iostream>

#include <iostream>

const std::string robot_ip = "172.16.0.2";

void setDefaultBehaviour(franka::Robot &robot);

int main()
{
  std::cout << "connect to robot " << std::endl;
  franka::Robot panda(robot_ip);

  try
  {
    const double endTime = 15.0;
    const double samplingTimestepWidth = 1e-3;

    // connect to robot
    setDefaultBehaviour(panda);

    // read current robot state
    franka::RobotState initialState = panda.readOnce();
    CartesianPose initialPose(initialState.O_T_EE);
    std::cout << "current pose: " << initialPose << std::endl;
    
    // calculate resting pose
    CartesianPose restingPose;
    restingPose.position <<  0.317125, -0.38625, 0.367743;  // in the air
    restingPose.position[2] += 0.31067;   // move to start position above bottom
   
    restingPose.orientation = CartesianPose::neutralOrientation;
    
    // LinearTrajectory and TrajectoryIteratorCartesianVelocity object creation
    LinearTrajectory linearTrajectory(initialPose, restingPose, 0.2, 0.2, 1.e-3);
    auto motionIterator = std::make_unique<TrajectoryIteratorCartesianVelocity>(linearTrajectory);
    
    // move to resting pose
    std::cout << " Robot will move to resting pose, press Enter.";
    std::cin.get();
    panda.control(*motionIterator);
    
    // read current pose for debugging
    franka::RobotState currentState = panda.readOnce();
    CartesianPose currentPose(currentState.O_T_EE);
    std::cout << "current pose: " << currentPose << std::endl << std::endl;

    // define bezier trajectory

    double p = 3;           // polynomial degree
    double continuity = 1;  // how often continuosly differentiable the curve will be

    std::vector<CartesianPose> poses(6);

    poses[0] = initialPose;
    poses[1].position <<  0.2, -0.38625, 0.367743;
    poses[2].position <<  0.1, -0.38625, 0.3;
    poses[3].position <<  0.1, -0.38625, 0.28;
    poses[4].position <<  0.0, -0.38625, 0.22;
    poses[5].position << -0.1, -0.38625, 0.30;

    // CartesianPose initialPose, std::vector<CartesianPose> &poses, double p, double continuity, double endTime, double dt
    BezierTrajectory bezierTrajectory(restingPose, poses, p, continuity, endTime, samplingTimestepWidth);

    // move along trajectory
    auto curveMotionIterator = std::make_unique<TrajectoryIteratorCartesianVelocity>(bezierTrajectory);
    
    std::cout << "Robot will move according to trajectory, press Enter." << std::endl 
      << "Afterwards, Enter aborts the movement\a";
    std::cin.ignore();

    panda.control(*curveMotionIterator,
                  /*controller_mode = */ franka::ControllerMode::kCartesianImpedance);

    franka::RobotState endState = panda.readOnce();
    CartesianPose endPose(endState.O_T_EE);
    std::cout << "end pose: " << endPose << std::endl;
  }
  catch (const franka::Exception &e)
  {
    std::cout << e.what() << std::endl;
    return -1;
  }
  catch (const std::invalid_argument &e)
  {
    std::cout << e.what() << std::endl;
    return -2;
  }
  catch (const std::exception &e)
  {
    std::cout << e.what() << std::endl;
    return -10;
  }

  panda.automaticErrorRecovery();

  std::cout << "Motion finished regularly." << std::endl;
  return 0;
}

void setDefaultBehaviour(franka::Robot &robot)
{
  const double safetyFactor = 1.0;     // seems to have no effect at all

  const double torqueContactAcceleration =    safetyFactor * 30;     //20
  const double torqueCollisionAcceleration =  safetyFactor * 30;  // 20
  const double torqueContactNominal =         safetyFactor * 30;     // 10
  const double torqueCollisionNominal =       safetyFactor * 30;  // 10
  const double forceContactAcceleration =     safetyFactor * 20;    // 20
  const double forceCollisionAcceleration =   safetyFactor * 20;  // 20
  const double forceContactNominal =          safetyFactor * 10;     // 10
  const double forceCollisionNominal =        safetyFactor * 10;   // 10

  const std::array<double, 7>	lower_torque_thresholds_acceleration({torqueContactAcceleration, torqueContactAcceleration, torqueContactAcceleration, torqueContactAcceleration, torqueContactAcceleration, torqueContactAcceleration, torqueContactAcceleration});   // contact torque
  const std::array<double, 7>	upper_torque_thresholds_acceleration{torqueCollisionAcceleration, torqueCollisionAcceleration, torqueCollisionAcceleration, torqueCollisionAcceleration, torqueCollisionAcceleration, torqueCollisionAcceleration, torqueCollisionAcceleration};   // collision torque
  const std::array<double, 7>	lower_torque_thresholds_nominal{torqueContactNominal, torqueContactNominal, torqueContactNominal, torqueContactNominal, torqueContactNominal, torqueContactNominal, torqueContactNominal};        // contact torque
  const std::array<double, 7>	upper_torque_thresholds_nominal{torqueCollisionNominal, torqueCollisionNominal, torqueCollisionNominal, torqueCollisionNominal, torqueCollisionNominal, torqueCollisionNominal, torqueCollisionNominal};        // collision torque
  const std::array<double, 6>	lower_force_thresholds_acceleration{forceContactAcceleration, forceContactAcceleration, forceContactAcceleration, forceContactAcceleration, forceContactAcceleration, forceContactAcceleration};
  const std::array<double, 6>	upper_force_thresholds_acceleration{forceCollisionAcceleration, forceCollisionAcceleration, forceCollisionAcceleration, forceCollisionAcceleration, forceCollisionAcceleration, forceCollisionAcceleration};
  const std::array<double, 6>	lower_force_thresholds_nominal{forceContactNominal, forceContactNominal, forceContactNominal, forceContactNominal, forceContactNominal, forceContactNominal};
  const std::array<double, 6>	upper_force_thresholds_nominal{forceCollisionNominal, forceCollisionNominal, forceCollisionNominal, forceCollisionNominal, forceCollisionNominal, forceCollisionNominal};

  robot.setCollisionBehavior(
      lower_torque_thresholds_acceleration, upper_torque_thresholds_acceleration,
      lower_torque_thresholds_nominal,      upper_torque_thresholds_nominal,
      lower_force_thresholds_acceleration,  upper_force_thresholds_acceleration,
      lower_force_thresholds_nominal,       upper_force_thresholds_nominal);
  robot.automaticErrorRecovery();

  const double impedance_scaling_factor = 1.0;  // don't change
  robot.setJointImpedance({{3000*impedance_scaling_factor, 3000*impedance_scaling_factor, 3000*impedance_scaling_factor, 2500*impedance_scaling_factor, 2500*impedance_scaling_factor, 2000*impedance_scaling_factor, 2000*impedance_scaling_factor}});
  robot.setCartesianImpedance({{3000*impedance_scaling_factor, 3000*impedance_scaling_factor, 3000*impedance_scaling_factor, 300*impedance_scaling_factor, 300*impedance_scaling_factor, 300*impedance_scaling_factor}});
}
