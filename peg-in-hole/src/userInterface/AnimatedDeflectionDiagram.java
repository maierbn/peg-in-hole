package userInterface;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.stream.DoubleStream;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.KeyStroke;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.style.AxesChartStyler;
import org.knowm.xchart.style.Styler.LegendPosition;
import org.knowm.xchart.style.markers.SeriesMarkers;

import core.FlexibleObject;
import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import javafx.geometry.Point3D;

public class AnimatedDeflectionDiagram {
	
	public static void draw(FlexibleObject f, 
							ArrayList<Point2D[]> deflections,
							Point3D[] trajectory, double slitSize) {
		// bounding boxes
		BoundingBox upperSlitBoundingBox = new BoundingBox(0, slitSize/2, 0, f.length);
		BoundingBox lowerSlitBoundingBox = new BoundingBox(0, -((slitSize/2)+f.length), 0, f.length);
		
		double[] drawUpperBoundingBoxX = new double[] {
				upperSlitBoundingBox.getMinX(),
				upperSlitBoundingBox.getMinX()				
				};
		
		double[] drawUpperBoundingBoxY = new double[] {
				upperSlitBoundingBox.getMaxY(),
				upperSlitBoundingBox.getMinY()
				};
		
		double[] drawLowerBoundingBoxX = new double[] {
				lowerSlitBoundingBox.getMinX(),
				lowerSlitBoundingBox.getMinX()
				};
		
		double[] drawLowerBoundingBoxY = new double[] {
				lowerSlitBoundingBox.getMaxY(),
				lowerSlitBoundingBox.getMinY()
				};	
		
		
		// initially fill render data with values at P0
		double[] xData = deflectionRenderData(f, deflections.get(0))[0];
		double[] yData = deflectionRenderData(f, deflections.get(0))[1];
		double[] xDataProj = trajectoryRenderData(trajectory)[0];
		double[] yDataProj = trajectoryRenderData(trajectory)[1];

		// create chart object
		XYChart chart = new XYChartBuilder()
				.width(1200)
				.height(1150)
				.title("Deflection")
				.xAxisTitle("x in m, real world")
				.yAxisTitle("x in m, real world")
				.build();
		XYSeries deflectionSeriesUpper = chart.addSeries(
				"Deflection of flexible Object, upper, w(x)", 
				xData, 
				yData);
		XYSeries deflectionSeriesLower = chart.addSeries(
				"Deflection of flexible Object, lower, w(x)", 
				xData, 
				yData);
		XYSeries trajectorySeries = chart.addSeries(
				"Trajectory of the arm, B(t)", 
				xDataProj, 
				yDataProj);
		XYSeries upperBoxSeries = chart.addSeries(
				"upper bounding box", 
				drawUpperBoundingBoxX,
				drawUpperBoundingBoxY);
		XYSeries lowerBoxSeries = chart.addSeries(
				"lower bounding box", 
				drawLowerBoundingBoxX,
				drawLowerBoundingBoxY);

		
		((AxesChartStyler) chart.getStyler()
				.setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Line)
				.setChartTitleVisible(false)
//				.setToolTipsEnabled(true)	// comment this one out to remove labels
				.setToolTipsAlwaysVisible(true)
				.setLegendVisible(true)
				.setLegendPosition(LegendPosition.InsideNE))
				.setMarkerSize(8);
		
		chart.getStyler()
			.setXAxisMin(-f.length)
			.setXAxisMax(0d)
			.setYAxisMin(-f.length/2)
			.setYAxisMax(f.length/2);
		
		trajectorySeries
			.setMarker(SeriesMarkers.CIRCLE)
			.setMarkerColor(Color.ORANGE)
			.setLineColor(Color.ORANGE);
		deflectionSeriesUpper
			.setMarker(SeriesMarkers.NONE)
			.setLineColor(Color.BLUE);	
		deflectionSeriesLower
			.setMarker(SeriesMarkers.NONE)
			.setLineColor(Color.BLUE);
		upperBoxSeries
			.setMarker(SeriesMarkers.NONE)
			.setLineColor(Color.MAGENTA);
		lowerBoxSeries
			.setMarker(SeriesMarkers.NONE)
			.setLineColor(Color.MAGENTA);

		// show the chart
		SwingWrapper<XYChart> sw = new SwingWrapper<XYChart>(chart);
		sw.displayChart("Animated Deflection Diagram");
		
