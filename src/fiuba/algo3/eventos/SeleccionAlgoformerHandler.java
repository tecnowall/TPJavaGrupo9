package fiuba.algo3.eventos;

import fiuba.algo3.modelo.algoformer.Algoformer;
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
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Algoformer");
		alert.setHeaderText("Algoformer seleccionado: " + algoformer.getNombre());
		alert.setContentText("Vida actual: " + algoformer.getVida() + "\nPoder: " + algoformer.getPoder()
		+ " (Poder Base: " + algoformer.getPoderBase() + ")\nVelocidad: "+ algoformer.getVelocidad()
		+ " (Velocidad Base: " + algoformer.getVelocidadBase() + ")");

		alert.showAndWait();
		
	}

}
