package oop1;

public class Bankkonto {
//	Klasse Bankkonto
//	- (eindeutige) kontonummer: int
//	- kontostand: double
//	- name: String
//	- kontotyp: String
//	- zinsen: float
//	- überziehungsrahmen: int
//
//	Methoden
//	+ createSparbuch(String, float, double): void
//	+ createGehaltskonto(String, int/double, int): void
//	+ einzahlen(double): double/void
//	+ abheben(double): double/void
//	+ ausgabe(): void
//	[+ ueberweisung(Bankkonto, Bankkonto,double)]
	
	private int kontonummner;
	private int kontostand;
	private String name;
	private String kontotyp;
	private float zinsen;
	private int uez_rahmen;
	
	
	public void createSparbuch(int kontonummner, String name, String kontotyp, float zinsen) {
		
		this.kontonummner = kontonummner;
		this.name = name;
		this.kontotyp = kontotyp;
		this.zinsen = zinsen;
	}
	
	public void createGehaltskonto(int kontonummner, String name, String kontotyp, int uez_rahmen) {

		this.kontonummner = kontonummner;
		this.name = name;
		this.kontotyp = kontotyp;
		this.uez_rahmen = uez_rahmen;
		
		
	}
	
	public void einzahlen(double wert) {
		if (wert > 15_000) {
			System.out.println("Betrag darf 15.000€ nicht überschreiten");
			return;
		}
		kontostand += wert;
	}
	
	public void abheben(double wert) {
		if (kontotyp.equals("Gehaltskonto")  &&  wert < uez_rahmen ) {
			kontostand -= wert; 
		}else if (kontostand < 0 || wert > kontostand) {
			System.out.println("Kontostand ist zu niedrig");
			return;
		}else {kontostand -= wert;}
	}
	
	public void ausgeben() {
		System.out.println("Inhaber: " + name + "\nKontotyp: " + kontotyp + "\nAktueller Kontostand: " + kontostand);
	}
	
	
}
