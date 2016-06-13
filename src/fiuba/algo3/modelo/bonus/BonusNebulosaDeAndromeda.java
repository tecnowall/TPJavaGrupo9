package fiuba.algo3.modelo.bonus;

import fiuba.algo3.modelo.algoformer.Algoformer;

public class BonusNebulosaDeAndromeda extends Bonus {
	
	public BonusNebulosaDeAndromeda(){
		bonusID = BonusID.NEBULOSA;
		efecto = new EfectoMultiplicativo( 0, TipoEfecto.VELOCIDAD );//impide el movimiento
	}
		
	@Override
	public int aplicar( Algoformer algoformer, TipoEfecto tipo ){
//		return this.efecto.getValorModificado( algoformer.getVelocidadBase(), tipo );
		int nuevaVelocidad = this.efecto.getValorModificado( algoformer.getVelocidadAnterior(), tipo );
		algoformer.setVelocidad( nuevaVelocidad );
		return nuevaVelocidad;
	}
}
