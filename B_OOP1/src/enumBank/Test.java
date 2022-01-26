package enumBank;

public class Test {

	public static void main(String[] args) {

		
		Bankkonto2 kontoSpar = new Bankkonto2("Hannes", 0.5f);
		Bankkonto2 kontoGehalt = new Bankkonto2("Ulrich", 10_000);
		
	
		
		kontoSpar.einzahlen(100);
		kontoGehalt.einzahlen(500);
		
		System.out.println();
		
		kontoSpar.abheben(100);
		kontoGehalt.abheben(10_000);
		// abheben methode funktioniert nicht mehr
		// wahrscheinlich wegen if (k.equals(Kontotyp.GEHALTSKONTO) &&...
		
		kontoSpar.ausgeben();
		System.out.println();
		kontoGehalt.ausgeben();
		
	}

}
