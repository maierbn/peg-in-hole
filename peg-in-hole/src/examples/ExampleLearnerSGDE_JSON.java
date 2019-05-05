package examples;

//Copyright (C) 2008-today The SG++ project
//This file is part of the SG++ project. For conditions of distribution and
//use, please see the copyright notice provided with SG++ or at
//sgpp.sparsegrids.org

/**
* \page example_example_learnerSGDE_java Learner SGDE
* This tutorial demostrates the sparse grid density estimation 
*/

//import all packages
import sgpp.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Simple Java SG++ example for the SGDE learner
 */
public class ExampleLearnerSGDE_JSON {

	public static DataMatrix readARFF(String fileNameDefined) {
		/**
		 * -File class needed to turn stringName to actual file
		 */
		File file = new File(fileNameDefined);
		DataMatrix ans = new DataMatrix(0, 0);
		try {
			/**
			 * -read from filePooped with Scanner class
			 */
			Scanner inputStream = new Scanner(file);

			for (int i = 0; i < 18; i++) {
				inputStream.next();
			}

			if (inputStream.hasNext()) {
				String[] data = inputStream.next().split(",");
				ans.resize(1, data.length);
				for (int i = 0; i < data.length; i++) {
					ans.set(0, i, Double.parseDouble(data[i]));
				}
			}

			/**
			 * hashNext() loops line-by-line
			 */
			while (inputStream.hasNext()) {
				/**
				 * read single line, put in string
				 */
				String[] data = inputStream.next().split(",");
				long row = ans.appendRow();
				for (int i = 0; i < data.length; i++) {
					ans.set(row, i, Double.parseDouble(data[i]));
				}
			}
			/**
			 * after loop, close scanner
			 */
			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return ans;
	}

	/**
	 * Main method.
	 *
	 * @param args ignored
	 */
	public static void main(String[] args) {
		// Two working possiblities for loading the shared object file:
		// java.lang.System.load("/path/to/SGpp/trunk/lib/jsgpp/libjsgpp.so");
		sgpp.LoadJSGPPLib.loadJSGPPLib();

		String filename = "./data/friedman2_4d_500.arff";

		System.out.println("# loading file: " + filename);
		DataMatrix samples = readARFF(filename);

		/**
		 * Initialize and run learner
		 */
		System.out.println("# creating the learner");
		LearnerSGDE learner = new LearnerSGDE(new LearnerSGDEConfiguration("learnerConfig.json"));
		learner.initialize(samples);
		learner.train(); // THIS IS MISSING, COMPARED TO C++ EXAMPLE
		
		/**
		 * For comparison, run the sparse grid kernel-based learner
		 */
		KernelDensityEstimator kde = new KernelDensityEstimator(samples);
		DataVector x = new DataVector(4);

		/**
		 * View resulting pdf in the middle of the domain
		 */
//		for (int i = 0; i < x.getSize(); i++) {
//			x.set(i, 0.5);
//		}
		
//		0.116395189855,0.21581511168,0.487417585411,0.186104900549,169.433061281
		x.append(0.116395189855);
		x.append(0.21581511168);
		x.append(0.487417585411);
		x.append(0.186104900549);
		
		System.out.println("--------------------------------------------------------");
		System.out.println(learner.getGrid().getSize() + " -> "); // + learner.getAlpha().sum());	THIS DOES NOT EXITS IN JAVA BINDINGS
		System.out.println("pdf_SGDE(x) = " + learner.pdf(x) + " ~ " + kde.pdf(x) + " = pdf_KDE(x)");
		System.out.println("mean_SGDE(x) = " + learner.mean() + " ~ " + kde.mean() + " = mean_KDE(x)");
		System.out.println("var_SGDE(x) = " + learner.variance() + " ~ " + kde.variance() + " = var_KDE(x)");
	}
}
