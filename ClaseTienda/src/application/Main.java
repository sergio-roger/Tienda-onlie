package application;
	
import java.util.ArrayList;
import java.util.List;

import Autentificacion.Autentificar;
import Autentificacion.Rol;
import Autentificacion.Usuario;
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
	public static List<Usuario> lista_usuario_main = new ArrayList<Usuario>();
	
	@Override
	public void start(Stage primaryStage) {
		try 
		{
			Productos_Default();
			Datos_default();
			
			Parent root = FXMLLoader.load(getClass().getResource("/Viewintrotienda.fxml"));
			Scene scene = new Scene(root,640,360);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
			
			//Consultar_productos_existentes();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private void Productos_Default() {
		
		Producto p1 = new Producto(1, "Camisetas", 25, "Camiseta playera", "Polo", "/camisa_p.jpg", Genero.MASCULINO, "A");
		Producto p2 = new Producto(2, "Camiseta blanca", 20, "Blanco lindo", "Polo", "/camiseta-b.jpg", Genero.MASCULINO, "A");
		Producto p3 = new Producto(3, "Camiseta mujer", 45, "Elegante y femenina", "Polo", "/camiseta-mujer.jpg", Genero.FEMENINO, "A");
		Producto p4 = new Producto(4, "Pantalón mujer", 33, "Camisa mangas cortas", "Polo", "/pantalon-mujer.jpg", Genero.FEMENINO, "A");
		
		lista_main.add(p1);
		lista_main.add(p2);
		lista_main.add(p3);
		lista_main.add(p4);	
	}
	
	private void Consultar_productos_existentes()
	{
		System.out.println("--------------------------");
		
		for(Producto i: lista_main)
			System.out.println(i.getId() + " " + i.getNombre() + " " + i.getEstado());
		
		System.out.println("--------------------------");
	}
	
	private void Datos_default()
	{	
		//Creando las credenciales
		Autentificar admi_auth = new Autentificar("admi", "123"); 
		Autentificar cli_auth = new Autentificar("user", "user");
		Autentificar karen_auth = new Autentificar("karen", "karen");
		
		//Creando los datos principales
		Usuario admi = new  Usuario(1, "Sergio", "Floreano", "sergio@gmail.com", Rol.ADMINISTRADOR, "A");
		Usuario cliente = new Usuario(2, "usuario", "", "usuario@gmail.com", Rol.USUARIO, "A");
		Usuario karen = new Usuario(3, "2400234126","Karen", "Velarde", "Santa Elena", "karen@hotmail.com", "0981278470", Rol.ANALISTA, "A");
		
		admi.setAutentificar(admi_auth);
		cliente.setAutentificar(cli_auth);
		karen.setAutentificar(karen_auth);
		
		lista_usuario_main.add(admi);
		lista_usuario_main.add(cliente);
		lista_usuario_main.add(karen);
	}
}
