package fiuba.algo3.vista;

import fiuba.algo3.modelo.Jugabilidad.Jugador.Jugador;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class JugadorVista {
	VBox vbox;
	Jugador jugador;

	
	public JugadorVista(VBox unVBox,Jugador unJugador){
		vbox=unVBox;
		jugador=unJugador;
	}
	public void dibujarJugador(){
		Label j= new Label(jugador.obtenerNombre());
		vbox.getChildren().add(j);
		j.setFont(new Font("Arial",20));
		vbox.setTranslateY(150);
	}
}
