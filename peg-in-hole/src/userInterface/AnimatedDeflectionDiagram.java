package userInterface;

import java.util.ArrayList;
import java.util.stream.DoubleStream;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.style.AxesChartStyler;
import org.knowm.xchart.style.Styler.LegendPosition;
import org.knowm.xchart.style.markers.SeriesMarkers;

import core.FlexibleObject;
import javafx.geometry.Point2D;

public class AnimatedDeflectionDiagram {
	public static void draw(FlexibleObject f, 
							ArrayList<Point2D[]> deflections) {
		
		// initially fill render data with values at P0
		double[] xData = generateRenderData(f, deflections.get(0))[0];
		double[] yData = generateRenderData(f, deflections.get(0))[1];

		// create chart object
		XYChart chart = new XYChartBuilder()
				.width(1200)
				.height(1150)
				.title("Deflection")
				.xAxisTitle("x in m, real world")
				.yAxisTitle("x in m, real world")
				.build();
		XYSeries series = chart.addSeries(
				"Deflection of flexible Object, w(x)", 
				xData, 
				yData);
		
		((AxesChartStyler) chart.getStyler()
				.setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Line)
				.setChartTitleVisible(false)
//				.setToolTipsEnabled(true)	// comment this one out to remove labels
				.setToolTipsAlwaysVisible(true)
				.setLegendVisible(true)
				.setLegendPosition(LegendPosition.InsideNE))
				.setMarkerSize(8);
		
		if (xData[1] < 0) {
			chart.getStyler()
				.setXAxisMin(-f.length)
				.setXAxisMax(0d)
				.setYAxisMin(-f.length/2)
				.setYAxisMax(f.length/2);
		} else {
			chart.getStyler()
				.setXAxisMin(0d)
				.setXAxisMax(f.length)
				.setYAxisMin(-f.length/2)
				.setYAxisMax(f.length/2);
		}
			
		series.setMarker(SeriesMarkers.CIRCLE);

		
		// show the chart
		SwingWrapper<XYChart> sw = new SwingWrapper<XYChart>(chart);
		sw.displayChart("Animated Deflection Diagram");
		
		for (int i = 1; i < deflections.size(); i++) {
			// wait a second
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// generate render data for new point
			xData = generateRenderData(f, deflections.get(i))[0];
			yData = generateRenderData(f, deflections.get(i))[1];
			
			// update render data, redraw
			chart.updateXYSeries(
					"Deflection of flexible Object, w(x)", 
					xData, 
					yData, 
					null);
			sw.repaintChart();
		}
	}
	
	public static double[][] generateRenderData(FlexibleObject f, Point2D[] deflection){
		// corrected x axis values
		double[] xData = new double[f.deflectionRes+1];
		for (int i = 0; i < xData.length; i++) {
			xData[i] = deflection[i].getX();
		}
		
		// get the deflection values from the flexibleObject as graph y data
		double[] yData = new double[f.deflectionRes+1];
		for (int i = 0; i < yData.length; i++) {
			yData[i] = deflection[i].getY();
		}
		// cause it doesn't bend upwards *lennyface*
		double[] yDataInverted = DoubleStream.of(yData).map(x -> -x).toArray();
		
		return new double[][] {xData, yDataInverted};
	}
}
