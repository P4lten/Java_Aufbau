package tasks;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TasksMain extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("TasksView.fxml"));
			Scene scene = new Scene(root, 450, 250);
			scene.getStylesheets().add(getClass().getResource("tasks.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Tasks demo");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
