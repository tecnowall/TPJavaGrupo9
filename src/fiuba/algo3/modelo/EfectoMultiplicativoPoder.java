package fiuba.algo3.modelo;

public class EfectoMultiplicativoPoder {
	private Modificador modificador;
	
	public EfectoMultiplicativoPoder( double valor ){
		modificador = new Modificador( valor, TipoModificador.PODER );				
	}
	  
	public int aplicar( double poderBase ){
		return this.modificador.aplicar( poderBase );
	}
	
	public TipoModificador tipo(){
		return this.modificador.tipo();
	}
}
