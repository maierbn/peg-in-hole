/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.8
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sgpp;

public class OptHimmelblauObjective extends OptTestScalarFunction {
  private transient long swigCPtr;

  protected OptHimmelblauObjective(long cPtr, boolean cMemoryOwn) {
    super(jsgppJNI.OptHimmelblauObjective_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(OptHimmelblauObjective obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        jsgppJNI.delete_OptHimmelblauObjective(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public OptHimmelblauObjective() {
    this(jsgppJNI.new_OptHimmelblauObjective(), true);
  }

  public double evalUndisplaced(DataVector x) {
    return jsgppJNI.OptHimmelblauObjective_evalUndisplaced(swigCPtr, this, DataVector.getCPtr(x), x);
  }

  public void clone(SWIGTYPE_p_std__unique_ptrT_sgpp__optimization__ScalarFunction_t clone) {
    jsgppJNI.OptHimmelblauObjective_clone(swigCPtr, this, SWIGTYPE_p_std__unique_ptrT_sgpp__optimization__ScalarFunction_t.getCPtr(clone));
  }

}
