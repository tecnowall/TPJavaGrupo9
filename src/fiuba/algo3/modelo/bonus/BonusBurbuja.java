package fiuba.algo3.modelo.bonus;

import fiuba.algo3.modelo.algoformer.Algoformer;

public class BonusBurbuja extends Bonus {

	public BonusBurbuja(){
		super();
		bonusID = BonusID.BURBUJA;
		duracion = 2;
		efectos.add( new EfectoAditivo( 100, TipoEfecto.ARMADURA ) );
	}
	
	@Override
	public int aplicar( Efecto efecto, Algoformer algoformer, TipoEfecto tipo ) {
		int	nuevaArmadura = efecto.getValorModificado( algoformer.getArmaduraAnterior(), tipo);
			algoformer.setArmadura( nuevaArmadura);	
		return nuevaArmadura;		
	}

}
