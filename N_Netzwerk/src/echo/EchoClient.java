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
			// Client-Socket erzeugen -> damit wird die Verbindung zum Server hergestellt
			Socket clientSocket = new Socket(hostName, 54321);
			// reader und Writer
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
			BufferedWriter writer = new BufferedWriter(
					new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"));
			
			System.out.printf("Verbindung zum Server hergestellt, hostname=%s\n", hostName);
			System.out.println("Daten zeilenweise eingeben, beenden mit Leerzeile");
			String line;
			// solang der User Text eingeben hat
			while ((line = scanner.nextLine()) != null && !line.isEmpty()) {
				// Daten senden
				writer.write(line);
				writer.newLine();
				// jetzt alles übertragen
				writer.flush();
				
				String antwort = reader.readLine();
				System.out.println("Antwort vom Server: " + antwort);
			}
			
			clientSocket.close();
			
			
		} catch (Exception e) {
			System.err.println("Fehler im EchoClient:");
			e.printStackTrace();
		}

	}

}
