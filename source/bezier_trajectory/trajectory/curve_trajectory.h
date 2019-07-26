#pragma once

#include <memory>
#include <array>

#include <franka/control_types.h>
#include <franka/robot_state.h>
#include <franka/duration.h>

#include "trajectory.h"

/** \brief implements a trajectory following an arbitrary curve ∈ ℝ^3
 */
class CurveTrajectory : public Trajectory {
public:

  /** \brief create a linear trajectory witgh motion profile between to points (end effector in
   * robot base coordinate system).
   *
   * \arg[in] initialPose starting pose (could be obtained from the robot directly before creation)
   *          [x,y,z, R, P, Y], position in [m], orientation in [rad]
   * \arg[in] curve trajectory curve, offset by initialPose [x,y,z]
   * \arg[in] dt the discretization step width [s]. Must match the execution times in the robot
   * control.
   */
  CurveTrajectory(const Eigen::Vector6d initialPose, Eigen::Vector6d (*curve)(double t), double endTime, double dt);

  /** \brief get the pose values column-wise for the whole trajectory (end effector in robot base),
   * sampled with dt. */
  Eigen::Matrix6dynd p_t() const override;

  /** \brief get the pose velocity values column-wise for the whole trajectory (end effector in
   * robot base), sampled with dt. */
  Eigen::Matrix6dynd dp_dt() const override;

  /** \brief get sample period dt [s] */
  double getDt() const override;

  /** \brief get calculated end time [s] */
  double getTEnd() const override;

protected:
  /// the geometric description of the path
  std::unique_ptr<LinearPath> p_s;

  double endTime_;  // duration of trajectory
  double dt_;  // timstep width or sampling width of the trajectory
  Eigen::Vector6d initialPose_;  // initial pose from where to start trajectory

  Eigen::Vector6d (*curve_)(double t);   // curve that describes the trajectory
};
