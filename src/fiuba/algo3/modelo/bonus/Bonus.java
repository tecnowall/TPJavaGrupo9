package fiuba.algo3.modelo;

public abstract class Bonus {
	private static int bonusID; //cada bonus tiene que tener un ID unico
	
	public int getBonusID(){
		return bonusID;
	}
	
	abstract int  aplicar( Algoformer algoformer, TipoEfecto tipo);
	
}