#!/bin/bash

#add the sgpp-libray-path to the environment
export LD_LIBRARY_PATH="$PWD/SGpp/lib/sgpp/:$LD_LIBRARY_PATH"

#execute the program
./optimize test.arff 0.007932304688 0.026636875000 0.050237929688 0.075210000000
