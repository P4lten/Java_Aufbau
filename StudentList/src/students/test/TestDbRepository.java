package students.test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import students.repository.Gender;
import students.repository.Student;
import students.repository.StudentRepository;
import students.repository.StudentRepositoryException;
import students.repository.db.StudentDbRepository;
import students.repository.xml.StudentXmlRepository;

public class TestDbRepository {

	public static void main(String[] args) {

		StudentRepository repo = new StudentDbRepository("jdbc:mariadb://localhost/CourseDb", "root", "");

//		StudentRepository repo = new StudentXmlRepository("Repository.xml");
		
		
		//		testSelect(repo);
//		
		testInsert(repo);

//		testDelete(repo);

//		testUpdateStudents(repo, false);

//		testUpdateStudents(repo, false);

	}

	private static void testSelect(StudentRepository repo) {
		try {
			Student std1 = repo.selectById(1);
			System.out.println("Student geladen: " + std1);

			Student std2 = repo.selectById(100);
			System.out.println("Student geladen" + std2);
		} catch (StudentRepositoryException e) {
			System.out.println("Fehler beim Laden: " + e.getMessage());
			if (e.getCause() != null) {
				System.out.println("\t" + e.getCause().getMessage());
			}
		}
	}

	private static void testInsert(StudentRepository repo) {
		try {
			Student std1 = new Student();
			std1.setName("Peter");
			std1.setAreaCode(1010);
			std1.setCity("Salzburg");
			std1.setComment("hallöchen");
			std1.setBirthDate(LocalDate.of(2001, 1, 1));
			std1.setHtml(true);
			std1.setGender(Gender.MALE);
			std1.setLanguage("Soizburgerisch");
			int studentId = repo.insertStudent(std1);
			System.out.println("Ergebnis von Insert: " + studentId);

		} catch (StudentRepositoryException e) {
			System.out.println("Fehler beim Einfügen: " + e.getMessage());
			if (e.getCause() != null) {
				System.out.println("\t" + e.getCause().getMessage());
			}
		}
	}

	private static void testDelete(StudentRepository repo) {

		try {
			// geht max 1x gut, dann nicht mehr
			repo.deleteStudent(1);
			System.out.println("Student gelöscht");
		} catch (Exception e) {
			System.out.println("Fehler beim Löschen: ");
			e.printStackTrace();
		}
	}

	private static void testUpdateStudents(StudentRepository repo, boolean withError) {
		try {
			// Alle studenten laden
			List<Student> allStudents = repo.selectAll();
			// bei jedem Objekt etwas ändern
			for (Student student : allStudents) {
				String text = String.format("Student überprüft von Peter am %s", LocalDateTime.now());
				if (student.getComment() != null) {
					student.setComment(student.getComment() + "\n" + text);
				} else {
					student.setComment(text);
				}
			}
			// wenn wir einen Fehler simulieren sollen
			if (withError) {
				// den Namen des Letzten Datensatzes auf null setzen
				allStudents.get(allStudents.size() - 1).setName(null);
			}

			// alle auf einmal speichern
			repo.updateStudents(allStudents);

		} catch (Exception e) {
			System.err.println("Fehler beim Update der Student-Liste");
			e.printStackTrace();
		}
	}

}
