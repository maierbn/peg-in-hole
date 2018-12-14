package core;

import java.util.Arrays;

public class Simulation {
	public FlexibleObject flexObj;
	public int deflectionRes;
	public double[] deflectionValues;

	public Simulation(FlexibleObject f, int steps) {
		this.flexObj = f;
		this.deflectionRes = steps;
	}

	public void calcDeflectionValues(FlexibleObject object, int steps) {
		Log.print("Calculatin deflection values..");
		
		deflectionValues = new double[steps+1];

		int i = 0;
		for (double x = 0; x <= object.length; x += (object.length / steps)) {
			deflectionValues[i] = Formulas.deflection(object, x);
			i++;
		}
		
		Log.print("Deflection value array:\n\t"
				+ Arrays.toString(deflectionValues));
	}

	public void start() {
		Log.print("Starting simulation..");
		
		calcDeflectionValues(this.flexObj, this.deflectionRes);
	}
}
