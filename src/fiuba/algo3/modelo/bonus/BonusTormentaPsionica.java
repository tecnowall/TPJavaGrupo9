package fiuba.algo3.modelo.bonus;

import fiuba.algo3.modelo.algoformer.Algoformer;

public class BonusTormentaPsionica extends Bonus {
	
	public BonusTormentaPsionica(){
		bonusID = bonusID.TORMENTA;
		efecto = new EfectoMultiplicativo( 0.6, TipoEfecto.PODER );
	}
	
	
	@Override
	public int aplicar( Algoformer algoformer, TipoEfecto tipo ){	
		int nuevoPoder = this.efecto.getValorModificado( algoformer.getPoderAnterior(), tipo );
		algoformer.setPoder( nuevoPoder );
		return nuevoPoder;
	}
}
