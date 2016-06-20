package fiuba.algo3.modelo.observadores;

public interface ObservablePartida {
    public void suscribir( ObservadorPartida nuevoObservador );
    public void desSuscribir( ObservadorPartida nuevoObservador );
}
