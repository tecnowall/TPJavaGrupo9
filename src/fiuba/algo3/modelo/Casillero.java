package fiuba.algo3.modelo;

public class Casillero extends Nodo {
	private Coordenada coordenada;
	private Ubicable contenido;
	private Terreno terreno;
	private boolean ocupado;
	
	public Casillero( Coordenada coordenada ){
		super( coordenada );
		this.coordenada = coordenada;
		this.contenido = null;
		this.terreno = new Rocoso();
		this.ocupado = false;
	}
	
	public Casillero( Coordenada coordenada, Terreno terreno ){
		super( coordenada );
		this.coordenada = coordenada;
		this.contenido = null;
		this.terreno = terreno;
		this.ocupado = false;
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
	
	public void setTerreno( Terreno terreno ){
		this.terreno = terreno;
	}
	
	public Terreno getTerreno(){
		return this.terreno;
	}
	
	public void poner( Ubicable unUbicable ) {
		if ( estaOcupado() ){
			throw new CasilleroOcupadoException();
		}
		this.contenido = unUbicable;
		this.ocupado = true;
		unUbicable.ubicar( this.coordenada );
	}
	
	public boolean estaOcupado(){
		return ( ocupado );
	}
	
	public void vaciar(){
		contenido = null;
		this.ocupado = false;
	}
	
	public Ubicable sacar(){
		try{
			return this.getContenido();
		} finally {
			vaciar();
		}
	}
}
