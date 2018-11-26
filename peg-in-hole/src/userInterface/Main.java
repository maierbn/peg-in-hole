package userInterface;

import core.FlexibleObject;
import core.Formulas;

public class Main {

	public static void main(String[] args) {
		testDeflection();
	}

	public static void testDeflection() {
		float length = 0.080f;	//meter
		float width = 0.015f;	//meter
		float heigth = 0.002f;	//meter
		float youngsModulus = (float) (3.6f * Math.pow(10, 6));
		
		FlexibleObject f = new FlexibleObject(length, width, heigth, youngsModulus);
		
		System.out.println(f);
		
		for(float x = 0; x <= f.length; x += 0.01) {
			System.out.println("w(" + x + ") = " + Formulas.deflection(f, x));
		}
		
		System.out.println("w_max = " + Formulas.deflectionMax(f));
	}
}
