/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.8
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sgpp;

public class OptG12EqualityConstraint extends OptTestVectorFunction {
  private transient long swigCPtr;

  protected OptG12EqualityConstraint(long cPtr, boolean cMemoryOwn) {
    super(jsgppJNI.OptG12EqualityConstraint_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(OptG12EqualityConstraint obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        jsgppJNI.delete_OptG12EqualityConstraint(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public OptG12EqualityConstraint() {
    this(jsgppJNI.new_OptG12EqualityConstraint(), true);
  }

  public void evalUndisplaced(DataVector x, DataVector value) {
    jsgppJNI.OptG12EqualityConstraint_evalUndisplaced(swigCPtr, this, DataVector.getCPtr(x), x, DataVector.getCPtr(value), value);
  }

  public void clone(SWIGTYPE_p_std__unique_ptrT_sgpp__optimization__VectorFunction_t clone) {
    jsgppJNI.OptG12EqualityConstraint_clone(swigCPtr, this, SWIGTYPE_p_std__unique_ptrT_sgpp__optimization__VectorFunction_t.getCPtr(clone));
  }

}
