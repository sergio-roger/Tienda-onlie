package Controllers;

import java.util.List;

import Autentificacion.Usuario;
import Produtos.Carrito;
import Produtos.Carrito.Objeto_Carrito;
import Produtos.Envio;
import Produtos.Factura;
import Produtos.ObjetoCarrito;
import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

public class ControllerFactura {
	
    @FXML private Label lbl_numero;
	@FXML private Label lbl_fecha_factura;
    @FXML private Label lbl_nombre_empresa;
    @FXML private Label lbl_telefono_empresa;
    @FXML private Label lbl_correo_empresa;
    @FXML private Label lbl_direccion_empresa;
    @FXML private Label lbl_ruc_empresa;
    @FXML private Label lbl_nombre_usuario;
    @FXML private Label lbl_celular_usuario;
    @FXML private Label lbl_correo_usuario;
    @FXML private Label lbl_direccion_usuario;
    @FXML private Label lbl_num_pedido;
    
    @FXML private TableView<Objeto_Carrito> tabla_factura;
	@FXML private TableColumn<Objeto_Carrito, Integer> col_cantidad;
	@FXML private TableColumn<Objeto_Carrito, String> col_producto;
	@FXML private TableColumn<Objeto_Carrito, Double> col_unitario;
	@FXML private TableColumn<Objeto_Carrito, Double> col_total;
	
    @FXML private Label lbl_subtotal;
    @FXML private Label lbl_iva;
    @FXML private Label lbl_total_factura;

    
    private Factura factura = null;
    private static Carrito car = null;
    private static Usuario user = null;
    private static Envio env = null;
    
	public void initialize()
	{
		car = ControllerCarrito.c;
		user = ControllerListaProducto.usuario_actual;
		env = ControllerEnvio.env;
		
		factura = new Factura();
		factura.setCarrito(car);
		factura.setUsuario(user);
		factura.setEnvio(env);
	
		Cargar_tabla(factura.getCarrito());
		Cargar_cabecera(factura);
		Cargar_montos(factura);
	}
	
	private void Cargar_cabecera(Factura fact)
	{
		lbl_numero.setText(String.valueOf(fact.getNumero()));
		lbl_fecha_factura.setText(fact.getFecha_emision().toString());
		
		//Datos de la compañia
		lbl_nombre_empresa.setText(fact.getEmpresa().getNombre());
		lbl_ruc_empresa.setText(fact.getEmpresa().getRuc());
		lbl_telefono_empresa.setText(fact.getEmpresa().getTelefono());
		lbl_correo_empresa.setText(fact.getEmpresa().getCorreo());
		lbl_direccion_empresa.setText(fact.getEmpresa().getDireccion());
		
		//Datos para el usuario
		lbl_nombre_usuario.setText(factura.getUsuario().getNombres() + " " + factura.getUsuario().getApellidos());
		lbl_direccion_usuario.setText(factura.getUsuario().getDireccion());	
		
		lbl_num_pedido.setText(String.valueOf(factura.getEnvio().getCodigo_guia()));
	}
	
	private void Cargar_montos(Factura factura)
	{
		//String iva  = String.valueOf(factura.getIva());
		lbl_total_factura.setText("$ " + factura.getTotal_fac());
		lbl_subtotal.setText(String.valueOf(factura.getSubtotal()));
		lbl_iva.setText(factura.getIva().toString());
	}
	
	public void Pagar_factura(ActionEvent event)
	{		
		Main.lista_factura.add(factura);
		ControllerListaProducto.listaProductosCarrito.clear();
		factura = null;
		
		Stage stage = (Stage) ((Parent) event.getSource()).getScene().getWindow();
    	stage.close();
    	
    	//Helper.abrirPantalla("Forma de pago", "/ViewPago.fxml");
	}
	
	public void Regresar_carrito(ActionEvent event)
	{	
		Stage stage = (Stage) ((Parent) event.getSource()).getScene().getWindow();
    	stage.close();
	}
	
	private void Cargar_tabla(Carrito c)
	{
		ObservableList<Carrito.Objeto_Carrito> lista_compra_observable = FXCollections.observableArrayList(c.getListObjetoCarrito());
		
		col_cantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));		col_cantidad.setEditable(true);
		col_producto.setCellValueFactory(new PropertyValueFactory<>("nombre"));			col_producto.setEditable(false);
		col_unitario.setCellValueFactory(new PropertyValueFactory<>("precio"));			col_unitario.setEditable(false);
		col_total.setCellValueFactory(new PropertyValueFactory<>("monto"));				col_total.setEditable(false);
		
		col_cantidad.setCellFactory(TextFieldTableCell.<Carrito.Objeto_Carrito, Integer>forTableColumn(new IntegerStringConverter()));
		
		tabla_factura.setItems(lista_compra_observable);
	}
}
