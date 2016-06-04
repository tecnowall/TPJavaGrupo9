package fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Camino {
	private Tablero tablero;
	private Coordenada origen;
	private Coordenada destino;
	private List<Nodo> openList;
	private List<Nodo> closedList;
	private boolean terminado = false;

	private Comparator<Nodo> comparadorNodos = new Comparator<Nodo>(){

		@Override
		public int compare(Nodo o1, Nodo o2) {
			if ( o2.getFCost() < o1.getFCost() ) return 1;
			if ( o2.getFCost() > o1.getFCost() ) return -1;
			return 0;
		}

	};
	
	public Camino ( Tablero tablero, Coordenada origen, Coordenada destino){
		this.tablero = tablero;
		this.origen = origen;
		this.destino = destino;
	}

	private List<Coordenada> armarCamino(Nodo origen, Nodo destino) {
		LinkedList<Coordenada> camino = new LinkedList<Coordenada>();

		Nodo actual = destino;
		boolean terminado = false;
		while (!terminado) {
			camino.addFirst(actual.getPosicion());
			actual = (Nodo) actual.getPadre();
			if (actual.equals(origen)) {
				terminado = true;
			}
		}
		return camino;
	}

	public List<Nodo> getAdyacentes( Tablero tablero, Coordenada centro ){
		List<Nodo> adyacentes = new ArrayList<Nodo>();
		for ( int x = -1 ; x <= 1; x++ ){
			for ( int y = -1; y <= 1; y++ ){
				if ( x !=0 || y != 0 ){
					try{
						Coordenada posicion = new Coordenada( centro.getX() + x, centro.getY() + y );
						Nodo nodo = tablero.getCasillero( posicion );
						if ( !closedList.contains( nodo ) ) adyacentes.add( nodo );					
					}	
					catch (FueraDelTableroException e){					
					}
				}

			}
		}
		return adyacentes;
	}
	
	//algoritmo A*
	public List<Coordenada> crearCamino() {
		openList = new LinkedList<Nodo>();
		closedList = new LinkedList<Nodo>();
		openList.add(tablero.getCasillero( origen ) );

		terminado = false;
		Nodo actual;
		while (!terminado) {
			Collections.sort( openList, comparadorNodos );
			actual = openList.get(0);
			closedList.add(actual);
			openList.remove(actual);

			if ( actual.getX() == destino.getX() && actual.getY() == destino.getY() ) {
				return armarCamino(tablero.getCasillero( origen ), actual);
			}

			List<Nodo> nodosAdyacentes = getAdyacentes( tablero, actual.getPosicion() );
			for (int i = 0; i < nodosAdyacentes.size(); i++) {
				Nodo adyacenteActual = nodosAdyacentes.get(i);
				if (!openList.contains(adyacenteActual)) {
					adyacenteActual.setPadre(actual);
					adyacenteActual.setHCost(tablero.getCasillero( destino ) );
					adyacenteActual.setGCost(actual);
					openList.add(adyacenteActual);
				} else {
					if (adyacenteActual.getGCost() > adyacenteActual.calcularGCost(actual)) {
						adyacenteActual.setPadre(actual);
						adyacenteActual.setGCost(actual);
					}
				}
			}

			if (openList.isEmpty()) { 
				return new LinkedList<Coordenada>(); 
			}
		}
		return null;
	}
}