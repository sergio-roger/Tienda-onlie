package Controllers;

import java.io.IOException;

import application.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
}
