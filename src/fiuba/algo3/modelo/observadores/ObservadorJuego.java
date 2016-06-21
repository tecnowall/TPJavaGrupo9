package fiuba.algo3.modelo.observadores;

import fiuba.algo3.modelo.Jugabilidad.Jugador.Jugador;

public interface ObservadorJuego {

    public void finalizoJuego (Jugador playerWin);

    public void esElTurnoDelJugador (Jugador unJugador);
}
