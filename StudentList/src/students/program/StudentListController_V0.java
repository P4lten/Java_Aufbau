package students.program;

import java.util.List;

import common.MessageBox;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import students.repository.Student;
import students.repository.StudentRepository;
import students.repository.xml.StudentXmlRepository;

// Controller-Klasse, die ohne Observable-Properties und ohne Property-Binding auskommt
public class StudentListController_V0 {

	// Zugriff auf das Repository
	private StudentRepository repository;

	@FXML
	private Button btnAdd;

	@FXML
	private Button btnEdit;

	@FXML
	private Button btnDelete;

	@FXML
	private Button btnReload;

	// für ListView und Student-VBox
	@FXML
	private ListView<Student> lstStudents;

	@FXML
	private VBox boxStudent;

	@FXML
	private Label lblId;

	@FXML
	private Label lblName;

	@FXML
	private Label lblAreaCode;

	@FXML
	private Label lblCity;

	// Feld für das Objekt das in der Liste selektiert ist
	private Student selStudent;



	public void setRepositoryPath(String path) {
		repository = new StudentXmlRepository(path);
		reload();
	}

	@FXML
	private void initialize() {
		System.out.println("Student List initialize");
		// Anfangs-Initialisierung von disable und visible
		btnEdit.setDisable(true);
		btnDelete.setDisable(true);
		boxStudent.setVisible(false);

		// Handler für Änderung des Selection im Listview
		lstStudents.getSelectionModel().selectedItemProperty().addListener(
				// in der ObjectProperty das Student-Objekt setzen
				(o, oldStd, newStd) -> {
					// Änderung der Selection behandeln
					// das selektierte Objekt merken
					selStudent = newStd;
					if (selStudent != null) {
						lblId.setText(Integer.toString(selStudent.getId()));
						lblName.setText(selStudent.getName());
						lblAreaCode.setText(Integer.toString(selStudent.getAreaCode()));
						lblCity.setText(selStudent.getCity());
					} else {
						lblId.setText("");
						lblName.setText("");
						lblAreaCode.setText("");
						lblCity.setText("");
					}

					// den Edit-und Delete-Button disablen, wenn kein Student selektiert ist
					btnEdit.setDisable(selStudent == null);
					btnDelete.setDisable(selStudent == null);
					// die Sutdent-Box verbergen, wenn kein Student selektiert ist
					boxStudent.setVisible(selStudent != null);

				});
		
		// die Standard-ListCell zeigt für jedes Item die toString-Implementierung an
		// Student.toString ist zu umfassend -> unsere eigene ListcellFactory setzen
		// setCellFactory bekommt eine Callback-Implemntierung, die ein ListCell-Objekt liefert 
		// unsere Methode createStudentListCell passt zur Signatur dieses Callbacks
		lstStudents.setCellFactory(this::createStudentListCell);
	}

	@FXML
	public void addStudent() {
		try {
			// dialog ohne existierende Objekt anzeigen
			EditStudentWindow dlg = new EditStudentWindow();
			Student entity = dlg.showModal();
			if (entity != null) {
				// im Repository hinzufügen
				System.out.println("Neuer Student: " + entity);
				repository.insertStudent(entity);
				// kompletten Reload, damit die neue ID auch in der Liste angezeigt wird
				reload();
			}
		} catch (Exception e) {
			e.printStackTrace();
			MessageBox.show("Neuer Student", "Fehler beim Erfassen: " + e.getMessage(),
					AlertType.ERROR, ButtonType.OK);
		}

	}

	@FXML
	public void editStudent() {
		try {
			// den selektieren Stundenten bearbeiten
			System.out.println("Selektierter Student: " + selStudent);
			// den Dialog mit dem selektierten Studenten anzeigen
			EditStudentWindow dlg = new EditStudentWindow(selStudent);
			Student entity = dlg.showModal();
			if (entity != null) {
				System.out.println("Geänderter Student: " + entity);

				// im Repository ersetzen
				repository.updateStudent(entity);
				// im ListView aktualisieren
				// den Index des original-Objekts holen
				int index = lstStudents.getItems().indexOf(selStudent);
				// an diesem Index das geänderte Objekt setzen
				lstStudents.getItems().set(index, entity);

			}
		} catch (Exception e) {
			e.printStackTrace();
			MessageBox.show("Student bearbeiten", "Fehler beim Erfassen: " + e.getMessage(),
					AlertType.ERROR, ButtonType.OK);
		}
	}

	@FXML
	public void deleteStudent() {
		try {
			System.out.println("Student löschen: " + selStudent);
			// im repository löschen
			repository.deleteStudent(selStudent.getId());
			// aus den Items löschen
			lstStudents.getItems().remove(selStudent);
		} catch (Exception e) {
			e.printStackTrace();
			MessageBox.show("Student löschen", "Fehler beim Löschen: " + e.getMessage(),
					AlertType.ERROR, ButtonType.OK);
		}
	}

	@FXML
	public void reload() {
		try {
			List<Student> students = repository.selectAll();
			System.out.printf("%d Entities geladen\n", students.size());

			// die Studenten im Hauptfenster anzeigen:
			lstStudents.getItems().clear();
			for (Student student : students) {
				lstStudents.getItems().add(student);
			}

		} catch (Exception e) {
			e.printStackTrace();
			MessageBox.show("Reload", "Fehler beim Laden: " + e.getMessage(),
					AlertType.ERROR, ButtonType.OK);
		}
	}

	// Methode, deren Signatur zum Callback für die  ListCellFactory passt
	// -> kann als Method reference bei setCellFactory des ListView verwendet werden
	private ListCell<Student> createStudentListCell(ListView<Student> lstView) {
		System.out.println("createStudentListCell - CellFactory wird erzeugt");
		// eigene ListCell-Ableitung zurückliefern (mit anonymer Klasse)
		return new ListCell<Student>() {
			@Override
			protected void updateItem(Student item, boolean empty) {
				// Basis-Implementierung ausführen (Styles etc richtig setzen)
				super.updateItem(item, empty);
				// das Aussehen anpassen
				if (empty || item == null) {
					setText("");
				} else {
					setText(String.format("%s (%s %s, ID=%d)", 
							item.getName(), item.getAreaCode(), item.getCity(), item.getId()));
				}
			}

		};
	}
}
