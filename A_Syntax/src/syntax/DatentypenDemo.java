package syntax;

public class DatentypenDemo {

	public static void main(String[] args) {
		
		//Suffix L ist Plicht, weil die zahl nicht als int dargestellt werden kann -> zu groß
		long lWert = 10_000_000_000l;
		System.out.println("Long Wert: " + lWert);
		
		//Suffix L ist optional weil int darstellen kann
		long lWert2 = 10_000;
		System.out.println("LongWert2: " + lWert2);
		
		//Suffix F ist Pflicht, weil Wert sonst vom Typ double ist
		float fWert = 234.567f;
		System.out.println("FloatWert: " + fWert);
		
		int iWert = 123456789;
		System.out.println("IntWert: " + iWert);
		
		
		//hier geht Genauigkeit verloren
		//Implizite Typumwandlung von int nach float -> (float) = explizite umwandlung
		float fWert2 = iWert;
	
		System.out.println("Float Wert2: " + fWert2);
		
		//Escape-Sequenz für Unicode zeichen
		System.out.println("Alpha: \u03B1");
		
		var text = "Hallo Welt";
		System.out.println(text);
		
		String info = iWert % 2 == 0 ? "gerade" : "ungerade";
		System.out.println(iWert + " ist eine " + info + " Zahl");
		System.out.printf("%d als float ist %.1f\n", iWert, fWert2);
		System.out.printf("%.1f resultiert aus %d\n",fWert2 , iWert);
		
	}

}
