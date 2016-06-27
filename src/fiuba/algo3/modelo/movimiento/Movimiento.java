package fiuba.algo3.modelo.movimiento;

import java.util.LinkedList;

import fiuba.algo3.modelo.Coordenada;
import fiuba.algo3.modelo.algoformer.Algoformer;
import fiuba.algo3.modelo.tablero.CasilleroOcupadoException;
import fiuba.algo3.modelo.tablero.Tablero;

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
		if ( this.tablero.estaOcupado( destino ) ) {
			throw new CasilleroOcupadoException();
		}
		GeneradorDeCaminos generador = new GeneradorDeCaminos( tablero, origen, destino );
		camino = generador.crearCamino();
		truncarCamino();
		caminoRecorrido = new LinkedList<Coordenada>();
	}
	
	private void truncarCamino(){
		Coordenada coordenada;
		boolean done = false;
		while ( !done && !camino.isEmpty() ){
			coordenada = camino.getLast();
			if ( tablero.estaOcupado( coordenada ) ){
				camino.removeLast();
			}
			else done = true;
		}
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
