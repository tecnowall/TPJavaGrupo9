package fiuba.algo3.modelo.bonus;

import fiuba.algo3.modelo.algoformer.Algoformer;

public class BonusTormentaPsionica extends Bonus {
	
	public BonusTormentaPsionica(){
		bonusID = bonusID.TORMENTA;
		efectos.add( new EfectoMultiplicativo( 0.6, TipoEfecto.PODER ) );
	}
	
	
	@Override
	public int aplicar( Efecto efecto, Algoformer algoformer, TipoEfecto tipo ){	
		int	nuevoPoder = efecto.getValorModificado( algoformer.getPoderAnterior(), tipo );
			algoformer.setPoder( nuevoPoder );
		return nuevoPoder;
	}
}
