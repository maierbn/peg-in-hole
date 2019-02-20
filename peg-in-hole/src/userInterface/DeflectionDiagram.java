package userInterface;

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

		/**
		 * width-height proportions of the window are chosen as workaround for 1:1 axis scaling
		 * change with caution
		 */
		XYChart chart = new XYChartBuilder()
				.width(1200)
				.height(1150)
				.title("Deflection")
				.xAxisTitle("x in m, real world")
				.yAxisTitle("x in m, real world")
				.build();
		XYSeries series = chart.addSeries("Deflection of flexible Object, w(x)", xData, yDataInverted);
		
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
		
		new SwingWrapper<XYChart>(chart).displayChart();
	}
}