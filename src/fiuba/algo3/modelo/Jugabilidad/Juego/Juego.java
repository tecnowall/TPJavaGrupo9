package fiuba.algo3.modelo.Jugabilidad.Juego;

import fiuba.algo3.modelo.ChispaSuprema;
import fiuba.algo3.modelo.Coordenada;
import fiuba.algo3.modelo.Jugabilidad.Jugador.Jugador;
import fiuba.algo3.modelo.TipoEquipo;
import fiuba.algo3.modelo.algoformer.Autobot;
import fiuba.algo3.modelo.algoformer.Decepticon;
import fiuba.algo3.modelo.bonus.Bonus;
import fiuba.algo3.modelo.bonus.BonusDobleCanion;
import fiuba.algo3.modelo.observadores.ObservableJuego;
import fiuba.algo3.modelo.observadores.ObservadorJuego;
import fiuba.algo3.modelo.tablero.Tablero;
import fiuba.algo3.modelo.terreno.*;

import java.util.ArrayList;
import java.util.List;


//TODO esta clase puede ser que este demas... luego se ve.
public class Juego implements ObservableJuego {

    private List<ObservadorJuego> observadores;
    private Jugador jugadorUno, jugadorDos;
    private Tablero unTablero;
    private int altoTablero;
    private int anchoTablero;
    //todo revisar
    private Turno turno;
    private ChispaSuprema chispa;
    private boolean terminada;


    //crea los jugadores, el tablero y los algoformers.
    public Juego(int alto, int ancho) {

        this.altoTablero = alto;
        this.anchoTablero = ancho;
        this.terminada = true;
        this.observadores = new ArrayList<ObservadorJuego>();
    }

    public Juego() {

        this.observadores = new ArrayList<ObservadorJuego>();
        this.terminada = true;
    }

    public void iniciar (){


        this.terminada = false;

        //****************************************************************************//
        // creo los jugadores
        //***************************************************************************//
        this.jugadorUno = new Jugador ("Jugador 1", TipoEquipo.AUTOBOTS);
        this.jugadorDos = new Jugador ("Jugador 2", TipoEquipo.DECEPTICONS);


        //****************************************************************************//
        // creo un tablero
        this.unTablero = new Tablero(this.altoTablero,this.anchoTablero);
        //****************************************************************************//
        //****************************************************************************//
        // creo los algoformers - Autobots
        //****************************************************************************//
        Autobot a1=Autobot.getOptimus();
        a1.setTablero(unTablero);
        a1.setEquipo(TipoEquipo.AUTOBOTS);

        Autobot a2=Autobot.getBumblebee();
        a2.setTablero(unTablero);
        a2.setEquipo(TipoEquipo.AUTOBOTS);

        Autobot a3=Autobot.getRatchet();
        a3.setTablero(unTablero);
        a3.setEquipo(TipoEquipo.AUTOBOTS);

        //Decepticons
        Decepticon a4=Decepticon.getMegatron();
        a3.setTablero(unTablero);
        a4.setEquipo(TipoEquipo.DECEPTICONS);


        Decepticon a5=Decepticon.getBonecrusher();
        a5.setTablero(unTablero);
        a5.setEquipo(TipoEquipo.DECEPTICONS);


        Decepticon a6=Decepticon.getFrenzy();
        a6.setTablero(unTablero);
        a6.setEquipo(TipoEquipo.DECEPTICONS);



        //****************************************************************************//
       // agrego los Algoformers a cada jugador
        this.jugadorUno.agregarPersonaje(a1);         this.jugadorUno.agregarPersonaje(a2);         this.jugadorUno.agregarPersonaje(a3);
      this.jugadorDos.agregarPersonaje(a4); this.jugadorDos.agregarPersonaje(a5);this.jugadorDos.agregarPersonaje(a6);




        //****************************************************************************//
        this.turno= new Turno(this.jugadorUno,this.jugadorDos);



        //TODO escribirlo mejor
        int x = this.unTablero.getAncho();   x= (x-1) /2;
        int y = this.unTablero.getAlto();   y= (y-1) /2;


        this.chispa = new ChispaSuprema();
        Coordenada cChispa = new Coordenada( x, y );
        this.posicionarChispaSuprema(cChispa);
        this.posicionarAlgoformers();
        this.posicionarTerrenos();

        jugadorUno.setJuego(this);
        jugadorDos.setJuego(this);

        //arranco la partida
        this.pasarTurno();

    }


