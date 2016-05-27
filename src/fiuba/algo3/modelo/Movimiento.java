package fiuba.algo3.modelo;

public class Movimiento {
	private int rango;
	
	public Movimiento( int rango ){
		this.rango = rango;
	}
	
	public int getRango(){
		return this.rango;
	}
	
	public boolean movimientoValido( Coordenada origen, Coordenada destino ){
		
		return ( ( destino.getX() <= ( origen.getX() + this.rango ) ) && (  destino.getY() <= ( origen.getY() + this.rango ) ) );
	}
}
