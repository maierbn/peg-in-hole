// Copyright (C) 2008-today The SG++ project
// This file is part of the SG++ project. For conditions of distribution and
// use, please see the copyright notice provided with SG++ or at
// sgpp.sparsegrids.org

/**
 * \page example_optimization_cpp optimization.cpp
 *
 * On this page, we look at an example application of the sgpp::optimization module.
 * Versions of the example are given in all languages
 * currently supported by SG++: C++, Python, Java, and MATLAB.
 *
 * The example interpolates a bivariate test function with B-splines instead
 * of piecewise linear basis functions to obtain a smoother interpolant.
 * The resulting sparse grid function is then minimized with the method of steepest descent.
 * For comparison, we also minimize the objective function with Nelder-Mead's method.
 */

/**
 * First, we include all the necessary headers, including those of the sgpp::base and
 * sgpp::optimization module.
 */
#include <sgpp_base.hpp>
#include <sgpp/base/grid/Grid.hpp>
#include <sgpp/base/datatypes/DataMatrix.hpp>
#include <sgpp/datadriven/DatadrivenOpFactory.hpp>
#include <sgpp/datadriven/application/KernelDensityEstimator.hpp>
#include <sgpp/datadriven/application/LearnerSGDE.hpp>
#include <sgpp/datadriven/configuration/RegularizationConfiguration.hpp>
#include <sgpp/datadriven/tools/ARFFTools.hpp>
#include <sgpp_optimization.hpp>
#include <sgpp/globaldef.hpp>

#include <algorithm>
#include <iostream>
#include <iterator>
#include <random>
#include <string>

using sgpp::base::DataMatrix;
using sgpp::base::DataVector;
using sgpp::base::Grid;
using sgpp::base::GridGenerator;
using sgpp::base::GridStorage;

/**
 * The function \f$f\colon [0, 1]^d \to \mathbb{R}\f$ to be minimized
 * is called <i>objective function</i> and has to derive from
 * sgpp::optimization::ScalarFunction.
 * In the constructor, we give the dimensionality of the domain
 * (in this case \f$d = 2\f$).
 * The eval method evaluates the objective function and returns the function
 * value \f$f(\vec{x})\f$ for a given point \f$\vec{x} \in [0, 1]^d\f$.
 * The clone method returns a std::unique_ptr to a clone of the object
 * and is used for parallelization (in case eval is not thread-safe).
 */
class ExampleFunction : public sgpp::optimization::ScalarFunction {
 public:
  sgpp::base::DataVector featureVector;
  sgpp::datadriven::LearnerSGDE& learner;
  
  // our optimization problem is 3dim
  ExampleFunction(sgpp::datadriven::LearnerSGDE& _learner) : sgpp::optimization::ScalarFunction(3), learner(_learner) {}

  // x is 3dim, we need to supply the rest (feature vector)
  double eval(const sgpp::base::DataVector& x) {

        sgpp::base::DataVector xNew(x);

        xNew.append(featureVector.get(0));
        xNew.append(featureVector.get(1));
        xNew.append(featureVector.get(2));
        xNew.append(featureVector.get(3));
        return learner.pdf(xNew);
  }

  virtual void clone(std::unique_ptr<sgpp::optimization::ScalarFunction>& clone) const {
    clone = std::unique_ptr<sgpp::optimization::ScalarFunction>(new ExampleFunction(*this));
  }

  void setParamters(const sgpp::base::DataVector& reqFeatureVector){
    featureVector=reqFeatureVector;
  }
};

/**
 * Now, we can start with the \c main function.
 */
void printLine() {
  std::cout << "----------------------------------------"
               "----------------------------------------\n";
}

