package fiuba.algo3.modelo.Jugabilidad.Jugador;


import fiuba.algo3.modelo.Coordenada;
import fiuba.algo3.modelo.Jugabilidad.*;
import fiuba.algo3.modelo.TipoEquipo;
import fiuba.algo3.modelo.algoformer.Algoformer;

import fiuba.algo3.modelo.tablero.Tablero;

import java.util.HashMap;


public class Jugador {

    private String nombre;
    private TipoEquipo equipo;
    private EstadoJugador estado;
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

    }

    private boolean existePersonaje (String nombre){
        return this.personajes.containsKey(nombre);
    }

    public Algoformer obtenerPersonaje (String nombre){

        if (!existePersonaje(nombre)) throw new PersonajeInexistenteException();
        return this.personajes.get(nombre);

    }

    public String obtenerNombre(){return this.nombre;}


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

    public void moverPersonaje(String personaje, Coordenada posicion, Tablero tablero) {

        this.estado.moverPersonaje(this.obtenerPersonaje(personaje), posicion, tablero);
    }

    public void atacarConPersonaje(String personaje, Coordenada posicion, Tablero tablero) {

        this.estado.atacarConPersonaje(this.obtenerPersonaje(personaje), posicion, tablero);
    }

    public void capturarChispa(String personaje, Coordenada posicion, Tablero tablero) {
        this.estado.capturarChispa(this.obtenerPersonaje(personaje), posicion, tablero);
    }

    public void tranformarPersonaje(String personaje, Coordenada posicion, Tablero tablero) {

        this.estado.tranformarPersonaje(this.obtenerPersonaje(personaje), posicion, tablero);
    }

    public void CombinarPersonaje(String personaje, Coordenada posicion, Tablero tablero) {


    }


}
