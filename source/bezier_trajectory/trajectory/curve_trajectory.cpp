#include "curve_trajectory.h"


CurveTrajectory::CurveTrajectory(const Eigen::Vector6d &initialPose, Eigen::Vector3d (*curve)(double t), double dt) {

}

Eigen::Matrix6dynd CurveTrajectory::p_t() const {

}

Eigen::Matrix6dynd CurveTrajectory::dp_dt() const {

}

double CurveTrajectory::getDt() const {  }

double CurveTrajectory::getTEnd() const {  }
