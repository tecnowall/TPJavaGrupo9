package fiuba.algo3.tests;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.modelo.Coordenada;

public class CoordenadaTest {

	@Test
	public void testDosCoordenadasConLasMismasComponentesSonLaMismaCoordenada() {
		Coordenada coordenada1 = new Coordenada( 2, 2 );
		Coordenada coordenada2 = new Coordenada( 2, 2 );
		Coordenada coordenada3 = new Coordenada( 3, 3 );
		
		Assert.assertThat(coordenada1, is(coordenada2 ) );
		Assert.assertThat(coordenada1, is( not( coordenada3 ) ) );
	}

//	@Test
//	public void testProbandoCosasConDistanciaEsteTestNoVa(){
//		Coordenada coordenada1 = new Coordenada( 1, 1 );
//		Coordenada coordenada2 = new Coordenada( 2, 2 );
//		
//		Assert.assertThat( coordenada1.getDistancia( coordenada2 ), is( 1.4 ) );
//	}
}
