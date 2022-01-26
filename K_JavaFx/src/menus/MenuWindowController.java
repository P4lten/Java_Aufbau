package menus;

import java.io.File;
import java.util.Optional;

//import common.MessageBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class MenuWindowController {
	@FXML
	private BorderPane mainPane;
	@FXML
	private MenuItem cmdQuit;
	@FXML
	private ToggleGroup grpSize;
	@FXML
	private TextArea txtMessages;
	@FXML
	private ContextMenu menuColor;

	private FileChooser fileDialog;

	public MenuWindowController() {
		fileDialog = new FileChooser();
		// Extenstions registrieren
		fileDialog.getExtensionFilters().add(new ExtensionFilter("Text files", "*.txt"));
		fileDialog.getExtensionFilters().add(new ExtensionFilter("XML files", "*.xml", "*.fxml"));
		fileDialog.getExtensionFilters().add(new ExtensionFilter("All files", "*.*"));
	}

	@FXML
	private void initialize() {
		appendText("Initialisierung abgeschlossen");
	}

	// Event Listener on MenuItem.onAction
	@FXML
	public void onOpen(ActionEvent event) {
		System.out.println("Konstruktor von MenuWindowController");
		// den FilceChooser mit dem Hauptfenster als Parent anzeigen
		File selFile = fileDialog.showOpenDialog(mainPane.getScene().getWindow());
		if (selFile != null) {
			appendText("Selektiertes File (open): " + selFile.getAbsolutePath());
		} else {
			appendText("Kein File selektiert (open)");
		}
	}

	// Event Listener on MenuItem.onAction
	@FXML
	public void onSave(ActionEvent event) {
		File selFile = fileDialog.showSaveDialog(mainPane.getScene().getWindow());
		if (selFile != null) {
			appendText("Selektiertes File (save): " + selFile.getAbsolutePath());
		} else {
			appendText("Kein File selektiert (save)");
		}
	}

	// Event Listener on MenuItem[#cmdQuit].onAction
	@FXML
	public void onQuit(ActionEvent event) {
		close();
	}

	// das Beenden durchführen (kann von außen aufgerufen werden
	public void close() {

		// mit Alert eine Meldung anzeigen
		Alert message = new Alert(AlertType.CONFIRMATION, "Möchtest du wirklich beenden?", ButtonType.YES,
				// Kopnstruktor erlaubt ein varargs-Array von Buttontypes
				ButtonType.NO, ButtonType.CANCEL);
		// den Header entfernen
		message.setHeaderText("");
		message.setTitle("Beenden");
		// die Meldung anzeigen und die Auswahl des Benutzers holen (blockiert bis ich
		// das Fenster schließe)
		Optional<ButtonType> result = message.showAndWait();
		if (result.isPresent()) {
			ButtonType button = result.get();
			if (button.equals(ButtonType.YES)) {
				System.out.println("Benutzer will beenden");
				// das Hauptfenster schließen
				((Stage) mainPane.getScene().getWindow()).close();

			} else {
				System.out.println("Benutzer will nicht beenden");
			}
		} else {
			System.out.println("Kein Button ausgewählt");
		}
	}

	// Event Listener on RadioMenuItem.onAction
	@FXML
	public void onSize(ActionEvent event) {
		String size = grpSize.getSelectedToggle().getUserData().toString();
		switch (size) {
		case "small":
			size = "12";
			break;
		case "medium":
			size = "16";
			break;
		case "large":
			size = "20";
			break;
		}
		// die Size als Style in der Textarea setzen
		txtMessages.setStyle("-fx-font-size: " + size + "px");
		appendText("Set size to " + size + "px");
	}

	// Event Listener on CheckMenuItem.onAction
	@FXML
	public void onBold(ActionEvent event) {
		// CSS-Klasse "bold" hinzufügen oder entfernen
		toggleClass("bold", ((CheckMenuItem) event.getSource()).isSelected());
	}

	// Event Listener on CheckMenuItem.onAction
	@FXML
	public void onItalic(ActionEvent event) {
		// CSS-Klasse "italic" hinzufügen oder entfernen
		toggleClass("italic", ((CheckMenuItem) event.getSource()).isSelected());
	}

	// Event Listener on MenuItem.onAction
	@FXML
	public void onAbout(ActionEvent event) {
		// mit Alert eine Meldung anzeigen
		Alert message = new Alert(AlertType.INFORMATION, "Demoprogramm zu Java FX Menüs", ButtonType.OK);
		// den Header entfernen
		message.setHeaderText("");
		message.setTitle("Über MenuMain");
		// die Meldung anzeigen (blockiert bis ich das Fenster schließe)
		message.show();
	}

	// Event Listener on MenuItem.onAction
	@FXML
	public void onColor(ActionEvent event) {
		// den Hintergrund der Textarea setzen
		String color = ((MenuItem) event.getSource()).getUserData().toString();

		// den Bereich mit dem Content der Textarea holen
		Region content = (Region) txtMessages.lookup(".content");
		BackgroundFill bgFill = new BackgroundFill(Paint.valueOf(color), // die Farbe
				CornerRadii.EMPTY, // keine abgerundeten Ecken
				Insets.EMPTY); // keine Einrückung
//				new Insets(2)); // 2px Abstand rundherum

		content.setBackground(new Background(bgFill));

		appendText("Set bg-color to " + color);
	}

	private void appendText(String text) {
		System.out.println(text);
		txtMessages.setText(txtMessages.getText() + "\n" + text);
	}

	private void toggleClass(String clName, boolean set) {
		// wenn die Klasse hinzugefügt werden soll:
		if (set) {
			// in der Style-Class-Liste hinzufügen
			txtMessages.getStyleClass().add(clName);
			appendText("Added class " + clName);
		} else {
			// sonst: aus der Style-Class-Liste entfernen
			txtMessages.getStyleClass().remove(clName);
			appendText("Removed class " + clName);
		}
	}
}
