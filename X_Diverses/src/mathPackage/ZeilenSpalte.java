package mathPackage;

import java.util.Random;

public class ZeilenSpalte {

	public static void main(String[] args) {
		final int rowCount = 3, colCount = 3;

		char[][] field = new char[rowCount][colCount];
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field.length; j++) {
				field[i][j] = 'o';
			}
			System.out.println();
		}
		
		final int cellCount = rowCount * colCount;

		Random rand = new Random();

		for (int i = 0; i < 10; i++) {
//			zufalls zahl zwischen 0 und cellCount (exkl.) holen
			int cellIndex = rand.nextInt(cellCount);
			// mit Division die Zeile ermitteln
			int rowIndex = cellIndex / colCount;
			// mit Divisionsrest die Spalte ermitteln
			int colIndex = cellIndex % colCount;
			field[rowIndex][colIndex] = 'x';
			System.out.printf("Zellindex %d entspricht Zeile %d/Spalte %d\n", cellIndex, rowIndex, colIndex);
		}

		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field.length; j++) {
				System.out.print(field[i][j]);
			}
			System.out.println();
		}

	}

}
