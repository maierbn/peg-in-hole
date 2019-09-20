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

  curve_ = curve;
  curveStartPose_ = curve_(0);
}

Eigen::Matrix7dynd CurveTrajectory::poses() const {
  
  int nSteps = int(round(endTime_ / dt_));

  Eigen::Matrix7dynd poses(7,nSteps);

  for (int i = 0; i < nSteps; i++)
  {
    // compute current time
    double t = i*dt_;

    const GripperPose position = curve_(t) - curveStartPose_ + initialPose_;
    poses.col(i) = position.getVector7d();
  }

  return poses;
}

Eigen::Matrix6dynd CurveTrajectory::poseVelocities() const
{
  Eigen::Vector6d velocity;

  int nSteps = int(round(endTime_ / dt_));

  GripperPose endPose = curve_(endTime_) - curveStartPose_ + initialPose_;

  std::cout << std::endl;
  std::cout << "CurveTrajectory" << std::endl;
  std::cout << "    from: " << initialPose_ << std::endl;
  std::cout << "      to: " << endPose << std::endl;
  std::cout << "  dt: " << dt_ << " m, duration: " << endTime_ << " s, " << nSteps << " steps." << std::endl;
  std::cout << std::endl;

  Eigen::Matrix6dynd velocities(6,nSteps);

  const double h = 1e-5;   // do not set too small!

  for (int i = 0; i < nSteps; i++)
  {
    // compute current time
    double t = i*dt_;

    //std::cout << "t: " << t << ", curve(" << t << "): " << curve_(t).position.transpose() << std::endl;

    // compute velocity by 2nd order central difference quotient
    GripperPose curve0 = curve_(t-h);
    GripperPose curve1 = curve_(t+h);

    // compute translational and rotational velocity
    Eigen::Vector6d velocity = curve0.getDifferenceTo(curve1) / (2*h);

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
