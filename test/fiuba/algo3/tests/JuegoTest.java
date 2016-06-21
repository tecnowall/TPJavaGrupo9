package fiuba.algo3.tests;

import fiuba.algo3.modelo.Jugabilidad.Juego.Juego;
import fiuba.algo3.modelo.Jugabilidad.Juego.JuegoNoIniciadoException;
import fiuba.algo3.modelo.algoformer.Algoformer;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by jose on 21/06/2016.
 */
public class JuegoTest {





    @Test (expected = JuegoNoIniciadoException.class)
    public void testPasarTurnoDebeLanzarExcepcionSiElJuegoFinalizo(){

        Juego unJuego = new Juego(10,15);
                unJuego.iniciar();
             unJuego.pasarTurno();
        unJuego.finalizar();
        unJuego.pasarTurno();

    }

    @Test (expected = JuegoNoIniciadoException.class)
    public void testPasarTurnoDebeLanzarExcepcionSiElJuegoNoEstaIniciado(){

        Juego unJuego = new Juego(10,15);
        unJuego.pasarTurno();
    }


    @Test (expected = JuegoNoIniciadoException.class)
    public void testAlMorirTodosLosPersonajesDeUnJugadorElJuegoTermina(){

        Juego unJuego = new Juego(10,15);
        unJuego.pasarTurno();

       ArrayList<Algoformer> lista = unJuego.getJugadorUno().getAllPersonajes();

                for (Algoformer personaje : unJuego.getJugadorUno().getAllPersonajes() ){unJuego.getJugadorUno().murioUnPersonaje(personaje);}

        unJuego.pasarTurno();
    }

}
