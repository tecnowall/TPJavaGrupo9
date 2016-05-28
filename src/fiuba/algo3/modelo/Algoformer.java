package fiuba.algo3.modelo;

public class Algoformer implements Ubicable {
	private Coordenada posicion;
	private String nombre;
	private int vida;
	private Forma alterna;
	private Forma actual;
	
	public Algoformer( String nombre ){
		this.nombre = nombre;
	}
	
	public Algoformer( String nombre, int vida, Forma humanoide, Forma alterna ){
		this.nombre = nombre;
		this.vida = vida;
		this.actual = humanoide;
		this.alterna = alterna;
	}
	
	public void transformar(){
		Forma temp = actual;
		actual = alterna;
		alterna = temp;
	}
	
	public void ubicar( Coordenada unaCoordenada ){
		this.posicion = unaCoordenada;
	}
	
	public boolean movimientoValido( Coordenada destino ){
		return  ( ( destino.getX() <= ( this.posicion.getX() + this.getRango() ) ) && (  destino.getY() <= ( this.posicion.getY() + this.getRango() ) ) );
	}
	
	public void mover( Tablero unTablero, Coordenada destino ){		
		if ( movimientoValido( destino ) ) {
			unTablero.mover( this.posicion, destino);		
		}		
		else throw new MovimientoFueraDeRangoException();
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public int getVida(){
		return this.vida;
	}
	
	public Coordenada getPosicion(){
		return this.posicion;
	}
	
	public void setVida( int vida ){
		this.vida = vida;
	}
	
	public int getPoder() {
		return actual.getPoder();
	}


	public int getRango() {
		return actual.getRango();
	}


	public int getVelocidad() {
		return actual.getVelocidad();
	}

}
