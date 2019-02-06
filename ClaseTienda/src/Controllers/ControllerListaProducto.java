package Controllers;
import java.util.ArrayList;
import java.util.List;

import Produtos.Producto;
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

public void initialize() {
	
	cargarComboTemporada();
	Cargar_productos_desdeclase();
	
}

private List<Producto> listaProductosCarrito = new ArrayList<Producto>();

private void cargarComboTemporada() {
	List<String> listaTemporadas= new ArrayList<String>();
	listaTemporadas.add("invierno");
	listaTemporadas.add("verano");

	ObservableList<String> listaObservableTemporadas = FXCollections.observableArrayList(listaTemporadas);
	cmbTemporada.setItems(listaObservableTemporadas);
	
}
public void CargarProducto(){
    //Creamos Productos
	//VBox producto1 = CrearProducto("/modelo2.jpg"," Lleve lleve, su modelo "," 10");
	//VBox producto2 = CrearProducto("/pantalonm2.jpg"," Lleve lleve, su pantalon "," 10");
	//VBox producto3 = CrearProducto("/ropa.jpg"," Lleve lleve, su ropa de bombero "," 35");
	//VBox producto4 = CrearProducto("/ropaniña.jpg"," Lleve lleve, su ropa de niña "," 25");
	
	//tileProductos.getChildren().add(producto1);
	//tileProductos.getChildren().add(producto2);
	//tileProductos.getChildren().add(producto3);
	//tileProductos.getChildren().add(producto4);

}

public VBox CrearProducto(Producto p){
	
	VBox tileProducto = new VBox(2);
	String imagenProductoURL = p.getUrlimage();
	ImageView imgProducto = new ImageView(p.getUrlimage());
	imgProducto.setFitWidth(200);
	imgProducto.setFitHeight(250);
	imgProducto.setPreserveRatio(true);
	Label lblnombrep = new Label(p.getNombre());
	Label lbldescripcionProducto = new Label(p.getDescripcion());
	Label lblprecio = new Label("$"+ p.getPrecio());
	
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
	
	
	private void Cargar_productos_desdeclase() {
		
		//Crenado a través de la clase producto
		Producto p1 = new Producto(1, 10, "Camisa personalizada", "Hermosa casmisa personalizada", "/camisa_p.jpg");
		Producto p2 = new Producto(2, 10, "Camiseta blanca elgante", "Casual blanco con estilo negro", "/camiseta-b.jpg");
		Producto p3 = new Producto(3, 35, "Camiseta mujer", "Casual para la belleza femenina", "/camiseta-mujer.jpg");
		Producto p4 = new Producto(4, 30, "Pantalon Jean azul marino", "Diseño estético y moderno", "/pantalon-mujer.jpg");
		Producto p5 = new Producto(5, 20, "Pantalón trivo negro", "Deportivo y a la moda", "/pantalon-tn.jpg");
		Producto p6 = new Producto(6, 40, "Pantalón blanco moderno", "Look and feel, perfeccion", "/pantalon-blanco.jpg");
		
		//Agregando el objeto producto a un vbox
		VBox producto1 = Crear_producto_claseproducto(p1);			producto1.setSpacing(5.0);
		VBox producto2 = Crear_producto_claseproducto(p2);			producto2.setSpacing(5.0);
		VBox producto3 = Crear_producto_claseproducto(p3);			producto3.setSpacing(5.0);
		VBox producto4 = Crear_producto_claseproducto(p4);			producto4.setSpacing(5.0);
		VBox producto5 = Crear_producto_claseproducto(p5);			producto5.setSpacing(5.0);
		VBox producto6 = Crear_producto_claseproducto(p6);			producto6.setSpacing(5.0);
		
		//Agregando el vbox a un tittlepanel
		tileProductos.getChildren().add(producto1);
		tileProductos.getChildren().add(producto2);
		tileProductos.getChildren().add(producto3);
		tileProductos.getChildren().add(producto4);
		tileProductos.getChildren().add(producto5);
		tileProductos.getChildren().add(producto6);
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
}
