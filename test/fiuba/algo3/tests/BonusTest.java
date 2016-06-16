package fiuba.algo3.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.modelo.algoformer.Algoformer;
import fiuba.algo3.modelo.algoformer.Forma;
import fiuba.algo3.modelo.algoformer.Humanoide;
import fiuba.algo3.modelo.algoformer.Terrestre;
import fiuba.algo3.modelo.bonus.*;
import fiuba.algo3.modelo.bonus.BonusBurbuja;
import fiuba.algo3.modelo.bonus.BonusDobleCanion;
import fiuba.algo3.modelo.bonus.BonusFlash;
import fiuba.algo3.modelo.bonus.BonusFusion;
import fiuba.algo3.modelo.bonus.BonusNebulosaDeAndromeda;
import fiuba.algo3.modelo.bonus.BonusTormentaPsionica;

public class BonusTest {

	@Test
	public void testConsumirDuracionDisminuyeDuracionEnUno() {
		Bonus bonus = new BonusBurbuja();

		bonus.consumirDuracion();
			
		Assert.assertThat( bonus.getDuracion(), is( 1 ) );
	}
	
	@Test
	public void testConsumirDuracionDesafectaAlgoformerSiSeTerminaLaDuracion() {
		Forma forma1 = new Humanoide( 50, 2, 2 );
		Forma forma2 = new Terrestre( 15, 4, 5 );
		Algoformer optimus = new Algoformer( "Optimus", 500, forma1, forma2);
		Bonus bonus = new BonusDobleCanion();
		
		optimus.nuevoBonus( bonus );	
		Assert.assertThat( optimus.getPoder(), is( 100 ) );
		Assert.assertThat( optimus.cantidadBonus(), is( 1 ) );
		
		bonus.consumirDuracion();
		bonus.consumirDuracion();
		bonus.consumirDuracion();//duracion base 3 -> consumo 3 veces
		
		Assert.assertThat( optimus.getPoder(), is( 50 ) );
		Assert.assertThat( optimus.cantidadBonus(), is( 0 ) );
	}
	
	@Test
	public void testBonusAtaqueDobleDuplicaElPoder() {
		Forma forma1 = new Humanoide( 50, 2, 2 );
		Forma forma2 = new Terrestre( 15, 4, 5 );
		Algoformer optimus = new Algoformer( "Optimus", 500, forma1, forma2);
		Bonus bonus = new BonusDobleCanion();
		
		optimus.nuevoBonus( bonus );
		
		Assert.assertThat( optimus.getPoder(), is( 100 ) );
	}
	
	@Test
	public void testBonusTormentaPsionicaReducePoderEn40PorCiento() {
		Forma forma1 = new Humanoide( 50, 2, 2 );
		Forma forma2 = new Terrestre( 15, 4, 5 );
		Algoformer optimus = new Algoformer( "Optimus", 500, forma1, forma2);
		Bonus bonus = new BonusTormentaPsionica();
		
		optimus.nuevoBonus( bonus );
		
		Assert.assertThat( optimus.getPoder(), is( 30 ) );
	}
	
	@Test
	public void testBonusNebulosaReduceVelocidadACero() {
		Forma forma1 = new Humanoide( 50, 2, 2 );
		Forma forma2 = new Terrestre( 15, 4, 5 );
		Algoformer optimus = new Algoformer( "Optimus", 500, forma1, forma2);
		Bonus bonus = new BonusNebulosaDeAndromeda();
		
		optimus.nuevoBonus( bonus );
		
		Assert.assertThat( optimus.getVelocidad(), is( 0 ) );
	}
	
	@Test
	public void testBonusFlashTriplicaVelocidad() {
		Forma forma1 = new Humanoide( 50, 2, 2 );
		Forma forma2 = new Terrestre( 15, 4, 5 );
		Algoformer optimus = new Algoformer( "Optimus", 500, forma1, forma2);
		Bonus bonus = new BonusFlash();
		
		optimus.nuevoBonus( bonus );
		
		Assert.assertThat( optimus.getVelocidad(), is( 6 ) );
	}
	
	@Test
	public void testBonusBurbujaInmunizaAlDanio() {
		Forma forma1 = new Humanoide( 50, 2, 2 );
		Forma forma2 = new Terrestre( 15, 4, 5 );
		Algoformer optimus = new Algoformer( "Optimus", 500, forma1, forma2);
		Bonus bonus = new BonusBurbuja();
		
		optimus.nuevoBonus( bonus );
		optimus.recibirDanio( 500 );
		
		Assert.assertThat( optimus.getVida(), is( 500 ) );
	}
	
	@Test
	public void testBonusFusionReduceVelocidadACero() {
		Forma forma1 = new Humanoide( 50, 2, 2 );
		Forma forma2 = new Terrestre( 15, 4, 5 );
		Algoformer optimus = new Algoformer( "Optimus", 500, forma1, forma2);
		Bonus bonus = new BonusFusion();
	
		optimus.nuevoBonus( bonus );

		Assert.assertThat( optimus.getVelocidad(), is( 0 ) );
	}
}
