package core;

public class Simulation {
	public FlexibleObject f;
	public int steps;
	
	public Simulation(FlexibleObject f, int steps) {
		this.f = f;
		this.steps = steps;
	}
	
	public void start() {
		for(double x = 0; x <= f.length; x += (f.length / steps)) {
			double deflection = Formulas.deflection(f, x);
			System.out.println(deflection);
			System.out.println(x);
		}
	}

}
