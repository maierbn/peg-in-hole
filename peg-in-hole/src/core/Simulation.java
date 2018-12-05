package core;

public class Simulation {
	public FlexibleObject f;
	public int steps;
	
	public Simulation(FlexibleObject f, int steps) {
		this.f = f;
		this.steps = steps;
	}
	
	public void start() {
		Log.print("Starting Simulation..");
		for(double x = 0; x <= f.length; x += (f.length / steps)) {
			double deflection = Formulas.deflection(f, x);
			Log.print("Deflection(x=" + x * 1000 + "mm) = " + deflection * 1000 + "mm");
		}
	}

}
