package fiuba.algo3.vista;
import fiuba.algo3.eventos.SeleccionAlgoformerHandler;
import fiuba.algo3.modelo.Coordenada;
import fiuba.algo3.modelo.algoformer.Algoformer;
import fiuba.algo3.modelo.observadores.ObservadorTablero;
import fiuba.algo3.modelo.tablero.*;
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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class TableroVistaControlador implements ObservadorTablero {
	//implements ObservadorTablero
	Tablero tablero;
	GridPane tableroView;
	
	public TableroVistaControlador(Tablero unTablero,GridPane unGrid){
		tableroView=unGrid;
		tablero=unTablero;
	}


	public void dibujarTablero() {
		tableroView.setOnMousePressed(null);
		for (int i = 0;i<(tablero.getAncho());i++){
			tableroView.getColumnConstraints().add(new ColumnConstraints(70));
		}
		for (int j = 0; j<(tablero.getAlto());j++){
			tableroView.getRowConstraints().add(new RowConstraints(30));
		} //Creo tablero de 11x11 si Tablero (10,10);
		crearBotones();
	}

	private void crearBotones(){
		for (int i = 0; i<=tablero.getAncho();i++){
			for (int j = 0; j<=tablero.getAlto();j++){
				Button boton=new Button("    ");
				boton.setAlignment(Pos.CENTER);
				boton.setPrefWidth(70);
				boton.setPrefHeight(30);
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
		
		SeleccionAlgoformerHandler seleccionAlgoformerHandler = new SeleccionAlgoformerHandler(unAlgoformer);
		botonAlgo.setOnAction(seleccionAlgoformerHandler);
		
		tableroView.add(botonAlgo, x,y);
	}


	@Override
	public void notificartableroCreado(int ancho, int alto) {
		// TODO Auto-generated method stub
		
	}
}