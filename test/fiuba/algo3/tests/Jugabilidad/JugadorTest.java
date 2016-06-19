package fiuba.algo3.tests.Jugabilidad;

import fiuba.algo3.modelo.Jugabilidad.Jugador.Jugador;
import fiuba.algo3.modelo.Jugabilidad.Jugador.PersonajeDeOtroEquipoException;
import fiuba.algo3.modelo.Jugabilidad.Jugador.PersonajeInexistenteException;
import fiuba.algo3.modelo.Jugabilidad.Jugador.PersonajeNombreDuplicadoExeptions;
import fiuba.algo3.modelo.TipoEquipo;
import fiuba.algo3.modelo.algoformer.Algoformer;
import org.junit.Assert;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by jose on 09/06/2016.
 */
public class JugadorTest {

//Jugador j2 = new Jugador ("Maradona", TipoEquipo.DECEPTICONS);
    @Test
    public void testObtenerNombreDevuelveElNombreDelJugador(){
        Jugador j1 = new Jugador ("Diego", TipoEquipo.AUTOBOTS);

        Assert.assertTrue(j1.obtenerNombre()=="Diego");}

    @Test
    public void testObtenerEquipoDevuelveElEquipoDelJugador(){
        Jugador j1 = new Jugador ("Armando", TipoEquipo.AUTOBOTS);
        Assert.assertTrue(j1.obtenerEquipo()==TipoEquipo.AUTOBOTS);}


    @Test//proba si algoformer no tiene equipo
    public void testAgregarPersonajeDebeAgregarPersonajeDelEquipoDelJugador(){
        Jugador j1 = new Jugador ("Diego", TipoEquipo.AUTOBOTS);
        Algoformer a1= new Algoformer("maradona");
        a1.setEquipo(TipoEquipo.AUTOBOTS);
        j1.agregarPersonaje(a1);
        Assert.assertTrue(a1==j1.obtenerPersonaje("maradona"));}


    @Test( expected = PersonajeDeOtroEquipoException.class )
    public void testAgregarPersonajeDebeLanzarExepcionCuandoElPersonajeEsDeOtroEquipo(){
        Jugador j1 = new Jugador ("Diego", TipoEquipo.AUTOBOTS);
        Algoformer a1= new Algoformer("maradona");
        a1.setEquipo(TipoEquipo.DECEPTICONS);
        j1.agregarPersonaje(a1);
        }

    @Test( expected = PersonajeDeOtroEquipoException.class )
    public void testAgregarPersonajeDebeLanzarExepcionCuandoElPersonajeNoTieneEquipo(){
        Jugador j1 = new Jugador ("Diego", TipoEquipo.AUTOBOTS);
        Algoformer a1= new Algoformer("maradona");
        j1.agregarPersonaje(a1);
    }

    @Test( expected = PersonajeNombreDuplicadoExeptions.class)
    public void testAgregarPersonajeDebeLanzarExepcionCuandoSeAgreganPersonajesConNombreIdentico(){
        Jugador j1 = new Jugador ("Diego", TipoEquipo.AUTOBOTS);
        Algoformer a1= new Algoformer("maradona");
        Algoformer a2= new Algoformer("maradona");
        a1.setEquipo(TipoEquipo.AUTOBOTS);
        a2.setEquipo(TipoEquipo.AUTOBOTS);
        j1.agregarPersonaje(a1);
        j1.agregarPersonaje(a2);

        }


    @Test( expected = PersonajeInexistenteException.class)
    public void testObtenerPersonajeDebeLanzarExepcionCuandoElPersonajeNoFueAgregado(){
        Jugador j1 = new Jugador ("Diego", TipoEquipo.AUTOBOTS);
        j1.obtenerPersonaje("PacoPerez");

    }

    @Test
    public void testObtenerPersonajeDevuelveElPersonajeCuandoFueAgregadoPreviamente(){

        Jugador j1 = new Jugador ("Diego", TipoEquipo.AUTOBOTS);
        Algoformer a1 = new Algoformer("diego");
        a1.setEquipo(TipoEquipo.AUTOBOTS);
        j1.agregarPersonaje(a1);
        Assert.assertTrue( a1== j1.obtenerPersonaje("diego"));
    }

    @Test
    public void testObtenerNombresDePersonajesDevuelveUnaListaVaciaSiNoHayPersonajes(){
        Jugador j1 = new Jugador ("Diego", TipoEquipo.AUTOBOTS);
        ArrayList<String> lista = j1.obtenerNombresDePersonajes();
        Assert.assertTrue(lista.isEmpty());
    }


    @Test //ojo si hay eliminados
    public void testObtenerNombresDePersonajesDevuelveListaConLosPersonajesAgregados(){

        Jugador j1 = new Jugador ("Diego", TipoEquipo.AUTOBOTS);
        Algoformer a1 = new Algoformer("diego");
        Algoformer a2 = new Algoformer("armando");
        a1.setEquipo(TipoEquipo.AUTOBOTS);
        a2.setEquipo(TipoEquipo.AUTOBOTS);
        j1.agregarPersonaje(a1);
        j1.agregarPersonaje(a2);

        ArrayList<String> lista = j1.obtenerNombresDePersonajes();
        Assert.assertTrue(lista.contains("diego"));
        Assert.assertTrue(lista.contains("armando"));

    }

