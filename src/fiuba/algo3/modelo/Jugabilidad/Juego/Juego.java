package fiuba.algo3.modelo.Jugabilidad.Juego;

import fiuba.algo3.modelo.ChispaSuprema;
import fiuba.algo3.modelo.Jugabilidad.Jugador.Jugador;
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

        /*
        creo tablero
        creo los 2 jugadores
        creo el los algoformer
        creo los equipos
        creo la partida

         */
    }


    public Jugador  getJugadorUno() {return this.jugadorUno;}
    public Jugador  getJugadorDos() {return this.jugadorDos;}
    public Tablero  getTablero() {return this.unTablero;}
    public ChispaSuprema getChispaSuperma (){return this.unaPartida.obtenerChispaSuprema();}
    public List<Bonus> getListaBonus (){return this.unaPartida.getListaDeBonus();}

    //todo  hay que ver para que lance exepcion si el juego no esta iniciado
    public void pasarTurno(){
/*
      if (unaPartida.esFin()) {


          notificarObservadores();
          this.finalizar();
      }
      unaPartida.pasarTurno();
*/
    }

public void finalizar (){

    //hay que finalizar el juego

   }

    //todo
 private void notificarObsevadores (){

     // para cada observador
     // observadores.finjuego(Jugador playerGanador)

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


