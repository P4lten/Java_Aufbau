package tictactoe;

import java.util.Scanner;

public class TicTacToe {

	Scanner input = new Scanner(System.in);
	private char[][] spielfeld;

	char spieler1 = 'X';
	char spieler2 = 'O';

	public TicTacToe() {
		// "leeres" Brett wird mit '-' belegt
		spielfeld = new char[3][3];

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++)
				spielfeld[i][j] = '-';
		}
	}

	// das aktuelle Brett wird angezeigt
	public void showBoard() {
		int zähler = 0;
		System.out.println(" 0" + "1" + "2");
		for (char[] zeilenWerte : spielfeld) {
			System.out.print(zähler);
			for (char cs : zeilenWerte) {
				System.out.print(cs);
			}

			System.out.println();
			zähler++;
		}
		System.out.println();
	}

	public char[][] setzenSpieler1() {
		System.out.print("Spieler 1(X): Bitte zuerst Zeile dann Spalte setzen: ");
		byte a = input.nextByte();
		// überprüfen ob Input von richtig ist wenn nicht -> nochmal eingeben
		if (a > 2) {
			System.out.println("Bitte nur Zahlen von 0- 2 eingeben");
			return setzenSpieler1();
		}
		byte b = input.nextByte();
		// überprüfen ob Input von richtig ist wenn nicht -> nochmal eingeben
		if (b > 2) {
			System.out.println("Bitte nur Zahlen von 0- 2 eingeben");
			return setzenSpieler1();
		}
		// muss ueberpruefen ob das feld nicht schon von Spieler 2 oder sich selbst
		// besetzt ist
		if (spielfeld[a][b] != spieler2 && spielfeld[a][b] != spieler1) {
			spielfeld[a][b] = spieler1;
			return spielfeld;
		}
		// wenn besetzt ist wird methode wieder aufgerufen
		System.out.println("Feld schon vergeben");
		return setzenSpieler1();

	}

	public char[][] setzenSpieler2() {
		System.out.print("Spieler 2(O): Bitte zuerst Zeile dann Spalte setzen: ");
		byte a = input.nextByte();
		// überprüfen ob Input von richtig ist wenn nicht -> nochmal eingeben
		if (a > 2) {
			System.out.println("Bitte nur Zahlen von 0- 2 eingeben");
			return setzenSpieler2();
		}
		byte b = input.nextByte();
		// überprüfen ob Input von richtig ist wenn nicht -> nochmal eingeben
		if (b > 2) {
			System.out.println("Bitte nur Zahlen von 0- 2 eingeben");
			return setzenSpieler2();
		}
		// muss ueberpruefen ob das feld nicht schon von Spieler 2 oder sich selbst
		// besetzt ist
		if (spielfeld[a][b] != spieler1 && spielfeld[a][b] != spieler2) {
			spielfeld[a][b] = spieler2;
			return spielfeld;
		}
		// wenn besetzt ist wird methode wieder aufgerufen
		System.out.println("Feld schon vergeben");
		return setzenSpieler2();
	}

	// das Spielergebnis wird ausgegeben
	public String resultGame() {
		int wert = pruefenObGewonnen(spielfeld);
		if (wert == 264)
			return "Spieler 1 gewinnt";
		else if (wert == 237)
			return "Spieler 2 gewinnt";
		else
			return "Spiel endet unentschieden";
	}

	public boolean resultGameBoolean() {
		int wert = pruefenObGewonnen(spielfeld);
		if (wert == 264) {
			return true;
		} else if (wert == 237) {
			return true;
		} else {
			return false;
		}
	}

	public int pruefenObGewonnen(char[][] b) {
		// 'O' + 'O' + 'O' = 264
		// 'X' + 'X' + 'X' = 237;
		int sum, sum2;

		// prüfe zeilen und spalten
		for (int i = 0; i < b.length; i++) {
			sum = b[i][0] + b[i][1] + b[i][2];
			sum2 = b[0][i] + b[1][i] + b[2][i];
			if ((sum == 264) || (sum2 == 264)) {
				return 264;
			} else if ((sum == 237) || (sum2 == 237)) {
				return 237;
			}
			// prüfe die Diagonale links oben nach rechts unten
			sum = b[0][0] + b[1][1] + b[2][2];
			// prüfe die Diagonale links unten nach rechts oben
			sum2 = b[0][2] + b[1][1] + b[2][0];

			if ((sum == 264) || (sum2 == 264)) {
				return 264;
			} else if ((sum == 237) || (sum2 == 237)) {
				return 237;
			}

		}
		// ansonsten ist es (noch) unentschieden
		return 0;

	}

}
