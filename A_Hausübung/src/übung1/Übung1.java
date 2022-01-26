package übung1;

import java.util.Scanner;

public class Übung1 {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int sumMonate = 0;
		int[] arrayMonat = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31, 0 };
//		jaenner = 31, februar = 28, märz = 31, april = 30, mai = 31, juni = 30, july = 31, august = 31,
//		september = 30, oktober = 31, november = 30, dezember = 31;

		System.out.println("Bitte Jahreszahl eingeben: ");
		int jahr = scan.nextInt();
		int tage = 0;

		// Hier werden die Tage übergeben, geschaut ob es ein Schaltjahr ist und
		// überprüft ob die Tage im Rahmen des möglichen sind
		// Man muss sie so oft eingeben bis sie stimmen!!!
		if (isSchaltjahr(jahr)) {
			do {
				System.out.println("Bitte Zahl zwischen 1 und 366 eingeben: ");
				tage = scan.nextInt();
			} while (!(tage >= 1 && tage <= 366));
		} else {
			do {
				System.out.println("Bitte Zahl zwischen 1 und 365 eingeben: ");
				tage = scan.nextInt();
			} while (!(tage >= 1 && tage <= 365));
		}

		// Wenn ein SChaltjahr ist wird der Februar auf 29 tage gesetzt
		if (isSchaltjahr(jahr)) {
			arrayMonat[1] = 29;
		}

		for (int i = 0; i < arrayMonat.length; i++) {
			if (sumMonate < tage) {
				sumMonate = sumMonate + arrayMonat[i];
			} else {
				sumMonate -= tage;
				sumMonate = arrayMonat[i - 1] - sumMonate;
				System.out.println("Ergebniss: " + sumMonate + "." + i + " " + jahr);
				break;
			}
		}

		
		scan.close();
	}

		
	static boolean isSchaltjahr(int jahr) {

		if (jahr % 4 == 0 && (jahr % 100 != 0 || jahr % 400 == 0)) {
			return true;
		} else {
			return false;
		}

	}
	

}
