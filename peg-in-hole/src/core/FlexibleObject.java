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
	
	
	//
	public int deflectionRes;
//	public double[] deflectionYValues;
//	public double[] deflectionXValues;
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
		
		Log.print("Calculatin deflection values..");
		
//		deflectionYValues = new double[deflectionRes+1];
//		deflectionXValues = new double[deflectionRes+1];
		
		deflectionPoints = new Point2D[deflectionRes+1];

		double stepSize = this.length/this.deflectionRes;
		
		// creates delta values for deflection
		int i = 0;
		for (double x = 0; x <= this.length; x += stepSize) {
			
			double yPrev = (i==0)?0:deflectionPoints[i-1].getY();
			double yCoord = Formulas.deflection(this, x);
			double yDelta = yCoord-yPrev;
			
			double cSq = Math.pow(stepSize, 2);
			double aSq = Math.pow(yDelta, 2);
			
			double xPrev = (i==0)?0:deflectionPoints[i-1].getX();
			double xCoord = Math.sqrt(cSq-aSq) + xPrev;
			
			deflectionPoints[i] = new Point2D(xCoord, yCoord);			
			
			i++;
		}
		
		// displace the curve so end is at (0,0)
		Point2D displacement = new Point2D(-deflectionPoints[deflectionRes].getX(),-deflectionPoints[deflectionRes].getY());
		
		for (int j = 0; j < deflectionPoints.length; j++) {
			deflectionPoints[j] = deflectionPoints[j].add(displacement);
		}
		
		// generate kartesian x values, since naive approach IS WRONG
//		deflectionXValues[0] = 0;
//		
//		for (int j = 1; j < deflectionYValues.length; j++) {
//			deflectionXValues[j] = Math.sqrt( - );
//			deflectionXValues[j] += deflectionXValues[j-1];
//
//		}
		
		Log.print("Deflection values X:\n\t"
				+ Arrays.toString(deflectionPoints) 
				+ "Deflection values Y:\n\t"
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
