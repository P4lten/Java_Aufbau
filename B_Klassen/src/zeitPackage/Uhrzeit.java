package zeitPackage;

public class Uhrzeit {
	// Attribute (Feld, Membervariable) für Stunde und Minute
	// enthalten den Status eines Uhrzeit-Objekts
	private int m_stunde, m_minute;

	// Methode um neue Werte ins Objekt zu schreiben
	public void setzen(int stunde, int minute) {
		try {
			if (stunde < 0 || stunde >= 24) {
//			System.out.println("Ungültiger Wert für Stunde " + stunde);
				throw new IllegalArgumentException("Ungültiger Wert für Stunde " + stunde);
			}

			if (minute < 0 || minute >= 60) {
//			System.out.println("Ungültiger Wert für Minute " + minute);
				throw new IllegalArgumentException("Ungültiger Wert für Minute \n" + minute);
			}

			// die beiden Werte ins Objekt übernehmen
			m_stunde = stunde;
			m_minute = minute;
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	// die Uhrzeit an der konsole anzeigen
	public void anzeigen() {
		System.out.printf("%02d:%02d", m_stunde, m_minute);
	}

	// Lesezugriff auf Atribute über Hilfsmethoden:
	// get-Methode (=getter) für die Stunde
	public int getStunde() {
		return m_stunde;
	}

	// get-Methode für die Minute
	public int getMinute() {
		return m_minute;
	}

	public int calcDifferenz(Uhrzeit zeit2) {
		int minuten = m_stunde * 60 + m_minute;
		int minuten2 = zeit2.m_stunde * 60 + zeit2.m_minute;
		return minuten2 - minuten;
	}

}
