package core;

import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import userInterface.TrajectoryDiagram;

public class Simulation {
	public FlexibleObject f;

	public int deflectionRes;
	public ArrayList<Point2D[]> deflections = new ArrayList<Point2D[]>();

	public int trajectoryRes;
	public Point3D[] trajectory;

	public Point3D cp;
	public double slitSize;

	public Simulation(FlexibleObject f, Point3D cp, int trajectoryRes, double slitSize) {
		this.f = f;
		this.deflectionRes = f.deflectionRes;
		this.trajectoryRes = trajectoryRes;
		this.cp = cp;
		this.slitSize=slitSize;
		
		deflections.add(f.deflectionP0);
	}

	public void calcTrajectory() {
		trajectory = new Point3D[trajectoryRes + 1];
		Point2D[] deflectionP0 = deflections.get(0);
		// the angle in P0 is getting approximated by calculating the angle between the
		// x-axis and the vector between the first two points of the initial deflection
		Point2D vectorP0toPnext = deflectionP0[1].subtract(deflectionP0[0]);
		double angleP0 = vectorP0toPnext.angle(1d, 0d);
		
		/*System.out.println("angle vector =\t" + angleP0);
		double q = f.width * f.thickness * f.density * Constants.gravitationalAcceleration;
		System.out.println("q = " + q);
		angleP0 = Math.atan(q);
		System.out.println("angle formula =\t" + Math.toDegrees(angleP0));*/

		// p0-y is negative since gravitation generally does not bend stuff upwards
		Point3D p0 = new Point3D(deflectionP0[0].getX(), -deflectionP0[0].getY(), angleP0);
		Point3D p1 = new Point3D(0, 0, 0);

		double stepSize = 1d / trajectoryRes;
		double t = 0;
		// t between 0, 1
		for (int i = 0; i <= trajectoryRes; i++) {
			trajectory[i] = Formulas.bigB(p0, cp, p1, t);
			t += stepSize;
		}
		
		//Log.print("Trajectory Points:\n\t" + Arrays.toString(trajectory));
	}

	public void drawTrajectory() {
		TrajectoryDiagram.draw(trajectoryRes, trajectory, cp);
	}

	public void calcDeflectionsWithTrajectory() {
		for (int i = 1; i < trajectory.length; i++) {
			Point3D point = trajectory[i];
			deflections.add(calcDeflectionValuesPi(point));
		}
	}

	/**
	 * calculate deflections[res/i]
	 * 
	 * @param i
	 * @return
	 */
	private Point2D[] calcDeflectionValuesPi(Point3D trajectoryPoint) {
		return Formulas.deflectionsPi(f, trajectoryPoint);
	}

	/**
	 * calculate the smallest distance to the hole. (if it collides with it -->
	 * return negative value)
	 * 
	 * @return
	 */
	public double calcClearance() {
		double minimalClearance = Double.MAX_VALUE;
		BoundingBox upperSlitBoundingBox = new BoundingBox(0, this.slitSize/2, 0, f.length);
		BoundingBox lowerSlitBoundingBox = new BoundingBox(0, -((this.slitSize/2)+f.length), 0, f.length);
		
		/**
		 * we use .sublist(1, .size()) to remove the first (read: initial) deflection
		 * it would skew the min distance
		 */
		int i = 1;
		for (Point2D[] deflectionArray : deflections.subList(1, deflections.size())) {
			
			int j;
			for (j = 0; j < deflectionArray.length; j++) {
				if (deflectionArray[j].getX() > 0) {
					break;
				}
			}
			
			Point2D pointRightFromHole = deflectionArray[j];
			Point2D pointLeftFromHole = deflectionArray[j-1];
			
			/**
			 * creates a bounding box between a deflection point left from the y axis and one to the right.
			 * then the bounding box gets rotated accordingly.
			 */
			double width = pointRightFromHole.distance(pointLeftFromHole);
			BoundingBox box = new BoundingBox(pointLeftFromHole.getX(), pointLeftFromHole.getY()-f.thickness/2, width, f.thickness);
			double angle = pointRightFromHole.angle(pointLeftFromHole);
			Rotate rot = new Rotate(angle, pointLeftFromHole.getX(), pointLeftFromHole.getY());
			
			rot.transform(box);
			
			// box has been placed in the slit and rotated accordingly 
			
			if (box.intersects(upperSlitBoundingBox) || box.intersects(lowerSlitBoundingBox)) {
				System.out.println("Collision occured in step " + i);
				return -1;
			}
			
			// if this gets executed, no collision has been found, we compute the current clearance to the slit edges
			
			double upperClearance = Line2D.ptLineDist(pointLeftFromHole.getX(), 
														pointLeftFromHole.getY(), 
														pointRightFromHole.getX(),
														pointRightFromHole.getY(), 
														0, 
														this.slitSize/2) - f.thickness / 2;
			double lowerClearance = Line2D.ptLineDist(pointLeftFromHole.getX(), 
														pointLeftFromHole.getY(), 
														pointRightFromHole.getX(),
														pointRightFromHole.getY(), 
														0, 
														-this.slitSize/2) - f.thickness / 2;
			double clearance = Math.min(upperClearance, lowerClearance);
			
			if (clearance < minimalClearance) {
				minimalClearance = clearance;
			}
			
			i++;
		}
		return minimalClearance;
	}
}
