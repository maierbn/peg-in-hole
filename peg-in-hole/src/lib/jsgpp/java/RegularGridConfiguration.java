/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.8
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sgpp;

public class RegularGridConfiguration {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected RegularGridConfiguration(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(RegularGridConfiguration obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        jsgppJNI.delete_RegularGridConfiguration(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setType_(GridType value) {
    jsgppJNI.RegularGridConfiguration_type__set(swigCPtr, this, value.swigValue());
  }

  public GridType getType_() {
    return GridType.swigToEnum(jsgppJNI.RegularGridConfiguration_type__get(swigCPtr, this));
  }

  public void setDim_(long value) {
    jsgppJNI.RegularGridConfiguration_dim__set(swigCPtr, this, value);
  }

  public long getDim_() {
    return jsgppJNI.RegularGridConfiguration_dim__get(swigCPtr, this);
  }

  public void setLevel_(int value) {
    jsgppJNI.RegularGridConfiguration_level__set(swigCPtr, this, value);
  }

  public int getLevel_() {
    return jsgppJNI.RegularGridConfiguration_level__get(swigCPtr, this);
  }

  public RegularGridConfiguration() {
    this(jsgppJNI.new_RegularGridConfiguration(), true);
  }

}
