package geoformenAbstrakt;

public class MainGeoFormen {

	public static void main(String[] args) {

		System.out.println("Formendemo mit abstrakter Klasse");

		GeoForm geoform[] = new GeoForm[] { 
				new Kreis(5, 3, 10.5), //Umwandlung von Rechteck nach Interface GeoForm
				new Rechteck(1,-7, 20.5, 30.5),//Umwandlung von Rechteck nach Interface GeoForm
				};

		for (GeoForm f : geoform) {
			System.out.printf("Die Form ist ein %s\n", f.getClass().getSimpleName());
			System.out.println(f);
			System.out.printf("Fläche: %.2f; Umfang: %.2f%n%n", f.calcFlaeche(), f.calcUmfang());
		}
	
		
		System.out.println("Formendemo beendet");
		
	}

	

}
