/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.8
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sgpp;

public class DataSourceBuilder {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected DataSourceBuilder(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(DataSourceBuilder obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        jsgppJNI.delete_DataSourceBuilder(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public DataSourceBuilder() {
    this(jsgppJNI.new_DataSourceBuilder(), true);
  }

  public DataSourceBuilder withPath(String filePath) {
    return new DataSourceBuilder(jsgppJNI.DataSourceBuilder_withPath(swigCPtr, this, filePath), false);
  }

  public DataSourceBuilder withCompression(boolean isCompressed) {
    return new DataSourceBuilder(jsgppJNI.DataSourceBuilder_withCompression(swigCPtr, this, isCompressed), false);
  }

  public DataSourceBuilder withFileType(DataSourceFileType fileType) {
    return new DataSourceBuilder(jsgppJNI.DataSourceBuilder_withFileType(swigCPtr, this, fileType.swigValue()), false);
  }

  public DataSourceBuilder inBatches(long howMany) {
    return new DataSourceBuilder(jsgppJNI.DataSourceBuilder_inBatches(swigCPtr, this, howMany), false);
  }

  public DataSourceBuilder withBatchSize(long batchSize) {
    return new DataSourceBuilder(jsgppJNI.DataSourceBuilder_withBatchSize(swigCPtr, this, batchSize), false);
  }

  public DataSource assemble() {
    long cPtr = jsgppJNI.DataSourceBuilder_assemble(swigCPtr, this);
    return (cPtr == 0) ? null : new DataSource(cPtr, false);
  }

  public DataSource fromConfig(DataSourceConfig config) {
    long cPtr = jsgppJNI.DataSourceBuilder_fromConfig(swigCPtr, this, DataSourceConfig.getCPtr(config), config);
    return (cPtr == 0) ? null : new DataSource(cPtr, false);
  }

}
