/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.8
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sgpp;

public class SLESolver extends SGSolver {
  private transient long swigCPtr;

  protected SLESolver(long cPtr, boolean cMemoryOwn) {
    super(jsgppJNI.SLESolver_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(SLESolver obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        jsgppJNI.delete_SLESolver(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public void solve(OperationMatrix SystemMatrix, DataVector alpha, DataVector b, boolean reuse, boolean verbose, double max_threshold) {
    jsgppJNI.SLESolver_solve__SWIG_0(swigCPtr, this, OperationMatrix.getCPtr(SystemMatrix), SystemMatrix, DataVector.getCPtr(alpha), alpha, DataVector.getCPtr(b), b, reuse, verbose, max_threshold);
  }

  public void solve(OperationMatrix SystemMatrix, DataVector alpha, DataVector b, boolean reuse, boolean verbose) {
    jsgppJNI.SLESolver_solve__SWIG_1(swigCPtr, this, OperationMatrix.getCPtr(SystemMatrix), SystemMatrix, DataVector.getCPtr(alpha), alpha, DataVector.getCPtr(b), b, reuse, verbose);
  }

  public void solve(OperationMatrix SystemMatrix, DataVector alpha, DataVector b, boolean reuse) {
    jsgppJNI.SLESolver_solve__SWIG_2(swigCPtr, this, OperationMatrix.getCPtr(SystemMatrix), SystemMatrix, DataVector.getCPtr(alpha), alpha, DataVector.getCPtr(b), b, reuse);
  }

  public void solve(OperationMatrix SystemMatrix, DataVector alpha, DataVector b) {
    jsgppJNI.SLESolver_solve__SWIG_3(swigCPtr, this, OperationMatrix.getCPtr(SystemMatrix), SystemMatrix, DataVector.getCPtr(alpha), alpha, DataVector.getCPtr(b), b);
  }

}
