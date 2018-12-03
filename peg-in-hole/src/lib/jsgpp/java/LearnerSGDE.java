/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.8
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sgpp;

public class LearnerSGDE extends DensityEstimator {
  private transient long swigCPtr;

  protected LearnerSGDE(long cPtr, boolean cMemoryOwn) {
    super(jsgppJNI.LearnerSGDE_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(LearnerSGDE obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        jsgppJNI.delete_LearnerSGDE(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public LearnerSGDE(RegularGridConfiguration gridConfig, AdpativityConfiguration adaptivityConfig, SLESolverConfiguration solverConfig, RegularizationConfiguration regularizationConfig, SWIGTYPE_p_CrossvalidationConfiguration crossvalidationConfig) {
    this(jsgppJNI.new_LearnerSGDE__SWIG_0(RegularGridConfiguration.getCPtr(gridConfig), gridConfig, AdpativityConfiguration.getCPtr(adaptivityConfig), adaptivityConfig, SLESolverConfiguration.getCPtr(solverConfig), solverConfig, RegularizationConfiguration.getCPtr(regularizationConfig), regularizationConfig, SWIGTYPE_p_CrossvalidationConfiguration.getCPtr(crossvalidationConfig)), true);
  }

  public LearnerSGDE(LearnerSGDEConfiguration learnerSGDEConfig) {
    this(jsgppJNI.new_LearnerSGDE__SWIG_1(LearnerSGDEConfiguration.getCPtr(learnerSGDEConfig), learnerSGDEConfig), true);
  }

  public LearnerSGDE(LearnerSGDE learnerSGDE) {
    this(jsgppJNI.new_LearnerSGDE__SWIG_2(LearnerSGDE.getCPtr(learnerSGDE), learnerSGDE), true);
  }

  public void initialize(DataMatrix samples) {
    jsgppJNI.LearnerSGDE_initialize(swigCPtr, this, DataMatrix.getCPtr(samples), samples);
  }

  public double pdf(DataVector x) {
    return jsgppJNI.LearnerSGDE_pdf__SWIG_0(swigCPtr, this, DataVector.getCPtr(x), x);
  }

  public void pdf(DataMatrix points, DataVector res) {
    jsgppJNI.LearnerSGDE_pdf__SWIG_1(swigCPtr, this, DataMatrix.getCPtr(points), points, DataVector.getCPtr(res), res);
  }

  public double mean() {
    return jsgppJNI.LearnerSGDE_mean(swigCPtr, this);
  }

  public double variance() {
    return jsgppJNI.LearnerSGDE_variance(swigCPtr, this);
  }

  public void cov(DataMatrix cov) {
    jsgppJNI.LearnerSGDE_cov(swigCPtr, this, DataMatrix.getCPtr(cov), cov);
  }

  public SWIGTYPE_p_std__shared_ptrT_sgpp__base__DataVector_t getSamples(long dim) {
    return new SWIGTYPE_p_std__shared_ptrT_sgpp__base__DataVector_t(jsgppJNI.LearnerSGDE_getSamples__SWIG_0(swigCPtr, this, dim), true);
  }

  public SWIGTYPE_p_std__shared_ptrT_sgpp__base__DataMatrix_t getSamples() {
    return new SWIGTYPE_p_std__shared_ptrT_sgpp__base__DataMatrix_t(jsgppJNI.LearnerSGDE_getSamples__SWIG_1(swigCPtr, this), true);
  }

  public long getDim() {
    return jsgppJNI.LearnerSGDE_getDim(swigCPtr, this);
  }

  public long getNsamples() {
    return jsgppJNI.LearnerSGDE_getNsamples(swigCPtr, this);
  }

  public SWIGTYPE_p_std__shared_ptrT_sgpp__base__DataVector_t getSurpluses() {
    return new SWIGTYPE_p_std__shared_ptrT_sgpp__base__DataVector_t(jsgppJNI.LearnerSGDE_getSurpluses(swigCPtr, this), true);
  }

  public SWIGTYPE_p_std__shared_ptrT_sgpp__base__Grid_t getGrid() {
    return new SWIGTYPE_p_std__shared_ptrT_sgpp__base__Grid_t(jsgppJNI.LearnerSGDE_getGrid(swigCPtr, this), true);
  }

  public void train(Grid grid, DataVector alpha, DataMatrix trainData, double lambdaReg) {
    jsgppJNI.LearnerSGDE_train__SWIG_0(swigCPtr, this, Grid.getCPtr(grid), grid, DataVector.getCPtr(alpha), alpha, DataMatrix.getCPtr(trainData), trainData, lambdaReg);
  }

  public void train() {
    jsgppJNI.LearnerSGDE_train__SWIG_1(swigCPtr, this);
  }

  public void trainOnline(DataVector labels, DataMatrix testData, DataVector testLabels, DataMatrix validData, DataVector validLabels, DataVector classLabels, long maxDataPasses, String refType, String refMonitor, long refPeriod, double accDeclineThreshold, long accDeclineBufferSize, long minRefInterval, boolean usePrior) {
    jsgppJNI.LearnerSGDE_trainOnline(swigCPtr, this, DataVector.getCPtr(labels), labels, DataMatrix.getCPtr(testData), testData, DataVector.getCPtr(testLabels), testLabels, DataMatrix.getCPtr(validData), validData, DataVector.getCPtr(validLabels), validLabels, DataVector.getCPtr(classLabels), classLabels, maxDataPasses, refType, refMonitor, refPeriod, accDeclineThreshold, accDeclineBufferSize, minRefInterval, usePrior);
  }

  public void storeResults(DataMatrix testDataset) {
    jsgppJNI.LearnerSGDE_storeResults(swigCPtr, this, DataMatrix.getCPtr(testDataset), testDataset);
  }

  public void predict(DataMatrix testDataset, DataVector predictedLabels) {
    jsgppJNI.LearnerSGDE_predict(swigCPtr, this, DataMatrix.getCPtr(testDataset), testDataset, DataVector.getCPtr(predictedLabels), predictedLabels);
  }

  public double getAccuracy(DataMatrix testDataset, DataVector referenceLabels, double threshold) {
    return jsgppJNI.LearnerSGDE_getAccuracy__SWIG_0(swigCPtr, this, DataMatrix.getCPtr(testDataset), testDataset, DataVector.getCPtr(referenceLabels), referenceLabels, threshold);
  }

  public double getAccuracy(DataVector referenceLabels, double threshold, DataVector predictedLabels) {
    return jsgppJNI.LearnerSGDE_getAccuracy__SWIG_1(swigCPtr, this, DataVector.getCPtr(referenceLabels), referenceLabels, threshold, DataVector.getCPtr(predictedLabels), predictedLabels);
  }

  public double getError(DataMatrix data, DataVector labels, double threshold, String errorType) {
    return jsgppJNI.LearnerSGDE_getError(swigCPtr, this, DataMatrix.getCPtr(data), data, DataVector.getCPtr(labels), labels, threshold, errorType);
  }

  public void setError(double value) {
    jsgppJNI.LearnerSGDE_error_set(swigCPtr, this, value);
  }

  public double getError() {
    return jsgppJNI.LearnerSGDE_error_get(swigCPtr, this);
  }

  public void setAvgErrors(DataVector value) {
    jsgppJNI.LearnerSGDE_avgErrors_set(swigCPtr, this, DataVector.getCPtr(value), value);
  }

  public DataVector getAvgErrors() {
    long cPtr = jsgppJNI.LearnerSGDE_avgErrors_get(swigCPtr, this);
    return (cPtr == 0) ? null : new DataVector(cPtr, false);
  }

}
