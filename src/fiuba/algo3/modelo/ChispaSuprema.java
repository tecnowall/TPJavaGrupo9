package fiuba.algo3.modelo;

public class ChispaSuprema implements Ubicable {
	private Coordenada posicion;

	public ChispaSuprema( Coordenada posicion ){
		ubicar ( posicion );
	}
	
	public Coordenada getPosicion() {
		return posicion;
	}

	public void setPosicion(Coordenada posicion) {
		this.posicion = posicion;
	}

	@Override
	public void ubicar(Coordenada unaCoordenada) {
			this.posicion = unaCoordenada;
		
	}

	@Override
	public void recibirAtaque(Algoformer atacante) throws FuegoAmigoException {
		// TODO Auto-generated method stub

	}

}
