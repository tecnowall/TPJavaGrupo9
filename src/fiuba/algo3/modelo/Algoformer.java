package fiuba.algo3.modelo;

public class Algoformer implements Ubicable {
	private Coordenada posicion;
	private String nombre;
	
	public Algoformer( String nombre ){
		this.nombre = nombre;
	}
	
	public void ubicar( Coordenada unaCoordenada ){
		this.posicion = unaCoordenada;
	}
	
	public String getNombre(){
		return this.nombre;
	}
}
