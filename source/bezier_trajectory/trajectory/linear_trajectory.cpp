#include "linear_trajectory.h"

#include <iostream>

#include "trajectory_utility/smooth_motion_profile.h"

LinearTrajectory::LinearTrajectory(const GripperPose &startPose, const GripperPose &endPose, double vMax, double aMax, double dt) :
  startPose_(startPose), endPose_(endPose), vMax_(vMax), aMax_(aMax), dt_(dt)
{
  // compute length of trajectory
  length_ = (endPose_.position - startPose_.position).norm();

  std::cout << "Linear trajectory has end time " << endTime() << "." << std::endl;
}

Eigen::Matrix7dynd LinearTrajectory::poses() const
{
  // get smooth motion profile for dt
  Eigen::VectorXd motionProfileValues = SmoothMotionProfile::s_t(vMax_, aMax_, length_, dt_);

  int nValues = motionProfileValues.size();
  std::cout << "LinearTrajectory::poses nSteps: " << nValues << std::endl;
  Eigen::Matrix7dynd poses(7,nValues);

  // set translation values
  Eigen::Vector3d direction = endPose_.position - startPose_.position;
  poses.topRows<3>() = startPose_.position * Eigen::VectorXd::Ones(nValues).transpose() + direction * motionProfileValues.transpose();

  // set rotation values
  for (int i = 0; i < nValues; i++)
  {
    double alpha = i/(nValues-1);

    poses.col(i).tail<4>() = startPose_.rotation.slerp(alpha, endPose_.rotation).coeffs();
  }

  return poses;
}

Eigen::Matrix6dynd LinearTrajectory::poseVelocities() const
{
  // get smooth motion profile for dt
  Eigen::VectorXd motionProfileDerivatives = SmoothMotionProfile::ds_dt(vMax_, aMax_, length_, dt_);

  int nValues = motionProfileDerivatives.size();
  std::cout << "LinearTrajectory::poseVelocities nSteps: " << nValues << std::endl;
  Eigen::Matrix6dynd poseVelocities(6,nValues);

  // get constant derivative of the trajectory without motion profile
  Eigen::Vector6d trajectoryDerivative;
  Eigen::Vector3d direction = endPose_.position - startPose_.position;
  trajectoryDerivative.head<3>() = direction / endTime();

#ifndef NDEBUG
  std::cout << "rotation from " << startPose_.rotation.coeffs().transpose()
    << " (" << startPose_.rotation.toRotationMatrix().eulerAngles(0, 1, 2).transpose() / M_PI * 180. << ") to "
    << endPose_.rotation.coeffs().transpose()
    << " (" << endPose_.rotation.toRotationMatrix().eulerAngles(0, 1, 2).transpose() / M_PI * 180. << ") " << std::endl;

  Eigen::Quaterniond rotation = startPose_.rotation.slerp(1.0, endPose_.rotation);

  std::cout << "resulting rotation: " << rotation.coeffs().transpose()
    << " (" << rotation.toRotationMatrix().eulerAngles(0, 1, 2).transpose() / M_PI * 180. << ") " << std::endl;
#endif

  double alpha = 0.5;
  double inverseAlpha = 1./alpha;
  trajectoryDerivative.tail<3>() = startPose_.rotation.slerp(alpha, endPose_.rotation).toRotationMatrix().eulerAngles(0, 1, 2);
  trajectoryDerivative.tail<3>() *= inverseAlpha / endTime();

#ifndef NDEBUG
  std::cout << "trajectoryDerivative: " << trajectoryDerivative.transpose() << std::endl;
#endif

  // compute the pose velocities as derivative of the linear path times the derivative of the smooth motion profile,
  // column vector * row vector = matrix where each column is for one timestep.
  poseVelocities = trajectoryDerivative * motionProfileDerivatives.transpose();

  return poseVelocities;
}

double LinearTrajectory::dt() const
{
  return dt_;
}

double LinearTrajectory::endTime() const
{
  return SmoothMotionProfile::endTime(vMax_, aMax_, length_, dt_);
}
