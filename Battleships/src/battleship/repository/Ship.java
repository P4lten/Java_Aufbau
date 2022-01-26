package battleship.repository;

public class Ship {

	
	public int type;
	public boolean vertical = true;

	
	private int health;

	/**
	 * @param type der type is die länge und zugleich die Lebenspunkte des Schiffes
	 * @param vertical ob das Schiff vertikal oder horizontal plaziert wird
	 */
	public Ship(int type, boolean vertical) {
		super();
		this.type = type;
		this.vertical = vertical;
		health = type;
		
	}
	
	public void hit() {// methode um das leben der schiffe zu veringern
		health--;
	}
		
	public boolean isAlive() {// ist schiff noch am leben?
//		System.out.println("Leben = " + health);
//		System.out.println(health > 0);
		return health > 0;
	}
}
