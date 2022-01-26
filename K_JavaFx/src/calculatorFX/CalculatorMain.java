package calculatorFX;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CalculatorMain extends Application{

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// den Viewe erzeugen
		CalculatorFX root = new CalculatorFX();
		//Scene erstellen
		Scene scene = new Scene(root, 320, 500);
		primaryStage.setScene(scene);
		primaryStage.setTitle("CalculatorFX");
		// und das hauptfenster anzeigen
		primaryStage.show();
		
	}

}
