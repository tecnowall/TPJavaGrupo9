package fiuba.algo3.tests;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.modelo.Aerea;
import fiuba.algo3.modelo.Algoformer;
import fiuba.algo3.modelo.Coordenada;
import fiuba.algo3.modelo.Espinas;
import fiuba.algo3.modelo.Forma;
import fiuba.algo3.modelo.Humanoide;
import fiuba.algo3.modelo.NebulosaDeAndromeda;
import fiuba.algo3.modelo.Pantano;
import fiuba.algo3.modelo.Rocoso;
import fiuba.algo3.modelo.Tablero;
import fiuba.algo3.modelo.Terreno;
import fiuba.algo3.modelo.Terrestre;
import fiuba.algo3.modelo.TormentaPsionica;

public class TerrenoTest {

	@Test
	public void testTerrenoRocosoNoAfectaMovimientoHumanoide() {
		Tablero unTablero = new Tablero( 20, 20 );
		Terreno rocoso = new Rocoso();
		Forma forma1 = new Humanoide( 50, 2, 2 );
		Forma forma2 = new Terrestre( 15, 4, 5 );
		Algoformer optimus = new Algoformer( "Optimus", 500, forma1, forma2 );
		Coordenada origen = new Coordenada( 2, 2 );
		Coordenada destino = new Coordenada( 3, 3 );
		
		unTablero.setTerreno( destino, rocoso );
		unTablero.poner( optimus, origen );
						
		optimus.mover( unTablero, destino );
		Assert.assertThat( optimus.getPosicion(), is( destino ) );
	}
	
	@Test
	public void testTerrenoRocosoNoAfectaMovimientoTerrestre() {
		Tablero unTablero = new Tablero( 20, 20 );
		Terreno rocoso = new Rocoso();
		Forma forma1 = new Humanoide( 50, 2, 2 );
		Forma forma2 = new Terrestre( 15, 4, 5 );
		Algoformer optimus = new Algoformer( "Optimus", 500, forma1, forma2 );
		Coordenada origen = new Coordenada( 2, 2 );
		Coordenada destino = new Coordenada( 3, 3 );
		
		unTablero.setTerreno( destino, rocoso );
		unTablero.poner( optimus, origen );
		optimus.transformar();
						
		optimus.mover( unTablero, destino );
		Assert.assertThat( optimus.getPosicion(), is( destino ) );
	}
	
	@Test
	public void testTerrenoRocosoNoAfectaMovimientoAereo() {
		Tablero unTablero = new Tablero( 20, 20 );
		Terreno rocoso = new Rocoso();
		Forma forma1 = new Humanoide( 10, 3, 1 );
		Forma forma2 = new Aerea( 55, 2, 8 );
		Algoformer megatron = new Algoformer( "Megatron", 550, forma1, forma2 );
		Coordenada origen = new Coordenada( 2, 2 );
		Coordenada destino = new Coordenada( 3, 3 );
		
		unTablero.setTerreno( destino, rocoso );
		unTablero.poner( megatron, origen );
		megatron.transformar();
						
		megatron.mover( unTablero, destino );
		Assert.assertThat( megatron.getPosicion(), is( destino ) );
	}

	@Test
	public void testTerrenoEspinasReduceVidaHumanoide() {
		Tablero unTablero = new Tablero( 20, 20 );
		Terreno espinas = new Espinas();
		Forma forma1 = new Humanoide( 50, 2, 2 );
		Forma forma2 = new Terrestre( 15, 4, 5 );
		Algoformer optimus = new Algoformer( "Optimus", 500, forma1, forma2 );
		Coordenada origen = new Coordenada( 1, 1 );
		Coordenada destino = new Coordenada( 2, 2 );
		
		unTablero.setTerreno( destino, espinas );
		unTablero.poner( optimus, origen );
						
		optimus.mover( unTablero, destino );
		Assert.assertThat( optimus.getVida(), is( 475 ) ); //pierde 5%
		

	}
	
