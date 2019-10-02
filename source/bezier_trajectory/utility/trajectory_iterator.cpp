#include "utility/trajectory_iterator.h"

#include <iostream>
#include "utility/kbhit.h"

TrajectoryIteratorCartesian::TrajectoryIteratorCartesian(const Trajectory &trajectory)
    : poses_(trajectory.poses()), poseVelocitiesEulerAngles_(trajectory.poseVelocities()), dt_(trajectory.dt()), endTime_(trajectory.endTime()), currentIndex_(0)
{

}

std::array<double, 16> TrajectoryIteratorCartesian::getCartesianPose() const
{
  const Eigen::Vector7d currentPose = this->poses_.col(this->currentIndex_);

  Eigen::Isometry3d transformation = Eigen::Isometry3d::Identity();
  transformation.translation() = currentPose.head<3>();

  Eigen::Quaterniond orientation(currentPose[6], currentPose[3], currentPose[4], currentPose[5]);  // qw,qx,qy,qz
  transformation.rotate(orientation);

  std::array<double, 16> result;
  Eigen::Matrix<double, 4, 4, Eigen::ColMajor>::Map(result.data()) = transformation.matrix();

  return result;
}

std::array<double,6> TrajectoryIteratorCartesian::getCartesianVelocity() const
{
  const Eigen::Vector6d currentVelocity = this->poseVelocitiesEulerAngles_.col(this->currentIndex_);

  std::array<double,6> result;
  Eigen::Vector6d::Map(result.data()) = currentVelocity;
  return result;
}

void TrajectoryIteratorCartesian::step()
{
  currentIndex_++;
}

double TrajectoryIteratorCartesian::currentTime() const
{
  return currentTime_; 
}

double TrajectoryIteratorCartesian::getEndTime() const
{
  return this->endTime_;
}

franka::CartesianPose TrajectoryIteratorCartesian::
operator()(const franka::RobotState &, franka::Duration time_step)
{
  currentTime_ += time_step.toSec();

  int i = 0;
  for (; currentTime_ >= (this->currentIndex_+1) * this->dt_; i++)
    this->currentIndex_++;

  // only do expensive checks after 100 iterations
  if (this->currentIndex_ % 100 == 0)
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

  if (currentTime() < getEndTime()) {
    return franka::CartesianPose(getCartesianPose());
  } else {
    return franka::MotionFinished(franka::CartesianPose(getCartesianPose()));
  }
}
