package userInterface;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.style.markers.SeriesMarkers;

import javafx.geometry.Point3D;

public class TrajectoryDiagram {

	public static void draw(int resolution, Point3D[] trajPoints) {
		double[] xDataProj = new double[resolution+1];
		double[] yDataProj = new double[resolution+1];
		

		for (int j = 0; j < xDataProj.length; j++) {
			xDataProj[j] = trajPoints[j].getX();
		}
		

		for (int j = 0; j < yDataProj.length; j++) {
			yDataProj[j] = trajPoints[j].getY();
		}
		
		
//		// create chart and show it
		XYChart chart = new XYChartBuilder().width(600).height(400).title("Trajectory").xAxisTitle("t").yAxisTitle("y").build();
	
		chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Line);
		chart.getStyler().setChartTitleVisible(false);
		chart.getStyler().setMarkerSize(8);
		
		XYSeries series = chart.addSeries("P(t)", xDataProj, yDataProj);
		series.setMarker(SeriesMarkers.CIRCLE);
			
//		chart.getStyler().setXAxisMin(-0.04d);
//		chart.getStyler().setXAxisMax(0d);
//		chart.getStyler().setYAxisMax(0.04d);
		
		chart.getStyler().setLegendVisible(false);
		
		new SwingWrapper<XYChart>(chart).displayChart();
	}
	
	
}
