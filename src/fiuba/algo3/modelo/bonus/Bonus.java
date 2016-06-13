package fiuba.algo3.modelo.bonus;

import fiuba.algo3.modelo.algoformer.Algoformer;
import fiuba.algo3.modelo.tablero.Capturable;

public abstract class Bonus implements Capturable{
	protected BonusID bonusID; //cada bonus tiene que tener un ID unico
	protected Efecto efecto;
	
	public BonusID getBonusID(){
		return bonusID;
	}
	
	public void serCapturado( Algoformer unAlgoformer ){
		unAlgoformer.nuevoBonus( this );
	}
	
	abstract public int aplicar( Algoformer algoformer, TipoEfecto tipo);
	
}