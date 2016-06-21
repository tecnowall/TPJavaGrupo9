package fiuba.algo3.eventos;

import fiuba.algo3.modelo.Coordenada;
import fiuba.algo3.vista.ContenedorPrincipal;
import fiuba.algo3.vista.TableroVistaControlador;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SeleccionVacioHandler implements EventHandler<ActionEvent>{
	
	Coordenada coordenada;
	public SeleccionVacioHandler(Coordenada unaCoordenada){
		coordenada=unaCoordenada;
	}

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		if ("Mover"==ContenedorPrincipal.menuInferior.getSelectionModel().getSelectedItem().toString()){
			if (TableroVistaControlador.seleccionado==false){
				System.out.println("Por favor, seleccione un Algoformer");
				}
				else{
					//Mover y terminar turno
					TableroVistaControlador.algoformerSeleccionado.getJugador().moverPersonaje(coordenada, ContenedorPrincipal.juego.getTablero());
					TableroVistaControlador.seleccionado=false;
					System.out.println("algoformer: mover a posicion " + coordenada.getX() + " , " + coordenada.getY());
				}
		}
		if ("Atacar"==ContenedorPrincipal.menuInferior.getSelectionModel().getSelectedItem().toString()){
			if (TableroVistaControlador.seleccionado==false){
				System.out.println("Por favor, seleccione un Algoformer");
			}
			else{
				//Atacar a lugar vacio
			}
		}
	}
}

