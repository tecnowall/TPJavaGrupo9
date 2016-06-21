package fiuba.algo3.eventos;

import fiuba.algo3.modelo.algoformer.Algoformer;
import fiuba.algo3.modelo.algoformer.AtaqueFueraDeRangoException;
import fiuba.algo3.modelo.algoformer.FuegoAmigoException;
import fiuba.algo3.vista.ContenedorPrincipal;
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
		
		if ("Observar"==ContenedorPrincipal.menuInferior.getSelectionModel().getSelectedItem().toString()){
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Algoformer");
			alert.setHeaderText("Algoformer seleccionado: " + algoformer.getNombre());
			alert.setContentText("Vida actual: " + algoformer.getVida() + "\nPoder: " + algoformer.getPoder()
			+ " (Poder Base: " + algoformer.getPoderBase() + ")\nVelocidad: "+ algoformer.getVelocidad()
			+ " (Velocidad Base: " + algoformer.getVelocidadBase() + ")");
	
			alert.showAndWait();
		}
		if ("Mover"==ContenedorPrincipal.menuInferior.getSelectionModel().getSelectedItem().toString()){
			if (TableroVistaControlador.seleccionado==false){
				if (algoformer.getJugador().getEstado()=="activo"){
					System.out.println("Algoformer seleccionado! Elija la posicion objetivo");
					TableroVistaControlador.seleccionado=true;
					TableroVistaControlador.algoformerSeleccionado=algoformer;
				}
				else{
					System.out.println("Este algoformer no es tuyo");
				}
				
			}
			else{
				System.out.println("Hay otro algoformer en esta ubicacion, movimiento no valido");
				TableroVistaControlador.seleccionado=false;
				
			} //el movimiento se realiza cuando hay un algoformer seleccionado y se hace click en un boton vacio
			//TODO MOVIMIENTO
		}
		if ("Atacar"==ContenedorPrincipal.menuInferior.getSelectionModel().getSelectedItem().toString()){
			//TODO ATAQUE
			if (TableroVistaControlador.seleccionado==false){
				if (algoformer.getJugador().getEstado()=="activo"){
					System.out.println("Algoformer seleccionado! Elija blanco de ataque");
					TableroVistaControlador.seleccionado=true;
					algoformer.getJugador().seleccionarPersonaje(algoformer.getNombre());
					TableroVistaControlador.algoformerSeleccionado=algoformer;
					
				}
				else{
					System.out.println("Este algoformer no es tuyo");
				}
				
			}
			else{ //Seleccionado=true
				//ATACAR
				try{
					TableroVistaControlador.algoformerSeleccionado.getJugador().atacar(algoformer.getPosicion(), ContenedorPrincipal.juego.getTablero());
					System.out.println(TableroVistaControlador.algoformerSeleccionado.getNombre()+" ataca a " + algoformer.getNombre());
					ContenedorPrincipal.juego.pasarTurno();
				}
				catch(FuegoAmigoException e){
					System.out.println("Fuego amigo!");
				}
				catch(AtaqueFueraDeRangoException e){
					System.out.println("Ataque fuera de rango");
				}
				
				TableroVistaControlador.seleccionado=false;
				//atrapar excepciones DONE
			}
	}

}
}
