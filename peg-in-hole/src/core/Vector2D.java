package core;

/**
 * object representing a vector w/ two coefficients
 */
public class Vector2D {
	public double x;
	public double y;
	
	public Vector2D(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * length of a two-dim vector
	 * 
	 * @param vector
	 * @return
	 */
	public static double vectorLength(Vector2D vector) {
		return Math.sqrt(vector.x * vector.x + vector.y * vector.y);
	}
	
	/**
	 * Scalar product of two vectors
	 * 
	 * @param a first vector
	 * @param b second vector
	 * @return dot product
	 */
	public static double dotProduct(Vector2D a, Vector2D b) {
		return a.x * b.x + a.y * b.y;
	}
	
	/**
	 * 
	 * @param a the first vector
	 * @param b the second vector
	 * @return the angle between two vectors
	 */
	public static double angleOfTwoVectors(Vector2D a, Vector2D b) {
		return Math.acos(dotProduct(a,b)/(vectorLength(a)*vectorLength(b)));
	}
}
