package core;

import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import userInterface.TrajectoryDiagram;

public class Simulation {
	public FlexibleObject f;

	public int deflectionRes;
	public ArrayList<Point2D[]> deflections = new ArrayList<Point2D[]>();

	public int trajectoryRes;
	public Point3D[] trajectory;

	public Point3D cp;

	public Simulation(FlexibleObject f, Point3D cp, int trajectoryRes) {
		this.f = f;
		this.deflectionRes = f.deflectionRes;
		this.trajectoryRes = trajectoryRes;
		this.cp = cp;
		
		deflections.add(f.deflectionP0);
	}

	public void calcTrajectory() {
		trajectory = new Point3D[trajectoryRes + 1];
		Point2D[] deflectionP0 = deflections.get(0);
		// the angle in P0 is getting approximated by calculating the angle between the
		// x-axis and the vector between the first two points of the initial deflection
		Point2D vectorP0toPnext = deflectionP0[1].subtract(deflectionP0[0]);
		double angleP0 = vectorP0toPnext.angle(1d, 0d);

		// p0-y is negative since gravitation generally does not bend stuff upwards
		Point3D p0 = new Point3D(deflectionP0[0].getX(), -deflectionP0[0].getY(), angleP0);
		Point3D p1 = new Point3D(0, 0, 0);

		int i = 0;
		double stepSize = 1 / (double) trajectoryRes;
		// t between 0, 1
		for (double t = 0; t <= 1; t += stepSize) {
			trajectory[i] = Formulas.bigB(p0, cp, p1, t);
			i++;
		}
		//Log.print("Trajectory Points:\n\t" + Arrays.toString(trajectory));
	}

	public void drawTrajectory() {
		TrajectoryDiagram.draw(trajectoryRes, trajectory);
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
	public double calcSmallestDistanceToHole() {
		// TODO
		return 0;
	}

}
