package fiuba.algo3.modelo.Jugabilidad.Juego;


import fiuba.algo3.modelo.ChispaSuprema;
import fiuba.algo3.modelo.Coordenada;
import fiuba.algo3.modelo.Jugabilidad.Jugador.Jugador;
import fiuba.algo3.modelo.bonus.Bonus;
import fiuba.algo3.modelo.bonus.BonusDobleCanion;
import fiuba.algo3.modelo.tablero.Tablero;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jose on 09/06/2016.
 */
public class Partida {

    private Turno turno;
    private Jugador player1, player2;
    private Tablero tablero;
    private ChispaSuprema chispa;

    //todo si los jugadores no tienen 3 algoformers que lance exepcion
    public Partida (Jugador j1, Jugador j2, Tablero tablero){

            this.turno= new Turno(j1,j2);
            this.player1 = j1;
            this.player2 = j2;
            this.tablero = tablero;

    //TODO escribirlo mejor
        int x = this.tablero.getAncho();   x= (x-1) /2;
        int y = this.tablero.getAlto();   y= (y-1) /2;

        Coordenada cChispa = new Coordenada( x, y );
         this.chispa = new ChispaSuprema();


        this.posicionarChispaSuprema(cChispa);
        this.posicionarAlgoformers();



    }

    public void pasarTurno(){
        this.turno.getTurno().finTurno();
        this.turno.siguiente().inicioTurno();
    };

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

    //para test

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
}
