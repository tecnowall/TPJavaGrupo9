package fiuba.algo3.modelo;

public class BonusNebulosaDeAndromeda implements Bonus {
	private static final int bonusID = 2;
	private EfectoMultiplicativoPoder efecto;
	
	public BonusNebulosaDeAndromeda(){
		efecto = new EfectoMultiplicativoPoder( 0 );//impide el movimiento
	}
	
	@Override
	public int aplicar( Algoformer algoformer, TipoModificador tipo ){
		if ( tipo == this.efecto.tipo() )
		return efecto.aplicar( algoformer.getVelocidadBase() );
		
		else return algoformer.getVelocidadBase();
	}
}
