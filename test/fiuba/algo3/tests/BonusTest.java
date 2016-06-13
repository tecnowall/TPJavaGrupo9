package fiuba.algo3.tests;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.modelo.algoformer.Algoformer;
import fiuba.algo3.modelo.algoformer.Forma;
import fiuba.algo3.modelo.algoformer.Humanoide;
import fiuba.algo3.modelo.algoformer.Terrestre;
import fiuba.algo3.modelo.bonus.Bonus;
import fiuba.algo3.modelo.bonus.BonusBurbuja;
import fiuba.algo3.modelo.bonus.BonusDobleCanion;
import fiuba.algo3.modelo.bonus.BonusFlash;
import fiuba.algo3.modelo.bonus.BonusNebulosaDeAndromeda;
import fiuba.algo3.modelo.bonus.BonusTormentaPsionica;

public class BonusTest {

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
	public void testBonusFlashTriplicaVelocidadssss() {
		Forma forma1 = new Humanoide( 50, 2, 2 );
		Forma forma2 = new Terrestre( 15, 4, 5 );
		Algoformer optimus = new Algoformer( "Optimus", 500, forma1, forma2);
		Bonus bonus = new BonusBurbuja();
		
		optimus.nuevoBonus( bonus );
		
//		Assert.assertThat( optimus.getArmadura(), is( 100.0 ) );
		
		optimus.recibirDanio( 500 );
		
		Assert.assertThat( optimus.getVida(), is( 500 ) );
	}
}
