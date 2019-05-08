package userInterface;

import core.FlexibleObject;
import core.Formulas;
import core.Simulation;
import javafx.geometry.Point3D;

public class Main {
	public static void main(String[] args) {
		double length = 0.080; // meter
		double width = 0.015; // meter
		double thickness = 0.002; // meter
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
		double slitSize = 0.005;

		FlexibleObject f = new FlexibleObject(length, width, thickness, density, youngsModulus, deflectionRes);

		// CONTROL POINTS
		Point3D[] generatedCPs = Formulas.generateCPs(f, 40);
		System.out.println("Generated a total of " + generatedCPs.length + " control points");
		Formulas.generateSampleMatrix(generatedCPs, f, trajectoryRes, slitSize, 8, "results_successful.arff", true, true);
		
		
		Point3D cp = new Point3D(2.97567275825991239824e-001*-1*0.1,
				2.98237271554092494668e-010*0.1,
				6.11962449156885246460e-001*20);
		
		// SIMULATION RUN
		Simulation testSim = new Simulation(f, cp, trajectoryRes, slitSize);
		testSim.calcTrajectory();
		testSim.calcDeflectionsWithTrajectory();
		double clearance = testSim.calcClearance();
		System.out.println("Resulting clearance is " + clearance);
		AnimatedDeflectionDiagram.draw(f, testSim.deflections, testSim.trajectory, testSim.slitSize);
	}
}
