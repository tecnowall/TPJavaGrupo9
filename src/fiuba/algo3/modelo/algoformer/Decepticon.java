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
	
	public static Decepticon getMegatron(){
		Forma humanoide = new Humanoide( 10, 3, 1 );
		Forma aerea = new Aerea( 55, 2, 8 );
		Decepticon megatron = new Decepticon( "Megatron", 550, humanoide, aerea );
		
		return megatron;
	}
	
	public static Decepticon getBonecrusher(){
		Forma humanoide = new Humanoide( 30, 3, 1 );
		Forma terrestre = new Terrestre( 30, 3, 8 );
		Decepticon bonecrusher = new Decepticon( "Bonecrusher", 200, humanoide, terrestre );
		
		return bonecrusher;
	}
	
	public static Decepticon getFrenzy(){
		Forma humanoide = new Humanoide( 10, 5, 2 );
		Forma terrestre = new Terrestre( 25, 2, 6 );
		Decepticon frenzy = new Decepticon( "frenzy", 400, humanoide, terrestre );
		
		return frenzy;
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
			parte.salirDelTablero();
		}
		
		this.getTablero().poner( menasor, partes.get(0).getPosicion() );
		
		return menasor;
	}
	
	
}
