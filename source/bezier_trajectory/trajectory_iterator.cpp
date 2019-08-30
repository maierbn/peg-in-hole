#include "trajectory_iterator.h"

#include <iostream>
#include "kbhit.h"

TrajectoryIteratorCartesian::TrajectoryIteratorCartesian(const Trajectory &traj)
    : p_t(traj.p_t()), dp_dt(traj.dp_dt()), dt(traj.getDt()), t_E(traj.getTEnd()), itr(0) {}

std::array<double, 16> TrajectoryIteratorCartesian::getCartesianPose() const {
  const Eigen::Vector6d currentPose = this->p_t.col(this->itr);

  return poseVec2HomogeneousTfArray(currentPose);
}

std::array<double, 6> TrajectoryIteratorCartesian::getCartesianVelocity() const {
  const Eigen::Vector6d currentVel = this->dp_dt.col(this->itr);
  std::array<double, 6> retVal;
  Eigen::Vector6d::Map(retVal.data()) = currentVel;
  return retVal;
}

void TrajectoryIteratorCartesian::step() { itr = itr + 1; }

double TrajectoryIteratorCartesian::getCurrentTime() const { 
  return currentTime_; 
}
double TrajectoryIteratorCartesian::getEndTime() const { return this->t_E; }

franka::CartesianVelocities TrajectoryIteratorCartesianVelocity::
operator()(const franka::RobotState &, franka::Duration time_step) {
  auto cartesianVelDes = franka::CartesianVelocities(getCartesianVelocity());
  
  currentTime_ += time_step.toSec();

  int i = 0;
  for (; currentTime_ >= (this->itr+1) * this->dt; i++)
    this->itr++;

  // only do expensive checks after 100 iterations
  if (this->itr % 100 == 0)
  {
    // check if key was pressed, then abort
    if ( _kbhit())
    {
      std::cout << "Key was pressed, abort!" << std::endl;
      throw std::exception();
    }

    // check if proceeded further than one sample
    if (i > 1)
      std::cout << "Warning: Sampling width is too small, jumping to " << i << "th sample!" << std::endl;
  }

  if (getCurrentTime() < getEndTime()) {
    if (true)
    {
      std::cout << "t: " << currentTime_ << ", cartVelocity: " 
        << "  vx: " << cartesianVelDes.O_dP_EE[0] << "," 
        << "  vy: " << cartesianVelDes.O_dP_EE[1] << "," 
        << "  vz: " << cartesianVelDes.O_dP_EE[2] << "," 
        << "  omegax: " << cartesianVelDes.O_dP_EE[3] << "," 
        << "  omegay: " << cartesianVelDes.O_dP_EE[4] << "," 
        << "  omegaz: " << cartesianVelDes.O_dP_EE[5] << std::endl;
    }
    return cartesianVelDes;
  } else {
    return franka::MotionFinished(cartesianVelDes);
  }
}
