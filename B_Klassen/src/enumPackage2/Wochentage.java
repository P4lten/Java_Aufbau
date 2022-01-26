package enumPackage2;

public enum Wochentage {
	// es gibt nur diese 7 Objekte weitere koennen nicht erzeugt werden
	//Die Referenzen sind alle public, static und final
	MONTAG(1), DIENSTAG(2) , MITTWOCH(3), DONNERSTAG(4), FREITAG(5), SAMSTAG(6), SONNTAG(7);
	
	//Instanz-Attribut für die Nummer von jedem Objekt
	private int tagesNummer;
	
	private Wochentage(int tNr) {
		tagesNummer = tNr;
	}
	
	public int getTagesNummer() {
		return this.tagesNummer;
	}
	
	
	
	public boolean isWochenende() {
		//testen ob das aktuelle Objekt ein Wchentag ist
		// Wochentage aktTag = this;
		switch (this) {
		// Samstag und Sonntag true
		case SAMSTAG, SONNTAG:
			return true;
		// alle anderen tage false
		default:
			return false;
		}
	}
}
// Der Compiler erzeugt daraus in etwa so eine Klasse: 
//class WTag_Temp{
//	public static final WTag_Temp MMONTAG = new WTag_Temp(),
//			DIENSTAG = new WTag_Temp(),
//			MITTWOCH = new WTag_Temp()
//			//....
//			;
//
//}