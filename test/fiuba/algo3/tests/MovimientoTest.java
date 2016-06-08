package fiuba.algo3.tests;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.modelo.Algoformer;
import fiuba.algo3.modelo.Coordenada;
import fiuba.algo3.modelo.Forma;
import fiuba.algo3.modelo.Humanoide;
import fiuba.algo3.modelo.Movimiento;
import fiuba.algo3.modelo.Tablero;
import fiuba.algo3.modelo.Terrestre;

public class MovimientoTest {

	@Test
	public void testAvanzarDisminuyeCaminoRestante() {
		Tablero tablero = new Tablero( 10, 10 );
		Coordenada origen = new Coordenada ( 1, 1 );
		Coordenada destino = new Coordenada ( 5, 5 );
		Movimiento movimiento = new Movimiento( tablero );
		Coordenada destinoInvalido = new Coordenada ( 20, 20 );
		Forma forma1 = new Humanoide( 50, 2, 2 );
		Forma forma2 = new Terrestre( 15, 4, 5 );
		Algoformer optimus = new Algoformer( "Optimus", 500, forma1, forma2 );
		tablero.poner( optimus, origen);
		
		movimiento.generarCamino( origen, destino );
		Assert.assertThat( movimiento.caminoRestante(), is( 4 ) );

		movimiento.avanzar( optimus );
		Assert.assertThat( movimiento.caminoRestante(), is( 3 ) );	
	}

	@Test
	public void testRetrocederVuelveALaPosicionAnterior() {
		Tablero tablero = new Tablero( 10, 10 );
		Coordenada origen = new Coordenada ( 1, 1 );
		Coordenada destino = new Coordenada ( 5, 5 );
		Movimiento movimiento = new Movimiento( tablero );
		Coordenada destinoRetrocedido = new Coordenada ( 2, 2 );
		Forma forma1 = new Humanoide( 50, 2, 2 );
		Forma forma2 = new Terrestre( 15, 4, 5 );
		Algoformer optimus = new Algoformer( "Optimus", 500, forma1, forma2 );
		tablero.poner( optimus, origen);
		
		movimiento.generarCamino( origen, destino );
		movimiento.avanzar( optimus ); //2 2
		movimiento.avanzar( optimus ); //3 3
		movimiento.avanzar( optimus ); //4 4
		movimiento.retroceder( optimus );
		movimiento.retroceder( optimus );
		
		Assert.assertThat( optimus.getPosicion(), is ( destinoRetrocedido ) );
	}
}
