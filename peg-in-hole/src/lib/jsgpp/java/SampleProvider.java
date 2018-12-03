/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.8
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sgpp;

public class SampleProvider {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected SampleProvider(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(SampleProvider obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        jsgppJNI.delete_SampleProvider(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public SampleProvider __assign__(SampleProvider rhs) {
    return new SampleProvider(jsgppJNI.SampleProvider___assign__(swigCPtr, this, SampleProvider.getCPtr(rhs), rhs), false);
  }

  public SampleProvider clone() {
    long cPtr = jsgppJNI.SampleProvider_clone(swigCPtr, this);
    return (cPtr == 0) ? null : new SampleProvider(cPtr, false);
  }

  public Dataset getNextSamples(long howMany) {
    long cPtr = jsgppJNI.SampleProvider_getNextSamples(swigCPtr, this, howMany);
    return (cPtr == 0) ? null : new Dataset(cPtr, false);
  }

  public Dataset getAllSamples() {
    long cPtr = jsgppJNI.SampleProvider_getAllSamples(swigCPtr, this);
    return (cPtr == 0) ? null : new Dataset(cPtr, false);
  }

  public long getDim() {
    return jsgppJNI.SampleProvider_getDim(swigCPtr, this);
  }

}
