package fiuba.algo3.modelo.Jugabilidad.Jugador;

import fiuba.algo3.modelo.Coordenada;

import fiuba.algo3.modelo.algoformer.Algoformer;
import fiuba.algo3.modelo.tablero.Tablero;

import java.util.ArrayList;

public class  EstadoJugadorEsperando extends EstadoJugador{

        public void moverPersonaje(Algoformer personaje, Coordenada posicion, Tablero tablero) {
                throw new JugadorEnEstadoDeEsperaException();
        }

        public void atacar(Algoformer personaje, Coordenada posicion, Tablero tablero) {
                throw new JugadorEnEstadoDeEsperaException();
        }


        public void tranformarPersonaje(Algoformer personaje, Coordenada posicion, Tablero tablero) {
                throw new JugadorEnEstadoDeEsperaException();
        }

        public void combinarPersonaje(Algoformer personaje, ArrayList<Algoformer> listaPersonajes) {
                throw new JugadorEnEstadoDeEsperaException();
        }

        public String getEstado (){return "esperando";}




}
