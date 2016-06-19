package fiuba.algo3.modelo.Jugabilidad.Juego;

import fiuba.algo3.modelo.ChispaSuprema;
import fiuba.algo3.modelo.Jugabilidad.Jugador.Jugador;
import fiuba.algo3.modelo.TipoEquipo;
import fiuba.algo3.modelo.algoformer.Algoformer;

import fiuba.algo3.modelo.bonus.Bonus;
import fiuba.algo3.modelo.observadores.ObservableJuego;
import fiuba.algo3.modelo.observadores.ObservadorJuego;
import fiuba.algo3.modelo.tablero.Tablero;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jose on 19/06/2016.
 */

//TODO esta clase puede ser que este demas... luego se ve.
public class Juego implements ObservableJuego {



    private List<ObservadorJuego> observadores;
    private Jugador jugadorUno, jugadorDos;
    private Tablero unTablero;
    private Partida unaPartida;

    public  Juego (){
    }

    public void nuevoJuego() {


        //creo los jugadores
        this.jugadorUno = new Jugador ("Diego", TipoEquipo.AUTOBOTS);
        this.jugadorDos = new Jugador ("Maradona", TipoEquipo.DECEPTICONS);

        // creo un tablero
        this.unTablero = new Tablero(20,20);

        // creo los algoformers
        Algoformer a1 = new Algoformer("OPTIMUSS"); a1.setEquipo(TipoEquipo.AUTOBOTS);
        Algoformer a2 = new Algoformer("BUMBLEBEE"); a2.setEquipo(TipoEquipo.AUTOBOTS);
        Algoformer a3 = new Algoformer("RATCHET"); a3.setEquipo(TipoEquipo.AUTOBOTS);

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

public void finalizar (){ this.unaPartida= null; }

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



}


