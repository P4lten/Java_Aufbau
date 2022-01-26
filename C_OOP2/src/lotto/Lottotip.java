package lotto;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Lottotip {

	private int[] lottotip = new int[6];
	private Random random = new Random();

	Scanner input = new Scanner(System.in);

	public void manuellerTipp() {
		try {
			System.out.println("Bitte 6 Zahlen zwischen 1 und 45 eingeben:");
			for (int i = 0; i < lottotip.length; i++) {
				lottotip[i] = input.nextInt();
				if (lottotip[i] > 45 && lottotip[i] < 1) {
					throw new IllegalArgumentException("Wert nicht im Werteprogramm");
				}
			}
			input.close();
		} catch (InputMismatchException e) {
			input.nextLine();

		}

	}

	public void quickTipp() {

		for (int i = 0; i < lottotip.length; i++) {
			lottotip[i] = random.nextInt(45) + 1;
		}
		int i = 0;
		while (i < lottotip.length) {
			for (int j = i + 1; j < lottotip.length; j++) {

				if (lottotip[i] == lottotip[j]) {
					lottotip[j] = random.nextInt(46);
					i = 0;
				}
			}
			i++;
		}

	}

	public void ausgeben() {

		System.out.println(Arrays.toString(lottotip));

	}

	public int[] gewinnPruefung(int[] gewinnzahlen) {

		int counter = 0;
		int[] zahlen = new int[6];
		int i = 0;
		while (i < gewinnzahlen.length) {
			for (int j = 0; j < gewinnzahlen.length; j++) {
				if (gewinnzahlen[i] == lottotip[j]) {
					counter++;
					zahlen[counter - 1] = gewinnzahlen[i];
				}
			}
			i++;
		}
		System.out.println("Sie haben " + counter + " uebereinstimmende Zahlen!");

		System.out.print(Arrays.toString(zahlen) + "\n");

		return zahlen;
	}

}