	@Test
	public void testTerrenoEspinasReduceVidaTerrestre() {
		Tablero unTablero = new Tablero( 20, 20 );
		Terreno espinas = new Espinas();
		Forma forma1 = new Humanoide( 50, 2, 2 );
		Forma forma2 = new Terrestre( 15, 4, 5 );
		Algoformer optimus = new Algoformer( "Optimus", 500, forma1, forma2 );
		Coordenada origen = new Coordenada( 1, 1 );
		Coordenada enElCamino = new Coordenada( 2, 2 );
		Coordenada destino = new Coordenada( 5, 5 );
		
		unTablero.poner( optimus, origen );
		unTablero.setTerreno( enElCamino, espinas );
		enElCamino.setXY( 4 , 4 );
		unTablero.setTerreno( enElCamino, espinas );	
		
		optimus.transformar();
		optimus.mover( unTablero, destino );
		Assert.assertThat( optimus.getVida(), is( 451 ) ); //pierde 5% de la vida actual dos veces
	}
	
	@Test
	public void testTerrenoEspinasNoAfectaAerea() {
		Tablero unTablero = new Tablero( 20, 20 );
		Terreno espinas = new Espinas();
		Forma forma1 = new Humanoide( 10, 3, 1 );
		Forma forma2 = new Aerea( 55, 2, 8 );
		Algoformer megatron = new Algoformer( "Megatron", 550, forma1, forma2 );
		Coordenada origen = new Coordenada( 2, 2 );
		Coordenada destino = new Coordenada( 3, 3 );
		
		unTablero.setTerreno( destino, espinas );
		unTablero.poner( megatron, origen );
		
		megatron.transformar();
		megatron.mover( unTablero, destino );
		Assert.assertThat( megatron.getVida(), is( 550 ) ); 
	}
		
	@Test
	public void testTerrenoPantanoBloqueaHumanoide() {
		Tablero unTablero = new Tablero( 20, 20 );
		Terreno pantano = new Pantano();
		Forma forma1 = new Humanoide( 50, 20, 20 );
		Forma forma2 = new Terrestre( 15, 4, 5 );
		Algoformer optimus = new Algoformer( "Optimus", 500, forma1, forma2 );
		Coordenada origen = new Coordenada( 1, 1 );
		Coordenada enElCamino = new Coordenada ( 8, 8 );
		Coordenada destinoOriginal = new Coordenada( 15, 15 );
		Coordenada destinoAfectado = new Coordenada( 7, 7 );
		
		unTablero.setTerreno( enElCamino, pantano );
		unTablero.poner( optimus, origen );
		
		optimus.mover( unTablero, destinoOriginal );
		Assert.assertThat( optimus.getPosicion(), is( destinoAfectado ) ); 
	}
	
	@Test
	public void testTerrenoPantanoTerrestreGastaDosMovimientos() {
		Tablero unTablero = new Tablero( 20, 20 );
		Terreno pantano = new Pantano();
		Forma forma1 = new Humanoide( 50, 2, 2 );
		Forma forma2 = new Terrestre( 15, 4, 5 );
		Algoformer optimus = new Algoformer( "Optimus", 500, forma1, forma2 );
		Coordenada origen = new Coordenada( 1, 1 );
		Coordenada enElCamino = new Coordenada ( 2, 2 );
		Coordenada destinoOriginal = new Coordenada( 6, 6 );
		Coordenada destinoAfectado = new Coordenada( 5, 5 );
		
		unTablero.setTerreno( enElCamino, pantano );
		unTablero.poner( optimus, origen );
		optimus.transformar();
		
		optimus.mover( unTablero, destinoOriginal );
		Assert.assertThat( optimus.getPosicion(), is( destinoAfectado ) ); 
	}
	
