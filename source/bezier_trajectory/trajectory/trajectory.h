#pragma once

#include <memory>
#include <array>

#include <franka/control_types.h>
#include <franka/robot_state.h>
#include <franka/duration.h>

#include "utility/eigen_utility.h"

/** \brief base class for a cartesian trajectory */
class Trajectory {
public:
  virtual ~Trajectory() {}

  /** \brief interface to get the pose values column-wise for the whole trajectory, sampled with dt, orientation as quaternions
   */
  virtual Eigen::Matrix7dynd poses() const = 0;

  /** \brief interface to get the pose velocity values column-wise for the whole trajectory, sampled
   * with dt, orientations as Euler angles */
  virtual Eigen::Matrix6dynd poseVelocities() const = 0;

  /** \brief interface to get sample period dt [s] */
  virtual double dt() const = 0;

  /** \brief interface to get calculated end time [s] */
  virtual double endTime() const = 0;
};
