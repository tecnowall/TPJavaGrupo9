package fiuba.algo3.modelo.Jugabilidad.Juego;

import fiuba.algo3.modelo.ChispaSuprema;
import fiuba.algo3.modelo.Jugabilidad.Jugador.Jugador;
import fiuba.algo3.modelo.TipoEquipo;
import fiuba.algo3.modelo.algoformer.Aerea;
import fiuba.algo3.modelo.algoformer.Algoformer;
import fiuba.algo3.modelo.algoformer.Forma;
import fiuba.algo3.modelo.algoformer.Humanoide;
import fiuba.algo3.modelo.algoformer.Terrestre;
import fiuba.algo3.modelo.bonus.Bonus;
import fiuba.algo3.modelo.observadores.ObservableJuego;
import fiuba.algo3.modelo.observadores.ObservadorJuego;
import fiuba.algo3.modelo.observadores.ObservadorPartida;
import fiuba.algo3.modelo.tablero.Tablero;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jose on 19/06/2016.
 */

//TODO esta clase puede ser que este demas... luego se ve.
public class Juego implements ObservableJuego, ObservadorPartida {



    private List<ObservadorJuego> observadores;
    private Jugador jugadorUno, jugadorDos;
    private Tablero unTablero;
    private Partida unaPartida;


    //crea los jugadores, el tablero y los algoformers.
    public Juego(int alto, int ancho) {


        //creo los jugadores
        this.jugadorUno = new Jugador ("Diego", TipoEquipo.AUTOBOTS);
        this.jugadorDos = new Jugador ("Maradona", TipoEquipo.DECEPTICONS);

        // creo un tablero
        this.unTablero = new Tablero(alto,ancho);

        // creo los algoformers
        Forma forma1a1 = new Humanoide( 50, 2, 2 );
		Forma forma2a1 = new Terrestre( 15, 4, 5 );
		Algoformer a1 = new Algoformer( "Optimus", 500, forma1a1, forma2a1 );
        a1.setEquipo(TipoEquipo.AUTOBOTS);

		Forma forma1a2 = new Humanoide( 40, 1, 2 );
		Forma forma2a2 = new Terrestre( 20, 3, 5 );
		Algoformer a2= new Algoformer("Bumblebee",350,forma1a2,forma2a2);
		a2.setEquipo(TipoEquipo.AUTOBOTS);


		Forma forma1a3 = new Humanoide( 5, 5, 1 );
		Forma forma2a3 = new Aerea( 35, 2, 8 );
		Algoformer a3= new Algoformer("Ratchet",150,forma1a3,forma2a3);
        a3.setEquipo(TipoEquipo.AUTOBOTS);

		//TODO falta esta parte, no la hago por que hay que ver la vista, asi la vista falla, hacer algun chequeo?
		// si creo los algoformers a partir del nombre falta el resto de la informacion
        Algoformer a4 = new Algoformer("MEGATRON"); a4.setEquipo(TipoEquipo.DECEPTICONS);
        Algoformer a5 = new Algoformer("BOECRUSHER");a5.setEquipo(TipoEquipo.DECEPTICONS);
        Algoformer a6 = new Algoformer("FRENZY");a6.setEquipo(TipoEquipo.DECEPTICONS);




        //agrego los Algoformers a cada jugador
        this.jugadorUno.agregarPersonaje(a1);         this.jugadorUno.agregarPersonaje(a2);         this.jugadorUno.agregarPersonaje(a3);
        this.jugadorDos.agregarPersonaje(a4); this.jugadorDos.agregarPersonaje(a5);this.jugadorDos.agregarPersonaje(a6);

    }

    public void iniciar (){

        // Creo la partida
        this.unaPartida = new Partida(this.jugadorUno,this.jugadorDos,this.unTablero);
        //this.pasarTurno(); TODO esto deberia estar aca adentro al iniciar

    }

    public Jugador  getJugadorUno() {return this.jugadorUno;}
    public Jugador  getJugadorDos() {return this.jugadorDos;}
    public Tablero  getTablero() {return this.unTablero;}
    public ChispaSuprema getChispaSuperma (){return this.unaPartida.obtenerChispaSuprema();}
    public List<Bonus> getListaBonus (){return this.unaPartida.getListaDeBonus();}

    //todo  hay que ver para que lance exepcion si el juego no esta iniciado
    public void pasarTurno(){

       if (this.unaPartida ==null) { throw new JuegoNoIniciadoException();};

        this.unaPartida.pasarTurno();
/*
      if (unaPartida.esFin()) {


          notificarObservadores();
          this.finalizar();
      }

*/
    }

public void finalizar (Jugador unJugador){

    //notificar quien ganoo
    this.unaPartida= null; }

    //todo
 private void notificarObsevadores (Jugador playerWin){

     // para cada observador
     for (ObservadorJuego unObservador : this.observadores){ unObservador.finalizoJuego(playerWin);}

 }

    @Override
    public void suscribir(ObservadorJuego nuevoObservador) {
        observadores.add(nuevoObservador);

    }

    @Override
    public void desSuscribir(ObservadorJuego unObservador) {

        if (this.observadores.contains(unObservador)) this.observadores.remove(unObservador);

    }


    @Override
    public void jugadorSinPersonajes(Jugador playerWin) {

    }

    @Override
    public void capturaronChispaSuprema(Jugador unJugador) {

    }
}


