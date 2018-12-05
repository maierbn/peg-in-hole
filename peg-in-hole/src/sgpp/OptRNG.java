/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.8
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sgpp;

public class OptRNG {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected OptRNG(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(OptRNG obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        jsgppJNI.delete_OptRNG(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public static OptRNG getInstance() {
    return new OptRNG(jsgppJNI.OptRNG_getInstance(), false);
  }

  public double getUniformRN(double a, double b) {
    return jsgppJNI.OptRNG_getUniformRN__SWIG_0(swigCPtr, this, a, b);
  }

  public double getUniformRN(double a) {
    return jsgppJNI.OptRNG_getUniformRN__SWIG_1(swigCPtr, this, a);
  }

  public double getUniformRN() {
    return jsgppJNI.OptRNG_getUniformRN__SWIG_2(swigCPtr, this);
  }

  public void getUniformRV(DataVector vector, double a, double b) {
    jsgppJNI.OptRNG_getUniformRV__SWIG_0(swigCPtr, this, DataVector.getCPtr(vector), vector, a, b);
  }

  public void getUniformRV(DataVector vector, double a) {
    jsgppJNI.OptRNG_getUniformRV__SWIG_1(swigCPtr, this, DataVector.getCPtr(vector), vector, a);
  }

  public void getUniformRV(DataVector vector) {
    jsgppJNI.OptRNG_getUniformRV__SWIG_2(swigCPtr, this, DataVector.getCPtr(vector), vector);
  }

  public long getUniformIndexRN(long size) {
    return jsgppJNI.OptRNG_getUniformIndexRN(swigCPtr, this, size);
  }

  public double getGaussianRN(double mean, double stdDev) {
    return jsgppJNI.OptRNG_getGaussianRN__SWIG_0(swigCPtr, this, mean, stdDev);
  }

  public double getGaussianRN(double mean) {
    return jsgppJNI.OptRNG_getGaussianRN__SWIG_1(swigCPtr, this, mean);
  }

  public double getGaussianRN() {
    return jsgppJNI.OptRNG_getGaussianRN__SWIG_2(swigCPtr, this);
  }

  public void getGaussianRV(DataVector vector, double mean, double stdDev) {
    jsgppJNI.OptRNG_getGaussianRV__SWIG_0(swigCPtr, this, DataVector.getCPtr(vector), vector, mean, stdDev);
  }

  public void getGaussianRV(DataVector vector, double mean) {
    jsgppJNI.OptRNG_getGaussianRV__SWIG_1(swigCPtr, this, DataVector.getCPtr(vector), vector, mean);
  }

  public void getGaussianRV(DataVector vector) {
    jsgppJNI.OptRNG_getGaussianRV__SWIG_2(swigCPtr, this, DataVector.getCPtr(vector), vector);
  }

  public SWIGTYPE_p_std__mt19937__result_type getSeed() {
    return new SWIGTYPE_p_std__mt19937__result_type(jsgppJNI.OptRNG_getSeed(swigCPtr, this), true);
  }

  public void setSeed() {
    jsgppJNI.OptRNG_setSeed__SWIG_0(swigCPtr, this);
  }

  public void setSeed(SWIGTYPE_p_std__mt19937__result_type seed) {
    jsgppJNI.OptRNG_setSeed__SWIG_1(swigCPtr, this, SWIGTYPE_p_std__mt19937__result_type.getCPtr(seed));
  }

}
