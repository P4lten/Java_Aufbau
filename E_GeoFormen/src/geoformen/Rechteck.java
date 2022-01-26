package geoformen;

public class Rechteck extends GeoForm {

	private double laenge;
	private double breite;

	/**
	 * @param laenge
	 * @param breite
	 */
	public Rechteck(double laenge, double breite) {
		this.laenge = laenge;
		this.breite = breite;
	}

	@Override
	public double calcFlaeche() {
		double flaeche = laenge * breite;
		return flaeche;
	}

	@Override
	public double calcUmfang() {
		double umfang = (2 * laenge) + (2 * breite);
		return umfang;
	}

	public String toString() {

		return String.format("Rechteck (l=%d, b=%d) ", laenge, breite);
	}
}
