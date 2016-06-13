package fiuba.algo3.modelo.tablero;

import fiuba.algo3.modelo.Coordenada;
import fiuba.algo3.modelo.movimiento.Nodo;
import fiuba.algo3.modelo.terreno.Rocoso;
import fiuba.algo3.modelo.terreno.Terreno;

public class Casillero extends Nodo {
	private Coordenada coordenada;
	private Ubicable contenido;
	private Capturable capturable;
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
	
	private boolean capturable(){
		return ( this.capturable != null );
	}
	
	public void poner( Ubicable unUbicable ) {
		if ( estaOcupado() ){
			throw new CasilleroOcupadoException();
		}
		this.contenido = unUbicable;
		this.ocupado = true;
		super.setPasable( false );
		unUbicable.ubicar( this.coordenada );
		if ( capturable() ){
			unUbicable.capturar( capturable );
			this.capturable = null;
		}
	}
	
	public void poner( Capturable unCapturable ){
		this.capturable = unCapturable;
	}
	
	public boolean estaOcupado(){
		return ( ocupado );
	}
	
	public void vaciar(){
		contenido = null;
		this.ocupado = false;
		super.setPasable( true );
	}
	
	public Ubicable sacar(){
		try{
			return this.getContenido();
		} finally {
			vaciar();
		}
	}
	
//	public Capturable capturar(){
//		if ( capturable != null ) return capturable;
//	}
}
