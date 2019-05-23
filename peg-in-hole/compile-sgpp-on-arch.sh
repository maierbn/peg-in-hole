sudo pacman -S gcc scons boost boost-libs swig jdk8-openjdk doxygen graphviz gsl eigen suitesparse gmm zlib #python-numpy

cd cpp

git clone https://github.com/SGpp/SGpp

cd SGpp

scons -j 8
