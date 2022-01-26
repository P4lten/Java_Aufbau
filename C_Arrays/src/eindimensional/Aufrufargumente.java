package eindimensional;

public class Aufrufargumente {

	// args enthält Aufrufargumenten
	public static void main(String[] args) {

	
		if (args.length == 0) {
			System.out.println("Keine aufrufargumente vorhanden");
		} else {
			System.out.println("Aufrufargumente :");
			for (int i = 0; i < args.length; i ++) {
				String arg = args[i];
				System.out.printf("%d Argument %s\n", i+1, arg);
			}
		}
		

	}

}
