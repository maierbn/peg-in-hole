package core;

import java.util.Arrays;

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
	public double[] deflectionYValues;
	public double[] deflectionXValues;
	
	
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
		
		deflectionYValues = new double[deflectionRes+1];
		deflectionXValues = new double[deflectionRes+1];

		double stepSize = this.length/this.deflectionRes;
		
		// creates delta values for deflection
		int i = 0;
		for (double x = 0; x <= this.length; x += stepSize) {
			deflectionYValues[i] = Formulas.deflection(this, x);
			i++;
		}
		
		// generate kartesian x values, since naive approach IS WRONG
		deflectionXValues[0] = 0;
		
		for (int j = 1; j < deflectionYValues.length; j++) {
			deflectionXValues[j] = Math.sqrt(Math.pow(stepSize, 2) - Math.pow(deflectionYValues[j]-deflectionYValues[j-1], 2));
			deflectionXValues[j] += deflectionXValues[j-1];

		}
		
		Log.print("Deflection values X:\n\t"
				+ Arrays.toString(deflectionXValues) 
				+ "Deflection values Y:\n\t"
				+ Arrays.toString(deflectionYValues)
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
