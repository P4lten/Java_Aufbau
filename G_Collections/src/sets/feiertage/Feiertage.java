package sets.feiertage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Feiertage {

	private final int jahr;

	// TODO eine Collectin für die Feiertage dieses Jahres definieren
	private Set<LocalDate> feiertage = new TreeSet<>();
	
	public Feiertage(int jahr) {
		this.jahr = jahr;
		 // Feiertage einfügen
		 feiertage.add(LocalDate.of(jahr, 1, 1));
		 feiertage.add(LocalDate.of(jahr, 1, 6));
		 feiertage.add(LocalDate.of(jahr, 5, 1));
		 feiertage.add(LocalDate.of(jahr, 8, 15));
		 feiertage.add(LocalDate.of(jahr, 10, 26));
		 feiertage.add(LocalDate.of(jahr, 11, 1));
		 feiertage.add(LocalDate.of(jahr, 12, 8));
		 feiertage.add(LocalDate.of(jahr, 12, 25));
		 feiertage.add(LocalDate.of(jahr, 12, 26));

		 // bewegliche Feiertage:
		 LocalDate tag = calcOstern();
		 // Ostermontag: 1 Tag
		 feiertage.add(tag = tag.plusDays(1));
		 // Christi Himmelfahrt
		 feiertage.add(tag = tag.plusDays(38));
		 // Pfingstmontag
		 feiertage.add(tag = tag.plusDays(11));
		 // Fronleichnam
		 feiertage.add(tag = tag.plusDays(10));
		 System.out.printf("Feiertage hinzugefügt: im Jahr %d gibt es %d Feiertage\n", jahr, feiertage.size());
	}
	
	public int getJahr() {
		return jahr;
	}

	// KEINEN getter für das Feiertage-Set - der Aufrufer könnte sonst
	// nach Belieben Feiertage hinzufügen oder entfernen
	// getter für die Feiertage
//	 public Set<LocalDate> getFeiertage(){
//	 return feiertage;
//	 }

	// Stattdessen ein Array von Datums-Objekten liefern
	public LocalDate[] getFeiertage() {
		// TODO Kopie der Tage zurückliefern
		LocalDate[] tage = new LocalDate[feiertage.size()];
		// parallele Iteration über den Array-Index und die Feiertage
		//Iterator für die Iteration der Feiertage-Collection
		Iterator<LocalDate> iterator = feiertage.iterator();
		// for-Schleife für die Iteration über den Array-Index
		for (int i = 0; i < tage.length; i++) {
//			LocalDate tag = iterator.next();
//			tage [i] = tag;
			// ohne Hilfsvariable
			tage [i] = iterator.next();
		}
		return tage;
	}

	
	public LocalDate[] getFeiertage2() {
		// Kopie der Tage zurückliefern
		// ein neues, passend großes Array erstellen
		LocalDate[] tage = new LocalDate[feiertage.size()];
		// die Elemente aus dem Set hineinkopieren
		feiertage.toArray(tage);
		// das array zurückliefern
		return tage;
	}
	
	public LocalDate[] getFeiertage3() {
		// mit Stram API die Feiertage in ein Array kopieren
		return feiertage.stream().toArray(size -> new LocalDate[size]);
	}
	
	public boolean istFeiertag(int tag, int monat) {
		// im Set prüfen ob das Datum enthalten ist
		
		return feiertage.contains(LocalDate.of(jahr, monat, tag));
	}
	
	public boolean istFeiertag2(int tag, int monat) {
		// im Set prüfen ob das Datum enthalten ist
		for (LocalDate feiertag : feiertage) {
			// wenn Tag und Monat übereinstrimmen ist es ein Feiertag
			if(feiertag.getDayOfMonth() == tag && feiertag.getMonthValue() == monat) {
				return true;
			}
			//else: weitersuchen
		}
		// nicht gefunden -> kein Feiertag
		return false;
	}

	public void zeigeFeiertage() {
		// TODO: alle Feiertage anzeigen
		System.out.printf("Feiertage im Jahr %d: \n", jahr);
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
		
		for (LocalDate feiertag : feiertage) {
			System.out.println(formatter.format(feiertag));
		}
	}

	private LocalDate calcOstern() {
		int days = (((255 - 11 * (jahr % 19)) - 21) % 30) + 21;
		int variant_bool = days > 48 ? -1 : 0;
		LocalDate ostern = LocalDate.of(jahr, 3, 1).plusDays(days + (variant_bool) + 6
				- ((jahr + jahr / 4 + days + (variant_bool) + 1) % 7));

		return ostern;
	}

}
