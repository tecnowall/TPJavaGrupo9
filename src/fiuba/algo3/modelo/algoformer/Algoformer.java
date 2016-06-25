package fiuba.algo3.modelo.algoformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fiuba.algo3.modelo.Coordenada;
import fiuba.algo3.modelo.Jugabilidad.Jugador.Jugador;
import fiuba.algo3.modelo.TipoEquipo;
import fiuba.algo3.modelo.bonus.Bonus;
import fiuba.algo3.modelo.bonus.BonusID;
import fiuba.algo3.modelo.bonus.TipoEfecto;
import fiuba.algo3.modelo.movimiento.Movimiento;
import fiuba.algo3.modelo.observadores.ObservableAlgoformer;
import fiuba.algo3.modelo.observadores.ObservadorAlgoformer;
import fiuba.algo3.modelo.tablero.Capturable;
import fiuba.algo3.modelo.tablero.Tablero;
import fiuba.algo3.modelo.tablero.Ubicable;

public class Algoformer implements Ubicable, Fusionable, ObservableAlgoformer{
	private Tablero tablero;
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
	protected Jugador jugador;
	protected List<ObservadorAlgoformer> observadores;
	
	public Algoformer(){
	}
	
	public Algoformer( String nombre ){
		this.nombre = nombre;
		this.observadores = new ArrayList<ObservadorAlgoformer>();
	}
	
	public Algoformer( String nombre, int vida, Forma humanoide, Forma alterna ){
		this.nombre = nombre;
		this.vida = vida;
		this.actual = humanoide;
		this.alterna = alterna;
		this.buffs = new HashMap<BonusID, Bonus>();
		this.observadores = new ArrayList<ObservadorAlgoformer>();
	}
	
	public Algoformer( String nombre, int vida, Forma humanoide, Forma alterna, Tablero tablero ){
		this.nombre = nombre;
		this.vida = vida;
		this.actual = humanoide;
		this.alterna = alterna;
		this.buffs = new HashMap<BonusID, Bonus>();
		this.observadores = new ArrayList<ObservadorAlgoformer>();
		setTablero( tablero );
	}
	
	public void nuevoBonus( Bonus unBonus ){
		unBonus.setAfectado( this );
		this.buffs.put( unBonus.getBonusID(), unBonus );
		//Notifica
		for(ObservadorAlgoformer unObservador : this.observadores){
			unObservador.seConsumioUnBonus(posicion);
		}
	}
	
	public void perderBonus( BonusID ID ){
		this.buffs.remove( ID );
	}
	
	public int cantidadBonus(){
		return this.buffs.size();
	}
	
	public void transformar(){
		Forma temp = actual;
		actual = alterna;
		alterna = temp;
	}
	
	public void ubicar( Coordenada unaCoordenada ){
		
		Coordenada original = this.posicion;
		this.posicion = unaCoordenada;
		

		for(ObservadorAlgoformer unObservador : this.observadores){
			unObservador.huboUnMovimiento(this,original);
		}
	}
	
	public boolean movimientoValido( Coordenada destino ){
		return  ( Math.abs ( destino.getX() - this.posicion.getX()) <= ( this.getVelocidad() ) ) && ( Math.abs (  destino.getY()- this.posicion.getY()) <= ( this.getVelocidad() ) ) ;
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
		if ( vida < 1 ) morir();
	}
	
	public Movimiento getMovimiento(){
		return this.movimiento;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	public String getNombreForma(){
		return actual.getNombre();
	}
	
	public int getVida(){
		return this.vida;
	}
	
	public Coordenada getPosicion(){
		return this.posicion;
	}
	
	public Tablero getTablero() {
		return tablero;
	}

	public void setTablero(Tablero tablero) {
		this.tablero = tablero;
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
			bonus.aplicarEfectos( this, tipo );
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

	@Override
	public void iniciarFusion(Algoformer parte1, Algoformer parte2, Algoformer parte3) {
		// TODO Auto-generated method stub
	}

	@Override
	public Algofusion completarFusion() {
		return null;
	}

	//TODO REVISAR ESTO QUE PINCHA al llamarlo desde  juego.pasarturno
	public void inicioTurno(){

			Bonus bonus;
			for ( Map.Entry< BonusID, Bonus> elemento : buffs.entrySet() ){
			bonus = elemento.getValue();
			bonus.consumirDuracion();
		}

	}

	protected void salirDelTablero(){
		this.tablero.sacar( this.posicion );
	}
	
	public void morir(){
		salirDelTablero();
		this.jugador.murioUnPersonaje(this);
		for(ObservadorAlgoformer unObservador : this.observadores){
			unObservador.fallecioAlgoformer(this);
		}//Notificar muerte
	}



	protected void  notificarFusionAObservadores (Algofusion unAlgoformer){

		//TODO aca la vista va a tener que retirar a los 3 (se los pide a funsion) y poner a fusion a la vista
		for (ObservadorAlgoformer unObservador : this.observadores){ unObservador.huboUnaFusion(unAlgoformer);}

	}


	public void agregarJugador (Jugador unJugador) {
		if ( unJugador != null ){
		if (this.jugador !=null) {throw new YaPoseoJugadorException();}
		this.jugador = unJugador;	
		}
	}

	//metodo para test
	public Jugador  getJugador(){return this.jugador;}

	@Override
	public void suscribir(ObservadorAlgoformer nuevoObservador) {
		this.observadores.add( nuevoObservador );		
	}

	@Override
	public void desSuscribir(ObservadorAlgoformer nuevoObservador) {		
		if ( this.observadores.contains( nuevoObservador ) ){
			this.observadores.remove( this.observadores.indexOf( nuevoObservador ) );
		}
		
	}
//TODO
  protected void setPosicion (Coordenada coordenada){

	  this.posicion= coordenada;


  }


}
