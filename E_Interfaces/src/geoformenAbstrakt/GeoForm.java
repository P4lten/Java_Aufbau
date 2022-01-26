package geoformenAbstrakt;

abstract public class GeoForm {

	// in einer abstrakten Klasse durfen wir Attribute definieren
	private int x, y;
	
	// in einer abstrakten Klasse dürfen wir Konstruktoren definieren
	public GeoForm(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	// in einer abstrakten Klasse dürfen wir belibige Methoden definieren
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	
	// ermitteln der Fläche
	abstract public double calcFlaeche();
	
	// ermitteln des Umfangs
	abstract public double calcUmfang();

	@Override
	public String toString() {
		return "Position %d/%d".formatted(x,y);
	}
}
