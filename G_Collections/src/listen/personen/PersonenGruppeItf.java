package listen.personen;

public interface PersonenGruppeItf {

	/**
	 * eine Person anmelden. Die Person wird im Array am nächsten feien Platz
	 * hinzugefügt
	 * @param p die Person, die angemeldet werden soll
	 */

	void anmelden(Person p);

	/**
	 * Eine Person anmelden. Es wird eine Persinobjekt erzeugt und im Array am
	 * nächsten freien Platz hinzugefügt
	 * 
	 * @param name der Name der Person
	 * @param alter das Alter der Person
	 */

	void anmelden(String name, int alter);

	void alleAnzeigen();

	void anzeigen(int nr);

	/**
	 * eine Person abmelden. Die Person wird aus dem Array entfernt. Dahinterliegende Objekte werden
	 * nach vorne verschoben
	 * @param nr die Nummer Person die abgemeldet wird
	 * @return das Person-Objekt
	 */

	Person abmelden(int nr);
	
	Person suchen(int nr);

}