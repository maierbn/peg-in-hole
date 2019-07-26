#pragma once

#include <memory>
#include <array>

#include <franka/control_types.h>
#include <franka/robot_state.h>
#include <franka/duration.h>

#include "../path.h"
#include "../motion_profile.h"
#include "trajectory.h"

/** \brief implements a cartesian linear trajectory between two points.
 *
 * The motion is determined via a polynomial reference trajectory, which is guaranteed to keep the
 * given dynamics limits.
 *
 * \note The trajectory is not time-optimal w.r.t. the dynamics limits.
 *
 * \warning: jerk limits are not yet implemented.
 */
class LinearTrajectory : public Trajectory {
public:

  /** \brief create a linear trajectory witgh motion profile between to points (end effector in
   * robot base coordinate system).
   *
   * \arg[in] from starting pose (could be obtained from the robot directly before creation)
   *          [x,y,z, R, P, Y], position in [m], orientation in [rad]
   * \arg[in] to goal position [x,y,z, R, P, Y]
   * \arg[in] v_max the maximum allowed velocity [m/s, rad/s]
   * \arg[in] a_max the maximum allowed acceleration [m/s², rad/s²]
   * \arg[in] j_max the maximum allowed jerk [m/s³, rad/s³]
   * (**This is not yet kept.**)
   * \arg[in] dt the discretization step width [s]. Must match the execution times in the robot
   * control.
   */
  LinearTrajectory(const Eigen::Vector6d &from, const Eigen::Vector6d &to, double v_max,
                   double a_max, double dt);

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

  /// the polynomial mapping between path coordinate and time.
  std::unique_ptr<MotionProfile> s_t;

};
