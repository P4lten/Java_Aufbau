package mitarbeiter.repository.xml;

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
import mitarbeiter.repository.Mitarbeiter;
import mitarbeiter.repository.MitarbeiterRepository;
import mitarbeiter.repository.MitarbeiterRepositoryException;

public class MitarbeiterXmlRepository implements MitarbeiterRepository {

	private String repoPath;

	private MitarbeiterCollection employeeColl;

	public MitarbeiterXmlRepository(String path) {
		repoPath = path;
	}

	@Override
	public List <Mitarbeiter> selectAll() throws MitarbeiterRepositoryException {
		// Daten laden
		employeeColl = loadData(repoPath);
		return employeeColl.getEmployees();
	}

	@Override
	public Mitarbeiter selectById(int id) throws MitarbeiterRepositoryException {
		if (employeeColl == null) {
			throw new MitarbeiterRepositoryException("Das Repository wurde noch nicht geladen");
		}
		Optional<Mitarbeiter> employee = employeeColl.getEmployees().stream().filter(e -> e.getMitarbeiterId() == id)
				.findFirst();
		return employee.orElseThrow(
				() -> new MitarbeiterRepositoryException("Das Objekt mit Id " + id + " wurde nicht gefunden"));

	}

	@Override
	public int insertEmployee(Mitarbeiter employee) throws MitarbeiterRepositoryException {
		// nächste ID für das Mitarbeiter-Objekt verwenden
		employee.setMitarbeiterId(employeeColl.incrementNextEmployee());
		// Objekt im Repository hinzufügen
		employeeColl.getEmployees().add(employee);
		// und alles speichern
		saveData();
		// ID des neuen Objekts zurückliefern
		return employee.getMitarbeiterId();
	}

	@Override
	public void updateEmployee(Mitarbeiter employee) throws MitarbeiterRepositoryException {
		Mitarbeiter orig = selectById(employee.getMitarbeiterId());
		// wenn nicht (mehr) vorhanden -> Exception
		if (orig == null) {
			new MitarbeiterRepositoryException(
					"Das Objekt mit Id " + employee.getMitarbeiterId() + " wurde nicht gefunden");
		}
		// den Index des Objekts in der Liste suchen
		int index = employeeColl.getEmployees().indexOf(orig);
		// und das geänderte Objekt an diesem Index setzen
		employeeColl.getEmployees().set(index, employee);
		// wieder alles speichern
		saveData();

	}

	@Override
	public void deleteEmployee(int id) throws MitarbeiterRepositoryException {
		Mitarbeiter orig = selectById(id);
		// wenn nicht (mehr) vorhanden -> Exception
		if (orig == null) {
			new MitarbeiterRepositoryException("Das Objekt mit Id " + id + " wurde nicht gefunden");
		}
		// das Objekt aus der Liste entfernen
		employeeColl.getEmployees().remove(orig);
		// und alles speichern
		saveData();
	}

	// MitarbeiterCollection aus dem XML File laden oder neue MitarbeiterCollection
	// erzeugen
	private MitarbeiterCollection loadData(String repoPath) throws MitarbeiterRepositoryException {
		File repoFile = new File(repoPath);
		// wenn das File noch nicht existiert
		if (!repoFile.exists()) {
			// neue MitarbeiterCollection erzeugen
			return new MitarbeiterCollection();
		} else {
			// sonst: wenn das File existiert Repository aus XML File einlesen
			try (InputStreamReader reader = new InputStreamReader(new FileInputStream(repoFile), "UTF-8")) {

				// JAXBContext erzeugen und Daten laden
				JAXBContext ctx = JAXBContext.newInstance(MitarbeiterCollection.class);
				Unmarshaller u = ctx.createUnmarshaller();

				return (MitarbeiterCollection) u.unmarshal(new File(repoPath));
			} catch (JAXBException | IOException e) {
				throw new MitarbeiterRepositoryException("Fehler beim Laden des XML-Repository", e);
			}
		}

	}

	// Save the inventory to the XML file
	private void saveData() throws MitarbeiterRepositoryException {
		{
			try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(repoPath), "UTF-8")) {
				// JAXBContext erzeugen und Daten speichern
				JAXBContext ctx = JAXBContext.newInstance(MitarbeiterCollection.class);
				Marshaller m = ctx.createMarshaller();
				m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
				m.marshal(employeeColl, writer);
			} catch (JAXBException | IOException e) {
				throw new MitarbeiterRepositoryException("Fehler beim Speichern des XML-Repository", e);
			}
		}

	}
}
