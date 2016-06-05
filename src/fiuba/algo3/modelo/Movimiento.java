package fiuba.algo3.modelo;

import java.util.LinkedList;
import java.util.List;

//no se usa en ningun lado por el momento

public class Movimiento {
	private Tablero tablero;
	private LinkedList<Coordenada> camino;
	private Coordenada anterior;
	private boolean terminado = false;
	
	public Movimiento( Tablero tablero ){
		this.tablero = tablero;
	}
	
	public void generarCamino( Coordenada origen, Coordenada destino ){
		GeneradorDeCaminos generador = new GeneradorDeCaminos( tablero, origen, destino );
		camino = generador.crearCamino();
	}
	
	public boolean terminado(){
		return this.terminado;
	}
	
	public void terminar(){
		this.terminado = true;
	}
	
	public void avanzar( Algoformer unAlgoformer ){
		Coordenada siguiente = camino.poll();
		anterior = unAlgoformer.getPosicion();		
		tablero.getTerreno( siguiente ).afectar( unAlgoformer );
		if ( !terminado( ) ) 			tablero.mover( anterior, siguiente );	
		if ( this.camino.isEmpty() )	terminar();		
	}
	
	private void retroceder( Algoformer unAlgoformer ){	
	}

	public void reducir( int gastados ) {
		for ( int i = 0; i < gastados; i ++ ){
			this.camino.removeLast();
		}	
	}
}
