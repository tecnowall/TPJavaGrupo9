package fiuba.algo3.tests.Jugabilidad;

import fiuba.algo3.modelo.*;
import fiuba.algo3.modelo.Jugabilidad.Jugador.Jugador;
import fiuba.algo3.modelo.Jugabilidad.Juego.Partida;
import fiuba.algo3.modelo.algoformer.Algoformer;
import fiuba.algo3.modelo.observadores.ObservadorPartida;
import fiuba.algo3.modelo.tablero.Tablero;

import org.junit.Assert;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created by jose on 09/06/2016.
 */
public class PartidaTest {

    @Test
    public void testCrearPartidaDebeUbicarLaChispaEnElMedioDelTablero(){

     Jugador j1= new Jugador("Mario", TipoEquipo.AUTOBOTS);
     Jugador j2= new Jugador("Alfred", TipoEquipo.DECEPTICONS);
     Tablero tablero= new Tablero (20,20);

        Algoformer a1 = new Algoformer("OPTIMUSS"); a1.setEquipo(TipoEquipo.AUTOBOTS);
        Algoformer a2 = new Algoformer("BUMBLEBEE"); a2.setEquipo(TipoEquipo.AUTOBOTS);
        Algoformer a3 = new Algoformer("RATCHET"); a3.setEquipo(TipoEquipo.AUTOBOTS);

        Algoformer a4 = new Algoformer("MEGATRON"); a4.setEquipo(TipoEquipo.DECEPTICONS);
        Algoformer a5 = new Algoformer("BOECRUSHER");a5.setEquipo(TipoEquipo.DECEPTICONS);
        Algoformer a6 = new Algoformer("FRENZY");a6.setEquipo(TipoEquipo.DECEPTICONS);


        j1.agregarPersonaje(a1);         j1.agregarPersonaje(a2);         j1.agregarPersonaje(a3);

        j2.agregarPersonaje(a4); j2.agregarPersonaje(a5);j2.agregarPersonaje(a6);

     Partida unaPartida = new Partida(j1,j2,tablero);

        int x= (20-1) /2;
        int y= (20-1) /2;

        Coordenada cChispa = new Coordenada( x, y );

        j1.obtenerNombresDePersonajes();


        Assert.assertThat( unaPartida.obtenerChispaSuprema().getPosicion(), is( cChispa ) );



    };

    //TODO probar que de false con otras coodenadas
    //TODO exepcion si no estan los 6 personajes
    //TODO ojo sobrecarga Algoformer, sin parametros de vida, etc puede pinchar partida

    @Test
    public void testCrearPartidaDebePosicionarALosAlgoformersEnLosExtremos(){};

    @Test
    public void testCrearPartidaDebeLanzarExepcionCuandoLosJugadoresTienenElMismoEquipo(){};




}
