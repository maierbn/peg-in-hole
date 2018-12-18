package userInterface;

import core.FlexibleObject;

public class Main {

	public static void main(String[] args) {
		testDeflection();
	}

	public static void testDeflection() {
		double length = 0.040; // meter
		double width = 0.015; // meter
		double heigth = 0.002; // meter
		double density = 1150;
		double youngsModulus = 3.6E6;

		/**
		 * for how many x-values do we want to calculate the deflection
		 * 
		 * also reflects the amount of rigid blocks for collision detection (this - 1)
		 * as well as amount of joints (this - 1)
		 */
		int deflectionRes = 3000;

		FlexibleObject flex = new FlexibleObject(length, width, heigth, density, youngsModulus);
		
		flex.calcDeflectionValues(deflectionRes);
		
		deflectionDiagram.draw(flex);
		
		double[] a = {1,2};
		double[] b = {2,3};
		
		double veclength = core.Formulas.vectorLength(a);
		
		System.out.println(veclength);
		
		double result = core.Formulas.dotProduct(a, b);
		
		System.out.println(result);
		
		double angle = core.Formulas.angleOfTwoVectors(a, b);
		
		System.out.println(angle);
	}
}
