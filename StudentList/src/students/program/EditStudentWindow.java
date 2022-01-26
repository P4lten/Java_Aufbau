package students.program;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import students.repository.Student;

public class EditStudentWindow {
	
	private Student editStudent;
	
	public EditStudentWindow() {
		// Dialog ohne Objekt anzeigen 
		//this.editStudent = null;
	}
	public EditStudentWindow(Student student) {
		this.editStudent = student;
		
	}

	public Student showModal() throws IOException {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/students/views/EditStudent.fxml"));
		Parent root = loader.load();
		
		Scene scene = new Scene(root, 450, 500);
		
		// eine Stage für die Scene erzeugen, mit Rahmen usw.
		Stage stage = new Stage(StageStyle.DECORATED);
		stage.setScene(scene);
		// anpassen an add/edit
		EditStudentController controller = loader.getController();
		controller.setStudent(editStudent);
		stage.setTitle(editStudent != null ? "Student bearbeiten" : "Neuer Student" );
		// solange dieses Fenster offen ist, kann kein anderes Fenster der Anwendung den Fokus bekommen
		stage.initModality(Modality.APPLICATION_MODAL);
		// anzeigen und warten bis das Fenster geschlossen wurde
		stage.showAndWait();
		Student result = controller.getStudent();
		// den editierten Studenten zurückliefern
		return result;
	}
}
