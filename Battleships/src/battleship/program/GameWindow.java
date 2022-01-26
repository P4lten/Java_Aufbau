package battleship.program;

import java.io.IOException;


import battleship.repository.Player;
import common.MessageBox;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import static battleship.repository.Constants.GAMEWINDOWFXML;
import static battleship.repository.Constants.DBPATH;
import static battleship.repository.Constants.DBUSER;
import static battleship.repository.Constants.BATTLESHIPSSTYLES;
import static battleship.repository.Constants.DBPASSWORD;

public class GameWindow {

	private Player player;
	
	
	public GameWindow() {

	}
	

	public Player showModal() throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource(GAMEWINDOWFXML));
		Parent root = loader.load();

		Scene scene = new Scene(root);
		// eine Stage für die Scene erzeugen, mit Rahmen usw.
		Stage stage = new Stage(StageStyle.DECORATED);
		stage.setScene(scene);
		// anpassen an add/edit
		GameWindowController controller = loader.getController();
		// player an PlayerProperty im Controller weitergeben, damit Labels durch
		// Bindings gesetzt werden.
		controller.setSelectedPlayer(player);
		controller.setConnection(DBPATH, DBUSER, DBPASSWORD);
		stage.setTitle("Spielfeld");
		scene.getStylesheets().add(getClass().getResource(BATTLESHIPSSTYLES).toExternalForm());
		controller.startGame();
		stage.show();
		Player result = controller.getSelectedPlayer();
		return result;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
}
