#include "trajectory_iterator.h"
#include "trajectory/linear_trajectory.h"
#include "trajectory/curve_trajectory.h"

#include <franka/exception.h>
#include <franka/robot.h>
#include <string>
#include <iostream>

#include <iostream>

const std::string robot_ip = "172.16.0.2";

void setDefaultBehaviour(franka::Robot &robot);

/** example curve function used for the trajectory */
Eigen::Vector6d curve(double t) {
  Eigen::Vector6d result;
  // Movement has to be very slow!
  result[0] = 0.005*t;      // x
  result[1] = 0.0001*(t-1)*(t-1);    // y
  result[2] = 0.0;    // y
  //result[0] = 0;      // x
  //result[1] = 0;    // y
  //result[2] = 0.001*t*t;      // z

  result[3] = 0;
  result[4] = 0;
  result[5] = 0;

  return result;
}

int main() {

  std::cout << "connect to robot " << std::endl;
  franka::Robot panda(robot_ip);

  try {

    // connect to robot
    setDefaultBehaviour(panda);

    // read current robot state
    franka::RobotState initialState = panda.readOnce();
    Eigen::Vector6d initialPose = homogeneousTfArray2PoseVec(initialState.O_T_EE);
    std::cout << "initial pose: " << initialPose.transpose() << std::endl 
      << " Robot will move to resting pose, press Enter.";

    std::cin.get();

    // calculate resting pose
    Eigen::Vector6d restingPose;
    //const double epsilon = 0
    restingPose << 0.209435, -0.470376, 0.546975, initialPose[3], initialPose[4], initialPose[5];
    //restingPose << 0.209435, -0.470376, 0.546975, 3.11606, 0.00298367,  0.0321451;
    // LinearTrajectory and TrajectoryIteratorCartesianVelocity object creation
    LinearTrajectory linearTrajectory(initialPose, restingPose, 0.5, 0.5, 1.e-3);
    
    // move to resting pose
    auto motionIterator = std::make_unique<TrajectoryIteratorCartesianVelocity>(linearTrajectory);
    
    panda.control(*motionIterator,
                  /*controller_mode = */ franka::ControllerMode::kCartesianImpedance);
    

    franka::RobotState currentState = panda.readOnce();
    Eigen::Vector6d currentPose = homogeneousTfArray2PoseVec(currentState.O_T_EE);

    std::cout << "current pose: " << currentPose.transpose() << std::endl << std::endl
              << "Robot will move according to trajectory, press Enter.";
    std::cin.ignore();

    CurveTrajectory curveTrajectory(restingPose, curve, 60.0, 1.e-3);

    // move along trajectory
    auto curveMotionIterator = std::make_unique<TrajectoryIteratorCartesianVelocity>(curveTrajectory);
    
    // Franka Robot Controller:
    panda.control(*curveMotionIterator,
                  /*controller_mode = */ franka::ControllerMode::kCartesianImpedance);

  } catch (const franka::Exception &e) {
    std::cout << e.what() << std::endl;
    return -1;
  } catch (const std::invalid_argument &e) {
    std::cout << e.what() << std::endl;
    return -2;
  } catch (const std::exception &e) {
    std::cout << e.what() << std::endl;
    return -10;
  }

  panda.automaticErrorRecovery();

  std::cout << "Motion finished regularly." << std::endl;
  return 0;
}

void setDefaultBehaviour(franka::Robot &robot) {
  const double safetyFactor = 1.0;    

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

  robot.setJointImpedance({{3000, 3000, 3000, 2500, 2500, 2000, 2000}});
  robot.setCartesianImpedance({{3000, 3000, 3000, 300, 300, 300}});


  
}
