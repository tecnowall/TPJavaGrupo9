package fiuba.algo3.modelo;

public class BonusTormentaPsionica extends Bonus {
	private static final int bonusID = 1;
	private Efecto efecto;
	
	public BonusTormentaPsionica(){
		efecto = new EfectoMultiplicativo( 0.6, TipoEfecto.PODER );
	}
	
	
	@Override
	public int aplicar( Algoformer algoformer, TipoEfecto tipo ){	
		return this.efecto.getValorModificado( algoformer.getPoderBase(), tipo );
	}
}
