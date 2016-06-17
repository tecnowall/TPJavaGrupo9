package fiuba.algo3.modelo.Jugabilidad.Jugador;


import fiuba.algo3.modelo.Coordenada;
import fiuba.algo3.modelo.Jugabilidad.*;
import fiuba.algo3.modelo.TipoEquipo;
import fiuba.algo3.modelo.algoformer.Algoformer;

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
    private HashMap<String, Algoformer> personajes = new HashMap<String,Algoformer>();


    public Jugador(String nombre, TipoEquipo equipo) {
        this.nombre = nombre;
        this.equipo = equipo;
        this.estado = new EstadoJugadorEsperando();
    }

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

    private boolean existePersonaje (String nombreDelPersonaje){
        return this.personajes.containsKey(nombreDelPersonaje);
    }

    public Algoformer obtenerPersonaje (String nombreDelPersonaje){

        if (!existePersonaje(nombreDelPersonaje)) throw new PersonajeInexistenteException();
        return this.personajes.get(nombreDelPersonaje);

    }
//TODO refacrçtttt
    public ArrayList <String > obtenerNombresDePersonajes(){

        Iterator it = personajes.entrySet().iterator();
        ArrayList listaDeNombre = new ArrayList<String>();
        while (it.hasNext()) {
            Map.Entry e = (Map.Entry)it.next();
            System.out.println(e.getKey());
            listaDeNombre.add (e.getKey());
        }
        return listaDeNombre;
    }


    public void hacerJugada(){
       /*
        TODO

        a jugador hay que agregarle una clase controlador

        elegir algoformer    this.controlador.getSelection()
        obtener orden        this.controlador.getOrder()
        ordenar al algofomer   Algoformer.do
        */

    }




    private void notificarTurno(){
        //TODO
      //notificar a los algoformer de un nuevo turno
    };

    public void inicioTurno(){
        this.estado= new EstadoJugadorActivo();
        notificarTurno();
    };
    public void finTurno(){
        this.estado= new EstadoJugadorEsperando();

    };

    public void seleccionarPersonaje (String nombreDelPersonaje){

        if (!existePersonaje(nombreDelPersonaje)) throw new PersonajeInexistenteException();
        personajeActivo= this.personajes.get(nombreDelPersonaje);

    }

    public void moverPersonaje( Coordenada posicion, Tablero tablero) {

        this.estado.moverPersonaje(this.personajeActivo, posicion, tablero);
    }

    public void atacar( Coordenada posicion, Tablero tablero) {

        this.estado.atacar((this.personajeActivo), posicion, tablero);
    }


//TODO  hace un private get personaje y que lance excepcion si no hay activo
    public void tranformarPersonaje( Coordenada posicion, Tablero tablero) {

        this.estado.tranformarPersonaje(this.personajeActivo, posicion, tablero);
    }

    //TODO
    public void CombinarPersonaje(String personaje, Coordenada posicion, Tablero tablero) {


    }

    public void murioUnPersonaje (Algoformer unAlgoformer){

        this.eliminarPersonaje (unAlgoformer.getNombre());

    }

    public void eliminarPersonaje (String nombreDelPerosnaje){

        //verificar existencia del personaje, sino excepcion
        if (!existePersonaje(nombreDelPerosnaje)) throw new PersonajeInexistenteException();

        // eliminar personaje
        personajes.remove(nombreDelPerosnaje);

    }

}
