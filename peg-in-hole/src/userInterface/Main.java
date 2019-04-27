package userInterface;

import core.FlexibleObject;
import core.Formulas;
import core.Simulation;
import javafx.geometry.Point3D;
import sgpp.*;

public class Main {
	public static void main(String[] args) {
		double length = 0.040; // meter
		double width = 0.015; // meter
		double thickness = 0.002; // meter
		double density = 1150;
		double youngsModulus = 3.6E6;

		/**
		 * for how many x-values do we want to calculate the deflection
		 * 
		 * also reflects the amount of rigid blocks for collision detection (this - 1)
		 * as well as amount of joints (this - 1)
		 */
		int deflectionRes = 30;
		int trajectoryRes = 30;
		double slitSize = 0.005;

		FlexibleObject f = new FlexibleObject(length, width, thickness, density, youngsModulus, deflectionRes);

		// CONTROL POINTS
		Point3D[] generatedCPs = Formulas.generateCPs(f, 5);
//		Point3D cp = new Point3D(-0.03, 0.01, -15.0);
		
		// SIMULATION RUN
		Point3D cp = generatedCPs[(int) (Math.random()*generatedCPs.length)];
//		Simulation testSim = new Simulation(f, cp, trajectoryRes, slitSize);
//		testSim.calcTrajectory();
////		testSim.drawTrajectory();
//		testSim.calcDeflectionsWithTrajectory();
//		double clearance = testSim.calcClearance();
//		System.out.println("Resulting clearance is " + clearance);
		
		// SG++ TESTING
		sgpp.LoadJSGPPLib.loadJSGPPLib();
//		System.out.println("JSGPP library loaded");
		
		/**
		 * fill our control points into a sgpp-kde-compatible structure
		 * which is a matrix of 8 columns and #cp rows
		 * 
		 * columns are cpX, cpY, cpZ, fv1, fv2, fv3, fv4, fv5, clearance
		 * each row represents a cp-featurevector pair
		 * 
		 * TODO: this deserves its own function in formulas class
		 */
//		DataMatrix samples = new DataMatrix(0, 9);
//		for (Point3D point3d : generatedCPs) {
//			DataVector row = new DataVector();
//			
//			// simulation with cp and fv
//			Simulation sim = new Simulation(f, point3d, trajectoryRes, slitSize);
//			sim.calcTrajectory();
//			sim.calcDeflectionsWithTrajectory();
//			double clearance = sim.calcClearance();
//			
//			// write data to a vector
//			row.append(point3d.getX());
//			row.append(point3d.getY());
//			row.append(point3d.getZ());
//			row.append(f.featureVector[0]);
//			row.append(f.featureVector[1]);
//			row.append(f.featureVector[2]);
//			row.append(f.featureVector[3]);
//			row.append(f.featureVector[4]);
//			row.append(clearance);
//			
//			// append row vector to matrix
//			samples.appendRow(row);
//		}
//		
//		samples.toFile("testControlPoints.mat");
		
		DataMatrix samplesFromFile = DataMatrix.fromFile("results_all.mat");

	    // grid configuration
	    RegularGridConfiguration gridConfig = new RegularGridConfiguration();
	    gridConfig.setDim_(samplesFromFile.getNcols());		// sets dimensions, conveniently from sample. probably too big to compute.
	    gridConfig.setLevel_(4);							// discretization resolution ???
	    gridConfig.setType_(GridType.Linear);				// TODO: BSpline as per project description
		
	    // adaptive refinement 
	    AdaptivityConfiguration adaptConfig = new AdaptivityConfiguration();
	    adaptConfig.setNoPoints_(5);						// max. number of points to be refined (?)
	    adaptConfig.setNumRefinements_(0);					// number of refinements (?)
	    
	    // solver
	    SLESolverConfiguration solverConfig = new SLESolverConfiguration();
	    solverConfig.setType_(SLESolverType.CG);			// CG, BiCGSTAB or FISTA (?)
	    solverConfig.setMaxIterations_(100);				// (?)
	    solverConfig.setEps_(1e-10);						// (?)
	    solverConfig.setThreshold_(1e-10);					// (?)
	    
	    // regularization
	    RegularizationConfiguration regularizationConfig = new RegularizationConfiguration();
	    regularizationConfig.setType_(RegularizationType.Laplace);	// Identity, Laplace, Diagonal, Lasso, ElasticNet, GroupLasso
		
		// learner - LearnerSGDEConfiguration _seems_ deprecated
	    // superseded by crossvalidationConfigutation?  
	    CrossvalidationForRegularizationConfiguration learnerConfig = new CrossvalidationForRegularizationConfiguration();
	    learnerConfig.setEnable_(false);
	    learnerConfig.setKfold_(5);
	    learnerConfig.setLambdaStart_(1e-1);
	    learnerConfig.setLambdaEnd_(1e-10);
	    learnerConfig.setLambdaSteps_(5);
	    learnerConfig.setLogScale_(true);
	    learnerConfig.setShuffle_(true);
	    learnerConfig.setSeed_(1234567);
	    learnerConfig.setSilent_(false);
	    
	    // init and run
	    /**
	     * the following looks like a bug in the java bindings. elaboration:
	     * 
	     * 		In the process of wrapping a native library in C++ to Java, 
	     * 		I've come across the SWIGTYPE_p_ classes. Reading around the SWIG docs, 
	     * 		and following the answer from here I more or less understand that SWIG 
	     * 		generates these classes when it doesn't know what to do with a C++ data
	     * 		type. It tries to create a pointer to the data type, but these SWIGTYPE
	     * 		classes seem to be functionally useless.
	     * 
	     * the constructor expects a swigtype_p =(
	     */
//	    LearnerSGDE learner = new LearnerSGDE(gridConfig, adaptConfig,solverConfig,regularizationConfig, (SWIGTYPE_p_CrossvalidationConfiguration) learnerConfig);
	    
	    // looks like the following constructor reads the config from a json.
//	    LearnerSGDE learner = new LearnerSGDE(new LearnerSGDEConfiguration("filename"));
//	    learner.initialize(samplesFromFile);
		
	    // OR
	    SGDEConfiguration sgdeconfig = new SGDEConfiguration();		// what to configure?
	    
	    SparseGridDensityEstimator sgde = new SparseGridDensityEstimator(
	    		gridConfig, 
	    		adaptConfig, 
	    		solverConfig, 
	    		regularizationConfig, 
	    		learnerConfig, 
	    		sgdeconfig);
	    
		sgde.initialize(samplesFromFile);
		
		DataVector test = new DataVector(9);
		samplesFromFile.getRow(0, test);
		System.out.println("SGDE: PDF at #th sample: " + sgde.pdf(test));
		
		
		// DIAGRAMS
//		DeflectionDiagram.draw(f, testSim.deflections.get(testSim.deflections.size() - 1));
//		f.drawDeflectionP0();
//		DeflectionDiagram.draw(f, testSim.deflections.get(1));
//		DeflectionDiagram.draw(f, testSim.deflections.get(2));
//		AnimatedDeflectionDiagram.draw(f, testSim.deflections, testSim.trajectory, testSim.slitSize);
	}
}
