package fiuba.algo3.vista;
import fiuba.algo3.eventos.SeleccionAlgoformerHandler;
import fiuba.algo3.eventos.SeleccionVacioHandler;
import fiuba.algo3.modelo.Coordenada;
import fiuba.algo3.modelo.Jugabilidad.Juego.Juego;
import fiuba.algo3.modelo.Jugabilidad.Jugador.Jugador;
import fiuba.algo3.modelo.algoformer.Algoformer;
import fiuba.algo3.modelo.bonus.Bonus;
import fiuba.algo3.modelo.observadores.ObservadorAlgoformer;
import fiuba.algo3.modelo.observadores.ObservadorBonus;
import fiuba.algo3.modelo.observadores.ObservadorJuego;
import fiuba.algo3.modelo.observadores.ObservadorTablero;
import fiuba.algo3.modelo.tablero.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

//TODO ojo el obsvador juego....
public class TableroVistaControlador implements ObservadorTablero, ObservadorBonus, ObservadorAlgoformer, ObservadorJuego {
	//implements ObservadorTablero
	Tablero tablero;
	GridPane tableroView;
	Juego juego;
	public static boolean seleccionado;
	public static Algoformer algoformerSeleccionado;
	
	public TableroVistaControlador(Juego unJuego,GridPane unGrid){
		tableroView=unGrid;
		tablero=unJuego.getTablero();
		juego=unJuego;
		seleccionado=false;
	}


	public void dibujarTablero() {
		tableroView.setOnMousePressed(null);
		for (int i = 0;i<(tablero.getAncho());i++){
			tableroView.getColumnConstraints().add(new ColumnConstraints(80));
		}
		for (int j = 0; j<=(tablero.getAlto());j++){
			tableroView.getRowConstraints().add(new RowConstraints(40));
		} //Creo tablero de 11x11 si Tablero (10,10);
		crearBotones();
		tableroView.setAlignment(Pos.CENTER);
	}

	private void crearBotones(){
		Coordenada coordenada=new Coordenada(0, 0);
		for (int i = 0; i<tablero.getAncho();i++){
			for (int j = 0; j<=tablero.getAlto();j++){
				Button boton=new Button("    ");
				boton.setAlignment(Pos.CENTER);
				boton.setTranslateX(5);
				boton.setPrefWidth(70);
				boton.setPrefHeight(30);
				coordenada.setXY(i, j);
				SeleccionVacioHandler seleccionVacioHandler = new SeleccionVacioHandler(coordenada);
				boton.setOnAction(seleccionVacioHandler);
				if ((ContenedorPrincipal.juego.getChispaSuperma().getPosicion().getX()==i) && (ContenedorPrincipal.juego.getChispaSuperma().getPosicion().getX()==j)){
					boton.setStyle("-fx-base: #e6e600;"); //Color de chispa suprema
				}
				
				tableroView.add(boton, i, j);
			}
		}
	}
	
	public void ubicarAlgoformer(Algoformer unAlgoformer,int x,int y){
		Button botonAlgo=new Button();

		botonAlgo.setText(unAlgoformer.getNombre());
		botonAlgo.setAlignment(Pos.CENTER);
		botonAlgo.setPrefWidth(70);
		botonAlgo.setPrefHeight(30);
		botonAlgo.setTranslateX(5);
		
		SeleccionAlgoformerHandler seleccionAlgoformerHandler = new SeleccionAlgoformerHandler(unAlgoformer);
		botonAlgo.setOnAction(seleccionAlgoformerHandler);
		
		tableroView.add(botonAlgo, x,y);
	}


	@Override
	public void notificartableroCreado(int ancho, int alto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fallecioAlgoformer(Algoformer unAlgoformerFallecido) {
			//retirar el algoformer del tablero
	}

	@Override
	public void huboUnMovimiento(Algoformer unAlgoformer) {
        //actualizar el movimiento
		this.juego.pasarTurno();

	}

	@Override
	public void huboUnAtaque(Algoformer unAlgoformer) {
          //actualizzar vista
		this.juego.pasarTurno();
	}

	@Override
	public void huboUnaTransformacion(Algoformer unAlgoformer) {
		//actualizzar vista
		this.juego.pasarTurno();
	}

	@Override
	public void huboUnaFusion(Algoformer unAlgoformer) {
		//actualizzar vista
		// hay que retirar los 3 algoformers y poner el fusionado

		this.juego.pasarTurno();

	}

	@Override
	public void seConsumioBonus(Bonus unBonus) {
          //retirar bonus
	}

	@Override
	public void finalizoJuego(Jugador playerWin) {
		//actualizar vista, mostrar al ganador

	}



	@Override
	public void esElTurnoDelJugador(Jugador unJugador) {
		System.out.println("Es turno de: " + unJugador.obtenerNombre());
		MenuInferior.log.insertText(0, "\nEs turno de: " + unJugador.obtenerNombre());
		//TODO te va a pasar el jugador activo que es el que tiene el turno para jugar
	}
}
