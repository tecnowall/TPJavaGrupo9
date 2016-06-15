package fiuba.algo3.modelo.algoformer;

import java.util.ArrayList;
import java.util.List;

public class Algofusion extends Algoformer {
	
	private List<Algoformer> partes;
	
	public Algofusion( String nombre, Forma forma, Algoformer parte1, Algoformer parte2, Algoformer parte3 ){
		super( nombre, parte1.getVida() + parte2.getVida() + parte3.getVida(), forma, forma );
		partes = new ArrayList<Algoformer>();
		partes.add( parte1 );
		partes.add( parte2 );
		partes.add( parte3 );
	}
	
	@Override
	public void transformar(){
		//las fusiones no se transforman
	}
}
