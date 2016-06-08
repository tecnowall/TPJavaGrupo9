package fiuba.algo3.modelo;

public class BonusTormentaPsionica implements Bonus {
	private static final int bonusID = 1;
	private EfectoMultiplicativoPoder efecto;
	
	public BonusTormentaPsionica(){
		efecto = new EfectoMultiplicativoPoder( 0.6 );
	}
	
	@Override
	public int aplicar( Algoformer algoformer, TipoModificador tipo ){
		if ( tipo == this.efecto.tipo() ){
			return efecto.aplicar( algoformer.getPoderBase() );
		}				
		else return algoformer.getPoderBase();
	}
}
