package fiuba.algo3.vista;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ui extends Application {

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Algoformers");
		StackPane layout = new StackPane();
		Scene scene = new Scene(layout,300,250);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
