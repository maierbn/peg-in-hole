package core;

/*
 * This class represents our flexible objects
 */
public class FlexibleObject {
	float length;
	float width;
	float height;
	
	float youngsModulus;
	
	double secondMomentOfInertia;
	
	//constructor
	public FlexibleObject(float length, float width, float height, float youngsModulus) {
		this.length = length;
		this.width = width;
		this.height = height;
		
		this.youngsModulus = youngsModulus;
		
		this.secondMomentOfInertia = Formulas.calculateSecondMomentOfInertia(width, height);
	}
	
	

}
