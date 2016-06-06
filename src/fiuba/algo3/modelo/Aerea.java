package fiuba.algo3.modelo;

public class Aerea extends Alterna {

	public Aerea(int poder, int rango, int velocidad) {
		super(poder, rango, velocidad);
	}

	public void aplicarEfectoTerrenoEspinas( Algoformer unAlgoformer ){
		//no causa efecto
	}
	public void aplicarEfectoTerrenoTormenta(Algoformer unAlgoformer){
		int nuevoPoder = (int) (unAlgoformer.getPoder()*0.6);
		System.out.println(nuevoPoder);
		unAlgoformer.setPoder(nuevoPoder);
	}
	@Override
	public void aplicarEfectoTerrenoPantano(Algoformer unAlgoformer) {
		//no causa efecto		
	}

}
