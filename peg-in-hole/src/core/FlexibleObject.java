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
	public double[] deflectionValues;
	
	
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
		
		deflectionValues = new double[deflectionRes+1];

		int i = 0;
		for (double x = 0; x <= this.length; x += (this.length / deflectionRes)) {
			deflectionValues[i] = Formulas.deflection(this, x);
			i++;
		}
		
		Log.print("Deflection value array:\n\t"
				+ Arrays.toString(deflectionValues));
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
