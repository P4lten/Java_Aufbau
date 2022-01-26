package students.repository.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Optional;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import students.repository.Student;
import students.repository.StudentRepository;
import students.repository.StudentRepositoryException;

public class StudentXmlRepository implements StudentRepository {

	private String repoPath;

	private StudentCollection studentColl;

	public StudentXmlRepository(String path) {
		repoPath = path;
		

	}

	@Override
	public List<Student> selectAll() throws StudentRepositoryException {
		// Daten laden
		studentColl = loadData(repoPath);
		// Studenten liste zurückliefern
		return studentColl.getStudents();

	}

	@Override
	public Student selectById(int id) throws StudentRepositoryException {
		if (studentColl == null) {
			throw new StudentRepositoryException("Das Repository wurde noch nicht geladen");
		}
		// Student-Objekt mit der gesuchten ID aus der Liste holen
		Optional<Student> student = studentColl.getStudents().stream().filter(s -> s.getId() == id).findFirst();
		// ... und zurückliefern bzw. Fehler werfen falls es nicht existiert
		return student.orElseThrow(
				() -> new StudentRepositoryException("Das Objekt mit Id " + id + " wurde nicht gefunden"));

	}

	@Override
	public int insertStudent(Student student) throws StudentRepositoryException {
		// nächste ID für das Student-Objekt verwenden
		student.setId(studentColl.incrementNextStudentId());
		// Objekt im Repository hinzufügen
		studentColl.getStudents().add(student);
		// und alles speichern
		saveData();
		// ID des neuen Objekts zurückliefern
		return student.getId();

	}

	@Override
	public void updateStudent(Student student) throws StudentRepositoryException {
		// Student-Objekt mit der gesuchten ID aus der Liste holen
		Student orig = selectById(student.getId());
		// wenn nicht (mehr) vorhanden -> Exception
		if (orig == null) {
			new StudentRepositoryException("Das Objekt mit Id " + student.getId() + " wurde nicht gefunden");
		}
		// den Index des Objekts in der Liste suchen
		int index = studentColl.getStudents().indexOf(orig);
		// und das geänderte Objekt an diesem Index setzen
		studentColl.getStudents().set(index, student);
		// wieder alles speichern
		saveData();

	}

	@Override
	public void deleteStudent(int id) throws StudentRepositoryException {
		// Student-Objekt mit der gesuchten ID aus der Liste holen
		Student orig = selectById(id);
		// wenn nicht (mehr) vorhanden -> Exception
		if (orig == null) {
			new StudentRepositoryException("Das Objekt mit Id " + id + " wurde nicht gefunden");
		}
		// das Objekt aus der Liste entfernen
		studentColl.getStudents().remove(orig);
		// und alles speichern
		saveData();

	}
	
	@Override
	public void updateStudents(List<Student> students) throws StudentRepositoryException {
		// TODO implementieren: 
		for(Student student: students) {
			// das Original-Objekt mit dieser ID suchen
			Student orig = selectById(student.getId());
			// wenn nicht (mehr) vorhanden -> Exception
			if (orig == null) {
				new StudentRepositoryException("Das Objekt mit Id " + student.getId() + " wurde nicht gefunden");
			}
			// sonst ersetzen
			// den Index des Objekts in der Liste suchen
			int index = studentColl.getStudents().indexOf(orig);
			// und das geänderte Objekt an diesem Index setzen
			studentColl.getStudents().set(index, student);
		}
		// wenn alles erledigt ist -> speichern
		saveData();
		
	}

	// StudentCollection aus dem XML File laden oder neue StudentCollection erzeugen
	private StudentCollection loadData(String repoPath) throws StudentRepositoryException {
		File repoFile = new File(repoPath);
		// wenn das File noch nicht existiert
		if (!repoFile.exists()) {
			// neue StudentCollection erzeugen
			return new StudentCollection();
		} else {
			// sonst: wenn das File existiert Repository aus XML File einlesen
			try (InputStreamReader reader = new InputStreamReader(new FileInputStream(repoFile), "UTF-8")) {

				// JAXBContext erzeugen und Daten laden
				JAXBContext ctx = JAXBContext.newInstance(StudentCollection.class);
				Unmarshaller u = ctx.createUnmarshaller();

				return (StudentCollection) u.unmarshal(new File(repoPath));
			} catch (JAXBException | IOException e) {
				throw new StudentRepositoryException("Fehler beim Laden des XML-Repository", e);
			}
		}

	}

	// Save the inventory to the XML file
	private void saveData() throws StudentRepositoryException {
		try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(repoPath), "UTF-8")) {
			// JAXBContext erzeugen und Daten speichern
			JAXBContext ctx = JAXBContext.newInstance(StudentCollection.class);
			Marshaller m = ctx.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.marshal(studentColl, writer);
		} catch (JAXBException | IOException e) {
			throw new StudentRepositoryException("Fehler beim Speichern des XML-Repository", e);
		}
	}

	

}
