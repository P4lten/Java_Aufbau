package bankverwaltung;

import java.time.LocalDate;

public class Gehaltskonto extends Bankkonto {
	private static final long serialVersionUID = 1L;

	private float zinsen;
	private int uez_rahmen;

	public Gehaltskonto(String name, LocalDate eröffungnsDatum, double kontostand, int uez_rahmen) {
		super(name, eröffungnsDatum, kontostand);
		this.zinsen = 0.1f;
		this.uez_rahmen = uez_rahmen;

	}

	public float getZinsen() {
		return zinsen;
	}

	public int getUez_rahmen() {
		return uez_rahmen;
	}

	public void setUez_rahmen(int uez_rahmen) {
		this.uez_rahmen = uez_rahmen;
	}

	public String toString() {

		return String.format("Kontonummer: " + getKontonr() + "\nInhaber: " + getName() + "\nAktueller Kontostand: "
				+ getKontostand());
	}

	@Override
	public void abheben(int kontonummer, double wert) {
		if ((getUez_rahmen() + getKontostand()) > wert && getKontonr() == kontonummer) {
			double erg = getKontostand();
			setKontostand(erg - wert);
		} else {
			System.out.printf("Das Gehaltskonto von %s mit der Kontonummer: %d hat nicht genügend Geld am Konto\n",
					getName(), getKontonr());
		}

	}

	@Override
	public void einzahlen(int kontonr, double wert) {
		if (wert < 15000 && getKontonr() == kontonr) {
			double erg = getKontostand();
			setKontostand(erg + wert);
		} else {
			System.out.println("Der Betrag darf 15.000€ nicht überschreiten");
		}

	}

	@Override
	public void kontoInfo(int kontoNr) {
		if(getKontonr() == kontoNr) {
			System.out.println("Inhaber: " + getName() + "\nKontotyp: Gehaltskonto" + "\nAktueller Kontostand: " + getKontostand());
		}else {
			System.out.println("Kontonummer existiert nicht");
		}

	}
}
