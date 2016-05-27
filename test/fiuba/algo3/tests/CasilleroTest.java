package fiuba.algo3.tests;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.modelo.Casillero;
import fiuba.algo3.modelo.Coordenada;
import fiuba.algo3.modelo.Ubicable;
import fiuba.algo3.modelo.Algoformer;

public class CasilleroTest {

	@Test
	public void testSeCreaVacio() {
		Coordenada coordenada = new Coordenada( 1, 1 );
		Casillero casillero = new Casillero( coordenada );
		
		Assert.assertThat(casillero.estaVacio(), is( true ) );
	}
	
	@Test
	public void testAlPonerUnContenidoYaNoEstaVacio(){
		Coordenada coordenada = new Coordenada( 1, 1 );
		Casillero casillero = new Casillero( coordenada );
		Ubicable algoformer = new Algoformer("Optimus");
		
		casillero.poner( algoformer );
		
		Assert.assertThat(casillero.estaVacio(), is( false ) );
	}
	
	@Test
	public void testSacarContenidoVaciaElCasillero(){
		Coordenada coordenada = new Coordenada( 1, 1 );
		Casillero casillero = new Casillero( coordenada );
		Ubicable algoformer = new Algoformer("Optimus");
		
		casillero.poner( algoformer );
		algoformer = casillero.sacar();
		
		Assert.assertThat(casillero.estaVacio(), is( true ) );
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
}
