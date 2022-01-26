package mathPackage;

public class MathFunktion {

	public static void main(String[] args) {

		int a, b, c;
		a = 3;
		b = 4;
		c = 5;
		// pow-Methode zum Prüfen ob rechtwinkliges Dreieck (Pythagoras)

		if ((a ^ 2) + (b ^ 2) == (c ^ 2)) {
			System.out.println("Was immer wir damit testen, ^ ist der XOR Operator");
		}

		if ((a * a) + (b * b) == (c * c)) {
			System.out.println("Es ist ein rechtwinkeliges Dreieck!");
		}else {
			System.out.println("Es ist KEIN rechtwinkeliges Dreieck!");
		}
		
		if (Math.pow(a, 2) + Math.pow(b, 2) == Math.pow(c, 2)){
			System.out.println("Es ist ein rechtwinkeliges Dreieck!");
		}else {
			System.out.println("Es ist KEIN rechtwinkeliges Dreieck!");
		}

	}

}
