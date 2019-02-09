package Controllers;
import java.util.ArrayList;
import java.util.List;

import Produtos.Producto;
import application.Main;
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
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ControllerListaProducto {
@FXML TilePane tileProductos;
@FXML ComboBox<String> cmbTemporada;
@FXML private Button Ir_carrito;

ControllerHelper ch;
private List<Producto> listaProductosCarrito = new ArrayList<Producto>();
private List<Producto> lista_producto = new ArrayList<Producto>();

public void initialize() {
	
	cargarComboTemporada();
	//Cargar_productos_desdeclase();
	lista_producto = Main.lista_main;
	
	Cargar_productos_vista(lista_producto);
	//Cargar_productos_existentes(lista_producto);
}

private void Cargar_productos_existentes(List<Producto> lista)
{
	System.out.println("-----------------------------");
	
	for(Producto i: lista)
		System.out.println(i.getId() + " " + i.getNombre() + " " + i.getEstado());
	
	System.out.println("-----------------------------");
}

private void cargarComboTemporada() {
	List<String> listaTemporadas= new ArrayList<String>();
	listaTemporadas.add("invierno");
	listaTemporadas.add("verano");

	ObservableList<String> listaObservableTemporadas = FXCollections.observableArrayList(listaTemporadas);
	cmbTemporada.setItems(listaObservableTemporadas);	
}

public VBox CrearProducto(Producto p){
	
	VBox tileProducto = new VBox(2);
	//String imagenProductoURL = p.getUrlimage();
	ImageView imgProducto = new ImageView(p.getUrlimage());
	
	imgProducto.setFitWidth(200);
	imgProducto.setFitHeight(250);
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
			System.out.println(p.toString());	
		}
	});
	return tileProducto;
}
	
	private VBox Crear_producto_claseproducto(Producto p) {
		
		return CrearProducto(p);
	}
	
	public void Cargar_productos_vista(List<Producto> lista)
	{
		System.out.println("********************************");
		
		for(Producto i: lista)
		{
			if(i.getEstado().equals("A"))
			{
				VBox vbx = Crear_producto_claseproducto(i);			vbx.setSpacing(5.0);
				System.out.println("Producto: " + i.getNombre());
				tileProductos.getChildren().add(vbx);
			}
		}	
		System.out.println("********************************");
	}
	
	public void Cargar_vista() {
		
		Stage primaryStage = new Stage();
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/ViewCarrito.fxml"));
			Scene scene = new Scene(root,640,360);
			
			scene.getStylesheets().add("/estilooriginal.css");
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("Carrito de Compras");
			primaryStage.setResizable(false);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

public void CerrarSesion(ActionEvent event) {
				
		Stage stage = (Stage) ((Parent) event.getSource()).getScene().getWindow();		
		stage.close();
		
		ch.MostrarVista("/Viewintrotienda.fxml", "Store Online", false);
		//Scene scene = new Scene(root,640,360);
	}
}
