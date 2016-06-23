package fiuba.algo3.modelo.observadores;

import fiuba.algo3.modelo.Coordenada;
import fiuba.algo3.modelo.algoformer.Algoformer;
import fiuba.algo3.modelo.algoformer.Algofusion;

public interface ObservadorAlgoformer {

    public void fallecioAlgoformer(Algoformer unAlgoformerFallecido);
    public void huboUnMovimiento(Algoformer unAlgoformer, Coordenada original);
    public void huboUnAtaque(Algoformer unAlgoformer);
    public void huboUnaTransformacion(Algoformer unAlgoformer);
    public void huboUnaFusion (Algofusion unAlgoformer);
}
