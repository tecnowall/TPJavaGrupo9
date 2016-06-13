package fiuba.algo3.modelo.bonus;

import fiuba.algo3.modelo.algoformer.Algoformer;

public class BonusDobleCanion extends Bonus {

	public BonusDobleCanion(){
		bonusID = BonusID.DOBLE_CANION;
		efecto = new EfectoMultiplicativo( 2, TipoEfecto.PODER );
	}


	@Override
	public int aplicar( Algoformer algoformer, TipoEfecto tipo ){	
		int nuevoPoder = this.efecto.getValorModificado( algoformer.getPoderAnterior(), tipo );
		algoformer.setPoder( nuevoPoder );
		return nuevoPoder;
	}
}
