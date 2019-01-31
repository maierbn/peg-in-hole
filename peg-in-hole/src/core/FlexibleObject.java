package core;

import javafx.geometry.Point2D;
import userInterface.DeflectionDiagram;

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
	public Point2D[] deflectionP0;
	
	
	public FlexibleObject(double length, double width, double thickness, double density, double youngsModulus, int deflectionRes) {
		
		this.length = length;
		this.width = width;
		this.thickness = thickness;
		
		this.density = density;
		this.youngsModulus = youngsModulus;
		
		this.deflectionRes = deflectionRes;
		
		
		this.secondMomentOfInertia = Formulas.calculateSecondMomentOfInertia(width, thickness);
		
		deflectionP0 = Formulas.deflectionsP0(this);
		
		Log.print("Created flexible object:\n" + this);
	}
	
	public void drawDeflectionP0() {
		DeflectionDiagram.draw(this);
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
