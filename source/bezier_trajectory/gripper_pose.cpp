#include "gripper_pose.h"

GripperPose::GripperPose()
{
  position << 0,0,0;
  rotation = Eigen::Quaterniond::Identity();
}

GripperPose::GripperPose(const GripperPose &rhs) : position(rhs.position), rotation(rhs.rotation)
{
}

GripperPose::GripperPose(const std::array<double, 16> &poseTransformationMatrix)
{
  // store the 4x4 transformation matrix as Eigen data structure
  Eigen::Isometry3d transformation = Eigen::Isometry3d::Identity();
  transformation.matrix() = Eigen::Matrix<double, 4, 4, Eigen::ColMajor>(poseTransformationMatrix.data());

  rotation = transformation.rotation();
  position = transformation.translation();
}

std::array<double, 16> GripperPose::getHomogenousTransformArray()
{
  Eigen::Isometry3d transformation = Eigen::Isometry3d::Identity();
  transformation.translation() = position;
  transformation.rotate(rotation);

  std::array<double, 16> result;
  Eigen::Matrix<double, 4, 4, Eigen::ColMajor>::Map(result.data()) = transformation.matrix();

  return result;
}

Eigen::Vector6d GripperPose::getVector6d() const
{
  Eigen::Vector6d result;

  // initialize the vector values x,y,z, roll,pitch,yaw
  result << position, rotation.toRotationMatrix().eulerAngles(0, 1, 2);
  return result;
}

Eigen::Vector7d GripperPose::getVector7d() const
{
  Eigen::Vector7d result;

  // initialize the vector values x,y,z, qx,qy,qz,qw
  result << position, rotation.coeffs();
  return result;
}

// addition operator
GripperPose GripperPose::operator+(const GripperPose &rhs)
{
  GripperPose result(*this);
  result.position += rhs.position;
  result.rotation.normalize();
  result.rotation *= rhs.rotation;

  return result;
}

// substraction operator
GripperPose GripperPose::operator-(const GripperPose &rhs)
{
  GripperPose result(*this);
  result.position -= rhs.position;
  result.rotation.normalize();
  result.rotation *= rhs.rotation.conjugate();  // reverse rotation

  return result;
}
