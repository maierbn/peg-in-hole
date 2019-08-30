#pragma once

#include "curve_trajectory.h"

/** \brief implements a trajectory following a bezier trajectory with 3 control points.
 */
class ThreePointsBezierTrajectory : public CurveTrajectory {
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
  ThreePointsBezierTrajectory(const Eigen::Vector6d initialPose, std::array<Eigen::Vector3d,3> controlPoints, double endTime, double dt);

};
