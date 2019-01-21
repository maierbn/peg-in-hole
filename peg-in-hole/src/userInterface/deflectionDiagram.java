package userInterface;

import java.util.stream.DoubleStream;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.style.markers.SeriesMarkers;
import core.FlexibleObject;
import core.Log;

public class deflectionDiagram {
	public static void draw(FlexibleObject flex) {
		Log.print("Drawing deflection-diagram..");
		
		
		// corrected x axis values
		double[] xData = new double[flex.deflectionRes+1];
		for (int i = 0; i < xData.length; i++) {
			xData[i] = flex.deflectionPoints[i].getX();
		}
		
		// get the deflection values from the flexibleObject as graph y data
		double[] yData = new double[flex.deflectionRes+1];
		for (int i = 0; i < yData.length; i++) {
			yData[i] = flex.deflectionPoints[i].getY();
		}
		// cause it doesn't bend upwards *lennyface*
		double[] yDataInverted = DoubleStream.of(yData).map(x -> -x).toArray();

		// create chart and show it
		XYChart chart = new XYChartBuilder().width(600).height(400).title("Deflection").xAxisTitle("X in m").yAxisTitle("Y in m").build();
		
		chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Line);
		chart.getStyler().setChartTitleVisible(false);
		chart.getStyler().setMarkerSize(8);
		
		XYSeries series = chart.addSeries("y(x)", xData, yDataInverted);
		series.setMarker(SeriesMarkers.CIRCLE);
		
		// if displaced to zero, use - length & 0
		chart.getStyler().setXAxisMin(-flex.length);
		chart.getStyler().setXAxisMax(0d);
		chart.getStyler().setYAxisMax(flex.length);
		
		new SwingWrapper<XYChart>(chart).displayChart();
	}
}
