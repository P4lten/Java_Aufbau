package layout;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;

public class LayoutController {
	
	// dieses Attribut wird mit dem Controller initialisiert
	// der via fx:include eines separaten FXML Files verfügbar ist.
	// Das fx:include muss die fx:id="grid" haben
	@FXML
	private GridContentController gridController;

	@FXML
	private TabPane tabsContent;

	@FXML
	public void initialize() {
		if (tabsContent != null) {
			System.out.println("init");
			tabsContent.getSelectionModel().select(0);
		}
		
		// auf den untergeordneten Controller zugreifen
		gridController.setName("Michaela");
	}

}
