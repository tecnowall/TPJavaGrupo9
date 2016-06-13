package fiuba.algo3.modelo.bonus;

import java.util.ArrayList;
import java.util.List;

public abstract class Efecto {

	protected double factor;
	protected List<TipoEfecto> tipos;

	public Efecto() {
		super();
	}
	
	public Efecto( double valor, TipoEfecto tipo ){
		this.factor = valor;
		this.tipos = new ArrayList<TipoEfecto>();
		this.tipos.add( tipo );
	}

	public Efecto( double valor, List<TipoEfecto> tipos ){
		this.factor = valor;
		this.tipos = tipos;
	}
	
	public List<TipoEfecto> tipo() {
		return this.tipos;
	}
	
	public boolean esTipo( TipoEfecto tipo ){
		return this.tipos.contains( tipo );
	}
	public int getValorModificado( double valorBase, TipoEfecto tipo ){
		if ( esTipo( tipo ) ){
			return aplicar( valorBase );
		}
		else return (int)valorBase;
	}
	
	public abstract int aplicar( double valorBase );	
}