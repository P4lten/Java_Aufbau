package intro;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class IntroView extends GridPane {

	private TextField txtName;
	private Button btnKlick1, btnKlick2;
	private ListView<String> lvlMessages;

	public IntroView() {
		// Abstand zwischen Controls
		setHgap(10);
		setVgap(10);
		

		// Abstand zum Rand
		setPadding(new Insets(20));

		// Controls erzeugen
		int row = 0;

		// Titel
		Label lblTitle = new Label();
		lblTitle.setText("Willkommen bei Java FX");
		lblTitle.setId("lblTitle"); // die ID für CSS setzen
		
		add(lblTitle,
				// Spalte und Zeile
				0, row,
				// Anzahl Spalten / Zeilen
				4, 1);

		setHalignment(lblTitle, HPos.CENTER);

		// 2.Zeile:Name
		row++;
		Label lblName = new Label("Dein Name: ");
		add(lblName, 0, row);
		add(txtName = new TextField(), 1, row, 2, 1);

		// 3. Zeile: Buttons
		row++;
		add(btnKlick1 = new Button("Klick mich"), 1, row);
		btnKlick1.setUserData("KLICK1");
		btnKlick1.setOnAction(this::onButtonKlick);

		add(btnKlick2 = new Button("Klick 2"), 2, row);
		btnKlick2.setUserData("KLICK2");
		btnKlick2.setPrefSize(100, 25);
		btnKlick2.setOnAction(this::onButtonKlick);
		
		// 4. Zeile: Listview
		row++;
		add(lvlMessages = new ListView<>(), 1, row, 2, 1);
		
		
		// ein bisscen später ausführen, wenn alles initialisiert ist
		Platform.runLater(() ->{
			lvlMessages.getItems().add("Initialisierung abgeschlossen");
		});
		
	}
	
	private void onButtonKlick(ActionEvent ae) {
		// den Button holen auf den geklickt wurde
		Node source = (Node) ae.getSource();
		String action = source.getUserData().toString();
		System.out.println("Action von Objekt mit UserData " + action);
		switch (action) {
		case "KLICK1":
			System.out.println("Action für Button 1...");
			lvlMessages.getItems().add("Hallo, + " + txtName.getText());
			break;
		case "KLICK2":
			System.out.println("Action für Button 2...");
			lvlMessages.getItems().add("Hey, " + txtName.getText());
		default:
			break;
		}
	}

}
