package layout;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class GridContentController {

	@FXML 
	private TextField txtName;
	
	public void setName(String name){
		txtName.setText(name);
	}

}
