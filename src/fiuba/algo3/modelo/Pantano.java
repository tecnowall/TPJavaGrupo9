package fiuba.algo3.modelo;

public class Pantano implements Terreno {

	@Override
	public void afectar(Algoformer unAlgoformer) {
		unAlgoformer.aplicarEfectoTerrenoPantano();

	}

}
