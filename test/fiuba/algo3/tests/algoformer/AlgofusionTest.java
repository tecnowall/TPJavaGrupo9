package fiuba.algo3.tests.algoformer;

import fiuba.algo3.modelo.Jugabilidad.Juego.Partida;
import fiuba.algo3.modelo.Jugabilidad.Jugador.Jugador;
import fiuba.algo3.modelo.TipoEquipo;
import org.junit.Assert;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

import fiuba.algo3.modelo.Coordenada;
import fiuba.algo3.modelo.algoformer.Aerea;
import fiuba.algo3.modelo.algoformer.Algoformer;
import fiuba.algo3.modelo.algoformer.Algofusion;
import fiuba.algo3.modelo.algoformer.Autobot;
import fiuba.algo3.modelo.algoformer.Decepticon;
import fiuba.algo3.modelo.algoformer.Forma;
import fiuba.algo3.modelo.algoformer.Humanoide;
import fiuba.algo3.modelo.algoformer.Terrestre;
import fiuba.algo3.modelo.tablero.Tablero;

public class AlgofusionTest {

	@Test
	public void testFusionarTresAutobotsDevuelveSuperionEnLaPosicionDelQueIniciaLaFusion(){
		Tablero tablero = new Tablero( 20, 20 );
		Forma forma1 = new Humanoide( 50, 2, 2 );
		Forma forma2 = new Terrestre( 15, 4, 5 );
		Autobot optimus = new Autobot( "Optimus", 500, forma1, forma1, tablero );
		Forma forma3 = new Humanoide( 40, 1, 2 );
		Forma forma4 = new Terrestre( 20, 3, 5 );
		Autobot bumblebee = new Autobot( "Bumblebee", 350, forma3, forma4, tablero );
		Forma forma5 = new Humanoide( 5, 5, 1 );
		Forma forma6 = new Aerea( 35, 2, 8 );
		Autobot ratchet = new Autobot( "Ratchet", 150, forma5, forma6, tablero );
		Coordenada origena = new Coordenada( 0, 0 );
		Coordenada origenb = new Coordenada( 1, 1 );
		Coordenada origenc = new Coordenada( 2, 2 );
				
		tablero.poner( optimus, origena);
		tablero.poner( bumblebee, origenb);
		tablero.poner( ratchet, origenc);

		Jugador j1= new Jugador("Maradona", TipoEquipo.AUTOBOTS);

		optimus.setEquipo(TipoEquipo.AUTOBOTS);
		bumblebee.setEquipo(TipoEquipo.AUTOBOTS);
		ratchet.setEquipo(TipoEquipo.AUTOBOTS);

		j1.agregarPersonaje(optimus);
		j1.agregarPersonaje(bumblebee);
		j1.agregarPersonaje(ratchet);

		Partida partida = new Partida();
		j1.setPartida(partida);



		optimus.iniciarFusion( optimus, bumblebee, ratchet );
		Algofusion superion = optimus.completarFusion();
		
		Assert.assertThat( superion.getVida(), is( 500 + 350 + 150 ) );
		Assert.assertThat( superion.getPoder(), is( 100 ) );
		Assert.assertThat( superion.getRango(), is( 2 ) );
		Assert.assertThat( superion.getVelocidad(), is( 3 ) );
		Assert.assertThat( superion.getPosicion(), equalTo( optimus.getPosicion()));
	}
	
	@Test
	public void testFusionarTresDecepticonDevuelveMenasorEnLaPosicionDelQueIniciaLaFusion(){
		Tablero tablero = new Tablero( 20, 20 );
		Forma forma1 = new Humanoide( 10, 3, 1 );
		Forma forma2 = new Aerea( 55, 2, 8 );
		Decepticon megatron = new Decepticon( "Megatron", 550, forma1, forma1, tablero );
		Forma forma3 = new Humanoide( 30, 3, 1 );
		Forma forma4 = new Terrestre( 30, 3, 8 );
		Decepticon bonecrusher = new Decepticon( "Bonecrusher", 200, forma3, forma4, tablero );
		Forma forma5 = new Humanoide( 10, 5, 2 );
		Forma forma6 = new Terrestre( 25, 2, 6 );
		Decepticon frenzy = new Decepticon( "Frenzy", 400, forma5, forma6, tablero );
		
		Coordenada origena = new Coordenada( 0, 0 );
		Coordenada origenb = new Coordenada( 1, 1 );
		Coordenada origenc = new Coordenada( 2, 2 );
				
		tablero.poner( megatron, origena);
		tablero.poner( bonecrusher, origenb);
		tablero.poner( frenzy, origenc);

		Jugador j1= new Jugador("Maradona", TipoEquipo.DECEPTICONS);

		megatron.setEquipo( TipoEquipo.DECEPTICONS);
		bonecrusher.setEquipo( TipoEquipo.DECEPTICONS);
		frenzy.setEquipo( TipoEquipo.DECEPTICONS);

		j1.agregarPersonaje(megatron);
		j1.agregarPersonaje(bonecrusher);
		j1.agregarPersonaje(frenzy);
		Partida partida = new Partida();
		j1.setPartida(partida);


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
		Tablero tablero = new Tablero( 20, 20 );
		Forma forma1 = new Humanoide( 50, 2, 2 );
		Forma forma2 = new Terrestre( 15, 4, 5 );
		Autobot optimus = new Autobot( "Optimus", 500, forma1, forma1, tablero );
		Forma forma3 = new Humanoide( 40, 1, 2 );
		Forma forma4 = new Terrestre( 20, 3, 5 );
		Autobot bumblebee = new Autobot( "Bumblebee", 350, forma3, forma4, tablero );
		Forma forma5 = new Humanoide( 5, 5, 1 );
		Forma forma6 = new Aerea( 35, 2, 8 );
		Autobot ratchet = new Autobot( "Ratchet", 150, forma5, forma6, tablero );
		Coordenada origena = new Coordenada( 0, 0 );
		Coordenada origenb = new Coordenada( 1, 1 );
		Coordenada origenc = new Coordenada( 2, 2 );
				
		tablero.poner( optimus, origena);
		tablero.poner( bumblebee, origenb);
		tablero.poner( ratchet, origenc);


		Jugador j1= new Jugador("Maradona", TipoEquipo.AUTOBOTS);

		optimus.setEquipo(TipoEquipo.AUTOBOTS);
		bumblebee.setEquipo(TipoEquipo.AUTOBOTS);
		ratchet.setEquipo(TipoEquipo.AUTOBOTS);

		j1.agregarPersonaje(optimus);
		j1.agregarPersonaje(bumblebee);
		j1.agregarPersonaje(ratchet);

		Partida partida = new Partida();
		j1.setPartida(partida);


		optimus.iniciarFusion( optimus, bumblebee, ratchet );
		
		Assert.assertThat( optimus.getVelocidad(), is( 0 ) );
		optimus.finTurno();
		optimus.finTurno(); //al final del 2do turno se completa la fusion
		
		Assert.assertThat( tablero.estaOcupado( optimus.getPosicion() ), is( true ) ); //la fusion se pone donde estaba el que la invoco
		Assert.assertThat( tablero.estaOcupado( bumblebee.getPosicion() ), is( false ) );
		Assert.assertThat( tablero.estaOcupado( ratchet.getPosicion() ), is( false ) );
	}
}
