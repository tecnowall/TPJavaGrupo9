package fiuba.algo3.vista;
import java.io.IOException;

import fiuba.algo3.modelo.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainApp extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
	@Override
	public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Algoformers");

        initRootLayout();
        mostrarVistaTablero();
	}
	
	public void initRootLayout(){
        // Cargar fxml
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("RootLayout.fxml"));
        try {
			rootLayout = (BorderPane) loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Mostrar scene con rootLayout.
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
	}
    public void mostrarVistaTablero() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("TableroVista.fxml"));
            AnchorPane tableroVista = (AnchorPane) loader.load();
            rootLayout.setCenter(tableroVista);
            // Darle acceso al controlador

            TableroControlador controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

	public static void main(String[] args) {
		launch(args);
	}
}
