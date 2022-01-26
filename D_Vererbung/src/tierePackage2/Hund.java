package tierePackage2;

/**
 * Klasse Hund erbt von Haustier
 * @author Paltenberg
 *
 */

public class Hund extends Haustier{
	
	private int gewicht;
	
	public Hund(String kName, int gewicht) {
		// Default- Konstrukto der Basisklasse steht nicht zur verfügung
		// -> impliezieter super-Aufruf ist nicht möglich
		// super();
		super(kName); // den Namen an die Basisklasse
		this.gewicht = gewicht; //das gewicht selber verarbeiten
		System.out.println("Konstruktor von Hund");
	}
	
	/**
	 * getter für Eigenschaft gewicht
	 * @return
	 */
	public int getGewicht() {
		return gewicht;
	}
	
	/**
	 * setter für Eigenschaft gewicht
	 * @param gewicht
	 */
	public void setGewicht(int gewicht) {
		this.gewicht = gewicht;
	}
	
	// nicht erlaubt weil final
//	public String getKosename() {
//		return "xxx";
//	}
	
	/**
	 * der Hund gibt ein Bellen von sich
	 */
	public void belle() {
		System.out.printf("%s macht wau wau\n", getKosename());
	}
	
	@Override
	public void zeigeDich() {
//		System.out.println("Hey, mein Name ist " + getKosename());
		// BAsisklassen-Implementierung aufrufen
		super.zeigeDich();
		System.out.printf("Ich bin ein Hund und habe %d kg\n" , getGewicht());
	}
	
	@Override
	public void gibEinenLautVonDir() {
		belle();
	}

	@Override
	public void bewegDich() {
		System.out.printf("%s wedelt mit dem Schwanz\n" , getKosename());
		
	}
	
	@Override
	public String toString() {
		
		return " %s, Gewicht= %d".formatted(super.toString(), getGewicht());
	}
}
