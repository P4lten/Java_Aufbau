package mitarbeiter.program;

import common.MessageBox;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import mitarbeiter.repository.Experte;
import mitarbeiter.repository.Manager;
import mitarbeiter.repository.Mitarbeiter;
import mitarbeiter.repository.Typ;

public class EditEmployeeController {

	@FXML
	private TextField txtId;
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtAreaCode;
	@FXML
	private TextField txtCity;
	@FXML
	private TextField txtSalary;
	@FXML
	private ToggleGroup grpTyp;
	@FXML
	private RadioButton rbExperte;
	@FXML
	private RadioButton rbManager;
	@FXML
	private TextField txtFachgebiet;
	@FXML
	private TextField txtBonus;
	@FXML
	private DatePicker dtpBirthdate;
	@FXML
	private DatePicker dtpEnterdate;
	@FXML
	private TextArea txtComment;
	@FXML
	private Button btnOk;
	@FXML
	private Button btnCancel;

	// den Student merken, der bearbeitet wird,
	private Mitarbeiter employeeResult;

	@FXML
	private void initialize() {
		System.out.println("Edit Employee initialize...");

		checkValid();
		// Handler für das Changed-Event der erforderlichen Felder hinzufügen
		txtName.textProperty().addListener((o, oldval, newval) -> checkValid());
		txtAreaCode.textProperty().addListener((o, oldval, newval) -> checkValid());
		txtCity.textProperty().addListener((o, oldval, newval) -> checkValid());
		txtSalary.textProperty().addListener((o, oldval, newval) -> checkValid());
		dtpBirthdate.valueProperty().addListener((o, oldval, newval) -> checkValid());
		dtpEnterdate.valueProperty().addListener((o, oldval, newval) -> checkValid());
		grpTyp.selectedToggleProperty().addListener((o, oldval, newval) -> checkValid());

		BooleanBinding isExperteBinding = Bindings.createBooleanBinding(
				// Callable: beliebigen Codeblock um den booleschen wert zu berechnen
				() -> rbExperte.isSelected(),
				// properties, von der wert abhängig ist
				rbExperte.selectedProperty());
		// und z.B. die visibleProperty von einem Control an das Binding binden
		
		BooleanBinding isManagerBinding = Bindings.createBooleanBinding(
				// Callable: beliebigen Codeblock um den booleschen wert zu berechnen
				() -> rbManager.isSelected(),
				// properties, von der wert abhängig ist
				rbManager.selectedProperty());
		// und z.B. die visibleProperty von einem Control an das Binding binden
		
		txtBonus.visibleProperty().bind(isManagerBinding);
		txtFachgebiet.visibleProperty().bind(isExperteBinding);
	}

	public void setEmployee(Mitarbeiter editEmployee) {
		// die Werte aus dem Objekt anzeigen
		if (editEmployee != null) {
			// Textfelder füllen
			txtId.setText(Integer.toString(editEmployee.getMitarbeiterId()));
			txtSalary.setText(Double.toString(editEmployee.getGehalt()));
			txtName.setText(editEmployee.getName());
			txtCity.setText(editEmployee.getCity());
			txtComment.setText(editEmployee.getComment());
			txtAreaCode.setText(Integer.toString(editEmployee.getAreaCode()));
//			txtFachgebiet.setText(editEmployee.getFachgebiet());
//			txtBonus.setText(Double.toString(editEmployee.getBonus()));

			// Date Picker: Property value ist vom Typ LocalDate
			dtpBirthdate.setValue(editEmployee.getGeburtsdatum());
			dtpBirthdate.setValue(editEmployee.getEintrittsdatum());

			System.out.println("setEmployeeMethode");

			// welche Art von mitarbeiter haben wir?
			if(editEmployee instanceof Experte) {
				// Typumwandlung
				Experte editExperte = (Experte)editEmployee;
				txtFachgebiet.setText(editExperte.getFachgebiet());
				// Radio-Button
				rbExperte.setSelected(true);
			}else if (editEmployee instanceof Manager){
				Manager editManager = (Manager)editEmployee;
				txtBonus.setText(Double.toString(editManager.getBonus()));
			}
			// wenn es ein "Normaler" Mitarbeiter ist
			else {
				//TODO Radio-Button für Mitarbeiter selektieren
			}
			
			
			// Radiobuttons
			if (editEmployee.getTyp() != null) {
				switch (editEmployee.getTyp()) {
				case EXPERTE -> rbExperte.setSelected(true);
				case MANAGER -> rbManager.setSelected(true);
				default -> rbExperte.setSelected(true);
				}
			} else {
				rbExperte.setSelected(true);
			}
		}
	}

