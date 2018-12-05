/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.8
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sgpp;

public class ImpurityRefinement extends RefinementDecorator {
  private transient long swigCPtr;

  protected ImpurityRefinement(long cPtr, boolean cMemoryOwn) {
    super(jsgppJNI.ImpurityRefinement_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(ImpurityRefinement obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        jsgppJNI.delete_ImpurityRefinement(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public void free_refine(GridStorage storage, RefinementFunctor functor) {
    jsgppJNI.ImpurityRefinement_free_refine__SWIG_0_0(swigCPtr, this, GridStorage.getCPtr(storage), storage, RefinementFunctor.getCPtr(functor), functor);
  }

  public ImpurityRefinement(AbstractRefinement refinement) {
    this(jsgppJNI.new_ImpurityRefinement(AbstractRefinement.getCPtr(refinement), refinement), true);
  }

  public void free_refine(GridStorage storage, ImpurityRefinementIndicator functor) {
    jsgppJNI.ImpurityRefinement_free_refine__SWIG_1(swigCPtr, this, GridStorage.getCPtr(storage), storage, ImpurityRefinementIndicator.getCPtr(functor), functor);
  }

}
