package fiuba.algo3.modelo.Jugabilidad.Jugador;

import fiuba.algo3.modelo.Coordenada;

import fiuba.algo3.modelo.algoformer.Algoformer;
import fiuba.algo3.modelo.tablero.Tablero;

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

        public void CombinarPersonaje(Algoformer personaje, Coordenada posicion, Tablero tablero) {
                throw new JugadorEnEstadoDeEsperaException();
        }

        public String getEstado (){return "esperando";}




}
