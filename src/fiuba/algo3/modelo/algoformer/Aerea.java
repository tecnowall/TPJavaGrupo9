package fiuba.algo3.modelo.algoformer;

import fiuba.algo3.modelo.bonus.Bonus;
import fiuba.algo3.modelo.bonus.BonusNebulosaDeAndromeda;
import fiuba.algo3.modelo.bonus.BonusTormentaPsionica;

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

	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return "Aerea";
	}

}
