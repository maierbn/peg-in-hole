#include "utility/trajectory_plotter.h"

#include <iostream>

TrajectoryPlotter::TrajectoryPlotter(CartesianPose startPose, std::shared_ptr<Trajectory> trajectory, double samplingTimestepWidth)
{
  // compute poses from poseVelocities to immitate trajectory_iterator_cartesian_velocity
  Eigen::Matrix6dynd poseVelocities = trajectory->poseVelocities();

  int nEntries = poseVelocities.size();
  std::cout << "nEntries: " << nEntries << std::endl;
}