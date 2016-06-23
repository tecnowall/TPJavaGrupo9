package fiuba.algo3.vista;
import fiuba.algo3.eventos.SeleccionAlgoformerHandler;
import fiuba.algo3.eventos.SeleccionVacioHandler;
import fiuba.algo3.modelo.Coordenada;
import fiuba.algo3.modelo.Jugabilidad.Juego.Juego;
import fiuba.algo3.modelo.Jugabilidad.Jugador.Jugador;
import fiuba.algo3.modelo.algoformer.Algoformer;
import fiuba.algo3.modelo.algoformer.Algofusion;
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
import javafx.scene.layout.Pane;
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
		for (int j = 0; j<(tablero.getAlto());j++){
			tableroView.getRowConstraints().add(new RowConstraints(40));
		} //Creo tablero de 11x11 si Tablero (10,10);
		dibujarTerrenos();
		crearBotones();
		dibujarBonus();
		
		tableroView.setAlignment(Pos.CENTER);
	}

	private void crearBotones(){
		
		for (int i = 0; i<tablero.getAncho();i++){
			for (int j = 0; j<tablero.getAlto();j++){

				Button boton=new Button("");
				boton.setAlignment(Pos.CENTER);
				boton.setTranslateX(5);
				boton.setPrefWidth(70);
				boton.setPrefHeight(30);
				Coordenada coordenada=new Coordenada(i, j);
				SeleccionVacioHandler seleccionVacioHandler = new SeleccionVacioHandler(coordenada);
				boton.setOnAction(seleccionVacioHandler);
				if ((ContenedorPrincipal.juego.getChispaSuperma().getPosicion().getX()==i) && (ContenedorPrincipal.juego.getChispaSuperma().getPosicion().getX()==j)){
					boton.setStyle("-fx-base: #e6e600;"); //Color de chispa suprema
				}
				boton.setId("#" + Integer.toString(i)+"," + Integer.toString(j));
				tableroView.add(boton, i, j);
				
			}
		}
	}
	
	public void ubicarAlgoformer(Algoformer unAlgoformer,int x,int y){
		Button botonAlgo=new Button();

		botonAlgo = (Button) tableroView.getChildren().get((tablero.getAlto()*x) + y + (tablero.getAlto()*tablero.getAncho())+1);
		botonAlgo.setText(unAlgoformer.getNombre());
		
		SeleccionAlgoformerHandler seleccionAlgoformerHandler = new SeleccionAlgoformerHandler(unAlgoformer);
		botonAlgo.setOnAction(seleccionAlgoformerHandler);
		
		
	}
	
	public void quitarAlgoformer(int x, int y){
		Button botonAlgo=new Button();
		
		Coordenada coordenada=new Coordenada(x,y);
		botonAlgo = (Button) tableroView.getChildren().get((tablero.getAlto()*x) + y + (tablero.getAlto()*tablero.getAncho())+1);
		botonAlgo.setText("");
		
		SeleccionVacioHandler seleccionVacioHandler = new SeleccionVacioHandler(coordenada);
		botonAlgo.setOnAction(seleccionVacioHandler);
	}
	
	public void dibujarBonus(){

		for (int i = 0; i<tablero.getAncho();i++){
			for (int j = 0; j<tablero.getAlto();j++){
				Coordenada coordenada = new Coordenada (i,j);
				Button boton = new Button();
				boton = (Button) tableroView.getChildren().get((tablero.getAlto()*i) + j + (tablero.getAlto()*tablero.getAncho())+1);
				if (tablero.getCapturable(coordenada)!=null){
					switch (tablero.getCapturable(coordenada).getClass().toString()){
						case "class fiuba.algo3.modelo.bonus.BonusBurbuja":
							boton.setStyle("-fx-background-color: #1ac6ff");break;
						case "class fiuba.algo3.modelo.bonus.BonusDobleCanion":
							boton.setStyle("-fx-background-color: #cc0000");break;
						case "class fiuba.algo3.modelo.bonus.BonusFlash":
							boton.setStyle("-fx-background-color: #ffff99");
							break;
						default:
							break;
				}
				}

			}
		}
	}
	public void quitarBonus(int x, int y){
		Button boton=new Button();
		boton = (Button) tableroView.getChildren().get((tablero.getAlto()*x) + y + (tablero.getAlto()*tablero.getAncho())+1);
		boton.setStyle("-fx-background-color: #f2f2f2;");
		boton.setStyle("-fx-border-width: 1px;");


	}
	
	public void dibujarTerrenos(){
	for (int i = 0; i<tablero.getAncho();i++){
		for (int j = 0; j<tablero.getAlto();j++){
			Coordenada coordenada = new Coordenada (i,j);
			Pane r = new Pane();
			switch (tablero.getTerreno(coordenada).getClass().toString()){
				case "class fiuba.algo3.modelo.terreno.Rocoso":
					r.setStyle("-fx-background-color: #f2e6d9;");break;
				case "class fiuba.algo3.modelo.terreno.Pantano":
					r.setStyle("-fx-background-color: #714e28");break;
				case "class fiuba.algo3.modelo.terreno.Espinas":
					r.setStyle("-fx-background-color: #008000");break;
				case "class fiuba.algo3.modelo.terreno.Nube":
					r.setStyle("-fx-background-color: #b3ffff");break;
				case "class fiuba.algo3.modelo.terreno.NebulosaDeAndromeda":
					r.setStyle("-fx-background-color: #990099");break;
				case "class fiuba.algo3.modelo.terreno.TormentaPsionica":
					r.setStyle("-fx-background-color: #0047b3");break;
				default:
					r.setStyle("-fx-background-color: #ffffff;");break;
					
			}
			
			r.prefHeight(70);
			r.prefWidth(70);
			tableroView.add(r, i, j);
		}
	}
	}
	

	@Override
	public void notificartableroCreado(int ancho, int alto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fallecioAlgoformer(Algoformer unAlgoformerFallecido) {
			//retirar el algoformer del tablero
		MenuInferior.log.appendText("\n" + unAlgoformerFallecido.getNombre() + " ha muerto!");
		this.quitarAlgoformer(unAlgoformerFallecido.getPosicion().getX(), unAlgoformerFallecido.getPosicion().getY());
	}

	@Override
	public void huboUnMovimiento(Algoformer unAlgoformer, Coordenada original) {
        //actualizar el movimiento
		this.quitarAlgoformer(original.getX(), original.getY());
		this.ubicarAlgoformer(unAlgoformer, unAlgoformer.getPosicion().getX(), unAlgoformer.getPosicion().getY());
		
		//this.juego.pasarTurno();

	}

	@Override
	public void huboUnAtaque(Algoformer unAlgoformer) {
          //actualizzar vista
		this.juego.pasarTurno();
		MenuInferior.log.appendText("\n ataque"  );
	}

	@Override
	public void huboUnaTransformacion(Algoformer unAlgoformer) {
		//actualizzar vista
		MenuInferior.log.appendText("\n transformacion"  );
		this.juego.pasarTurno();
	}

	@Override
	public void huboUnaFusion(Algofusion unAlgoformer) {


		//retiro las 3 partes
		for (Algoformer unaParte : unAlgoformer.getPartes()){this.quitarAlgoformer(unaParte.getPosicion().getX(), unaParte.getPosicion().getY());}
		//ubico al fusionado
		this.ubicarAlgoformer(unAlgoformer, unAlgoformer.getPosicion().getX(), unAlgoformer.getPosicion().getY());

		//actualizzar vista
		MenuInferior.log.appendText("\n SFusion"  );
		//this.juego.pasarTurno();







	}


	@Override
	public void finalizoJuego(Jugador playerWin) {
		//actualizar vista, mostrar al ganador
		MenuInferior.log.appendText("\n Finalizo juego" + playerWin.obtenerNombre() + " ......!");

	}



	@Override
	public void esElTurnoDelJugador(Jugador unJugador) {
		//MenuInferior.log.insertText(0, "\nEs turno de: " + unJugador.obtenerNombre());
		MenuInferior.log.appendText("\nEs turno de: " + unJugador.obtenerNombre());
		//TODO te va a pasar el jugador activo que es el que tiene el turno para jugar
	}


	@Override
	public void seConsumioUnBonus(Coordenada coordenada) {
		// TODO Auto-generated method stub
		this.quitarBonus(coordenada.getX(), coordenada.getY());
		MenuInferior.log.appendText("\nSe consumio un bonus"  );
		
	}
}
