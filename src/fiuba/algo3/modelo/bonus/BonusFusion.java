package fiuba.algo3.modelo.bonus;

import fiuba.algo3.modelo.algoformer.Algoformer;

public class BonusFusion extends Bonus {

	public BonusFusion(){
		bonusID = BonusID.FUSION;
		duracion = 2;
		efectos.add( new EfectoMultiplicativo( 0, TipoEfecto.VELOCIDAD ) );
//		efectos.add( new EfectoFusionar( TipoEfecto.FIN_DE_TURNO ) );
	}
	
	@Override
	public void consumirDuracion(){
		duracion --;
		if ( duracion < 1 ){
			afectado.completarFusion();
			afectado.perderBonus( this.bonusID );
		}
	}
	
	@Override
	public int aplicar( Efecto efecto, Algoformer algoformer, TipoEfecto tipo ) {
		int	nuevaVelocidad = efecto.getValorModificado( algoformer.getVelocidadAnterior(), tipo );
		algoformer.setVelocidad( nuevaVelocidad );	
		
		return nuevaVelocidad;		
	}

}
