package fiuba.algo3.modelo;

public class Coordenada {
	private int x;
	private int y;
	
	public Coordenada( int x, int y ){
		this.x = x;
		this.y = y;
	}
	
	public void setXY( int x, int y ){
		setX( x );
		setY( y );
	}
	public void setX( int x ) {
		this.x = x;
	}
	
	public void setY( int y ) {
		this.y = y;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public double getDistancia ( Coordenada destino ){
		double dx = this.getX() - destino.getX();
		double dy = this.getY() - destino.getY();
		return Math.sqrt( ( dx * dx ) + ( dy * dy ) ); 
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordenada other = (Coordenada) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
}
