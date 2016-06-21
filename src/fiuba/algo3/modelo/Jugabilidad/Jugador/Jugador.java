package fiuba.algo3.modelo.Jugabilidad.Jugador;


import fiuba.algo3.modelo.Coordenada;
import fiuba.algo3.modelo.Jugabilidad.Juego.Partida;
import fiuba.algo3.modelo.TipoEquipo;
import fiuba.algo3.modelo.algoformer.Algoformer;
import fiuba.algo3.modelo.algoformer.Algofusion;
import fiuba.algo3.modelo.tablero.Tablero;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class Jugador {

    private String nombre;
    private TipoEquipo equipo;
    private EstadoJugador estado;
    private Algoformer personajeActivo;
    private HashMap<String, Algoformer> personajes;
    private Partida partida;

    public Jugador(String nombre, TipoEquipo equipo) {
        this.nombre = nombre;
        this.equipo = equipo;
        this.estado = new EstadoJugadorEsperando();
        this.personajes = new HashMap<String,Algoformer>();
    }
    public String obtenerNombre(){return this.nombre;}
    public TipoEquipo obtenerEquipo(){return this.equipo;}
    public String getEstado (){return estado.getEstado();} //TODO for test
    public Algoformer getJugadorActivo(){return this.personajeActivo;} //todo for test

    public void agregarPersonaje(Algoformer unPersonaje)  {

        if ( unPersonaje.getEquipo() != this.equipo) {
            throw new PersonajeDeOtroEquipoException();
        }

        if (existePersonaje(unPersonaje.getNombre())){
            throw new PersonajeNombreDuplicadoExeptions();
        }

        personajes.put(unPersonaje.getNombre(), unPersonaje);
        unPersonaje.agregarJugador(this);

    }


    //
    public void agregarPersonaje(Algofusion personajeFusionado)  {

        if ( personajeFusionado.getEquipo() != this.equipo) {
            throw new PersonajeDeOtroEquipoException();
        }

        if (existePersonaje(personajeFusionado.getNombre())){
            throw new PersonajeNombreDuplicadoExeptions();
        }

        personajes.put(personajeFusionado.getNombre(), personajeFusionado);
        personajeFusionado.agregarJugador(this);

        //TODO quita a los fusionados
        for (Algoformer algoformer : personajeFusionado.getPartes()){ this.eliminarPersonaje(algoformer.getNombre());}


    }

    private boolean existePersonaje (String nombreDelPersonaje){
        return this.personajes.containsKey(nombreDelPersonaje);
    }

    public Algoformer obtenerPersonaje (String nombreDelPersonaje){

        if (!existePersonaje(nombreDelPersonaje)) throw new PersonajeInexistenteException();
        return this.personajes.get(nombreDelPersonaje);

    }

    public ArrayList <String > obtenerNombresDePersonajes(){

        Iterator it = personajes.entrySet().iterator();
        ArrayList <String> listaDeNombres = new ArrayList<String>();
        while (it.hasNext()) {
            Map.Entry e = (Map.Entry)it.next();
            listaDeNombres.add ((String)e.getKey());
        }
        return listaDeNombres;
    }



    private void notificarFinDeTurno(){
        //TODO
      //notificar a los algoformer de un nuevo turno

        Iterator it = personajes.entrySet().iterator();
        while (it.hasNext()) {
            ((Algoformer)(((Map.Entry)it.next()).getValue())).finTurno();
        }

    };

    public void inicioTurno(){
        this.estado= new EstadoJugadorActivo();

    };

    public void finTurno(){
        this.estado= new EstadoJugadorEsperando();
        notificarFinDeTurno();

    };

    public void seleccionarPersonaje (String nombreDelPersonaje){

        if (!existePersonaje(nombreDelPersonaje)) throw new PersonajeInexistenteException();

        personajeActivo= this.personajes.get(nombreDelPersonaje);

    }

    //todo debe lanzarexepcion
    public void moverPersonaje( Coordenada posicion, Tablero tablero) {

        this.estado.moverPersonaje(this.personajeActivo, posicion, tablero);
    }

    public void atacar( Coordenada posicion, Tablero tablero) {

        this.estado.atacar((this.personajeActivo), posicion, tablero);
    }
////////-------------------------------------------------------------------
//TODO  hace un private get personaje y que lance excepcion si no hay activo
    public void tranformarPersonaje( Coordenada posicion, Tablero tablero) {

        this.estado.tranformarPersonaje(this.personajeActivo, posicion, tablero);
    }

    //TODO
    public void CombinarPersonaje(String personaje, Coordenada posicion, Tablero tablero) {


    }

    public void murioUnPersonaje (Algoformer unAlgoformer){

        this.eliminarPersonaje (unAlgoformer.getNombre());

        if (!this.tenesPersonajes())  this.sinPersonajes();

    }

    public void sinPersonajes(){

        if (this.partida==null) throw new JugadorSinPartidaException();

        this.partida.jugadorSinPersonajes(this);

    }

    private void eliminarPersonaje (String nombreDelPerosnaje){

        //verificar existencia del personaje, sino excepcion
        if (!existePersonaje(nombreDelPerosnaje)) throw new PersonajeInexistenteException();

        // eliminar personaje
        personajes.remove(nombreDelPerosnaje);

    }
    //TODO refactor.. que devuelva lista de algoformers no esta estructura
    public HashMap<String,Algoformer> getAllPersonajes(){
    	return personajes;
    }

    public boolean tenesPersonajes( ){ return (!this.personajes.isEmpty()); }

    public void setPartida(Partida unaPartida){

        this.partida=unaPartida;

    }


    
}
