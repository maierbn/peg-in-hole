package userInterface;

import java.awt.Color;
import java.awt.Font;
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
				.yAxisTitle("y in m, real world")
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
		
		// pretty pictures for paper
		series.setLineWidth(50);
		chart.getStyler().setLegendFont(new Font(Font.SANS_SERIF, Font.PLAIN, 50));
		chart.getStyler().setLegendSeriesLineLength(0);
		chart.getStyler().setLegendBorderColor(Color.WHITE);
		chart.getStyler().setLegendPadding(100);
		chart.getStyler().setAxisTitleFont(new Font(Font.SANS_SERIF, Font.ITALIC, 25));
		chart.getStyler().setAxisTickLabelsFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
		chart.getStyler().setYAxisTickMarkSpacingHint(100);
		
		if (xData[1] < 0) {
			chart.getStyler()
				.setXAxisMin(-f.length)
				.setXAxisMax(0d)
				.setYAxisMin(-f.length/16)
				.setYAxisMax(f.length/16);
		} else {
			chart.getStyler()
				.setXAxisMin(0d)
				.setXAxisMax(f.length)
				.setYAxisMin(-f.length/16)
				.setYAxisMax(f.length/16);
		}
			
		series.setMarker(SeriesMarkers.NONE);
		
		new SwingWrapper<XYChart>(chart).displayChart("Deflection Diagram");
	}
}