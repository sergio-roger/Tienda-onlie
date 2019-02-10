package Controllers;

import java.awt.Frame;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.sun.corba.se.impl.orbutil.graph.Node;

import Autentificacion.Autentificar;
import Autentificacion.Rol;
import Autentificacion.Usuario;
import Produtos.Producto;
import application.Main;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.stage.Window;

public class ControllerIntroTienda implements Initializable {
	
	@FXML MediaView mediaTienda;
	@FXML private AnchorPane introTienda;
	@FXML private TextField txt_usuario;
	@FXML private TextField txt_pass;
    @FXML private Button btn_login;
    
	MediaPlayer mp;
	ControllerHelper ch;
	private String usuario = "Admi";
	private String pass = "123";
	public static List<Producto> lista_tienda = new ArrayList<>();
	public static List<Usuario> lista_usuario_intro = new ArrayList<>();
	public static Usuario activo = null;
	
	public  ControllerIntroTienda() {
		
	}	
		
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		cargarVideoPromocionallocal();
		lista_tienda = Main.lista_main;
		lista_usuario_intro = Main.lista_usuario_main;
		
		for(Usuario i:lista_usuario_intro)
				System.out.println(i.getNombres() + " rol: " + i.getRol());
	}
	
	private void cargarVideoPromocionallocal() {
		
	File f=new File("ClaseTienda/resoursesvideo/fashion.mp4");
	Media media;
	try {
		media=new Media(f.toURI().toURL().toString());
		mp=new MediaPlayer(media);
		mediaTienda.setMediaPlayer(mp);		
		mp.play();
		mp.setAutoPlay(true);
		//para toque para siempre
		mp.setCycleCount(MediaPlayer.INDEFINITE);
	}catch(MalformedURLException e) {
		e.printStackTrace();
	}
				
	}

	private void cargarVideoPromocionalonline() {
		Media media=new Media("https://gcs-vimeo.akamaized.net/exp=1547855572~acl=%2A%2F644267110.mp4%2A~hmac=e5139927d6edeeaff0251971b8b06fca89739937700992cf439e88dd308cc434/vimeo-prod-skyfire-std-us/01/3606/7/193033143/644267110.mp4");
		mp=new MediaPlayer(media);
		mediaTienda.setMediaPlayer(mp);		
		mp.play();
		mp.setAutoPlay(true);
	}
	
	public void play() {
		mp.play();
	}
	
	public void pause() {
		mp.pause();
	}
	
	public void stop() {
		mp.stop();
	}
	
	public void Iniciar(ActionEvent event) throws IOException 
	{
		int informacion = Validar_datos();
		
		Alert alerta = new Alert(AlertType.ERROR);
		alerta.setTitle("Login");
		alerta.setHeaderText(null);
		
		Stage stage = (Stage) ((Parent) event.getSource()).getScene().getWindow();	
		
		switch(informacion)
		{
			case 1:		//Datos vacíos
				alerta.setContentText("Faltan datos, vuelva a ingresar");
				alerta.showAndWait();
				break;
				
			case 2: 	//Existen datos y es tipo usuario o cliente
				//alerta.setAlertType(AlertType.INFORMATION);
				//alerta.setContentText("Datos de usuario");
				stage.close();
				ch.MostrarVista("/ViewListaProductos.fxml", "Lista de Productos", true);
				break;
			
			case 3: 	//Existe y solo es administrador total
				//alerta.setAlertType(AlertType.INFORMATION);
				//alerta.setContentText("Datos de Administrador");
				stage.close();
				ch.MostrarVista("/ViewPanelControl.fxml", "Dashboard", true);
				
				break;
				
			case 4:		//Existe y solo es un empleado
				alerta.setAlertType(AlertType.INFORMATION);
				alerta.setContentText("Datos del empleado");
				break;
			
			case 5:
				alerta.setAlertType(AlertType.ERROR);
				alerta.setContentText("Datos incorrectos");
				Limpiar_cajas();
				alerta.showAndWait();
				break;
				
			default: 
				alerta.setAlertType(AlertType.ERROR);
				alerta.setContentText("Ocurió error inesperado, vuelva a ingresar datos");
				Limpiar_cajas();
				alerta.showAndWait();
		}
	}
	
	
	private int Validar_datos()
	{
		int opcion = 0;	Boolean existe = false;
		String usuario = txt_usuario.getText(); 
		String pass = txt_pass.getText();
		
		if(usuario.isEmpty() || usuario == null || pass.isEmpty() || pass == null)
			return 1;		//Datos vacíos
		else
		{
			for(Usuario i: lista_usuario_intro)
			{
				Autentificar auth = i.getAutentificar();
				
				if(auth.getUsuario().equals(usuario) && auth.getContrasenia().equals(pass))
				{
					existe = true;
					
					if(i.getRol().equals(Rol.USUARIO))
					{						
						ControllerListaProducto.usuario_actual = i;
						System.out.println("Nombres: " + i.getNombres());
						return 2;
					}
					
					if(i.getRol().equals(Rol.ADMINISTRADOR))
						return 3;
					
					if(i.getRol().equals(Rol.ANALISTA) ||
							   i.getRol().equals(Rol.GERENTE) || i.getRol().equals(Rol.VENTAS))
						return 4;
					
				}
			}
			
			if(!existe)
				return 5;
		}	
		return opcion;
	}
	
	private void Limpiar_cajas()
	{
		txt_usuario.clear();
		txt_pass.clear();
	}
	
	public void Cargar_vista_registro(ActionEvent event) throws IOException {
		
		Stage stage = (Stage) ((Parent) event.getSource()).getScene().getWindow();		
		stage.close();
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/ViewRegistro.fxml"));
		
		Parent page = loader.load();
		Stage stage1 = new Stage();
		
		stage1.setTitle("Formulario de regisro");
		Scene scene = new Scene(page, 609, 668);
		
		scene.getStylesheets().add("/estilooriginal.css");
		stage1.setScene(scene);
		stage1.setResizable(false);
		stage1.show();
	}
}
