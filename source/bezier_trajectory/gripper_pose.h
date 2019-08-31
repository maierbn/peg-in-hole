#pragma once

#include <eigen3/Eigen/Eigen>
#include "eigen_utility.h"

/** Data structure to store gripper position and rotation.
 */
struct GripperPose
{
  Eigen::Vector3d position;       ///< the position stored as [x,y,z]
  Eigen::Quaterniond rotation;    ///< the rotation stored as normalized quaternion, [qx,qy,qz,qw]

  //! constructor, initialize to zero and identity
  GripperPose();

  //! copy constructor
  GripperPose(const GripperPose &rhs);

  //! constructor from affine transformation matrix data, column-major
  GripperPose(const std::array<double, 16> &pose_TF_as_array);

  //! return the values of an affine transformation matrix, column-major
  std::array<double, 16> getHomogenousTransformArray();

  //! return a vector 6d with the entries [x,y,z,roll,pitch,yaw]
  Eigen::Vector6d getVector6d() const;

  //! return a vector 7d with the entries [x,y,z,qx,qy,qz,qw]
  Eigen::Vector7d getVector7d() const;

  // addition operator
  GripperPose operator+(const GripperPose &rhs);

  // substraction operator
  GripperPose operator-(const GripperPose &rhs);
};
