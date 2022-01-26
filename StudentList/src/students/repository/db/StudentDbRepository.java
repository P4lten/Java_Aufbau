package students.repository.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import students.repository.Gender;
import students.repository.Student;
import students.repository.StudentRepository;
import students.repository.StudentRepositoryException;

public class StudentDbRepository implements StudentRepository {

	// Informationen zur Connection
	private String dbUrl, userName, password;

	private final static String INSERT_STUDENT_STATEMENT = "insert into Students "
			+ "		 	(name, areaCode, city, birthDate , gender, language, xml, html, fxml, comment) "
			+ " values"
			+ "			(?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

	// update statement mit den Parametern in der gleichen Reihenfolge wie beim Insert-Statement
	// zusätzlicher Parameter am Ende: für die Id
	private final static String UPDATE_STUDENT_STATEMENT = "update Students set name = ?, areaCode = ?, city = ?, birthDate = ?, "
			+ " gender = ?, language = ?, xml = ?, html = ?, fxml = ?, comment = ? "
			+ " where id = ?";

	// Delete-Statement hat nur einen Parameter: die Id
	private final static String DELETE_STUDENT_STATEMENT = "delete from Students where id = ?";


	public StudentDbRepository(String dbUrl, String userName, String password) {
		this.dbUrl = dbUrl;
		this.password = password;
		this.userName = userName;
	}

	@Override
	public List<Student> selectAll() throws StudentRepositoryException {
		// TODO alle Student-Datensätze aus der DB laden
		// name, areaCode, city, birthDate , gender, language, xml, html, fxml, comment
		try (Connection conn = DriverManager.getConnection(dbUrl, userName, password)) {
			Statement stmt = conn.createStatement();
			// Vorsicht bei größeren Datenmengen - wir ereugen ohne etwas zu checken für
			// jeden Datensatz ein objekt, im Zweifelsfall auch für 100.000 Objekte
			String query = "select id, name, areaCode, city, birthDate , gender, language, xml, html, fxml, comment "
					+ " from Students";
			ResultSet result = stmt.executeQuery(query);
			// Liste für den Returnwert
			List<Student> allStudents = new ArrayList<>();
			while (result.next()) {
				// aus dem Datensatz ein Student-Objekt hinzufügen
				allStudents.add(readStudent(result));

			}
			// die Student-Liste zurückliefern
			return allStudents;
		} catch (SQLException e) {
			System.err.println("Fehler beim Abrufen der Daten ");
//			e.printStackTrace();
			throw new StudentRepositoryException("Fehler beim Zugriff auf die Datenbank", e);
		}
	}

	@Override
	public Student selectById(int id) throws StudentRepositoryException {
		// den Student-Datensatze der angegebenen ID aus der DB laden

		try (Connection conn = DriverManager.getConnection(dbUrl, userName, password)) {
			String query = "select id , name , areaCode , city , birthDate , gender , language , xml , html , fxml , comment "
					+ " from Students " + " where id = ? ";
			System.out.println("selectById: " + query);
			// ein Statement das Parameter unterstützt
			PreparedStatement stmt = conn.prepareStatement(query);
			// jetzt Wert für den Parameter setzten (Nummerierung beginnt bei 1)
			stmt.setInt(1, id);
			// dann erst ausführen
			ResultSet result = stmt.executeQuery();
			// wenn es keinen Nächsten Datensatz gibt -> Exception werfen
			if (!result.next()) {
				throw new StudentRepositoryException("Student mit ID " + id + " existiert nicht");
			}
			Student entity = readStudent(result);
			return entity;

		} catch (Exception e) {
			System.err.println("Fehler beim Laden eines Datensatzes");
			e.printStackTrace();
			throw new StudentRepositoryException("Fehler beim Laden eines Studenten-Datensatzes", e);
		}

	}

