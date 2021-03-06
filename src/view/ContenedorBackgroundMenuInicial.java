package view;

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import unidades.*;

import java.util.ArrayList;

import edificios.*;
import juego.*;
import excepciones.*;
import mapa.*;
import mapa.excepcionesMapa.cajaEstaOcupada;
import mapa.excepcionesMapa.casilleroInvalido;
import mapa.excepcionesMapa.tamanioDeMapaInvalido;


public class ContenedorBackgroundMenuInicial {

	private ArrayList<ImageView> imagenes;
	
	public ContenedorBackgroundMenuInicial() {
		
		this.imagenes = new ArrayList<ImageView>();
		
		Image imagen = new Image("backgroundFinal.jpg");
		ImageView fondo = new ImageView(imagen);
		fondo.setFitHeight(650);
		fondo.setFitWidth(1200);
		
		this.imagenes.add(fondo);
	}
	
	public ArrayList<ImageView> obtenerBackgroundMenuInicial() {
		return this.imagenes;
	}
}
