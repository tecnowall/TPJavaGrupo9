package fiuba.algo3.vista;
import java.io.IOException;

import fiuba.algo3.modelo.*;
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

        /* TODO
          hay que hacer una aplicacion principal que haga lo siguiente:

          Juego unJuego = new Juego()
          Vista unaVista = new Vista (unJuego)

          el new de Vista va a tener lo siguiente:
              juego.Iniciar;
              juego.gettablero   y dibujar tablero

              juego.getjugadoruno.     pones el nombre en la vista etc....
              juego.getjugadoruno.getalgoformrs
                   para cada algoformer ponerlos en la pantalla
                        suscribirles un observador

              lo mismo para jugador 2

           juego.getchispa    y posicionarla

           juego.getbonus,    posicionar los bonus en pantalla
                                suscribir observadores

           juego.agregarobservadores (this)     // este me va a avisar cuando el juego termina

           juego.pasarturno

           fin del new vista.


         */




        
        Tablero tablero = new Tablero(14,9);
        
		Forma forma1 = new Humanoide( 50, 2, 2 );
		Forma forma2 = new Terrestre( 15, 4, 5 );
		Algoformer optimus = new Algoformer( "Optimus", 500, forma1, forma2 );
		//tablero.poner(optimus, new Coordenada(2,2));
		
        ContenedorPrincipal contenedorPrincipal = new ContenedorPrincipal(primaryStage, tablero);
        Scene scene = new Scene(contenedorPrincipal,680,480);
        primaryStage.setScene(scene);
        primaryStage.show();

	}
//	
//	public void initRootLayout(){
//        // Cargar fxml
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(MainApp.class.getResource("RootLayout.fxml"));
//        try {
//			rootLayout = (BorderPane) loader.load();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//        // Mostrar scene con rootLayout.
//        Scene scene = new Scene(rootLayout);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//	}
//    public void mostrarVistaTablero() {
//        try {
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(MainApp.class.getResource("TableroVista.fxml"));
//            AnchorPane tableroVista = (AnchorPane) loader.load();
//            rootLayout.setCenter(tableroVista);
//            // Darle acceso al controlador
//            TableroVistaControlador controller = loader.getController();
//            controller.setMainApp(this);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }

	public static void main(String[] args) {
		launch(args);
	}
}
