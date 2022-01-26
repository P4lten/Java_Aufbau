package sets.feiertage;

import java.time.LocalDate;
import java.util.Set;

public class FeiertageDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Feiertage feiertage;
		try {
			feiertage = new Feiertage(2021);

			feiertage.zeigeFeiertage();

			LocalDate einTag = LocalDate.of(feiertage.getJahr(), 12, 25);
			if (feiertage.istFeiertag2(einTag.getDayOfMonth(), einTag.getMonthValue())) {
				System.out.printf("%s ist ein Feiertag %n", einTag);
			} else {
				System.out.printf("%s ist leider kein Feiertag %n", einTag);
			}
//			Set<LocalDate> alleFeiertage = feiertage.getFeiertage();
//			alleFeiertage.add(LocalDate.of(feiertage.getJahr(), 7, 4));
//			
//			feiertage.zeigeFeiertage();
//			
			// Feiertage-Auflistung
			// Anderung der Collection aus dem Feiertage-Array nicht möglichn
			LocalDate[] alleFeiertage = feiertage.getFeiertage2();
			for (LocalDate datum : alleFeiertage) {
				if (feiertage.istFeiertag2(datum.getDayOfMonth(), datum.getMonthValue())) {
					System.out.printf("OK, %s IST ein Feiertag%n", datum);
				} else {
					System.out.printf("FEHLER, %s sollte ein Feiertag sein%n",
							datum);
				}

				datum = datum.plusDays(2);

				if (feiertage.istFeiertag2(datum.getDayOfMonth(), datum.getMonthValue())) {
					System.out.printf("FEHLER, %s sollte KEIN Feiertag sein%n",
							datum);
				} else {
					System.out.printf("OK, %s IST KEIN Feiertag%n", datum);
				}
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

}
