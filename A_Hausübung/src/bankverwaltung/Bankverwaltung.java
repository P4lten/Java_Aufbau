/**

    Zinsberechnung (optional): Der Benutzer gibt die Kontonummer an. Anschließend werden die Zinsen seit der letzten Zinsgutschrift berechnet.
    Zinsgutschrift (optional): Der Benutzer gibt die Kontonummer an. Dann werden die Zinsen seit der letzten Zinsgutschrift berechnet und dem Konto gutgeschrieben.
    Transaktionsliste für einen Zeitraum anzeigen: Der Benutzer gibt den Zeitraum ein (Datum von, Datum bis). Alle Transaktionen die im Zeitraum durchgeführt wurden, werden aufgelistet.
    Transaktionsliste für ein Konto anzeigen: Der Benutzer gibt die Kontonummer an. Alle Transaktionen, welche für das Konto durchgeführt wurden, werden aufgelistet.
    
	Die Methoden sind sich zeitlich leider nicht mehr ausgegangen
  
 **/

package bankverwaltung;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Bankverwaltung implements Serializable {

	private static final long serialVersionUID = 1L;
	private Map<Integer, Bankkonto> konten = new TreeMap<>();
	private List<String> tListe = new ArrayList<>();

	private String fileName;
	private String fileName2;

	public Bankverwaltung(String fileName, String fileName2) {
		this.fileName = fileName;
		this.fileName2 = fileName2;
	}

	public Map<Integer, Bankkonto> getBankkonto() {
		return konten;
	}

	public List<String> getTListe() {
		return tListe;
	}

	public void anmelden(Bankkonto b) {
		konten.put(b.getKontonr(), b);
		anmeldenList(b);
	}

	public void anmeldenList(Bankkonto b) {
		tListe.add("Konto von " + konten.get(b.getKontonr()).getName() + " wurde eröffnet");
	}

	public void schließen(int kontoNummer) {
		try {
			if (konten.get(kontoNummer).getKontostand() > 5) {
				System.out.println("Folgendes Konto wird gelöscht");
				kontoInfo(kontoNummer);
				konten.remove(kontoNummer);
			} else {
				System.out.println("Kontostand ist zu niedrig!");
			}
		} catch (Exception e) {
			System.out.println("Konto existiert nicht!" + e);
		}
	}

	public void kontoInfo(int kontoNummer) {

		try {
			konten.get(kontoNummer).kontoInfo(kontoNummer);
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			System.out.println("Konto existiert nicht");
		}
	}

	public void alleAnzeigen() {
		konten.values().forEach(System.out::println);
	}
	
	public void listePerPerson() {
		
	}

	public void anzeigenListe() {
		for (int i = 0; i < tListe.size(); i++) {
			String eintrag = tListe.get(i);
			System.out.println(eintrag);
		}
	}

	public void einzahlen(int kontonummer, double wert) {
		konten.get(kontonummer).einzahlen(kontonummer, wert);
		einzahlenList(kontonummer, wert);

	}

	public void einzahlenList(int kontonummer, double wert) {
		tListe.add(konten.get(kontonummer).getName() + " hat Geld in Höhe von " + wert + "€ eingezahlt");
	}

	public void abheben(int kontonummer, double wert) {
		konten.get(kontonummer).abheben(kontonummer, wert);
		abhebenList(kontonummer, wert);
	}

	public void abhebenList(int kontonummer, double wert) {
		tListe.add(konten.get(kontonummer).getName() + " hat Geld in Höhe von " + wert + "€ abgehoben");
	}

	public void ueberweisung(int koNrSender, int koNrEmpfänger, double betrag) {
		abheben(koNrSender, betrag);
		einzahlen(koNrEmpfänger, betrag);
		ueberweisungList(koNrSender, koNrEmpfänger, betrag);
	}

	public void ueberweisungList(int koNrSender, int koNrEmpfänger, double betrag) {
		tListe.add(konten.get(koNrSender).getName() + " hat Geld in Höhe von " + betrag + "€ an "
				+ konten.get(koNrEmpfänger).getName() + " überwiesen");
	}

	public void loadData() throws ClassNotFoundException, IOException {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {

			// das Bankkonto-Objekt lesen
			Object obj1 = ois.readObject();
			konten = (Map<Integer, Bankkonto>) obj1;

			System.out.printf("%d Bankkonten geladen \n", konten.size());
			// den nextNr lesen(int-Wert binär lesen)
			Bankkonto.setNextKtNr(ois.readInt());

		}
	}

	public void loadData2() throws ClassNotFoundException, IOException {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName2))) {

			// das Bankkonto-Objekt lesen
			Object obj1 = ois.readObject();
			tListe = (List<String>) obj1;

			System.out.printf("%d Transaktionen werden geladen \n", tListe.size());
			// den nextNr lesen(int-Wert binär lesen)
			Bankkonto.setNextKtNr(ois.readInt());

		}
	}

	public void saveData() throws IOException {

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
			// das Konto-Objekt in den Stream schreiben
			oos.writeObject(konten);
//			// weitere Daten schreiben
//			oos.writeObject("Alle Daten wurden gespeichert");
			// und die akt. nextNr ins File schreiben
			oos.writeInt(Bankkonto.getNextKtNr());

			System.out.println("Serialisierung erfolgreich!");

		}
	}

	public void saveData2() throws IOException {

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName2))) {
			// das Listen-Objekt in den Stream schreiben
			oos.writeObject(tListe);
//			// weitere Daten schreiben
//			oos.writeObject("Alle Daten wurden gespeichert");
			// und die akt. nextNr ins File schreiben
			oos.writeInt(Bankkonto.getNextKtNr());

			System.out.println("Serialisierung erfolgreich!");

		}
	}

}