package userInterface;


import core.FlexibleObject;
import javafx.geometry.*;
import javafx.scene.shape.QuadCurve;

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
		
		/**
		 * trajectory - first iteration, to be outsourced into another class
		 * 
		 * Coefficients are x-coord, y-coord, angle to x-axis
		 */
		Point3D p1 = new Point3D(0, 0, 0);
		
		double deflectionXmax = flex.deflectionPoints[deflectionRes].getX();
		double deflectionYmax = flex.deflectionPoints[deflectionRes].getY();
		Point2D deflectionFirst = flex.deflectionPoints[1];
		double angle0 = deflectionFirst.angle(1, 0);

		Point3D p0 = new Point3D(-deflectionXmax,-deflectionYmax,angle0);
			
		deflectionDiagram.draw(flex);

	}
}
