package fiuba.algo3.modelo;

import fiuba.algo3.modelo.algoformer.Algoformer;
import fiuba.algo3.modelo.tablero.Capturable;

public class ChispaSuprema implements Capturable {
	private Coordenada posicion;

	public ChispaSuprema(){

	}
	
	public Coordenada getPosicion() {
		return posicion;
	}

	public void setPosicion(Coordenada posicion) {
		this.posicion = posicion;
	}

	@Override
	public void serCapturado(Algoformer unAlgoformer) {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public void ubicar(Coordenada unaCoordenada) {
//			this.posicion = unaCoordenada;
//		
//	}
//
//	@Override
//	public void recibirAtaque(Algoformer atacante) throws FuegoAmigoException {
//		// TODO Auto-generated method stub
//
//	}

}
