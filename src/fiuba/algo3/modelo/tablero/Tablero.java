package fiuba.algo3.modelo.tablero;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fiuba.algo3.modelo.Coordenada;
import fiuba.algo3.modelo.observadores.ObservadorTablero;
import fiuba.algo3.modelo.terreno.Terreno;


public class Tablero {
	private transient Map<Coordenada, Casillero> casilleros;
	private int ancho;
	private int alto;
	private ObservadorTablero observador;


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


	public List<Coordenada> getAdyacentes( Coordenada centro ){
		List<Coordenada> adyacentes = new ArrayList<Coordenada>();
		for ( int x = -1 ; x <= 1; x++ ){
			for ( int y = -1; y <= 1; y++ ){
				if ( x !=0 || y != 0 ){
					try{
						Coordenada posicion = new Coordenada( centro.getX() + x, centro.getY() + y );
						getCasillero( posicion );
						adyacentes.add( posicion );
					}	
					catch (FueraDelTableroException e){					
					}
				}

			}
		}
		return adyacentes;
	}

	public Casillero getCasillero( Coordenada coordenada ){
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
	public Capturable getCapturable(Coordenada coordenada){
		return getCasillero(coordenada).getCapturable();
	}

	public void setTerreno( Coordenada coordenada, Terreno terreno ){
		getCasillero( coordenada ).setTerreno( terreno );
	}

	public void setCapturable (Coordenada coordenada, Capturable unCapturable){
		getCasillero(coordenada).poner(unCapturable);
	}

	public Terreno getTerreno( Coordenada coordenada ){
		return getCasillero( coordenada ).getTerreno();
	}

	public void poner( Ubicable contenido, Coordenada coordenada ){
		getCasillero( coordenada ).poner( contenido );

		//Notificar vista
		//observador.ubicarAlgoformer((Algoformer) contenido, coordenada.getX(), coordenada.getY());
	}

	public void poner( Capturable capturable, Coordenada coordenada ){
		getCasillero( coordenada ).poner( capturable );
	}



	public Ubicable sacar( Coordenada coordenada ){
		return getCasillero( coordenada ).sacar();
	}

	public void mover( Coordenada origen, Coordenada destino ) {
		poner( sacar(origen), destino);
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}


}
