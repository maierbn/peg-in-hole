package core;

/*
 * This class contains static formulas used by other classes
 */
public class Formulas {
	
	public static double calculateSecondMomentOfInertia(float width, float height) {
		//I = bh^3 / 12
		return (width * Math.pow(height, 3)) / 12;
	}
	
	public static double deflection(FlexibleObject flexibleObject, float x) {
		//w(x) = qx^2(6L^2 - 4Lx + x^2) / 24EI
		
		float q = Constants.gravitationalAcceleration;
		float L = flexibleObject.length;
		float E = flexibleObject.youngsModulus;
		double I = flexibleObject.secondMomentOfInertia;
		
		return (q * x * x * (6 * L * L - 4 * L * x + x * x))/(24 * E * I);
	}
	
	//according to wikipedia https://en.wikipedia.org/wiki/Euler%E2%80%93Bernoulli_beam_theory
	public static double deflectionMax(FlexibleObject flexibleObject) {
		float q = Constants.gravitationalAcceleration;
		float L = flexibleObject.length;
		float E = flexibleObject.youngsModulus;
		double I = flexibleObject.secondMomentOfInertia;
		
		return (q * L * L * L * L)/(8 * E * I);
	}
}
