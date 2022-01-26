package battleship.repository;


import java.util.Random;

public class Tests {

	public static void main(String[] args) {
		


		String[] name = new String[100];

		char buchstabe = 'A';
		int zahl = 1;
		
			for (int i = 0; i < name.length; i++) {
				if(zahl <= 10) {
				name[i] =" btn" + Character.toString(buchstabe) + Integer.toString(zahl);
				zahl++;
				}else {
					buchstabe++;
					zahl=1;
					i--;
			}}

//		for (int j = 0; j < name.length; j++) {
//			System.out.print(name[j]);
//		}
		
			int k = 0;

		String array[][] = new String[10][10];
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length; j++) {
				array[j][i]= name[k];
				k++;
			}
		}
		
		for(int i=0; i<array.length;i++){
			for(int j=0; j<array.length; j++){
				System.out.print(array[i][j]);	
			}
			System.out.println();
		}
		
		
		
		String s = shootLetterAt();
		System.out.println(s);
		int x = shootNumberAt();
		System.out.println(x);
		String st = shootAt();
		System.out.println(st);
		}
	
	
	public static String shootLetterAt() {
		Random random = new Random();
		int r = random.nextInt(10) + 1;
		String y ="";
		
		switch (r) {
		case 1 -> y = "A";
		case 2 -> y = "B";
		case 3 -> y = "C";
		case 4 -> y = "D";
		case 5 -> y = "E";
		case 6 -> y = "F";
		case 7 -> y = "G";
		case 8 -> y = "H";
		case 9 -> y = "I";
		case 10 -> y = "J";
		default ->
		throw new IllegalArgumentException("Unexpected value: " + y);
		}
		return y;
	}
	
	
	public static int shootNumberAt() {
		Random random = new Random();
		int x = random.nextInt(10) + 1;
		return x;
	}
	
	public static String shootAt() {
		int x = shootNumberAt();
		String y = shootLetterAt();
		String together = String.valueOf(x) + y;
		
		return together;
		
	}
	
	
	
	
}