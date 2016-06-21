package fiuba.algo3.eventos;

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
		algoformer=unAlgoformer;
	}
	@Override
	public void handle(ActionEvent event) {
		
		if ("Observar"==MenuInferior.selecOpciones.getSelectionModel().getSelectedItem().toString()){
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Algoformer");
			alert.setHeaderText("Algoformer seleccionado: " + algoformer.getNombre());
			alert.setContentText("Vida actual: " + algoformer.getVida() + "\nPoder: " + algoformer.getPoder()
			+ " (Poder Base: " + algoformer.getPoderBase() + ")\nVelocidad: "+ algoformer.getVelocidad()
			+ " (Velocidad Base: " + algoformer.getVelocidadBase() + ")");
	
			alert.showAndWait();
		}
		if ("Mover"==MenuInferior.selecOpciones.getSelectionModel().getSelectedItem().toString()){
			if (TableroVistaControlador.seleccionado==false){
				if (algoformer.getJugador().getEstado()=="activo"){
					
					MenuInferior.log.insertText(0, "\nAlgoformer seleccionado! Elija la posicion objetivo");
					TableroVistaControlador.seleccionado=true;
					TableroVistaControlador.algoformerSeleccionado=algoformer;
				}
				else{
					System.out.println("Este algoformer no es tuyo");
				}
				
			}
			else{
				
				MenuInferior.log.insertText(0, "\nHay otro algoformer en esta ubicacion, movimiento no valido");
				TableroVistaControlador.seleccionado=false;
				
			} //el movimiento se realiza cuando hay un algoformer seleccionado y se hace click en un boton vacio
			//TODO MOVIMIENTO
		}
		if ("Atacar"==MenuInferior.selecOpciones.getSelectionModel().getSelectedItem().toString()){
			//TODO ATAQUE
			if (TableroVistaControlador.seleccionado==false){
				if (algoformer.getJugador().getEstado()=="activo"){
					
					MenuInferior.log.insertText(0, "\nAlgoformer seleccionado! Elija blanco de ataque");
					TableroVistaControlador.seleccionado=true;
					algoformer.getJugador().seleccionarPersonaje(algoformer.getNombre());
					TableroVistaControlador.algoformerSeleccionado=algoformer;
					
				}
				else{
					
					MenuInferior.log.insertText(0, "\nEste algoformer no es tuyo");
				}
				
			}
			else{ //Seleccionado=true
				//ATACAR
				try{
					TableroVistaControlador.algoformerSeleccionado.getJugador().atacar(algoformer.getPosicion(), ContenedorPrincipal.juego.getTablero());
					
					MenuInferior.log.insertText(0, "\n" + TableroVistaControlador.algoformerSeleccionado.getNombre()+" ataca a " + algoformer.getNombre());
					ContenedorPrincipal.juego.pasarTurno();
				}
				catch(FuegoAmigoException e){
					MenuInferior.log.insertText(0, "\nFuego amigo!");
					
				}
				catch(AtaqueFueraDeRangoException e){
					
					MenuInferior.log.insertText(0, "\nAtaque fuera de rango");
				}
				
				TableroVistaControlador.seleccionado=false;
				//atrapar excepciones DONE
			}
	}

}
}
