package fiuba.algo3.modelo;

import fiuba.algo3.modelo.bonus.TipoEfecto;

public class Modificador {
	private double factor;
	private TipoEfecto tipo;
	
	public Modificador( double valor, TipoEfecto tipo ){
		this.factor = valor;
		this.tipo = tipo;
	}
	
	public int aplicar( double valorBase ){
		return (int)( valorBase * factor) ;
	}

	public TipoEfecto tipo(){
		return tipo;
	}
}
