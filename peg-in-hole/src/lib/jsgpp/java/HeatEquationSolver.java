/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.8
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sgpp;

public class HeatEquationSolver extends ParabolicPDESolver {
  private transient long swigCPtr;

  protected HeatEquationSolver(long cPtr, boolean cMemoryOwn) {
    super(jsgppJNI.HeatEquationSolver_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(HeatEquationSolver obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        jsgppJNI.delete_HeatEquationSolver(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public HeatEquationSolver() {
    this(jsgppJNI.new_HeatEquationSolver(), true);
  }

  public void constructGrid(BoundingBox myBoundingBox, long level) {
    jsgppJNI.HeatEquationSolver_constructGrid(swigCPtr, this, BoundingBox.getCPtr(myBoundingBox), myBoundingBox, level);
  }

  public void solveExplicitEuler(long numTimesteps, double timestepsize, long maxCGIterations, double epsilonCG, DataVector alpha, boolean verbose, boolean generateAnimation) {
    jsgppJNI.HeatEquationSolver_solveExplicitEuler__SWIG_0(swigCPtr, this, numTimesteps, timestepsize, maxCGIterations, epsilonCG, DataVector.getCPtr(alpha), alpha, verbose, generateAnimation);
  }

  public void solveExplicitEuler(long numTimesteps, double timestepsize, long maxCGIterations, double epsilonCG, DataVector alpha, boolean verbose) {
    jsgppJNI.HeatEquationSolver_solveExplicitEuler__SWIG_1(swigCPtr, this, numTimesteps, timestepsize, maxCGIterations, epsilonCG, DataVector.getCPtr(alpha), alpha, verbose);
  }

  public void solveExplicitEuler(long numTimesteps, double timestepsize, long maxCGIterations, double epsilonCG, DataVector alpha) {
    jsgppJNI.HeatEquationSolver_solveExplicitEuler__SWIG_2(swigCPtr, this, numTimesteps, timestepsize, maxCGIterations, epsilonCG, DataVector.getCPtr(alpha), alpha);
  }

  public void solveImplicitEuler(long numTimesteps, double timestepsize, long maxCGIterations, double epsilonCG, DataVector alpha, boolean verbose, boolean generateAnimation) {
    jsgppJNI.HeatEquationSolver_solveImplicitEuler__SWIG_0(swigCPtr, this, numTimesteps, timestepsize, maxCGIterations, epsilonCG, DataVector.getCPtr(alpha), alpha, verbose, generateAnimation);
  }

  public void solveImplicitEuler(long numTimesteps, double timestepsize, long maxCGIterations, double epsilonCG, DataVector alpha, boolean verbose) {
    jsgppJNI.HeatEquationSolver_solveImplicitEuler__SWIG_1(swigCPtr, this, numTimesteps, timestepsize, maxCGIterations, epsilonCG, DataVector.getCPtr(alpha), alpha, verbose);
  }

  public void solveImplicitEuler(long numTimesteps, double timestepsize, long maxCGIterations, double epsilonCG, DataVector alpha) {
    jsgppJNI.HeatEquationSolver_solveImplicitEuler__SWIG_2(swigCPtr, this, numTimesteps, timestepsize, maxCGIterations, epsilonCG, DataVector.getCPtr(alpha), alpha);
  }

  public void solveCrankNicolson(long numTimesteps, double timestepsize, long maxCGIterations, double epsilonCG, DataVector alpha, long NumImEul) {
    jsgppJNI.HeatEquationSolver_solveCrankNicolson__SWIG_0(swigCPtr, this, numTimesteps, timestepsize, maxCGIterations, epsilonCG, DataVector.getCPtr(alpha), alpha, NumImEul);
  }

  public void solveCrankNicolson(long numTimesteps, double timestepsize, long maxCGIterations, double epsilonCG, DataVector alpha) {
    jsgppJNI.HeatEquationSolver_solveCrankNicolson__SWIG_1(swigCPtr, this, numTimesteps, timestepsize, maxCGIterations, epsilonCG, DataVector.getCPtr(alpha), alpha);
  }

  public void setHeatCoefficient(double a) {
    jsgppJNI.HeatEquationSolver_setHeatCoefficient(swigCPtr, this, a);
  }

  public void initGridWithSmoothHeat(DataVector alpha, double mu, double sigma, double factor) {
    jsgppJNI.HeatEquationSolver_initGridWithSmoothHeat(swigCPtr, this, DataVector.getCPtr(alpha), alpha, mu, sigma, factor);
  }

  public void initScreen() {
    jsgppJNI.HeatEquationSolver_initScreen(swigCPtr, this);
  }

  public void storeInnerRHS(DataVector alpha, String tFilename, double timestepsize) {
    jsgppJNI.HeatEquationSolver_storeInnerRHS(swigCPtr, this, DataVector.getCPtr(alpha), alpha, tFilename, timestepsize);
  }

  public void storeInnerSolution(DataVector alpha, long numTimesteps, double timestepsize, long maxCGIterations, double epsilonCG, String tFilename) {
    jsgppJNI.HeatEquationSolver_storeInnerSolution(swigCPtr, this, DataVector.getCPtr(alpha), alpha, numTimesteps, timestepsize, maxCGIterations, epsilonCG, tFilename);
  }

}
