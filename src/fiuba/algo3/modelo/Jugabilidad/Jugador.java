package fiuba.algo3.modelo.Jugabilidad;


import fiuba.algo3.modelo.Algoformer;
import fiuba.algo3.modelo.TipoEquipo;


import java.util.HashMap;

/**
 * Created by jose on 09/06/2016.
 */
public class Jugador {

    private String nombre;
    private TipoEquipo equipo;
private HashMap<String, Algoformer> personajes = new HashMap<String,Algoformer>();

    public Jugador(String nombre, TipoEquipo equipo) {
        this.nombre = nombre;
        this.equipo = equipo;
    }

    public void agregarPersonaje(Algoformer unPersonaje)  {

        if ( unPersonaje.getEquipo() != this.equipo) {
            throw new PersonajeDeOtroEquipoException ();
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

        if (!existePersonaje(nombre)) throw new PersonajeInexistenteException ();
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

    public void aregarControlador (){
        // TODO objeto controlador, harcodeo para que pase y dues que obtenga del conrolador posta
         };
}