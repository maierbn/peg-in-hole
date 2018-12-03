/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.8
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sgpp;

public class OptTestVectorFunction extends OptVectorFunction {
  private transient long swigCPtr;

  protected OptTestVectorFunction(long cPtr, boolean cMemoryOwn) {
    super(jsgppJNI.OptTestVectorFunction_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(OptTestVectorFunction obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        jsgppJNI.delete_OptTestVectorFunction(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public void eval(DataVector x, DataVector value) {
    jsgppJNI.OptTestVectorFunction_eval(swigCPtr, this, DataVector.getCPtr(x), x, DataVector.getCPtr(value), value);
  }

  public void evalUndisplaced(DataVector x, DataVector value) {
    jsgppJNI.OptTestVectorFunction_evalUndisplaced(swigCPtr, this, DataVector.getCPtr(x), x, DataVector.getCPtr(value), value);
  }

  public DataVector getDisplacement() {
    return new DataVector(jsgppJNI.OptTestVectorFunction_getDisplacement(swigCPtr, this), false);
  }

  public void setDisplacement(DataVector displacement) {
    jsgppJNI.OptTestVectorFunction_setDisplacement(swigCPtr, this, DataVector.getCPtr(displacement), displacement);
  }

}
