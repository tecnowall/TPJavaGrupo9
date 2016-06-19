package fiuba.algo3.modelo.observadores;

public interface ObservableJuego {
    public void suscribir( ObservadorJuego nuevoObservador );
    public void desSuscribir( ObservadorJuego nuevoObservador );
}
