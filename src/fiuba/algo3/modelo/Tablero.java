package fiuba.algo3.modelo;

import java.util.HashMap;

public class Tablero {
	private HashMap<Coordenada, Casillero> casilleros;
	private int ancho;
	private int alto;
	
	public Tablero ( int ancho, int alto ){
		this.ancho = ancho;
		this.alto = alto;
		casilleros = new HashMap<Coordenada, Casillero>( ancho * alto );
	}
	
	public boolean estaOcupado( Coordenada coordenada ){
		return this.casilleros.containsKey( coordenada );
	}
	public Ubicable getContenido( Coordenada coordenada ){ 
		if ( estaOcupado ( coordenada ) ){
			return this.casilleros.get( coordenada ).getContenido();
		}
		else return null; //hacer otra cosa
		
	}
	
	public void poner( Ubicable contenido, Coordenada coordenada ){
		Casillero casillero = new Casillero( coordenada );
		casillero.poner( contenido );
		this.casilleros.put( coordenada, casillero );
	}
	
	public Ubicable sacar( Coordenada coordenada ){
		try{
		return this.casilleros.get( coordenada ).sacar();
		} finally {
			this.casilleros.remove( coordenada );
		}
		
	}
	
	public void mover( Coordenada origen, Coordenada destino ) {
		poner( sacar(origen), destino);
	}
}
