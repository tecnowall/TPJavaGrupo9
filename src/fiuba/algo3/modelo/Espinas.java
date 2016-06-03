package fiuba.algo3.modelo;

public class Espinas implements Terreno {

	@Override
	public void afectar( Algoformer unAlgoformer ) {
		unAlgoformer.aplicarEfectoTerrenoEspinas();

	}

}
