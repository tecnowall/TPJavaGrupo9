package fiuba.algo3.eventos;

import fiuba.algo3.modelo.Coordenada;
import fiuba.algo3.modelo.algoformer.MovimientoFueraDeRangoException;
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
		if ("Observar"==MenuInferior.selecOpciones.getSelectionModel().getSelectedItem().toString()){
			MenuInferior.log.appendText("\nCasillero: "+coordenada.getX() +"," + coordenada.getY());
		}
		if ("Mover"==MenuInferior.selecOpciones.getSelectionModel().getSelectedItem().toString()){
			if (TableroVistaControlador.seleccionado==false){
				MenuInferior.log.appendText("\nPor favor, seleccione un Algoformer");
				}
				else{
					//Mover y terminar turno
					try{
						TableroVistaControlador.algoformerSeleccionado.getJugador().moverPersonaje(coordenada, ContenedorPrincipal.juego.getTablero());			
						MenuInferior.log.appendText("\nAlgoformer: mover a posicion " + coordenada.getX() + " , " + coordenada.getY());
						ContenedorPrincipal.juego.pasarTurno();
					}
					catch (MovimientoFueraDeRangoException e){
						MenuInferior.log.appendText("\nMovimiento fuera de rango");
					}

					
					TableroVistaControlador.seleccionado=false;
					
					
				}
		}
		if ("Atacar"==MenuInferior.selecOpciones.getSelectionModel().getSelectedItem().toString()){
			if (TableroVistaControlador.seleccionado==false){
				
				MenuInferior.log.appendText("\nPor favor, seleccione un Algoformer");
			}
			else{
				//TableroVistaControlador.algoformerSeleccionado.getJugador().atacar(coordenada, ContenedorPrincipal.juego.getTablero());
				MenuInferior.log.appendText("\n" + TableroVistaControlador.algoformerSeleccionado.getNombre()+" :ataque fallido!");
				ContenedorPrincipal.juego.pasarTurno();
				TableroVistaControlador.seleccionado=false;
				//Atacar a lugar vacio
			}
		}
	}
}

