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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jose on 19/06/2016.
 */

//TODO esta clase puede ser que este demas... luego se ve.
public class Juego implements ObservableJuego {

    private List<ObservadorJuego> observadores = new ArrayList<ObservadorJuego>();
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
    }

    public Juego() {


        this.terminada = true;
    }

    public void iniciar (){


        this.terminada = false;

        //****************************************************************************//
        // creo los jugadores
        //***************************************************************************//
        this.jugadorUno = new Jugador ("Diego", TipoEquipo.AUTOBOTS);
        this.jugadorDos = new Jugador ("Maradona", TipoEquipo.DECEPTICONS);

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
        //agrego los Algoformers a cada jugador
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

        jugadorUno.setJuego(this);
        jugadorDos.setJuego(this);

        //arranco la partida
        this.pasarTurno();

    }


    private void posicionarAlgoformers( ){

        Coordenada c1 = new Coordenada( 0, 1 );
        Coordenada c2 = new Coordenada( 0, 2 );
        Coordenada c3 = new Coordenada( 0, 3 );

        Coordenada c4 = new Coordenada( this.unTablero.getAncho()-1, 1 );
        Coordenada c5 = new Coordenada( this.unTablero.getAncho()-1, 2 );
        Coordenada c6 = new Coordenada( this.unTablero.getAncho()-1, 3 );

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
  //  public List<Bonus> getListaBonus (){return this.unaPartida.getListaDeBonus();}


    public void pasarTurno(){

       if (terminada) { throw new JuegoNoIniciadoException();};

        this.turno.getTurno().finTurno();
        this.turno.siguiente().inicioTurno();

    }

    public void finalizar (){ this.terminada= true;}

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


/*

public class Partida {

    private Turno turno;
    private Jugador player1, player2;
    private Tablero tablero;
    private ChispaSuprema chispa;
    private List<ObservadorPartida> observadores = new ArrayList<ObservadorPartida>();
    private boolean terminada;

    public Partida() {
    } //for test

    //todo si los jugadores no tienen 3 algoformers que lance exepcion
    public Partida(Jugador j1, Jugador j2, Tablero tablero) {

    }

    public void pasarTurno() {


        if (finalizada()) throw new PartidaFffffinalizadaException();

        this.turno.getTurno().finTurno();
        this.turno.siguiente().inicioTurno();


    }

    ;


    private boolean finalizada() {
        return this.terminada;
    }


    public void jugadorSinPersonajes(Jugador unJugador) {

        finalizar();  // la marco finalizada
        notificarJugadorSinPersonajes(unJugador);


    }

    private void finalizar() {
        this.terminada = true;
    }

    private void notificarJugadorSinPersonajes(Jugador unJugador) {

        for (ObservadorPartida unObservador : this.observadores) {
            unObservador.jugadorSinPersonajes(unJugador);
        }

    }

    //TODO EN UNO le avisas quien gana y en otro quien pierde..... revisar
    // refac repetis codifo con jugadorSinPersonajes


    public void chispaCapturada(Jugador unJugador) {

        finalizar();  // la marco finalizada
        notificarChispaCapturada(unJugador);

    }

    private void notificarChispaCapturada(Jugador unJugador) {

        for (ObservadorPartida unObservador : this.observadores) {
            unObservador.capturaronChispaSuprema(unJugador);
        }

    }

}



 */