package fiuba.algo3.modelo.Jugabilidad.Juego;


import fiuba.algo3.modelo.ChispaSuprema;
import fiuba.algo3.modelo.Coordenada;
import fiuba.algo3.modelo.Jugabilidad.Jugador.Jugador;
import fiuba.algo3.modelo.bonus.Bonus;
import fiuba.algo3.modelo.bonus.BonusDobleCanion;
import fiuba.algo3.modelo.observadores.ObservablePartida;
import fiuba.algo3.modelo.observadores.ObservadorJuego;
import fiuba.algo3.modelo.observadores.ObservadorPartida;
import fiuba.algo3.modelo.tablero.Tablero;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jose on 09/06/2016.
 */
public class Partida implements ObservablePartida {

    private Turno turno;
    private Jugador player1, player2;
    private Tablero tablero;
    private ChispaSuprema chispa;
    private List<ObservadorPartida> observadores = new ArrayList<ObservadorPartida>();
    private boolean terminada;

    public Partida (){} //for test

    //todo si los jugadores no tienen 3 algoformers que lance exepcion
    public Partida (Jugador j1, Jugador j2, Tablero tablero){
            this.terminada = false;
            this.turno= new Turno(j1,j2);
            this.player1 = j1;

            this.player2 = j2;
            this.tablero = tablero;


    //TODO escribirlo mejor
        int x = this.tablero.getAncho();   x= (x-1) /2;
        int y = this.tablero.getAlto();   y= (y-1) /2;


        this.chispa = new ChispaSuprema();
        Coordenada cChispa = new Coordenada( x, y );
        this.posicionarChispaSuprema(cChispa);
        this.posicionarAlgoformers();

        player1.setPartida(this);
        player2.setPartida(this);
    }

    public void pasarTurno(){


        if (finalizada()) throw new PartidaFinalizadaException();

        this.turno.getTurno().finTurno();
        this.turno.siguiente().inicioTurno();


    };




    private boolean finalizada (){return this.terminada;}
    private void posicionarAlgoformers( ){

        Coordenada c1 = new Coordenada( 0, 1 );
        Coordenada c2 = new Coordenada( 0, 2 );
        Coordenada c3 = new Coordenada( 0, 3 );

        Coordenada c4 = new Coordenada( this.tablero.getAncho()-1, 1 );
        Coordenada c5 = new Coordenada( this.tablero.getAncho()-1, 2 );
        Coordenada c6 = new Coordenada( this.tablero.getAncho()-1, 3 );

        //TODO refactoring..., crear un iterador
        // this.player1.obtenerPersonajes
        //
        ArrayList<String> lista1= this.player1.obtenerNombresDePersonajes();

        this.tablero.poner (this.player1.obtenerPersonaje(lista1.get(0)),c1);
        this.player1.obtenerPersonaje(lista1.get(0)).ubicar(c1);
        this.tablero.poner (this.player1.obtenerPersonaje(lista1.get(1)),c2);
        this.player1.obtenerPersonaje(lista1.get(1)).ubicar(c2);
        this.tablero.poner (this.player1.obtenerPersonaje(lista1.get(2)),c3);
        this.player1.obtenerPersonaje(lista1.get(2)).ubicar(c3);


        ArrayList<String> lista2= this.player2.obtenerNombresDePersonajes();
        this.tablero.poner (this.player2.obtenerPersonaje(lista2.get(0)),c4);
        this.player2.obtenerPersonaje(lista2.get(0)).ubicar(c4);
        this.tablero.poner (this.player2.obtenerPersonaje(lista2.get(1)),c5);
        this.player2.obtenerPersonaje(lista2.get(1)).ubicar(c5);
        this.tablero.poner (this.player2.obtenerPersonaje(lista2.get(2)),c6);
        this.player2.obtenerPersonaje(lista2.get(2)).ubicar(c6);

    }

    private void posicionarChispaSuprema (Coordenada posicion){

        this.tablero.poner(this.chispa, posicion);
        this.chispa.setPosicion(posicion);
    }



    public ChispaSuprema obtenerChispaSuprema (){

        return this.chispa;
    }

    //TODO implementar esto!
    public List<Bonus> getListaDeBonus(){


        List<Bonus> lista;
        lista = new ArrayList<>();
        Bonus unBonus = new BonusDobleCanion();
       lista.add(unBonus);
        return lista;

    };



    public void jugadorSinPersonajes (Jugador unJugador){

        finalizar();  // la marco finalizada
        notificarJugadorSinPersonajes ( unJugador);


    }

    private void finalizar(){ this.terminada = true; }

    private void notificarJugadorSinPersonajes (Jugador unJugador){

        for (ObservadorPartida unObservador : this.observadores){ unObservador.jugadorSinPersonajes(unJugador);}

    }

    //TODO EN UNO le avisas quien gana y en otro quien pierde..... revisar
    // refac repetis codifo con jugadorSinPersonajes




    public void chispaCapturada (Jugador unJugador){

       finalizar();  // la marco finalizada
       notificarChispaCapturada(unJugador);

    }

    private void notificarChispaCapturada (Jugador unJugador){

        for (ObservadorPartida unObservador : this.observadores){ unObservador.capturaronChispaSuprema(unJugador);}

    }



    @Override
    public void suscribir(ObservadorPartida nuevoObservador) {
        observadores.add(nuevoObservador);
    }

    @Override
    public void desSuscribir(ObservadorPartida nuevoObservador) {
        if ( this.observadores.contains( nuevoObservador ) ){
            this.observadores.remove( this.observadores.indexOf( nuevoObservador ) );
        }
    }
}
