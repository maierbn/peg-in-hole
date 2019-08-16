#include "curve_trajectory.h"

#include <iomanip>
#include <iostream>

CurveTrajectory::CurveTrajectory(const Eigen::Vector6d initialPose, 
  Eigen::Vector6d (*curve)(double t), double endTime, double dt) :
  endTime_(endTime), dt_(dt), initialPose_(initialPose)
{

  // compute dt such that it allows constant time step widths within endTime
  int nSteps = int(round(endTime / dt));
  dt_ = endTime / nSteps;
  std::cout << "dt: " << dt_ << std::endl;

  curve_ = curve;
  curveStartPos_ = curve_(0);
  std::cout << "curveStartPos: " << curveStartPos_.transpose() << std::endl;
}

Eigen::Matrix6dynd CurveTrajectory::p_t() const {
  
  int nSteps = int(round(endTime_ / dt_));
  std::cout << "nSteps: " << nSteps << std::endl;

  Eigen::Matrix6dynd poses(6,nSteps);

  for (int i = 0; i < nSteps; i++)
  {
    // compute current time
    double t = i*dt_;

    Eigen::Vector6d position = curve_(t) - curveStartPos_;
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

    double h = 1e-5;   // do not set too small!
/*
    std::cout << "curve: " << curve_(t+h).transpose() << std::endl;
    std::cout << "curve: " << curve_(t-h).transpose() << std::endl;
*/
    if (t-h < 0)
    {
      Eigen::Vector6d velocity = (curve_(t+h) - curve_(t)) / h;
      velocities.col(i) = velocity;
      //std::cout << "0 v: " << velocities.col(i).transpose() << std::endl;
    }
    else if (t+h > endTime_)
    {
      Eigen::Vector6d velocity = (curve_(t) - curve_(t-h)) / h;
      velocities.col(i) = velocity;
      //std::cout << "2 v: " << velocities.col(i).transpose() << std::endl;
    }
    else
    {
      Eigen::Vector6d velocity = (curve_(t+h) - curve_(t-h)) / (2*h);
      velocities.col(i) = velocity;      
      //std::cout << "  v: " << velocities.col(i).transpose() << std::endl;
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
