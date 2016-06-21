package fiuba.algo3.modelo.Jugabilidad.Juego;

import fiuba.algo3.modelo.Jugabilidad.Jugador.Jugador;

/**
 * Created by jose on 09/06/2016.
 */
public class Turno {
//TODO listacircular
    private int siguiente;
    private Jugador[] turnos = new Jugador [2];



    public Turno (Jugador player1, Jugador player2){

        turnos[0] = player1;
        turnos[1] = player2;

        int turnoAlAzar =  (int) (Math.random()*turnos.length);
        this.siguiente= turnoAlAzar;

    }

    //este metodo es para test
public Jugador getTurno (){

    return this.turnos [siguiente];

}

    public Jugador siguiente(){

        int auxiliar = this.siguiente;
        this.siguiente = this.siguiente +1;
        if (this.siguiente >= this.turnos.length) this.siguiente=0;

        return this.turnos [auxiliar];

    }



}
