package mitarbeiter.program;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import mitarbeiter.repository.Mitarbeiter;
import mitarbeiter.repository.MitarbeiterRepository;
import mitarbeiter.repository.Typ;
import mitarbeiter.repository.xml.MitarbeiterXmlRepository;

public class EmployeeTableController {

	private MitarbeiterRepository repository;

	@FXML
	private Button btnAdd;

	@FXML
	private Button btnEdit;

	@FXML
	private Button btnDelete;

	@FXML
	private Button btnReload;

	@FXML
	private TableView<Mitarbeiter> tblEmployees;

	@FXML
	private VBox boxEmployee;

	@FXML
	private Label lblTyp;

	@FXML
	private Label lblId;

	@FXML
	private Label lblName;

	@FXML
	private Label lblAreaCode;

	@FXML
	private Label lblCity;

	@FXML
	private Label lblBirthdate;

	@FXML
	private Label lblEntry;

	@FXML
	private Label lblSalary;

	@FXML
	private Label lblComment;

	// Spalten für TableView
	@FXML
	private TableColumn<Mitarbeiter, Integer> colId;

	@FXML
	private TableColumn<Mitarbeiter, String> colName;

	@FXML
	private TableColumn<Mitarbeiter, LocalDate> colBirthdate;

	@FXML
	private TableColumn<Mitarbeiter, LocalDate> colEntry;
	
	@FXML
	private TableColumn<Mitarbeiter, Typ> colTyp;

	private ListProperty<Mitarbeiter> employees;

	private ObjectProperty<Mitarbeiter> selectedEmployee;

	public EmployeeTableController() {
		// die Properties für das Binding erzeugen
		employees = new SimpleListProperty<>();
		selectedEmployee = new SimpleObjectProperty<>();
	}

	// Property, die im FXML per Binding-Ausdruck gesetzt werden kann
	public ListProperty<Mitarbeiter> getEmployeeList() {
		return employees;
	}

	// getter
	public Mitarbeiter getSelcetedEmployee() {
		// den Student aus der ObjectProperty zurückliefern
		return selectedEmployee.get();
	}

	// setter
	public void setSelectedEmployee(Mitarbeiter selEmployee) {
		// den Student in der ObjectProperty setzen
		selectedEmployee.set(selEmployee);

	}

	// die Property als solches
	public ObjectProperty<Mitarbeiter> selectedEmployeeProperty() {
		return selectedEmployee;
	}

	public void setRepositoryPath(String path) {
		repository = new MitarbeiterXmlRepository(path);
		reload();
	}

	@FXML
	public void onClicked(MouseEvent me) {
		System.out.println("mouse clicked");
		// wenn die Maus 2x gecklickt wurde
		if (me.getClickCount() == 2) {
			editEmployee();
		}
	}

	@FXML
	public void onKey(KeyEvent ke) {
		System.out.printf("Key pressed: KeyCode=%s\n", ke.getCode());

		switch (ke.getCode()) {
		case ENTER -> editEmployee();
		case DELETE -> {
			if (MessageBox.show("Mitarbeiter löschen", "Diesen Datensatz löschen?", AlertType.CONFIRMATION,
					ButtonType.OK, ButtonType.CANCEL) == ButtonType.OK) {
				deleteEmployee();
			}
		}

		default -> System.out.println("Keine Aktion zugeordnet");

		}
	}

	@FXML
	private void initialize() {
		System.out.println("Mitarbeiter Table initialize");
		// handler für Änderunge des Selection im Listview
		tblEmployees.getSelectionModel().selectedItemProperty().addListener
		// in der ObjectProperty das Mitarbeiter-Objekt setzen
		((o, oldMa, newMa) -> {
			// in der Object-Property den selektierten Mitarbeiter setzten
			// damit wird ein Changed-Event von der Property ausgelöst
			selectedEmployee.set(newMa);

		});

		// die disable-Properties an unsere Object Property binden
		// buttons werden disabled, wenn kein Student selektiert ist
		btnEdit.disableProperty().bind(Bindings.isNull(selectedEmployee));
		btnDelete.disableProperty().bind(Bindings.isNull(selectedEmployee));
		// die visibility Property ebenfalls
		// die box wird nur angezeigt, wenn ein Mitarbeiter selektiert ist
		boxEmployee.visibleProperty().bind(Bindings.isNotNull(selectedEmployee));
		
		
		
		
		lblTyp.textProperty().bind(Bindings.selectString(selectedEmployee, "typ"));
		lblId.textProperty().bind(Bindings.selectString(selectedEmployee, "mitarbeiterId"));
		lblName.textProperty().bind(Bindings.selectString(selectedEmployee, "name"));
		lblAreaCode.textProperty().bind(Bindings.selectString(selectedEmployee, "areaCode"));
		lblCity.textProperty().bind(Bindings.selectString(selectedEmployee, "city"));
		lblSalary.textProperty().bind(Bindings.selectString(selectedEmployee, "gehalt"));
		lblComment.textProperty().bind(Bindings.selectString(selectedEmployee, "comment"));
		lblBirthdate.textProperty().bind(Bindings.selectString(selectedEmployee, "geburtsdatum"));
		lblEntry.textProperty().bind(Bindings.selectString(selectedEmployee, "eintrittsdatum"));
		

		// die Standard-ListCell zeigt für jedes Item die toString-implementierung an
		// Student.toString ist zu umfassend -> unsere eigene ListcellFactory setzen
		// setCellFactory bekommt eine Callback-Implementierung, die eine
		// List-Cell-Implementierung liefert
		// unsere Methode createStudentListCell passt zur Signatur dieses Callbacks

		// Cell.Value-Factories für die Spalten
		colId.setCellValueFactory(new PropertyValueFactory<>("mitarbeiterId"));
		colName.setCellValueFactory(new PropertyValueFactory<>("name"));
		colBirthdate.setCellValueFactory(new PropertyValueFactory<>("geburtsdatum"));
		colEntry.setCellValueFactory(new PropertyValueFactory<>("eintrittsdatum"));
		colTyp.setCellValueFactory(new PropertyValueFactory<>("typ"));

		// die Standard-ListCell zeigt für jedes Item die toString-implementierung an
		// für datum und die Booleans eigenen Implementierung verwenden
		// Cellfactories
		colBirthdate.setCellFactory(this::createLocalDateCell);
		colEntry.setCellFactory(this::createLocalDateCell);

		// die Items des TableView an unsere ListProperty binden
		// statt items="${controller.studentList}"
		tblEmployees.itemsProperty().bind(getEmployeeList());

	}

