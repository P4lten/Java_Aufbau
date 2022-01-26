package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

	public static void main(String[] args) {
		System.out.println("Server wird gestartet");

		try {
			boolean keepRunning = true;
			while (keepRunning) {
				System.out.println("Warte auf Clients...");
				// TODO: Client-Verbindung annehmen
				try {
					// TODO: Daten mit dem Client austauschen
				} catch (Exception e) {
					System.err.println("Fehler in Client-Kommunikation");
					e.printStackTrace();
				}
				System.out.println("Der Client hat keine weiteren Daten gesendet");
				
			}
		} catch (Exception e) {
			System.err.println("Fehler im EchoServer:");
			e.printStackTrace();
		}

	}

}
