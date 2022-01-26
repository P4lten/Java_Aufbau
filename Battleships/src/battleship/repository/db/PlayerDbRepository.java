package battleship.repository.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import battleship.repository.BattleshipsException;
import battleship.repository.Player;
import battleship.repository.PlayerRepository;

public class PlayerDbRepository implements PlayerRepository {

	private String dbUrl, userName, password;

	private final static String INSERT_PLAYER_STATEMENT = "insert into Players  "
			+ "   (name, rank, points, time, date)" + "    values   " + "  (?, ?, ?, ?, ?)  ";

	private final static String INSERT_PLAYER_GAMES_STATEMENT = "insert into Games  " + "   (name, points, time, date)"
			+ "    values   " + "  ( ?, ?, ?, ?)  ";

	private final static String UPDATE_PLAYER_STATEMENT = "update Players set name = ?, rank = ?, points = ?, time = ?, date = ?  where name = ? ";

	private final static String UPDATE_PLAYER_GAMES_STATEMENT = "update Games set name = ?, points = ?, date = ?  where time = ? ";

	private final static String UPDATE_PLAYER_GAMES_TIME = "update Games set time = ?  where time = ? ";

	private final static String SORT_PLAYER_DESC = "select * from players order by points desc ";

	

	public PlayerDbRepository(String dbUrl, String userName, String password) {
		this.dbUrl = dbUrl;
		this.userName = userName;
		this.password = password;
	}
	
	

	public List<Player> sortPlayerList(List<Player> allPlayers) throws BattleshipsException {
		try (Connection conn = DriverManager.getConnection(dbUrl, userName, password)) {
			System.out.println("sortPlayerList... ");
			PreparedStatement stmt = conn.prepareStatement(SORT_PLAYER_DESC);
			ResultSet result = stmt.executeQuery();
			allPlayers = new ArrayList<>();
			while (result.next()) {
				// aus dem Datensatz ein Player-Objekt hinzufügen
				allPlayers.add(readPlayer(result));
			}
			return allPlayers;
		} catch (Exception e) {
			System.err.println("Fehler beim Abrufen der Daten ");

			throw new BattleshipsException("Fehler beim Zugriff auf die Datenbank", e);
		}
	}
	
	public void updatePlayerRank(int rank, String name) throws BattleshipsException {
		// einen Player updateb
		try (Connection conn = DriverManager.getConnection(dbUrl, userName, password)) {
			String query = "update Players set  rank = " + rank + "  where name = '" + name + "'";
//			System.out.println("updatePlayer: " + query);
			// ein Statement das Parameter unterstützt
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.executeQuery(query);
		} catch (Exception e) {
			System.err.println("Fehler beim Aktualisieren eines Datensatzes");
			e.printStackTrace();
			throw new BattleshipsException("Fehler beim Aktualisieren eines Player-Datensatzes", e);
		}
	}
	

	@Override
	public List<Player> selectAllFromPlayers() throws BattleshipsException {
		try (Connection conn = DriverManager.getConnection(dbUrl, userName, password)) {
			Statement stmt = conn.createStatement();

			String query = " select name, rank, points, time, date from Players";
			ResultSet result = stmt.executeQuery(query);
			// Liste für den Returnwert
			List<Player> allPlayers = new ArrayList<>();
			while (result.next()) {
				// aus dem Datensatz ein Player-Objekt hinzufügen
				allPlayers.add(readPlayer(result));
			}
			return allPlayers;
		} catch (Exception e) {
			System.err.println("Fehler beim Abrufen der Daten ");

			throw new BattleshipsException("Fehler beim Zugriff auf die Datenbank", e);
		}
	}
	
	@Override
	public List<Player> selectAllFromGames() throws BattleshipsException {
		try (Connection conn = DriverManager.getConnection(dbUrl, userName, password)) {
			Statement stmt = conn.createStatement();
			String query = " select name, points, time, date from Games";
			ResultSet result = stmt.executeQuery(query);
			// Liste für den Returnwert
			List<Player> allPlayers = new ArrayList<>();
			while (result.next()) {
				// aus dem Datensatz ein Player-Objekt hinzufügen
				allPlayers.add(readPlayerGames(result));
			}
			return allPlayers;
		} catch (Exception e) {
			System.err.println("Fehler beim Abrufen der Daten ");
			
			throw new BattleshipsException("Fehler beim Zugriff auf die Datenbank", e);
		}
	}
	
	
	@Override
	public Player selectByName(String name) throws BattleshipsException {
		try (Connection conn = DriverManager.getConnection(dbUrl, userName, password)) {
			String query = "select  name, rank, points, time, date from Players where name = ? ";
			System.out.println("selectByRank: " + query);
			// ein Statement das Parameter unterstützt
			PreparedStatement stmt = conn.prepareStatement(query);
			// jetzt Wert für den Parameter setzten 
			stmt.setString(1, name);
			// dann erst ausführen
			ResultSet result = stmt.executeQuery();
			// wenn es keinen Nächsten Datensatz gibt -> Exception werfen
			if (!result.next()) {
				throw new BattleshipsException("Player mit Name " + name + " existiert nicht");
			}
			Player entity = readPlayer(result);
			return entity;

		} catch (Exception e) {
			System.err.println("Fehler beim Laden eines Datensatzes ");

			throw new BattleshipsException("Fehler beim Laden eines Player-Datensatzes", e);
		}
	}

