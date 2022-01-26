package students.program;

import java.util.List;

import common.MessageBox;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import students.repository.Student;
import students.repository.StudentRepository;
import students.repository.db.StudentDbRepository;
import students.repository.xml.StudentXmlRepository;

public class StudentListController {

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

	// Alt: Feld für das Objekt das in der Liste selektiert ist
	// private Student selStudent;

	// Für das Property Binding:
	// 1) binden des ListView an eine ListProperty
	private ListProperty<Student> students;

	// 2) Binden des selektierten Student an eine ObjectProperty
	// statt einem "normalen" Feld -> eine Observable Property definieren
	// ObjectProperty informiert ihre Listener, wenn ein anderes Objekt gesetzt wird
	private ObjectProperty<Student> selectedStudent;

	public StudentListController() {
		// die Properties für das Binding erzeugen
		students = new SimpleListProperty<>();
		selectedStudent = new SimpleObjectProperty<>();
	}

	// Property, die im FXML per Binding-Ausdruck gesetzt werden kann
	public ListProperty<Student> getStudentList() {
		return students;
	}

	// Observable Property für den SelectedStudent für Verwendung im FXML bereitstellen
	// a) getter
	public Student getSelectedStudent() {
		// den Student aus der ObjectProperty zurückliefern
		return selectedStudent.get();
	}

	// b) setter
	public void setSelectedStudent(Student selStudent) {
		// den Student in der ObjectProperty setzen
		selectedStudent.set(selStudent);
	}

	// c) die Property als solches
	public ObjectProperty<Student> selectedStudentProperty() {
		return selectedStudent;
	}

	public void setRepositoryPath(String path) {
		repository = new StudentXmlRepository(path);
		reload();
	}
	
	// 2. Möglichkeit für die Repository-Initialisierung
	public void setConnection(String dbUrl, String userName, String password) {
		repository = new StudentDbRepository(dbUrl, userName, password);
		reload();
	}