int main(int argc, const char* argv[]) {
  (void)argc;
  (void)argv;

  std::string filename = "./results_successful.arff";

  std::cout << "# loading file: " << filename << std::endl;
  sgpp::datadriven::Dataset dataset =
    sgpp::datadriven::ARFFTools::readARFFFromFile(filename);
  sgpp::base::DataMatrix& samples = dataset.getData();

  /**
   * Configure the sparse grid of level 3 with linear basis functions and the same dimension as the
   * given test data.
   * Alternatively load a sparse grid that has been saved to a file, see the commented line.
   */
  std::cout << "# create grid config" << std::endl;
  sgpp::base::RegularGridConfiguration gridConfig;
  gridConfig.dim_ = dataset.getDimension();
  gridConfig.level_ = 3;
  gridConfig.type_ = sgpp::base::GridType::Linear; //TODO: BSplines not available, but does not matter
  //  gridConfig.filename_ = "/tmp/sgde-grid-4391dc6e-54cd-4ca2-9510-a9c02a2889ec.grid";

  /**
   * Configure the adaptive refinement. Therefore the number of refinements and the number of points
   * are specified.
   */
  std::cout << "# create adaptive refinement config" << std::endl;
  sgpp::base::AdaptivityConfiguration adaptConfig;
  adaptConfig.numRefinements_ = 0;
  adaptConfig.noPoints_ = 10;

  /**
   * Configure the solver. The solver type is set to the conjugent gradient method and the maximum
   * number of iterations, the tolerance epsilon and the threshold are specified.
   */
  std::cout << "# create solver config" << std::endl;
  sgpp::solver::SLESolverConfiguration solverConfig;
  solverConfig.type_ = sgpp::solver::SLESolverType::CG;
  solverConfig.maxIterations_ = 1000;
  solverConfig.eps_ = 1e-14;
  solverConfig.threshold_ = 1e-14;

  /**
   * Configure the regularization for the laplacian operator.
   */
  std::cout << "# create regularization config" << std::endl;
  sgpp::datadriven::RegularizationConfiguration regularizationConfig;
  regularizationConfig.type_ = sgpp::datadriven::RegularizationType::Laplace;

  /**
   * Configure the learner by specifying:
   * - an initial value for the lagrangian multiplier \f$\lambda\f$ and the interval
   *   \f$ [\lambda_{Start} , \lambda_{End}] \f$ in which \f$\lambda\f$ will be searched,
   * - whether a logarithmic scale is used,
   * - the parameters shuffle and an initial seed for the random value generation,
   * - whether parts of the output shall be kept off.
   */
  std::cout << "# create learner config" << std::endl;
  sgpp::datadriven::CrossvalidationConfiguration crossvalidationConfig;
  crossvalidationConfig.enable_ = false;
  crossvalidationConfig.kfold_ = 3;
  crossvalidationConfig.lambda_ = 3.16228e-06;
  crossvalidationConfig.lambdaStart_ = 1e-1;
  crossvalidationConfig.lambdaEnd_ = 1e-10;
  crossvalidationConfig.lambdaSteps_ = 3;
  crossvalidationConfig.logScale_ = true;
  crossvalidationConfig.shuffle_ = true;
  crossvalidationConfig.seed_ = 1234567;
  crossvalidationConfig.silent_ = false;

  /**
   * Create the learner using the configuratons set above. Then initialize it with the data read
   * from the file in the first step and train the learner.
   */
  std::cout << "# creating the learner" << std::endl;
  sgpp::datadriven::LearnerSGDE learner(gridConfig, adaptConfig, solverConfig, regularizationConfig,
                                        crossvalidationConfig);
  learner.initialize(samples);
  learner.train();
  /**
   * Estimate the probability density function (pdf) via a gaussian kernel density estimation (KDE)
   * and print the corresponding values.
   */
  sgpp::datadriven::KernelDensityEstimator kde(samples);
  sgpp::base::DataVector x(learner.getDim());
  // samples.getRow(samples.getNrows()/2, x);
  samples.getRow(1, x);
  
  std::cout << "--------------------------------------------------------\n";
  std::cout << "x= " << x.toString() << "\n";
  std::cout << "SGDE dimensions " << learner.getDim() << "\n";
  std::cout << "SGDE sample count " << learner.getNsamples() << "\n";
  std::cout << learner.getSurpluses()->getSize() << " -> " << learner.getSurpluses()->sum() << "\n";
  std::cout << "pdf_SGDE(x) = " << learner.pdf(x) << " ~ " << kde.pdf(x) << " =pdf_KDE(x)\n";
  std::cout << "mean_SGDE(x) = " << learner.mean() << " ~ " << kde.mean() << " = mean_KDE(x)\n";
  std::cout << "var_SGDE(x) = " << learner.variance() << " ~ " << kde.variance() << "=var_KDE(x)\n";

  std::cout << "sgpp::optimization example program started.\n\n";
  // increase verbosity of the output
  sgpp::optimization::Printer::getInstance().setVerbosity(2);

  /**
   * Here, we define some parameters: objective function, dimensionality,
   * B-spline degree, maximal number of grid points, and adaptivity.
   */
  // objective function
  ExampleFunction f(learner);
  sgpp::base::DataVector testFV;
  testFV.append(samples.get(0,3));
  testFV.append(samples.get(0,4));
  testFV.append(samples.get(0,5));
  testFV.append(samples.get(0,6));

  f.setParamters(testFV);
  // dimension of domain
  const size_t d = f.getNumberOfParameters();
  // B-spline degree
  const size_t p = 3;
  // maximal number of grid points
  const size_t N = 30;
  // adaptivity of grid generation
  const double gamma = 0.95;

  /**
   * First, we define a grid with modified B-spline basis functions and
   * an iterative grid generator, which can generate the grid adaptively.
   */
  sgpp::base::ModBsplineGrid grid(d, p);
  sgpp::optimization::IterativeGridGeneratorRitterNovak gridGen(f, grid, N, gamma);

  /**
   * With the iterative grid generator, we generate adaptively a sparse grid.
   */
  printLine();
  std::cout << "Generating grid...\n\n";

  if (!gridGen.generate()) {
    std::cout << "Grid generation failed, exiting.\n";
    return 1;
  }

  /**
   * Then, we hierarchize the function values to get hierarchical B-spline
   * coefficients of the B-spline sparse grid interpolant
   * \f$\tilde{f}\colon [0, 1]^d \to \mathbb{R}\f$.
   */
  printLine();
  std::cout << "Hierarchizing...\n\n";
  sgpp::base::DataVector functionValues(gridGen.getFunctionValues());
  sgpp::base::DataVector coeffs(functionValues.getSize());
  sgpp::optimization::HierarchisationSLE hierSLE(grid);
  sgpp::optimization::sle_solver::Auto sleSolver;

  // solve linear system
  if (!sleSolver.solve(hierSLE, functionValues, coeffs)) {
    std::cout << "Solving failed, exiting.\n";
    return 1;
  }

  /**
   * We define the interpolant \f$\tilde{f}\f$ and its gradient
   * \f$\nabla\tilde{f}\f$ for use with the gradient method (steepest descent).
   * Of course, one can also use other optimization algorithms from
   * sgpp::optimization::optimizer.
   */
  printLine();
  std::cout << "Optimizing smooth interpolant...\n\n";
  sgpp::optimization::InterpolantScalarFunction ft(grid, coeffs);
  sgpp::optimization::InterpolantScalarFunctionGradient ftGradient(grid, coeffs);
  sgpp::optimization::optimizer::GradientDescent gradientDescent(ft, ftGradient);
  sgpp::base::DataVector x0(d);
  double fX0;
  double ftX0;

  /**
   * The gradient method needs a starting point.
   * We use a point of our adaptively generated sparse grid as starting point.
   * More specifically, we use the point with the smallest
   * (most promising) function value and save it in x0.
   */
  {
    sgpp::base::GridStorage& gridStorage = grid.getStorage();

    // index of grid point with minimal function value
    size_t x0Index =
        std::distance(functionValues.getPointer(),
                      std::min_element(functionValues.getPointer(),
                                       functionValues.getPointer() + functionValues.getSize()));

    //x0 = gridStorage.getCoordinates(gridStorage[x0Index]);

    //x0.set(0, 0.399712630494);
    //x0.set(1, 0.010028000000);
    //x0.set(2, 0.547298970951);

    x0.set(0, samples.get(0,0));
    x0.set(1, samples.get(0,1));
    x0.set(2, samples.get(0,2));


    fX0 = functionValues[x0Index];
    ftX0 = ft.eval(x0);
  }

  std::cout << "Gradient starting point:\n";
  std::cout << "x0 = " << x0.toString() << "\n";
  std::cout << "f(x0) = " << fX0 << ", ft(x0) = " << ftX0 << "\n\n";

  /**
   * We apply the gradient method and print the results.
   */
  gradientDescent.setStartingPoint(x0);
  gradientDescent.optimize();
  const sgpp::base::DataVector& xOpt = gradientDescent.getOptimalPoint();
  const double ftXOpt = gradientDescent.getOptimalValue();
  const double fXOpt = f.eval(xOpt);

  std::cout << "\noptimal control point:\n";
  std::cout << "for feature vector" << testFV.toString() << "\n";
  std::cout << "xOpt = " << xOpt.toString() << "\n";
  std::cout << "f(xOpt) = " << fXOpt << ", ft(xOpt) = " << ftXOpt << "\n\n";
  
  return 0;
}

/**
 * The example program outputs the following results:
 * \verbinclude optimization.output.txt
 *
 * We see that both the gradient-based optimization of the smooth sparse grid
 * interpolant and the gradient-free optimization of the objective function
 * find reasonable approximations of the minimum, which lies at
 * \f$(3\pi/16, 3\pi/14) \approx (0.58904862, 0.67319843)\f$.
 */
