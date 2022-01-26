package program;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class JbdcStatementsDemo {

	public static void main(String[] args) {
		testSimpleStatement();

	}

	static void testSimpleStatement() {
		String connUrl = "jdbc:mariadb://localhost/zoo";
		// Verbindung zur DB herstellen, dafür muss ein passender Treiber am
		// classpath/modulepath vorhanden sein
		try (Connection conn = DriverManager.getConnection(connUrl, "root", "")) {
			// Statement erzeugen (von der connection erzeugen lassen)
			Statement sqlBefehl = conn.createStatement();
			// einen Befehl ausführen
			ResultSet result = sqlBefehl.executeQuery("select tierId, name, imZooSeit, pflanzenfresser, gewicht from tiere");
			System.out.println("Ergebnis der Abfrage: ");
			// solange ein nächster Datensatz zu lesen ist
			while (result.next()) {
				// die werte des Datensatzes lesen
				// Variante 1 : mit den Ordinalwerten der Spalten
//				int tierId = result.getInt(1); // den Wert aus Spalte 1 als int lesen
//				String name = result.getString(2); // den Wert aus Spalte 2 als String lesen
//				LocalDate imZooSeit = result.getDate(3).toLocalDate(); // den Wert aus Spalte 3 als Date lesen
//				boolean pflanzenfresser = result.getBoolean(4);// den Wert aus Spalte 4 als Boolean lesen
				
				// Variante 2: mit Spaltennamen (sicherer!)
				int tierId = result.getInt("tierId"); // den Wert aus Spalte tierId als int lesen
				String name = result.getString("name"); // den Wert aus Spalte name als String lesen
				LocalDate imZooSeit = result.getDate("imZooSeit").toLocalDate(); // den Wert aus Spalte imZooSeit als Date lesen
				boolean pflanzenfresser = result.getBoolean("pflanzenfresser");// den Wert aus Spalte pflanzenfresser als Boolean lesen
				Integer gewicht = result.getInt("gewicht");
				// wenn der zuletzt gewesene Wert DB NULL war
				if(result.wasNull()) {
					// dan den Wert null setzen
					gewicht = null;
				}

				System.out.printf("%s: Id = %d, ImZooSeit = %s, Pflanzenfresser: %b, Gewicht: %s\n", name, tierId, imZooSeit,
						pflanzenfresser,
						gewicht != null ? (gewicht + "kg") : "n.v.");
				
				
			}

		} catch (SQLException e) {
			System.err.println("Fehler beim Abrufen der Daten");
			// den Fehler anzeigen
			e.printStackTrace();
		}
	}
}
