package userInterface;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

import core.FlexibleObject;
import core.Log;

public class deflectionDiagram {
	public static void draw(FlexibleObject flex) {
		Log.print("Drawing deflection-diagram..");
		// generate x axis values through some esoteric streaming bullshit
		//double[] xData = IntStream.rangeClosed(0, simDeflectionRes).mapToDouble((x) -> (double) x).toArray();
			
		// improvement: x axis should be length of object, not amount of steps
		double[] xData = IntStream
				.rangeClosed(0, flex.deflectionRes)
				.mapToDouble(x -> x*flex.length/flex.deflectionRes)
				.toArray();
		
		// get the deflection values from the flexibleObject as graph y data
		double[] yData = flex.deflectionValues;
		// cause it doesn't bend upwards *lennyface*
		double[] yDataInverted = DoubleStream.of(yData).map(x -> -x).toArray();

		// create chart and show it
		XYChart chart = QuickChart.getChart("Deflection", "x in m", "y in m", "y(x)", xData, yDataInverted);
		new SwingWrapper<XYChart>(chart).displayChart();
	}
}