	@FXML
	private void initialize() {
		System.out.println("Student List initialize");
//		// Anfangs-Initialisierung von disable und visible ist beim Verwenden der Bindings nicht nötig 
//		btnEdit.setDisable(true);
//		btnDelete.setDisable(true);
//		boxStudent.setVisible(false);

		// Handler für Änderung des Selection im Listview
		lstStudents.getSelectionModel().selectedItemProperty().addListener(
				// in der ObjectProperty das Student-Objekt setzen
				(o, oldStd, newStd) -> {
					// in der Object-Property den selektierten Studenten setzen
					// damit wird ein Changed-Event von der Property ausgelöst
					selectedStudent.set(newStd);

					// Änderungen werden über Bindings automatisch angezeigt
//					// Änderung der Selection behandeln
//					// das selektierte Objekt merken
//					selStudent = newStd;
//					if (selStudent != null) {
//						lblId.setText(Integer.toString(selStudent.getId()));
//						lblName.setText(selStudent.getName());
//						lblAreaCode.setText(Integer.toString(selStudent.getAreaCode()));
//						lblCity.setText(selStudent.getCity());
//					} else {
//						lblId.setText("");
//						lblName.setText("");
//						lblAreaCode.setText("");
//						lblCity.setText("");
//					}
//
//					// den Edit-und Delete-Button disablen, wenn kein Student selektiert ist
//					btnEdit.setDisable(selStudent == null);
//					btnDelete.setDisable(selStudent == null);
//					// die Sutdent-Box verbergen, wenn kein Student selektiert ist
//					boxStudent.setVisible(selStudent != null);

				});

		// die disable-Properties an unsere Object Property binden
		// Buttons werden disabled, wenn kein Student selektiert ist
		btnEdit.disableProperty().bind(Bindings.isNull(selectedStudent));
		btnDelete.disableProperty().bind(Bindings.isNull(selectedStudent));
		// die visiblity Property ebenfalls
		// Box wird nur angezeigt, wenn ein Student selektiert ist
		boxStudent.visibleProperty().bind(Bindings.isNotNull(selectedStudent));

		// Text-Properties der Labels binden
		// statt Binding expression text="${controller.selectedStudent.id}"
		lblId.textProperty().bind(Bindings.selectString(selectedStudent, "id"));
		lblName.textProperty().bind(Bindings.selectString(selectedStudent, "name"));
		lblAreaCode.textProperty().bind(Bindings.selectString(selectedStudent, "areaCode"));
		lblCity.textProperty().bind(Bindings.selectString(selectedStudent, "city"));

		// die Standard-ListCell zeigt für jedes Item die toString-Implementierung an
		// Student.toString ist zu umfassend -> unsere eigene ListcellFactory setzen
		// setCellFactory bekommt eine Callback-Implemntierung, die ein ListCell-Objekt liefert
		// unsere Methode createStudentListCell passt zur Signatur dieses Callbacks
		lstStudents.setCellFactory(this::createStudentListCell);

		// die Items des ListView an unsere ListProperty binden
		// statt items="${controller.studentList}"
		lstStudents.itemsProperty().bind(getStudentList());
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
			// Neu: lokale Variable
			Student selStudent = getSelectedStudent();
			// den selektieren Stundenten bearbeiten
			System.out.println("Selektierter Student: " + selStudent);
			// den Dialog mit dem selektierten Studenten anzeigen
			EditStudentWindow dlg = new EditStudentWindow(selStudent);
			Student entity = dlg.showModal();
			if (entity != null) {
				System.out.println("Geänderter Student: " + entity);

				// im Repository ersetzen
				repository.updateStudent(entity);
				// im ListView aktualisieren, aber nicht mehr über die Items des ListView
				// sondern direkt über die List-Property -> der List View wird über das Binding
				// über Änderungen in der List-Property informiert
				// alter Code:
//				// den Index des original-Objekts holen
//				int index = lstStudents.getItems().indexOf(selStudent);
//				// an diesem Index das geänderte Objekt setzen
//				lstStudents.getItems().set(index, entity);

				// neuer Code
				// den Index des original-Objekts holen
				int index = students.indexOf(getSelectedStudent());
				// an diesem Index das geänderte Objekt setzen
				students.set(index, entity);

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

			System.out.println("Student löschen: " + getSelectedStudent());
			// im repository löschen
			// repository.deleteStudent(selStudent.getId());
			// Alt: aus den Items löschen
			// lstStudents.getItems().remove(selStudent);

			// im repository löschen
			repository.deleteStudent(getSelectedStudent().getId());
			// Neu: aus der List-proptery löschen
			students.remove(getSelectedStudent());
		} catch (Exception e) {
			e.printStackTrace();
			MessageBox.show("Student löschen", "Fehler beim Löschen: " + e.getMessage(),
					AlertType.ERROR, ButtonType.OK);
		}
	}

	@FXML
	public void reload() {
		try {
			List<Student> tmplist = repository.selectAll();
			System.out.printf("%d Entities geladen\n", tmplist.size());

			// die Studenten im Hauptfenster anzeigen:
			// Alt: Items löschen und neu einfügen
//			lstStudents.getItems().clear();
//			for (Student student : tmplist) {
//				lstStudents.getItems().add(student);
//			}

			// neu: der List-Property eine neue Collection verpassen
			this.students.set(FXCollections.observableArrayList(tmplist));
		} catch (Exception e) {
			e.printStackTrace();
			MessageBox.show("Reload", "Fehler beim Laden: " + e.getMessage(),
					AlertType.ERROR, ButtonType.OK);
		}
	}

	@FXML
	public void onClicked(MouseEvent me) {
		System.out.println("Mouse clicked");
		// wenn die Maus 2x geklickt wurde
		if (me.getClickCount() == 2) {
			editStudent();
		}
	}

	@FXML
	public void onKey(KeyEvent ke) {
		System.out.printf("Key pressed: KeyCode=%s\n", ke.getCode());

		switch (ke.getCode()) {
		case ENTER -> editStudent();
		case DELETE -> {
			// nach Rückfrage löschen
			if (MessageBox.show("Student löschen", "Den selektierten Datensatz löschen?",
					AlertType.CONFIRMATION, ButtonType.OK, ButtonType.CANCEL) == ButtonType.OK) {
				deleteStudent();
			}
		}
		default -> System.out.println("Keine Aktion zugeordnet");
		}

	}

	// Methode, deren Signatur zum Callback für die ListCellFactory passt
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
