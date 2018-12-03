/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.8
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sgpp;

public class StdNormalDistribution {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected StdNormalDistribution(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(StdNormalDistribution obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        jsgppJNI.delete_StdNormalDistribution(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public StdNormalDistribution() {
    this(jsgppJNI.new_StdNormalDistribution(), true);
  }

  public double getCumulativeDensity(double x) {
    return jsgppJNI.StdNormalDistribution_getCumulativeDensity(swigCPtr, this, x);
  }

  public double getDensity(double x) {
    return jsgppJNI.StdNormalDistribution_getDensity__SWIG_0(swigCPtr, this, x);
  }

  public double getDensity(double x, double mu, double sigma) {
    return jsgppJNI.StdNormalDistribution_getDensity__SWIG_1(swigCPtr, this, x, mu, sigma);
  }

  public double getNormedDensity(double x, double mu, double sigma) {
    return jsgppJNI.StdNormalDistribution_getNormedDensity(swigCPtr, this, x, mu, sigma);
  }

}
