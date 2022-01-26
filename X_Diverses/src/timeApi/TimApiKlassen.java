package timeApi;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class TimApiKlassen {

	public static void main(String[] args) {

		// letzter Tag Sommerzeit im Jahr 2020
		LocalDateTime start1 = LocalDateTime.of(2020, 10, 24, 10, 30);
		// erste Tag Winterzeit im Jahr2020
		LocalDateTime ende1 = LocalDateTime.of(2020, 10, 25, 10, 30);
		System.out.printf("Local: %s bis %s\n", start1, ende1);
		// Zeitspanne zwischen den beiden Werten
		Duration span1 = Duration.between(start1, ende1);
		System.out.printf("Zeitspanne dazwischen: %s\n",span1);
		
		// zu unserem Start sieben tage dazuzählen
		LocalDateTime date1 = ChronoUnit.DAYS.addTo(start1, 7); 
		System.out.printf("Plus 7 Tage: %s\n",date1);

		
		
		// das gleiche Datum mit Zeitzone
		ZonedDateTime start2 = ZonedDateTime.of(2020, 10, 24, 10, 30, 0, 0, ZoneId.systemDefault());
		ZonedDateTime ende2 = ZonedDateTime.of(2020, 10, 25, 10, 30, 0, 0, ZoneId.systemDefault());
		System.out.printf("Mit Zeitzone: %s bis %s \n", start2, ende2);
		Duration span2 = Duration.between(start2, ende2);
		System.out.printf("Zeitspanne dazwischen: %s\n",span2);

		ZonedDateTime date2 = ChronoUnit.DAYS.addTo(start2, 7); 
		System.out.printf("Plus 7 Tage: %s\n",date2);
		
		ZonedDateTime date3 = ChronoUnit.HOURS.addTo(start2, 24); 
		System.out.printf("Plus 7 Stunden: %s\n",date3);
		
	}

}
