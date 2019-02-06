package application;
	
import java.util.ArrayList;
import java.util.List;

import Produtos.Genero;
import Produtos.Producto;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	public static List<Producto> lista_main = new ArrayList<Producto>();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Productos_Default();
			Parent root = FXMLLoader.load(getClass().getResource("/Viewintrotienda.fxml"));
			Scene scene = new Scene(root,640,360);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private void Productos_Default() {
		
		
		Producto p1 = new Producto(1, "Camisetas", 25, "Camiseta playerae", "Polo", "../img/camiseta.jpg", Genero.MASCULINO, "A");
		Producto p2 = new Producto(2, "Blusa", 25, "Blusa cafe", "Polo", "../img/blusa.jpg", Genero.FEMENINO, "A");
		Producto p3 = new Producto(3, "Pantalon", 25, "Pantalon crema", "Polo", "../img/pantalon.jpg", Genero.MASCULINO, "A");
		Producto p4 = new Producto(4, "Camisa", 25, "Camisa mangas cortas", "Polo", "../img/camisa.jpg", Genero.MASCULINO, "A");
		
		lista_main.add(p1);
		lista_main.add(p2);
		lista_main.add(p3);
		lista_main.add(p4);
		
	}
}
