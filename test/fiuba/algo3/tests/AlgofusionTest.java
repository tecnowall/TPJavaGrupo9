package fiuba.algo3.tests;

import org.junit.Assert;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;

import fiuba.algo3.modelo.algoformer.Aerea;
import fiuba.algo3.modelo.algoformer.Algoformer;
import fiuba.algo3.modelo.algoformer.Algofusion;
import fiuba.algo3.modelo.algoformer.Autobot;
import fiuba.algo3.modelo.algoformer.Decepticon;
import fiuba.algo3.modelo.algoformer.Forma;
import fiuba.algo3.modelo.algoformer.Humanoide;
import fiuba.algo3.modelo.algoformer.Terrestre;

public class AlgofusionTest {

	@Test
	public void testFusionarTresAutobotsDevuelveSuperion(){
		Forma forma1 = new Humanoide( 50, 2, 2 );
		Forma forma2 = new Terrestre( 15, 4, 5 );
		Autobot optimus = new Autobot( "Optimus", 500, forma1, forma1 );
		Forma forma3 = new Humanoide( 40, 1, 2 );
		Forma forma4 = new Terrestre( 20, 3, 5 );
		Autobot bumblebee = new Autobot( "Bumblebee", 350, forma3, forma4 );
		Forma forma5 = new Humanoide( 5, 5, 1 );
		Forma forma6 = new Aerea( 35, 2, 8 );
		Autobot ratchet = new Autobot( "Ratchet", 150, forma5, forma6 );
		
		Algofusion superion = optimus.fusionar( optimus, bumblebee, ratchet );
		
		Assert.assertThat( superion.getVida(), is( 500 + 350 + 150 ) );
		Assert.assertThat( superion.getPoder(), is( 100 ) );
		Assert.assertThat( superion.getRango(), is( 2 ) );
		Assert.assertThat( superion.getVelocidad(), is( 3 ) );
	}
	
	@Test
	public void testFusionarTresDecepticonDevuelveMenasor(){
		Forma forma1 = new Humanoide( 10, 3, 1 );
		Forma forma2 = new Aerea( 55, 2, 8 );
		Decepticon megatron = new Decepticon( "Megatron", 550, forma1, forma1 );
		Forma forma3 = new Humanoide( 30, 3, 1 );
		Forma forma4 = new Terrestre( 30, 3, 8 );
		Decepticon bonecrusher = new Decepticon( "Bonecrusher", 200, forma3, forma4 );
		Forma forma5 = new Humanoide( 10, 5, 2 );
		Forma forma6 = new Terrestre( 25, 2, 6 );
		Decepticon frenzy = new Decepticon( "Frenzy", 400, forma5, forma6 );
		
		Algofusion menasor = megatron.fusionar( megatron, bonecrusher, frenzy );
		
		Assert.assertThat( menasor.getVida(), is( 550 + 200 + 400 ) );
		Assert.assertThat( menasor.getPoder(), is( 115 ) );
		Assert.assertThat( menasor.getRango(), is( 2 ) );
		Assert.assertThat( menasor.getVelocidad(), is( 2 ) );
	}
}