	@Override
	public List<Player> selectPlayersFromGames(String name) throws BattleshipsException {
		try (Connection conn = DriverManager.getConnection(dbUrl, userName, password)) {
			String query = " select name, points, time, date from Games where name = ? ";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, name);
			// Liste für den Returnwert
			ResultSet result = stmt.executeQuery(query);
			List<Player> allPlayers = new ArrayList<>();
			while (result.next()) {
				// aus dem Datensatz ein Player-Objekt hinzufügen
				allPlayers.add(readPlayerGames(result));
			}
			return allPlayers;
		} catch (Exception e) {
			System.err.println("Fehler beim Abrufen der Daten ");

			throw new BattleshipsException("Fehler beim Zugriff auf die Datenbank", e);
		}
	}
	
	@Override
	public void insertPlayer(Player player) throws BattleshipsException {
		try (Connection conn = DriverManager.getConnection(dbUrl, userName, password)) {

			// Statement erzeugen
			PreparedStatement stmt = conn.prepareStatement(INSERT_PLAYER_STATEMENT, Statement.RETURN_GENERATED_KEYS);
			setCommonParameters(player, stmt);

			// den Befehl ausf�hren, Ergebniss ist Anzahl der betroffenen Datensätze
			// -> muss 1 sein, wenn alles passt
			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected != 1) {
				System.out.println("Keine Datensätze vom Insert betroffen");
				throw new BattleshipsException("Es sind keine Dantensätze vom Insert betroffen");
			}
		} catch (Exception e) {
			System.err.println("Fehler beim Einf�gen eines Datensatzes");
			e.printStackTrace();
			throw new BattleshipsException("Fehler beim Einfügen eines Player-Datensatzes", e);
		}
	}

	@Override
	public void updatePlayer(Player player) throws BattleshipsException {
		// einen Player �ndern
		try (Connection conn = DriverManager.getConnection(dbUrl, userName, password)) {
			PreparedStatement stmt = conn.prepareStatement(UPDATE_PLAYER_STATEMENT);
			// alle Parameter setzen
//			System.out.println("Update Player =" + player);
//			System.out.println("PlayerPunkte = " +  player.getPoints());
			setCommonParameters(player, stmt);
			stmt.setString(6, player.getName());
			int changerows = stmt.executeUpdate();
			System.out.println("Changerows = " + changerows);
			
		} catch (Exception e) {
			System.err.println("Fehler beim Aktualisieren eines Datensatzes");
			e.printStackTrace();
			throw new BattleshipsException("Fehler beim Aktualisieren eines Player-Datensatzes", e);
		}
	}

	
	@Override
	public void insertPlayerInGames(Player player) throws BattleshipsException {
		try (Connection conn = DriverManager.getConnection(dbUrl, userName, password)) {

			// Statement erzeugen, das nach dem Ergebnis auch ein Resultset mit dem neuen
			// Keywert zurückliefert
			PreparedStatement stmt = conn.prepareStatement(INSERT_PLAYER_GAMES_STATEMENT,
					Statement.RETURN_GENERATED_KEYS);
			setCommonParametersGames(player, stmt);

			// den Befehl ausführen, Ergebniss ist Anzahl der betroffenen Datensätze
			// -> muss 1 sein, wenn alles passt
			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected != 1) {
				System.out.println("Keine Datensätze vom Insert betroffen");
				throw new BattleshipsException("Es sind keine Dantensätze vom Insert betroffen");
			}

		} catch (Exception e) {
			System.err.println("Fehler beim Einfügen eines Datensatzes");
			e.printStackTrace();
			throw new BattleshipsException("Fehler beim Einfügen eines Player-Datensatzes", e);
		}
	}

	
	
	@Override
	public void updatePlayerInGames(Player player) throws BattleshipsException {
		// einen Player von Games �ndern
		try (Connection conn = DriverManager.getConnection(dbUrl, userName, password)) {
			PreparedStatement stmt = conn.prepareStatement(UPDATE_PLAYER_GAMES_STATEMENT);
			// alle Parameter setzen
//			System.out.println("Update Player Games =" + player);
//			System.out.println("PlayerPunkteGames = " +  player.getPoints());
			stmt.setString(1, player.getName());
			stmt.setInt(2, player.getPoints());
			stmt.setDate(3, Date.valueOf(player.getDate()));
			stmt.setLong(4, player.getTime());
			int changerows = stmt.executeUpdate();
			System.out.println("Changerows = " + changerows);
			
		} catch (Exception e) {
			System.err.println("Fehler beim Aktualisieren eines Datensatzes");
			e.printStackTrace();
			throw new BattleshipsException("Fehler beim Aktualisieren eines Player-Datensatzes", e);
		}
	}

	@Override
	public void updatePlayerGamesTime(long time, Player player) throws BattleshipsException{
		try (Connection conn = DriverManager.getConnection(dbUrl, userName, password)) {
			PreparedStatement stmt = conn.prepareStatement(UPDATE_PLAYER_GAMES_TIME);
			stmt.setLong(1, time);
			stmt.setLong(2, player.getTime());
			int changerows = stmt.executeUpdate();
			System.out.println("Changerows = " + changerows);
		} catch (Exception e) {
			System.err.println("Fehler beim Aktualisieren eines Datensatzes");
			e.printStackTrace();
			throw new BattleshipsException("Fehler beim Aktualisieren eines Player-Datensatzes", e);
		}
	}
	
	

	private void setCommonParameters(Player player, PreparedStatement stmt) throws SQLException {
		stmt.setString(1, player.getName());
		stmt.setInt(2, player.getRank());
		stmt.setInt(3, player.getPoints());
		stmt.setLong(4, player.getTime());
		// LocalDate: in Date umgewandelt
		stmt.setDate(5, Date.valueOf(player.getDate()));
		
	}
	
	private void setCommonParametersGames(Player player, PreparedStatement stmt) throws SQLException {
		stmt.setString(1, player.getName());
		stmt.setInt(2, player.getPoints());
		stmt.setLong(3, player.getTime());
		// LocalDate: in Date umgewandelt
		stmt.setDate(4, Date.valueOf(player.getDate()));
		
	}
	
	
	private Player readPlayer(ResultSet result) throws SQLException {
		// je nach wert ein passendes Object erzeugen
		Player entity = new Player();
		// Alle werte in die Properties schreiben
		entity.setName(result.getString("name"));
		entity.setRank(result.getInt("rank"));
		entity.setPoints(result.getInt("points"));
		entity.setTime(result.getLong("time"));
		entity.setDate(result.getDate("date").toLocalDate());

		return entity;
	}
	
	
	private Player readPlayerGames(ResultSet result) throws SQLException {
		// je nach wert ein passendes Object erzeugen
		Player entity = new Player();
		// Alle werte in die Properties schreiben
		entity.setName(result.getString("name"));
		entity.setPoints(result.getInt("points"));
		entity.setTime(result.getLong("time"));
		entity.setDate(result.getDate("date").toLocalDate());

		return entity;
	}



	@Override
	public void deletePlayer(Player player) throws BattleshipsException {
		try (Connection conn = DriverManager.getConnection(dbUrl, userName, password)) {
			String query = "delete from Players where time = ? ";
			System.out.println("selectByTime: " + query);
			System.out.println("gel�schter Spieler\n" + player);
			// ein Statement das Parameter unterstützt
			PreparedStatement stmt = conn.prepareStatement(query);
			// jetzt Wert für den Parameter setzten 
			stmt.setLong(1, player.getTime());
			// dann erst ausführen
			stmt.executeQuery();
		} catch (Exception e) {
			System.err.println("Fehler beim L�schen eines Datensatzes ");
			throw new BattleshipsException("Fehler beim L�schen eines Player-Datensatzes", e);
		}
	}
		
	



	@Override
	public void deletePlayerInGames(Player player) throws BattleshipsException {
		try (Connection conn = DriverManager.getConnection(dbUrl, userName, password)) {
			String query = "delete from Games where time = ? ";
			System.out.println("selectByTime: " + query);
			System.out.println("gel�schter Spieler" + player);
			// ein Statement das Parameter unterstützt
			PreparedStatement stmt = conn.prepareStatement(query);
			// jetzt Wert für den Parameter setzten 
			stmt.setLong(1, player.getTime());
			// dann erst ausf�hren
			stmt.executeQuery();
		} catch (Exception e) {
			System.err.println("Fehler beim L�schen eines Datensatzes ");
			throw new BattleshipsException("Fehler beim L�schen eines Player-Datensatzes", e);
		}
	}
		
	
}