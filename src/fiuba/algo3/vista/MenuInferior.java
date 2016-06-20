package fiuba.algo3.vista;


import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class MenuInferior extends ChoiceBox<String>{
		
	public MenuInferior(Stage stage){
    	ContenedorPrincipal.setAlignment(this, Pos.BASELINE_CENTER);
    	this.getItems().addAll("Observar","Mover","Atacar");
    	this.getSelectionModel().selectFirst();
	}

}
