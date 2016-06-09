package fiuba.algo3.modelo.Jugabilidad;


import fiuba.algo3.modelo.ChispaSuprema;
import fiuba.algo3.modelo.Coordenada;
import fiuba.algo3.modelo.Tablero;

/**
 * Created by jose on 09/06/2016.
 */
public class Partida {

    private Turno turno;
    private Jugador player1, player2;
    private Tablero tablero;
    private ChispaSuprema chispa;
    public Partida (Jugador j1, Jugador j2, Tablero tablero){

            this.turno= new Turno(j1,j2);
            this.player1 = j1;
            this.player2 = j2;
            this.tablero = tablero;

        int x = this.tablero.getAncho();   x= (x-1) /2;
        int y = this.tablero.getAlto();   y= (y-1) /2;

        Coordenada cChispa = new Coordenada( x, y );
         this.chispa = new ChispaSuprema(cChispa);


        posicionarChispaSuprema();
        posicionarAlgoformers();



    }

    public void siguienteTurno(){
        Jugador  unJugador = this.turno.siguiente();
        unJugador.hacerJugada();
    };

    private void posicionarAlgoformers( ){

        Coordenada c1 = new Coordenada( 0, 1 );
        Coordenada c2 = new Coordenada( 0, 2 );
        Coordenada c3 = new Coordenada( 0, 3 );

        Coordenada c4 = new Coordenada( this.tablero.getAncho()-1, 1 );
        Coordenada c5 = new Coordenada( this.tablero.getAncho()-1, 2 );
        Coordenada c6 = new Coordenada( this.tablero.getAncho()-1, 3 );
        //TODO refactoring..., crear un iterador
        this.tablero.poner (this.player1.obtenerPersonaje("OPTIMUSS"),c1);
        this.tablero.poner (this.player1.obtenerPersonaje("BUMBLEBEE"),c2);
        this.tablero.poner (this.player1.obtenerPersonaje("RATCHET"),c3);

        this.tablero.poner (this.player2.obtenerPersonaje("MEGATRON"),c4);
        this.tablero.poner (this.player2.obtenerPersonaje("BOECRUSHER"),c5);
        this.tablero.poner (this.player2.obtenerPersonaje("FRENZY"),c6);


    }

    private void posicionarChispaSuprema (){

        this.tablero.poner(this.chispa, this.chispa.getPosicion());

    }

    //para test

    public ChispaSuprema obtenerChispaSuprema (){

        return this.chispa;
    }

}
