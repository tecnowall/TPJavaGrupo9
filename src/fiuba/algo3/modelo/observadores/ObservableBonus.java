package fiuba.algo3.modelo.observadores;

public interface ObservableBonus {
    public void suscribir( ObservadorBonus nuevoObservador );
    public void desSuscribir( ObservadorBonus nuevoObservador );
}
