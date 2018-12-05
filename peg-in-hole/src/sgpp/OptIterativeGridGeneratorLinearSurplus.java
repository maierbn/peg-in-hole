/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.8
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sgpp;

public class OptIterativeGridGeneratorLinearSurplus extends OptIterativeGridGenerator {
  private transient long swigCPtr;

  protected OptIterativeGridGeneratorLinearSurplus(long cPtr, boolean cMemoryOwn) {
    super(jsgppJNI.OptIterativeGridGeneratorLinearSurplus_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(OptIterativeGridGeneratorLinearSurplus obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        jsgppJNI.delete_OptIterativeGridGeneratorLinearSurplus(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public OptIterativeGridGeneratorLinearSurplus(OptScalarFunction f, Grid grid, long N, double adaptivity, long initialLevel) {
    this(jsgppJNI.new_OptIterativeGridGeneratorLinearSurplus__SWIG_0(OptScalarFunction.getCPtr(f), f, Grid.getCPtr(grid), grid, N, adaptivity, initialLevel), true);
  }

  public OptIterativeGridGeneratorLinearSurplus(OptScalarFunction f, Grid grid, long N, double adaptivity) {
    this(jsgppJNI.new_OptIterativeGridGeneratorLinearSurplus__SWIG_1(OptScalarFunction.getCPtr(f), f, Grid.getCPtr(grid), grid, N, adaptivity), true);
  }

  public OptIterativeGridGeneratorLinearSurplus(OptScalarFunction f, Grid grid, long N) {
    this(jsgppJNI.new_OptIterativeGridGeneratorLinearSurplus__SWIG_2(OptScalarFunction.getCPtr(f), f, Grid.getCPtr(grid), grid, N), true);
  }

  public boolean generate() {
    return jsgppJNI.OptIterativeGridGeneratorLinearSurplus_generate(swigCPtr, this);
  }

  public double getAdaptivity() {
    return jsgppJNI.OptIterativeGridGeneratorLinearSurplus_getAdaptivity(swigCPtr, this);
  }

  public void setAdaptivity(double adaptivity) {
    jsgppJNI.OptIterativeGridGeneratorLinearSurplus_setAdaptivity(swigCPtr, this, adaptivity);
  }

  public long getInitialLevel() {
    return jsgppJNI.OptIterativeGridGeneratorLinearSurplus_getInitialLevel(swigCPtr, this);
  }

  public void setInitialLevel(long initialLevel) {
    jsgppJNI.OptIterativeGridGeneratorLinearSurplus_setInitialLevel(swigCPtr, this, initialLevel);
  }

  public final static double DEFAULT_ADAPTIVITY = jsgppJNI.OptIterativeGridGeneratorLinearSurplus_DEFAULT_ADAPTIVITY_get();
  public final static long DEFAULT_INITIAL_LEVEL = jsgppJNI.OptIterativeGridGeneratorLinearSurplus_DEFAULT_INITIAL_LEVEL_get();
}
