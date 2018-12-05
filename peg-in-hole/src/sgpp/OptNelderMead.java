/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.8
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sgpp;

public class OptNelderMead extends OptUnconstrainedOptimizer {
  private transient long swigCPtr;

  protected OptNelderMead(long cPtr, boolean cMemoryOwn) {
    super(jsgppJNI.OptNelderMead_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(OptNelderMead obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        jsgppJNI.delete_OptNelderMead(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public OptNelderMead(OptScalarFunction f, long maxFcnEvalCount, double alpha, double beta, double gamma, double delta) {
    this(jsgppJNI.new_OptNelderMead__SWIG_0(OptScalarFunction.getCPtr(f), f, maxFcnEvalCount, alpha, beta, gamma, delta), true);
  }

  public OptNelderMead(OptScalarFunction f, long maxFcnEvalCount, double alpha, double beta, double gamma) {
    this(jsgppJNI.new_OptNelderMead__SWIG_1(OptScalarFunction.getCPtr(f), f, maxFcnEvalCount, alpha, beta, gamma), true);
  }

  public OptNelderMead(OptScalarFunction f, long maxFcnEvalCount, double alpha, double beta) {
    this(jsgppJNI.new_OptNelderMead__SWIG_2(OptScalarFunction.getCPtr(f), f, maxFcnEvalCount, alpha, beta), true);
  }

  public OptNelderMead(OptScalarFunction f, long maxFcnEvalCount, double alpha) {
    this(jsgppJNI.new_OptNelderMead__SWIG_3(OptScalarFunction.getCPtr(f), f, maxFcnEvalCount, alpha), true);
  }

  public OptNelderMead(OptScalarFunction f, long maxFcnEvalCount) {
    this(jsgppJNI.new_OptNelderMead__SWIG_4(OptScalarFunction.getCPtr(f), f, maxFcnEvalCount), true);
  }

  public OptNelderMead(OptScalarFunction f) {
    this(jsgppJNI.new_OptNelderMead__SWIG_5(OptScalarFunction.getCPtr(f), f), true);
  }

  public OptNelderMead(OptNelderMead other) {
    this(jsgppJNI.new_OptNelderMead__SWIG_6(OptNelderMead.getCPtr(other), other), true);
  }

  public void optimize() {
    jsgppJNI.OptNelderMead_optimize(swigCPtr, this);
  }

  public double getAlpha() {
    return jsgppJNI.OptNelderMead_getAlpha(swigCPtr, this);
  }

  public void setAlpha(double alpha) {
    jsgppJNI.OptNelderMead_setAlpha(swigCPtr, this, alpha);
  }

  public double getBeta() {
    return jsgppJNI.OptNelderMead_getBeta(swigCPtr, this);
  }

  public void setBeta(double beta) {
    jsgppJNI.OptNelderMead_setBeta(swigCPtr, this, beta);
  }

  public double getGamma() {
    return jsgppJNI.OptNelderMead_getGamma(swigCPtr, this);
  }

  public void setGamma(double gamma) {
    jsgppJNI.OptNelderMead_setGamma(swigCPtr, this, gamma);
  }

  public double getDelta() {
    return jsgppJNI.OptNelderMead_getDelta(swigCPtr, this);
  }

  public void setDelta(double delta) {
    jsgppJNI.OptNelderMead_setDelta(swigCPtr, this, delta);
  }

  public void clone(SWIGTYPE_p_std__unique_ptrT_sgpp__optimization__optimizer__UnconstrainedOptimizer_t clone) {
    jsgppJNI.OptNelderMead_clone(swigCPtr, this, SWIGTYPE_p_std__unique_ptrT_sgpp__optimization__optimizer__UnconstrainedOptimizer_t.getCPtr(clone));
  }

  public final static double DEFAULT_ALPHA = jsgppJNI.OptNelderMead_DEFAULT_ALPHA_get();
  public final static double DEFAULT_BETA = jsgppJNI.OptNelderMead_DEFAULT_BETA_get();
  public final static double DEFAULT_GAMMA = jsgppJNI.OptNelderMead_DEFAULT_GAMMA_get();
  public final static double DEFAULT_DELTA = jsgppJNI.OptNelderMead_DEFAULT_DELTA_get();
  public final static long DEFAULT_MAX_FCN_EVAL_COUNT = jsgppJNI.OptNelderMead_DEFAULT_MAX_FCN_EVAL_COUNT_get();
  public final static double STARTING_SIMPLEX_EDGE_LENGTH = jsgppJNI.OptNelderMead_STARTING_SIMPLEX_EDGE_LENGTH_get();
}
