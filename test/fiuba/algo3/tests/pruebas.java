package fiuba.algo3.tests;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.modelo.Coordenada;
import fiuba.algo3.modelo.TipoEquipo;
import fiuba.algo3.modelo.algoformer.Aerea;
import fiuba.algo3.modelo.algoformer.Algoformer;
import fiuba.algo3.modelo.algoformer.Algofusion;
import fiuba.algo3.modelo.algoformer.Autobot;
import fiuba.algo3.modelo.algoformer.Decepticon;
import fiuba.algo3.modelo.algoformer.Forma;
import fiuba.algo3.modelo.algoformer.Humanoide;
import fiuba.algo3.modelo.algoformer.Terrestre;
import fiuba.algo3.modelo.bonus.Bonus;
import fiuba.algo3.modelo.bonus.BonusDobleCanion;
import fiuba.algo3.modelo.bonus.BonusFusion;
import fiuba.algo3.modelo.bonus.BonusNebulosaDeAndromeda;
import fiuba.algo3.modelo.bonus.BonusTormentaPsionica;
import fiuba.algo3.modelo.bonus.TipoEfecto;
import fiuba.algo3.modelo.tablero.Capturable;
import fiuba.algo3.modelo.tablero.Tablero;
import fiuba.algo3.modelo.terreno.NebulosaDeAndromeda;
import fiuba.algo3.modelo.terreno.Terreno;
import fiuba.algo3.modelo.terreno.TormentaPsionica;

public class pruebas {

	
	
	
//	@Test
//	public void testassasa(){
//		Forma forma1 = new Humanoide( 50, 2, 2 );
//		Forma forma2 = new Terrestre( 15, 4, 5 );
//		Autobot optimus = new Autobot( "Optimus", 500, forma1, forma1 );
//		Forma forma3 = new Humanoide( 40, 1, 2 );
//		Forma forma4 = new Terrestre( 20, 3, 5 );
//		Autobot bumblebee = new Autobot( "Bumblebee", 350, forma3, forma4 );
//		Forma forma5 = new Humanoide( 5, 5, 1 );
//		Forma forma6 = new Aerea( 35, 2, 8 );
//		Autobot ratchet = new Autobot( "Ratchet", 150, forma5, forma6 );
//		
//		Algofusion superion = optimus.iniciarFusion( optimus, bumblebee, ratchet );
//		optimus.inicioTurno();
//		optimus.inicioTurno();
//	}
	
//	@Test
//	public void testBonusFusionReduceVelocidadACero() {
//		Forma forma1 = new Humanoide( 50, 2, 2 );
//		Forma forma2 = new Terrestre( 15, 4, 5 );
//		Autobot optimus = new Autobot( "Optimus", 500, forma1, forma2);
//		Forma forma3 = new Humanoide( 50, 2, 2 );
//		Forma forma4 = new Terrestre( 15, 4, 5 );
//		Decepticon megatron = new Decepticon( "Megatron", 500, forma1, forma2);
//		Bonus bonus = new BonusFusion();
//		Bonus bonus2 = new BonusFusion();
//		List<TipoEfecto> tipos = new ArrayList<TipoEfecto>();
//		tipos.add( TipoEfecto.VELOCIDAD);
//		tipos.add(TipoEfecto.PODER );
//		
//		optimus.nuevoBonus( bonus );
//		megatron.nuevoBonus( bonus2 );
//		bonus.consumirDuracion();
//		bonus.consumirDuracion();
//		bonus2.consumirDuracion();
//		bonus2.consumirDuracion();
//		bonus.aplicarEfectos(optimus, tipos);
//		Assert.assertThat( optimus.getVelocidad(), is( 0 ) );
//	}
	
	
//	@Test
//	public void testPasarPorUnBonusLoCaptura(){
//		Tablero unTablero = new Tablero( 20, 20 );
//		Forma forma1a = new Aerea( 100, 20, 20 );
//		Forma forma2a = new Aerea( 15, 4, 5 );
//		Algoformer optimus = new Algoformer( "Optimus", 500, forma1a, forma2a);
//		Capturable bonus = new BonusAtaqueDoble();
//		Capturable bonus2 = new BonusTormentaPsionica();
//		Terreno tormenta = new TormentaPsionica();
//		Coordenada origen = new Coordenada( 0, 0 );
//		Coordenada enElCamino = new Coordenada( 1, 1 );
//		Coordenada enElCamino2 = new Coordenada( 4, 4 );
//		Coordenada destino = new Coordenada( 4, 4 );
//		
//		
//		unTablero.poner( bonus, enElCamino2 );
//		unTablero.setTerreno( enElCamino, tormenta );		
//		
//		unTablero.poner( optimus, origen );
//
//		optimus.mover( unTablero, destino );
//		System.out.println( optimus.buffs.size() );
//		Assert.assertThat( optimus.getPoder(), is( 120 ) ); //100*0.6*2 = 120
//	}
	
	
//	@Test
//	public void testAtacarAUnAlgoFormerReduceSuVidassss(){
//		Tablero unTablero = new Tablero( 20, 20 );
//		Forma forma1a = new Humanoide( 50, 200, 200 );
//		Forma forma2a = new Terrestre( 15, 4, 5 );
//		Algoformer optimus = new Algoformer( "Optimus", 500, forma1a, forma2a);
//		optimus.setEquipo(TipoEquipo.AUTOBOTS);
//		Forma forma1b = new Humanoide( 10, 3, 1 );
//		Forma forma2b = new Terrestre( 55, 2, 8 );
//		Algoformer megatron = new Algoformer( "Megatron", 550, forma1b, forma2b);
//		megatron.setEquipo(TipoEquipo.DECEPTICONS);
//		
//		Coordenada origena = new Coordenada( 0, 0 );
//		Coordenada origenb = new Coordenada( 6, 6 );
//		Coordenada destino = new Coordenada( 13, 12);
//		
//		unTablero.poner( optimus, origena );
//		unTablero.poner( megatron, origenb );
//		
//		optimus.mover(unTablero, destino);
//	}
//	
//	@Test
//	public void testMoverUnAlgoformerDentroDeSuAlcanceCambiaSuPosicion() {
//		Tablero unTablero = new Tablero( 20, 20 );
//		Forma forma1 = new Humanoide( 50, 20, 20 );
//		Forma forma2 = new Terrestre( 15, 40, 50 );
//		Algoformer optimus = new Algoformer( "Optimus", 500, forma1, forma2 );
//		Coordenada origen = new Coordenada( 10, 10 );
//		Coordenada destino = new Coordenada( 5, 7 );
//		Coordenada destinoLejano = new Coordenada( 9, 9 ); //alcanzable por el modo alterno pero no por el humanoide
//		
//		unTablero.poner( optimus, origen );
//		Assert.assertThat( optimus.getPosicion(), is( origen ) );
//		
//		optimus.mover( unTablero, destino );
//		Assert.assertThat( optimus.getPosicion(), is( destino ) );
//		
//		optimus.transformar();
//		optimus.mover( unTablero, destinoLejano );
//		Assert.assertThat( optimus.getPosicion(), is( destinoLejano ) );
//	}
}
