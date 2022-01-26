package timeApi;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class LocalDateDemo {

	public static void main(String[] args) {
		// Das aktuelle Datum holen
		LocalDate heute = LocalDate.now();
		
		// Ein Datum aus den Einzel-Bestandteilen erstellen
		LocalDate geburtsdatum = LocalDate.of(1994, 7, 18);
		
		// Ein Datum aus einer Zeichenfolge im ISO-Format Parsen
		Scanner input = new Scanner(System.in);
		System.out.println("Bitte Geburtsdatum eingeben: (yyyy-MM-dd)");
		String strDate = input.nextLine();
		LocalDate gebDatum2 = LocalDate.parse(strDate);
		
		System.out.printf("Heute %s, GebDaum: %s, GebDatum2: %s\n", heute, geburtsdatum, gebDatum2);
		
		// Zeitspanne zwischen 2 Datumswerten berechnen:
		// Variante 1: Period
		Period span1 = Period.between( geburtsdatum, heute);
		
		System.out.printf("Zwischen %s und %s liegen %d Jahre, %d Monate und %d Tagen\n", 
				geburtsdatum, heute, span1.getYears(), span1.getMonths(), span1.getDays());
		System.out.printf("Zwischen %s und %s liegt die Period %s\n",
				geburtsdatum, heute, span1);
		
		// Variante 2 : ChronoUnit
		long daysBetween = ChronoUnit.DAYS.between(gebDatum2, heute);
		System.out.printf("Zwischen %s und %s liegen %d Tage\n", gebDatum2, heute, daysBetween);
		
		long monthsBetween = ChronoUnit.MONTHS.between(gebDatum2, heute);
		System.out.printf("Zwischen %s und %s liegen %d Monate\n", gebDatum2, heute, monthsBetween);
		
		long yearsBetween = ChronoUnit.YEARS.between(gebDatum2, heute);
		System.out.printf("Zwischen %s und %s liegen %d Jahre\n", gebDatum2, heute, yearsBetween);
		
		// vergleichen wir geburtsdatum und gebDatum2
		int ret = geburtsdatum.compareTo(gebDatum2);
		// das Ergebnis anzeigen (ist beim Sortieren nicht nötig
		if (ret < 0) {
			System.out.printf("%s is kleiner als %s\n", geburtsdatum, gebDatum2);
		} else if(ret > 0) {
			System.out.printf("%s is größer als %s\n", geburtsdatum, gebDatum2);
		}else {
			System.out.printf("%s und %s sind gleich \n", geburtsdatum, gebDatum2);

		}
		
		
		
		input.close();
	}

}
