package mitarbeiter.program;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mitarbeiter.repository.Mitarbeiter;


public class EditEmployeeWindow {
	
private Mitarbeiter editEmployee;
	
	public EditEmployeeWindow() {
		// DIalog ohne Objekt anzeigen
		// this.editStudent = null;
	}
	
	public EditEmployeeWindow(Mitarbeiter employee) {
		this.editEmployee = employee;
	}
	
	public Mitarbeiter showModal() throws IOException {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/employees/views/EditEmployee.fxml"));
		Parent root = loader.load();
		
		Scene scene = new Scene(root, 450, 500);
		
		// eine Stage für die Scene erzeugen, mit Rahmen usw.
		Stage stage = new Stage(StageStyle.DECORATED);
		stage.setScene(scene);
		// anpassen an add/edit
		EditEmployeeController controller = loader.getController();
		controller.setEmployee(editEmployee);
		stage.setTitle(editEmployee != null ? "Mitarbeiter bearbeiten" : "Neuer Mitarbeiter" );
		// solange dieses Fenster offen ist, kann kein anderes Fenster der Anwendung den Fokus bekommen
		stage.initModality(Modality.APPLICATION_MODAL);
		// anzeigen und warten bis das Fenster geschlossen wurde
		stage.showAndWait();
		Mitarbeiter result = controller.getEmployee();
		// den editierten Studenten zurückliefern
		return result;
	}
}


