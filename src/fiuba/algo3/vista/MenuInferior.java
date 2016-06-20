package fiuba.algo3.vista;

import org.omg.CORBA.INITIALIZE;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MenuInferior extends ChoiceBox<String>{
	
	public MenuInferior(Stage stage){
    	ContenedorPrincipal.setAlignment(this, Pos.BASELINE_CENTER);
    	this.getItems().addAll("Observar","Mover","Atacar");
    	this.getSelectionModel().selectFirst();
    	if ("uno"==this.getSelectionModel().getSelectedItem().toString()){
    		System.out.println("wesa");
    	}
	}

}
