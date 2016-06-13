package fiuba.algo3.modelo.terreno;

import fiuba.algo3.modelo.algoformer.Algoformer;

public class Espinas implements Terreno {
	static final public int PODER_EFECTO = 5;
	
	@Override
	public void afectar( Algoformer unAlgoformer ) {
		unAlgoformer.aplicarEfectoTerrenoEspinas();

	}

}
