package fiuba.algo3.tests;

import fiuba.algo3.modelo.Jugabilidad.Jugador;
import fiuba.algo3.modelo.Jugabilidad.Turno;
import fiuba.algo3.modelo.TipoEquipo;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static jdk.nashorn.internal.objects.Global.print;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;


/**
 * Created by jose on 09/06/2016.
 */
public class TurnoTest {


    @Test
    public void testAlCrearelTurnoElSiguienteDebeSerElCreado(){
        Jugador player1 = new Jugador("Maradona", TipoEquipo.AUTOBOTS);
        Jugador player2 = new Jugador("DIEGO", TipoEquipo.DECEPTICONS);
        Turno turno =  new Turno(player1,player2);
        Jugador creado = turno.getTurno();

        Assert.assertThat( turno.siguiente(),is( creado )  );
    }

    @Test
     public void testSiguienteDebeDarUnJugadorDistintoAlAnteriorLuegoDeHaberSolicitadoElPrimerTurno(){
        Jugador player1 = new Jugador("Maradona", TipoEquipo.AUTOBOTS);
        Jugador player2 = new Jugador("DIEGO", TipoEquipo.DECEPTICONS);
        Turno turno =  new Turno(player1,player2);

        Jugador anterior = turno.siguiente();  //primer turno

        Assert.assertThat( turno.siguiente(),is( not( anterior ) ) );


    }

    @Test
    public void testSiguienteDebeDarUnJugadorDistintoAlAnteriorLuegoDeProbarloDosVeces(){
        Jugador player1 = new Jugador("Maradona", TipoEquipo.AUTOBOTS);
        Jugador player2 = new Jugador("DIEGO", TipoEquipo.DECEPTICONS);
        Turno turno =  new Turno(player1,player2);

        Jugador turno1= turno.siguiente();
        Jugador turno2= turno.siguiente();
        Assert.assertThat( turno1,is( not( turno2 ) ) );
        Assert.assertThat( turno.siguiente(),is( not( turno2 ) ) );


    }

}
