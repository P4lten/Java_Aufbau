package tierePackage2;

/**
 * Basisklasse für verschiedene Arten von Haustier
 * @author Paltenberg
 *
 */

	abstract public class Haustier {

	private String kosename;
	
	// protected: nur abgeleitete Klassen (und Klassen im selben Package) sehen diesen Konstruktor
	protected Haustier(String name) {
		kosename = name;
		System.out.println("Konstruktor von Haustier");
	}
	
	
	/**
	 * getter für die Eigenschaft kosename
	 * @return
	 */
	public String getKosename() {
		return kosename;
	}
	
	/**
	 * setter für die Eigenschaft kosename
	 * @param kosename
	 */
	public void setKosename(String kosename) {
		this.kosename = kosename;
	}
	
	/**
	 * Anzeigen von Informationen zum Haustier
	 */
	public void zeigeDich() {
		System.out.printf("Hallo, mein Name ist %s. \n", kosename );
	}
	
//	public void gibEinenLautVonDir() {
//		System.out.println("??? welches Geräusch soll ich machen");
//	}
	
	abstract public void gibEinenLautVonDir();
		
	
	abstract public void bewegDich();
		
	@Override
	public String toString() {
		// statt
		// return String.format("%s: Kosename= %s",getClass().getSimpleName(), getKosename());
		return "%s: Kosename= %s".formatted(getClass().getSimpleName(), getKosename());
	}
	
	
}
