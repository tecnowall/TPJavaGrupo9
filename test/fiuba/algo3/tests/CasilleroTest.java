package fiuba.algo3.tests;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.modelo.Algoformer;
import fiuba.algo3.modelo.Casillero;
import fiuba.algo3.modelo.CasilleroOcupadoException;
import fiuba.algo3.modelo.CasilleroVacioException;
import fiuba.algo3.modelo.Coordenada;
import fiuba.algo3.modelo.Ubicable;

public class CasilleroTest {

	@Test
	public void testSeCreaVacio() {
		Coordenada coordenada = new Coordenada( 1, 1 );
		Casillero casillero = new Casillero( coordenada );
		
		Assert.assertThat(casillero.estaOcupado(), is( false ) );
	}
	
	@Test
	public void testAlPonerUnContenidoYaNoEstaVacio(){
		Coordenada coordenada = new Coordenada( 1, 1 );
		Casillero casillero = new Casillero( coordenada );
		Ubicable algoformer = new Algoformer("Optimus");
		
		casillero.poner( algoformer );
		
		Assert.assertThat(casillero.estaOcupado(), is( true ) );
	}
	
	@Test
	public void testSacarContenidoVaciaElCasillero(){
		Coordenada coordenada = new Coordenada( 1, 1 );
		Casillero casillero = new Casillero( coordenada );
		Ubicable algoformer = new Algoformer("Optimus");
		
		casillero.poner( algoformer );
		algoformer = casillero.sacar();
		
		Assert.assertThat(casillero.estaOcupado(), is( false ) );
	}

	@Test
	public void testSacarContenidoDevuelveElContenido(){
		Coordenada coordenada = new Coordenada( 1, 1 );
		Casillero casillero = new Casillero( coordenada );
		Algoformer algoformer1 = new Algoformer("Optimus");
		Algoformer algoformer2 = new Algoformer("Megatron");
		
		casillero.poner( algoformer1 );
		algoformer2 = (Algoformer)casillero.sacar();
		
		Assert.assertThat( algoformer2.getNombre(), is( "Optimus" ) );
	}
	
	@Test( expected = CasilleroOcupadoException.class )
	public void testPonerEnUnCasilleroOcupadoLanzaExcepcion(){
		Coordenada coordenada = new Coordenada( 1, 1 );
		Casillero casillero = new Casillero( coordenada );
		Algoformer algoformer = new Algoformer("Optimus");
		
		casillero.poner( algoformer );
		casillero.poner( algoformer );
	}
	
	@Test( expected = CasilleroVacioException.class )
	public void testSacarDeUnCasilleroVacioLanzaExcepcion(){
		Coordenada coordenada = new Coordenada( 1, 1 );
		Casillero casillero = new Casillero( coordenada );
		
		casillero.sacar();	
	}
}
