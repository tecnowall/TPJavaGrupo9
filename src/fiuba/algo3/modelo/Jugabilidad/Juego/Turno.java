package fiuba.algo3.modelo.Jugabilidad.Juego;

import fiuba.algo3.modelo.Jugabilidad.Jugador.Jugador;


/**
 * Created by jose on 09/06/2016.
 */
public class Turno {
    //TODO listacircular
    private int numeroDeTurno;
    private Jugador[] turnos = new Jugador [2];



    public Turno (Jugador player1, Jugador player2){

        turnos[0] = player1;
        turnos[1] = player2;

        int turnoAlAzar =  (int) (Math.random()*turnos.length);
        this.numeroDeTurno= turnoAlAzar;


    }

    //este metodo es para test
    public Jugador getTurnoActual(){

        return this.turnos [numeroDeTurno];

    }

    public Jugador getTurnoSiguiente(){

        this.numeroDeTurno = this.numeroDeTurno +1;
        if (this.numeroDeTurno >= this.turnos.length) this.numeroDeTurno=0;

        return this.turnos [this.numeroDeTurno];

    }



}
