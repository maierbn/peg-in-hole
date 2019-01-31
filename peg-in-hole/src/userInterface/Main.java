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
		int deflectionRes = 9;
		int trajectoryRes = 10;

		FlexibleObject f = new FlexibleObject(length, width, heigth, density, youngsModulus, deflectionRes);
		f.drawDeflectionP0();

		Point3D cp = new Point3D(-0.035, 0.01, 0.16);
		Simulation testSim = new Simulation(f, cp, trajectoryRes);
		
		testSim.calcTrajectory();
		testSim.drawTrajectory();

		testSim.calcDeflectionsWithTrajectory();
		testSim.calcSmallestDistanceToHole();
	}
}
