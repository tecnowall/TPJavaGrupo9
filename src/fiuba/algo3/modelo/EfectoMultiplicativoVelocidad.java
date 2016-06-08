package fiuba.algo3.modelo;

public class EfectoMultiplicativoVelocidad {
	private Modificador modificador;
	
	public EfectoMultiplicativoVelocidad( double valor ){
		modificador = new Modificador( valor, TipoModificador.VELOCIDAD );				
	}
	  
	public int aplicar( double velocBase ){
		return this.modificador.aplicar( velocBase );
	}
	
	public TipoModificador tipo(){
		return this.modificador.tipo();
	}

}
