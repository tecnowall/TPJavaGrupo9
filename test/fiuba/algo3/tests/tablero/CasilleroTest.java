package fiuba.algo3.tests.tablero;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.modelo.Coordenada;
import fiuba.algo3.modelo.algoformer.Algoformer;
import fiuba.algo3.modelo.bonus.BonusNebulosaDeAndromeda;
import fiuba.algo3.modelo.tablero.Capturable;
import fiuba.algo3.modelo.tablero.Casillero;
import fiuba.algo3.modelo.tablero.CasilleroOcupadoException;
import fiuba.algo3.modelo.tablero.CasilleroVacioException;
import fiuba.algo3.modelo.tablero.Ubicable;

public class CasilleroTest {

	@Test
	public void testSeCreaVacio() {
		Coordenada coordenada = new Coordenada( 1, 1 );
		Casillero casillero = new Casillero( coordenada );
		
		Assert.assertThat(casillero.estaOcupado(), is( false ) );
	}
	
	@Test
	public void testAlPonerUnUbicableQuedaOcupado(){
		Coordenada coordenada = new Coordenada( 1, 1 );
		Casillero casillero = new Casillero( coordenada );
		Ubicable algoformer = new Algoformer("Optimus");
		
		casillero.poner( algoformer );
		
		Assert.assertThat(casillero.estaOcupado(), is( true ) );
	}
	
	@Test
	public void testAlPonerUnCapturableNoQuedaOcupado(){
		Coordenada coordenada = new Coordenada( 1, 1 );
		Casillero casillero = new Casillero( coordenada );
		Capturable bonus = new BonusNebulosaDeAndromeda();
		
		casillero.poner( bonus );
		
		Assert.assertThat(casillero.estaOcupado(), is( false ) );
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
