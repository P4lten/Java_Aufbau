package mitarbeiter.repository.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mitarbeiter.repository.Experte;
import mitarbeiter.repository.Manager;
import mitarbeiter.repository.Mitarbeiter;
import mitarbeiter.repository.MitarbeiterRepository;
import mitarbeiter.repository.MitarbeiterRepositoryException;
import mitarbeiter.repository.Typ;

public class EmployeeDbRepository implements MitarbeiterRepository {

	// Informationen zur Connection
	private String dbUrl, userName, password;

	private final static String INSERT_EMPLOYEE_STATEMENT = "insert into Employees"
			+ "         (name, areaCode, city, birthDate, entryDate, salary, typ, bonus, specialField, experte, manager, comment)"
			+ " values" + "          (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private final static String UPDATE_EMPLOYEE_STATEMENT = "update Employees set name = ?, areaCode = ?, city = ? , birthDate = ?, entryDate = ?, salary = ?, "
			+ " typ = ? , bonus = ? , specialField = ? , experte = ?, manager = ?, comment = ? " + " where id = ? ";

	private final static String DELETE_EMPLOYEE_STATEMENT = "delete from Employees where id = ?";

	public EmployeeDbRepository(String dbUrl, String userName, String password) {
		this.dbUrl = dbUrl;
		this.password = password;
		this.userName = userName;
	}

	@Override
	public List<Mitarbeiter> selectAll() throws MitarbeiterRepositoryException {

		try (Connection conn = DriverManager.getConnection(dbUrl, userName, password)) {
			Statement stmt = conn.createStatement();

			String query = "select id, name, areaCode, city, birthDate, entryDate, salary, typ, bonus, specialField, experte, manager, comment"
					+ " from Employees ";
			ResultSet result = stmt.executeQuery(query);
			// Liste für den Returnwert
			List<Mitarbeiter> allEmployees = new ArrayList<>();
			while (result.next()) {
				// aus dem Datensatz ein Mitarbeiter-Objekt hinzufügen
				allEmployees.add(readEmployee(result));

			}

			return allEmployees;

		} catch (Exception e) {
			System.err.println("Fehler beim Abrufen der Daten ");

			throw new MitarbeiterRepositoryException("Fehler beim Zugriff auf die Datenbank", e);
		}
	}

	@Override
	public Mitarbeiter selectById(int id) throws MitarbeiterRepositoryException {
		try (Connection conn = DriverManager.getConnection(dbUrl, userName, password)) {
			String query = "select id, name, areaCode, city, birthDate, entryDate, salary, typ, bonus, specialField, experte, manager, comment"
					+ " from Employees " + " where id = ?";
			System.out.println("selectById: " + query);
			// ein Statement das Parameter unterstützt
			PreparedStatement stmt = conn.prepareStatement(query);
			// jetzt Wert für den Parameter setzten (Nummerierung beginnt bei 1)
			stmt.setInt(1, id);
			// dann erst ausführen
			ResultSet result = stmt.executeQuery(query);
			// wenn es keinen Nächsten Datensatz gibt -> Exception werfen
			if (!result.next()) {
				throw new MitarbeiterRepositoryException("Mitarbeiter mit ID " + id + " existiert nicht");
			}
			Mitarbeiter entity = readEmployee(result);
			return entity;

		} catch (Exception e) {
			System.err.println("Fehler beim Laden eines Datensatzes ");

			throw new MitarbeiterRepositoryException("Fehler beim Laden eines Mitarbeiter-Datensatzes", e);
		}
	}

