package fiuba.algo3.modelo;

public class Modificador {
	private double factor;
	private TipoModificador tipo;
	
	public Modificador( double valor, TipoModificador tipo ){
		this.factor = valor;
		this.tipo = tipo;
	}
	
	public int aplicar( double valorBase ){
		return (int)( valorBase * factor) ;
	}

	public TipoModificador tipo(){
		return TipoModificador.PODER;
	}
}
