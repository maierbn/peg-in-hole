#include "curve_trajectory.h"


CurveTrajectory::CurveTrajectory(const Eigen::Vector6d &initialPose, Eigen::Vector3d (*curve3d)(double t), double endTime, double dt) :
  endTime_(endTime), dt_(dt), curve_(curve)
{
  // compute dt such that it allows constant time step widths within endTime
  int nSteps = int(round(endTime / dt));
  dt_ = endTime / nSteps;
}

Eigen::Matrix6dynd CurveTrajectory::p_t() const {
  Eigen::Matrix6dynd poses;

  int nSteps = int(round(endTime / dt));

  for (int i = 0; i < nSteps; i++)
  {
    // compute current time
    double t = i*dt_;

    //Eigen::Vector3d (*curve)(double t)
    Eigen::Vector3d position = curve_(t);
  }
}

Eigen::Matrix6dynd CurveTrajectory::dp_dt() const {

}

double CurveTrajectory::getDt() const {
  return dt_;
}

double CurveTrajectory::getTEnd() const {
  return endTime_;
}
