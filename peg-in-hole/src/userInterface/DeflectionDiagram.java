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
import javafx.geometry.Point2D;

public class DeflectionDiagram {
	public static void draw(FlexibleObject f, Point2D[] deflection) {
		Log.print("Drawing deflection-diagram..");
		
		
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

		// create chart and show it
		XYChart chart = new XYChartBuilder().width(1200).height(1100).title("Deflection").xAxisTitle("X in m").yAxisTitle("Y in m").build();
		
		chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Line);
		chart.getStyler().setChartTitleVisible(false);
		chart.getStyler().setMarkerSize(8);
		
		XYSeries series = chart.addSeries("y(x)", xData, yDataInverted);
		series.setMarker(SeriesMarkers.CIRCLE);
		
		// if displaced to zero, use - length & 0
//		chart.getStyler().setChartPadding(100);
//		chart.getStyler().setPlotContentSize(0.2);
		
		if (xData[1] < 0) {
			chart.getStyler().setXAxisMin(-f.length);
			chart.getStyler().setXAxisMax(0d);
			chart.getStyler().setYAxisMin(-f.length/2);
			chart.getStyler().setYAxisMax(f.length/2);
		} else {
			chart.getStyler().setXAxisMin(0d);
			chart.getStyler().setXAxisMax(f.length);
			chart.getStyler().setYAxisMin(-f.length/2);
			chart.getStyler().setYAxisMax(f.length/2);
		}
			
		chart.getStyler().setLegendVisible(false);
		
		new SwingWrapper<XYChart>(chart).displayChart();
	}
}
