/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.8
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sgpp;

public class SWaveletBase extends SBasis {
  private transient long swigCPtr;

  protected SWaveletBase(long cPtr, boolean cMemoryOwn) {
    super(jsgppJNI.SWaveletBase_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(SWaveletBase obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        jsgppJNI.delete_SWaveletBase(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public double eval(long l, long i, double x) {
    return jsgppJNI.SWaveletBase_eval(swigCPtr, this, l, i, x);
  }

  public double evalDx(long l, long i, double x) {
    return jsgppJNI.SWaveletBase_evalDx(swigCPtr, this, l, i, x);
  }

  public double evalDxDx(long l, long i, double x) {
    return jsgppJNI.SWaveletBase_evalDxDx(swigCPtr, this, l, i, x);
  }

  public SWaveletBase() {
    this(jsgppJNI.new_SWaveletBase(), true);
  }

}
