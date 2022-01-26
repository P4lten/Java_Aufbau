package students.program;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

import common.MessageBox;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import students.repository.Student;
import students.repository.StudentRepository;
import students.repository.xml.StudentXmlRepository;

public class StudentTableController {

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

	// statt ListView haben wir hier einen TableView
	@FXML
//	private ListView<Student> lstStudents;
	private TableView<Student> tblStudents;

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
	
	// Spalten für den TableView
	  @FXML
	    private TableColumn<Student, Integer> colId;

	    @FXML
	    private TableColumn<Student, String> colName;

	    @FXML
	    private TableColumn<Student, LocalDate> colBirthdate;

	    @FXML
	    private TableColumn<Student, Boolean> colXml;

	    @FXML
	    private TableColumn<Student, Boolean> colHtml;

	// Alt: Feld für das Objekt das in der Liste selektiert ist
	// private Student selStudent;

	// Für das Property Binding:
	// 1) binden des ListView an eine ListProperty
	private ListProperty<Student> students;

	// 2) Binden des selektierten Student an eine ObjectProperty
	// statt einem "normalen" Feld -> eine Observable Property definieren
	// ObjectProperty informiert ihre Listener, wenn ein anderes Objekt gesetzt wird
	private ObjectProperty<Student> selectedStudent;

	public StudentTableController() {
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

	@FXML
	private void initialize() {
		System.out.println("Student List initialize");

		// Handler für Änderung des Selection im Listview
		tblStudents.getSelectionModel().selectedItemProperty().addListener(
				// in der ObjectProperty das Student-Objekt setzen
				(o, oldStd, newStd) -> {
					// in der Object-Property den selektierten Studenten setzen
					// damit wird ein Changed-Event von der Property ausgelöst
					selectedStudent.set(newStd);

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

	
		// Cell-Value-Factories für die Spalten: legen fest, welcher Wert für das Item
		// in der jeweiligen Spalte angezeigt werden soll
		colId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colName.setCellValueFactory(new PropertyValueFactory<>("name"));
		colBirthdate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
		colXml.setCellValueFactory(new PropertyValueFactory<>("xml"));
		colHtml.setCellValueFactory(new PropertyValueFactory<>("html"));
		
		// die Standard-TableCell zeigt für jeden Zellwert die toString-Implementierung an
		// für Datum und die Booleans eigene Implementierungen verwenden
		// Cellfactories
		colBirthdate.setCellFactory(this::createLocalDateCell);
		colXml.setCellFactory(this::createCheckBoxCell);
		colHtml.setCellFactory(this::createCheckBoxCell);

		// die Items des TableView an unsere ListProperty binden
		// statt items="${controller.studentList}"
		tblStudents.itemsProperty().bind(getStudentList());
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
	// TableCell-Factory-Methode für die Anzeige von einem LocalDate mit Regionaleinstellungen 
	private TableCell<Student, LocalDate> createLocalDateCell(TableColumn<Student, LocalDate> col){
		return new TableCell<Student, LocalDate>() {
			@Override
			protected void updateItem(LocalDate value, boolean empty) {
				// Basisklasse aufrufen
				super.updateItem(value, empty);
				if (empty || value == null) {
					setText("");
				} else {
					setText(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).format(value));
				}
				
			}
		};
	}

	
	private TableCell<Student, Boolean> createCheckBoxCell(TableColumn<Student, Boolean> col){
		// eine TableCell, die als Checkbox angezeigt wird
		return new CheckBoxTableCell<Student, Boolean>(index -> {
			// bei Bedarf den Wert für die jeweilige zelle abrufen
			return new SimpleBooleanProperty(col.getCellData(index));
		});
	}

}
