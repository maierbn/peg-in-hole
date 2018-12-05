/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.8
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sgpp;

public class OperationInverseRosenblattTransformationKDE {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected OperationInverseRosenblattTransformationKDE(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(OperationInverseRosenblattTransformationKDE obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        jsgppJNI.delete_OperationInverseRosenblattTransformationKDE(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public OperationInverseRosenblattTransformationKDE(GaussianKDE kde, double sigmaFactor, double inversionEpsilon) {
    this(jsgppJNI.new_OperationInverseRosenblattTransformationKDE__SWIG_0(GaussianKDE.getCPtr(kde), kde, sigmaFactor, inversionEpsilon), true);
  }

  public OperationInverseRosenblattTransformationKDE(GaussianKDE kde, double sigmaFactor) {
    this(jsgppJNI.new_OperationInverseRosenblattTransformationKDE__SWIG_1(GaussianKDE.getCPtr(kde), kde, sigmaFactor), true);
  }

  public OperationInverseRosenblattTransformationKDE(GaussianKDE kde) {
    this(jsgppJNI.new_OperationInverseRosenblattTransformationKDE__SWIG_2(GaussianKDE.getCPtr(kde), kde), true);
  }

  public void doTransformation(DataMatrix pointsUniform, DataMatrix pointsCdf) {
    jsgppJNI.OperationInverseRosenblattTransformationKDE_doTransformation(swigCPtr, this, DataMatrix.getCPtr(pointsUniform), pointsUniform, DataMatrix.getCPtr(pointsCdf), pointsCdf);
  }

  public void doShuffledTransformation(DataMatrix pointsUniform, DataMatrix pointsCdf) {
    jsgppJNI.OperationInverseRosenblattTransformationKDE_doShuffledTransformation(swigCPtr, this, DataMatrix.getCPtr(pointsUniform), pointsUniform, DataMatrix.getCPtr(pointsCdf), pointsCdf);
  }

  public double doTransformation1D(double y, DataVector samples1d, double sigma, double xlower, double xupper, double ylower, double yupper, DataVector kern) {
    return jsgppJNI.OperationInverseRosenblattTransformationKDE_doTransformation1D(swigCPtr, this, y, DataVector.getCPtr(samples1d), samples1d, sigma, xlower, xupper, ylower, yupper, DataVector.getCPtr(kern), kern);
  }

  public double getMaxInversionError() {
    return jsgppJNI.OperationInverseRosenblattTransformationKDE_getMaxInversionError(swigCPtr, this);
  }

}
