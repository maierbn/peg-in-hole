package userInterface;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import org.knowm.xchart.*;
import core.FlexibleObject;
//import core.Log;
import core.Simulation;

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
		int simDeflectionRes = 3;

		FlexibleObject flexObj = new FlexibleObject(length, width, heigth, density, youngsModulus);

		Simulation sim = new Simulation(flexObj, simDeflectionRes);

		sim.start();
		
		deflectionDiagram.draw(sim);

	}
}
