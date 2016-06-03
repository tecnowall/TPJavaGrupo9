package fiuba.algo3.modelo;

public class Llanura implements Terreno {

	@Override
	public void afectar(Algoformer unAlgoformer) {
		unAlgoformer.aplicarEfectoTerrenoLlanura();
	}

}
