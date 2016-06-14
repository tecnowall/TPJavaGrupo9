package fiuba.algo3.vista;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class BarraDeMenu {
	Menu menuAyuda;
	MenuItem opcionAcercaDe;
	
	public void acercaDeHandle(){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Acerca de...");
        alert.setHeaderText("Ejemplo de mensaje de alerta");
        String mensaje = "Algoformers \nGrupo 9 \nPatente Pendiente";
        alert.setContentText(mensaje);
        alert.show();
	}
}
