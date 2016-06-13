package fiuba.algo3.modelo.algoformer;

public abstract class Forma {
	private int poder;
	private int rango;
	private int velocidad;
	private double armadura;
	
	public Forma( int poder, int rango, int velocidad ){
		this.poder = poder;
		this.rango = rango;
		this.velocidad = velocidad;
		this.armadura = 0;
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
	
	public double getArmadura() {
		return armadura;
	}

	public void setArmadura( double armadura ) {
		this.armadura = armadura;
	}

	abstract public void aplicarEfectoTerrenoEspinas( Algoformer unAlgoformer );

	abstract public void aplicarEfectoTerrenoPantano( Algoformer unAlgoformer );
	
	abstract public void aplicarEfectoTerrenoTormenta( Algoformer unAlgoformer );
	
	abstract public void aplicarEfectoTerrenoNebulosa (Algoformer unAlgoformer);
	
}
