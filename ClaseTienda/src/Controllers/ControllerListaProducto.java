package Controllers;
import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Autentificacion.Usuario;
import Produtos.Genero;
import Produtos.Marca;
import Produtos.Producto;
import application.Main;
import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Skin;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

//1064 y 458

public class ControllerListaProducto 
{

    @FXML private VBox vbox_principal;
	@FXML private VBox vbox_superior;
    @FXML private ScrollPane scrol_panel;
    
	@FXML TilePane tileProductos;
	@FXML ComboBox<Marca> cmb_marca;
	@FXML private Button Ir_carrito;
	@FXML private HBox agregar_carrito_not;
	@FXML private Label lbl_usuario;
	@FXML private ImageView img_perfil;
	@FXML private Label lbl_nombres;
	@FXML private Label lbl_apellido;
	@FXML private RadioButton rdb_masc;
	@FXML private RadioButton rdb_fem;
	@FXML private RadioButton rdb_lg;
	@FXML private RadioButton rdb_unisex;
	@FXML private RadioButton rdb_otros;
	@FXML private RadioButton todos;
	
	
	ControllerHelper ch;
	public static List<Producto> listaProductosCarrito = new ArrayList<Producto>();
	private List<Producto> lista_producto = new ArrayList<Producto>();
	private List<Marca> lista_marca = new ArrayList<Marca>();
	
	public static Usuario usuario_actual = null;
	
	public void initialize() {
		
		listaProductosCarrito.clear();
		lista_producto = Main.lista_main;
		lista_marca = Main.lista_marca_main;
		
		Datos_usuarios_cargados();
		Cargar_combo_marca();
		
		Cargar_productos_vista(lista_producto);
		agregar_carrito_not.setTranslateY(65*2);
		agregar_carrito_not.setPrefHeight(60);
		 
		 Listener_vbox_principal();						//Funciona que escucha los cambios en el tamaño de la ventana
		 todos.setSelected(true);
		 Botones_Control_Cliente();
	}
	
	private void Datos_usuarios_cargados()
	{
		Image img = new Image(usuario_actual.getUrl_foto());
		
		lbl_nombres.setText(usuario_actual.getNombres());;
		lbl_apellido.setText(usuario_actual.getApellidos());
		lbl_usuario.setText(usuario_actual.getAutentificar().getUsuario());
		System.out.println("Url default: " + usuario_actual.getUrl_foto());
		
		img_perfil.setImage(img);
		img_perfil.setFitWidth(200);
		img_perfil.setFitHeight(150);
		img_perfil.setPreserveRatio(true);
	}
		
	private void Listener_vbox_principal()
	{
		vbox_principal.widthProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				// TODO Auto-generated method stub
				
				Double new_vbox = Double.parseDouble(newValue.toString());
				double const_ini = 257; 
				double ancho = new_vbox - const_ini;
				
				System.out.println("------------------------------------------------");
		        System.out.println("Vbox old = " + oldValue + ", new = " + newValue);
		        
		        scrol_panel.setPrefWidth(ancho);
		        tileProductos.setPrefWidth(ancho);
			}
		});
	}
	
	private void Cargar_combo_marca()
	{
		ObservableList<Marca> marca = FXCollections.observableArrayList ();
		
		lista_marca.get(0).setEstado("A");
		
		for(Marca i: lista_marca)
			if(i.getEstado().equals("A"))
				marca.add(i);
		
		cmb_marca.setItems(marca);
		cmb_marca.setPromptText("Elija marca");
		cmb_marca.setValue(lista_marca.get(0));
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
		Pane pi = new Pane();		pi.setPrefHeight(10);
		System.out.println("Url: " + p.getUrlimage());
		
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
		String estilo = spnCantidad.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL;
		spnCantidad.getStyleClass().add(estilo);
		spnCantidad.setPrefWidth(imgProducto.getFitWidth());
		spnCantidad.getStylesheets().add("-fx-background-color: #000;");
		
		tileProducto.getChildren().addAll(imgProducto, lblnombrep, lbldescripcionProducto,lblprecio,spnCantidad,btncomprar, pi);
		
		btncomprar.setPrefWidth(imgProducto.getFitWidth());
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
				//ControllerCarrito.lista = listaProductosCarrito;
				Notificacion_button(-1 * 2, 1500);
			}
		});
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
	
	private void Filtrar_productos_genero(Genero genero)
	{
		System.out.println("***************" + genero.name() + "*****************");
		
		tileProductos.getChildren().clear();
		
		for(Producto i: lista_producto)
		{
			if(i.getEstado().equals("A") && i.getGeneroObjetivo().name().equals(genero.name()))
			{
				
				VBox vbx = CrearProducto(i);			vbx.setSpacing(4.0);
				HBox hbx = Espacio_tile_productos(vbx);
				
				tileProductos.getChildren().add(hbx);
				System.out.println("Producto: " + i .getNombre());
			}
		}	
		System.out.println("********************************");
	}
	
	public void Cargar_vista() throws IOException 
	{
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
		trans.play();
	}
	
	private void Botones_Control_Cliente(){
		
		final ToggleGroup grupo = new ToggleGroup();
		
		 todos.setToggleGroup(grupo);
		 rdb_lg.setToggleGroup(grupo);
		 rdb_otros.setToggleGroup(grupo);
		 rdb_masc.setToggleGroup(grupo);
		 rdb_fem.setToggleGroup(grupo);
		 rdb_unisex.setToggleGroup(grupo);
	}

	public void Radio_lgbti()
	{	
		tileProductos.getChildren().clear();
		Filtrar_productos_genero(Genero.LGBTI);
	}
	
	public void Radio_unisex()
	{
		tileProductos.getChildren().clear();
		Filtrar_productos_genero(Genero.UNISEX);
	}
	
	public void Radio_masculino()
	{
		tileProductos.getChildren().clear();
		Filtrar_productos_genero(Genero.MASCULINO);
	}
	
	public void Radio_femenino()
	{
		tileProductos.getChildren().clear();
		Filtrar_productos_genero(Genero.FEMENINO);
	}
	
	public void Radio_otros()
	{
		tileProductos.getChildren().clear();
		Filtrar_productos_genero(Genero.OTRO);
	}
	
	public void Radio_todos()
	{
		tileProductos.getChildren().clear();
		Cargar_productos_vista(lista_producto);
	}
}
