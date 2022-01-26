package mitarbeiter.program;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class MitarbeiterMain extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	
	
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/employees/views/EmployeeTableView.fxml"));
		Parent root = loader.load();
//		Parent root = (Parent) 
//				FXMLLoader.load(getClass().getResource("/employees/views/EmployeeTableView.fxml"));
		// den Controller holne
		EmployeeTableController controller = loader.getController();
		// und das Repository setzen
		controller.setConnection("jdbc:mariadb://localhost/EmployeeDB2", "root", "");
		
		Scene scene = new Scene(root, 500, 750);
		// das CSS-Stylesheet laden und setzen
		scene.getStylesheets().add(getClass().getResource("/employees/views/employeeStyles.css").toExternalForm());
		// das ganze anzeigen
		primaryStage.setScene(scene);
		primaryStage.setTitle("Mitarbeiter JDBC Demo");
		primaryStage.show();

	}
	
	
	
}
