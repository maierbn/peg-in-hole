package core;

/*
 * This class represents our flexible objects
 */
public class FlexibleObject {
	public double length;
	public double width;
	public double thickness;
	
	public double density;
	public double youngsModulus;				//Young's modulus (a quantity that measures an object or substance's resistance to being deformed elasticall)
	
	public double secondMomentOfInertia;	//2nd Area Moment (Flaechentraegheitsmoment)
	
	//constructor
	public FlexibleObject(double length, double width, double thickness, double density, double youngsModulus) {
		this.length = length;
		this.width = width;
		this.thickness = thickness;
		
		this.density = density;
		this.youngsModulus = youngsModulus;
		
		this.secondMomentOfInertia = Formulas.calculateSecondMomentOfInertia(width, thickness);
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
