package userInterface;

import core.FlexibleObject;
import core.Formulas;
import javafx.geometry.*;

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
		int deflectionRes = 5;

		FlexibleObject flex = new FlexibleObject(length, width, heigth, density, youngsModulus);
		
		flex.calcDeflectionValues(deflectionRes);
		flex.displaceToZero();
		deflectionDiagram.draw(flex);
		
		//TODO
		testTrajectory(flex);
	}
	
	public static void testTrajectory(FlexibleObject flex) {
		
		Point3D[] trajPoints = new Point3D[flex.deflectionRes+1];
		double stepSize = 1/(double)flex.deflectionRes;
		
		// control points
		Point3D cp = new Point3D(-0.035, 0.01, 0.16);
		
		Point3D p1 = new Point3D(0, 0, 0);
		
		Point2D deflectionFirst = flex.deflectionPoints[1];
		double angle0 = 180-deflectionFirst.angle(1, 0);
		// y is negative since gravitation generally does not bend stuff upwards
		Point3D p0 = new Point3D(flex.deflectionPoints[0].getX(),-flex.deflectionPoints[0].getY(),angle0);

		// t between 0, 1
		int i = 0;
		for (double t = 0; t <= 1; t += stepSize)  {
			trajPoints[i]=Formulas.bigB(p0, cp, p1, t);
			System.out.println("Point "+i);
			System.out.println(trajPoints[i].getX());
			System.out.println(trajPoints[i].getY());
			System.out.println(trajPoints[i].getZ());
			i++;
		}
		
		TrajectoryDiagram.draw(flex.deflectionRes, trajPoints);
	}
}
