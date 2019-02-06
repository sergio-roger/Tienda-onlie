package Controllers;

import java.awt.Frame;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import com.sun.corba.se.impl.orbutil.graph.Node;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
	
	public  ControllerIntroTienda() {
		
	}	
		
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//hacerVentanaEstatica();
	//cargarVideoPromocionalonline();
		// TODO Auto-generated method stub
		
		cargarVideoPromocionallocal();
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

	//private void hacerVentanaEstatica() {
		//Stage s=(Stage) mediaTienda.getScene().getWindow();
		//s.setResizable(false);		
	//}

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
	
	public void CargarVistaProducto(ActionEvent event) throws IOException {
		
		Stage stage = (Stage) ((Parent) event.getSource()).getScene().getWindow();		
		stage.close();
		
		if((txt_usuario.getText().equals(usuario)) && (txt_pass.getText().equals(pass)))
		{
			//Abrir view de Panel de Control para administrador
			ch.MostrarVista("/ViewPanelControl.fxml", "Dashboard", true);
		}
		else
		{
			//Abrir view de lista productos para los clientes
			ch.MostrarVista("/ViewListaProductos.fxml", "Lista de Productos", true);
		}
	}

}
