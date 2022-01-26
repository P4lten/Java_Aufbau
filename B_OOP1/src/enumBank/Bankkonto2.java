package enumBank;

public class Bankkonto2 {

	private static int kontonummer;

	
	private int kontostand;
	private String name;
	private float zinsen;
	private int uez_rahmen;
	private int kontonr;
	Kontotyp k;
	
	

	public Bankkonto2(String name, float zinsen) {
		kontonummer++;
		this.kontonr = kontonummer;
		this.name = name;
		this.k = Kontotyp.SPARKONTO;
		this.zinsen = zinsen;
	}

	public Bankkonto2(String name, int uez_rahmen) {
		// inkrement for zuweisung =1
		kontonummer++;
		this.kontonr = kontonummer;
		this.name = name;
		this.k = Kontotyp.GEHALTSKONTO;
		
		this.uez_rahmen = uez_rahmen;
	}
	
	public Bankkonto2() {
		kontonummer++;
		this.kontonr = kontonummer;
		
	};
	
	public void einzahlen(double wert) {
		if (wert > 15_000) {
			System.out.println("Betrag darf 15.000€ nicht überschreiten");
			return;
		}
		kontostand += wert;
	}

	public void abheben(double wert) {
		if( k.equals(Kontotyp.GEHALTSKONTO) && kontostand-wert < uez_rahmen) {
			kontostand -= wert;
		} else if (kontostand < 0 || wert > kontostand) {
			throw new IllegalArgumentException("Kontostand ist zu niedrig");
		
		} else {
			kontostand -= wert;
		}
	}

	public void ausgeben() {
		System.out.println("Kontonummer: " + kontonr + "\nInhaber: " + name + "\nKontotyp: " + k + "\nAktueller Kontostand: " + kontostand);
	}

}
