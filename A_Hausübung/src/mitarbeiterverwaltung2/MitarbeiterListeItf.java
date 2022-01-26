package mitarbeiterverwaltung2;

public interface MitarbeiterListeItf {

	void addMitarbeiter(Mitarbeiter m);

	void erhöheAlleGehalt(float pct);

	void erhoehungG(int maNummer, float pct);

	void anzeigen(int maNummer);

	void sortiertName();

	void sortiertNachTyp();

	Mitarbeiter abmelden(int maNummer);

	
	
}