    private void posicionarAlgoformers( ){

        Coordenada c1 = new Coordenada( 0, 2 );
        Coordenada c2 = new Coordenada( 0, 4 );
        Coordenada c3 = new Coordenada( 0, 6 );

        Coordenada c4 = new Coordenada( this.unTablero.getAncho()-1, 2 );
        Coordenada c5 = new Coordenada( this.unTablero.getAncho()-1, 4 );
        Coordenada c6 = new Coordenada( this.unTablero.getAncho()-1, 6 );




        //TODO refactoring..., crear un iterador
        // this.player1.obtenerPersonajes
        //
        ArrayList<String> lista1= this.jugadorUno.obtenerNombresDePersonajes();

        this.unTablero.poner (this.jugadorUno.obtenerPersonaje(lista1.get(0)),c1);
        this.jugadorUno.obtenerPersonaje(lista1.get(0)).ubicar(c1);
        this.unTablero.poner (this.jugadorUno.obtenerPersonaje(lista1.get(1)),c2);
        this.jugadorUno.obtenerPersonaje(lista1.get(1)).ubicar(c2);
        this.unTablero.poner (this.jugadorUno.obtenerPersonaje(lista1.get(2)),c3);
        this.jugadorUno.obtenerPersonaje(lista1.get(2)).ubicar(c3);


        ArrayList<String> lista2= this.jugadorDos.obtenerNombresDePersonajes();
        this.unTablero.poner (this.jugadorDos.obtenerPersonaje(lista2.get(0)),c4);
        this.jugadorDos.obtenerPersonaje(lista2.get(0)).ubicar(c4);
        this.unTablero.poner (this.jugadorDos.obtenerPersonaje(lista2.get(1)),c5);
        this.jugadorDos.obtenerPersonaje(lista2.get(1)).ubicar(c5);
        this.unTablero.poner (this.jugadorDos.obtenerPersonaje(lista2.get(2)),c6);
        this.jugadorDos.obtenerPersonaje(lista2.get(2)).ubicar(c6);

    }

    private void posicionarChispaSuprema (Coordenada posicion){

        this.unTablero.poner(this.chispa, posicion);
        this.chispa.setPosicion(posicion);
    }
    
    private void posicionarTerrenos(){
    	//TODO
    	//Por default es rocoso
    	Rocoso rocoso = new Rocoso();
    	Pantano pantano = new Pantano();
    	NebulosaDeAndromeda andromeda = new NebulosaDeAndromeda();
    	TormentaPsionica psi = new TormentaPsionica();
    	
    	Coordenada c1 = new Coordenada(6,2);
    	Coordenada c2 = new Coordenada(2,2);
    	Coordenada c3 = new Coordenada(4,6);
    	
    	this.unTablero.setTerreno(c1, psi);
    	this.unTablero.setTerreno(c2, pantano);
    	this.unTablero.setTerreno(c3, andromeda);
    	
    	

    	
    }

    //TODO implementar esto!
    public List<Bonus> getListaDeBonus(){


        List<Bonus> lista;
        lista = new ArrayList<>();
        Bonus unBonus = new BonusDobleCanion();
        lista.add(unBonus);
        return lista;

    };

    public Jugador  getJugadorUno() {return this.jugadorUno;}

    public Jugador  getJugadorDos() {return this.jugadorDos;}

    public Tablero  getTablero() {return this.unTablero;}

    public ChispaSuprema getChispaSuperma (){return this.chispa;}

    public void pasarTurno(){

       if (this.terminada) { throw new JuegoNoIniciadoException();};

        this.turno.getTurnoActual().finTurno();
        this.turno.getTurnoSiguiente().inicioTurno();
        notificarObsevadoresTurnoDeJugador(this.turno.getTurnoActual());

    }

    public void finalizar (){ this.terminada= true;}

    private void notificarObsevadoresTurnoDeJugador (Jugador jugadorActivo){

        for (ObservadorJuego unObservador : this.observadores){ unObservador.esElTurnoDelJugador(jugadorActivo);}

    }

    //todo
 private void notificarObsevadores (Jugador playerWin){

     for (ObservadorJuego unObservador : this.observadores){ unObservador.finalizoJuego(playerWin);}

 }

    @Override
    public void suscribir(ObservadorJuego nuevoObservador) {
        observadores.add(nuevoObservador);

    }

    @Override
    public void desSuscribir(ObservadorJuego unObservador) {

        if (this.observadores.contains(unObservador)) this.observadores.remove(unObservador);

    }



    public void jugadorSinPersonajes(Jugador unJugador) {

        this.finalizar();
        //notificarObsevadores(unJugador); //todo  notificar a observadores  pero hay que pasarle el ganador


    }


    public void capturaronChispaSuprema(Jugador unJugador) {

        this.finalizar();
        notificarObsevadores(unJugador);


    }

}