	public void addEmployee() {
		try {
			// dialog ohne existierende Objekt anzeigen
			EditEmployeeWindow dlg = new EditEmployeeWindow();
			Mitarbeiter entity = dlg.showModal();
			if (entity != null) {
				// im Repository hinzufügen
				System.out.println("Neuer Mitarbeiter: " + entity);
				repository.insertEmployee(entity);
				// kompletten Reload, damit die neue ID auch in der Liste angzeigt wird
				reload();
			}
		} catch (Exception e) {
			e.printStackTrace();
			MessageBox.show("Neuer Mitarbeiter", "Fehler beim Hinzufügen: " + e.getMessage(), AlertType.ERROR,
					ButtonType.OK);
		}

	}

	@FXML
	public void editEmployee() {
		try {
			// Neu: lokale Variable
			Mitarbeiter selEmployee = getSelcetedEmployee();
			// den selektierten Studenten bearbeiten
			System.out.println("Selectierter Mitarbeiter: " + selEmployee);
			// den Dialog mit dem selektierten Studenten anzeigen
			EditEmployeeWindow dlg = new EditEmployeeWindow(selEmployee);
			Mitarbeiter entity = dlg.showModal();
			if (entity != null) {
				System.out.println("Geänderter Mitarbeiter: " + entity);
				// : im Repository hinzufügen
				repository.updateEmployee(entity);
				// im ListView aktualisieren, aber nicht über die Items des Listview
				// sondern direkt über die List-Property -> der List View wird über das Binding
				// Über Änderungen in der List-Property informiert
//					// den Index des original-Objekts holen
//					int index = lstStudents.getItems().indexOf(selStudent);
//					// an diesem Index das geänderte Objekt setzen
//					lstStudents.getItems().set(index, entity);

				// neuer Code
				// den Index des originalObjekts holen
				int index = employees.indexOf(getSelcetedEmployee());
				// an diesem Index das geänderte Objekt setzen
				employees.set(index, entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
			MessageBox.show("Mitarbeiter bearbeiten", "Fehler beim Erfassen: " + e.getMessage(), AlertType.ERROR,
					ButtonType.OK);
		}
	}

	public void deleteEmployee() {
		try {
			System.out.println("Mitarbeiter löschen: " + getSelcetedEmployee());
			// repository.deleteStudent(selStudent.getId());
			// Alt: aus den Items löschen
			// lstStudents.getItems().remove(selStudent);

			// im repository löschen
			repository.deleteEmployee(getSelcetedEmployee().getMitarbeiterId());
			// Neu: aus der List.Property löschen
			employees.remove(getSelcetedEmployee());
		} catch (Exception e) {
			e.printStackTrace();
			MessageBox.show("Mitarbeiter löschen", "Fehler beim Löschen: " + e.getMessage(), AlertType.ERROR,
					ButtonType.OK);
		}
	}

	@FXML
	public void reload() {
		try {
			List<Mitarbeiter> tmplist = repository.selectAll();
			System.out.printf("%d Entities geladen\n", tmplist.size());
			// neu der List-Property eine neue Collection verpassen
			this.employees.set(FXCollections.observableArrayList(tmplist));
			System.out.printf("%d Entities geladen\n", employees.size());
		} catch (Exception e) {
			e.printStackTrace();
			MessageBox.show("Reload", "Fehler beim Laden: " + e.getMessage(), AlertType.ERROR, ButtonType.OK);
		}
	}

	// TableCell-Factory-Methode für die Anzeige von einem LocalDate mit
	// Regionaleinstellungen
	private TableCell<Mitarbeiter, LocalDate> createLocalDateCell(TableColumn<Mitarbeiter, LocalDate> col) {
		return new TableCell<Mitarbeiter, LocalDate>() {
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

//		private TableCell<Mitarbeiter, Boolean> createCheckboxCell(TableColumn<Mitarbeiter,Boolean> col){
//			// eine TableCell, die als Checkbox angezeigt wird
//			return new CheckBoxTableCell<Mitarbeiter,Boolean>(index -> {
//				// bei Bedarf den Wert für die jeweilige zelle abrufen
//				return new SimpleBooleanProperty(col.getCellData(index));
//			});
//		}

}
