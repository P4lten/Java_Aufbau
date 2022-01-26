package tierePackage;

public class Dackel extends Hund {

	public Dackel(String kName, int gewicht) {
		super(kName, gewicht);
		System.out.println("Konstruktor von Dackel");
	}

	@Override
	public void belle() {
		// Kein Aufruf der Basisklasse, wir möchten die Methode ganz neu schreiben
		// super.belle();
		System.out.printf("Dackel %s macht wuff wuff wuff\n", getKosename());
	}
}
