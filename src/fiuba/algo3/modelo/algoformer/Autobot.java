package fiuba.algo3.modelo.algoformer;

import java.util.List;

import fiuba.algo3.modelo.TipoEquipo;
import fiuba.algo3.modelo.bonus.Bonus;
import fiuba.algo3.modelo.bonus.BonusFusion;
import fiuba.algo3.modelo.tablero.Tablero;

public class Autobot extends Algoformer{
	
	private Algofusion superion;
	
	public Autobot( String nombre, int vida, Forma humanoide, Forma alterna ){
		super( nombre, vida, humanoide, alterna );
	}

	public Autobot( String nombre, int vida, Forma humanoide, Forma alterna, Tablero tablero ){
		super( nombre, vida, humanoide, alterna, tablero );
	}
	
	public static Autobot getOptimus(){
		Forma humanoide = new Humanoide( 50, 2, 2 );
		Forma terrestre = new Terrestre( 15, 4, 5 );
		Autobot optimus = new Autobot( "Optimus", 500, humanoide, terrestre );
		optimus.setEquipo( TipoEquipo.AUTOBOTS );
		
		return optimus;
	}
	
	public static Autobot getBumblebee(){
		Forma humanoide = new Humanoide( 40, 1, 2 );
		Forma terrestre = new Terrestre( 20, 3, 5 );
		Autobot bumblebee = new Autobot( "Bumblebee", 350, humanoide, terrestre );
		bumblebee.setEquipo( TipoEquipo.AUTOBOTS );
		
		return bumblebee;
	}
	
	public static Autobot getRatchet(){
		Forma humanoide = new Humanoide( 5, 5, 1 );
		Forma aerea = new Aerea( 35, 2, 8 );
		Autobot ratchet = new Autobot( "Ratchet", 150, humanoide, aerea );
		ratchet.setEquipo( TipoEquipo.AUTOBOTS );
		
		return ratchet;
	}
	
	@Override
	public void iniciarFusion( Algoformer parte1, Algoformer parte2, Algoformer parte3 ) {
		Forma forma = new Terrestre( 100, 2, 3 );
		superion = new Algofusion( "Superion", forma, parte1, parte2, parte3 );
		superion.setEquipo( TipoEquipo.AUTOBOTS);
		Bonus bonus = new BonusFusion();
		parte1.nuevoBonus( bonus );
	}

	@Override
	public Algofusion completarFusion() {
		List<Algoformer> partes = superion.getPartes();
		
		////superion.agregarJugador( superion.getPartes().get(0).getJugador() );
		
		for( Algoformer parte : partes ){
			parte.salirDelTablero();
		}
		
		this.getTablero().poner( superion, partes.get(0).getPosicion() );
		this.jugador.agregarPersonaje( superion );
		
		return superion;
	}
	
	
	
}
