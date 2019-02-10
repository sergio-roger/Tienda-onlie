package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Autentificacion.Autentificar;
import Autentificacion.Rol;
import Autentificacion.Usuario;
import Produtos.Genero;
import Produtos.Producto;
import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ControllerEmpleado implements Initializable  {
	
	@FXML private ImageView image;
	@FXML private TextField txt_nombre;
 	@FXML private TextField txt_apellido;
    @FXML private TextField txt_direccion;
    @FXML private TextField txt_correo;
    @FXML private TextField txt_celular;
    @FXML private ComboBox<String> cmb_rol;
    @FXML private TextField txt_user;
    @FXML private PasswordField txt_pass;
    @FXML private PasswordField txt_pass_conf;
    @FXML private TextField txt_cedula;
    @FXML private TextField txt_url;
    
    private String url_default = "/user_default.jpg";
    public static List<Usuario> lista_usuario_empleado = new ArrayList<Usuario>();
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		lista_usuario_empleado = Panel_ControlController.lista_panel_usuario;
		Datos_defualt();
		
	}
	
	long Generar_id()
	{
		long id = lista_usuario_empleado.size() + 1;
		return id;
	}
	
	public void Cerrar_vista(ActionEvent event) throws IOException
	{
		Stage stage = (Stage) ((Parent) event.getSource()).getScene().getWindow();		
		stage.close();
	}
	
	public void Guardar_empelado()
	{
		int opcion = 0;
		
		Usuario emp = Crear_usuario();
		Autentificar auth = Crear_auth();
		
		System.out.println("Nombres: " + emp.getNombres() + "\tApellidos: " + emp.getApellidos());
		
		opcion = Validar_iformacion(emp, auth);
		Tipo_alerta(opcion, emp, auth);
		Main.lista_usuario_main = lista_usuario_empleado;
	}
	
	private void Tipo_alerta(int opcion, Usuario usuario, Autentificar auth)
	{
		Alert alerta = new Alert(AlertType.ERROR);
		alerta.setTitle("Login");
		alerta.setHeaderText(null);
		
		switch(opcion)
		{
			case 1:		//Nombres y/o apellidos, cédula vacios
				alerta.setContentText("Ingrese nombres y/o apellidos");
				alerta.showAndWait();
				break;
			
			case 2: 	//Falta correo electronico
				alerta.setContentText("Ingrese correo electrónico");
				alerta.showAndWait();
				break;
				
			case 3: 	//Datos de inicio de seción vacío
				alerta.setContentText("Datos de inicio no puede esta vacío");
				alerta.showAndWait();
				break;
			
			case 4: 	//Confirmación de contraseña no coinciden
				alerta.setContentText("Las contraseñas no coinciden");
				alerta.showAndWait();
				break;
				
			case 5: 	//Contraseña coinciden y tiene datos completos
				usuario.setAutentificar(auth);
				lista_usuario_empleado.add(usuario);
				Limpiar_cajas();
				alerta.setAlertType(AlertType.INFORMATION);
				alerta.setContentText("Cuenta creada");
				alerta.showAndWait();
				break;
		}
	}
	
	private int Validar_iformacion(Usuario user, Autentificar auth)
	{
		int opcion = 0;
		
		//Validando los datos de usuario 
		if(user.getNombres().isEmpty() || user.getNombres() == null|| user.getApellidos().isEmpty() || user.getApellidos() == null
				|| user.getCedula().isEmpty() || user.getCedula() == null)
			return 1;
		else
		if(user.getCorreo().isEmpty() || user.getCorreo() == null)
			return 2;
		else
		{
			//Validando los datos del login 
			String pass_con = txt_pass_conf.getText();
			
			if(auth.getUsuario().isEmpty() || auth.getContrasenia().isEmpty() || pass_con.isEmpty())
				return 3;
			
			if(!auth.getContrasenia().equals(pass_con))
				return 4;
			
			if(!auth.getUsuario().isEmpty() && (pass_con.equals(auth.getContrasenia())))
				return 5;
		}
		return opcion;
	}
	
	private Usuario Crear_usuario()
	{
		Usuario nuevo = null; 
		
		long id = Generar_id();
		String nombre = txt_nombre.getText();		String apellido = txt_apellido.getText();
		String cedula = txt_cedula.getText();		String direccion = txt_direccion.getText();
		String celular = txt_celular.getText();		String correo = txt_correo.getText();
		String aux_rol = cmb_rol.getValue();		String url = txt_url.getText();
		
		if(aux_rol == null || aux_rol.isEmpty())
			aux_rol = "";
		
		switch(aux_rol){
			case "ADMINISTRADOR" :	nuevo = new Usuario(id, cedula, nombre, apellido, direccion, correo, celular, Rol.ADMINISTRADOR, "A");	break;			
			case "GERENTE" :		nuevo = new Usuario(id, cedula, nombre, apellido, direccion, correo, celular, Rol.GERENTE, "A");		break;
			case "VENTAS" :			nuevo =	new Usuario(id, cedula, nombre, apellido, direccion, correo, celular, Rol.VENTAS, "A");			break;		
			case "ANALISTA" :		nuevo =	new Usuario(id, cedula, nombre, apellido, direccion, correo, celular, Rol.ANALISTA, "A");		break;		
			default:				nuevo =	new Usuario(id, cedula, nombre, apellido, direccion, correo, celular, Rol.VENTAS, "A");
		}  
		
		if(url.isEmpty() || url == null)
			url = url_default;
		
		nuevo.setUrl_foto(url);
		
		return nuevo;
	}
	
	private Autentificar Crear_auth()
	{
		Autentificar auth = null;
		
		String user = txt_user.getText();
		String pass = txt_pass.getText();
		
		auth = new Autentificar(user, pass);
		
		return auth;
	}

	private void Cargar_combo()
	{

		ObservableList<String> options2 = 
    		    FXCollections.observableArrayList (
    		    		"","ADMINISTRADOR", "GERENTE", "VENTAS", "ANALISTA" );
    	
		cmb_rol.setItems(options2);
	}
	
	private void Image_default()
	{
		Image img = new Image(url_default);
		image.setImage(img);
		
	}
	
	private void Datos_defualt()
	{
		Cargar_combo();
		Image_default();
	}
	
	public void Limpiar_cajas()
	{
		txt_nombre.clear();
		txt_apellido.clear();
		txt_correo.clear();
		txt_user.clear();
		txt_pass.clear();
		txt_pass_conf.clear();
		txt_cedula.clear();
		cmb_rol.getItems().clear();
		cmb_rol.setPromptText("Rol");
		txt_url.clear();
		txt_direccion.clear();
		txt_celular.clear();
		Cargar_combo();
	}
	
}
