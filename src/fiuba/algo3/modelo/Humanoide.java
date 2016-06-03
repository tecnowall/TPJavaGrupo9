package fiuba.algo3.modelo;

public class Humanoide extends Forma {

	public Humanoide(int poder, int rango, int velocidad) {
		super(poder, rango, velocidad);
	}

	public void aplicarEfectoTerrenoEspinas( Algoformer unAlgoformer ){
		unAlgoformer.setVida( unAlgoformer.getVida() * 95/100 );
	}
}
