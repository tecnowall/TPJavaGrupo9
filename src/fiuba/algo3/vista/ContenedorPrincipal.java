package fiuba.algo3.vista;

import fiuba.algo3.modelo.ChispaSuprema;
import fiuba.algo3.modelo.Jugabilidad.Juego.Juego;
import fiuba.algo3.modelo.Jugabilidad.Jugador.Jugador;
import fiuba.algo3.modelo.algoformer.Algoformer;
import fiuba.algo3.modelo.algoformer.Forma;
import fiuba.algo3.modelo.algoformer.Humanoide;
import fiuba.algo3.modelo.algoformer.Terrestre;
import fiuba.algo3.modelo.bonus.Bonus;
import fiuba.algo3.modelo.tablero.*;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;

public class ContenedorPrincipal extends BorderPane {

    BarraDeMenu menuBar;
    TableroVistaControlador vistaTablero;
    Juego juego;
    Canvas canvasCentral;
    
	public ContenedorPrincipal(Stage unStage, Juego unJuego){
		this.setMenu(unStage);
		this.juego = unJuego;

        this.setTablero(unJuego.getTablero()); //dibuja el tablero
        this.setJugadores (unJuego.getJugadorUno(),unJuego.getJugadorDos()); // dibujar nombres en pantalla, obtener y presentar alfogormers, suscribir observadores que tiene que ser la vista
        this.setElementos (unJuego.getListaBonus(),unJuego.getChispaSuperma()); //


         /*
           Falta
         juego.agregarobservadores (this)     // este me va a avisar cuando el juego termina


         */
	}

    //TODO
    private void setElementos(List<Bonus> listaBonus, ChispaSuprema chispaSuperma) {
        /*
        juego.getchispa    y posicionarla

        juego.getbonus,    posicionar los bonus en pantalla
        suscribir observadores
         */
    }

    //TODO
    private void setJugadores(Jugador jugadorUno, Jugador jugadorDos) {

        /*
        // poner los nombres de los jugadores en pantalla
        // obtener los algoformers de cada uno y ponerlos en pantalla
        // jugador.getAlgoformers..........
        // subscribir los observadores
        //  algoformer.subscribirobservador
        */
    }



    private void setTablero(Tablero unTablero) {
		// TODO Auto-generated method stub
        GridPane grid=new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setGridLinesVisible(true);
        vistaTablero = new TableroVistaControlador(unTablero,grid);
        vistaTablero.dibujarTablero();
        this.setCenter(grid);
        //TEST
		Forma forma1 = new Humanoide( 50, 2, 2 );
		Forma forma2 = new Terrestre( 15, 4, 5 );
		Algoformer optimus = new Algoformer( "Optimus", 500, forma1, forma2 );
		vistaTablero.ubicarAlgoformer(optimus,2,2);
	}

	private void setMenu(Stage stage) {
        this.menuBar = new BarraDeMenu(stage);
        this.setTop(menuBar);
    }
}
