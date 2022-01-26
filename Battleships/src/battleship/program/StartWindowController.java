package battleship.program;

import java.io.IOException;
import java.util.List;
import battleship.repository.Player;
import battleship.repository.PlayerRepository;
import battleship.repository.db.PlayerDbRepository;
import common.MessageBox;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class StartWindowController {

	private PlayerRepository repository;

	@FXML
	private TextField txtName;

	@FXML
	private Button btnGameStart;

	@FXML
	private Button btnHighscore;

	@FXML
	private Button btnExit;

	@FXML
	private ComboBox<String> cmbDifficulty;

	private ListProperty<Player> players;

	private ObjectProperty<Player> selectedPlayer;
	
	private String difficulty;
	
	public String getDifficulty() {
		return difficulty;
	}

	public StartWindowController() {
		players = new SimpleListProperty<>();
		selectedPlayer = new SimpleObjectProperty<>();
	}

	public Player getSelectedPlayer() {
		return selectedPlayer.get();
	}

	// setSelectedPlayer bekommt einen Player! keine Property. Ist die Konvention
	// für Property-Setter/Getters!
	public void setSelectedPlayer(ObjectProperty<Player> selectedPlayers) {
		this.selectedPlayer = selectedPlayers;

	}

	public ObjectProperty<Player> selectedPlayerProperty() {
		return selectedPlayer;
	}

	public ListProperty<Player> getPlayerList() {
		return players;
	}

	// Im Start Window wird der Spieler in die Datenbank eingefügt

	@FXML
	private void initialize() {
		// comboBoxen Namen setzen
		cmbDifficulty.getItems().add("Leicht");
		cmbDifficulty.getItems().add("Mittel");
		cmbDifficulty.getItems().add("Schwer");

		checkValid();

		// überprüfen ob was in name steht und ob eine Combobox ausgewählt wurde
		txtName.textProperty().addListener((o, oldval, newval) -> checkValid());
		cmbDifficulty.valueProperty().addListener((o, oldval, newval) -> checkValid());
	}

	public void setConnection(String dbUrl, String userName, String password) {
		repository = new PlayerDbRepository(dbUrl, userName, password);
		reload();
	}

	@FXML
	public void gotoHighscore(ActionEvent evt) throws IOException {
		// Highscore fenster öffnen
		System.out.println("btnHighscore clicked...");
		FinishWindow dlg = new FinishWindow();
		Player entity = dlg.showModal();

	}

	@FXML
	public void startGame(ActionEvent evt) throws IOException {
		try {
			reload();
			// reload für den Fall das wir einen spieler mit dem wir spielen gerade gelöoscht haben
			// und sofort nochmal mit dem spielen wollen sonst -> error weil games keinen Fremdschlüssel hat
			System.out.println("btnGameStart clicked...");
			difficulty = cmbDifficulty.getValue();
			System.out.println(difficulty);
			GameWindow dlg = new GameWindow();
			Player player = new Player(txtName.getText());
			dlg.setPlayer(player);
			player.setDifficulty(difficulty);
//			 Überprüfen Namen sezten & überprüfen obs den Spieler in der Datenbank schon
//			 gibt;
		 
//			System.out.println("Methode " + isPlayerInList());
			if (isPlayerInList()) {//Player Überprüfen ob schon existiert
				// im Repository hinzufügen
				MessageBox.show("Spieler existiert schon!", "Wird in Games hinzugefügt!", AlertType.INFORMATION,
						ButtonType.OK);
				repository.insertPlayerInGames(player); // wenn ja in Games hinzufügen
			}else {// sonst in Highscore-Liste
				repository.insertPlayer(player);
				System.out.println("Neuer Player: " + player);
			}
			Player player2 = dlg.showModal();
			reload();
		} catch (Exception e) {
			e.printStackTrace();
			MessageBox.show("Neuer Player", "Fehler beim Hinzufügen: " + e.getMessage(), AlertType.ERROR,
					ButtonType.OK);
		}

	}

	@FXML
	public void reload() {
		try {
			List<Player> tmplist = repository.selectAllFromPlayers();
			System.out.printf("%d Entities geladen\n", tmplist.size());
			// neu der List-Property eine neue Collection verpassen
			this.players.set(FXCollections.observableArrayList(tmplist));
			System.out.printf("%d Entities geladen\n", players.size());
		} catch (Exception e) {
			e.printStackTrace();
			MessageBox.show("Reload", "Fehler beim Laden: " + e.getMessage(), AlertType.ERROR, ButtonType.OK);
		}
	}
	
//

	@FXML
	public void onCancel(ActionEvent event) {
		// das Fenster schließen (damit kehrt showAndWait zurück)
		Stage stage = (Stage) btnExit.getScene().getWindow();
		stage.close();
	}

	private void checkValid() {
		boolean valid = isInputValid();
		if (valid) {
			btnGameStart.setDisable(false);
		} else {
			btnGameStart.setDisable(true);
		}

	}

	private boolean isInputValid() {// Hilfsmethode überprüft ob name geschrieben oder cmb ausgewählt wurde
		boolean valid = txtName.getText() != null && !txtName.getText().isBlank() && cmbDifficulty.getValue() != null;
		System.out.printf("Check Valid: isValid = %s\n", valid);
		return valid;

	}

	private boolean isPlayerInList() {// Hilfsmethode überprüft ob spielername in Highscore Liste ist
		for (int i = 0; i < players.size(); i++) {
			if(players.get(i).getName().equals(txtName.getText())) {
				return true;
			}
		}
		return false;
	}
	
}
