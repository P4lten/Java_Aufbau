package enumPackage3;

import java.util.Scanner;

public class EnumDemo {

	public static void main(String[] args) {
		System.out.printf("Unsere Kurstage sind %s und %s\n", Wochentage.MONTAG, Wochentage.DIENSTAG);
//		teste(Wochentage.DIENSTAG);
//		teste(Wochentage.SAMSTAG);

		
		Wochentage [] alleTage = Wochentage.values();
		for (int i = 0; i < alleTage.length; i++) {
			System.out.printf("Index %d: %s (ordial=%d)\n", i , alleTage[i], alleTage[i].ordinal());
		}
		
		
		// eine Zeichenfolge einlesen
		Scanner input = new Scanner(System.in);
		System.out.println("Welcher Wochentag?");
		String strTage = input.nextLine();
		// ... und einen enum-Wert daraus ermitteln (in Großbuchstaben)
		Wochentage wTag = Wochentage.valueOf(strTage.toUpperCase());

		teste(wTag);

		input.close();
	}

	static void teste(Wochentage wTag) {
		System.out.printf("Wochentag %s: \n", wTag);
		switch (wTag) {
		case MONTAG, DIENSTAG -> System.out.println("Wir haben Java-Kurs :-)");

		default -> System.out.printf("Am %s ist kein Java-Kurs :-(\n", wTag);
		}
		if (wTag.isWochenende()) {
			System.out.println("Es ist Wochenende :D");
		} else {
			System.out.println("Es ist ein Arbeitstag :'(");
		}
	}

}
