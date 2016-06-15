package fiuba.algo3.modelo.algoformer;

public class Autobot extends Algoformer implements Fusionable{
	
	public Autobot( String nombre, int vida, Forma humanoide, Forma alterna ){
		super( nombre, vida, humanoide, alterna );
	}

	@Override
	public Algofusion fusionar( Algoformer parte1, Algoformer parte2, Algoformer parte3 ) {
		Forma forma = new Terrestre( 100, 2, 3 );
		Algofusion superion = new Algofusion( "Superion", forma, parte1, parte2, parte3 );
		return superion;
	}
	
	
	
}
