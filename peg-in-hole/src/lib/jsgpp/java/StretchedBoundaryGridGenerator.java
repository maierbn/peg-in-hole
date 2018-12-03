/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.8
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sgpp;

public class StretchedBoundaryGridGenerator extends GridGenerator {
  private transient long swigCPtr;

  protected StretchedBoundaryGridGenerator(long cPtr, boolean cMemoryOwn) {
    super(jsgppJNI.StretchedBoundaryGridGenerator_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(StretchedBoundaryGridGenerator obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        jsgppJNI.delete_StretchedBoundaryGridGenerator(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public StretchedBoundaryGridGenerator(GridStorage storage) {
    this(jsgppJNI.new_StretchedBoundaryGridGenerator(GridStorage.getCPtr(storage), storage), true);
  }

  public void regular(long level) {
    jsgppJNI.StretchedBoundaryGridGenerator_regular(swigCPtr, this, level);
  }

  public void cliques(long level, long clique_size) {
    jsgppJNI.StretchedBoundaryGridGenerator_cliques(swigCPtr, this, level, clique_size);
  }

  public void full(long level) {
    jsgppJNI.StretchedBoundaryGridGenerator_full(swigCPtr, this, level);
  }

  public void refine(RefinementFunctor func) {
    jsgppJNI.StretchedBoundaryGridGenerator_refine(swigCPtr, this, RefinementFunctor.getCPtr(func), func);
  }

  public long getNumberOfRefinablePoints() {
    return jsgppJNI.StretchedBoundaryGridGenerator_getNumberOfRefinablePoints(swigCPtr, this);
  }

  public void coarsen(CoarseningFunctor func, DataVector alpha) {
    jsgppJNI.StretchedBoundaryGridGenerator_coarsen(swigCPtr, this, CoarseningFunctor.getCPtr(func), func, DataVector.getCPtr(alpha), alpha);
  }

  public void coarsenNFirstOnly(CoarseningFunctor func, DataVector alpha, long numFirstOnly) {
    jsgppJNI.StretchedBoundaryGridGenerator_coarsenNFirstOnly(swigCPtr, this, CoarseningFunctor.getCPtr(func), func, DataVector.getCPtr(alpha), alpha, numFirstOnly);
  }

  public long getNumberOfRemovablePoints() {
    return jsgppJNI.StretchedBoundaryGridGenerator_getNumberOfRemovablePoints(swigCPtr, this);
  }

  public void refineMaxLevel(RefinementFunctor func, long maxLevel) {
    jsgppJNI.StretchedBoundaryGridGenerator_refineMaxLevel(swigCPtr, this, RefinementFunctor.getCPtr(func), func, maxLevel);
  }

  public long getNumberOfRefinablePointsToMaxLevel(long maxLevel) {
    return jsgppJNI.StretchedBoundaryGridGenerator_getNumberOfRefinablePointsToMaxLevel(swigCPtr, this, maxLevel);
  }

}
