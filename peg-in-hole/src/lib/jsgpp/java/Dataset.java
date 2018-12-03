/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.8
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sgpp;

public class Dataset {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected Dataset(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(Dataset obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        jsgppJNI.delete_Dataset(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public Dataset() {
    this(jsgppJNI.new_Dataset__SWIG_0(), true);
  }

  public Dataset(long numberInstances, long dimension) {
    this(jsgppJNI.new_Dataset__SWIG_1(numberInstances, dimension), true);
  }

  public long getNumberInstances() {
    return jsgppJNI.Dataset_getNumberInstances(swigCPtr, this);
  }

  public long getDimension() {
    return jsgppJNI.Dataset_getDimension(swigCPtr, this);
  }

  public DataVector getTargets() {
    return new DataVector(jsgppJNI.Dataset_getTargets(swigCPtr, this), false);
  }

  public DataVector getConstTargets() {
    return new DataVector(jsgppJNI.Dataset_getConstTargets(swigCPtr, this), false);
  }

  public DataMatrix getData() {
    return new DataMatrix(jsgppJNI.Dataset_getData(swigCPtr, this), false);
  }

  public DataMatrix getConstData() {
    return new DataMatrix(jsgppJNI.Dataset_getConstData(swigCPtr, this), false);
  }

}
