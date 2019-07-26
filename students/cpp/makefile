optimize: optimize.cpp
	g++ optimize.cpp \
	-c -std=c++11 -fopenmp \
	-ISGpp/base/src \
	-ISGpp/combigrid/src \
	-ISGpp/datadriven/src \
	-ISGpp/misc/src \
	-ISGpp/optimization/src \
	-ISGpp/pde/src \
	-ISGpp/quadrature/src \
	-ISGpp/solver/src \
	-o optimize.o

	g++ optimize.o -fopenmp \
	-LSGpp/lib/sgpp \
	-lsgppbase \
	-lsgppcombigrid \
	-lsgppdatadriven \
	-lsgppmisc \
	-lsgppoptimization \
	-lsgpppde \
	-lsgppquadrature \
	-lsgppsolver \
	-o optimize