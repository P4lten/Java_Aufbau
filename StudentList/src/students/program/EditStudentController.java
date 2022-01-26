package students.program;

import common.MessageBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import students.repository.Gender;
import students.repository.Student;

public class EditStudentController {
	@FXML
	private TextField txtId;
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtAreaCode;
	@FXML
	private TextField txtCity;
	@FXML
	private RadioButton rbMale;
	@FXML
	private ToggleGroup grpGender;
	@FXML
	private RadioButton rbFemale;
	@FXML
	private RadioButton rbOther;
	@FXML
	private DatePicker dtpBirthdate;
	@FXML
	private CheckBox cbXml;
	@FXML
	private CheckBox cbhtml;
	@FXML
	private CheckBox cbFxml;
	@FXML
	private ComboBox<String> cmbLanguage;
	@FXML
	private TextArea txtComment;
	@FXML
	private Button btnOk;
	@FXML
	private Button btnCancel;

	// den Studen merken, der bearbeitet wird,
	private Student studentResult;

	@FXML
	private void initialize() {
		System.out.println("Edit Student initialize...");
		cmbLanguage.getItems().add("Deutsch");
		cmbLanguage.getItems().add("English");
		cmbLanguage.getItems().add("Italiano");

		checkValid();
		// Handler für das Changed-Event der erforderlichen Felder hinzufügen
		txtName.textProperty().addListener((o, oldval, newval) -> checkValid());
		txtAreaCode.textProperty().addListener((o, oldval, newval) -> checkValid());
		txtCity.textProperty().addListener((o, oldval, newval) -> checkValid());
		dtpBirthdate.valueProperty().addListener((o, oldval, newval) -> checkValid());
		cmbLanguage.valueProperty().addListener((o, oldval, newval) -> checkValid());
		grpGender.selectedToggleProperty().addListener((o, oldval, newval) -> checkValid());

	}

	public void setStudent(Student editStudent) {
		// die Werte aus dem Objekt anzeigen
		if (editStudent != null) {
			// Textfelder füllen
			txtId.setText(Integer.toString(editStudent.getId()));
			txtName.setText(editStudent.getName());
			txtCity.setText(editStudent.getCity());
			txtComment.setText(editStudent.getComment());
			txtAreaCode.setText(Integer.toString(editStudent.getAreaCode()));

			// Checkboxen
			cbXml.setSelected(editStudent.isXml());
			cbhtml.setSelected(editStudent.isHtml());
			cbFxml.setSelected(editStudent.isFxml());

			// Combobox: setValue bekommt einen Parameter vom gen. Typargument, bei uns String
			cmbLanguage.setValue(editStudent.getLanguage());

			// Date Picker: Property value ist vom Typ LocalDate
			dtpBirthdate.setValue(editStudent.getBirthDate());

			// Radiobuttons
			if (editStudent.getGender() != null) {
				switch (editStudent.getGender()) {
				case MALE -> rbMale.setSelected(true);
				case FEMALE -> rbFemale.setSelected(true);
				default -> rbOther.setSelected(true);
				}
			} else {
				rbOther.setSelected(true);
			}
		}
	}

	public Student getStudent() {
		return studentResult;
	}

	// Event Listener on Button[#btnOk].onAction
	@FXML
	public void onOk(ActionEvent event) {

		try {
			studentResult = new Student();
			// die Werte aus den Controls setzen
			if (txtId.getText() != null && !txtId.getText().isEmpty()) {
				studentResult.setId(Integer.parseInt(txtId.getText()));
			}
			studentResult.setAreaCode(Integer.parseInt(txtAreaCode.getText()));
			studentResult.setName(txtName.getText());
			studentResult.setCity(txtCity.getText());
			studentResult.setComment(txtComment.getText());

			studentResult.setXml(cbXml.isSelected());
			studentResult.setHtml(cbhtml.isSelected());
			studentResult.setFxml(cbFxml.isSelected());

			studentResult.setLanguage(cmbLanguage.getValue());

			studentResult.setBirthDate(dtpBirthdate.getValue());

			if (rbMale.isSelected()) {
				studentResult.setGender(Gender.MALE);
			} else if (rbFemale.isSelected()) {
				studentResult.setGender(Gender.FEMALE);
			} else {
				studentResult.setGender(Gender.OTHER);
			}

			// das Fenster schließen (damit kehrt showAndWait zurück)
			((Stage) txtId.getScene().getWindow()).close();
		} catch (Exception e) {
			// das Student-Objekt kann nicht verwendet werden 
			studentResult = null;
			e.printStackTrace();
			MessageBox.show("Erfassen", "Fehler beim Erfassen: " + e.getMessage(),
					AlertType.ERROR, ButtonType.OK);
		}

	}

	// Event Listener on Button[#btnCancel].onAction
	@FXML
	public void onCancel(ActionEvent event) {
		// student bleibt null
		studentResult = null;
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
		boolean valid = txtName.getText() != null && !txtName.getText().isBlank() &&
				txtAreaCode.getText() != null && !txtAreaCode.getText().isBlank() &&
				txtCity.getText() != null && !txtCity.getText().isBlank() &&
				dtpBirthdate.getValue() != null &&
				cmbLanguage.getValue() != null &&
				grpGender.getSelectedToggle() != null;

		System.out.printf("Check Valid: isValid = %s\n", valid);
		return valid;

	}
}
