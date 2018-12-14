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
		 * on how many points we want a deflection to be calculated
		 * 
		 * also reflects the amount of rigid blocks for collision detection (this - 1)
		 * as well as amount of joints (this - 1)
		 */
		int simDeflectionRes = 3;

		FlexibleObject flexObj = new FlexibleObject(length, width, heigth, density, youngsModulus);

		Simulation sim = new Simulation(flexObj, simDeflectionRes);

		sim.start();

		// generate x axis values through some esoteric streaming bullshit
		//double[] xData = IntStream.rangeClosed(0, simDeflectionRes).mapToDouble((x) -> (double) x).toArray();
		
		// improvement: x axis should be length of object, not amount of steps
		double[] xData = IntStream
				.rangeClosed(0, simDeflectionRes)
				.mapToDouble(x -> x*length/simDeflectionRes)
				.toArray();
		
		// get the deflection values from simulation as graph y data
		double[] yData = sim.deflectionValArray;
		// cause it doesn't bend upwards *lennyface*
		double[] yDataInverted = DoubleStream.of(yData).map(x -> -x).toArray();

		// create chart and show it
		XYChart chart = QuickChart.getChart("Sample Chart", "X", "Y", "y(x)", xData, yDataInverted);
		new SwingWrapper(chart).displayChart();

	}
}
