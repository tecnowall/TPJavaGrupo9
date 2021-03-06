package fiuba.algo3.modelo.Jugabilidad.Jugador;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import fiuba.algo3.modelo.Coordenada;
import fiuba.algo3.modelo.TipoEquipo;
import fiuba.algo3.modelo.Jugabilidad.Juego.Juego;
import fiuba.algo3.modelo.algoformer.Algoformer;
import fiuba.algo3.modelo.algoformer.Algofusion;
import fiuba.algo3.modelo.tablero.Tablero;


public class Jugador {

    private String nombre;
    private TipoEquipo equipo;
    private EstadoJugador estado;
    private Algoformer personajeSeleccionado;
    private HashMap<String, Algoformer> personajes;
    private Juego juego;
    private int cantidadPersonajes;
    private boolean fusionUtilizada;
    private boolean fusionTerminada;

    public Jugador(String nombre, TipoEquipo equipo) {
        this.nombre = nombre;
        this.equipo = equipo;
        this.estado = new EstadoJugadorEsperando();
        this.personajes = new HashMap<String,Algoformer>();
        this.fusionUtilizada = false;
        this.fusionTerminada= false;

    }
    public String obtenerNombre(){return this.nombre;}
    public TipoEquipo obtenerEquipo(){return this.equipo;}
    public String getEstado (){return estado.getEstado();} //TODO for test
    public Algoformer getPersonajeSeleccionado(){return this.personajeSeleccionado;} //todo for test

    public void agregarPersonaje(Algoformer unPersonaje)  {

        if ( unPersonaje.getEquipo() != this.equipo) {
            throw new PersonajeDeOtroEquipoException();
        }

        if (existePersonaje(unPersonaje.getNombre())){
            throw new PersonajeNombreDuplicadoException();
        }

        personajes.put(unPersonaje.getNombre(), unPersonaje);
        unPersonaje.agregarJugador(this);

    }

    public void agregarPersonaje(Algofusion personajeFusionado)  {

        if ( personajeFusionado.getEquipo() != this.equipo) {
            throw new PersonajeDeOtroEquipoException();
        }

        if (existePersonaje(personajeFusionado.getNombre())){
            throw new PersonajeNombreDuplicadoException();
        }

        //TODO no se puede modificar el hashmap mientras esta siendo iterado
        personajes.put(personajeFusionado.getNombre(), personajeFusionado);
        personajeFusionado.agregarJugador(this);

       // quita a los fusionados
       for (Algoformer algoformer : personajeFusionado.getPartes()){ this.eliminarPersonaje(algoformer.getNombre());}
      this.fusionTerminada=true;

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


    public void inicioTurno(){
        this.estado= new EstadoJugadorActivo();

        //AVISO A ALGOFORMERS FIN DE TURNO
        Iterator it = personajes.entrySet().iterator();
        while (it.hasNext()) {
            ((Algoformer)(((Map.Entry)it.next()).getValue())).inicioTurno();
            if (this.fusionTerminada){

                this.fusionTerminada=false;
                break;
            }
        }

 };

    public void finTurno(){

        this.estado= new EstadoJugadorEsperando();

        Set entrySet = personajes.entrySet();




    };




    //****************************
    // Acciones ************
    //********************************
    public void seleccionarPersonaje (String nombreDelPersonaje){

        if (!existePersonaje(nombreDelPersonaje)) throw new PersonajeInexistenteException();

        personajeSeleccionado = this.personajes.get(nombreDelPersonaje);

    }

    public void moverPersonaje( Coordenada posicion, Tablero tablero) {

        if ( this.personajeSeleccionado ==null) throw new PersonajeNoSeleccionadoException();
        this.estado.moverPersonaje(this.personajeSeleccionado, posicion, tablero);
    }

    public void atacar( Coordenada posicion, Tablero tablero) {
        if ( this.personajeSeleccionado ==null) throw new PersonajeNoSeleccionadoException();
        this.estado.atacar((this.personajeSeleccionado), posicion, tablero);
    }

    public void tranformarPersonaje( Coordenada posicion, Tablero tablero) {
        if ( this.personajeSeleccionado ==null) throw new PersonajeNoSeleccionadoException();
        this.estado.tranformarPersonaje(this.personajeSeleccionado, posicion, tablero);
    }

    //TODO  ver si los algoformer tienen un tablero guardado por que sino no tiene sentido pasar tablero
    public void combinarPersonaje() {
        if ( this.personajeSeleccionado ==null) throw new PersonajeNoSeleccionadoException();
        if (fusionUtilizada) throw new NoSePuedeFusionarMasDeUnaVezException();
        if (this.cantidadPersonajes != this.personajes.size()) throw new FaltanPersonajesParaFusionException();


        this.estado.combinarPersonaje(this.personajeSeleccionado,this.getAllPersonajes());
        fusionUtilizada = true;
    }

    //***********************************

    public void murioUnPersonaje (Algoformer unAlgoformer){

        this.eliminarPersonaje (unAlgoformer.getNombre());
       if (!this.tenesPersonajes())  this.sinPersonajes();

    }

    public void sinPersonajes(){

        if (this.juego==null) throw new JugadorSinJuegoException();

        this.juego.jugadorSinPersonajes(this);

    }

    private void eliminarPersonaje (String nombreDelPersonaje){

       if (!existePersonaje(nombreDelPersonaje)) throw new PersonajeInexistenteException();
       if   (this.personajeSeleccionado == obtenerPersonaje(nombreDelPersonaje)) this.personajeSeleccionado= null;

        personajes.remove(nombreDelPersonaje);



    }

    public ArrayList<Algoformer> getAllPersonajes(){

        Iterator it = personajes.entrySet().iterator();
        ArrayList <Algoformer> listaDeAlgoformers = new ArrayList<Algoformer>();
        while (it.hasNext()) {
            Map.Entry e = (Map.Entry)it.next();
            listaDeAlgoformers.add ((Algoformer) e.getValue());
        }
        return listaDeAlgoformers;

    }

    public boolean tenesPersonajes( ){ return (!this.personajes.isEmpty()); }

    public void setJuego(Juego unJuego){

        this.juego=unJuego;

    }


    public void iniciaJuego (){

        this.cantidadPersonajes =  this.personajes.size();

    }

    public void captureChispaSuprema(){

        this.juego.capturaronChispaSuprema(this);

    }
}
