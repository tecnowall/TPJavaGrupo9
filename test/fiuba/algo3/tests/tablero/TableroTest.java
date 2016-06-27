package fiuba.algo3.tests.tablero;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.modelo.Coordenada;
import fiuba.algo3.modelo.algoformer.Algoformer;
import fiuba.algo3.modelo.tablero.FueraDelTableroException;
import fiuba.algo3.modelo.tablero.Tablero;
import fiuba.algo3.modelo.tablero.Ubicable;

public class TableroTest {

	@Test
	public void testNoSePoneNadaEnUnaCoordenadaEntoncesNoTieneContenido() {
		Tablero tablero = new Tablero( 10, 10 );
		Coordenada coordenada = new Coordenada( 2, 2 );

		
		Assert.assertThat( tablero.estaOcupado( coordenada ), is( false ) );
	}

	@Test
	public void testLuegoDePonerAlgoEsaCoordenadaEstaOcupada() {
		Tablero tablero = new Tablero( 10, 10 );
		Ubicable algoformer = new Algoformer("optimus");
		Coordenada coordenada = new Coordenada( 2, 2 );
		
		tablero.poner( algoformer, coordenada );
		
		Assert.assertThat( tablero.estaOcupado( coordenada ), is( true ) );
	}
	
	@Test
	public void testLuegoDeSacarEsaCoordenadaQuedaDesocupada(){
		Tablero tablero = new Tablero( 10, 10 );
		Ubicable algoformer = new Algoformer("optimus");
		Coordenada coordenada = new Coordenada( 2, 2 );
		
		tablero.poner( algoformer, coordenada );
		tablero.sacar(coordenada);
		
		Assert.assertThat( tablero.estaOcupado( coordenada ), is( false ) );
	}
	
	@Test
	public void testMoverDesocupaOrigenYOcupaDestino(){
		Tablero tablero = new Tablero( 10, 10 );
		Ubicable algoformer = new Algoformer("optimus");		
		Coordenada origen = new Coordenada( 2, 2 );
		Coordenada destino = new Coordenada( 5, 5 );
		
		tablero.poner( algoformer, origen );
		tablero.mover( origen, destino );
		
		Assert.assertThat( tablero.estaOcupado( origen ), is( false ) );
		Assert.assertThat( tablero.estaOcupado( destino ), is( true ) );
	}
	
	@Test( expected = FueraDelTableroException.class )
	public void testPonerFueraDelTableroLanzaExcepcion(){
		Tablero tablero = new Tablero( 10, 10 );
		Ubicable algoformer = new Algoformer("optimus");		
		Coordenada origen = new Coordenada( 2, 20 );
		
		tablero.poner( algoformer, origen );
	}
	
	@Test( expected = FueraDelTableroException.class )
	public void testSacarDesdeFueraDelTableroLanzaExcepcion(){
		Tablero tablero = new Tablero( 10, 10 );	
		Coordenada coordenada = new Coordenada( 2, 20 );
		
		tablero.sacar( coordenada );
	}
	
	@Test( expected = FueraDelTableroException.class )
	public void testMoverDesdeFueraDelTableroLanzaExcepcion(){
		Tablero tablero = new Tablero( 10, 10 );
		Ubicable algoformer = new Algoformer("optimus");		
		Coordenada origen = new Coordenada( 2, 2 );
		Coordenada destino = new Coordenada( 5, 5 );
		Coordenada fueraDelLimite = new Coordenada( 15, 15 );
		
		tablero.poner( algoformer, origen );
		tablero.mover( fueraDelLimite, destino );		
	}
	
	@Test( expected = FueraDelTableroException.class )
	public void testMoverHastaFueraDelTableroLanzaExcepcion(){
		Tablero tablero = new Tablero( 10, 10 );
		Ubicable algoformer = new Algoformer("optimus");		
		Coordenada origen = new Coordenada( 2, 2 );
		Coordenada fueraDelLimite = new Coordenada( 15, 15 );
		tablero.poner( algoformer, origen );
		tablero.mover( origen, fueraDelLimite );		
	}
}
