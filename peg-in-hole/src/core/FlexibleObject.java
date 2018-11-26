package core;

/*
 * This class represents our flexible objects
 */
public class FlexibleObject {
	public float length;
	public float width;
	public float height;
	
	public float youngsModulus;			//Young's modulus (a quantity that measures an object or substance's resistance to being deformed elasticall)
	
	public double secondMomentOfInertia;	//2nd Area Moment (Flaechentraegheitsmoment)
	
	//constructor
	public FlexibleObject(float length, float width, float height, float youngsModulus) {
		this.length = length;
		this.width = width;
		this.height = height;
		
		this.youngsModulus = youngsModulus;
		
		this.secondMomentOfInertia = Formulas.calculateSecondMomentOfInertia(width, height);
	}
	
	@Override
	public String toString() {
		return "----------------------------------------------\n"
				+ "Flexible Object (@" + Integer.toHexString(hashCode()) + ")\n"
				+ "lenght = " + this.length + "\t"
				+ "width = " + this.width + "\t"
				+ "height = " + this.height + "\n"
				+ "E = " + this.youngsModulus + "\t"
				+ "I = " + this.secondMomentOfInertia + "\n"
				+ "----------------------------------------------\n";
		
	}
	

}
