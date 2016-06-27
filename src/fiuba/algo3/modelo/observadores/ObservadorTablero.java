package fiuba.algo3.modelo.observadores;

import fiuba.algo3.modelo.algoformer.Algoformer;

public interface ObservadorTablero {
	public void notificartableroCreado(int ancho,int alto);
	public void ubicarAlgoformer(Algoformer unAlgoformer,int x,int y);

		
}
