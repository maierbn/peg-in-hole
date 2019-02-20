package userInterface;

import java.awt.Color;
import org.knowm.xchart.*;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.internal.series.MarkerSeries;
import org.knowm.xchart.style.AxesChartStyler;
import org.knowm.xchart.style.Styler.LegendPosition;
import org.knowm.xchart.style.lines.SeriesLines;
import org.knowm.xchart.style.markers.SeriesMarkers;
import javafx.geometry.*;
import javafx.scene.transform.Rotate;

public class TrajectoryDiagram {

	public static void draw(int resolution, Point3D[] trajectory) {
		
		/**
		 * zDataProj contains the coordinates of the *endpoint* of the line
		 * representing the rotation. starting point is taken from x/yDataProj[i],
		 */
		double[] xDataProj = new double[resolution+1];
		double[] yDataProj = new double[resolution+1];
		Point2D[] zDataProj = new Point2D[resolution+1];
		String[] zDataAngle = new String[resolution+1];
	
		for (int i = 0; i < xDataProj.length; i++) {
			xDataProj[i] = trajectory[i].getX();
			yDataProj[i] = trajectory[i].getY();
			zDataAngle[i] = String.format("%.2f", trajectory[i].getZ());
			/**
			 * get the current point at (xDataProj[i],yDataProj[i])
			 * conjure up a very small vector, e.g. (0.002d,0d)
			 * feed it to the `.transform`ation as argument
			 * which `Rotate`s it by `trajectory[i].getZ()`
			 * `.add` the rotated vector to your current point
			 * 
			 * keep in mind, javafx rotates in degrees, not radians
			 */
			zDataProj[i] = new Point2D(xDataProj[i],yDataProj[i]).add(new Rotate(trajectory[i].getZ(),0,0).transform(0.002d,0d));
		}

		/**
		 * width-height proportions of the window are chosen as workaround for 1:1 axis scaling
		 * change with caution
		 */
		XYChart chart = new XYChartBuilder()
				.width(1200)
				.height(1150)
				.title("Trajectory")
				.xAxisTitle("x in m, real world")
				.yAxisTitle("y in m, real world")
				.build();
		XYSeries series = chart.addSeries("Trajectory of the arm, B(t)", xDataProj, yDataProj);
		
		/**
		 * quick and dirty way to draw multiple lines is to create multiple series
		 * creating tuples (represented as arrays) of line start and end, for each line
		 * for x and y separately
		 */
		for (int i = 0; i < zDataProj.length; i++) {
			((MarkerSeries) chart.addSeries(
					"Rotation at given p"+i+"int",
					new double[] {xDataProj[i],zDataProj[i].getX()},
					new double[] {yDataProj[i],zDataProj[i].getY()})
				.setShowInLegend((i==0)? true : false))
				.setMarker(SeriesMarkers.NONE)
				.setLineColor(Color.RED)
				.setLineStyle(SeriesLines.SOLID)
				.setToolTips(new String[] {null,null})
				;		
		}

		// rip java styleguide, method chaining ftw
		((AxesChartStyler) chart.getStyler()
			.setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Line)
			.setChartTitleVisible(false)
			.setToolTipsEnabled(true)	// comment this one out to remove labels
			.setToolTipsAlwaysVisible(true)
			.setLegendVisible(true)
			.setLegendPosition(LegendPosition.InsideNE))
			.setMarkerSize(8)
			// axis settings
			.setXAxisMin(xDataProj[0])
			.setXAxisMax(0d)
			.setYAxisMin(xDataProj[0]/2)
			.setYAxisMax(-xDataProj[0]/2)
			;
		
		series.setMarker(SeriesMarkers.CIRCLE)
			.setToolTips(zDataAngle);
		
		new SwingWrapper<XYChart>(chart).displayChart();
	}
}
