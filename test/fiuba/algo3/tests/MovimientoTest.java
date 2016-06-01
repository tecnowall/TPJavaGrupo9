package fiuba.algo3.tests;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.modelo.Coordenada;
import fiuba.algo3.modelo.Movimiento;

public class MovimientoTest {

	@Test
	public void testMovimientoDiagonalCuentaComoUno() {
		Movimiento movimiento = new Movimiento( 2 );
		Coordenada origen = new Coordenada ( 1, 1 );
		Coordenada destino = new Coordenada ( 3, 3 );
		Coordenada destinoInvalido = new Coordenada ( 20, 20 );
		
				
		Assert.assertThat( movimiento.movimientoValido( origen, destino ), is( true ) );
		Assert.assertThat( movimiento.movimientoValido( origen, destinoInvalido ), is( false ) );
		
	}

}
