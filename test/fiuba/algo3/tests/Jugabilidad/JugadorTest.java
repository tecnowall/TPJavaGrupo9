package fiuba.algo3.tests.Jugabilidad;

import fiuba.algo3.modelo.Coordenada;
import fiuba.algo3.modelo.Jugabilidad.Jugador.*;
import fiuba.algo3.modelo.TipoEquipo;
import fiuba.algo3.modelo.algoformer.*;
import fiuba.algo3.modelo.tablero.Tablero;
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

    @Test( expected = PersonajeNombreDuplicadoException.class)
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
    @Test
    public void testCreoUnJugadorYEstadoDelJugadorEsEsperando(){


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
        Assert.assertTrue(j1.getPersonajeSeleccionado()==a1);
        Assert.assertFalse(j1.getPersonajeSeleccionado()==a2);


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
        Assert.assertTrue(j1.getPersonajeSeleccionado()==a2);


    };

    @Test (expected = PersonajeNoSeleccionadoException.class)
    public void testMoverPersonajeDebeLanzarExcepcionSiNoHayPersonajeSeleccionado(){

        Jugador j1 = new Jugador ("Diego", TipoEquipo.AUTOBOTS);
        Algoformer a1 = new Algoformer("diego");

        a1.setEquipo(TipoEquipo.AUTOBOTS);

        j1.agregarPersonaje(a1);
        Coordenada c1 = new Coordenada(5,5);
        Tablero tablero= new Tablero(10,10);
        j1.moverPersonaje(c1,tablero);
    }


    // ********************************************************************************************
    //   ACCIONES
    //*************************************************************************************

    @Test (expected = JugadorEnEstadoDeEsperaException.class)
    public void testMoverPersonajeDebeLanzarExcepcionSiElJugadorTieneEstadoEsperando(){

        Jugador j1 = new Jugador ("Diego", TipoEquipo.AUTOBOTS);
        Algoformer a1 = new Algoformer("diego");

        a1.setEquipo(TipoEquipo.AUTOBOTS);

        j1.agregarPersonaje(a1);
        Coordenada c1 = new Coordenada(5,5);
        Tablero tablero= new Tablero(10,10);
        j1.seleccionarPersonaje("diego");
        j1.moverPersonaje(c1,tablero);

    };

    @Test (expected = JugadorEnEstadoDeEsperaException.class)
    public void testAtacarDebeLanzarExcepcionSiElJugadorTieneEstadoEsperando(){
        Jugador j1 = new Jugador ("Diego", TipoEquipo.AUTOBOTS);
        Algoformer a1 = new Algoformer("diego");

        a1.setEquipo(TipoEquipo.AUTOBOTS);

        j1.agregarPersonaje(a1);
        Coordenada c1 = new Coordenada(5,5);
        Tablero tablero= new Tablero(10,10);
        j1.seleccionarPersonaje("diego");
        j1.atacar(c1,tablero);

       };


    @Test (expected = JugadorEnEstadoDeEsperaException.class)
    public void testTranformarPersonajeDebeLanzarExcepcionSiElJugadorTieneEstadoEsperando(){
        Jugador j1 = new Jugador ("Diego", TipoEquipo.AUTOBOTS);
        Algoformer a1 = new Algoformer("diego");

        a1.setEquipo(TipoEquipo.AUTOBOTS);

        j1.agregarPersonaje(a1);
        Coordenada c1 = new Coordenada(5,5);
        Tablero tablero= new Tablero(10,10);
        j1.seleccionarPersonaje("diego");
        j1.tranformarPersonaje(c1,tablero);

        };



    @Test
    public void testMoverPersonajeDebeMoverAlPersonajeSeleccionado (){

        Jugador j1 = new Jugador ("Diego", TipoEquipo.AUTOBOTS);
        Forma forma1 = new Humanoide( 50, 2, 2 );
        Forma forma2 = new Terrestre( 15, 4, 5 );
        Algoformer a1 = new Algoformer( "diego", 500, forma1, forma2 );

        a1.setEquipo(TipoEquipo.AUTOBOTS);


        j1.agregarPersonaje(a1);
        Coordenada c0 = new Coordenada(4,4);
        Coordenada c1 = new Coordenada(5,5);
        Tablero tablero= new Tablero(10,10);
        j1.inicioTurno();
        j1.seleccionarPersonaje("diego");
        tablero.poner(a1,c0);
        a1.ubicar(c0);
        j1.moverPersonaje(c1,tablero);

        Assert.assertTrue(a1.getPosicion().equals(c1));
      };


    @Test (expected = PersonajeNoSeleccionadoException.class)
    public void testAtacarDebeLanzarExcepcionSiNoHayPersonajeSeleccionado(){
        Jugador j1 = new Jugador ("Diego", TipoEquipo.AUTOBOTS);
        Algoformer a1 = new Algoformer("diego");

        a1.setEquipo(TipoEquipo.AUTOBOTS);

        j1.agregarPersonaje(a1);
        Coordenada c1 = new Coordenada(5,5);
        Tablero tablero= new Tablero(10,10);
        j1.atacar(c1,tablero);
    };

    @Test (expected = PersonajeNoSeleccionadoException.class)
    public void testTranformarPersonajeDebeLanzarExcepcionSiNoHayPersonajeSeleccionado(){
        Jugador j1 = new Jugador ("Diego", TipoEquipo.AUTOBOTS);
        Algoformer a1 = new Algoformer("diego");

        a1.setEquipo(TipoEquipo.AUTOBOTS);

        j1.agregarPersonaje(a1);
        Coordenada c1 = new Coordenada(5,5);
        Tablero tablero= new Tablero(10,10);
        j1.tranformarPersonaje(c1,tablero);
       };


    @Test (expected = PersonajeInexistenteException.class)
    public void testMurioUnPersonajeDebeLanzarExepcionSiElPersonajeNoEsDelJugador(){

        Jugador j1 = new Jugador ("Diego", TipoEquipo.AUTOBOTS);
        Jugador j2 = new Jugador("Milton" , TipoEquipo.AUTOBOTS);
        Algoformer a1 = new Algoformer("diego");
        a1.setEquipo(TipoEquipo.AUTOBOTS);
        Algoformer a2 = new Algoformer("Sandro");
        a2.setEquipo(TipoEquipo.AUTOBOTS);
        j1.agregarPersonaje(a1);
        j2.agregarPersonaje(a2);

        j1.murioUnPersonaje(a2);}

    @Test (expected = PersonajeInexistenteException.class)
    public void testMurioUnPersonajeDebeQuitarAlPersonajeDeLaListaDelJugador(){

        Jugador j1 = new Jugador ("Diego", TipoEquipo.AUTOBOTS);

        Algoformer a1 = new Algoformer("diego");
        a1.setEquipo(TipoEquipo.AUTOBOTS);
        Algoformer a2 = new Algoformer("Sandro");
        a2.setEquipo(TipoEquipo.AUTOBOTS);

        j1.agregarPersonaje(a1);
        j1.agregarPersonaje(a2);

        j1.murioUnPersonaje(a1);
        j1.obtenerPersonaje("diego");
    }

    @Test
    public void testMurioUnPersonajeDebeDejaarIntactosAlRestoDeLosPersonajes(){

        Jugador j1 = new Jugador ("Diego", TipoEquipo.AUTOBOTS);

        Algoformer a1 = new Algoformer("diego");
        a1.setEquipo(TipoEquipo.AUTOBOTS);
        Algoformer a2 = new Algoformer("Sandro");
        a2.setEquipo(TipoEquipo.AUTOBOTS);

        j1.agregarPersonaje(a1);
        j1.agregarPersonaje(a2);

        j1.murioUnPersonaje(a1);



        Assert.assertTrue(a2.equals( j1.obtenerPersonaje("Sandro")));}

