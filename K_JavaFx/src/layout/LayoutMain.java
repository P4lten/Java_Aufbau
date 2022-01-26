package layout;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class LayoutMain extends Application {

	public void start(javafx.stage.Stage primaryStage) throws Exception {
		
		
		try { 
			// den Root des Scene Graph aus dem FXML-File laden  
			Parent root = (Parent) 
					FXMLLoader.load(getClass().getResource("LayoutView.fxml"));
			Scene scene = new Scene(root, 400, 300);
			// das CSS-Stylesheet laden und setzen
			scene.getStylesheets().add(getClass().getResource("layout.css").toExternalForm());
			// das ganze anzeigen
			primaryStage.setScene(scene);
			primaryStage.setTitle("Layout demo");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		launch(args);

	}

}
