package fiuba.algo3.modelo.observadores;

import fiuba.algo3.modelo.Coordenada;
import fiuba.algo3.modelo.algoformer.Algoformer;
import fiuba.algo3.modelo.tablero.Ubicable;

public interface ObservadorTablero {
	public void notificartableroCreado(int ancho,int alto);
	public void ubicarAlgoformer(Algoformer unAlgoformer,int x,int y);

		
}
