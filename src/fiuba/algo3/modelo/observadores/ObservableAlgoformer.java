package fiuba.algo3.modelo.observadores;

public interface ObservableAlgoformer {
		public void suscribir( ObservadorAlgoformer nuevoObservador );
		public void desSuscribir( ObservadorAlgoformer nuevoObservador );
}
