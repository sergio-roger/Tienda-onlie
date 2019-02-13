package Controllers;

import java.io.IOException;
import java.awt.Dimension;
import application.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.awt.Dimension;


public class ControllerHelper {
	
	public static void MostrarVista(String urlVista, String titulo, Boolean b) 
	{
		FXMLLoader loader = new FXMLLoader();
		
		loader.setLocation(Main.class.getResource(urlVista));
		
		try {
				Parent page = loader.load();
				Stage stage = new Stage();
				
				stage.setTitle(titulo);
				Scene scene = new Scene(page);
				scene.getStylesheets().add("/estilooriginal.css");
				stage.setScene(scene);
				
				if(b)
					stage.setMaximized(true);
				else
					stage.setMaximized(false);
				stage.show();
		}
		catch(IOException e){
			
			e.printStackTrace();
		}
		
	}
	public static void Mostrar_Vista_Modal(String urlVista, String titulo, int ancho, int largo) 
	{
		FXMLLoader loader = new FXMLLoader();
		
		loader.setLocation(Main.class.getResource(urlVista));	
		
		try {
				Parent page = loader.load();
				Stage stage = new Stage();
				
				stage.setTitle(titulo);
				Scene scene = new Scene(page, ancho, largo);
				
				scene.getStylesheets().add("/estilooriginal.css");
				stage.setScene(scene);
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.initStyle(StageStyle.UNDECORATED); 
				//stage.initOwner(((Parent)getSource()).getScene().getWindow() );
				stage.setMaximized(false);
				stage.setResizable(false);
				stage.show();
		}
		catch(IOException e){
			
			e.printStackTrace();
		}
	}
	
}
