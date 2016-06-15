package fiuba.algo3.modelo.algoformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fiuba.algo3.modelo.Coordenada;
import fiuba.algo3.modelo.TipoEquipo;
import fiuba.algo3.modelo.bonus.Bonus;
import fiuba.algo3.modelo.bonus.BonusID;
import fiuba.algo3.modelo.bonus.TipoEfecto;
import fiuba.algo3.modelo.movimiento.Movimiento;
import fiuba.algo3.modelo.tablero.Capturable;
import fiuba.algo3.modelo.tablero.Tablero;
import fiuba.algo3.modelo.tablero.Ubicable;

public class Algoformer implements Ubicable{
	private Coordenada posicion;
	private String nombre;
	private int vida;
	private int poder;
	private int velocidad;
	private double armadura;
	private Forma alterna;
	private Forma actual;
	private TipoEquipo equipo;
	private Movimiento movimiento;
	private transient Map<BonusID, Bonus> buffs;
	
	public Algoformer(){
	}
	
	public Algoformer( String nombre ){
		this.nombre = nombre;
	}
	
	public Algoformer( String nombre, int vida, Forma humanoide, Forma alterna ){
		this.nombre = nombre;
		this.vida = vida;
		this.actual = humanoide;
		this.alterna = alterna;
		this.buffs = new HashMap<BonusID, Bonus>();
	}
	
	public void nuevoBonus( Bonus unBonus ){
		this.buffs.put( unBonus.getBonusID(), unBonus );
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
			movimiento = new Movimiento( unTablero );
			movimiento.generarCamino( this.posicion , destino );
			while ( !movimiento.terminado() ){
				movimiento.avanzar( this );
			}		
		}		
		else throw new MovimientoFueraDeRangoException();
	}
	
	public void terminarMovimiento(){
		this.movimiento.terminar();
	}
	
	public void gastarMovimientos( int gastados ){
		this.movimiento.reducir( gastados );
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
		recibirDanio( atacante.getPoder() );
	}
	
	public void recibirDanio( int danio ){
		int danioTotal = (int) ( danio - ( danio * getArmadura() / 100) );
		vida = vida - danioTotal;
	}
	
	public Movimiento getMovimiento(){
		return this.movimiento;
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
	
	public void setEquipo(TipoEquipo unEquipo){
		this.equipo=unEquipo;
	}
	
	private void actualizarAtributos( TipoEfecto tipo ){
		Bonus bonus;
		for ( Map.Entry< BonusID, Bonus> elemento : buffs.entrySet() ){
			bonus = elemento.getValue();
			bonus.aplicar( this, tipo );
		}	
	}
	
	public int getPoder() {
		setPoder( getPoderBase() );
		actualizarAtributos( TipoEfecto.PODER );
		return this.poder;
	}
	
	public int getVelocidad(){
		setVelocidad( getVelocidadBase() );
		actualizarAtributos( TipoEfecto.VELOCIDAD );
		return this.velocidad;
	}
	
	public double getArmadura(){
		setArmadura( getArmaduraBase() );
		actualizarAtributos( TipoEfecto.ARMADURA );
		return this.armadura;
	}
	
	public int getPoderBase(){
		return actual.getPoder();
	}
	
	public int getPoderAnterior(){
		return this.poder;
	}
	
	public void setPoder( int poder ){
		this.poder = poder;
	}
	
	public void setPoderBase(int unPoder){
		actual.setPoder(unPoder);
	}

	public void setVelocidad( int velocidad ){
		this.velocidad = velocidad;
	}
	
	public void setVelocidadBase( int velocidad ){
		actual.setVelocidad( velocidad );
	}
	
	public int getVelocidadAnterior(){
		return this.velocidad;
	}
	
	public int getVelocidadBase() {
		return actual.getVelocidad();
	}
	
	public double getArmaduraBase() {
		return actual.getArmadura();
	}

	public void setArmadura( double armadura ) {
		this.armadura = armadura;
	}

	public void setArmaduraBase( double armadura ) {
		actual.setArmadura( armadura );
	}

	public double getArmaduraAnterior(){
		return this.armadura;
	}
	
	public TipoEquipo getEquipo(){

		return this.equipo;
	}

	public int getRango() {
		return actual.getRango();
	}
	
	public void aplicarEfectoTerrenoRocoso(){
		//this.actual.aplicarEfectoTerrenoRocoso;
	}
	public void aplicarEfectoTerrenoPantano(){
		this.actual.aplicarEfectoTerrenoPantano( this );
	}
	public void aplicarEfectoTerrenoEspinas(){
		this.actual.aplicarEfectoTerrenoEspinas( this );
	}
	public void aplicarEfectoTerrenoNube(){
		//this.actual.aplicarEfectoTerrenoNube;
	}
	public void aplicarEfectoTerrenoNebulosa(){
		this.actual.aplicarEfectoTerrenoNebulosa( this );
	}
	public void aplicarEfectoTerrenoTormenta(){
		this.actual.aplicarEfectoTerrenoTormenta( this );
	}

	@Override
	public void capturar( Capturable unCapturable ) {
		unCapturable.serCapturado( this );		
	}


}
