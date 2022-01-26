package geoformenAbstrakt;

public class Kreis extends GeoForm {

	private final double PI = Math.PI;
	private double radius;

	/**
	 * @param radius
	 */
	public Kreis(int x, int y, double radius) {
		super(x,y);
		this.radius = radius;
	}

	@Override
	public double calcFlaeche() {
		double flaeche = radius * radius * PI;
		return flaeche;

	}

	@Override
	public double calcUmfang() {
		double umfang = 2 * radius * PI;
		return umfang;
	}

	public String toString() {
		return String.format("Kreis (r=%f) , %s", radius, super.toString());
	}
}
