package battleship.repository.db;

import static battleship.repository.Constants.DBPASSWORD;
import static battleship.repository.Constants.DBPATH;
import static battleship.repository.Constants.DBUSER;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import battleship.repository.Player;

public class CreateDatabase {

	private static final String CREATE_PLAYERS_TABLE = "create table Players ( name  varchar(150)  NOT NULL  PRIMARY KEY , rank    int   NOT NULL,"
			+ "	points  int   NOT NULL," + "	time  long NOT NULL," + "	date date  NOT NULL);";

	private static final String CREATE_GAMES_TABLE = "create table Games (" + "	name  varchar(150) NOT NULL,"
			+ "	points  int NOT NULL," + "	time  long  NOT NULL," + "	date  date  NOT NULL,"
			+ "	constraint  fk_Players_Games" + "	foreign key(name) " + "	references  Players(name));";

	private static final String DROP_PLAYERS_TABLE = "DROP TABLE Players";

	private static final String DROP_GAMES_TABLE = "DROP TABLE Games";

	public static void main(String[] args) throws IOException {

		// Hier Methoden createTable, dropTable aufrufen

		// Verbindung zur Datenbank
		try {
			Connection con = DriverManager.getConnection(DBPATH, DBUSER, DBPASSWORD);
			System.out.println("Connection erstellt");

			// wirft Tabelle samt Datensätze weg!!
			dropGamesTable(con);
			dropPlayersTable(con);

			System.out.println("Tabelle Player gelöscht");

			// erstellt tabelle Player
			createPlayersTable(con);
			createGamesTable(con);
			System.out.println("Tabelle Players erstellt");

			Player player = new Player();
			

			// beispiel player
			insertPlayer(player, con);
			insertPlayer2(player, con);
			insertPlayerGames(player, con);

//			// Auslesen der Datensätze mit ids, von der Datenbank generiert
//			readAll(con).forEach(System.out::println);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private static void insertPlayer(Player player, Connection con) throws SQLException {

		PreparedStatement pStmt = con.prepareStatement("INSERT INTO PLAYERS ( name,rank, points, time, date) "
				+ " values ( 'Peter',1, 1100, 500 , '1995-06-10');");


		int rowsChanged = pStmt.executeUpdate();

		System.out.println("Rows changed: " + rowsChanged);

	}
	private static void insertPlayer2(Player player, Connection con) throws SQLException {
		PreparedStatement pStmt2 = con.prepareStatement("INSERT INTO PLAYERS ( name,rank, points, time, date) "
				+ " values ( 'Thomas',2, 900, 400 , '1995-06-10');");
		int rowsChanged2 = pStmt2.executeUpdate();
		System.out.println("Rows changed: " + rowsChanged2);

	}
	private static void insertPlayerGames(Player player, Connection con) throws SQLException {
		PreparedStatement pStmt2 = con.prepareStatement("INSERT INTO GAMES ( name, points, time, date) "
				+ " values ( 'Thomas', 800, 500 , '2021-06-10');");
		int rowsChanged2 = pStmt2.executeUpdate();
		System.out.println("Rows changed: " + rowsChanged2);
		
	}

	private static void dropPlayersTable(Connection con) throws SQLException {

		Statement stmt = con.createStatement();

		stmt.execute(DROP_PLAYERS_TABLE);

	}

	private static void dropGamesTable(Connection con) throws SQLException {

		Statement stmt = con.createStatement();

		stmt.execute(DROP_GAMES_TABLE);

	}

	private static void createPlayersTable(Connection con) throws SQLException {

		Statement stmt = con.createStatement();

		stmt.execute(CREATE_PLAYERS_TABLE);

	}

	private static void createGamesTable(Connection con) throws SQLException {

		Statement stmt = con.createStatement();

		stmt.execute(CREATE_GAMES_TABLE);

	}

}
