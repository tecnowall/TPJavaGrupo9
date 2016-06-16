package fiuba.algo3.modelo.bonus;

import fiuba.algo3.modelo.algoformer.Algoformer;

public class BonusDobleCanion extends Bonus {

	public BonusDobleCanion(){
		super();
		bonusID = BonusID.DOBLE_CANION;
		duracion = 3;
		efectos.add( new EfectoMultiplicativo( 2, TipoEfecto.PODER ) );
	}


	@Override
	public int aplicar( Efecto efecto, Algoformer algoformer, TipoEfecto tipo ){	
		int	nuevoPoder = efecto.getValorModificado( algoformer.getPoderAnterior(), tipo );
		algoformer.setPoder( nuevoPoder );
		return nuevoPoder;
	}
}
