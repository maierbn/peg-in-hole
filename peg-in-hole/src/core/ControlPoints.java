package core;

/**
 * This class calculates and holds the controlpoints for a FlexibleObject
 *
 */
public class ControlPoints {
	public double[] x;
	public double[] y;
	public double[] z;
	public double[] minDistance;
	
	FlexibleObject f;
	
	public ControlPoints(int count, FlexibleObject f){
		x = new double[count];
		y = new double[count];
		z = new double[count];
		minDistance = new double[count];
		
		f = this.f;
	}
	
	void calculateXYZ() {
		
	}
}
