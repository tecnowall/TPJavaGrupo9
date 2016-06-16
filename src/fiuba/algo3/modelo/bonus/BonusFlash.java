package fiuba.algo3.modelo.bonus;

import fiuba.algo3.modelo.algoformer.Algoformer;

public class BonusFlash extends Bonus {

	public BonusFlash(){
		bonusID = BonusID.FLASH;
		duracion = 3;
		efectos.add( new EfectoMultiplicativo( 3, TipoEfecto.VELOCIDAD ) );
	}

	@Override
	public int aplicar( Efecto efecto, Algoformer algoformer, TipoEfecto tipo ){
		int	nuevaVelocidad = efecto.getValorModificado( algoformer.getVelocidadAnterior(), tipo );
			algoformer.setVelocidad( nuevaVelocidad );	
		return nuevaVelocidad;
	}
}