	@Override
	public int insertEmployee(Mitarbeiter employee) throws MitarbeiterRepositoryException {
		try (Connection conn = DriverManager.getConnection(dbUrl, userName, password)) {

			// Statement erzeugen, das nach dem Ergebnis auch ein Resultset mit dem neuen
			// Keywert zurückliefert
			PreparedStatement stmt = conn.prepareStatement(INSERT_EMPLOYEE_STATEMENT, Statement.RETURN_GENERATED_KEYS);
			setCommonParameters(employee, stmt);
			

			// den Befehl ausführen, Ergebniss ist Anzahl der betroffenen Datensätze
			// -> muss 1 sein, wenn alles passt
			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected != 1) {
				System.out.println("Keine Datensätze vom Insert betroffen");
				throw new MitarbeiterRepositoryException("Es sind keine Dantensätze vom Insert betroffen");
			}
			// wenn alles gut gegangen ist, den Key abholen
			ResultSet keys = stmt.getGeneratedKeys();
			if (keys.next()) {
				int id = keys.getInt(1);
				System.out.println("Key= "+keys.getInt(1));
				System.out.printf("Objekt eingefügt neue ID=%d\n", id);
				return id;
			} else {
				// Das sollte nicht passieren
				System.err.println("Objekt eingefügt, neue ID nicht bekannt!");
				throw new MitarbeiterRepositoryException(
						"Der Datensatz wurde eingefügt, die Id konnte aber nicht ermittelt werden");
			}

//			return rowsAffected;
		} catch (Exception e) {
			System.err.println("Fehler beim Einfügen eines Datensatzes");
			e.printStackTrace();
			throw new MitarbeiterRepositoryException("Fehler beim Einfügen eines Mitarbeiter-Datensatzes", e);
		}
	}

	@Override
	public void updateEmployee(Mitarbeiter employee) throws MitarbeiterRepositoryException {
		// einen Mitarbeiter ändern
		try (Connection conn = DriverManager.getConnection(dbUrl, userName, password)) {
			PreparedStatement stmt = conn.prepareStatement(UPDATE_EMPLOYEE_STATEMENT);
			// alle Parameter setzen
			setCommonParameters(employee, stmt);
			// ID setzen (bei Tabelle Mitarbeiter ist es der 11. Parameter
			stmt.setInt(11, employee.getMitarbeiterId());
			int count = stmt.executeUpdate();
			// wenn kein Datensatz betroffen ist, dann existiert der Mitarbeiter-Datensatz
			// nicht
			// mehr
			if (count == 0) {
				throw new MitarbeiterRepositoryException(
						"Student mit ID " + employee.getMitarbeiterId() + " existiert nicht");
			}
		} catch (Exception e) {
			System.err.println("Fehler beim Aktualisieren eines Datensatzes");
			e.printStackTrace();
			throw new MitarbeiterRepositoryException("Fehler beim Aktualisieren eines Mitarbeiter-Datensatzes", e);
		}
	}

	@Override
	public void deleteEmployee(int id) throws MitarbeiterRepositoryException {
		try (Connection conn = DriverManager.getConnection(dbUrl, userName, password)) {
			PreparedStatement stmt = conn.prepareStatement(DELETE_EMPLOYEE_STATEMENT);
			// die Id setzen
			stmt.setInt(1, id);
			// und ausführen
			int count = stmt.executeUpdate();
			if (count == 0) {
				throw new MitarbeiterRepositoryException("Mitarbeiter mit ID " + id + " existiert nicht");
			}

		} catch (Exception e) {
			System.err.println("Fehler beim Löschen eines Datensatzes");
			e.printStackTrace();
			throw new MitarbeiterRepositoryException("Fehler beim Löschen eines Mitarbeiter-Datensatzes", e);
		}
	}

	@Override
	public void updateEmployees(List<Mitarbeiter> employees) throws MitarbeiterRepositoryException {
		try (Connection conn = DriverManager.getConnection(dbUrl, userName, password)) {
			// alle Mitarbeiter aktualisieren, alle änderungen müssen in einer Transaktion
			// laufen
			// autoCommit deaktivieren
			conn.setAutoCommit(false);
			PreparedStatement stmt = conn.prepareStatement(UPDATE_EMPLOYEE_STATEMENT);

			try {
				for (Mitarbeiter employee : employees) {
					// Parameter-Werte setzen
					setCommonParameters(employee, stmt);
					stmt.setInt(11, employee.getMitarbeiterId());
					// Update ausführen
					int count = stmt.executeUpdate();
					// wenn ein Mitarbeiter dabei ist der nicht mehr existiert
					if (count == 0) {
						throw new MitarbeiterRepositoryException(
								"Mitarbeiter mit ID " + employee.getMitarbeiterId() + " existiert nicht");
					}

					System.out.println("... Aktualisierung OK");
				}
				// wenn wir hier angelangt sind -> ist alles gut gegangen, alle Änderungen
				// wurden durchgeführt
				// -> Transaktion abschließen (commit)
				conn.commit();
			} catch (Exception inner) {
				// wenn innerhalb unserer Transaktion ein Fehler passiert
				// rollback ausführen
				conn.rollback();
				throw new MitarbeiterRepositoryException("Fehler beim Aktualisierung der MitarbeiterListe", inner);
			}
		} catch (Exception e) {

			System.err.println("Fehler beim Ändern der Datenbank");
			e.printStackTrace();
			throw new MitarbeiterRepositoryException("Fehler beim Aktualisieren der MitarbeiterListe", e);
		}

	}

	private void setCommonParameters(Mitarbeiter employee, PreparedStatement stmt) throws SQLException {
		// TODO analog zu EditEmployeeController.setEmployee:
		// passenden Wert für die Spalte mitarbeiterTyp ermitteln und ggf Type cast
		// durchführen

		// welche Art von mitarbeiter haben wir?
		if (employee instanceof Experte) {
			// Typumwandlung
			Experte editExperte = (Experte) employee;
			stmt.setString(9, employee.getFachgebiet());

		} else if (employee instanceof Manager) {
			Manager editManager = (Manager) employee;
			stmt.setDouble(8, employee.getBonus());
		}
		// wenn es ein "Normaler" Mitarbeiter ist
		else {
			// TODO mitarbeitertyp festlegen
		}

		// als 2. den Namen
		stmt.setString(1, employee.getName());
		// usw.
		stmt.setInt(2, employee.getAreaCode());

		stmt.setString(3, employee.getCity());
		// LocalDate: in Date umgewandelt
		stmt.setDate(4, Date.valueOf(employee.getGeburtsdatum()));
		stmt.setDate(5, Date.valueOf(employee.getEintrittsdatum()));
		// enum in Zeichenfolge umwandeln
		stmt.setDouble(6, employee.getGehalt());
		stmt.setString(7, employee.getTyp().toString());
		stmt.setDouble(8, employee.getBonus());
		stmt.setString(9, employee.getFachgebiet());

		// booleans
		stmt.setBoolean(10, employee.isExperte());
		stmt.setBoolean(11, employee.isManager());

		stmt.setString(12, employee.getComment());
	}

	private Mitarbeiter readEmployee(ResultSet result) throws SQLException {
		// 1) die Spalte "mitarbeiterTyp" lesen
		String mitarbeiterTyp = "";
		// TODO 2) je nach wert ein passendes Object erzeugen
		// employeeResult = new Mitarbeiter();
		// Je nachdem welcher Typ selectiert ist wird, ein passendes Objekt erzeugen

		Mitarbeiter employeeResult;
		if (mitarbeiterTyp.equals("manager")) {
			Manager m = new Manager();
			double bonus = result.getDouble("bonus");
			m.setBonus(bonus);
			employeeResult = m;

		} else if (mitarbeiterTyp.equals("experte")) {
			Experte e = new Experte();
			// TODO FACHGEBIET
			employeeResult = e;

		} else {
			employeeResult = new Mitarbeiter();
		}

		Mitarbeiter entity = new Mitarbeiter();
		// Alle werte in die Properties schreiben
		entity.setMitarbeiterId(result.getInt("id"));
		entity.setName(result.getString("name"));
		entity.setAreaCode(result.getInt("areaCode"));
		entity.setCity(result.getString("city"));
		entity.setGeburtsdatum(result.getDate("birthDate").toLocalDate());
		entity.setEintrittsdatum(result.getDate("entryDate").toLocalDate());
		entity.setGehalt(result.getDouble("salary"));
		// Typ ENUM
		String strTyp = result.getString("typ");
		entity.setTyp(Typ.valueOf(strTyp));

		entity.setBonus(result.getDouble("bonus"));
		entity.setFachgebiet(result.getString("specialField"));
		// boolean
		entity.setExperte(result.getBoolean("experte"));
		entity.setManager(result.getBoolean("manager"));

		entity.setComment(result.getString("comment"));

		return entity;
	}

}
