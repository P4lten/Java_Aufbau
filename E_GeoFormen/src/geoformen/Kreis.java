package geoformen;

public class Kreis extends GeoForm {

	private final double PI = Math.PI;
	private double radius;

	/**
	 * @param radius
	 */
	public Kreis(double radius) {
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
		return String.format("Kreis (r=%d) ", radius);
	}
}
