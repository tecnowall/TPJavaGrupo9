package fiuba.algo3.modelo.algoformer;

import fiuba.algo3.modelo.terreno.Pantano;

public class Terrestre extends Alterna {

	public Terrestre(int poder, int rango, int velocidad) {
		super(poder, rango, velocidad);
	}
	
	public void aplicarEfectoTerrenoEspinas( Algoformer unAlgoformer ){
		unAlgoformer.setVida( unAlgoformer.getVida() * 95/100 );
	}

	@Override
	public void aplicarEfectoTerrenoPantano(Algoformer unAlgoformer) {
		unAlgoformer.gastarMovimientos( Pantano.PODER_EFECTO );	
	}
	public void aplicarEfectoTerrenoTormenta(Algoformer unAlgoformer){
		//no causa efecto
	}
	public void aplicarEfectoTerrenoNebulosa(Algoformer unAlgoformer){
		//no causa efecto
	}

	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return "Terrestre";
	}
	
}
