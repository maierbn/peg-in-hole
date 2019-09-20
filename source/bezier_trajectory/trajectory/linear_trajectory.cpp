#include "linear_trajectory.h"

#include <iostream>

#include "utility/smooth_motion_profile.h"

LinearTrajectory::LinearTrajectory(const GripperPose &startPose, const GripperPose &endPose, double vMax, double aMax, double dt) :
  startPose_(startPose), endPose_(endPose), vMax_(vMax), aMax_(aMax), dt_(dt)
{
  // compute length of trajectory
  length_ = (endPose_.position - startPose_.position).norm();
}

Eigen::Matrix7dynd LinearTrajectory::poses() const
{
  // this method is called at the beginning of TrajectoryIterator, the result is only used if it is pose controlled

  // get smooth motion profile for dt
  Eigen::VectorXd motionProfileValues = SmoothMotionProfile::s_t(vMax_, aMax_, length_, dt_);

  int nValues = motionProfileValues.size();
  Eigen::Matrix7dynd poses(7,nValues);

  // set translation values
  Eigen::Vector3d direction = endPose_.position - startPose_.position;
  poses.topRows<3>() = startPose_.position * Eigen::VectorXd::Ones(nValues).transpose() + direction * motionProfileValues.transpose();

  // set orientation values
  for (int i = 0; i < nValues; i++)
  {
    double alpha = i/(nValues-1);

    poses.col(i).tail<4>() = startPose_.orientation.slerp(alpha, endPose_.orientation).coeffs();
  }

  return poses;
}

Eigen::Matrix6dynd LinearTrajectory::poseVelocities() const
{
  // get smooth motion profile for dt
  Eigen::VectorXd motionProfileDerivatives = SmoothMotionProfile::ds_dt(vMax_, aMax_, length_, dt_);

  // initialize result matrix
  int nValues = motionProfileDerivatives.size();
  Eigen::Matrix6dynd poseVelocities(6,nValues);

  std::cout << std::endl;
  std::cout << "LinearTrajectory" << std::endl;
  std::cout << "    from: " << startPose_ << std::endl;
  std::cout << "      to: " << endPose_ << std::endl;
  std::cout << "  length: " << length_ << " m, duration: " << endTime() << " s, " << nValues << " steps." << std::endl;
  std::cout << std::endl;


  // get constant derivative of the trajectory without motion profile
  Eigen::Vector6d trajectoryDerivative = startPose_.getDifferenceTo(endPose_);

  // compute the pose velocities as derivative of the linear path times the derivative of the smooth motion profile,
  // column vector * row vector = matrix where each column is for one timestep.
  
  poseVelocities = trajectoryDerivative * motionProfileDerivatives.transpose();

  // output for debugging, disabled
#if 0
  std::cout << "trajectoryDerivative: " << trajectoryDerivative.transpose() << std::endl;
  // check motion profile
  double s = 0;
  for (int i = 0; i < nValues; i++)
  {
    s += motionProfileDerivatives[i] * dt_;
    if (i % 100 == 0)
      std::cout << "i: " << i << ", t: " << i*dt_ << ", dt: " << dt_ << ", derivative: " << motionProfileDerivatives[i] << ", s: " << s << std::endl;
  }
  std::cout << std::endl;

  // check of poseVelocities
  Eigen::Vector6d currentPose = startPose_.getVector6d();
  for (int i = 0; i < nValues; i++)
  {
    currentPose += dt_ * poseVelocities.col(i);
    if (i % 100 == 0)
      std::cout << "i: " << i << ", t: " << i*dt_ << ", dt: " << dt_ << ", derivative: " << poseVelocities.col(i).transpose() << ", pose: " << currentPose.transpose() << std::endl;
  }
#endif

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
