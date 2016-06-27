package fiuba.algo3.modelo.Jugabilidad.Jugador;


import java.util.ArrayList;

import fiuba.algo3.modelo.Coordenada;
import fiuba.algo3.modelo.algoformer.Algoformer;
import fiuba.algo3.modelo.tablero.Tablero;

public abstract class EstadoJugador {

    public abstract void moverPersonaje(Algoformer personaje, Coordenada posicion, Tablero tablero);

    public abstract void atacar(Algoformer personaje, Coordenada posicion, Tablero tablero) ;

    public abstract void tranformarPersonaje(Algoformer personaje, Coordenada posicion, Tablero tablero);

    public abstract void combinarPersonaje(Algoformer personaje, ArrayList<Algoformer> listaPersonajes) ;

    public abstract String getEstado ();

}

