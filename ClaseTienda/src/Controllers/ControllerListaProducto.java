package Controllers;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Produtos.Producto;
import application.Main;
import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

	public class ControllerListaProducto 
	{
	@FXML TilePane tileProductos;
	@FXML ComboBox<String> cmbTemporada;
	@FXML private Button Ir_carrito;
	@FXML private HBox agregar_carrito_not;
	
	ControllerHelper ch;
	private List<Producto> listaProductosCarrito = new ArrayList<Producto>();
	private List<Producto> lista_producto = new ArrayList<Producto>();
	
	public void initialize() {
		
		lista_producto = Main.lista_main;
		cargarComboTemporada();
		
		Cargar_productos_vista(lista_producto);
		agregar_carrito_not.setTranslateY(65);
	}
	
	private void cargarComboTemporada() {
		List<String> listaTemporadas= new ArrayList<String>();
		listaTemporadas.add("invierno");
		listaTemporadas.add("verano");
	
		ObservableList<String> listaObservableTemporadas = FXCollections.observableArrayList(listaTemporadas);
		cmbTemporada.setItems(listaObservableTemporadas);	
	}
	
	private HBox Espacio_tile_productos(VBox producto)
	{
		HBox hbx = new HBox();
		Pane espacio = new Pane();
		
		espacio.setPrefWidth(30);
		hbx.getChildren().add(producto);
		hbx.getChildren().add(espacio);
		
		return hbx;
	}
	
	public VBox CrearProducto(Producto p){
		
		VBox tileProducto = new VBox(2);
		//String imagenProductoURL = p.getUrlimage();
		ImageView imgProducto = new ImageView(p.getUrlimage());
		
		imgProducto.setFitWidth(180);
		imgProducto.setFitHeight(230);
		imgProducto.setPreserveRatio(true);
		Label lblnombrep = new Label(p.getNombre());
		Label lbldescripcionProducto = new Label(p.getDescripcion());
		Label lblprecio = new Label("$"+ p.getPrecio());
		
		//Agregando estilos a los botones
		Button btncomprar = new Button("Comprar");
		btncomprar.getStyleClass().add("btn");
		btncomprar.getStyleClass().add("verde-oscuro");
		
		Spinner spnCantidad= new Spinner<>(0,20,1);
		
		tileProducto.getChildren().addAll(imgProducto, lblnombrep, lbldescripcionProducto,lblprecio,spnCantidad,btncomprar);
		
		btncomprar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				Ir_carrito.setDisable(false);
				int cantidad = (int)spnCantidad.getValue();
				
				System.out.println("Cantidad: " + cantidad);
				
				while(cantidad > 0)
				{
					listaProductosCarrito.add(p);
					cantidad--;
				}
				ControllerCarrito.lista = listaProductosCarrito;
				Notificacion_button(0, 1000);
			}
		});
		
		//btncomprar.set
		return tileProducto;
		}
		
	public void Cargar_productos_vista(List<Producto> lista)
	{
		System.out.println("********************************");
		
		for(Producto i: lista)
		{
			if(i.getEstado().equals("A"))
			{
				VBox vbx = CrearProducto(i);			vbx.setSpacing(4.0);
				HBox hbx = Espacio_tile_productos(vbx);
				
				System.out.println("Producto: " + i.getNombre());
				tileProductos.getChildren().add(hbx);
				
				System.out.println("Alto: " + tileProductos.getTileHeight() + " Ancho:  "  + tileProductos.getTileWidth());
			}
		}	
		System.out.println("********************************");
	}
	
	public void Cargar_vista() throws IOException 
	{
		//ch.Mostrar_Vista_Modal("/ViewCarrito.fxml", "Carrito de compras", 625, 377);
		FXMLLoader loader = new FXMLLoader();
		
		loader.setLocation(Main.class.getResource("/ViewCarrito.fxml"));
		
		Parent page = loader.load();
		Stage stage = new Stage();
		
		stage.setTitle("Carrito de compras");
		Scene scene = new Scene(page);
		
		scene.getStylesheets().add("/estilooriginal.css");
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setMaximized(false);
		stage.setResizable(false);
		stage.show();
	}

	public void CerrarSesion(ActionEvent event) {
				
		Stage stage = (Stage) ((Parent) event.getSource()).getScene().getWindow();		
		stage.close();
		
		ch.MostrarVista("/Viewintrotienda.fxml", "Store Online", false);
		//Scene scene = new Scene(root,640,360);
	}
	
	private void Notificacion_button(int y, int time)
	{				
		TranslateTransition trans = new TranslateTransition(Duration.millis(time), agregar_carrito_not);
		int ciclo = 0;
		
		trans.setToY(y);
		trans.setAutoReverse(true);
		trans.setCycleCount(2);
		//trans.setDelay(Duration.millis(1000));
		trans.play();
	
	}
}
