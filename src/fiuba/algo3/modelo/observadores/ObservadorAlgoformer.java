package fiuba.algo3.modelo.observadores;

import fiuba.algo3.modelo.algoformer.Algoformer;

public interface ObservadorAlgoformer {

    public void fallecioAlgoformer(Algoformer unAlgoformerFallecido);
    public void huboUnMovimiento(Algoformer unAlgoformer);
    public void huboUnAtaque(Algoformer unAlgoformer);
    public void huboUnaTransformacion(Algoformer unAlgoformer);
}
