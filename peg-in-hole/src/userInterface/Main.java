package userInterface;

import core.FlexibleObject;
import core.Simulation;
import javafx.geometry.Point3D;

public class Main {
	public static void main(String[] args) {
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
		int deflectionRes = 30;
		int trajectoryRes = 30;
		double slitSize = 0.1;

		FlexibleObject f = new FlexibleObject(length, width, heigth, density, youngsModulus, deflectionRes);

		Point3D cp = new Point3D(-0.03, 0.01, -15.0);
		Simulation testSim = new Simulation(f, cp, trajectoryRes, slitSize);
		
		testSim.calcTrajectory();
		testSim.drawTrajectory();

		testSim.calcDeflectionsWithTrajectory();
		double clearance = testSim.calcClearance();
		
//		DeflectionDiagram.draw(f, testSim.deflections.get(testSim.deflections.size() - 1));
		System.out.println("Resulting clearance is " + clearance);
		
		f.drawDeflectionP0();
		DeflectionDiagram.draw(f, testSim.deflections.get(1));
		DeflectionDiagram.draw(f, testSim.deflections.get(2));
		AnimatedDeflectionDiagram.draw(f, testSim.deflections, testSim.trajectory);
		
	}
}
