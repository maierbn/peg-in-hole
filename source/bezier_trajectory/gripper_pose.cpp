#include "gripper_pose.h"

#include <iostream>

const Eigen::Quaterniond GripperPose::neutralOrientation(0.0, 1.0, 0.0, 0.0);

GripperPose::GripperPose()
{
  position << 0,0,0;
  orientation = neutralOrientation;
}

GripperPose::GripperPose(const GripperPose &rhs) : position(rhs.position), orientation(rhs.orientation)
{
}

GripperPose::GripperPose(const std::array<double, 16> &poseTransformationMatrix)
{
  // Parameter is e.g. from franka::RobotState::O_T_EE
  // see: https://frankaemika.github.io/libfranka/structfranka_1_1RobotState.html#a193781d47722b32925e0ea7ac415f442
  // Pose is represented as a 4x4 matrix in column-major format. 

  // store the 4x4 transformation matrix as Eigen data structure
  Eigen::Isometry3d transformation = Eigen::Isometry3d::Identity();
  transformation.matrix() = Eigen::Matrix<double, 4, 4, Eigen::ColMajor>(poseTransformationMatrix.data());

  orientation = transformation.rotation();
  position = transformation.translation();

  //std::cout << "initialize GripperPose from transformation matrix: \n\ttranslation: " << position.transpose() << std::endl;
  //std::cout << "\torientation: (qx,qy,qz,qw) = " << orientation.coeffs().transpose() << ", Euler angles: " << orientation.toRotationMatrix().eulerAngles(0, 1, 2).transpose() << std::endl;
    
}

GripperPose::GripperPose(Eigen::Vector3d _position, Eigen::Quaterniond _orientation) : position(_position), orientation(_orientation)
{
}

std::array<double, 16> GripperPose::getHomogenousTransformArray()
{
  Eigen::Isometry3d transformation = Eigen::Isometry3d::Identity();
  transformation.translation() = position;
  transformation.rotate(orientation);

  std::array<double, 16> result;
  Eigen::Matrix<double, 4, 4, Eigen::ColMajor>::Map(result.data()) = transformation.matrix();

  return result;
}

Eigen::Vector6d GripperPose::getVector6d() const
{
  Eigen::Vector6d result;

  // initialize the vector values x,y,z, roll,pitch,yaw
  result << position, orientation.toRotationMatrix().eulerAngles(0, 1, 2);
  return result;
}

Eigen::Vector7d GripperPose::getVector7d() const
{
  Eigen::Vector7d result;

  // initialize the vector values x,y,z, qx,qy,qz,qw
  result << position, orientation.coeffs();
  return result;
}

// addition operator
GripperPose GripperPose::operator+(const GripperPose &rhs)
{
  GripperPose result(*this);
  result.position += rhs.position;
  result.orientation.normalize();
  result.orientation *= rhs.orientation;

  return result;
}

// substraction operator
GripperPose GripperPose::operator-(const GripperPose &rhs)
{
  GripperPose result(*this);
  result.position -= rhs.position;
  result.orientation.normalize();
  result.orientation *= rhs.orientation.conjugate();  // reverse orientation

  return result;
}

Eigen::Vector6d GripperPose::getDifferenceTo(const GripperPose &rhs) const
{
  Eigen::Vector6d difference;

  // compute translation
  difference.head<3>() = rhs.position - position;

  // debugging output
/*#ifndef NDEBUG
  std::cout << "orientation from (qx,qy,qz,qw)=" << orientation.coeffs().transpose()
    << ", Euler (deg): " << orientation.toRotationMatrix().eulerAngles(0, 1, 2).transpose() / M_PI * 180. << std::endl 
    << "           to (qx,qy,qz,qw)=" << rhs.orientation.coeffs().transpose()
    << ", Euler (deg): " << rhs.orientation.toRotationMatrix().eulerAngles(0, 1, 2).transpose() / M_PI * 180. << " " << std::endl;

  // slerp reference: https://eigen.tuxfamily.org/dox/classEigen_1_1QuaternionBase.html#ac840bde67d22f2deca330561c65d144e
#endif*/

  // Compute the rotation from orientation1 to orientation2.
  // Determine the Euler-angles for (orientation1 to orientation2) and the negative EUler-angles for (orientation2 to orientation1).
  // Use the set of Euler-angles with the smaller norm.

  // compute orientation
  Eigen::Quaterniond rotation = rhs.orientation * orientation.inverse();

  Eigen::Vector3d eulerAnglesForward = rotation.toRotationMatrix().eulerAngles(0, 1, 2);
  Eigen::Vector3d eulerAnglesBackward = -rotation.inverse().toRotationMatrix().eulerAngles(0, 1, 2);

  // select the euler angles with smaller norm
  difference.tail<3>() = eulerAnglesForward;
  if (eulerAnglesBackward.norm() < eulerAnglesForward.norm())
  {
    difference.tail<3>() = eulerAnglesBackward;
  }

  return difference;
}

std::ostream &operator<<(std::ostream &stream, const GripperPose &rhs)
{
  stream << "(" << rhs.position[0] << "," << rhs.position[1] << "," << rhs.position[2] << ")  ("
    << rhs.orientation.toRotationMatrix().eulerAngles(0, 1, 2).transpose() / M_PI * 180. << ")";
  return stream;
}