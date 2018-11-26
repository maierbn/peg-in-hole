package userInterface;

import core.FlexibleObject;
import core.Formulas;

public class Main {

	public static void main(String[] args) {
		FlexibleObject f = new FlexibleObject(0.2f, 0.05f, 0.02f, (float) (3.6f * Math.pow(10, 6)));
		
		for(float x = 0; x <= f.length; x += 0.01) {
			System.out.println("w(" + x + ") = " + Formulas.deflection(f, x));
		}

	}

	public float inertia(float I) {
		float h = 0;		//height
		float b = 0;		//width
		I = (b*(h*h*h))/12;
		return I;
		
	}
	public float deflection(float x) {
		
		
		return x;
	}
}
