package tierePackage;

/**
 * Basisklasse für verschiedene Arten von Haustier
 * @author Paltenberg
 *
 */

public class Haustier {

	private String kosename;
	
	
	public Haustier(String name) {
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
	
}
