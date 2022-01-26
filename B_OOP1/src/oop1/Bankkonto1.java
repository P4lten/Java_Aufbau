package oop1;

public class Bankkonto1 {
//		Klasse Bankkonto
//		- (eindeutige) kontonummer: int
//		- kontostand: double
//		- name: String
//		- kontotyp: String
//		- zinsen: float
//		- überziehungsrahmen: int
	//
//		Methoden
//		+ createSparbuch(String, float, double): void
//		+ createGehaltskonto(String, int/double, int): void
//		+ einzahlen(double): double/void
//		+ abheben(double): double/void
//		+ ausgabe(): void
//		[+ ueberweisung(Bankkonto, Bankkonto,double)]

	private static int kontonummer;
	final private String kontotyp;
	
	private int kontostand;
	private String name;
	private float zinsen;
	private int uez_rahmen;
	private int kontonr;
	
	
	

	public int getKontostand() {
		return kontostand;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKontonr() {
		return kontonr;
	}

	public void setKontonr(int kontonr) {
		this.kontonr = kontonr;
	}

	public String getKontotyp() {
		return kontotyp;
	}

	public Bankkonto1(String name, String kontotyp, float zinsen) {
		kontonummer++;
		this.kontonr = kontonummer;
		this.name = name;
		this.kontotyp = kontotyp;
		this.zinsen = zinsen;
		this.kontostand = 500;
	}

	public Bankkonto1(String name, String kontotyp, int uez_rahmen) {
		// inkrement for zuweisung =1
		kontonummer++;
		this.kontonr = kontonummer;
		this.name = name;
		this.kontotyp = kontotyp;
		this.uez_rahmen = uez_rahmen;
	}
	
	public Bankkonto1() {
		kontonummer++;
		this.kontonr = kontonummer;
		this.kontotyp = "";
	};
	
	public void einzahlen(double wert) {
		if (wert > 15_000) {
			throw new IllegalArgumentException("Wert darf 15.000 nicht überschreiten");
			
		}
		kontostand += wert;
	}

	public void abheben(double wert) {
		if (kontotyp.equals("Gehaltskonto") && (kontostand - wert) *-1 < uez_rahmen) {
			kontostand -= wert;
		} else if (kontostand < 0 || wert > kontostand) {
			throw new IllegalArgumentException("Kontostand zu niedrig");
		} else {
			kontostand -= wert;
		}
	}

	public String toString() {
		double rnd = Math.random() * 99;
		if (kontonr > 99) {
			return ("Kontonummer: AT" + (int)rnd + " 35056 0000 0000 0" + kontonr + "\nInhaber: " + name + "\nKontotyp: " + kontotyp + "\nAktueller Kontostand: " + kontostand + "\n");
		}else if (kontonr > 9){
			return ("Kontonummer: AT" + (int)rnd + " 35056 0000 0000 00" + kontonr + "\nInhaber: " + name + "\nKontotyp: " + kontotyp + "\nAktueller Kontostand: " + kontostand + "\n");
	
		}
		return ("Kontonummer: AT" + (int)rnd + " 35056 0000 0000 000" + kontonr + "\nInhaber: " + name + "\nKontotyp: " + kontotyp + "\nAktueller Kontostand: " + kontostand + "\n");
	}

}
