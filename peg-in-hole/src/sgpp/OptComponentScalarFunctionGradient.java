/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.8
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sgpp;

public class OptComponentScalarFunctionGradient extends OptScalarFunctionGradient {
  private transient long swigCPtr;

  protected OptComponentScalarFunctionGradient(long cPtr, boolean cMemoryOwn) {
    super(jsgppJNI.OptComponentScalarFunctionGradient_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(OptComponentScalarFunctionGradient obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        jsgppJNI.delete_OptComponentScalarFunctionGradient(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public OptComponentScalarFunctionGradient(OptScalarFunctionGradient fGradient, DoubleVector defaultValues) {
    this(jsgppJNI.new_OptComponentScalarFunctionGradient__SWIG_0(OptScalarFunctionGradient.getCPtr(fGradient), fGradient, DoubleVector.getCPtr(defaultValues), defaultValues), true);
  }

  public OptComponentScalarFunctionGradient(OptScalarFunctionGradient fGradient) {
    this(jsgppJNI.new_OptComponentScalarFunctionGradient__SWIG_1(OptScalarFunctionGradient.getCPtr(fGradient), fGradient), true);
  }

  public OptComponentScalarFunctionGradient(OptVectorFunctionGradient fGradient, long k, DoubleVector defaultValues) {
    this(jsgppJNI.new_OptComponentScalarFunctionGradient__SWIG_2(OptVectorFunctionGradient.getCPtr(fGradient), fGradient, k, DoubleVector.getCPtr(defaultValues), defaultValues), true);
  }

  public OptComponentScalarFunctionGradient(OptVectorFunctionGradient fGradient, long k) {
    this(jsgppJNI.new_OptComponentScalarFunctionGradient__SWIG_3(OptVectorFunctionGradient.getCPtr(fGradient), fGradient, k), true);
  }

  public double eval(DataVector x, DataVector gradient) {
    return jsgppJNI.OptComponentScalarFunctionGradient_eval(swigCPtr, this, DataVector.getCPtr(x), x, DataVector.getCPtr(gradient), gradient);
  }

  public void clone(SWIGTYPE_p_std__unique_ptrT_sgpp__optimization__ScalarFunctionGradient_t clone) {
    jsgppJNI.OptComponentScalarFunctionGradient_clone(swigCPtr, this, SWIGTYPE_p_std__unique_ptrT_sgpp__optimization__ScalarFunctionGradient_t.getCPtr(clone));
  }

}
