#pragma once

namespace CurveSmoothener
{

Eigen::Vector6d smoothCurve(double t);   //< the smooth curve, evaluation is slow
Eigen::Vector6d (*originalCurve) (double t);   //< the not smoothed curve
double originalCurveLength;         //< arc length of curve originalCurve

};
