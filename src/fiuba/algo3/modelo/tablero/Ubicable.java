package fiuba.algo3.modelo.tablero;

import fiuba.algo3.modelo.Coordenada;
import fiuba.algo3.modelo.algoformer.Algoformer;
import fiuba.algo3.modelo.algoformer.FuegoAmigoException;

public interface Ubicable {
	public void ubicar( Coordenada unaCoordenada );

	public void recibirAtaque(Algoformer atacante) throws FuegoAmigoException;
	
	public void capturar( Capturable unCapturable );
}
