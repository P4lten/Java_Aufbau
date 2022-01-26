package anderes;

import java.util.Scanner;

public class SwitchOrIf {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);

		System.out.println("Welcher Operator?");
		String operator = input.nextLine();

		switch (operator) {
		case "+" -> System.out.println("switch: Addition");
		case "-" -> System.out.println("switch: Subraktion");
		default -> System.out.println("switch: kein Operator erkannt");
		}

		if (operator.equals("+") ) {
			System.out.println("if: Addition");
		} else if (operator == "-") {
			System.out.println("if: Subtraktion");
		} else {
			System.out.println("if: kein Operator erkannt");
		}
		
		input.close();
	}

}
