package test;

import java.time.LocalDate;

import mitarbeiter.repository.Mitarbeiter;
import mitarbeiter.repository.MitarbeiterRepository;
import mitarbeiter.repository.MitarbeiterRepositoryException;
import mitarbeiter.repository.Typ;
import mitarbeiter.repository.db.EmployeeDbRepository;


public class Test {

	public static void main(String[] args) {


		MitarbeiterRepository repo = new EmployeeDbRepository("jdbc:mariadb://localhost/EmployeeDB2", "root", "");

		testInsert(repo);
	}

	private static void testInsert(MitarbeiterRepository repo) {
		try {
			Mitarbeiter ma1 = new Mitarbeiter();
			ma1.setName("Peter");
			ma1.setAreaCode(1010);
			ma1.setCity("Salzburg");
			ma1.setComment("hallo");
			ma1.setGeburtsdatum(LocalDate.of(2001, 1, 1));
			ma1.setEintrittsdatum(LocalDate.of(2001, 1, 1));
			ma1.setExperte(true);
			ma1.setTyp(Typ.EXPERTE);
			ma1.setGehalt(52000);
			int employeetId = repo.insertEmployee(ma1);
			System.out.println("Ergebnis von Insert: " + employeetId);

		} catch (MitarbeiterRepositoryException e) {
			System.out.println("Fehler beim Einfügen: " + e.getMessage());
			if (e.getCause() != null) {
				System.out.println("\t" + e.getCause().getMessage());
			}
		}
	}
	
}
