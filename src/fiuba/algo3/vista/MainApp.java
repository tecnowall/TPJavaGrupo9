package fiuba.algo3.vista;
import java.io.IOException;

import fiuba.algo3.modelo.*;
import fiuba.algo3.modelo.Jugabilidad.Juego.Juego;
import fiuba.algo3.modelo.algoformer.Algoformer;
import fiuba.algo3.modelo.algoformer.Forma;
import fiuba.algo3.modelo.algoformer.Humanoide;
import fiuba.algo3.modelo.algoformer.Terrestre;
import fiuba.algo3.modelo.tablero.Tablero;
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


        Juego unJuego = new Juego(10-1,10-1);
        unJuego.iniciar();
        ContenedorPrincipal contenedorPrincipal = new ContenedorPrincipal(primaryStage, unJuego); //

        Scene scene = new Scene(contenedorPrincipal,800,480);
        primaryStage.setScene(scene);
        primaryStage.show();


	}

	public static void main(String[] args) {


		launch(args);
	}
}
