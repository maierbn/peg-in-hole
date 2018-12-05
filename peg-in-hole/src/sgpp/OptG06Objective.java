/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.8
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sgpp;

public class OptG06Objective extends OptTestScalarFunction {
  private transient long swigCPtr;

  protected OptG06Objective(long cPtr, boolean cMemoryOwn) {
    super(jsgppJNI.OptG06Objective_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(OptG06Objective obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        jsgppJNI.delete_OptG06Objective(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public OptG06Objective() {
    this(jsgppJNI.new_OptG06Objective(), true);
  }

  public double evalUndisplaced(DataVector x) {
    return jsgppJNI.OptG06Objective_evalUndisplaced(swigCPtr, this, DataVector.getCPtr(x), x);
  }

  public void clone(SWIGTYPE_p_std__unique_ptrT_sgpp__optimization__ScalarFunction_t clone) {
    jsgppJNI.OptG06Objective_clone(swigCPtr, this, SWIGTYPE_p_std__unique_ptrT_sgpp__optimization__ScalarFunction_t.getCPtr(clone));
  }

}
