package introFxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class IntroViewController {

	@FXML // bewirkt das beim Ladem doeses Attribut mit dem Control das die fx:id="txtName initialisiert wird
	private TextField txtName;

	@FXML
	private ListView<String> lvMessages;

	public IntroViewController() {
		// hier ist der View noch nicht fertig, die FXML-Felder sind noch null
		System.out.println("Konstruktor von IntroViewController");
	}
	@FXML
	private void initialize() {
		// hier ist der View fertig generiert, alle Felder sind initialisiert
		lvMessages.getItems().add("FXML View ist fertig");
	}



	// Eventhandler für die Button-Klicks
	@FXML
	private void onButtonKlick(ActionEvent ae) {

		// den Button holen auf den geklickt wurde
		Node source = (Node) ae.getSource();
		String action = source.getUserData().toString();
		System.out.printf("Action von Objekt mit UserData " + action);
		switch (action) {
		case "KLICK1":
			System.out.println("Action für Button 1...");
			lvMessages.getItems().add("Hallo, " + txtName.getText());

			break;
		case "KLICK2":
			System.out.println("Action für Button 2...");
			lvMessages.getItems().add("Hey, " + txtName.getText());

			break;

		default:
			break;
		}
	}

}
