package fiuba.algo3.modelo.bonus;

import java.util.ArrayList;
import java.util.List;

import fiuba.algo3.modelo.algoformer.Algoformer;
import fiuba.algo3.modelo.tablero.Capturable;

public abstract class Bonus implements Capturable{
	protected BonusID bonusID; //cada bonus tiene que tener un ID unico
	protected List<Efecto> efectos;
	protected int duracion;
	protected Algoformer afectado;
	
	public Bonus(){
		this.efectos = new ArrayList<Efecto>();
	}
	
	public BonusID getBonusID(){
		return bonusID;
	}
	
	public void serCapturado( Algoformer unAlgoformer ){
		unAlgoformer.nuevoBonus( this );
	}
	
	public void consumirDuracion(){
		duracion --;
		if ( duracion == 0 ){
			afectado.perderBonus( this.bonusID );
		}
	}
	
	public int getDuracion(){
		return duracion;
	}
	
	public void setAfectado( Algoformer afectado ){
		this.afectado = afectado;
	}
	
	public void aplicarEfectos( Algoformer algoformer, List<TipoEfecto> tipos){
		for ( TipoEfecto tipo : tipos ){
			aplicarEfectos( algoformer, tipo );
		}
	}
	
	public void aplicarEfectos( Algoformer algoformer, TipoEfecto tipo){
		for ( Efecto efecto : efectos ){
			aplicar( efecto, algoformer, tipo );
		}
	}
	
	abstract public int aplicar( Efecto efecto, Algoformer algoformer, TipoEfecto tipo );
}