package core;

/*
 * This class contains static formulas used by other classes
 */
public class Formulas {

	public static double calculateSecondMomentOfInertia(double width, double thickness) {
		// I = bh^3 / 12
		return (width * Math.pow(thickness, 3)) / 12;
	}

	public static double deflection(FlexibleObject f, double x) {
		// w(x) = qx^2(6L^2 - 4Lx + x^2) / 24EI

		double q = f.width * f.thickness * f.density * Constants.gravitationalAcceleration;
		double L = f.length;
		double E = f.youngsModulus;
		double I = f.secondMomentOfInertia;

		return (q * x * x * (6 * L * L - 4 * L * x + x * x)) / (24 * E * I);
		// return (q * q * (6 * L * L - 4 * L * x + x * x))/(24 * E * I);
	}

}