		/**
		 * "This is so dirty, I want to take a shower now, just because I read it"
		 */
		final int[] counter = new int[] {0};
		
		String actionKeyL = "L";
		Action onLeftArrow = new AbstractAction(actionKeyL) {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {

				counter[0] = (counter[0]<=0) ? counter[0] : counter[0]-1;

				double[] xData = deflectionRenderData(f, deflections.get(counter[0]))[0];
				double[] yDataUpper = deflectionRenderData(f, deflections.get(counter[0]))[1];
				double[] yDataLower = deflectionRenderData(f, deflections.get(counter[0]))[2];

				// update render data, redraw
				chart.updateXYSeries(
						"Deflection of flexible Object, upper, w(x)", 
						xData, 
						yDataUpper, 
						null);
				chart.updateXYSeries(
						"Deflection of flexible Object, lower, w(x)", 
						xData, 
						yDataLower, 
						null);
				sw.repaintChart();
				
			}
		};
		
		String actionKeyR = "R";
		Action onRightArrow = new AbstractAction(actionKeyR) {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				counter[0] = (counter[0]<deflections.size()-1) ? counter[0]+1 : counter[0];
				
				double[] xData = deflectionRenderData(f, deflections.get(counter[0]))[0];
				double[] yDataUpper = deflectionRenderData(f, deflections.get(counter[0]))[1];
				double[] yDataLower = deflectionRenderData(f, deflections.get(counter[0]))[2];

				// update render data, redraw
				chart.updateXYSeries(
						"Deflection of flexible Object, upper, w(x)", 
						xData, 
						yDataUpper, 
						null);
				chart.updateXYSeries(
						"Deflection of flexible Object, lower, w(x)", 
						xData, 
						yDataLower, 
						null);
				sw.repaintChart();
			}
		};
		
		InputMap keypressListener = sw.getXChartPanel().getInputMap();
		keypressListener.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), actionKeyL);
		keypressListener.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), actionKeyR);
		
		ActionMap actionToKeypressMapper = sw.getXChartPanel().getActionMap();
		actionToKeypressMapper.put(actionKeyL, onLeftArrow);
		actionToKeypressMapper.put(actionKeyR, onRightArrow);
		
//		for (int i = 1; i < deflections.size(); i++) {
//			// wait a second
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			// generate render data for new point
//			xData = deflectionRenderData(f, deflections.get(i))[0];
//			yData = deflectionRenderData(f, deflections.get(i))[1];
//			
//			// update render data, redraw
//			chart.updateXYSeries(
//					"Deflection of flexible Object, w(x)", 
//					xData, 
//					yData, 
//					null);
//			sw.repaintChart();
//		}
	}
	
	public static double[][] deflectionRenderData(FlexibleObject f, Point2D[] deflection){
		// corrected x axis values
		double[] xData = new double[f.deflectionRes+1];
		for (int i = 0; i < xData.length; i++) {
			xData[i] = deflection[i].getX();
		}
		
		// get the deflection values from the flexibleObject as graph y data
		double[] yDataUpper = new double[f.deflectionRes+1];
		for (int i = 0; i < yDataUpper.length; i++) {
			yDataUpper[i] = deflection[i].getY()+f.thickness/2f;
		}
		double[] yDataLower = new double[f.deflectionRes+1];
		for (int i = 0; i < yDataLower.length; i++) {
			yDataLower[i] = deflection[i].getY()-f.thickness/2f;
		}
		// cause it doesn't bend upwards *lennyface*
		double[] yDataInvertedUpper = DoubleStream.of(yDataUpper).map(x -> -x).toArray();
		double[] yDataInvertedLower = DoubleStream.of(yDataLower).map(x -> -x).toArray();

		return new double[][] {xData, yDataInvertedUpper, yDataInvertedLower};
	}
	
	public static double[][] trajectoryRenderData(Point3D[] trajectory){
		
		double[] xDataProj = new double[trajectory.length];
		double[] yDataProj = new double[trajectory.length];
		
		for (int i = 0; i < trajectory.length; i++) {
			xDataProj[i] = trajectory[i].getX();
			yDataProj[i] = trajectory[i].getY();
		}
		
		return new double[][] {xDataProj, yDataProj};
	}
}