    //TODO refactor quizas...
    @Test//( expected = JugadorEnEstadoDeEsperaException.class)
    public void testCreoUnJugadorYEstadoDelJugadorEsEsperando(){

       /* Tablero t1= new Tablero(10,10);
        Coordenada c1 = new Coordenada(1,1);
        Coordenada c2 = new Coordenada(2,2);

        EstadoJugador estado = new EstadoJugadorEsperando();
        Jugador j1 = new Jugador ("Diego", TipoEquipo.AUTOBOTS);
        Algoformer a2 = new Algoformer("armando");
        a2.setEquipo(TipoEquipo.AUTOBOTS);
        j1.agregarPersonaje(a2);
        t1.poner(a2,c1);
       j1.seleccionarPersonaje("armando");
        j1.atacar(c2,t1);

        */
        Jugador j1 = new Jugador ("Diego", TipoEquipo.AUTOBOTS);
        Assert.assertTrue(j1.getEstado().equals("esperando"));
    }

    @Test
    public void testInicioTurnoCambiaElEstadoDelJugadorAActivo(){

        Jugador j1 = new Jugador ("Diego", TipoEquipo.AUTOBOTS);
        j1.inicioTurno();
        Assert.assertTrue(j1.getEstado().equals("activo"));

    }

    @Test
    public void testFinTurnoCambiaElEstadoDelJugadorAEsperando(){


        Jugador j1 = new Jugador ("Diego", TipoEquipo.AUTOBOTS);
        j1.finTurno();
        Assert.assertTrue(j1.getEstado().equals("esperando"));
    };

    //TODO
/*
    @Test
    public void testFinTurnoNotificaATodosSusPersonajesElFinDelTurno(){
        Assert.assertTrue(false);
    };
*/



    @Test (expected = PersonajeInexistenteException.class)
    public void testSeleccionarPersonajeDebeLanzarExcepcionSiNoExiste(){

        Jugador j1 = new Jugador ("Diego", TipoEquipo.AUTOBOTS);
        j1.seleccionarPersonaje("Alberto");


    };

    @Test
    //todo
    public void testSeleccionarPersonajeDebeSeleccionarAlPersonaje(){
        Jugador j1 = new Jugador ("Diego", TipoEquipo.AUTOBOTS);
        Algoformer a1 = new Algoformer("diego");
        Algoformer a2 = new Algoformer("armando");
        a1.setEquipo(TipoEquipo.AUTOBOTS);
        a2.setEquipo(TipoEquipo.AUTOBOTS);
        j1.agregarPersonaje(a1);
        j1.agregarPersonaje(a2);
        j1.seleccionarPersonaje("diego");
        Assert.assertTrue(j1.getJugadorActivo()==a1);
        Assert.assertFalse(j1.getJugadorActivo()==a2);


    };

    @Test
    public void testSeleccionarPersonajeDebeSeleccionarAlPersonajeLuegoDeTenerOtroSeleccionado(){
        Jugador j1 = new Jugador ("Diego", TipoEquipo.AUTOBOTS);
        Algoformer a1 = new Algoformer("diego");
        Algoformer a2 = new Algoformer("armando");
        a1.setEquipo(TipoEquipo.AUTOBOTS);
        a2.setEquipo(TipoEquipo.AUTOBOTS);
        j1.agregarPersonaje(a1);
        j1.agregarPersonaje(a2);
        j1.seleccionarPersonaje("diego");
        j1.seleccionarPersonaje("armando");
        Assert.assertTrue(j1.getJugadorActivo()==a2);


    };

/*
    // ********************************************************************************************
    //   ACCIONES
    //*************************************************************************************

    @Test
    public void testMoverPersonajeDebeLanzarExcepcionSiNoHayPersonajeSeleccionado(){Assert.assertTrue(false);};

    @Test
    public void testMoverPersonajeDebeLanzarExcepcionSiElJugadorTieneEstadoEsperando(){Assert.assertTrue(false);};

    @Test
    public void testMoverPersonajeDebeMoverAlPersonajeSeleccionado (){Assert.assertTrue(false);};

    // TODO ver firmas de mover...?


    @Test
    public void testAtacarDebeLanzarExcepcionSiNoHayPersonajeSeleccionado(){Assert.assertTrue(false);};

    @Test
    public void testAtacarDebeLanzarExcepcionSiElJugadorTieneEstadoEsperando(){Assert.assertTrue(false);};

    @Test
    public void testAtacarDebeAtacarElAreaSeleccioneada (){Assert.assertTrue(false);};


    @Test
    public void testTranformarPersonajeDebeLanzarExcepcionSiNoHayPersonajeSeleccionado(){Assert.assertTrue(false);};

    @Test
    public void testTranformarPersonajeDebeLanzarExcepcionSiElJugadorTieneEstadoEsperando(){Assert.assertTrue(false);};

    @Test //TODO faltan
    public void testTranformarPersonajeDebeCambiarAlPersonajeSeleccionado (){Assert.assertTrue(false);};


    @Test
    public void testMurioUnPersonajeDebeLanzarExepcionSiElPersonajeNoEsDelJugador(){Assert.assertTrue(false);}

    @Test
    public void testMurioUnPersonajeDebeQuitarAlPersonajeDeLaListaDelJugador(){Assert.assertTrue(false);}

    @Test
    public void testMurioUnPersonajeDebeDejaarIntactosAlRestoDeLosPersonajes(){Assert.assertTrue(false);}



// quien va a avisar que se ejecuto el movimiento???



*/



}
