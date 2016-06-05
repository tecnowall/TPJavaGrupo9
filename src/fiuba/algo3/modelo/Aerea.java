package fiuba.algo3.modelo;

public class Aerea extends Alterna {

	public Aerea(int poder, int rango, int velocidad) {
		super(poder, rango, velocidad);
	}

	public void aplicarEfectoTerrenoEspinas( Algoformer unAlgoformer ){
		//no causa efecto
	}

	@Override
	public void aplicarEfectoTerrenoPantano(Algoformer unAlgoformer) {
		//no causa efecto		
	}
}
