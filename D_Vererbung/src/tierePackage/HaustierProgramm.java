package tierePackage;

public class HaustierProgramm {

	public static void main(String[] args) {
		// Hund-Objekt erzeugen
		Hund rex = new Hund("Rex", 25);
		//Methoden der Basisklasse
		
		rex.setKosename("Rex");
		rex.zeigeDich();
		
		// Methoden der abgeleiteten Klasse
//		rex.setGewicht(25);
		rex.belle();

		// Typ-Kompatibilit
		Haustier einTier;
		// Basisklassen-Referenz verweist auf Objekt der abgeleiteten Klasse
		// es wird nur die Referenz umgewandelt, nicht das Objekt
		einTier = rex;
		einTier.zeigeDich();
		System.out.println("Kosename: " + einTier.getKosename());
	}

}
