package fiuba.algo3.modelo;

import java.util.LinkedList;
import java.util.List;

//no se usa en ningun lado por el momento

public class Movimiento {
	private Tablero tablero;
	private LinkedList<Coordenada> camino;
	private LinkedList<Coordenada> caminoRecorrido;
	private Coordenada anterior;
	private boolean terminado = false;
	
	public Movimiento( Tablero tablero ){
		this.tablero = tablero;
	}
	
	public void generarCamino( Coordenada origen, Coordenada destino ){
		GeneradorDeCaminos generador = new GeneradorDeCaminos( tablero, origen, destino );
		camino = generador.crearCamino();
		caminoRecorrido = new LinkedList<Coordenada>();
	}
	
	public int caminoRestante(){
		return this.camino.size();
	}
	
	public boolean terminado(){
		return this.terminado;
	}
	
	public void terminar(){
		this.terminado = true;
	}
	
	public void avanzar( Algoformer unAlgoformer ){
		Coordenada siguiente = camino.poll();	
		if ( !terminado( ) ) 	{	
			caminoRecorrido.add( unAlgoformer.getPosicion() );
			tablero.mover( unAlgoformer.getPosicion(), siguiente );	
			tablero.getTerreno( siguiente ).afectar( unAlgoformer );			
			}
		if ( this.camino.isEmpty() )	terminar();		
	}
	
	public void retroceder( Algoformer unAlgoformer ){
		if ( !caminoRecorrido.isEmpty() ){
		anterior = this.caminoRecorrido.pollLast();
		tablero.mover( unAlgoformer.getPosicion() , anterior );
		}
	}

	public void reducir( int gastados ) {
		for ( int i = 0; i < gastados; i ++ ){
			this.camino.removeLast();
		}	
	}
}
