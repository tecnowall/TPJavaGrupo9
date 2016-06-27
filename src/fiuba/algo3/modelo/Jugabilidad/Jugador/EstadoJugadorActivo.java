package fiuba.algo3.modelo.Jugabilidad.Jugador;

import java.util.ArrayList;

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

    }



    public void tranformarPersonaje(Algoformer personaje, Coordenada posicion, Tablero tablero) {
        personaje.transformar();

    }

    public void combinarPersonaje(Algoformer personaje, ArrayList<Algoformer> listaPersonajes) {
      //  TODO
        if (listaPersonajes.size() < 3) throw new FaltanPersonajesParaFusionException();
        personaje.iniciarFusion(listaPersonajes.get(0),listaPersonajes.get(1),listaPersonajes.get(2));
    }

    public String getEstado (){return "activo";}

}
