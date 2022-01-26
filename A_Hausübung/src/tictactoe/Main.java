package tictactoe;

import java.util.Random;

public class Main {

	public static void main(String[] args) {
		
		System.out.println('-' + 'X' + '-');

		TicTacToe game = new TicTacToe();
		int zuege = 0;
		Random random = new Random();
		int spielbeginn = random.nextInt(10);
		// per Zufall wird bestimmt welcher Spieler beginnt
		if (spielbeginn < 5) {
			System.out.println("Spieler 1 beginnt\n");
			do {
				game.showBoard();
				game.setzenSpieler1();
				zuege++;
				game.showBoard();
				if (game.resultGameBoolean()) {
					break;
				}
				if (zuege >=9) {
					break;
				}
				game.setzenSpieler2();
				zuege++;
				
				if (game.resultGameBoolean()) {
					game.showBoard();
					break;
				}
			} while (zuege < 9);
			System.out.println(game.resultGame());

		} else {
			System.out.println("Spieler 2 beginnt\n");
			do {
				game.showBoard();
				game.setzenSpieler2();
				zuege++;
				game.showBoard();
				if (game.resultGameBoolean()) {
					break;
				}
				if (zuege >= 9) {
					break;
				}
				game.setzenSpieler1();
				zuege++;
				
				if (game.resultGameBoolean()) {
					game.showBoard();
					break;
				}
			} while (zuege < 9);
			System.out.println(game.resultGame());
		}
	}
}
