package fiuba.algo3.modelo.terreno;

import fiuba.algo3.modelo.algoformer.Algoformer;

public class NebulosaDeAndromeda implements Terreno {

	@Override
	public void afectar(Algoformer unAlgoformer) {
		unAlgoformer.aplicarEfectoTerrenoNebulosa();
	}

}
