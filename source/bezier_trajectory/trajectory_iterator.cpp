#include "trajectory_iterator.h"

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

double TrajectoryIteratorCartesian::getCurrentTime() const { return this->itr * this->dt; }
double TrajectoryIteratorCartesian::getEndTime() const { return this->t_E; }

franka::CartesianVelocities TrajectoryIteratorCartesianVelocity::
operator()(const franka::RobotState &, franka::Duration) {
  auto cartesianVelDes = franka::CartesianVelocities(getCartesianVelocity());
  step();

  if (getCurrentTime() < getEndTime()) {
    return cartesianVelDes;
  } else {
    return franka::MotionFinished(cartesianVelDes);
  }
}
