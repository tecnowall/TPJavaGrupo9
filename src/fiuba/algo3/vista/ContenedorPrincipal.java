package fiuba.algo3.vista;

import java.util.List;

import fiuba.algo3.modelo.ChispaSuprema;
import fiuba.algo3.modelo.Jugabilidad.Juego.Juego;
import fiuba.algo3.modelo.Jugabilidad.Jugador.Jugador;
import fiuba.algo3.modelo.algoformer.Algoformer;
import fiuba.algo3.modelo.bonus.Bonus;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ContenedorPrincipal extends BorderPane {

    BarraDeMenu menuBar;
    public static MenuInferior menuInferior;
    TableroVistaControlador vistaTablero;
    public static Juego juego;  //OJO
    Canvas canvasCentral;
    
	public ContenedorPrincipal(Stage unStage, Juego unJuego){
		this.setMenu(unStage);
		this.juego = unJuego;

        this.setTablero(unJuego); //dibuja el tablero
        this.setJugadores (unJuego.getJugadorUno(),unJuego.getJugadorDos()); // dibujar nombres en pantalla, obtener y presentar alfogormers, suscribir observadores que tiene que ser la vista
        this.setElementos (unJuego.getListaDeBonus(),unJuego.getChispaSuperma()); //


         /*
           Falta
         juego.agregarobservadores (this)     // este me va a avisar cuando el juego termina


         */
        juego.suscribir(vistaTablero);
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
    	
    	VBox vbox1=new VBox(); VBox vbox2=new VBox();
    	
    	JugadorVista vistaJugador1 = new JugadorVista(vbox1,jugadorUno);
    	JugadorVista vistaJugador2 = new JugadorVista(vbox2,jugadorDos);
    	
    	vistaJugador1.dibujarJugador();
    	vistaJugador2.dibujarJugador();
    	vistaJugador1.dibujarInfoTerreno();
    	vistaJugador2.dibujarInfoBonus();
    	this.setLeft(vbox1);
    	this.setRight(vbox2);
    	vbox1.setTranslateX(20);
    	vbox2.setTranslateX(-20);

    	for(Algoformer value: jugadorUno.getAllPersonajes()){
    		vistaTablero.ubicarAlgoformer(value,value.getPosicion().getX(),value.getPosicion().getY());
    		value.suscribir(vistaTablero);
    	}
    	for(Algoformer value: jugadorDos.getAllPersonajes()){
    		vistaTablero.ubicarAlgoformer(value,value.getPosicion().getX(),value.getPosicion().getY());
            value.suscribir(vistaTablero);
    	}
    	
        /*
        // poner los nombres de los jugadores en pantalla DONE
        // obtener los algoformers de cada uno y ponerlos en pantalla DONE
        // jugador.getAlgoformers..........
        // subscribir los observadores
        //  algoformer.subscribirobservador
        */
    }



    private void setTablero(Juego unJuego) {
		// TODO Auto-generated method stub
        GridPane grid=new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setGridLinesVisible(true);
        vistaTablero = new TableroVistaControlador(unJuego,grid);
        vistaTablero.dibujarTablero();
        this.setCenter(grid);

	}

	private void setMenu(Stage stage) {
        this.menuBar = new BarraDeMenu(stage);
        this.setTop(menuBar);
        
        this.menuInferior = new MenuInferior(stage);
    	this.setBottom(menuInferior);
    	
    }
}
