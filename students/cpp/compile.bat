g++ optimize.cpp ^
-c -std=c++11 -fopenmp ^
-IC:\build_sgpp\sgppWorkDir\base\src ^
-IC:\build_sgpp\sgppWorkDir\combigrid\src ^
-IC:\build_sgpp\sgppWorkDir\datadriven\src ^
-IC:\build_sgpp\sgppWorkDir\misc\src ^
-IC:\build_sgpp\sgppWorkDir\optimization\src ^
-IC:\build_sgpp\sgppWorkDir\pde\src ^
-IC:\build_sgpp\sgppWorkDir\quadrature\src ^
-IC:\build_sgpp\sgppWorkDir\solver\src ^
-IC:\build_sgpp\boost ^
-o optimize.o

g++ optimize.o -fopenmp ^
-LC:\build_sgpp\sgppWorkDir\lib\sgpp ^
-lsgppbase ^
-lsgppcombigrid ^
-lsgppdatadriven ^
-lsgppmisc ^
-lsgppoptimization ^
-lsgpppde ^
-lsgppquadrature ^
-lsgppsolver ^
-o optimize.exe