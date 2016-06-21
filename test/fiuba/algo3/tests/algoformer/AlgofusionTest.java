package fiuba.algo3.tests.algoformer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.modelo.Coordenada;
import fiuba.algo3.modelo.TipoEquipo;
import fiuba.algo3.modelo.Jugabilidad.Jugador.Jugador;
import fiuba.algo3.modelo.algoformer.Algofusion;
import fiuba.algo3.modelo.algoformer.Autobot;
import fiuba.algo3.modelo.algoformer.Decepticon;
import fiuba.algo3.modelo.tablero.Tablero;

public class AlgofusionTest {

	@Test
	public void testFusionarTresAutobotsDevuelveSuperionEnLaPosicionDelQueIniciaLaFusion(){
		Jugador jugador = new Jugador("jugador", TipoEquipo.AUTOBOTS );
		Tablero tablero = new Tablero( 20, 20 );
		Autobot optimus = Autobot.getOptimus();
		Autobot bumblebee = Autobot.getBumblebee();
		Autobot ratchet = Autobot.getRatchet();
		optimus.setTablero(tablero);
		bumblebee.setTablero(tablero);
		ratchet.setTablero(tablero);

		Coordenada origena = new Coordenada( 0, 0 );
		Coordenada origenb = new Coordenada( 1, 1 );
		Coordenada origenc = new Coordenada( 2, 2 );
		
		tablero.poner( optimus, origena);
		tablero.poner( bumblebee, origenb);
		tablero.poner( ratchet, origenc);
		
		jugador.agregarPersonaje(optimus);
		System.out.println(optimus.getJugador().getAllPersonajes().size());
		jugador.agregarPersonaje(bumblebee);
		System.out.println(optimus.getJugador().getAllPersonajes().size());
		jugador.agregarPersonaje(ratchet);
		System.out.println(optimus.getJugador().getAllPersonajes().size());
		
		
		optimus.iniciarFusion( optimus, bumblebee, ratchet );
		Algofusion superion = optimus.completarFusion();
		
		System.out.println(superion.getJugador().getAllPersonajes().size());
		
		Assert.assertThat( superion.getVida(), is( 500 + 350 + 150 ) );
		Assert.assertThat( superion.getPoder(), is( 100 ) );
		Assert.assertThat( superion.getRango(), is( 2 ) );
		Assert.assertThat( superion.getVelocidad(), is( 3 ) );
		Assert.assertThat( superion.getPosicion(), equalTo( optimus.getPosicion()));
	}
	
	@Test
	public void testFusionarTresDecepticonDevuelveMenasorEnLaPosicionDelQueIniciaLaFusion(){
		Jugador jugador = new Jugador("jugador", TipoEquipo.DECEPTICONS );
		Tablero tablero = new Tablero( 20, 20 );
		Decepticon megatron = Decepticon.getMegatron();
		Decepticon bonecrusher = Decepticon.getBonecrusher();
		Decepticon frenzy = Decepticon.getFrenzy();
		megatron.setTablero(tablero);
		bonecrusher.setTablero(tablero);
		frenzy.setTablero(tablero);
		
		
		Coordenada origena = new Coordenada( 0, 0 );
		Coordenada origenb = new Coordenada( 1, 1 );
		Coordenada origenc = new Coordenada( 2, 2 );
				
		tablero.poner( megatron, origena);
		tablero.poner( bonecrusher, origenb);
		tablero.poner( frenzy, origenc);
		jugador.agregarPersonaje(megatron);
		jugador.agregarPersonaje(bonecrusher);
		jugador.agregarPersonaje(frenzy);

		megatron.setEquipo( TipoEquipo.DECEPTICONS);
		bonecrusher.setEquipo( TipoEquipo.DECEPTICONS);
		frenzy.setEquipo( TipoEquipo.DECEPTICONS);

		megatron.iniciarFusion( megatron, bonecrusher, frenzy );
		Algofusion menasor = megatron.completarFusion();
		
		Assert.assertThat( menasor.getVida(), is( 550 + 200 + 400 ) );
		Assert.assertThat( menasor.getPoder(), is( 115 ) );
		Assert.assertThat( menasor.getRango(), is( 2 ) );
		Assert.assertThat( menasor.getVelocidad(), is( 2 ) );
		Assert.assertThat( menasor.getPosicion(), equalTo( megatron.getPosicion()));
	}
	
	@Test
	public void testFusionarTardaDosTurnosEnCompletarseYEliminaLosAlgoformerQueSeFusionan(){
		Jugador jugador = new Jugador("jugador", TipoEquipo.AUTOBOTS );
		Tablero tablero = new Tablero( 20, 20 );
		Autobot optimus = Autobot.getOptimus();
		Autobot bumblebee = Autobot.getBumblebee();
		Autobot ratchet = Autobot.getRatchet();
		optimus.setTablero(tablero);
		bumblebee.setTablero(tablero);
		ratchet.setTablero(tablero);
		
		Coordenada origena = new Coordenada( 0, 0 );
		Coordenada origenb = new Coordenada( 1, 1 );
		Coordenada origenc = new Coordenada( 2, 2 );
				
		tablero.poner( optimus, origena);
		tablero.poner( bumblebee, origenb);
		tablero.poner( ratchet, origenc);
		jugador.agregarPersonaje(optimus);
		jugador.agregarPersonaje(bumblebee);
		jugador.agregarPersonaje(ratchet);


		optimus.iniciarFusion( optimus, bumblebee, ratchet );
		
		Assert.assertThat( optimus.getVelocidad(), is( 0 ) );
		optimus.finTurno();
		optimus.finTurno(); //al final del 2do turno se completa la fusion
		
		Assert.assertThat( tablero.estaOcupado( optimus.getPosicion() ), is( true ) ); //la fusion se pone donde estaba el que la invoco
		Assert.assertThat( tablero.estaOcupado( bumblebee.getPosicion() ), is( false ) );
		Assert.assertThat( tablero.estaOcupado( ratchet.getPosicion() ), is( false ) );
	}
}
