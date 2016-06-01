package fiuba.algo3.modelo;

public interface Ubicable {
	public void ubicar( Coordenada unaCoordenada );

	public void recibirAtaque(int equipo, int danio) throws FuegoAmigoException;
}
