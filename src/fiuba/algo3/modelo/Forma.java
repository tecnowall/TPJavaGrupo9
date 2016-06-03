package fiuba.algo3.modelo;

public abstract class Forma {
	private int poder;
	private int rango;
	private int velocidad;
	
	public Forma( int poder, int rango, int velocidad ){
		this.poder = poder;
		this.rango = rango;
		this.velocidad = velocidad;
	}

	public int getPoder() {
		return poder;
	}

	public void setPoder(int poder) {
		this.poder = poder;
	}

	public int getRango() {
		return rango;
	}

	public void setRango(int rango) {
		this.rango = rango;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
	
	abstract public void aplicarEfectoTerrenoEspinas( Algoformer unAlgoformer );
	
}
