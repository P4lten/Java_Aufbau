package eindimensional;

public class VarargsDemo {

	public static void main(String[] args) {
		
		double [] numbers = {3.5, 2.4, 4.2};
		double avg = average(numbers);
		System.out.printf("Der Durchschnitt ist %.2f\n", avg);
		
		
		avg = average(new double [] {7.5, 3.6, 5.6, 5.9});
		System.out.printf("Der Durchschnitt ist %.2f\n", avg);
		
		
		// oder kürzer:
		avg = average(7.5, 3.6, 5.6, 5.9);
		// Der Compiler mnacht daraus folgendes Statement:
		// avg = average(new double [] {7.5, 3.6, 5.6, 5.9})
		System.out.printf("Der Durchschnitt ist %.2f\n", avg);

		
		
	}
	// statt double [] values -> double ... values
	//  -> Methode mit variabler Argumenteliste
	public static double average(double ... values) {
		if(values.length > 0) {
			double sum = 0;
			for (int i = 0; i < values.length; i++) {
				sum += values[i];
			}
			return sum/values.length;
		}
		throw new IllegalArgumentException("Das Array muss mindestns 1 Element enthalten");
	}
	
	
	

}
