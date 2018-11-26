package userInterface;

import core.FlexibleObject;
import core.Formulas;

public class Main {

	public static void main(String[] args) {
		testDeflection();
	}

	public static void testDeflection() {
		double length = 0.080;	//meter
		double width = 0.015;	//meter
		double heigth = 0.002;	//meter
		double youngsModulus = 3.6 * Math.pow(10, 6);
		
		FlexibleObject f = new FlexibleObject(length, width, heigth, youngsModulus);
		
		System.out.println(f);
		
		for(double x = 0; x <= f.length; x += 0.01) {
			System.out.println("w(" + x + ") = " + Formulas.deflection(f, x));
		}
		
		System.out.println("w_max = " + Formulas.deflectionMax(f));
	}
}
