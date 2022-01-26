package geoformenInterface;

public abstract interface GeoForm {

	// im Interface dürfen wir keine Attribute definieren
	// Konstante (public static final....) wäre erlaubt
	// public int x, y;

	// Konstruktor ist nicht erlaubt
//	 public GeoForm() {
//		 
//	 }

	// Seit Java 8 sind statische und default-Methoden erlaubt
	static void doSomething() {
		System.out.println("statische Methode in einem Interface");
	}
	
	abstract public double calcFlaeche();

	abstract public double calcUmfang();

}
