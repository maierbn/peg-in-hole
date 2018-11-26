package userInterface;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public void werte() {
		float wx = 0;		//position
		float wy = 0;		//position 
		float t = 0; 		//time
		float E = 0;		// Young's modulus (a quantity that measures an object or substance's resistance to being deformed elasticall)
		float I = 0;		// 2nd Area Moment (Flaechentraegheitsmoment)
		float q = 0;		// body load (external force acting upon the beam)
		int x= 0;			// FIX Point left side
		float L = 0;		// other End of the Object where x = L
		float wofx = 0;		//deflection w(x) of the beam
	}
	public float inertia(float I) {
		float h = 0;		//height
		float b = 0;		//width
		I = (b*(h*h*h))/12;
		return I;
		
	}
	public float deflection(float wofx) {
		
		
		return wofx;
	}
}
