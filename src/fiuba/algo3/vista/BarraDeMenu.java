package fiuba.algo3.vista;

//import javafx.scene.control.Alert;
//import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.stage.Stage;

public class BarraDeMenu extends MenuBar {

    MenuItem opcionPantallaCompleta = new MenuItem("Pantalla completa");

    public BarraDeMenu(Stage stage) {

        Menu menuArchivo = new Menu("Archivo");
        Menu menuVer = new Menu("Ver");
        Menu menuAyuda = new Menu("Ayuda");

        MenuItem opcionSalir = new MenuItem("Salir");
        MenuItem opcionAbrir = new MenuItem("Abrir");
        MenuItem opcionAcercaDe = new MenuItem("Acerca de...");
        menuArchivo.getItems().addAll(opcionAbrir, new SeparatorMenuItem(), opcionSalir);
        menuAyuda.getItems().addAll(opcionAcercaDe);
        menuVer.getItems().addAll(opcionPantallaCompleta);

        this.getMenus().addAll(menuArchivo, menuVer, menuAyuda);

    }
    //faltan handlers
}
