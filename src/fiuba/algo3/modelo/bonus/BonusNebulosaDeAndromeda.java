package fiuba.algo3.modelo.bonus;

import fiuba.algo3.modelo.algoformer.Algoformer;

public class BonusNebulosaDeAndromeda extends Bonus {
	
	public BonusNebulosaDeAndromeda(){
		bonusID = BonusID.NEBULOSA;
		efectos.add( new EfectoMultiplicativo( 0, TipoEfecto.VELOCIDAD ) );//impide el movimiento
		duracion = 3;
	}
		
	@Override
	public int aplicar( Efecto efecto, Algoformer algoformer, TipoEfecto tipo ){
		int	nuevaVelocidad = efecto.getValorModificado( algoformer.getVelocidadAnterior(), tipo );
			algoformer.setVelocidad( nuevaVelocidad );	
		return nuevaVelocidad;
	}
}
