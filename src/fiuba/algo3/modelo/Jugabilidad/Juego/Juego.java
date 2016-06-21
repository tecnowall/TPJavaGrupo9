package fiuba.algo3.modelo.Jugabilidad.Juego;

import fiuba.algo3.modelo.ChispaSuprema;
import fiuba.algo3.modelo.Jugabilidad.Jugador.Jugador;
import fiuba.algo3.modelo.TipoEquipo;
import fiuba.algo3.modelo.algoformer.Autobot;
import fiuba.algo3.modelo.algoformer.Decepticon;
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

    private List<ObservadorJuego> observadores = new ArrayList<ObservadorJuego>();
    private Jugador jugadorUno, jugadorDos;
    private Tablero unTablero;
    private int altoTablero;
    private int anchoTablero;
    private Partida unaPartida;


    //crea los jugadores, el tablero y los algoformers.
    public Juego(int alto, int ancho) {

        this.altoTablero = alto;
        this.anchoTablero = ancho;

    }



    public void iniciar (){

        //creo los jugadores

        this.jugadorUno = new Jugador ("Diego", TipoEquipo.AUTOBOTS);
        this.jugadorDos = new Jugador ("Maradona", TipoEquipo.DECEPTICONS);

        // creo un tablero
        this.unTablero = new Tablero(this.altoTablero,this.anchoTablero);

        // creo los algoformers
        //Autobots
        Autobot a1=Autobot.getOptimus();
        a1.setTablero(unTablero);
        a1.setEquipo(TipoEquipo.AUTOBOTS);

        Autobot a2=Autobot.getBumblebee();
        a2.setTablero(unTablero);
        a2.setEquipo(TipoEquipo.AUTOBOTS);

        Autobot a3=Autobot.getRatchet();
        a3.setTablero(unTablero);
        a3.setEquipo(TipoEquipo.AUTOBOTS);

        //Decepticons
        Decepticon a4=Decepticon.getMegatron();
        a3.setTablero(unTablero);
        a4.setEquipo(TipoEquipo.DECEPTICONS);

        Decepticon a5=Decepticon.getBonecrusher();
        a5.setTablero(unTablero);
        a5.setEquipo(TipoEquipo.DECEPTICONS);

        Decepticon a6=Decepticon.getFrenzy();
        a6.setTablero(unTablero);
        a6.setEquipo(TipoEquipo.DECEPTICONS);

        //agrego los Algoformers a cada jugador
        this.jugadorUno.agregarPersonaje(a1);         this.jugadorUno.agregarPersonaje(a2);         this.jugadorUno.agregarPersonaje(a3);
        this.jugadorDos.agregarPersonaje(a4); this.jugadorDos.agregarPersonaje(a5);this.jugadorDos.agregarPersonaje(a6);


        // Creo la partida
        this.unaPartida = new Partida(this.jugadorUno,this.jugadorDos,this.unTablero);

        //arranco la partida
        this.pasarTurno();

    }

    public Jugador  getJugadorUno() {return this.jugadorUno;}
    public Jugador  getJugadorDos() {return this.jugadorDos;}
    public Tablero  getTablero() {return this.unTablero;}
    public ChispaSuprema getChispaSuperma (){return this.unaPartida.obtenerChispaSuprema();}
    public List<Bonus> getListaBonus (){return this.unaPartida.getListaDeBonus();}


    public void pasarTurno(){

       if (this.unaPartida ==null) { throw new JuegoNoIniciadoException();};

        this.unaPartida.pasarTurno();

    }

    public void finalizar (){ this.unaPartida= null;}

    //todo
 private void notificarObsevadores (Jugador playerWin){

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
    public void jugadorSinPersonajes(Jugador unJugador) {

        this.finalizar();
        //notificarObsevadores(unJugador); //todo  notificar a observadores  pero hay que pasarle el ganador


    }

    @Override
    public void capturaronChispaSuprema(Jugador unJugador) {

        this.finalizar();
        notificarObsevadores(unJugador);


    }
}


