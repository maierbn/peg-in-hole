#pragma once

#include <eigen3/Eigen/Eigen>
#include "../eigen_utility.h"

/**
 * This namespace provides functionality to smoothen a trajectory curve,
 * such that it can be executed by the robot arm.
 * This means that the parameterization of a given curve is changed according to a motian profile
 * such that the initial and finial movements are smooth.
 * Note: this is not implemented as a class, because it provides the function smoothCurve 
 * that can be used further.
 */
namespace CurveSmoothener
{

//! initialize the curve smoothener
//! \param curve the (not smooth) curve that should be smoothened
//! \param endTime The curve will be discretized in the interval [0, endTime]
void initialize(Eigen::Vector6d (*curve)(double), double endTime);

//! \brief This is the curve but parameterized with equal velocity, i.e. ||\dot{s}(t)|| = 1  ∀ t ∈ [0,endTime]
//! \arg[in] t evaluation time, t ∈ [0,endTime]
Eigen::Vector6d equalizedCurve(double t);

//! \brief This is the smoothed curve, i.e. equalizedCurve(motionProfile(t))
//! \arg[in] t evaluation time, t ∈ [0,endTime]
Eigen::Vector6d smoothCurve(double t);      //< the smooth curve, evaluation is slow

//! \brief computes the arc length of the original curve from 0 to t
//! \arg[in] t end of the interval for which the length of the graph of the curve should be computed
double computeArclengthOriginalCurve(double t);

extern Eigen::Vector6d (*originalCurve) (double t);   //< pointer to the not smoothed curve
extern double endTime;                     //< the end time of the move
extern double originalCurveLength;         //< arc length of curve originalCurve, will be initialized by initialize()
};
