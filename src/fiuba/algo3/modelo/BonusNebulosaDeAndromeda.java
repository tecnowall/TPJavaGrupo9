package fiuba.algo3.modelo;

public class BonusNebulosaDeAndromeda extends Bonus {
	private static final int bonusID = 2;
	private Efecto efecto;
	
	public BonusNebulosaDeAndromeda(){
		efecto = new EfectoMultiplicativo( 0, TipoEfecto.VELOCIDAD );//impide el movimiento
	}
		
	@Override
	public int aplicar( Algoformer algoformer, TipoEfecto tipo ){
		return this.efecto.getValorModificado( algoformer.getPoderBase(), tipo );
	}
}
