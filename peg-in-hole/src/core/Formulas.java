package core;

import javafx.geometry.Point2D;
import javafx.geometry.Point3D;

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
	private static double deflectionYP0(FlexibleObject f, double x) {
		double q = f.width * f.thickness * f.density * Constants.gravitationalAcceleration;
		double L = f.length;
		double E = f.youngsModulus;
		double I = f.secondMomentOfInertia;
		double additional = (-(1d / 6d) * q * Math.pow(L, 3) * x) / (E * I);

		// double angleP0 = Math.atan(-(1d/6d) * q * Math.pow(L, 3));

		return (q * x * x * (6 * L * L - 4 * L * x + x * x)) / (24 * E * I) + additional;
	}

	/**
	 * calculates the initial deflectionPoints at P0 using the assumption, that the
	 * right end of the FlexibleObject has an angle of 0 degrees and shifts the
	 * right end to (0,0)
	 * 
	 * @param f
	 * @return
	 */
	public static Point2D[] deflectionsP0(FlexibleObject f) {
		int deflectionRes = f.deflectionRes;
		Point2D[] deflectionPoints = new Point2D[deflectionRes + 1];
		
		//Log.print("Calculatin deflection values..");

		/**
		 * creates delta values for deflection
		 * 
		 * "(i equals 0)? then return 0 : else do other stuff" handles edge case at the
		 * beginning, inline
		 * 
		 * TODO sqrt is expensive - maybe there's a better way?
		 */
		double stepSize = f.length / deflectionRes;
		double x = 0;
		for (int i = 0; i <= deflectionRes; i++) {
			double yPrev = (i == 0) ? 0 : deflectionPoints[i - 1].getY();
			double yCoord = Formulas.deflectionYP0(f, x);
			double yDelta = yCoord - yPrev;

			// pythagoras: sqrt(bSquared=cSquared-aSquared)
			double cSq = (i == 0) ? 0 : Math.pow(stepSize, 2);
			double aSq = Math.pow(yDelta, 2);

			double xPrev = (i == 0) ? 0 : deflectionPoints[i - 1].getX();
			double xCoord = Math.sqrt(cSq - aSq) + xPrev;

			deflectionPoints[i] = new Point2D(xCoord, yCoord);
			x += stepSize;
		}
		//Log.print("Deflection Points:\n\t" + Arrays.toString(deflectionPoints));

		/**
		 * displace the curve so end is at (0,0) by getting the last point and moving
		 * all points by its negative vector
		 */
		Point2D displacement = new Point2D(-deflectionPoints[deflectionRes].getX(),
				-deflectionPoints[deflectionRes].getY());

		for (int j = 0; j < deflectionPoints.length; j++) {
			deflectionPoints[j] = deflectionPoints[j].add(displacement);
		}

		return deflectionPoints;
	}
	
	/**
	 * calculates the deflection-Points with a given trajectoryPoint (x,y,angle)
	 * @param f
	 * @param trajectoryPoint
	 * @return
	 */
	public static Point2D[] deflectionsPi(FlexibleObject f, Point3D trajectoryPoint) {
		//TODO
		return null;
	}

	/**
	 * factorial function limited for 0, 1 and 2 for internal use in bernstein
	 * polynomial calculations
	 * 
	 * @param i the integer that should be fac'd
	 * @return i! or 0 if input out of range
	 */
	private static double fac(int i) {
		switch (i) {
		case 0:
			return 1;
		case 1:
			return 1;
		case 2:
			return 2;
		default:
			return 0;
		}
	}

	/**
	 * solves the bernstein polynomial for hardcoded degree n=2
	 * 
	 * @param i range 0..2
	 * @param t position on curve, 0..1
	 * @return (n over i) * (1-t)^n-i * t^i
	 */
	private static double bernstein(int i, double t) {
		return (fac(2) / (fac(i) * fac(2 - i))) * Math.pow(1 - t, 2 - i) * Math.pow(t, i);
	}

	/**
	 * calculate big B term
	 * 
	 * all operations are component-wise multiply() and add() are already
	 * component-wise ops, should be fine
	 * 
	 * @param p0 staring point
	 * @param cp control point
	 * @param p1 end point aka (0,0,0)
	 * @param t  position on the trajectory, 0..1
	 * @return B(t)=b_0,2(t)*P0 + b_1,2(t)*2*cp + b_2,2(t)*P1 / b_0,2(t) +
	 *         b_1,2(t)*2 + b_2,2(t)
	 */
	public static Point3D bigB(Point3D p0, Point3D cp, Point3D p1, double t) {
		Point3D numerator = p0.multiply(bernstein(0, t)).add(cp.multiply(2 * bernstein(1, t)))
				.add(p1.multiply(bernstein(2, t)));
		double divisor = bernstein(0, t) + 2 * bernstein(1, t) + bernstein(2, t);

		Point3D supposedValue = numerator.multiply(1 / divisor);

		return supposedValue;
	}

	/**
	 * calculates the x-y-angle point on position t of the arm trajectory
	 * 
	 * all operations are component-wise
	 * 
	 * P1-P0 could be simplified to (-P0), since P1 is always zero however, for the
	 * sake of consistency and debugging, the original form is preferred
	 * 
	 * @param p0 staring point
	 * @param cp control point
	 * @param p1 end point aka (0,0,0)
	 * @param t  position on the trajectory, 0..1
	 * @return P(t) = P0 + B(t) * (P1 - P0)
	 */
	public static Point3D trajectory(Point3D p0, Point3D cp, Point3D p1, double t) {

		Point3D B = bigB(p0, cp, p1, t);
		Point3D P1minusP0 = p1.subtract(p0);

		double multCoeffX = B.getX() * P1minusP0.getX();
		double multCoeffY = B.getY() * P1minusP0.getY();
		double multCoeffZ = B.getZ() * P1minusP0.getZ();
		Point3D coeffMultB = new Point3D(multCoeffX, multCoeffY, multCoeffZ);

		return p0.add(coeffMultB);
	}
}
