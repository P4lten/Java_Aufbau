package battleship.program;




import static battleship.repository.Constants.DBPASSWORD;
import static battleship.repository.Constants.DBPATH;
import static battleship.repository.Constants.DBUSER;
import static battleship.repository.Constants.STARTWINDOWFXML;
import static battleship.repository.Constants.BATTLESHIPSSTYLES;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class BattleshipsMain extends Application {

	public static void main(String[] args) {
		
		launch(args);

	}

	
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		// jprie: Pfade zu Dateien an einer Stelle im Programm als Konstante speichern -> Constants.java und importieren
		FXMLLoader loader = new FXMLLoader(getClass().getResource(STARTWINDOWFXML));
		Parent root = loader.load();	
		
		// den Controller holen
		StartWindowController controller = loader.getController();
		// und das Repository setzen
		controller.setConnection(DBPATH,DBUSER,DBPASSWORD);	
		Scene scene = new Scene(root, 500, 600);
		// das CSS-Stylesheet laden und setzen
		scene.getStylesheets().add(getClass().getResource(BATTLESHIPSSTYLES).toExternalForm());
		// das ganze anzeigen
		primaryStage.setScene(scene);
		primaryStage.setTitle("Battleships");
		primaryStage.show();
		

	}
}
