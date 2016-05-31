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
		inicializarCasilleros();
	}
	
	public void inicializarCasilleros(){
		for ( int x = 0; x < this.ancho; x++){
			for ( int y = 0; y < this.alto; y++){
				Coordenada coordenada = new Coordenada( x, y );
				Casillero casillero = new Casillero( coordenada );
				this.casilleros.put( coordenada, casillero );
			}
		}
	}
	
	private Casillero getCasillero( Coordenada coordenada ){
		if ( this.casilleros.containsKey( coordenada ) ){
			return this.casilleros.get( coordenada );
		}
		else {
			throw new FueraDelTableroException(); 
		}	
	}
	
	public boolean estaOcupado( Coordenada coordenada ){
		if ( !this.casilleros.containsKey( coordenada ) ){
			return false;
		}
		else return getCasillero( coordenada ).estaOcupado();
	}
	
	public Ubicable getContenido( Coordenada coordenada ){ 
			return getCasillero( coordenada ).getContenido();
		
	}
	
	public void poner( Ubicable contenido, Coordenada coordenada ){
			getCasillero( coordenada ).poner( contenido );;
			
	}
	
	public Ubicable sacar( Coordenada coordenada ){
		return getCasillero( coordenada ).sacar();
	}
	
	public void mover( Coordenada origen, Coordenada destino ) {
		poner( sacar(origen), destino);
	}

	public void atacar(Coordenada destino, int equipo, int danio) throws FuegoAmigoException {
		getContenido(destino).recibirAtaque(equipo,danio);
	}
}
