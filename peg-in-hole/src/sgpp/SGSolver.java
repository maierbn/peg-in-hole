/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.8
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sgpp;

public class SGSolver {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected SGSolver(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(SGSolver obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        jsgppJNI.delete_SGSolver(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public SGSolver(long nMaximumIterations, double epsilon) {
    this(jsgppJNI.new_SGSolver(nMaximumIterations, epsilon), true);
  }

  public long getNumberIterations() {
    return jsgppJNI.SGSolver_getNumberIterations(swigCPtr, this);
  }

  public double getResiduum() {
    return jsgppJNI.SGSolver_getResiduum(swigCPtr, this);
  }

  public void setMaxIterations(long nIterations) {
    jsgppJNI.SGSolver_setMaxIterations(swigCPtr, this, nIterations);
  }

  public void setEpsilon(double eps) {
    jsgppJNI.SGSolver_setEpsilon(swigCPtr, this, eps);
  }

  public double getEpsilon() {
    return jsgppJNI.SGSolver_getEpsilon(swigCPtr, this);
  }

}
