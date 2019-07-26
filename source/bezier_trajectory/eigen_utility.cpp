#include "eigen_utility.h"

Eigen::Vector6d homogeneousTfArray2PoseVec(const std::array<double, 16> &pose_TF_as_array) {

  Eigen::Isometry3d TF = Eigen::Isometry3d::Identity();
  TF.matrix() = Eigen::Matrix<double, 4, 4, Eigen::ColMajor>(pose_TF_as_array.data());
  Eigen::Vector6d pose;
  pose << TF.translation(), TF.rotation().eulerAngles(0, 1, 2);
  return pose;
}

std::array<double, 16> poseVec2HomogeneousTfArray(const Eigen::Vector6d &pose) {
  if (pose(3) < 0 || pose(3) > M_PI || pose(4) < -M_PI || pose(4) > M_PI || pose(5) < -M_PI ||
      pose(5) > M_PI) {
    throw std::invalid_argument("Angle representation is currently constrained to [0,pi], "
                                "[-pi,pi], [-pi,pi] for last 3 elements of pose vector");
  }

  Eigen::Vector3d trans = pose.head(3);
  double roll = pose(3), pitch = pose(4), yaw = pose(5);
  Eigen::AngleAxisd rollAngle(roll, Eigen::Vector3d::UnitX());
  Eigen::AngleAxisd pitchAngle(pitch, Eigen::Vector3d::UnitY());
  Eigen::AngleAxisd yawAngle(yaw, Eigen::Vector3d::UnitZ());

  // ZY'X'' convention, see https://en.wikipedia.org/wiki/Euler_angles#Conventions_2, ch. Tait–Bryan
  // angles
  Eigen::Quaterniond q = rollAngle * pitchAngle * yawAngle; // yawAngle * pitchAngle * rollAngle;

  Eigen::Isometry3d tf = Eigen::Isometry3d::Identity();
  tf.translation() = trans;
  tf.rotate(q);

  std::array<double, 16> v;
  Eigen::Matrix<double, 4, 4, Eigen::ColMajor>::Map(v.data()) = tf.matrix();

  return v;
}
