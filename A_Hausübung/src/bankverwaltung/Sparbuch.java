package bankverwaltung;

import java.time.LocalDate;
import java.util.Scanner;

public class Sparbuch extends Bankkonto {

	private static final long serialVersionUID = 1L;
	private float zinsen;

	public Sparbuch(String name, LocalDate eröffungnsDatum, double kontostand, float zinsen) {
		super(name, eröffungnsDatum, kontostand);
		System.out.println("Sparbuch mit der Kontonummer: " + getKontonr());
		Scanner input = new Scanner(System.in);
		this.zinsen = zinsen;
		System.out.println("Wollen Sie eine ersteinlage hinzufügen? j/n");
		String str = input.nextLine();
		if (str.equals("j")) {
			System.out.println("Bitte höhe der Ersteinlage eingeben");
			setKontostand(input.nextDouble());
			System.out.println("Kontostand bertägt: " + getKontostand());
		} else {
			System.out.println("es wurde keine Ersteinlage hinzugefügt");
		}
		input.close();
	}

	public float getZinsen() {
		return zinsen;
	}

	public void setZinsen(float zinsen) {
		this.zinsen = zinsen;
	}

	@Override
	public void abheben(int kontonummer, double wert) {
		if (getKontostand() > 0 && getKontonr() == kontonummer) {
			double erg = getKontostand();
			setKontostand(erg - wert);
		} else {
			System.out.printf("Das Sparkonto von %s mit der Kontonummer: %d hat nicht genügend Geld am Konto", getName(),
					getKontonr());
		}

	}

	@Override
	public void einzahlen(int kontonr, double wert) {
		if (wert < 15000 && getKontonr()==kontonr) {
			double erg = getKontostand();
			setKontostand(erg + wert);
		}else {
			System.out.println("Der Betrag darf 15.000€ nicht überschreiten");
		}
		
	}


	@Override
	public void kontoInfo(int kontoNr) {
		if(getKontonr() == kontoNr) {
			System.out.println("Inhaber: " + getName() + "\nKontotyp: Sparkonto" + "\nAktueller Kontostand: " + getKontostand());
		}else {
			System.out.println("Kontonummer existiert nicht");
		}}
	
	public String toString() {
		
		return String.format("Kontonummer: " + getKontonr() + "\nInhaber: " + getName() + "\nAktueller Kontostand: "
				+ getKontostand());
	}
}
