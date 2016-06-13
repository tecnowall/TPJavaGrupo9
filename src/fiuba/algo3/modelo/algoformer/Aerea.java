package fiuba.algo3.modelo;

public class Aerea extends Alterna {

	public Aerea(int poder, int rango, int velocidad) {
		super(poder, rango, velocidad);
	}

	public void aplicarEfectoTerrenoEspinas( Algoformer unAlgoformer ){
		//no causa efecto
	}
	public void aplicarEfectoTerrenoTormenta(Algoformer unAlgoformer){
		Bonus bonus = new BonusTormentaPsionica();
		unAlgoformer.nuevoBonus( bonus );
//		int nuevoPoder = (int) (unAlgoformer.getPoderBase()*0.6);
//		unAlgoformer.setPoder(nuevoPoder);
	}
	public void aplicarEfectoTerrenoNebulosa(Algoformer unAlgoformer){
		unAlgoformer.terminarMovimiento();
		Bonus bonus = new BonusNebulosaDeAndromeda();
		unAlgoformer.nuevoBonus(bonus);
		//VER PERSISTENCIA EN TURNOS
	}
	@Override
	public void aplicarEfectoTerrenoPantano(Algoformer unAlgoformer) {
		//no causa efecto		
	}

}
