#include "curve_trajectory.h"

#include <iomanip>
#include <iostream>

CurveTrajectory::CurveTrajectory(const GripperPose initialPose,
  std::function<GripperPose (double t)> curve, double endTime, double dt) :
  endTime_(endTime), dt_(dt), initialPose_(initialPose)
{

  // compute dt such that it allows constant time step widths within endTime
  int nSteps = int(round(endTime / dt));
  dt_ = endTime / nSteps;
  std::cout << "dt: " << dt_ << std::endl;

  curve_ = curve;
  curveStartPos_ = curve_(0);
  std::cout << "curveStartPos: " << curveStartPos_.position.transpose() << std::endl;
}

Eigen::Matrix7dynd CurveTrajectory::poses() const {
  
  int nSteps = int(round(endTime_ / dt_));
  std::cout << "nSteps: " << nSteps << std::endl;

  Eigen::Matrix7dynd poses(7,nSteps);

  for (int i = 0; i < nSteps; i++)
  {
    // compute current time
    double t = i*dt_;

    const GripperPose position = curve_(t) - curveStartPos_ + initialPose_;
    poses.col(i) = position.getVector7d();
  }

  return poses;
}

Eigen::Matrix6dynd CurveTrajectory::poseVelocities() const {

  int nSteps = int(round(endTime_ / dt_));
  std::cout << "nSteps: " << nSteps << std::endl;

  Eigen::Matrix6dynd velocities(6,nSteps);

  const double h = 1e-5;   // do not set too small!

  for (int i = 0; i < nSteps; i++)
  {
    // compute current time
    double t = i*dt_;

    //std::cout << "t: " << t << ", curve(" << t << "): " << curve_(t).position.transpose() << std::endl;

    // compute velocity by 2nd order central difference quotient
    Eigen::Vector6d curve0 = curve_(t+h).getVector6d();
    Eigen::Vector6d curve1 = curve_(t-h).getVector6d();

    Eigen::Vector6d difference = curve0 - curve1;

    // account for wrap-around errors in the Euler angles
    for (int j = 3; j < 6; j++)
    {
      if (fabs(difference[j]) > M_PI)
      {
        if (difference[j] > 0)
          difference[j] -= M_2_PI;
        else
          difference[j] += M_2_PI;
      }
    }

    Eigen::Vector6d velocity = difference / (2*h);
    velocities.col(i) = velocity;

    //std::cout << "  v: " << velocities.col(i).transpose() << std::endl;
  }
  return velocities;

}

double CurveTrajectory::dt() const {
  return dt_;
}

double CurveTrajectory::endTime() const {
  return endTime_;
}
