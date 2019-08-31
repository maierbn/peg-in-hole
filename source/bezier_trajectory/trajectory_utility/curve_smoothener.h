#pragma once

#include <eigen3/Eigen/Eigen>

#include "eigen_utility.h"
#include "gripper_pose.h"

/**
 * This namespace provides functionality to smoothen a trajectory curve,
 * such that it can be executed by the robot arm.
 * This means that the parameterization of a given curve is changed according to a motian profile
 * such that the initial and finial movements are smooth.
 * Note: this is not implemented as a class, because it provides the function smoothCurve 
 * that can be used further.
 */
class CurveSmoothener
{
public:

  //! initialize the curve smoothener
  //! \param curve the (not smooth) curve that should be smoothened
  //! \param endTime The curve will be discretized in the interval [0, endTime]
  CurveSmoothener(std::function<GripperPose(double)> curve, double endTime);

  //! \brief This is the curve but parameterized with equal velocity, i.e. ||dot{s} (t)|| = 1  ∀ t ∈ [0,endTime]
  //! \arg[in] t evaluation time, t ∈ [0,endTime]
  std::function<GripperPose(double t)> equalizedCurve();

  //! \brief This is the smoothed curve, i.e. equalizedCurve(motionProfile(t))
  //! \arg[in] t evaluation time, t ∈ [0,endTime]
  std::function<GripperPose(double t)> smoothCurve();

private:

  //! \brief computes the arc length of the original curve from 0 to t
  //! \arg[in] t end of the interval for which the length of the graph of the curve should be computed
  double computeArclengthOriginalCurve(double t);

  std::function<GripperPose (double t)> originalCurve_;   //< pointer to the not smoothed curve
  double endTime_;                     //< the end time of the move
  double originalCurveLength_;         //< arc length of curve originalCurve, will be initialized by initialize()

  std::function<GripperPose(double)> equalizedCurve_;   //< equally parameterized curve, i.e. constant velocity when parameter t is increased
  std::function<GripperPose(double)> smoothCurve_;      //< the smooth curve with the motion profile, evaluation is slow
};
