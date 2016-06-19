package fiuba.algo3.tests;

import static org.hamcrest.CoreMatchers.is;

import fiuba.algo3.modelo.*;
import fiuba.algo3.modelo.Jugabilidad.Jugador.Jugador;
import fiuba.algo3.modelo.Jugabilidad.Jugador.PersonajeInexistenteException;
import fiuba.algo3.modelo.algoformer.*;
import fiuba.algo3.modelo.bonus.BonusDobleCanion;
import fiuba.algo3.modelo.bonus.BonusTormentaPsionica;
import fiuba.algo3.modelo.tablero.Capturable;
import fiuba.algo3.modelo.tablero.Tablero;
import fiuba.algo3.modelo.terreno.Terreno;
import fiuba.algo3.modelo.terreno.TormentaPsionica;

import org.junit.Assert;
import org.junit.Test;

public class AlgoformerTest {

	@Test
	public void testAlTransformarseCambianAtaqueRangoYVelocidad() {
		Forma forma1 = new Humanoide( 50, 2, 2 );
		Forma forma2 = new Terrestre( 15, 4, 5 );
		Algoformer optimus = new Algoformer( "Optimus", 500, forma1, forma2 );

		//forma inicial
		Assert.assertThat(optimus.getPoder(), is( 50 ) );
		Assert.assertThat(optimus.getRango(), is( 2 ) );
		Assert.assertThat(optimus.getVelocidad(), is( 2 ) );

		optimus.transformar();

		//forma alterna
		Assert.assertThat(optimus.getPoder(), is( 15 ) );
		Assert.assertThat(optimus.getRango(), is( 4 ) );
		Assert.assertThat(optimus.getVelocidad(), is( 5 ) );

		optimus.transformar();
		
		//forma inicial de vuelta
		Assert.assertThat(optimus.getPoder(), is( 50 ) );
		Assert.assertThat(optimus.getRango(), is( 2 ) );
		Assert.assertThat(optimus.getVelocidad(), is( 2 ) );
	}
	
	@Test
	public void testSeMantieneLaVidaAlTransformarse() {
		Forma forma1 = new Humanoide( 50, 2, 2 );
		Forma forma2 = new Terrestre( 15, 4, 5 );
		Algoformer optimus = new Algoformer( "Optimus", 500, forma1, forma2 );

		//forma inicial
		Assert.assertThat(optimus.getVida(), is( 500 ) );
		optimus.setVida( 250 );
		optimus.transformar();

		//forma alterna
		Assert.assertThat(optimus.getVida(), is( 250 ) );
		optimus.setVida( 125 );
		optimus.transformar();
		
		//forma inicial de vuelta
		Assert.assertThat(optimus.getVida(), is( 125 ) );

	}
	@Test
	public void testMoverUnAlgoformerDentroDeSuAlcanceCambiaSuPosicion() {
		Tablero unTablero = new Tablero( 20, 20 );
		Forma forma1 = new Humanoide( 50, 20, 20 );
		Forma forma2 = new Terrestre( 15, 4, 5 );
		Algoformer optimus = new Algoformer( "Optimus", 500, forma1, forma2 );
		Coordenada origen = new Coordenada( 0, 0 );
		Coordenada destino = new Coordenada( 14, 14 );
		Coordenada destinoLejano = new Coordenada( 9, 9 ); //alcanzable por el modo alterno pero no por el humanoide
		
		unTablero.poner( optimus, origen );
		Assert.assertThat( optimus.getPosicion(), is( origen ) );
		
		optimus.mover( unTablero, destino );
		Assert.assertThat( optimus.getPosicion(), is( destino ) );
		
		optimus.transformar();
		optimus.mover( unTablero, destinoLejano );
		Assert.assertThat( optimus.getPosicion(), is( destinoLejano ) );
	}
	
