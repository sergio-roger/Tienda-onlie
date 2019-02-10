package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Autentificacion.Autentificar;
import Autentificacion.Rol;
import Autentificacion.Usuario;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControllerRegistro implements Initializable {
	
	public static List<Usuario> lista_usuario_registro = new ArrayList<Usuario>();
	public static Boolean control_boton = false;
	ControllerHelper ch;
	
	@FXML private AnchorPane anchor_fondo;
	@FXML private TextField txt_nombres;
    @FXML private TextField txt_apellidos;
    @FXML private TextField txt_correo;
    @FXML private TextField txt_usuario;
    @FXML private PasswordField txt_pass;
    @FXML private PasswordField txt_pass_conf;
    @FXML private CheckBox chb_aceptar_termino;
	
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
    	lista_usuario_registro = Main.lista_usuario_main;
	}
	
    private long Generar_id()
    {
    	long aux = lista_usuario_registro.size() + 1;
    	return aux;
    	
    }

    public void Regresar_login(ActionEvent event) throws IOException
	{
		Stage stage = (Stage) ((Parent) event.getSource()).getScene().getWindow();		
		stage.close();
		
		if(!control_boton)
		{
			ch.MostrarVista("/Viewintrotienda.fxml", "Lista de Productos", false);
		}
			
		else
			control_boton = false;	
	}
	
	public void Guardar_usuario()
	{
		int op = 0; 
		
		Usuario user = Crear_usuario();
		Autentificar auth = Crear_auth();
		
		//System.out.println("Nombres: " + user.getNombres() + "\tApellidos: " + user.getApellidos());
		
		op = Validar_iformacion(user, auth);
		Tipo_alerta(op, user, auth);
		
		//System.out.println("Cantidad de usuarios: " + lista_usuario_registro.size());
		Main.lista_usuario_main = lista_usuario_registro;
	}
	
	private void Tipo_alerta(int opcion, Usuario usuario, Autentificar auth)
	{
		Alert alerta = new Alert(AlertType.ERROR);
		alerta.setTitle("Login");
		alerta.setHeaderText(null);
		
		switch(opcion)
		{
			case 1:		//Nombres y/o apellidos vacios
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
				lista_usuario_registro.add(usuario);
				Limpiar_cajas();
				alerta.setAlertType(AlertType.INFORMATION);
				alerta.setContentText("Cuenta creada");
				alerta.showAndWait();
				break;
		}
	}
	
	public void Limpiar_cajas()
	{
		txt_nombres.clear();
		txt_apellidos.clear();
		txt_correo.clear();
		txt_usuario.clear();
		txt_pass.clear();
		txt_pass_conf.clear();
	}
	
	private int Validar_iformacion(Usuario user, Autentificar auth)
	{
		int opcion = 0;
		
		//Validando los datos de usuario 
		if(user.getNombres().isEmpty() || user.getNombres() == null|| user.getApellidos().isEmpty() || user.getApellidos() == null)
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
		//(long id, String nombres, String apellidos, String correo, Rol rol, Autentificar autentificar)
		Usuario user = null; 
		
		long id = Generar_id();
		String nombres = txt_nombres.getText();
		String apellidos = txt_apellidos.getText();
		String correo = txt_correo.getText();
		
		user = new Usuario(id, nombres,apellidos,correo, Rol.USUARIO, "A");
		
		return user;
	}
	
	private Autentificar Crear_auth()
	{
		Autentificar auth = null;
		
		String user = txt_usuario.getText();
		String pass = txt_pass.getText();
		
		auth = new Autentificar(user, pass);
		
		return auth;
	}
}
