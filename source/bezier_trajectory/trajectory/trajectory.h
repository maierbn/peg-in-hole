#pragma once

#include <memory>
#include <array>

#include <franka/control_types.h>
#include <franka/robot_state.h>
#include <franka/duration.h>

#include "../path.h"
#include "../motion_profile.h"

/** \brief base class for a cartesian trajectory */
class Trajectory {
public:
  virtual ~Trajectory() {}

  /** \brief interface to get the pose values column-wise for the whole trajectory, sampled with dt
   */
  virtual Eigen::Matrix6dynd p_t() const = 0;

  /** \brief interface to get the pose velocity values column-wise for the whole trajectory, sampled
   * with dt */
  virtual Eigen::Matrix6dynd dp_dt() const = 0;

  /** \brief interface to get sample period dt [s] */
  virtual double getDt() const = 0;

  /** \brief interface to get calculated end time [s] */
  virtual double getTEnd() const = 0;
};
