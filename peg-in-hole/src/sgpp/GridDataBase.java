/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.8
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sgpp;

public class GridDataBase {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected GridDataBase(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(GridDataBase obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        jsgppJNI.delete_GridDataBase(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public GridDataBase(long dim) {
    this(jsgppJNI.new_GridDataBase__SWIG_0(dim), true);
  }

  public GridDataBase(Grid grid, DataVector values) {
    this(jsgppJNI.new_GridDataBase__SWIG_1(Grid.getCPtr(grid), grid, DataVector.getCPtr(values), values), true);
  }

  public GridDataBase(String filename) {
    this(jsgppJNI.new_GridDataBase__SWIG_2(filename), true);
  }

  public void clear() {
    jsgppJNI.GridDataBase_clear(swigCPtr, this);
  }

  public String toString() {
    return jsgppJNI.GridDataBase_toString(swigCPtr, this);
  }

  public boolean hasKey(GridPoint gi) {
    return jsgppJNI.GridDataBase_hasKey(swigCPtr, this, GridPoint.getCPtr(gi), gi);
  }

  public void set(GridPoint gi, double value) {
    jsgppJNI.GridDataBase_set(swigCPtr, this, GridPoint.getCPtr(gi), gi, value);
  }

  public void setValuesFor(Grid grid, DataVector values) {
    jsgppJNI.GridDataBase_setValuesFor(swigCPtr, this, Grid.getCPtr(grid), grid, DataVector.getCPtr(values), values);
  }

  public long size() {
    return jsgppJNI.GridDataBase_size(swigCPtr, this);
  }

  public long dim() {
    return jsgppJNI.GridDataBase_dim(swigCPtr, this);
  }

  public double get(GridPoint gi) {
    return jsgppJNI.GridDataBase_get(swigCPtr, this, GridPoint.getCPtr(gi), gi);
  }

  public void remove(GridPoint gi) {
    jsgppJNI.GridDataBase_remove(swigCPtr, this, GridPoint.getCPtr(gi), gi);
  }

  public void save(String filename, char ftype) {
    jsgppJNI.GridDataBase_save__SWIG_0(swigCPtr, this, filename, ftype);
  }

  public void save(String filename) {
    jsgppJNI.GridDataBase_save__SWIG_1(swigCPtr, this, filename);
  }

  public void load(String filename) {
    jsgppJNI.GridDataBase_load(swigCPtr, this, filename);
  }

  public SWIGTYPE_p_std__unordered_mapT_sgpp__base__HashGridPoint_p_double_sgpp__base__HashGridPointPointerHashFunctor_sgpp__base__HashGridPointPointerEqualityFunctor_t__iterator begin() {
    return new SWIGTYPE_p_std__unordered_mapT_sgpp__base__HashGridPoint_p_double_sgpp__base__HashGridPointPointerHashFunctor_sgpp__base__HashGridPointPointerEqualityFunctor_t__iterator(jsgppJNI.GridDataBase_begin(swigCPtr, this), true);
  }

  public SWIGTYPE_p_std__unordered_mapT_sgpp__base__HashGridPoint_p_double_sgpp__base__HashGridPointPointerHashFunctor_sgpp__base__HashGridPointPointerEqualityFunctor_t__iterator end() {
    return new SWIGTYPE_p_std__unordered_mapT_sgpp__base__HashGridPoint_p_double_sgpp__base__HashGridPointPointerHashFunctor_sgpp__base__HashGridPointPointerEqualityFunctor_t__iterator(jsgppJNI.GridDataBase_end(swigCPtr, this), true);
  }

  public final static char ascii = jsgppJNI.GridDataBase_ascii_get();
  public final static char binary = jsgppJNI.GridDataBase_binary_get();
}
