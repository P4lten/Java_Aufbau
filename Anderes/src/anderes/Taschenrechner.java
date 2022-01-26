	package anderes;

	import java.util.Scanner;

	public class Taschenrechner {

		static Scanner input = new Scanner(System.in);
		
		public static void main(String[] args) {
			System.out.println("Bitte abwechselnd Zahlen und einen Operator (+ - / * %) eingeben");
			
			int ergebnis = input.nextInt();
			char op;
//			op = input.next().charAt(0);
			// solange nicht das ergebnisszeichen eingegeben wurde
			while((op = input.next().charAt(0)) != '=') {
				int zahl = input.nextInt();
				switch (op) {
				case '+' -> ergebnis +=zahl; 
				case '-' -> ergebnis -=zahl; 
				case '*' -> ergebnis *=zahl; 
				case '/' -> ergebnis /=zahl; 
				case '%' -> ergebnis %=zahl; 
				
				default ->
					throw new IllegalArgumentException("Unexpected value: " + op);
				}
//				op = input.next().charAt(0);
			}
			
			System.out.println(ergebnis);
			
			

			input.close();
		}
		
		
		
		
		

	}

	
