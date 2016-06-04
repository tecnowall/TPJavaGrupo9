package fiuba.algo3.modelo;

import java.util.List;

public class Algoformer implements Ubicable {
	private Coordenada posicion;
	private String nombre;
	private int vida;
	private Forma alterna;
	private Forma actual;
	private int equipo;
	
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
		return  ( ( destino.getX() <= ( this.posicion.getX() + this.getVelocidad() ) ) && (  destino.getY() <= ( this.posicion.getY() + this.getVelocidad() ) ) );
	}
	
	public void mover( Tablero unTablero, Coordenada destino ){		
		if ( movimientoValido( destino ) ) {
			Camino camino = new Camino( unTablero, this.posicion, destino );
			List<Coordenada> coordenadas = camino.crearCamino();
			for ( Coordenada siguiente : coordenadas ){
				unTablero.mover( this.posicion, siguiente );
				unTablero.getTerreno( siguiente ).afectar( this );
			}

		}		
		else throw new MovimientoFueraDeRangoException();
	}
	
	public boolean ataqueValido( Coordenada destino ){
		return  ( ( destino.getX() <= ( this.posicion.getX() + this.getRango() ) ) && (  destino.getY() <= ( this.posicion.getY() + this.getRango() ) ) );
	}
	
	public void atacar (Tablero unTablero, Coordenada destino) throws AtaqueFueraDeRangoException, FuegoAmigoException{
		if (ataqueValido(destino)){
			unTablero.getContenido(destino).recibirAtaque(this);
		}
		else throw new AtaqueFueraDeRangoException();
	}
	
	public void recibirAtaque(Algoformer atacante) throws FuegoAmigoException{
		if (atacante.equipo == equipo){
			throw new FuegoAmigoException();
		}
		vida=vida-(atacante.getPoder());
		if (vida<=0){
			//MATAR ALGOFORMER
		}
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
	public void setEquipo(int unEquipo){
		this.equipo=unEquipo;
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
	
	public void aplicarEfectoTerrenoRocoso(){
		//this.actual.aplicarEfectoTerrenoRocoso;
	}
	public void aplicarEfectoTerrenoPantano(){
		//this.actual.aplicarEfectoTerrenoPantano;
	}
	public void aplicarEfectoTerrenoEspinas(){
		this.actual.aplicarEfectoTerrenoEspinas( this );
	}
	public void aplicarEfectoTerrenoNube(){
		//this.actual.aplicarEfectoTerrenoNube;
	}
	public void aplicarEfectoTerrenoNebulosa(){
		//this.actual.aplicarEfectoTerrenoNebulosa;
	}
	public void aplicarEfectoTerrenoTormenta(){
		//this.actual.aplicarEfectoTerrenoTormenta;
	}
	

}
