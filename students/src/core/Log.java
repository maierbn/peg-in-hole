package core;

public class Log {
	public static void print(Object o) {
		if(Constants.verboseMode) {
			System.out.println(o);
		}
	}
}
