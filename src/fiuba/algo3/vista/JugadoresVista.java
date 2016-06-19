package fiuba.algo3.vista;

import fiuba.algo3.modelo.Jugabilidad.Jugador.Jugador;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class JugadoresVista {
	HBox hbox;
	Jugador jugador1;
	Jugador jugador2;

	
	public JugadoresVista(HBox unHBox,Jugador unj1,Jugador unj2){
		hbox=unHBox;
		jugador1=unj1;
		jugador2=unj2;
	}
	public void dibujarJugadores(){
		Label j1= new Label("J1: " + jugador1.obtenerNombre());
		Label j2= new Label("J2: " + jugador2.obtenerNombre());
		hbox.getChildren().add(j1);
		hbox.getChildren().add(j2);
	}
}
