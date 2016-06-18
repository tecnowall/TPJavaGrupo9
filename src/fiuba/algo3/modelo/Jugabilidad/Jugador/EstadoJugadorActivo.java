package fiuba.algo3.modelo.Jugabilidad.Jugador;

import fiuba.algo3.modelo.Coordenada;
import fiuba.algo3.modelo.algoformer.Algoformer;
import fiuba.algo3.modelo.tablero.Tablero;

/**
 * Created by jose on 13/06/2016.
 */
public class EstadoJugadorActivo extends EstadoJugador {

    public void moverPersonaje(Algoformer personaje, Coordenada posicion, Tablero tablero) {

       personaje.mover(tablero, posicion);

    }

    public void atacar(Algoformer personaje, Coordenada posicion, Tablero tablero)  {
        personaje.atacar(tablero, posicion);
        //TODO revisar la excepcion
    }



    public void tranformarPersonaje(Algoformer personaje, Coordenada posicion, Tablero tablero) {
        personaje.transformar();

    }

    public void CombinarPersonaje(Algoformer personaje, Coordenada posicion, Tablero tablero) {
        // tiene que recibir un algoformer, revisar un rango y si estan en el rango comibar

    }

    public String getEstado (){return "activo";}

}