	@Test(expected = MovimientoFueraDeRangoException.class )
	public void testMoverUnAlgoformerFueraDeSuAlcanceLanzaExcepcion() {
		Tablero unTablero = new Tablero( 20, 20 );
		Forma forma1 = new Humanoide( 50, 2, 2 );
		Forma forma2 = new Terrestre( 15, 4, 5 );
		Algoformer optimus = new Algoformer( "Optimus", 500, forma1, forma2 );
		Coordenada origen = new Coordenada( 2, 2 );
		Coordenada destinoLejano = new Coordenada( 7, 7 );
		
		unTablero.poner( optimus, origen );
		Assert.assertThat( optimus.getPosicion(), is( origen ) );
				
		optimus.mover( unTablero, destinoLejano );
	}
	

	
	@Test
	public void testAtacarAUnAlgoFormerReduceSuVida() throws AtaqueFueraDeRangoException, FuegoAmigoException{
		Tablero unTablero = new Tablero( 20, 20 );
		Forma forma1a = new Humanoide( 50, 2, 2 );
		Forma forma2a = new Terrestre( 15, 4, 5 );
		Algoformer optimus = new Algoformer( "Optimus", 500, forma1a, forma2a);
		optimus.setEquipo(TipoEquipo.AUTOBOTS);
		Forma forma1b = new Humanoide( 10, 3, 1 );
		Forma forma2b = new Terrestre( 55, 2, 8 );
		Algoformer megatron = new Algoformer( "Megatron", 550, forma1b, forma2b);
		megatron.setEquipo(TipoEquipo.DECEPTICONS);
		
		Coordenada origena = new Coordenada( 2, 2 );
		Coordenada origenb = new Coordenada( 3, 3 );
		unTablero.poner( optimus, origena );
		unTablero.poner( megatron, origenb );
		optimus.atacar(unTablero, origenb);
		Assert.assertTrue(megatron.getVida()==500);
	}

	@Test
	public void testCuandoAlgoFormerMuereSeSacaDelTablero() throws AtaqueFueraDeRangoException, FuegoAmigoException{
		Tablero unTablero = new Tablero( 20, 20 );
		Forma forma1a = new Humanoide( 5000, 2, 2 );
		Forma forma2a = new Terrestre( 15, 4, 5 );
		Algoformer optimus = new Algoformer( "Optimus", 500, forma1a, forma2a);
		optimus.setEquipo(TipoEquipo.AUTOBOTS);
		Forma forma1b = new Humanoide( 10, 3, 1 );
		Forma forma2b = new Terrestre( 55, 2, 8 );
		Algoformer megatron = new Algoformer( "Megatron", 550, forma1b, forma2b);
		megatron.setEquipo(TipoEquipo.DECEPTICONS);
		optimus.setTablero(unTablero);
		megatron.setTablero(unTablero);

		Jugador j1= new Jugador("Diego", TipoEquipo.DECEPTICONS);
		Jugador j2= new Jugador("Maradona", TipoEquipo.AUTOBOTS);
		j1.agregarPersonaje(megatron);
		j2.agregarPersonaje(optimus);

		Coordenada origena = new Coordenada( 2, 2 );
		Coordenada origenb = new Coordenada( 3, 3 );
		unTablero.poner( optimus, origena );
		unTablero.poner( megatron, origenb );
		optimus.atacar(unTablero, origenb);
		Assert.assertThat( unTablero.estaOcupado( origenb ), is( false ) );
	}

	@Test(expected = PersonajeInexistenteException.class )
	public void testCuandoAlgoFormerMuereSeSacaDelJugador() throws AtaqueFueraDeRangoException, FuegoAmigoException{
		Tablero unTablero = new Tablero( 20, 20 );
		Forma forma1a = new Humanoide( 5000, 2, 2 );
		Forma forma2a = new Terrestre( 15, 4, 5 );
		Algoformer optimus = new Algoformer( "Optimus", 500, forma1a, forma2a);
		optimus.setEquipo(TipoEquipo.AUTOBOTS);
		Forma forma1b = new Humanoide( 10, 3, 1 );
		Forma forma2b = new Terrestre( 55, 2, 8 );
		Algoformer megatron = new Algoformer( "Megatron", 550, forma1b, forma2b);
		megatron.setEquipo(TipoEquipo.DECEPTICONS);
		optimus.setTablero(unTablero);
		megatron.setTablero(unTablero);

		Jugador j1= new Jugador("Diego", TipoEquipo.DECEPTICONS);
		Jugador j2= new Jugador("Maradona", TipoEquipo.AUTOBOTS);
		j1.agregarPersonaje(megatron);
		j2.agregarPersonaje(optimus);

		Coordenada origena = new Coordenada( 2, 2 );
		Coordenada origenb = new Coordenada( 3, 3 );
		unTablero.poner( optimus, origena );
		unTablero.poner( megatron, origenb );
		optimus.atacar(unTablero, origenb);

		j1.obtenerPersonaje("Megatron");
	}

	
	@Test(expected = AtaqueFueraDeRangoException.class )
	public void testAtacarAUnAlgoFormerFueraDeRango() throws AtaqueFueraDeRangoException, FuegoAmigoException{
		Tablero unTablero = new Tablero( 20, 20 );
		Forma forma1a = new Humanoide( 50, 2, 2 );
		Forma forma2a = new Terrestre( 15, 4, 5 );
		Algoformer optimus = new Algoformer( "Optimus", 500, forma1a, forma2a);
		optimus.setEquipo(TipoEquipo.AUTOBOTS);
		Forma forma1b = new Humanoide( 10, 3, 1 );
		Forma forma2b = new Terrestre( 55, 2, 8 );
		Algoformer megatron = new Algoformer( "Megatron", 550, forma1b, forma2b);
		megatron.setEquipo(TipoEquipo.DECEPTICONS);
		Coordenada origena = new Coordenada( 2, 2 );
		Coordenada origenb = new Coordenada( 10, 15 );
		unTablero.poner( optimus, origena );
		unTablero.poner( megatron, origenb );
		optimus.atacar(unTablero, origenb);
	}
	
