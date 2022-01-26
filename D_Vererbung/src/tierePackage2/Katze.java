package tierePackage2;

public class Katze extends Haustier {

	private String spielzeug;

	public Katze(String name, String spielzeug) {
		super(name);
		this.spielzeug = spielzeug;
		System.out.println("Konstruktor von Katze");
	}

	public String getSpielzeug() {
		return spielzeug;
	}
	
	public void setSpielzeug(String spielzeug) {
		this.spielzeug = spielzeug;
	}
	
	@Override
	public void zeigeDich() {
		
		super.zeigeDich();
		System.out.printf("Ich bin eine Katze und spiele gerne mit %s \n", spielzeug);
	}
	
	// eigene Funktionalität
	public void miaue() {
		System.out.printf("%s macht miauuuuu!\n", getKosename());
	}

	//Implementierung der abstraken Methoden ist Pflicht, wenn
	//Die Klasse nicht selbst auch abstract ist
	@Override
	public void gibEinenLautVonDir() {
		// die eigene Methode aufrufen
		miaue();		
	}

	@Override
	public void bewegDich() {
		System.out.printf("%s macht einen Katzenbuckel\n" , getKosename());
		
	}
	
	@Override
	public String toString() {
		
		return " %s, Spielzeug= %s".formatted(super.toString(), getSpielzeug());
	}
	
}
