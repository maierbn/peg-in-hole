package core;

/*
 * This class contains static formulas used by other classes
 */
public class Formulas {
	
	public static double calculateSecondMomentOfInertia(float width, float height) {
		//I = bh^3 / 12
		return (width * Math.pow(height, 3)) / 12;
	}
}
