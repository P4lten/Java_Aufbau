package geoformen;

public class MainGeoFormen {

	public static void main(String[] args) {

		GeoForm geoform[] = new GeoForm[] { new Kreis(10.5), new Rechteck(20.5, 30.5), };

		for (GeoForm f : geoform) {
			System.out.printf("Die Form ist ein %s\n", f.getClass().getSimpleName());
			System.out.printf("Fläche: %.2f; Umfang: %.2f%n%n", f.calcFlaeche(), f.calcUmfang());
		}
		
		
	}

	

}
