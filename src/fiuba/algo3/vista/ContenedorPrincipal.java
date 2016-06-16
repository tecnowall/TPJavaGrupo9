package fiuba.algo3.vista;

import fiuba.algo3.modelo.algoformer.Algoformer;
import fiuba.algo3.modelo.algoformer.Forma;
import fiuba.algo3.modelo.algoformer.Humanoide;
import fiuba.algo3.modelo.algoformer.Terrestre;
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
        //TEST
		Forma forma1 = new Humanoide( 50, 2, 2 );
		Forma forma2 = new Terrestre( 15, 4, 5 );
		Algoformer optimus = new Algoformer( "Optimus", 500, forma1, forma2 );
		vistaTablero.ubicarAlgoformer(optimus,2,2);
	}

	private void setMenu(Stage stage) {
        this.menuBar = new BarraDeMenu(stage);
        this.setTop(menuBar);
    }
}
