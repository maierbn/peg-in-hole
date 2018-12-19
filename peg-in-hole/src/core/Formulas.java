package core;

/*
 * This class contains static formulas used by other classes
 */
public class Formulas {

	/**
	 * 
	 * @param width
	 * @param thickness
	 * @return I = bh^3 / 12
	 */
	public static double calculateSecondMomentOfInertia(double width, double thickness) {
		return (width * Math.pow(thickness, 3)) / 12;
	}

	/**
	 * 
	 * @param f the flexible object
	 * @param x the position on the object
	 * @return w(x) = [qx^2(6L^2 - 4Lx + x^2) / 24EI] - [(1/6 q L^3 x) / EI]
	 */
	public static double deflection(FlexibleObject f, double x) {
		double q = f.width * f.thickness * f.density * Constants.gravitationalAcceleration;
		double L = f.length;
		double E = f.youngsModulus;
		double I = f.secondMomentOfInertia;
		double additional = (-(1d/6d) * q * Math.pow(L, 3) * x) /(E*I);

		//double angleP0 = Math.atan(-(1d/6d) * q * Math.pow(L, 3));

		return (q * x * x * (6 * L * L - 4 * L * x + x * x)) / (24 * E * I) + additional;
	}
}
