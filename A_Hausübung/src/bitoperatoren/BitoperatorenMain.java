package bitoperatoren;

public class BitoperatorenMain {

	public static void main(String[] args) {

		// Statt fixe Werte die Konstanten verwenden
		// Wetterbits wetter = new Wetterbits("Wien", LocalDate.now(), (byte) (2 | 1 |
		// 4));
		Bitoperatoren permission1 = new Bitoperatoren("Other",
				(byte) (Bitoperatoren.EXECUTEOTHER | Bitoperatoren.READOTHER | Bitoperatoren.WRITEOTHER));
		System.out.print(permission1.toString());
		Bitoperatoren permission2 = new Bitoperatoren("Group",
				(byte) (Bitoperatoren.EXECUTEGROUP | Bitoperatoren.READGROUP | Bitoperatoren.WRITEGROUP));
		System.out.print(permission2.toString());
		Bitoperatoren permission3 = new Bitoperatoren("User",
				(byte) (Bitoperatoren.EXECUTEUSER | Bitoperatoren.READUSER | Bitoperatoren.WRITEUSER));
		System.out.print(permission3.toString());

		System.out.println();
		System.out.println("User|Group|Other");
		permission1.bitsEntfernen((byte) (Bitoperatoren.EXECUTEOTHER|Bitoperatoren.WRITEOTHER|Bitoperatoren.READOTHER ));
		
		System.out.print(permission1.toString()+ permission2.toString()+permission3.toString());

		permission1.bitsHinzu((byte) (Bitoperatoren.EXECUTEOTHER|Bitoperatoren.WRITEOTHER|Bitoperatoren.READOTHER ));

		System.out.println();
		System.out.println("User|Group|Other");
		System.out.print(permission1.toString()+ permission2.toString() +permission3.toString());
		
		System.out.println();
		System.out.println("User|Group|Other");
		permission2.bitsEntfernen((byte) Bitoperatoren.EXECUTEGROUP);
		System.out.print(permission1.toString()+ permission2.toString() +permission3.toString());
		
		System.out.println();
		System.out.println("User|Group|Other");
		permission3.bitsEntfernen((byte) Bitoperatoren.EXECUTEGROUP);
		System.out.print(permission1.toString()+ permission2.toString() +permission3.toString());
		
	}

}
