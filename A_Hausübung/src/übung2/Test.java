package übung2;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] array;
		array = new int[11];
		int counter = 0;
		int i = 2; //
		boolean Prim = true; // Prim = true wenn i eine Primzahl ist
		while (i < 10) {
			for (int j = 2; j < i - 1; j++) {

				if (i % j == 0) {

					Prim = false;

				}

			}

			if (Prim) {

				array[counter] = i;
				counter++;
				System.out.println("Prim=     " + i);
			} else {

				Prim = true;
			}
			i++;
		}

		for (int p = 0; p < arrayTrueLength(array); p++) {
			System.out.println("array: " + array[p]);
		}
	}

	static int arrayTrueLength (int array[]) {
		int counter = 0;
		
		for (int i =0;i<array.length;i++) {
			if(array[i] != 0) {
				counter++;
			}
		}
		return counter;
		
	}
}
