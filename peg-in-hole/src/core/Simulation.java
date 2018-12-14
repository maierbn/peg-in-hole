package core;

public class Simulation {
	public FlexibleObject flexObj;
	public int deflectionRes;
	public double[] deflectionValArray;

	public Simulation(FlexibleObject f, int steps) {
		this.flexObj = f;
		this.deflectionRes = steps;
	}

	public void deflectionValues(FlexibleObject object, int steps) {

		deflectionValArray = new double[steps+1];

		int i = 0;
		for (double x = 0; x <= object.length; x += (object.length / steps)) {
			deflectionValArray[i] = Formulas.deflection(object, x);
			i++;
		}
	}

	public void start() {
//		Log.print("Starting Simulation..");
		System.out.println("Calculatin deflection values...");
		deflectionValues(this.flexObj, this.deflectionRes);
		System.out.println("Done");
		
		
//		Log.print("Deflection(x=" + x * 1000 + "mm) = " + deflection * 1000 + "mm");
	}
}
