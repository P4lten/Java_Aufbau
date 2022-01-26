package students.program;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StudentDbMain extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/students/views/StudentListView.fxml"));
		Parent root = loader.load();
//		Parent root = (Parent) 
//				FXMLLoader.load(getClass().getResource("/students/views/StudentListView.fxml"));
		// den Controller holne
		StudentListController controller = loader.getController();
		// und das Repository setzen
		controller.setConnection("jdbc:mariadb://localhost/CourseDb", "root", "");
		
		Scene scene = new Scene(root, 800, 750);
		// das CSS-Stylesheet laden und setzen
		scene.getStylesheets().add(getClass().getResource("/students/views/studentStyles.css").toExternalForm());
		// das ganze anzeigen
		primaryStage.setScene(scene);
		primaryStage.setTitle("Studend JDBC Demo");
		primaryStage.show();

	}

}
