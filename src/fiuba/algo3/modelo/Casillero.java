package fiuba.algo3.modelo;

public class Casillero {
	private Coordenada coordenada;
	private Ubicable contenido;
	
	public Casillero( Coordenada coordenada ){
		this.coordenada = coordenada;
		this.contenido = null;
	}
	
	public Coordenada getCoordenada(){
		return this.coordenada;
	}
	
	public Ubicable getContenido(){
		if ( !estaOcupado() ){
			throw new CasilleroVacioException();
		}
		return this.contenido;
	}
	
	public void poner( Ubicable unUbicable ) {
		if ( estaOcupado() ){
			throw new CasilleroOcupadoException();
		}
		this.contenido = unUbicable;
		unUbicable.ubicar( this.coordenada );
	}
	
	public boolean estaOcupado(){
		return ( contenido != null );
	}
	
	public void vaciar(){
		contenido = null;
	}
	
	public Ubicable sacar(){
		try{
			return this.getContenido();
		} finally {
			vaciar();
		}
	}
}
