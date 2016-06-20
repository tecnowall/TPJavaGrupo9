package fiuba.algo3.modelo.observadores;

import fiuba.algo3.modelo.Jugabilidad.Jugador.Jugador;

public interface ObservadorPartida {

    public void jugadorSinPersonajes (Jugador playerWin);
    public void capturaronChispaSuprema (Jugador unJugador);
}
