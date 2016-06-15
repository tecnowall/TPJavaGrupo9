package fiuba.algo3.vista;

import fiuba.algo3.modelo.tablero.*;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ContenedorPrincipal extends BorderPane {

    BarraDeMenu menuBar;
    TableroVistaControlador vistaTablero;
    Tablero tablero;
    Canvas canvasCentral;
    
	public ContenedorPrincipal(Stage unStage, Tablero unTablero){
		this.setMenu(unStage);
		this.setTablero(unTablero);
		this.tablero = unTablero;
	}
    private void setTablero(Tablero unTablero) {
		// TODO Auto-generated method stub
        GridPane grid=new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setGridLinesVisible(true);
        vistaTablero = new TableroVistaControlador(unTablero,grid);
        vistaTablero.dibujarTablero();
        this.setCenter(grid);
	}

	private void setMenu(Stage stage) {
        this.menuBar = new BarraDeMenu(stage);
        this.setTop(menuBar);
    }
}
