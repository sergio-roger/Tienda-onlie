package application;
	
import java.util.ArrayList;
import java.util.List;

import Autentificacion.Autentificar;
import Autentificacion.Rol;
import Autentificacion.Usuario;
import Produtos.Genero;
import Produtos.Marca;
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
	public static List<Marca> lista_marca_main = new ArrayList<Marca>();
	
	@Override
	public void start(Stage primaryStage) {
		try 
		{
			Marca_default();
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
	
	private void Consultar_productos_existentes()
	{
		System.out.println("--------------------------");
		
		for(Producto i: lista_main)
			System.out.println(i.getId() + " " + i.getNombre() + " " + i.getEstado());
		
		System.out.println("--------------------------");
	}
	
	private void Productos_Default() {
		
		Marca m1 = lista_marca_main.get(0);
		Marca m2 = lista_marca_main.get(1);
		Marca m3 = lista_marca_main.get(2);
		Marca m4 = lista_marca_main.get(3);
		
		Producto p1 = new Producto(1, "Camisetas", 25, "Camiseta playera", m1, "/camisa_p.jpg", Genero.MASCULINO, "A");
		Producto p2 = new Producto(2, "Camiseta blanca", 20, "Blanco lindo", m2, "/camiseta-b.jpg", Genero.MASCULINO, "A");
		Producto p3 = new Producto(3, "Camiseta mujer", 45, "Elegante y femenina", m3, "/camiseta-mujer.jpg", Genero.FEMENINO, "A");
		Producto p4 = new Producto(4, "Pantal�n mujer", 33, "Camisa mangas cortas", m4, "/pantalon-mujer.jpg", Genero.FEMENINO, "A");
		
		lista_main.add(p1);
		lista_main.add(p2);
		lista_main.add(p3);
		lista_main.add(p4);	
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
		
		admi.setUrl_foto("/user_default.jpg");
		cliente.setUrl_foto("/user_default.jpg");
		karen.setUrl_foto("/user_default.jpg");
		
		admi.setAutentificar(admi_auth);
		cliente.setAutentificar(cli_auth);
		karen.setAutentificar(karen_auth);
		
		lista_usuario_main.add(admi);
		lista_usuario_main.add(cliente);
		lista_usuario_main.add(karen);
	}
	
	private void Marca_default(){
		
		Marca todos = new Marca(0, "Todos", "A");
		Marca m1 = new Marca(1, "Dior", "A");
		Marca m2 = new Marca(2, "Indi Tex", "A");
		Marca m3 = new Marca(3, "Adidas", "A");
		Marca m4 = new Marca(4, "Kering", "A");
		
		lista_marca_main.add(todos);
		lista_marca_main.add(m1);		lista_marca_main.add(m2);
		lista_marca_main.add(m3);		lista_marca_main.add(m4);
	}
	
}
