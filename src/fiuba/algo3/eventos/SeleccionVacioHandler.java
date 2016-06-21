package fiuba.algo3.eventos;

import fiuba.algo3.modelo.Coordenada;
import fiuba.algo3.vista.ContenedorPrincipal;
import fiuba.algo3.vista.MenuInferior;
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
		if ("Mover"==MenuInferior.selecOpciones.getSelectionModel().getSelectedItem().toString()){
			if (TableroVistaControlador.seleccionado==false){
				MenuInferior.log.insertText(0, "\nPor favor, seleccione un Algoformer");
				}
				else{
					//Mover y terminar turno
					TableroVistaControlador.algoformerSeleccionado.getJugador().moverPersonaje(coordenada, ContenedorPrincipal.juego.getTablero());
					TableroVistaControlador.seleccionado=false;
					
					MenuInferior.log.insertText(0, "\nAlgoformer: mover a posicion " + coordenada.getX() + " , " + coordenada.getY());
				}
		}
		if ("Atacar"==MenuInferior.selecOpciones.getSelectionModel().getSelectedItem().toString()){
			if (TableroVistaControlador.seleccionado==false){
				
				MenuInferior.log.insertText(0, "\nPor favor, seleccione un Algoformer");
			}
			else{
				//TableroVistaControlador.algoformerSeleccionado.getJugador().atacar(coordenada, ContenedorPrincipal.juego.getTablero());
				MenuInferior.log.insertText(0, "\n" + TableroVistaControlador.algoformerSeleccionado.getNombre()+" :ataque fallido!");
				ContenedorPrincipal.juego.pasarTurno();
				//Atacar a lugar vacio
			}
		}
	}
}

