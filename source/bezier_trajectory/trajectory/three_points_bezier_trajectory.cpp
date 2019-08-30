#include "three_points_bezier_trajectory.h"

ThreePointsBezierTrajectory::ThreePointsBezierTrajectory(
  const Eigen::Vector6d initialPose, std::array<Eigen::Vector3d,3> controlPoints, double endTime, double dt) : 
  CurveTrajectory(
    initialPose, [&](double t) -> Eigen::Vector6d{
      Eigen::Vector6d result;
      result << controlPoints[0], controlPoints[1], controlPoints[2], initialPose[4], initialPose[5];
      return result;
    },
    endTime, dt
  )
{
}