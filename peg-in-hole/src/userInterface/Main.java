package userInterface;

import core.FlexibleObject;
import core.Formulas;

public class Main {

	public static void main(String[] args) {
		testDeflection();
	}

	public static void testDeflection() {
		double length = 0.040;	//meter
		double width = 0.015;	//meter
		double heigth = 0.002;	//meter
		double density = 1150;
		double youngsModulus = 3.6E6;
		
		FlexibleObject f = new FlexibleObject(length, width, heigth, density, youngsModulus);
		
		System.out.println(f);
		
		for(double x = 0; x <= f.length; x += 0.01) {
			System.out.println("w(" + x + ") = " + Formulas.deflection(f, x));
		}
		
	}
}
