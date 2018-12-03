/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.8
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sgpp;

public class OptVectorFunctionGradient {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected OptVectorFunctionGradient(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(OptVectorFunctionGradient obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        jsgppJNI.delete_OptVectorFunctionGradient(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  protected void swigDirectorDisconnect() {
    swigCMemOwn = false;
    delete();
  }

  public void swigReleaseOwnership() {
    swigCMemOwn = false;
    jsgppJNI.OptVectorFunctionGradient_change_ownership(this, swigCPtr, false);
  }

  public void swigTakeOwnership() {
    swigCMemOwn = true;
    jsgppJNI.OptVectorFunctionGradient_change_ownership(this, swigCPtr, true);
  }

  public OptVectorFunctionGradient(long d, long m) {
    this(jsgppJNI.new_OptVectorFunctionGradient(d, m), true);
    jsgppJNI.OptVectorFunctionGradient_director_connect(this, swigCPtr, swigCMemOwn, true);
  }

  public void eval(DataVector x, DataVector value, DataMatrix gradient) {
    jsgppJNI.OptVectorFunctionGradient_eval(swigCPtr, this, DataVector.getCPtr(x), x, DataVector.getCPtr(value), value, DataMatrix.getCPtr(gradient), gradient);
  }

  public long getNumberOfParameters() {
    return jsgppJNI.OptVectorFunctionGradient_getNumberOfParameters(swigCPtr, this);
  }

  public long getNumberOfComponents() {
    return jsgppJNI.OptVectorFunctionGradient_getNumberOfComponents(swigCPtr, this);
  }

  public void clone(SWIGTYPE_p_std__unique_ptrT_sgpp__optimization__VectorFunctionGradient_t clone) {
    jsgppJNI.OptVectorFunctionGradient_clone(swigCPtr, this, SWIGTYPE_p_std__unique_ptrT_sgpp__optimization__VectorFunctionGradient_t.getCPtr(clone));
  }

}
