package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {

	private static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		String hostName = "localhost";
		
		try {
		
			System.out.println("Daten zeilenweise eingeben, beenden mit Leerzeile");
			String line;
			// solang der User Text eingeben hat
			while ((line = scanner.nextLine()) != null && !line.isEmpty()) {
				System.out.println("Sende Text: " + line);
			}
			
		} catch (Exception e) {
			System.err.println("Fehler im EchoClient:");
			e.printStackTrace();
		}

	}

}
