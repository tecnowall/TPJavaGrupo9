package fiuba.algo3.vista;
import fiuba.algo3.modelo.tablero.*;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Cell;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class TableroVistaControlador {
	private Tablero tablero;
	private MainApp mainApp;
	@FXML
	private GridPane tableroView;
	@FXML
	private Cell celda;
	@FXML
	private Label rect=new Label("OPTIMUS");
	private Label rect2=new Label("MEGATRON");
	
public TableroVistaControlador() {

	}
	@FXML
	private void initialize() {
		tablero = new Tablero (19,10);
		tableroView.setAlignment(Pos.CENTER);
		for (int i = 0;i<=(tablero.getAncho());i++){
			tableroView.getColumnConstraints().add(new ColumnConstraints(100));
		}
		for (int j = 0; j<=(tablero.getAlto());j++){
			tableroView.getRowConstraints().add(new RowConstraints(30));
		} //Creo tablero de 11x11 si Tablero (10,10);
		tableroView.setConstraints(rect, 5, 5);
		tableroView.add(rect, 5, 5);
		tableroView.add(new Label("wesa"), 4, 5);

	}
public void setMainApp(MainApp mainApp) {
    this.mainApp = mainApp;
    }
}
