package core;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.math.BigDecimal;

import javafx.geometry.Point2D;
import javafx.geometry.Point3D;

/**
 * This class calculates and holds the controlpoints for a FlexibleObject
 *
 */
public class ControlPoints {
	public Point3D[] cps;
	public double[] clearance;
	public int amount;
	
	FlexibleObject f;
	int granularity;
	
	public ControlPoints(FlexibleObject f, int granularity){
		amount = (int) Math.pow(granularity,3);
		
		cps = new Point3D[amount];
		clearance = new double[amount];
		
		this.f = f;
		this.granularity = granularity;
	}
	
	/**
	 * generates possible control points in respect to the object in initial position
	 * x axis is sampled every 1/complexity of the object length
	 * y axis is sampled every 1/complexity of the object length
	 * angle is sampled every 1/complexity between initial angle and zero
	 * 
	 * all values are real-life meters / degrees
	 * 
	 * @param f				the flexible object
	 * @param granularity	amount of samples per dimension, including edge cases
	 * @return 
	 * @return				an array of potential control points
	 */
	public void generateCPs() {	
		double stepSizeXY = f.length/(double)(granularity-1);
		
		Point2D vectorP0toPnext = f.deflectionP0[1].subtract(f.deflectionP0[0]);
		double angleP0 = vectorP0toPnext.angle(1d, 0d);
		double stepSizeAngle = angleP0/(double)(granularity-1);
		
		int i = 0;
		double x = f.deflectionP0[0].getX();
		for (int j = 0; j < granularity; j++) {
			
			double y = f.deflectionP0[0].getY();
			for (int k = 0; k < granularity; k++) {
				
				double z = angleP0;
				for (int l = 0; l < granularity; l++) {
					cps[i] = new Point3D(x, y, z);
					z-=stepSizeAngle;
					i++;
				}
				y+=stepSizeXY;
			}
			x+=stepSizeXY;
		}
	}
	
	/**
	 * Start a simulation for all ControlPoints and save the minimal clearance
	 * @param trajRes
	 * @param slitSize
	 * @param dimension
	 */
	public void simulateAllCPs(int trajRes,
			double slitSize) {
		
		for (int i = 0; i < cps.length; i++) {
			Point3D cp = cps[i];
			// simulate CP and FV pair
			Simulation sim = new Simulation(f, cp, trajRes, slitSize);
			sim.calcTrajectory();
			sim.calcDeflectionsWithTrajectory();
			clearance[i] = sim.calcClearance();
		}
	}
	
	/**
	 * creates sgpp-readable simulation samples for
	 * controlpoint-featurevector pairs
	 * clearance is negated for later optimization procedure
	 * 
	 * @param dimension			datamatrix columns, default = 8 because first feature vector component is always zero
	 * @param fileName			file name to save the data with
	 * @param successfulOnly	discards results with collisions
	 */
	public void writeARFF(
			long dimension, 
			String fileName,
			Boolean successfulOnly) {
		
		int countWritten = 0;
		try (FileWriter fstream = new FileWriter(fileName);
				BufferedWriter info = new BufferedWriter(fstream)) {
			
			// write header
			info.write("@RELATION \"peg_in_hole_clearance\"\n");
			info.write("\n");
			info.write("@ATTRIBUTE cp0 NUMERIC\n");
			info.write("@ATTRIBUTE cp1 NUMERIC\n");
			info.write("@ATTRIBUTE cp2 NUMERIC\n");
			//don't write fv0 because its always 0
			info.write("@ATTRIBUTE fv1 NUMERIC\n");
			info.write("@ATTRIBUTE fv2 NUMERIC\n");
			info.write("@ATTRIBUTE fv3 NUMERIC\n");
			info.write("@ATTRIBUTE fv4 NUMERIC\n");
			info.write("@ATTRIBUTE clearance NUMERIC\n");
			info.write("\n");
			info.write("@DATA\n");

			for (int i = 0; i < cps.length; i++) {
				Point3D cp = cps[i];
				
				// discard failed simulations if successfulOnly is set
				if (successfulOnly && clearance[i] <0) {
					continue;
				}
				
				int scale = 12;
				info.write(BigDecimal.valueOf(Math.abs(cp.getX())*10).setScale(scale, BigDecimal.ROUND_HALF_UP)+",");
				info.write(BigDecimal.valueOf(cp.getY()*10).setScale(scale, BigDecimal.ROUND_HALF_UP)+",");
				info.write(BigDecimal.valueOf(cp.getZ()/20).setScale(scale, BigDecimal.ROUND_HALF_UP)+",");
				//don't write fv0 because its always 0
				info.write(BigDecimal.valueOf(f.featureVector[1]).setScale(scale, BigDecimal.ROUND_HALF_UP)+",");
				info.write(BigDecimal.valueOf(f.featureVector[2]).setScale(scale, BigDecimal.ROUND_HALF_UP)+",");
				info.write(BigDecimal.valueOf(f.featureVector[3]).setScale(scale, BigDecimal.ROUND_HALF_UP)+",");
				info.write(BigDecimal.valueOf(f.featureVector[4]).setScale(scale, BigDecimal.ROUND_HALF_UP)+",");
				info.write(BigDecimal.valueOf(clearance[i]*1000).setScale(scale, BigDecimal.ROUND_HALF_UP)+"\n");
				
				countWritten+=1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Written a total of " + countWritten + " simulation results to " + fileName);		
	}
}
