package fiuba.algo3.modelo.algoformer;

import java.util.List;

import fiuba.algo3.modelo.tablero.Tablero;

public class Decepticon extends Algoformer implements Fusionable {
	
	private Algofusion menasor;

	public Decepticon( String nombre, int vida, Forma humanoide, Forma alterna ){
		super( nombre, vida, humanoide, alterna );
	}

	public Decepticon( String nombre, int vida, Forma humanoide, Forma alterna, Tablero tablero ){
		super( nombre, vida, humanoide, alterna, tablero );
	}
	
	@Override
	public void iniciarFusion( Algoformer parte1, Algoformer parte2, Algoformer parte3 ) {
		Forma forma = new Terrestre( 115, 2, 2 );
		menasor = new Algofusion( "Menasor", forma, parte1, parte2, parte3 );
	}

	@Override
	public Algofusion completarFusion() {
		List<Algoformer> partes = menasor.getPartes();
		
		for( Algoformer parte : partes ){
			parte.morir();
		}
		
		this.getTablero().poner( menasor, partes.get(0).getPosicion() );
		
		return menasor;
	}
	
	
}
