package fiuba.algo3.eventos;

import fiuba.algo3.modelo.Jugabilidad.Jugador.FaltanPersonajesParaFusionException;
import fiuba.algo3.modelo.Jugabilidad.Jugador.NoSePuedeFusionarMasDeUnaVezException;
import fiuba.algo3.modelo.algoformer.Algoformer;
import fiuba.algo3.modelo.algoformer.AtaqueFueraDeRangoException;
import fiuba.algo3.modelo.algoformer.FuegoAmigoException;
import fiuba.algo3.vista.ContenedorPrincipal;
import fiuba.algo3.vista.MenuInferior;
import fiuba.algo3.vista.TableroVistaControlador;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class SeleccionAlgoformerHandler implements EventHandler<MouseEvent> {

	Algoformer algoformer;

	public SeleccionAlgoformerHandler(Algoformer unAlgoformer) {
		algoformer = unAlgoformer;
	}

	@Override
	public void handle(MouseEvent event) {
		
		MouseButton button = event.getButton();
		if(button==MouseButton.SECONDARY){ //Observar
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Observar Algoformer");
			alert.setHeaderText(algoformer.getNombre() + " (Forma " + algoformer.getNombreForma() + ")");
			alert.setContentText("Vida actual: " + algoformer.getVida() + "\nPoder: " + algoformer.getPoder()
					+ " (Poder Base: " + algoformer.getPoderBase() + ")\nVelocidad: " + algoformer.getVelocidad()
					+ " (Velocidad Base: " + algoformer.getVelocidadBase() + ")\nRango de ataque: "
					+ algoformer.getRango());
			alert.showAndWait();
		}
		else {
			
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
					algoformer.getJugador().combinarPersonaje();
					MenuInferior.log.appendText("\n" + algoformer.getNombre() + " combina " + algoformer.getNombreForma());
					ContenedorPrincipal.juego.pasarTurno();
					TableroVistaControlador.seleccionado = false;
					
				} catch (FaltanPersonajesParaFusionException e) {
					MenuInferior.log.appendText("\nFaltan personajes para la fusion!");
				} catch (NoSePuedeFusionarMasDeUnaVezException e) {

					MenuInferior.log.appendText("\nSolo se puede utilizar la fusion una vez");
				}
			} else {
				MenuInferior.log.appendText("\nEste algoformer no es tuyo");
			}


		}
	}
}
}
