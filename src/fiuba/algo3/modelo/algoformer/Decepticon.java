package fiuba.algo3.modelo.algoformer;

public class Decepticon extends Algoformer implements Fusionable {

	public Decepticon( String nombre, int vida, Forma humanoide, Forma alterna ){
		super( nombre, vida, humanoide, alterna );
	}

	@Override
	public Algofusion fusionar( Algoformer parte1, Algoformer parte2, Algoformer parte3 ) {
		Forma forma = new Terrestre( 115, 2, 2 );
		Algofusion menasor = new Algofusion( "Menasor", forma, parte1, parte2, parte3 );
		return menasor;
	}
}
