#include "curve_trajectory.h"


CurveTrajectory::CurveTrajectory(const Eigen::Vector6d initialPose, 
  Eigen::Vector6d (*curve)(double t), double endTime, double dt) :
  endTime_(endTime), dt_(dt), initialPose_(initialPose)
{
  // compute dt such that it allows constant time step widths within endTime
  int nSteps = int(round(endTime / dt));
  dt_ = endTime / nSteps;

  curve_ = curve;
}

Eigen::Matrix6dynd CurveTrajectory::p_t() const {
  
  int nSteps = int(round(endTime_ / dt_));

  Eigen::Matrix6dynd poses(6,nSteps);

  for (int i = 0; i < nSteps; i++)
  {
    // compute current time
    double t = i*dt_;

    Eigen::Vector6d position = curve_(t);
    poses.col(i) = position + initialPose_;
  }

  return poses;
}

Eigen::Matrix6dynd CurveTrajectory::dp_dt() const {

  int nSteps = int(round(endTime_ / dt_));

  Eigen::Matrix6dynd velocities(6,nSteps);

  for (int i = 0; i < nSteps; i++)
  {
    // compute current time
    double t = i*dt_;

    double h = 1e-12;

    if (t-h < 0)
    {
      Eigen::Vector6d velocity = (curve_(t+h) - curve_(t)) / h;
      velocities.col(i) = velocity;
    }
    else if (t+h > endTime_)
    {
      Eigen::Vector6d velocity = (curve_(t) - curve_(t-h)) / h;
      velocities.col(i) = velocity;
    }
    else
    {
      Eigen::Vector6d velocity = (curve_(t+h) - curve_(t-h)) / (2*h);
      velocities.col(i) = velocity;      
    }
  }

  return velocities;

}

double CurveTrajectory::getDt() const {
  return dt_;
}

double CurveTrajectory::getTEnd() const {
  return endTime_;
}
