/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.8
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sgpp;

public class MatrixDecompositionTypeParser {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected MatrixDecompositionTypeParser(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(MatrixDecompositionTypeParser obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        jsgppJNI.delete_MatrixDecompositionTypeParser(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public static MatrixDecompositionType parse(String input) {
    return MatrixDecompositionType.swigToEnum(jsgppJNI.MatrixDecompositionTypeParser_parse(input));
  }

  public static String toString(MatrixDecompositionType type) {
    return jsgppJNI.MatrixDecompositionTypeParser_toString(type.swigValue());
  }

  public MatrixDecompositionTypeParser() {
    this(jsgppJNI.new_MatrixDecompositionTypeParser(), true);
  }

}
