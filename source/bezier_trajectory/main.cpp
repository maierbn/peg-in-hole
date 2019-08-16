#include "trajectory_iterator.h"
#include "trajectory/linear_trajectory.h"
#include "trajectory/curve_trajectory.h"
#include "trajectory/curve_smoothener.h"

#include <franka/exception.h>
#include <franka/robot.h>
#include <string>
#include <iostream>

#include <iostream>

const std::string robot_ip = "172.16.0.2";

void setDefaultBehaviour(franka::Robot &robot);

const double endTime = 15.0;    // duration of the trajectory curve(t), t ∈ [0,endTime]

/** example curve function used for the trajectory */
Eigen::Vector6d curve(double t) {
  double alpha = t / endTime;  // run from 0 to 1
  double beta = 2*alpha - 1;      // run from -1 to 1

  Eigen::Vector6d result;

  // specify function in cm
  result[0] = alpha * 10;          // x
  result[1] = beta*beta * 10;          // y
  result[2] = 0;  // z

  // transform from centi-meters to meters
  result *= 1e-2;

  // no angle change
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
    std::cout << "initial pose: " << initialPose.transpose() << std::endl; 
    
    // calculate resting pose
    Eigen::Vector6d restingPose;
    //restingPose << 0.209435, -0.470376, 0.5, initialPose[3], initialPose[4], initialPose[5];
    restingPose << 0.257329,  -0.332922,   0.289701  , initialPose[3], initialPose[4], initialPose[5];
    //restingPose << 0.209435, -0.470376, 0.546975, 3.11606, 0.00298367,  0.0321451;
    
    // LinearTrajectory and TrajectoryIteratorCartesianVelocity object creation
    LinearTrajectory linearTrajectory(initialPose, restingPose, 0.5, 0.5, 1.e-3);
    
    // move to resting pose
    auto motionIterator = std::make_unique<TrajectoryIteratorCartesianVelocity>(linearTrajectory);
    
    std::cout << " Robot will move to resting pose, press Enter.";
    std::cin.get();

    panda.control(*motionIterator,
                  /*controller_mode = */ franka::ControllerMode::kCartesianImpedance);
    

    // read current pose for debugging
    franka::RobotState currentState = panda.readOnce();
    Eigen::Vector6d currentPose = homogeneousTfArray2PoseVec(currentState.O_T_EE);

    std::cout << "current pose: " << currentPose.transpose() << std::endl << std::endl;

    // define trajectory from resting pose along curve

    // initialize curve smoothener
    const double samplingTimestepWidth = 1e-3;
    CurveSmoothener::initialize(curve, endTime);
    CurveTrajectory curveTrajectory(restingPose, CurveSmoothener::smoothCurve, endTime, samplingTimestepWidth);

    // move along trajectory
    auto curveMotionIterator = std::make_unique<TrajectoryIteratorCartesianVelocity>(curveTrajectory);
    
    std::cout << "Robot will move according to trajectory, press Enter." << std::endl 
      << "Afterwards, Enter aborts the movement\a";
    std::cin.ignore();

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
