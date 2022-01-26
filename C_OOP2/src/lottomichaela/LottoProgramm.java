package lottomichaela;

import java.util.Arrays;

public class LottoProgramm {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] gewinnzahlen = { 11, 12, 13, 14, 15, 16 };

//		for (int i = 0; i < 10; i++) {
//
//			Lottotip tipp1 = new Lottotip();
//			tipp1.quicktipp();
//			tipp1.ausgeben();
//
//			int[] richtige = tipp1.gewinnPruefung(gewinnzahlen);
//			System.out.println("Richtige: " + Arrays.toString(richtige));
//		}
		
		
		
		Lottotip tipp2 = new Lottotip();
		tipp2.quicktipp();
		tipp2.ausgeben();
		
		// das Array holen
		int [] tippzahlen = tipp2.getTippZahlen();
		// Wert eines Elements ändern
		tippzahlen[0]++;
		System.out.println("Kopie der Tippzahlen: " + Arrays.toString(tippzahlen));
		tipp2.ausgeben();
		
		
	}

}
