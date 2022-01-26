package timeApi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Scanner;

public class LocalDateFormatierung {

	public static void main(String[] args) {
		
		LocalDate heute = LocalDate.now();
		// für DE dd.MM.yyyy
		DateTimeFormatter fmt = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
		// Zeichenfolge aus Datum erzeugen
		System.out.printf("Heute(toString): %s\n", heute);
		// formatierte Zeichenfolge
		System.out.printf("Heute(toString): %s\n", fmt.format(heute));
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Gib bitte ein Datum ein: ");
		String strDate = scan.nextLine();
		// aus der Zeichenfolge ein LocalDate-Objekt erzeugen
		// (die Zeichenfolge muss zum Formatter passen)
		LocalDate date2 = LocalDate.parse(strDate, fmt);
		
		System.out.printf("Dein Datum (toString): %s\n", date2);
		// formatierte Zeichenfolge
		System.out.printf("Dein Datum (formatter): %s\n", fmt.format(date2));

	}

}
