package fiuba.algo3.modelo;

public class Pantano implements Terreno {
	static final public int PODER_EFECTO = 1;
	
	@Override
	public void afectar(Algoformer unAlgoformer) {
		unAlgoformer.aplicarEfectoTerrenoPantano();
	}

}