	public Mitarbeiter getEmployee() {
		return employeeResult;
	}

	// Event Listener on Button[#btnOk].onAction
	@FXML
	public void onOk(ActionEvent event) {

		try {
			//employeeResult = new Mitarbeiter();
			// Je nachdem welcher Typ selectiert ist wird, ein passendes Objekt erzeugen
			
			if(rbManager.isSelected()) {
				Manager m = new Manager();
				double bonus = Double.parseDouble(txtBonus.getText());
				m.setBonus(bonus);
				employeeResult = m;
				
			}else if (rbExperte.isSelected()) {
				Experte e = new Experte();
				e.setFachgebiet(txtFachgebiet.getText());
				employeeResult = e;
				
			}else {
				employeeResult = new Mitarbeiter();
			}
			
			// die Werte aus den Controls setzen
			if (txtId.getText() != null && !txtId.getText().isEmpty()) {
				employeeResult.setMitarbeiterId(Integer.parseInt(txtId.getText()));
			}
			employeeResult.setAreaCode(Integer.parseInt(txtAreaCode.getText()));
			employeeResult.setName(txtName.getText());
			employeeResult.setCity(txtCity.getText());
			employeeResult.setComment(txtComment.getText());
			employeeResult.setGehalt(Integer.parseInt(txtSalary.getText()));

			employeeResult.setGeburtsdatum(dtpBirthdate.getValue());
			employeeResult.setEintrittsdatum(dtpEnterdate.getValue());

			if (rbExperte.isSelected()) {
				employeeResult.setTyp(Typ.EXPERTE);
			} else if (rbManager.isSelected()) {
				employeeResult.setTyp(Typ.MANAGER);
			}

			// das Fenster schließen (damit kehrt showAndWait zurück)
			((Stage) txtId.getScene().getWindow()).close();
		} catch (Exception e) {
			// das Mitarbeiter-Objekt kann nicht verwendet werden
			employeeResult = null;
			e.printStackTrace();
			MessageBox.show("Erfassen", "Fehler beim Erfassen: " + e.getMessage(), AlertType.ERROR, ButtonType.OK);
		}

	}

	// Event Listener on Button[#btnCancel].onAction
	@FXML
	public void onCancel(ActionEvent event) {
		// student bleibt null
		employeeResult = null;
		// das Fenster schließen (damit kehrt showAndWait zurück)
		((Stage) txtId.getScene().getWindow()).close();
	}

	private void checkValid() {
		boolean valid = isInputValid();
		if (valid) {
			btnOk.setDisable(false);
		} else {
			btnOk.setDisable(true);
		}

	}

	private boolean isInputValid() {
		boolean valid = txtName.getText() != null && !txtName.getText().isBlank() && txtAreaCode.getText() != null
				&& !txtAreaCode.getText().isBlank() && txtCity.getText() != null && !txtCity.getText().isBlank()
				&& !txtSalary.getText().isBlank() && dtpBirthdate.getValue() != null && dtpEnterdate.getValue() != null
				&& grpTyp.getSelectedToggle() != null;

		System.out.printf("Check Valid: isValid = %s\n", valid);
		return valid;

	}

}
