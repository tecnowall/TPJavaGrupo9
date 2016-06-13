package fiuba.algo3.modelo.bonus;

public class EfectoAditivo extends Efecto {

	public EfectoAditivo( double valor, TipoEfecto tipo ){
		super( valor, tipo );
	}

	@Override
	public int aplicar( double valorBase ) {
		return (int)( valorBase + factor) ;
	}
}
