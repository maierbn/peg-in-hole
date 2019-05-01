package examples;

//Copyright (C) 2008-today The SG++ project
//This file is part of the SG++ project. For conditions of distribution and
//use, please see the copyright notice provided with SG++ or at
//sgpp.sparsegrids.org

public class ExampleFunction extends sgpp.OptScalarFunction {
	public ExampleFunction() {
		super(2);
	}

	public double eval(sgpp.DataVector x) {
		if ((x.get(0) >= 0.0) && (x.get(0) <= 1.0) && (x.get(1) >= 0.0) && (x.get(1) <= 1.0)) {
			// minimum is f(x) = -2 for x[0] = 3*pi/16, x[1] = 3*pi/14
			return Math.sin(8.0 * x.get(0)) + Math.sin(7.0 * x.get(1));
		} else {
			return Double.POSITIVE_INFINITY;
		}
	}

	public void clone(sgpp.SWIGTYPE_p_std__unique_ptrT_sgpp__optimization__ScalarFunction_t clone) {
	}
}