	@Test(expected = FuegoAmigoException.class )
	public void testFuegoAmigoLanzaExcepcion() throws AtaqueFueraDeRangoException, FuegoAmigoException{
		Tablero unTablero = new Tablero( 20, 20 );
		Forma forma1a = new Humanoide( 50, 2, 2 );
		Forma forma2a = new Terrestre( 15, 4, 5 );
		Algoformer optimus = new Algoformer( "Optimus", 500, forma1a, forma2a);
		optimus.setEquipo(TipoEquipo.AUTOBOTS);
		Forma forma1b = new Humanoide( 10, 3, 1 );
		Forma forma2b = new Terrestre( 55, 2, 8 );
		Algoformer megatron = new Algoformer( "Megatron", 550, forma1b, forma2b);
		megatron.setEquipo(TipoEquipo.AUTOBOTS);
		
		Coordenada origena = new Coordenada( 2, 2 );
		Coordenada origenb = new Coordenada( 3, 3 );
		unTablero.poner( optimus, origena );
		unTablero.poner( megatron, origenb );
		optimus.atacar(unTablero, origenb);

	}
	
	@Test
	public void testPasarPorUnBonusLoCaptura(){
		Tablero unTablero = new Tablero( 20, 20 );
		Forma forma1a = new Aerea( 100, 20, 20 );
		Forma forma2a = new Aerea( 15, 4, 5 );
		Algoformer optimus = new Algoformer( "Optimus", 500, forma1a, forma2a);
		Capturable bonus = new BonusDobleCanion();
		Capturable bonus2 = new BonusTormentaPsionica();
		Terreno tormenta = new TormentaPsionica();
		Coordenada origen = new Coordenada( 0, 0 );
		Coordenada enElCamino = new Coordenada( 1, 1 );
		Coordenada enElCamino2 = new Coordenada( 4, 4 );
		Coordenada destino = new Coordenada( 4, 4 );
		
		
		unTablero.poner( bonus, enElCamino2 );
		unTablero.setTerreno( enElCamino, tormenta );		
		
		unTablero.poner( optimus, origen );

		optimus.mover( unTablero, destino );
		Assert.assertThat( optimus.getPoder(), is( 120 ) ); //100*0.6*2 = 120
	}

	@Test
	public void  testAgregarJugadorDebeAgregarAlJugador(){

		Jugador j1 = new Jugador("Diego",TipoEquipo.AUTOBOTS);
		Algoformer a1 = new Algoformer("truchibot");
		a1.setEquipo(TipoEquipo.AUTOBOTS);

		a1.agregarJugador(j1);

		Assert.assertTrue(j1 == a1.getJugador());}

	@Test(expected = YaPoseoJugadorException.class )
	public void  testAgregarJugadorDebeLanzarExcepcionSiHayAgregadoPreviamenteOtroJugador(){

		Jugador j1 = new Jugador("Diego",TipoEquipo.AUTOBOTS);
		Jugador j2 = new Jugador("Maradona",TipoEquipo.DECEPTICONS);
		Algoformer a1 = new Algoformer("truchibot");
		//a1.setEquipo(TipoEquipo.AUTOBOTS);

		a1.agregarJugador(j1);
		a1.agregarJugador(j2);
}

}
