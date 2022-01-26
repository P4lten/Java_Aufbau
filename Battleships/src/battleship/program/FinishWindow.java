package battleship.program;

import java.io.IOException;


import battleship.repository.Player;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static battleship.repository.Constants.FINISHWINDOWFXML;
import static battleship.repository.Constants.DBPATH;
import static battleship.repository.Constants.DBUSER;
import static battleship.repository.Constants.BATTLESHIPSSTYLES;
import static battleship.repository.Constants.DBPASSWORD;

public class FinishWindow {

	private Player player;
	
	public FinishWindow() {

	}

	public Player showModal() throws IOException {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource(FINISHWINDOWFXML));
		Parent root = loader.load();
		
		Scene scene = new Scene(root, 450, 500);
				// eine Stage für die Scene erzeugen, mit Rahmen usw.
		Stage stage = new Stage(StageStyle.DECORATED);
		stage.setScene(scene);
		stage.setTitle("Endfenster");
		FinishWindowController controller = loader.getController();
		controller.setConnection(DBPATH, DBUSER , DBPASSWORD);
		controller.setSelectedPlayer(player);
		controller.reload();
		scene.getStylesheets().add(getClass().getResource(BATTLESHIPSSTYLES).toExternalForm());
		stage.initModality(Modality.APPLICATION_MODAL);
		// anzeigen und warten bis das Fenster geschlossen wurde
		stage.showAndWait();
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
