/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.8
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sgpp;

public class OptInterpolantScalarFunction extends OptScalarFunction {
  private transient long swigCPtr;

  protected OptInterpolantScalarFunction(long cPtr, boolean cMemoryOwn) {
    super(jsgppJNI.OptInterpolantScalarFunction_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(OptInterpolantScalarFunction obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        jsgppJNI.delete_OptInterpolantScalarFunction(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public OptInterpolantScalarFunction(Grid grid, DataVector alpha) {
    this(jsgppJNI.new_OptInterpolantScalarFunction(Grid.getCPtr(grid), grid, DataVector.getCPtr(alpha), alpha), true);
  }

  public double eval(DataVector x) {
    return jsgppJNI.OptInterpolantScalarFunction_eval(swigCPtr, this, DataVector.getCPtr(x), x);
  }

  public void clone(SWIGTYPE_p_std__unique_ptrT_sgpp__optimization__ScalarFunction_t clone) {
    jsgppJNI.OptInterpolantScalarFunction_clone(swigCPtr, this, SWIGTYPE_p_std__unique_ptrT_sgpp__optimization__ScalarFunction_t.getCPtr(clone));
  }

  public DataVector getAlpha() {
    return new DataVector(jsgppJNI.OptInterpolantScalarFunction_getAlpha(swigCPtr, this), false);
  }

  public void setAlpha(DataVector alpha) {
    jsgppJNI.OptInterpolantScalarFunction_setAlpha(swigCPtr, this, DataVector.getCPtr(alpha), alpha);
  }

}
