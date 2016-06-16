package fiuba.algo3.modelo.algoformer;

import java.util.List;

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
	
	@Override
	public void iniciarFusion( Algoformer parte1, Algoformer parte2, Algoformer parte3 ) {
		Forma forma = new Terrestre( 100, 2, 3 );
		superion = new Algofusion( "Superion", forma, parte1, parte2, parte3 );
		Bonus bonus = new BonusFusion();
		parte1.nuevoBonus( bonus );
	}

	@Override
	public Algofusion completarFusion() {
		List<Algoformer> partes = superion.getPartes();
		
		for( Algoformer parte : partes ){
			parte.morir();
		}
		
		this.getTablero().poner( superion, partes.get(0).getPosicion() );
		
		return superion;
	}
	
	
	
}
