package fiuba.algo3.eventos;

import fiuba.algo3.modelo.Jugabilidad.Jugador.FaltanPersonajesParaFusionException;
import fiuba.algo3.modelo.Jugabilidad.Jugador.PersonajeNoSeleccionadoException;
import fiuba.algo3.modelo.algoformer.Algoformer;
import fiuba.algo3.modelo.algoformer.AtaqueFueraDeRangoException;
import fiuba.algo3.modelo.algoformer.FuegoAmigoException;
import fiuba.algo3.vista.ContenedorPrincipal;
import fiuba.algo3.vista.MenuInferior;
import fiuba.algo3.vista.TableroVistaControlador;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.control.Alert;

public class SeleccionAlgoformerHandler implements EventHandler<ActionEvent> {

	Algoformer algoformer;

	public SeleccionAlgoformerHandler(Algoformer unAlgoformer) {
		algoformer = unAlgoformer;
	}

	@Override
	public void handle(ActionEvent event) {

		if ("Observar" == MenuInferior.selecOpciones.getSelectionModel().getSelectedItem().toString()) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Observar Algoformer");
			alert.setHeaderText(algoformer.getNombre() + " (Forma " + algoformer.getNombreForma() + ")");
			alert.setContentText("Vida actual: " + algoformer.getVida() + "\nPoder: " + algoformer.getPoder()
					+ " (Poder Base: " + algoformer.getPoderBase() + ")\nVelocidad: " + algoformer.getVelocidad()
					+ " (Velocidad Base: " + algoformer.getVelocidadBase() + ")\nRango de ataque: "
					+ algoformer.getRango());
			alert.showAndWait();
		}
		if ("Mover" == MenuInferior.selecOpciones.getSelectionModel().getSelectedItem().toString()) {
			if (TableroVistaControlador.seleccionado == false) {
				if (algoformer.getJugador().getEstado() == "activo") {
					algoformer.getJugador().seleccionarPersonaje(algoformer.getNombre());
					MenuInferior.log.appendText("\nAlgoformer seleccionado! Elija la posicion objetivo");
					TableroVistaControlador.seleccionado = true;
					TableroVistaControlador.algoformerSeleccionado = algoformer;
				} else {
					MenuInferior.log.appendText("\nEste algoformer no es tuyo");
				}

			} else {

				MenuInferior.log.appendText("\nHay otro algoformer en esta ubicacion, movimiento no valido");
				TableroVistaControlador.seleccionado = false;

			} //el movimiento se realiza cuando hay un algoformer seleccionado y se hace click en un boton vacio
			//TODO MOVIMIENTO
		}
		if ("Atacar" == MenuInferior.selecOpciones.getSelectionModel().getSelectedItem().toString()) {
			//TODO ATAQUE
			if (TableroVistaControlador.seleccionado == false) {
				if (algoformer.getJugador().getEstado() == "activo") {

					MenuInferior.log.appendText("\nAlgoformer seleccionado! Elija blanco de ataque");
					TableroVistaControlador.seleccionado = true;
					algoformer.getJugador().seleccionarPersonaje(algoformer.getNombre());
					TableroVistaControlador.algoformerSeleccionado = algoformer;

				} else {

					MenuInferior.log.appendText("\nEste algoformer no es tuyo");
				}

			} else { //Seleccionado=true
				//ATACAR
				try {
					TableroVistaControlador.algoformerSeleccionado.getJugador().atacar(algoformer.getPosicion(), ContenedorPrincipal.juego.getTablero());

					MenuInferior.log.appendText("\n" + TableroVistaControlador.algoformerSeleccionado.getNombre() + " ataca a " + algoformer.getNombre());
					ContenedorPrincipal.juego.pasarTurno();
					TableroVistaControlador.seleccionado = false;
				} catch (FuegoAmigoException e) {
					MenuInferior.log.appendText("\nFuego amigo!");

				} catch (AtaqueFueraDeRangoException e) {

					MenuInferior.log.appendText("\nAtaque fuera de rango");
				}

				TableroVistaControlador.seleccionado = false;
				//atrapar excepciones DONE
			}
		}
		if ("Transformar" == MenuInferior.selecOpciones.getSelectionModel().getSelectedItem().toString()) {
			if (algoformer.getJugador().getEstado() == "activo") {
				algoformer.transformar();
				MenuInferior.log.appendText("\n" + algoformer.getNombre() + " cambia de forma a " + algoformer.getNombreForma());
				ContenedorPrincipal.juego.pasarTurno();
				TableroVistaControlador.seleccionado = false;
			} else {
				MenuInferior.log.appendText("\nEste algoformer no es tuyo");
			}
		}

		//TODO este metodo hay que armarlo bien con las excepciones

	     if ("Fusionar" == MenuInferior.selecOpciones.getSelectionModel().getSelectedItem().toString()) {
			if (algoformer.getJugador().getEstado() == "activo") {

				try {
					algoformer.getJugador().seleccionarPersonaje(algoformer.getNombre());
					algoformer.getJugador().combinarPersonaje(algoformer.getNombre());
					MenuInferior.log.appendText("\n" + algoformer.getNombre() + " combina " + algoformer.getNombreForma());
					ContenedorPrincipal.juego.pasarTurno();
					TableroVistaControlador.seleccionado = false;
					
				} catch (FaltanPersonajesParaFusionException e) {
					MenuInferior.log.appendText("\nFaltan personajes para la fusion!");
				} catch (PersonajeNoSeleccionadoException e) {

					MenuInferior.log.appendText("\nNo hay algoformer seleccionado");
				}
			} else {
				MenuInferior.log.appendText("\nEste algoformer no es tuyo");
			}


		}
	}
}
