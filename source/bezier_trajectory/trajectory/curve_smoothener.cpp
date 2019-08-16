#include "curve_smoothener.h"

#include <iostream>
#include "../motion_profile.h"

namespace CurveSmoothener
{

Eigen::Vector6d (*originalCurve) (double t);   //< pointer to the not smoothed curve
double endTime;                     //< the end time of the move
double originalCurveLength;         //< arc length of curve originalCurve, will be initialized by initialize()

void initialize(Eigen::Vector6d (*curve)(double), double endTime)
{
  ::CurveSmoothener::originalCurve = curve;
  ::CurveSmoothener::endTime = endTime;

  // compute curve length of original curve
  originalCurveLength = computeArclengthOriginalCurve(endTime);
  std::cout << "CurveSmoothener: computed arc length of original curve: " << originalCurveLength << std::endl;
}

double computeArclengthOriginalCurve(double time)
{
    const int nSamples = 1000;
    Eigen::Vector3d previousPosition = originalCurve(0).head<3>();
    double curveLength = 0;

    for (int i = 1; i < nSamples; i++)
    {
        double t = time / (nSamples-1);
        Eigen::Vector3d currentPosition = originalCurve(t).head<3>();

        double length = (currentPosition - previousPosition).norm();
        curveLength += length;

        previousPosition = currentPosition;
    }

    return curveLength;
}

Eigen::Vector6d equalizedCurve(double t)
{
  double arclength = t / endTime * originalCurveLength;

  // find parameter for original_curve that gives arclength
  // find originalT such that len(originalCurve(originalT)) = arclength, originalT ∈ [0,endTime]
  // Newton: f(x) = b  =>  x_i+1 = x_i - (f(x_i) - b) / f'(x_i)
  double b = arclength;
  const double epsilon = 1e-5;
  double originalT = 0.5*endTime;  // initial value is center
  double f = computeArclengthOriginalCurve(originalT);

  for (int i = 0; i < 10 && fabs(f - b) > 1e-9; i++)
  {
    // estimate derivative by finite differences
    double leftValue = originalT - epsilon;
    if (leftValue < 0)
      leftValue = 0;

    double rightValue = originalT + epsilon;
    if (rightValue > endTime)
      rightValue = endTime;

    double fprime = (computeArclengthOriginalCurve(rightValue) - computeArclengthOriginalCurve(leftValue)) / (rightValue - leftValue);

    // Newton scheme update
    originalT -= (f - b) / fprime;

    // adjust variable to bounds
    if (originalT < 0)
      originalT = 0;
    else if (originalT > endTime)
      originalT = endTime;

    // compute new value, needed for abortion criterion    
    f = computeArclengthOriginalCurve(originalT);

    //std::cout << "i = " << i << ", t=" << originalT << " left/right: " << leftValue << "/" << rightValue << ", f: " << f << ", b: " << b << ", error: " << (f - b) << std::endl;
  }

  // evaluate curve
  return originalCurve(originalT);
}

Eigen::Vector6d smoothCurve(double t)
{ 
  // compute equalized_curve(motion_profile(t))
  double motionProfile = PolynomialReferenceTraj::evaluate(t / endTime);
  //double motionProfile2 = PolynomialReferenceTraj::evaluate(motionProfile);
  //std::cout << "t: " << t << ", t/endTime: " << t / endTime << ", motionProfile: " << motionProfile << std::endl;
  return equalizedCurve(motionProfile*endTime);
}

};  // namespace
