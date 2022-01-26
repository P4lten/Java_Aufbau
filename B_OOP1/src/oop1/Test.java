package oop1;

public class Test {

	private static Bankverwaltung konten = new Bankverwaltung();

	public static void main(String[] args) {

		
		konten.anmelden(new Bankkonto1 ("Peter", "Gehaltskonto", 10_000));
		konten.anmelden(new Bankkonto1 ("Thomas", "Sparkonto", 0.5f));
		konten.anmelden(new Bankkonto1 ("Alex", "Gehaltskonto", 5_000));
		konten.anmelden(new Bankkonto1 ("Victoria", "Sparkonto", 0.7f));
		konten.anmelden(new Bankkonto1 ("Valentin", "Gehaltskonto", 11_000));
		

		
		
		
		

		
		

		konten.alleAnzeigen();
		
		
		konten.ueberweisung(1, 2, 500);
		
		System.out.println("Nach überweisung\n");
		konten.alleAnzeigen();
	
		konten.schließen(2);
		konten.alleAnzeigen();
		
		
	

	}

	
}