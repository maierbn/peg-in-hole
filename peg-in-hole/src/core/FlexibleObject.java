package core;

import java.util.Arrays;
import javafx.geometry.Point2D;

/*
 * This class represents our flexible objects
 */
public class FlexibleObject {
	public double length;
	public double width;
	public double thickness;
	
	public double density;
	public double youngsModulus;			//Young's modulus (a quantity that measures an object or substance's resistance to being deformed elasticall)
	
	public double secondMomentOfInertia;	//2nd Area Moment (Flaechentraegheitsmoment)
	
	public int deflectionRes;
	public Point2D[] deflectionPoints; 
	
	
	public FlexibleObject(double length, double width, double thickness, double density, double youngsModulus) {
		this.length = length;
		this.width = width;
		this.thickness = thickness;
		
		this.density = density;
		this.youngsModulus = youngsModulus;
		
		this.secondMomentOfInertia = Formulas.calculateSecondMomentOfInertia(width, thickness);
		
		Log.print("Created flexible object:\n" + this);
	}
	
	public void calcDeflectionValues(int deflectionRes) {
		this.deflectionRes = deflectionRes;
		deflectionPoints = new Point2D[deflectionRes+1];
		double stepSize = this.length/this.deflectionRes;
		
		Log.print("Calculatin deflection values..");
		
		/**
		 * creates delta values for deflection
		 * 
		 * "(i equals 0)? then return 0 : else do other stuff"
		 * handles edge case at the beginning, inline
		 * 
		 * sqrt is expensive - maybe there's a better way?
		 */
		int i = 0;
		for (double x = 0; x <= this.length; x += stepSize) {
			
			double yPrev = (i==0)?0:deflectionPoints[i-1].getY();
			double yCoord = Formulas.deflection(this, x);
			double yDelta = yCoord-yPrev;
			
			// pythagoras: sqrt(bSquared=cSquared-aSquared)
			double cSq = (i==0)?0:Math.pow(stepSize, 2);
			double aSq = Math.pow(yDelta, 2);
			
			double xPrev = (i==0)?0:deflectionPoints[i-1].getX();
			double xCoord = Math.sqrt(cSq-aSq) + xPrev;
			
			deflectionPoints[i] = new Point2D(xCoord, yCoord);			
			
			i++;
		}
		
		/**
		 * displace the curve so end is at (0,0)
		 * by getting the last point and moving all points
		 * by its negative vector
		 */
		Point2D displacement = new Point2D(-deflectionPoints[deflectionRes].getX(),-deflectionPoints[deflectionRes].getY());

		for (int j = 0; j < deflectionPoints.length; j++) {
			deflectionPoints[j] = deflectionPoints[j].add(displacement);
		}
		
		Log.print("Deflection Points:\n\t"
				+ Arrays.toString(deflectionPoints)
				);
	}
		
	@Override
	public String toString() {
		return "----------------------------------------------\n"
				+ "Flexible Object (@" + Integer.toHexString(hashCode()) + ")\n"
				+ "lenght = " + this.length + "\t"
				+ "width = " + this.width + "\t"
				+ "height = " + this.thickness + "\n"
				+ "E = " + this.youngsModulus + "\t"
				+ "I = " + this.secondMomentOfInertia + "\n"
				+ "----------------------------------------------";
	}
}
