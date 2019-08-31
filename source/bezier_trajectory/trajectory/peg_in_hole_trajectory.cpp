#include "peg_in_hole_trajectory.h"

PegInHoleTrajectory::PegInHoleTrajectory(GripperPose initialPose, GripperPose targetPose, double initialAngle, Eigen::Vector3d controlPoint,
                                         double endTime, double dt) :
  SmoothCurveTrajectory(initialPose,
    [&](double t) -> GripperPose
    {
      // determine control points for translation
      Eigen::Vector3d controlPoint0 = initialPose.position;

      Eigen::Vector3d controlPoint1;
      controlPoint1[0] = -controlPoint[0];   // x
      controlPoint1[1] = initialPose.position[1];   // y
      controlPoint1[2] = controlPoint[1];   // z

      Eigen::Vector3d controlPoint2 = targetPose.position;
      controlPoint2[1] = initialPose.position[1];   // y

      // compute translation
      GripperPose result;
      result.position = controlPoint0 * bernstein(0,t) + controlPoint1 * bernstein(1,t) + controlPoint2 * bernstein(2,t);

      // compute rotation
      double angle0 = initialAngle;
      double angle1 = controlPoint[2];
      double angle2 = 0;

      double angle = angle0 * bernstein(0,t) + angle1 * bernstein(1,t) + angle2 * bernstein(2,t);

      Eigen::AngleAxisd angleAxis(angle, -Eigen::Vector3d::UnitY());
      Eigen::Quaterniond q(angleAxis);

      result.rotation = PegInHoleTrajectory::rotateHorizontal() * q;
      result.rotation.normalize();

      return result;
    },
    endTime, dt
  )
{
}

Eigen::Quaterniond PegInHoleTrajectory::rotateHorizontal()
{
  // TODO: try out
  Eigen::AngleAxisd angle(M_PI_2, -Eigen::Vector3d::UnitY());
  return Eigen::Quaterniond(angle);
}

/**
  * factorial function limited for 0, 1 and 2 for internal use in bernstein
  * polynomial calculations
  *
  * @param i the integer that should be fac'd
  * @return i! or 0 if input out of range
  */
double PegInHoleTrajectory::fac(int i)
{
  switch (i) {
  case 0:
    return 1;
  case 1:
    return 1;
  case 2:
    return 2;
  default:
    return 0;
  }
}

/**
  * solves the bernstein polynomial for hardcoded degree n=2
  *
  * @param i range 0..2
  * @param t position on curve, 0..1
  * @return (n over i) * (1-t)^n-i * t^i
  */
double PegInHoleTrajectory::bernstein(int i, double t)
{
  return (fac(2) / (fac(i) * fac(2 - i))) * std::pow(1 - t, 2 - i) * std::pow(t, i);
}
