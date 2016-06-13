package fiuba.algo3.modelo;

public class Espinas implements Terreno {
	static final public int PODER_EFECTO = 5;
	
	@Override
	public void afectar( Algoformer unAlgoformer ) {
		unAlgoformer.aplicarEfectoTerrenoEspinas();

	}

}