	@Override
	public int insertStudent(Student student) throws StudentRepositoryException {
		// insert into Students (name, areaCode, city, birthDate , gender, language,
		// xml, html, fxml, comment)
		try (Connection conn = DriverManager.getConnection(dbUrl, userName, password)) {

			// Statement erzeugen, das nach dem Ergebnis auch ein Resultset mit dem neuen
			// Keywert zurückliefert
			PreparedStatement stmt = conn.prepareStatement(INSERT_STUDENT_STATEMENT, Statement.RETURN_GENERATED_KEYS);
			setCommonParameters(student, stmt);

			// den Befehl ausführen, Ergebniss ist Anzahl der betroffenen Datensätze
			// -> muss 1 sein, wenn alles passt
			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected != 1) {
				System.out.println("Keine Datensätze vom Insert betroffen");
				throw new StudentRepositoryException("Es sind keine Dantensätze vom Insert betroffen");
			}
			// wenn alles gut gegangen ist, den KEy abholen
			ResultSet keys = stmt.getGeneratedKeys();
			if (keys.next()) {
				int id = keys.getInt(1);
				System.out.printf("Objekt eingefügt neue ID=%d\n", id);
				return id;
			} else {
				// Das sollte nicht passieren
				System.err.println("Objekt eingefügt, neue ID nicht bekannt!");
				throw new StudentRepositoryException(
						"Der Datensatz wurde eingefügt, die Id konnte aber nicht ermittelt werden");
			}

//			return rowsAffected;
		} catch (Exception e) {
			System.err.println("Fehler beim Einfügen eines Datensatzes");
			e.printStackTrace();
			throw new StudentRepositoryException("Fehler beim Einfügen eines Studenten-Datensatzes", e);
		}
	}

	private void setCommonParameters(Student student, PreparedStatement stmt) throws SQLException {
		// als 1. Parameter den Namen setzen
		stmt.setString(1, student.getName());
		// als 2. die PLZ
		stmt.setInt(2, student.getAreaCode());
		// usw.
		stmt.setString(3, student.getCity());
		// LocalDate: in Date umgewandelt
		stmt.setDate(4, Date.valueOf(student.getBirthDate()));
		// enum in Zeichenfolge umwandeln
		stmt.setString(5, student.getGender().toString());
		stmt.setString(6, student.getLanguage());

		// booleans
		stmt.setBoolean(7, student.isXml());
		stmt.setBoolean(8, student.isHtml());
		stmt.setBoolean(9, student.isFxml());

		stmt.setString(10, student.getComment());
	}

	@Override
	public void updateStudent(Student student) throws StudentRepositoryException {
		// einen Studenten ändern
		try (Connection conn = DriverManager.getConnection(dbUrl, userName, password)) {
			PreparedStatement stmt = conn.prepareStatement(UPDATE_STUDENT_STATEMENT);
			// alle Parameter setzen
			setCommonParameters(student, stmt);
			// ID setzen (bei Tabelle Students ist es der 11. Parameter
			stmt.setInt(11, student.getId());
			int count = stmt.executeUpdate();
			// wenn kein Datensatz betroffen ist, dann existiert der Student-Datensatz nicht
			// mehr
			if (count == 0) {
				throw new StudentRepositoryException("Student mit ID " + student.getId() + " existiert nicht");
			}
		} catch (Exception e) {
			System.err.println("Fehler beim Aktualisieren eines Datensatzes");
			e.printStackTrace();
			throw new StudentRepositoryException("Fehler beim Aktualisieren eines Studenten-Datensatzes", e);
		}
	}

	@Override
	public void deleteStudent(int id) throws StudentRepositoryException {
		// Den Studenten löschen
		try (Connection conn = DriverManager.getConnection(dbUrl, userName, password)) {
			PreparedStatement stmt = conn.prepareStatement(DELETE_STUDENT_STATEMENT);
			// die Id setzen
			stmt.setInt(1, id);
			// und ausführen
			int count = stmt.executeUpdate();
			if (count == 0) {
				throw new StudentRepositoryException("Student mit ID " + id + " existiert nicht");
			}

		} catch (Exception e) {
			System.err.println("Fehler beim Löschen eines Datensatzes");
			e.printStackTrace();
			throw new StudentRepositoryException("Fehler beim Löschen eines Studenten-Datensatzes", e);
		}
	}

	@Override
	public void updateStudents(List<Student> students) throws StudentRepositoryException {
		
		try (Connection conn = DriverManager.getConnection(dbUrl, userName, password)) {
			// alle Studenten aktualisieren, alle änderungen müssen in einer Transaktion laufen
			// autoCommit deaktivieren
			conn.setAutoCommit(false);
			PreparedStatement stmt = conn.prepareStatement(UPDATE_STUDENT_STATEMENT);
			
			try {
			for (Student student : students) {
				// Parameter-Werte setzen
				setCommonParameters(student, stmt);
				stmt.setInt(11, student.getId());
				// Update ausführen
				int count = stmt.executeUpdate();
				// wenn ein Student dabei ist der nicht mehr existiert
				if (count == 0) {
					throw new StudentRepositoryException("Student mit ID " + student.getId() + " existiert nicht");
				}
				
				System.out.println("... Aktualisierung OK");
			}
			// wenn wir hier angelangt sind -> ist alles gut gegangen, alle Änderungen wurden durchgeführt
			// -> Transaktion abschließen (commit)
			conn.commit();
			}catch (Exception inner) {
				// wenn innerhalb unserer Transaktion ein Fehler passiert 
				// rollback ausführen
				conn.rollback();
				throw new StudentRepositoryException("Fehler beim Aktualisierung der StudentListe", inner);
			}
		} catch (Exception e) {
			
			System.err.println("Fehler beim Ändern der Datenbank");
			e.printStackTrace();
			throw new StudentRepositoryException("Fehler beim Aktualisieren der StudentListe", e);
		}

	}
	
	
	
	
	private Student readStudent(ResultSet result) throws SQLException {
		
		Student entity = new Student();
		// Alle werte in die Properties schreiben
		entity.setId(result.getInt("id"));
		entity.setName(result.getString("name"));
		entity.setAreaCode(result.getInt("areaCode"));
		entity.setCity(result.getString("city"));
		entity.setLanguage(result.getString("language"));
		entity.setComment(result.getString("comment"));
		// booleans
		entity.setXml(result.getBoolean("xml"));
		entity.setHtml(result.getBoolean("html"));
		entity.setFxml(result.getBoolean("fxml"));
		// LocalDate: über date
		entity.setBirthDate(result.getDate("birthdate").toLocalDate());
		// Gender-Enum: aus der Zeichenfolge ermitteln
		String strGender = result.getString("gender");
		entity.setGender(Gender.valueOf(strGender));

		return entity;
	}

}
