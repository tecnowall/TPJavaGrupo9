package fiuba.algo3.modelo.bonus;

import fiuba.algo3.modelo.algoformer.Fusionable;

public class EfectoFusionar extends Efecto {

	public EfectoFusionar( TipoEfecto tipo ){
		super( 0, tipo );
	}
	
	public void aplicar( Fusionable fusionable ){
		fusionable.completarFusion();
	}
	
	@Override
	public int aplicar( double valorBase ) {
		System.out.println("fusionar");
		return (int)valorBase;
	}

}
