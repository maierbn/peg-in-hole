## Forschungsprojekt Informatik - Peg-in-Hole
### ToDo
+ [ ] update makefile with path to boost library
----
### Dokumentation
Das Projekt besteht aus zwei Teilen: Datengenerierung + Simulation in Java und Datenauswertung in C++. Das Bindeglied beider Programmteile stellt die Konfigurationsdatei `object*.ini` dar. Referenzimplementierung (bitte keine Kommentare in der `*.ini`, diese gehen beim parsen verloren):

    [Global]
    generateDatabase=true			; if database from control points should be enabled
    simulateGivenControlPoint=false		; if scenario with given control point should be simulated and visualized
    deflectionRes=30			; resolution of object deflection for collision detection
    trajectoryRes=30			; resolution of trajectory for collision detection
    slitSize=0.005				; size of slit for collision detection
    databaseFilename=test.arff		; filename for output file
    
    [Object]
    objectLength=0.040			; object length
    objectWidth=0.015			; width, relevant for cross section
    objectThickness=0.002			; material parameter thickness
    objectDensity=1150			; material parameter rho
    objectYoungsModulus=3.6E6		; material parameter E
    
    [Simulation]
    cpX=0.20103816656013962			; control point: x coordinate
    cpY=3.8647759142919378e-010		; control point: y coordinate
    cpZ=0.27960541169755371			; control point: rotation
    
    [Database]
    granularity=20				; sampling size per dimension for generation of training data, 
                                        sampling takes place in x-direction [0,object-length], 
                                        y-direction: [0,object-length], angle [0,starting-angle]
    writeOnlySuccessful=true		; if only simulation results with successful scenarios without 
                                            collision should be written to the file

#### Kompilieren und Ausführen
Sowohl für Windows als auch Linux stehen Skripts zum Kompilieren (`compile.bat`/`makefile`) und Ausführen (`test-optimizer.bat`/`test-optimizer.sh`) bereit.

Ausführen des Java-Teils:

    java -jar .\test.jar configFileName.ini

Ausführen des C++-Teils:

	optimize.exe DataFile.arff configFileName.ini FeatureVectorDim1 FeatureVectorDim2 FeatureVectorDim3 FeatureVectorDim4
----
### Roadmap
#### Phase 1 - Offline
+ [x] Vorbereitung
	+ [x] JSGPP
+ [x] Deformation
	+ [x] Modellierung
	+ [x] Berechnung der Augangsdeformation
	+ [x] Visualisierung der Augangsdeformation
	+ [x] Berechnung der Deformation bei gegebenem Winkel in P0
+ [x] Trajektorie
	+ [x] Formel fuer die Trajektorie bei gegebenem CP
	+ [x] Visualisierung einer Trajektorie
+ [x] Simulation
	+ [x] Erstelle Array mit gut gewaehlten CP's
	+ [x] Simulation der Trajektorien mit live-Berechnung der Deformation
	+ [x] Kollisionserkennung und Minimalabstandsmessung
	+ [x] Speichern der CP's mit deren Minimalabstand
	+ [x] Kernelkomposition & Dichtefunktion

#### Phase 2 - Online
+ [x] Realtime-Suche
+ [x] Validierung
+ [x] Auswerung

####  Phase 3 - Ergebnisse
+ [x] Ausarbeitung
+ [ ] Poster
----
### Source
* [Paper: Learning Peg-In-Hole actions with flexible objects](https://www.researchgate.net/publication/265945436_Learning_Peg-In-Hole_actions_with_flexible_objects)
* [Sparse Grid Library](http://sgpp.sparsegrids.org/index.html) - [Manual](http://sgpp.sparsegrids.org/manual.html) - [Java Examples](http://sgpp.sparsegrids.org/examples_java.html)
---
### Disclaimer
This project uses the General Sparse Grid Toolbox SG++.
This project uses the open-source XChart library for debugging purposes, which is licensed under the Apache v2.0 License.

> The software SG++ is developed by The SG++ Project which is the
> owner of the software.
> 
> According to good scientific practice, publications on results
> achieved in whole or in part due to SG++ have to cite at least one
> paper presenting the SG++ software.
> 
> The owner wishes to make the software available to all users to use,
> reproduce, modify, distribute and redistribute also for commercial
> purposes under the following conditions of the original BSD
> license. Linking the SG++ module statically or dynamically with other
> modules is making a combined work based on SG++. Thus, the terms and
> conditions of this license cover the whole combination. As a special
> exception, the copyright holders of SG++ give you permission to link
> it with independent modules or to instantiate templates and macros
> from SG++'s source files to produce an executable, regardless of the
> license terms of these independent modules, and to copy and distribute
> the resulting executable under terms of your choice, provided that you
> also meet, for each linked independent module, the terms and
> conditions of this license of that module.
> 
> 
> Copyright (c) 2008-today The SG++ Project
> 
> All rights reserved.
> 
> Redistribution and use in source and binary forms, with or without
> modification, are permitted provided that the following conditions are
> met:
> 
>  Redistributions of source code must retain the above copyright notice, this
>     list of conditions and the following disclaimer.
>  Redistributions in binary form must reproduce the above copyright notice,
>     this list of conditions and the following disclaimer in the documentation
>     and/or other materials provided with the distribution.
>  All advertising materials mentioning features or use of this software must
>     display the following acknowledgement: "This product includes the software
>     SG++ developed 2008-today by The SG++ Project and its contributors."
>  Neither the name of The SG++ Project nor the names of its contributors
>     may be used to endorse or promote products derived from this software
>     without specific prior written permission.
> 
> THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
> "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
> LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
> A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
> HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
> SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
> LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
> DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
> THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
> (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
> OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.