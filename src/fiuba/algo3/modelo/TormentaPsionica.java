package fiuba.algo3.modelo;

public class TormentaPsionica implements Terreno {

	@Override
	public void afectar(Algoformer unAlgoformer) {
		unAlgoformer.aplicarEfectoTerrenoTormenta();

	}

}
