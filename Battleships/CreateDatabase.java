

import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.rowset.serial.SerialBlob;

import model.Photo;
import model.Photographer;

public class CreateDatabase {

	private static final String CONNECTION_STRING = "jdbc:mariadb://localhost/PlayerDB", "root", "" ; "create=true";
	private static final String CREATE_PLAYERS_TABLE = "CREATE TABLE Players (name  varchar(150)  NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,"

			+ "points  int   NOT NULL," + "time  int NOT NULL," + "date DATE," + "rank    int   NOT NULL,")";

	private static final String DROP_PLAYERS_TABLE = "DROP TABLE Players";

	
	public static void main(String[] args) throws IOException {

		// Hier Methoden createTable, dropTable und insertSampleData aufrufen

		// Verbindung zur Datenbank
		try {
			Connection con = DriverManager.getConnection(CONNECTION_STRING);
			System.out.println("Connection erstellt");

			// wirft Tabelle samt Datensätze weg!!
			dropPlayersTable(con);

			

			System.out.println("Tabelle Player gelöscht");

			// erstellt tabelle Photographer
			createPlayersTable(con);
			
			System.out.println("Tabelle Players erstellt");
			
			Player player = new Player("Peter", "Thomas");
			
			
			// beispiel photographer
			insertPlayer(player, con);

			
			// Auslesen der Datensätze mit ids, von der Datenbank generiert
			readAll(con).forEach(System.out::println);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	

	

	private static void insertPlayer(Player player, Connection con) throws SQLException {
		
		PreparedStatement pStmt = con.prepareStatement("INSERT INTO PLAYERS (rank, name, points, time, date) "
				+ " values (1, 'Peter', 1000, 500 , '1995-06-10');");
		
		PreparedStatement pStmt = con.prepareStatement("INSERT INTO PLAYERS (rank, name, points, time, date) "
				+ " values (2, 'Thomas', 900, 400 , '1995-06-10');");
		
		int rowsChanged = pStmt.executeUpdate();
		System.out.println("Rows changed: " + rowsChanged);
		
	}

	private static void dropPlayersTable(Connection con) throws SQLException {

		Statement stmt = con.createStatement();

		stmt.execute(DROP_PLAYERS_TABLE);

	}

	private static void createPlayersTable(Connection con) throws SQLException {

		Statement stmt = con.createStatement();

		stmt.execute(CREATE_PLAYERS_TABLE);

	}

	

	

	
}
