package userInterface;

import java.io.FileInputStream;
import java.util.Properties;

import core.ControlPoints;
import core.FlexibleObject;
import core.Simulation;
import javafx.geometry.Point3D;

public class Main {
	public static void main(String[] args) {
		String configFile = args[0];
		Properties p = new Properties();

		try {
			p.load(new FileInputStream(configFile));
			Boolean generateDatabase = Boolean.valueOf(p.getProperty("generateDatabase"));
			Boolean simulateGivenControlPoint = Boolean.valueOf(p.getProperty("simulateGivenControlPoint"));
			
			String databaseFilename = String.valueOf(p.getProperty("databaseFilename"));
			
			double length = Double.parseDouble(p.getProperty("objectLength")); // meter
			double width = Double.parseDouble(p.getProperty("objectWidth")); // meter
			double thickness = Double.parseDouble(p.getProperty("objectThickness")); // meter
			double density = Double.parseDouble(p.getProperty("objectDensity"));
			double youngsModulus = Double.parseDouble(p.getProperty("objectYoungsModulus"));

			/**
			 * for how many x-values do we want to calculate the deflection
			 * 
			 * also reflects the amount of rigid blocks for collision detection (this - 1)
			 * as well as amount of joints (this - 1)
			 */
			int deflectionRes = Integer.parseInt(p.getProperty("deflectionRes"));
			int trajectoryRes = Integer.parseInt(p.getProperty("trajectoryRes"));
			double slitSize = Double.parseDouble(p.getProperty("slitSize"));
			boolean writeOnlySuccessful = Boolean.parseBoolean(p.getProperty("writeOnlySuccessful"));

			FlexibleObject f = new FlexibleObject(length, width, thickness, density, youngsModulus, deflectionRes);

			// CONTROL POINTS
			if (generateDatabase) {
				int granularity = Integer.parseInt(p.getProperty("granularity"));
				
				ControlPoints controlPoints = new ControlPoints(f, granularity);
				controlPoints.generateCPs();
				controlPoints.simulateAllCPs(trajectoryRes, slitSize);

				System.out.println("Generated a total of " + controlPoints.amount + " control points");
				controlPoints.writeARFF(8, databaseFilename, writeOnlySuccessful);
			}
			// SIMULATION RUN

			if (simulateGivenControlPoint) {
				double cpX = Double.parseDouble(p.getProperty("cpX"));
				double cpY = Double.parseDouble(p.getProperty("cpY"));
				double cpZ = Double.parseDouble(p.getProperty("cpZ"));

				Point3D cp = new Point3D(cpX * -1 * 0.1, cpY * 0.1, cpZ * 20);

				Simulation testSim = new Simulation(f, cp, trajectoryRes, slitSize);
				testSim.calcTrajectory();
				testSim.calcDeflectionsWithTrajectory();
				double clearance = testSim.calcClearance();
				System.out.println("Resulting clearance is " + clearance);
				AnimatedDeflectionDiagram.draw(f, testSim.deflections, testSim.trajectory, testSim.slitSize, testSim.cp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
