#pragma once

#include "eigen_utility.h"
#include "smooth_curve_trajectory.h"

/** \brief implements a trajectory following a bezier trajectory with 3 control points.
 */
class PegInHoleTrajectory : public SmoothCurveTrajectory
{
public:

  /** \brief create a linear trajectory witgh motion profile between to points (end effector in
   * robot base coordinate system).
   *
   * \arg[in] initialPose starting pose (could be obtained from the robot directly before creation)
   *          [x,y,z, R, P, Y], position in [m], orientation in [rad]
   * \arg[in] controlPoints control points of the bezier curve, [x,y,angle] per point
   * \arg[in] endTime duration of the trajectory
   * \arg[in] dt the discretization step width [s].
   */
  PegInHoleTrajectory(GripperPose initialPose, GripperPose targetPose, double initialAngle, Eigen::Vector3d controlPoint,
                                         double endTime, double dt);

  //! rotation to horizontal gripper position
  static Eigen::Quaterniond rotateHorizontal();
private:

	/**
	 * factorial function limited for 0, 1 and 2 for internal use in bernstein
	 * polynomial calculations
	 *
	 * @param i the integer that should be fac'd
	 * @return i! or 0 if input out of range
	 */
	static double fac(int i);

	/**
	 * solves the bernstein polynomial for hardcoded degree n=2
	 *
	 * @param i range 0..2
	 * @param t position on curve, 0..1
	 * @return (n over i) * (1-t)^n-i * t^i
	 */
	static double bernstein(int i, double t);
};