/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.8
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sgpp;

public class ZeroCrossingRefinementFunctor extends MultiGridRefinementFunctor {
  private transient long swigCPtr;

  protected ZeroCrossingRefinementFunctor(long cPtr, boolean cMemoryOwn) {
    super(jsgppJNI.ZeroCrossingRefinementFunctor_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(ZeroCrossingRefinementFunctor obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        jsgppJNI.delete_ZeroCrossingRefinementFunctor(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public ZeroCrossingRefinementFunctor(SWIGTYPE_p_std__vectorT_sgpp__base__Grid_p_t grids, SWIGTYPE_p_std__vectorT_sgpp__base__DataVector_p_t alphas, long refinements_num, boolean level_penalize, boolean pre_compute, double threshold) {
    this(jsgppJNI.new_ZeroCrossingRefinementFunctor__SWIG_0(SWIGTYPE_p_std__vectorT_sgpp__base__Grid_p_t.getCPtr(grids), SWIGTYPE_p_std__vectorT_sgpp__base__DataVector_p_t.getCPtr(alphas), refinements_num, level_penalize, pre_compute, threshold), true);
  }

  public ZeroCrossingRefinementFunctor(SWIGTYPE_p_std__vectorT_sgpp__base__Grid_p_t grids, SWIGTYPE_p_std__vectorT_sgpp__base__DataVector_p_t alphas, long refinements_num, boolean level_penalize, boolean pre_compute) {
    this(jsgppJNI.new_ZeroCrossingRefinementFunctor__SWIG_1(SWIGTYPE_p_std__vectorT_sgpp__base__Grid_p_t.getCPtr(grids), SWIGTYPE_p_std__vectorT_sgpp__base__DataVector_p_t.getCPtr(alphas), refinements_num, level_penalize, pre_compute), true);
  }

  public ZeroCrossingRefinementFunctor(SWIGTYPE_p_std__vectorT_sgpp__base__Grid_p_t grids, SWIGTYPE_p_std__vectorT_sgpp__base__DataVector_p_t alphas, long refinements_num, boolean level_penalize) {
    this(jsgppJNI.new_ZeroCrossingRefinementFunctor__SWIG_2(SWIGTYPE_p_std__vectorT_sgpp__base__Grid_p_t.getCPtr(grids), SWIGTYPE_p_std__vectorT_sgpp__base__DataVector_p_t.getCPtr(alphas), refinements_num, level_penalize), true);
  }

  public ZeroCrossingRefinementFunctor(SWIGTYPE_p_std__vectorT_sgpp__base__Grid_p_t grids, SWIGTYPE_p_std__vectorT_sgpp__base__DataVector_p_t alphas, long refinements_num) {
    this(jsgppJNI.new_ZeroCrossingRefinementFunctor__SWIG_3(SWIGTYPE_p_std__vectorT_sgpp__base__Grid_p_t.getCPtr(grids), SWIGTYPE_p_std__vectorT_sgpp__base__DataVector_p_t.getCPtr(alphas), refinements_num), true);
  }

  public ZeroCrossingRefinementFunctor(SWIGTYPE_p_std__vectorT_sgpp__base__Grid_p_t grids, SWIGTYPE_p_std__vectorT_sgpp__base__DataVector_p_t alphas) {
    this(jsgppJNI.new_ZeroCrossingRefinementFunctor__SWIG_4(SWIGTYPE_p_std__vectorT_sgpp__base__Grid_p_t.getCPtr(grids), SWIGTYPE_p_std__vectorT_sgpp__base__DataVector_p_t.getCPtr(alphas)), true);
  }

  public double operatorParentheses(GridStorage storage, long seq) {
    return jsgppJNI.ZeroCrossingRefinementFunctor_operatorParentheses(swigCPtr, this, GridStorage.getCPtr(storage), storage, seq);
  }

  public double start() {
    return jsgppJNI.ZeroCrossingRefinementFunctor_start(swigCPtr, this);
  }

  public long getRefinementsNum() {
    return jsgppJNI.ZeroCrossingRefinementFunctor_getRefinementsNum(swigCPtr, this);
  }

  public double getRefinementThreshold() {
    return jsgppJNI.ZeroCrossingRefinementFunctor_getRefinementThreshold(swigCPtr, this);
  }

  public void setGridIndex(long grid_index) {
    jsgppJNI.ZeroCrossingRefinementFunctor_setGridIndex(swigCPtr, this, grid_index);
  }

  public long getNumGrids() {
    return jsgppJNI.ZeroCrossingRefinementFunctor_getNumGrids(swigCPtr, this);
  }

  public void preComputeEvaluations() {
    jsgppJNI.ZeroCrossingRefinementFunctor_preComputeEvaluations(swigCPtr, this);
  }

}
