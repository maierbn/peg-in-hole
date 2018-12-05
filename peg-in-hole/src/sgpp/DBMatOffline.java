/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.8
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sgpp;

public class DBMatOffline {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected DBMatOffline(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(DBMatOffline obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        jsgppJNI.delete_DBMatOffline(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public DBMatOffline clone() {
    long cPtr = jsgppJNI.DBMatOffline_clone(swigCPtr, this);
    return (cPtr == 0) ? null : new DBMatOffline(cPtr, false);
  }

  public boolean isRefineable() {
    return jsgppJNI.DBMatOffline_isRefineable(swigCPtr, this);
  }

  public RegularGridConfiguration getGridConfig() {
    return new RegularGridConfiguration(jsgppJNI.DBMatOffline_getGridConfig(swigCPtr, this), false);
  }

  public AdpativityConfiguration getAdaptivityConfig() {
    return new AdpativityConfiguration(jsgppJNI.DBMatOffline_getAdaptivityConfig(swigCPtr, this), false);
  }

  public RegularizationConfiguration getRegularizationConfig() {
    return new RegularizationConfiguration(jsgppJNI.DBMatOffline_getRegularizationConfig(swigCPtr, this), false);
  }

  public DensityEstimationConfiguration getDensityEstimationConfig() {
    return new DensityEstimationConfiguration(jsgppJNI.DBMatOffline_getDensityEstimationConfig(swigCPtr, this), false);
  }

  public DataMatrix getDecomposedMatrix() {
    return new DataMatrix(jsgppJNI.DBMatOffline_getDecomposedMatrix(swigCPtr, this), false);
  }

  public DataMatrix getLhsMatrix_ONLY_FOR_TESTING() {
    return new DataMatrix(jsgppJNI.DBMatOffline_getLhsMatrix_ONLY_FOR_TESTING(swigCPtr, this), false);
  }

  public Grid getGrid() {
    return new Grid(jsgppJNI.DBMatOffline_getGrid(swigCPtr, this), false);
  }

  public void buildMatrix() {
    jsgppJNI.DBMatOffline_buildMatrix(swigCPtr, this);
  }

  public void decomposeMatrix() {
    jsgppJNI.DBMatOffline_decomposeMatrix(swigCPtr, this);
  }

  public void printMatrix() {
    jsgppJNI.DBMatOffline_printMatrix(swigCPtr, this);
  }

  public void store(String fileName) {
    jsgppJNI.DBMatOffline_store(swigCPtr, this, fileName);
  }

  public void setInter(SWIGTYPE_p_std__vectorT_std__vectorT_size_t_t_t interactions) {
    jsgppJNI.DBMatOffline_setInter(swigCPtr, this, SWIGTYPE_p_std__vectorT_std__vectorT_size_t_t_t.getCPtr(interactions));
  }

  public void setInteractions(SWIGTYPE_p_std__vectorT_std__vectorT_size_t_t_t value) {
    jsgppJNI.DBMatOffline_interactions_set(swigCPtr, this, SWIGTYPE_p_std__vectorT_std__vectorT_size_t_t_t.getCPtr(value));
  }

  public SWIGTYPE_p_std__vectorT_std__vectorT_size_t_t_t getInteractions() {
    long cPtr = jsgppJNI.DBMatOffline_interactions_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_std__vectorT_std__vectorT_size_t_t_t(cPtr, false);
  }

}
