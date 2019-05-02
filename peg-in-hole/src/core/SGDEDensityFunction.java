package core;

import sgpp.SparseGridDensityEstimator;

public class SGDEDensityFunction extends sgpp.OptScalarFunction {
	
	SparseGridDensityEstimator sgde;

	public SGDEDensityFunction() {
		/**
		 * Dimensionality of the domain
		 * 
		 * We have 3-dim control points and 5-dim featue vectors 
		 * which makes for an 8-dim input?
		 */
		super(8);
	}
	
	/**
	 * sets the estimator, since it's not possible to reference it in constructor
	 * 
	 * @param sgde	DensityEstimator/Learner to use
	 */
	public void setSGDE(SparseGridDensityEstimator sgde) {
		this.sgde = sgde;
	}
	
	/**
	 * evaluates the density function of estimator/learner at position x
	 */
	public double eval(sgpp.DataVector x) {
		return sgde.pdf(x);
	}

	public void clone(sgpp.SWIGTYPE_p_std__unique_ptrT_sgpp__optimization__ScalarFunction_t clone) {
	}
}