@Test (expected = PersonajeNoSeleccionadoException.class)
    public void testAlEliminarUnPersonajeSiEsteEstabaSeleccionadoDejaDeEstarlo(){

        Jugador j1 = new Jugador ("Diego", TipoEquipo.AUTOBOTS);

        Algoformer a1 = new Algoformer("diego");
        a1.setEquipo(TipoEquipo.AUTOBOTS);
        Algoformer a2 = new Algoformer("Sandro");
        a2.setEquipo(TipoEquipo.AUTOBOTS);

        j1.agregarPersonaje(a1);
        j1.agregarPersonaje(a2);
        j1.seleccionarPersonaje(a1.getNombre());
        j1.murioUnPersonaje(a1);
        j1.combinarPersonaje();
    }

    @Test
    public void testTenesPersonajesDebeDarFalseCuandoElJugadorEsRecienCreado(){
        Jugador j1 = new Jugador ("Diego", TipoEquipo.AUTOBOTS);

        Assert.assertFalse(j1.tenesPersonajes());


    };



 @Test
    public void testTenesPersonajesDebeDarTrueCuandoAlJugadorSeLeAgregaUnPersonaje(){
     Jugador j1 = new Jugador ("Diego", TipoEquipo.AUTOBOTS);
     Algoformer a1 = new Algoformer("diego");

     a1.setEquipo(TipoEquipo.AUTOBOTS);
     j1.agregarPersonaje(a1);

     Assert.assertTrue(j1.tenesPersonajes());


    };

    @Test
    public void testTenesPersonajesDebeDarTrueCuandoAlJugadorSeLeAgreganVariosPersonajesYSeEliminaUno(){
        Jugador j1 = new Jugador ("Diego", TipoEquipo.AUTOBOTS);
        Algoformer a1 = new Algoformer("diego");
        Algoformer a2 = new Algoformer("maradona");
        a1.setEquipo(TipoEquipo.AUTOBOTS);
        j1.agregarPersonaje(a1);
        a2.setEquipo(TipoEquipo.AUTOBOTS);
        j1.agregarPersonaje(a2);

        j1.murioUnPersonaje(a1);
        Assert.assertTrue(j1.tenesPersonajes());
   };

  /*    TODO este va a dar JugadorSinPartidaException   por que notifica a la vista que se quedo sin personajes....
    @Test
    public void testTenesPersonajesDebeDarFalseCuandoAlJugadorSeLeAgregaUnPersonajeYSeLoElimina(){
        Jugador j1 = new Jugador ("Diego", TipoEquipo.AUTOBOTS);
        Algoformer a1 = new Algoformer("diego");

        a1.setEquipo(TipoEquipo.AUTOBOTS);
        j1.agregarPersonaje(a1);
        j1.murioUnPersonaje(a1);
        Assert.assertFalse(j1.tenesPersonajes());


    };


    @Test
    public void testAtacarDebeAtacarElAreaSeleccioneada (){Assert.assertTrue(false);};

    @Test //TODO faltan
    public void testTranformarPersonajeDebeCambiarAlPersonajeSeleccionado (){Assert.assertTrue(false);};


*/


        @Test (expected =FaltanPersonajesParaFusionException.class)
        public void testCombinarPersonajeDebeLanzarExepcionSiNoSeAvisoInicioJuegoAJugador(){

            Jugador j1 = new Jugador ("Diego", TipoEquipo.AUTOBOTS);
            Autobot a1 = Autobot.getOptimus();
            Autobot a2 = Autobot.getBumblebee();
            Autobot a3 = Autobot.getRatchet();

            j1.agregarPersonaje(a1);
            j1.agregarPersonaje(a2);
            j1.agregarPersonaje(a3);
            j1.inicioTurno();
            j1.seleccionarPersonaje(a1.getNombre());

            j1.combinarPersonaje();
        }

    @Test
        public void TestCombinarPersonajeDebreCrearUnaFusion(){
        Tablero tablero = new Tablero(10,10);
        Jugador j1 = new Jugador ("Diego", TipoEquipo.AUTOBOTS);
        Autobot a1 = Autobot.getOptimus();
        Autobot a2 = Autobot.getBumblebee();
        Autobot a3 = Autobot.getRatchet();

        a1.setTablero(tablero);
        a2.setTablero(tablero);
        a3.setTablero(tablero);


        Coordenada c1 = new Coordenada( 0, 2 );
        Coordenada c2 = new Coordenada( 0, 4 );
        Coordenada c3 = new Coordenada( 0, 6 );
        a1.ubicar(c1);
        a2.ubicar(c2);
        a3.ubicar(c3);

        tablero.poner (a1,c1);
        tablero.poner (a2,c2);
        tablero.poner (a3,c3);

        j1.agregarPersonaje(a1);
        j1.agregarPersonaje(a2);
        j1.agregarPersonaje(a3);
        j1.inicioTurno();
        j1.seleccionarPersonaje(a1.getNombre());
        j1.iniciaJuego();


        j1.combinarPersonaje();


        j1.finTurno();
        j1.inicioTurno();
        j1.finTurno();
        j1.inicioTurno();

        j1.obtenerPersonaje("Superion");

        assert (j1.getAllPersonajes().size() == 1);
    }








        @Test(expected =FaltanPersonajesParaFusionException.class)
        public void testCombinarPersonajeDebeLanzarExcepcionSiJugadorSeCreaConMenosDeTresPersonajes(){

            Jugador j1 = new Jugador ("Diego", TipoEquipo.AUTOBOTS);
            Autobot a1 = Autobot.getOptimus();
            Autobot a2 = Autobot.getBumblebee();


            j1.agregarPersonaje(a1);
            j1.agregarPersonaje(a2);

            j1.inicioTurno();
            j1.seleccionarPersonaje(a1.getNombre());
            j1.iniciaJuego();


            j1.combinarPersonaje();

        }

        @Test(expected = FaltanPersonajesParaFusionException.class)
        public void testCombinarPersonajeDebeLanzarExcepcionSiTieneTodasLasPartesYMuereUna(){


            Tablero tablero = new Tablero(10,10);
            Jugador j1 = new Jugador ("Diego", TipoEquipo.AUTOBOTS);
            Autobot a1 = Autobot.getOptimus();
            Autobot a2 = Autobot.getBumblebee();
            Autobot a3 = Autobot.getRatchet();

            a1.setTablero(tablero);
            a2.setTablero(tablero);
            a3.setTablero(tablero);


            Coordenada c1 = new Coordenada( 0, 2 );
            Coordenada c2 = new Coordenada( 0, 4 );
            Coordenada c3 = new Coordenada( 0, 6 );
            a1.ubicar(c1);
            a2.ubicar(c2);
            a3.ubicar(c3);

            tablero.poner (a1,c1);
            tablero.poner (a2,c2);
            tablero.poner (a3,c3);

            j1.agregarPersonaje(a1);
            j1.agregarPersonaje(a2);
            j1.agregarPersonaje(a3);
            j1.inicioTurno();
            j1.seleccionarPersonaje(a1.getNombre());
            j1.iniciaJuego();
            j1.murioUnPersonaje(a2);


            j1.combinarPersonaje();


        }


        @Test(expected =  NoSePuedeFusionarMasDeUnaVezException.class)
        public void TestCombinarPersonajeDebreLanzarExcepcionSiYaSeInicioUnaFusionPreviamente(){

            Tablero tablero = new Tablero(10,10);
            Jugador j1 = new Jugador ("Diego", TipoEquipo.AUTOBOTS);
            Autobot a1 = Autobot.getOptimus();
            Autobot a2 = Autobot.getBumblebee();
            Autobot a3 = Autobot.getRatchet();

            a1.setTablero(tablero);
            a2.setTablero(tablero);
            a3.setTablero(tablero);


            Coordenada c1 = new Coordenada( 0, 2 );
            Coordenada c2 = new Coordenada( 0, 4 );
            Coordenada c3 = new Coordenada( 0, 6 );
            a1.ubicar(c1);
            a2.ubicar(c2);
            a3.ubicar(c3);

            tablero.poner (a1,c1);
            tablero.poner (a2,c2);
            tablero.poner (a3,c3);

            j1.agregarPersonaje(a1);
            j1.agregarPersonaje(a2);
            j1.agregarPersonaje(a3);
            j1.inicioTurno();
            j1.seleccionarPersonaje(a1.getNombre());
            j1.iniciaJuego();
            j1.combinarPersonaje();
            j1.combinarPersonaje();


        }

    @Test(expected =  NoSePuedeFusionarMasDeUnaVezException.class)
    public void TestCombinarPersonajeDebreLanzarExcepcionSiSeCompletoLaFisionYSeIntentaUtilizarlaNuevamente(){

        Tablero tablero = new Tablero(10,10);
        Jugador j1 = new Jugador ("Diego", TipoEquipo.AUTOBOTS);
        Autobot a1 = Autobot.getOptimus();
        Autobot a2 = Autobot.getBumblebee();
        Autobot a3 = Autobot.getRatchet();

        a1.setTablero(tablero);
        a2.setTablero(tablero);
        a3.setTablero(tablero);


        Coordenada c1 = new Coordenada( 0, 2 );
        Coordenada c2 = new Coordenada( 0, 4 );
        Coordenada c3 = new Coordenada( 0, 6 );
        a1.ubicar(c1);
        a2.ubicar(c2);
        a3.ubicar(c3);

        tablero.poner (a1,c1);
        tablero.poner (a2,c2);
        tablero.poner (a3,c3);

        j1.agregarPersonaje(a1);
        j1.agregarPersonaje(a2);
        j1.agregarPersonaje(a3);
        j1.inicioTurno();
        j1.seleccionarPersonaje(a1.getNombre());
        j1.iniciaJuego();
        j1.combinarPersonaje();
        j1.inicioTurno();
        j1.inicioTurno();
        j1.seleccionarPersonaje("Superion");
        j1.combinarPersonaje();


    }

        @Test (expected =  JugadorEnEstadoDeEsperaException.class)
        public void testCombinarPersonajeDebeLanzarExepcionSiElJugadorNoEstaActivo(){

            Jugador j1 = new Jugador ("Diego", TipoEquipo.AUTOBOTS);
            Autobot a1 = Autobot.getOptimus();
            Autobot a2 = Autobot.getBumblebee();


            j1.agregarPersonaje(a1);
            j1.agregarPersonaje(a2);


            j1.seleccionarPersonaje(a1.getNombre());
            j1.iniciaJuego();


            j1.combinarPersonaje();

        }

    @Test

    public void testCombinarPersonajeDebeCrearUnFusionadoEnLaUltimaPosicionDelFusionante(){


        Tablero tablero = new Tablero(10,10);
        Jugador j1 = new Jugador ("Diego", TipoEquipo.AUTOBOTS);
        Autobot a1 = Autobot.getOptimus();
        Autobot a2 = Autobot.getBumblebee();
        Autobot a3 = Autobot.getRatchet();

        a1.setTablero(tablero);
        a2.setTablero(tablero);
        a3.setTablero(tablero);


        Coordenada c1 = new Coordenada( 0, 2 );
        Coordenada c2 = new Coordenada( 0, 4 );
        Coordenada c3 = new Coordenada( 0, 6 );
        Coordenada c4 = new Coordenada( 0, 3 );
        a1.ubicar(c1);
        a2.ubicar(c2);
        a3.ubicar(c3);

        tablero.poner (a1,c1);
        tablero.poner (a2,c2);
        tablero.poner (a3,c3);

        j1.agregarPersonaje(a1);
        j1.agregarPersonaje(a2);
        j1.agregarPersonaje(a3);
        j1.inicioTurno();
        j1.seleccionarPersonaje(a1.getNombre());
        j1.iniciaJuego();
        j1.combinarPersonaje();


        j1.inicioTurno();
        j1.inicioTurno();
       assert ( j1.obtenerPersonaje("Superion").getPosicion().equals(c1));




    }

    @Test (expected = MovimientoFueraDeRangoException.class)
    public void testAlCombinarPersonajeElFusionanteNoPuedeMoverseHastaCompletarFusion(){


        Tablero tablero = new Tablero(10,10);
        Jugador j1 = new Jugador ("Diego", TipoEquipo.AUTOBOTS);
        Autobot a1 = Autobot.getOptimus();
        Autobot a2 = Autobot.getBumblebee();
        Autobot a3 = Autobot.getRatchet();

        a1.setTablero(tablero);
        a2.setTablero(tablero);
        a3.setTablero(tablero);


        Coordenada c1 = new Coordenada( 0, 2 );
        Coordenada c2 = new Coordenada( 0, 4 );
        Coordenada c3 = new Coordenada( 0, 6 );
        Coordenada c4 = new Coordenada( 0, 3 );
        a1.ubicar(c1);
        a2.ubicar(c2);
        a3.ubicar(c3);

        tablero.poner (a1,c1);
        tablero.poner (a2,c2);
        tablero.poner (a3,c3);

        j1.agregarPersonaje(a1);
        j1.agregarPersonaje(a2);
        j1.agregarPersonaje(a3);
        j1.inicioTurno();
        j1.seleccionarPersonaje(a1.getNombre());
        j1.iniciaJuego();
        j1.combinarPersonaje();
        a1.mover(tablero,c4);
    }

    @Test
    public void testAlCombinarPersonajeElFusionadoSePuedeMoverCuandoSeCompletaLaFusion(){


        Tablero tablero = new Tablero(10,10);
        Jugador j1 = new Jugador ("Diego", TipoEquipo.AUTOBOTS);
        Autobot a1 = Autobot.getOptimus();
        Autobot a2 = Autobot.getBumblebee();
        Autobot a3 = Autobot.getRatchet();

        a1.setTablero(tablero);
        a2.setTablero(tablero);
        a3.setTablero(tablero);


        Coordenada c1 = new Coordenada( 0, 2 );
        Coordenada c2 = new Coordenada( 0, 4 );
        Coordenada c3 = new Coordenada( 0, 6 );
        Coordenada c4 = new Coordenada( 0, 3 );
        a1.ubicar(c1);
        a2.ubicar(c2);
        a3.ubicar(c3);

        tablero.poner (a1,c1);
        tablero.poner (a2,c2);
        tablero.poner (a3,c3);

        j1.agregarPersonaje(a1);
        j1.agregarPersonaje(a2);
        j1.agregarPersonaje(a3);
        j1.inicioTurno();
        j1.seleccionarPersonaje(a1.getNombre());
        j1.iniciaJuego();
        j1.combinarPersonaje();


        j1.inicioTurno();
        j1.inicioTurno();
        a1.mover(tablero,c4);
        assert ( j1.obtenerPersonaje("Superion").getPosicion().equals(c4));




    }


}

