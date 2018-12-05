package userInterface;

import core.FlexibleObject;
import core.Simulation;

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
		
		int simulationSteps = 3;
		
		FlexibleObject f = new FlexibleObject(length, width, heigth, density, youngsModulus);
		
		System.out.println(f);
		
		Simulation sim = new Simulation(f, simulationSteps);
		
		sim.start();
		
	}
}
