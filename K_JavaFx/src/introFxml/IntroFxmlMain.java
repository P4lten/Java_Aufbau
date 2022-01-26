package introFxml;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// unsere Applikationsklasse muss von der JavaFX-Applikationsklasse ableiten
public class IntroFxmlMain extends Application {

	public static void main(String[] args) {
		// die Methode erzeugt ein Objket von unserer Applikationsklasse,
		// bereitet alles vor und ruft dann unsere start-Implementierung auf
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// einen URL zum FXML-FIle holen (das FXML-File liegt im selben Package wie die Applikationsklasse
		URL fxmlUrl = getClass().getResource("IntroView.fxml");
		// den Control-Tree aus diesem FXML-Filel laden
		Parent root = (Parent) FXMLLoader.load(fxmlUrl);
		
		// Scene erstellen
		Scene scene = new Scene(root, 400, 300);
		// und im Hauptfenster setzen
		primaryStage.setScene(scene);
		// titel setzten
		primaryStage.setTitle("Erstes Java FX Fenster mit FXML");
		// und das hauptfenster anzeigen
		primaryStage.show();

	}

}
