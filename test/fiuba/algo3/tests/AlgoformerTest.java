package fiuba.algo3.tests;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.modelo.Algoformer;
import fiuba.algo3.modelo.AtaqueFueraDeRangoException;
import fiuba.algo3.modelo.Coordenada;
import fiuba.algo3.modelo.Forma;
import fiuba.algo3.modelo.FuegoAmigoException;
import fiuba.algo3.modelo.Humanoide;
import fiuba.algo3.modelo.MovimientoFueraDeRangoException;
import fiuba.algo3.modelo.Tablero;
import fiuba.algo3.modelo.Terrestre;

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
		Forma forma1 = new Humanoide( 50, 2, 2 );
		Forma forma2 = new Terrestre( 15, 4, 5 );
		Algoformer optimus = new Algoformer( "Optimus", 500, forma1, forma2 );
		Coordenada origen = new Coordenada( 2, 2 );
		Coordenada destino = new Coordenada( 4, 4 );
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
		optimus.setEquipo(1);
		Forma forma1b = new Humanoide( 10, 3, 1 );
		Forma forma2b = new Terrestre( 55, 2, 8 );
		Algoformer megatron = new Algoformer( "Megatron", 550, forma1b, forma2b);
		megatron.setEquipo(2);
		
		Coordenada origena = new Coordenada( 2, 2 );
		Coordenada origenb = new Coordenada( 3, 3 );
		unTablero.poner( optimus, origena );
		unTablero.poner( megatron, origenb );
		optimus.atacar(unTablero, origenb);
		Assert.assertTrue(megatron.getVida()==500);
	}

	@Test(expected = AtaqueFueraDeRangoException.class )
	public void testAtacarAUnAlgoFormerFueraDeRango() throws AtaqueFueraDeRangoException, FuegoAmigoException{
		Tablero unTablero = new Tablero( 20, 20 );
		Forma forma1a = new Humanoide( 50, 2, 2 );
		Forma forma2a = new Terrestre( 15, 4, 5 );
		Algoformer optimus = new Algoformer( "Optimus", 500, forma1a, forma2a);
		optimus.setEquipo(1);
		Forma forma1b = new Humanoide( 10, 3, 1 );
		Forma forma2b = new Terrestre( 55, 2, 8 );
		Algoformer megatron = new Algoformer( "Megatron", 550, forma1b, forma2b);
		megatron.setEquipo(2);
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
		optimus.setEquipo(1);
		Forma forma1b = new Humanoide( 10, 3, 1 );
		Forma forma2b = new Terrestre( 55, 2, 8 );
		Algoformer megatron = new Algoformer( "Megatron", 550, forma1b, forma2b);
		megatron.setEquipo(1);
		
		Coordenada origena = new Coordenada( 2, 2 );
		Coordenada origenb = new Coordenada( 3, 3 );
		unTablero.poner( optimus, origena );
		unTablero.poner( megatron, origenb );
		optimus.atacar(unTablero, origenb);

}
}
