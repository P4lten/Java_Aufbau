package intro;


import java.net.URL;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

// JavaFX-Applikationsklasse muss von der Java Fx-Apllikationsklasse ableiten
public class IntroMain extends Application {

	public static void main(String[] args) {
		// die Methode erzeugt ein Objekt von unserer Applikationsklasse,
		// bereitet alles vor und ruft dann unsere start-Implementierung auf
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// den Viewe erzeugen
		IntroView root = new IntroView();
		//Scene erstellen
		Scene scene = new Scene(root, 400, 300);
		// das Stylesheet bekanntgeben
		URL styleUrl = getClass().getResource("intro.css");
		scene.getStylesheets().add(styleUrl.toExternalForm());
		// und im Hauptfenster setzen
		primaryStage.setScene(scene);
		// titel setzen
		primaryStage.setTitle("Erstes Java FX Fenster");
		// und das hauptfenster anzeigen
		primaryStage.show();
		
	}

}
