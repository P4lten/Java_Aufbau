
package lotto;

public class LottotipMain {

	public static void main(String[] args) {
		
		Lottotip lotto1 = new Lottotip();
		
		int [] gewinnzahlen = {1, 2, 3, 4, 5, 6};
		int[] array = new int[6];
		
//		lotto1.manuellerTipp();
//		
		lotto1.ausgeben();
		
		lotto1.gewinnPruefung(gewinnzahlen);
		
		lotto1.quickTipp();

		lotto1.ausgeben();
		
		lotto1.gewinnPruefung(gewinnzahlen);
	}

}