	@Test
	public void testTerrenoPantanoNoAfectaAerea() {
		Tablero unTablero = new Tablero( 20, 20 );
		Terreno pantano = new Pantano();
		Forma forma1 = new Humanoide( 10, 3, 1 );
		Forma forma2 = new Aerea( 55, 2, 8 );
		Algoformer megatron = new Algoformer( "Megatron", 550, forma1, forma2 );
		Coordenada origen = new Coordenada( 1, 1 );
		Coordenada enElCamino = new Coordenada ( 4, 4 );
		Coordenada destino = new Coordenada( 5, 5 );
		
		unTablero.setTerreno( enElCamino, pantano );
		unTablero.poner( megatron, origen );
		
		megatron.transformar();
		megatron.mover( unTablero, destino );
		Assert.assertThat( megatron.getVida(), is( 550 ) ); 
	}
	@Test
	public void testTormentaPsionicaReducePoderDeAereos() {
		Tablero unTablero = new Tablero( 20, 20 );
		Terreno tormenta = new TormentaPsionica();
		Forma forma1 = new Humanoide( 10, 3, 1 );
		Forma forma2 = new Aerea( 55, 2, 8 );
		Algoformer megatron = new Algoformer( "Megatron", 550, forma1, forma2 );
		Coordenada origen = new Coordenada( 1, 1 );
		Coordenada enElCamino = new Coordenada( 2, 2 );
		Coordenada destino = new Coordenada( 3, 3 );
		
		unTablero.setTerreno( destino, tormenta );
		unTablero.setTerreno( enElCamino, tormenta );
		unTablero.poner( megatron, origen );
		megatron.transformar();	
		megatron.mover( unTablero, destino );
		Assert.assertThat( megatron.getPosicion(), is( destino ) );
		Assert.assertThat( megatron.getPoder(), is(33) ); //reducido permanentemente en un 40% habiendo pasado por 2 tormentas
		Assert.assertThat( megatron.getPoderBase(), is(55) ); //poder base no modificado
	}
	@Test
	public void testTormentaPsionicaNoAfectaTerrestres() {
		Tablero unTablero = new Tablero( 20, 20 );
		Terreno tormenta = new TormentaPsionica();
		Forma forma1 = new Humanoide( 10, 3, 1 );
		Forma forma2 = new Terrestre( 15, 4, 5 );
		Algoformer megatron = new Algoformer( "Megatron", 550, forma1, forma2 );
		Coordenada origen = new Coordenada( 2, 2 );
		Coordenada destino = new Coordenada( 3, 3 );
		
		unTablero.setTerreno( destino, tormenta );
		unTablero.poner( megatron, origen );
		megatron.transformar();	
		megatron.mover( unTablero, destino );
		Assert.assertThat( megatron.getPosicion(), is( destino ) );
		Assert.assertThat( megatron.getPoder(), is(15) );
	}
	@Test
	public void testNebulosaDeAndromedaDetienteAereos(){
		Tablero unTablero = new Tablero( 20, 20 );
		Terreno nebulosa = new NebulosaDeAndromeda();
		Forma forma1 = new Humanoide( 10, 3, 1 );
		Forma forma2 = new Aerea( 55, 2, 8 );
		Algoformer megatron = new Algoformer( "Megatron", 550, forma1, forma2 );
		Coordenada origen = new Coordenada( 1, 1 );
		Coordenada enElCamino = new Coordenada ( 4, 4 );
		Coordenada destinoOriginal = new Coordenada( 5, 5 );
		Coordenada destinoAfectado = new Coordenada( 4, 4 );
		
		unTablero.setTerreno( enElCamino, nebulosa );
		unTablero.poner( megatron, origen );
		megatron.transformar();
		megatron.mover( unTablero, 	destinoOriginal );
		Assert.assertThat( megatron.getPosicion(), is( destinoAfectado ) ); 
		Assert.assertThat(megatron.getVelocidad(), is(0));
		Assert.assertThat(megatron.getVelocidadBase(), is(8));
	}
}
