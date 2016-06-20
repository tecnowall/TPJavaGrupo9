package fiuba.algo3.eventos;

import fiuba.algo3.modelo.algoformer.Algoformer;
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
				}
				else{
					System.out.println("Este algoformer no es tuyo");
				}
				
			}
			else{
				//ATACAR
				TableroVistaControlador.seleccionado=false;
				
			}
	}

}
}
