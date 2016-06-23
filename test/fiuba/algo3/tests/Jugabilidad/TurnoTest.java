package fiuba.algo3.tests.Jugabilidad;

import fiuba.algo3.modelo.Jugabilidad.Jugador.Jugador;
import fiuba.algo3.modelo.Jugabilidad.Juego.Turno;
import fiuba.algo3.modelo.TipoEquipo;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;


/**
 * Created by jose on 09/06/2016.
 */
public class TurnoTest {


    @Test
     public void testTurnoSiguienteDebeDarUnJugadorDistintoAlAnteriorLuegoDeHaberSolicitadoElPrimerTurno(){
        Jugador player1 = new Jugador("Maradona", TipoEquipo.AUTOBOTS);
        Jugador player2 = new Jugador("DIEGO", TipoEquipo.DECEPTICONS);
        Turno turno =  new Turno(player1,player2);

        Jugador anterior = turno.getTurnoActual();  //primer turno

        Assert.assertThat( turno.getTurnoSiguiente(),is( not( anterior ) ) );


    }

    @Test
    public void testSiguienteDebeDarUnJugadorDistintoAlAnteriorLuegoDeProbarloDosVeces(){
        Jugador player1 = new Jugador("Maradona", TipoEquipo.AUTOBOTS);
        Jugador player2 = new Jugador("DIEGO", TipoEquipo.DECEPTICONS);
        Turno turno =  new Turno(player1,player2);

        Jugador turno1= turno.getTurnoActual();
        Jugador turno2= turno.getTurnoSiguiente();
        Assert.assertThat( turno1,is( not( turno2 ) ) );
        Assert.assertThat( turno.getTurnoSiguiente(),is( not( turno2 ) ) );


    }

}
