package core;

/*
 * This class represents our flexible objects
 */
public class FlexibleObject {
	float length;
	float width;
	float height;
	
	float stiffness;
	
	double secondMomentOfInertia;
	
	//constructor
	public FlexibleObject(float length, float width, float height, float stiffness) {
		this.length = length;
		this.width = width;
		this.height = height;
		
		this.stiffness = stiffness;
		
		this.secondMomentOfInertia = Formulas.calculateSecondMomentOfInertia(width, height);
	}
	
	

}
