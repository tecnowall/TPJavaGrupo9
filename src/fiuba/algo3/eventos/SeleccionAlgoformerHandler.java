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
					System.out.println("algoformer seleccionado!");
				}
				else{
					System.out.println("este algoformer no es tuyo");
				}
				
			}
			else{
				
				
			}
			//TODO MOVIMIENTO
		}
		if ("Atacar"==ContenedorPrincipal.menuInferior.getSelectionModel().getSelectedItem().toString()){
			//TODO ATAQUE
			if (TableroVistaControlador.seleccionado==false){
				
			}
			else{
				
			}
		}

		
	}

}
