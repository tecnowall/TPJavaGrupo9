package fiuba.algo3.modelo;

import java.util.ArrayList;

public class EfectoMultiplicativo extends Efecto {
	
	public EfectoMultiplicativo( double valor, TipoEfecto tipo ){
		super( valor, tipo );
	}

	@Override
	public int aplicar( double valorBase ) {
		return (int)( valorBase * factor) ;
	}
}
