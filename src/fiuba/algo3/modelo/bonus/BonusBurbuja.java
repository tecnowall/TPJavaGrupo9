package fiuba.algo3.modelo.bonus;

import fiuba.algo3.modelo.algoformer.Algoformer;

public class BonusBurbuja extends Bonus {

	public BonusBurbuja(){
		bonusID = BonusID.BURBUJA;
		efecto = new EfectoAditivo( 100, TipoEfecto.ARMADURA );
	}
	
	@Override
	public int aplicar( Algoformer algoformer, TipoEfecto tipo ) {
		int nuevaArmadura = this.efecto.getValorModificado( algoformer.getArmaduraAnterior(), tipo);
		algoformer.setArmadura( nuevaArmadura);
		return nuevaArmadura;		
	}

}
