/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.8
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sgpp;

public class OptG04EqualityConstraint extends OptTestVectorFunction {
  private transient long swigCPtr;

  protected OptG04EqualityConstraint(long cPtr, boolean cMemoryOwn) {
    super(jsgppJNI.OptG04EqualityConstraint_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(OptG04EqualityConstraint obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        jsgppJNI.delete_OptG04EqualityConstraint(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public OptG04EqualityConstraint() {
    this(jsgppJNI.new_OptG04EqualityConstraint(), true);
  }

  public void evalUndisplaced(DataVector x, DataVector value) {
    jsgppJNI.OptG04EqualityConstraint_evalUndisplaced(swigCPtr, this, DataVector.getCPtr(x), x, DataVector.getCPtr(value), value);
  }

  public void clone(SWIGTYPE_p_std__unique_ptrT_sgpp__optimization__VectorFunction_t clone) {
    jsgppJNI.OptG04EqualityConstraint_clone(swigCPtr, this, SWIGTYPE_p_std__unique_ptrT_sgpp__optimization__VectorFunction_t.getCPtr(clone));
  }

}
