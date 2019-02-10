package Controllers;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Autentificacion.Rol;
import Autentificacion.Usuario;
import Produtos.Genero;
import Produtos.Producto;
import application.Main;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class Panel_ControlController implements Initializable 
{
	 	@FXML private TabPane panel_opciones;
	    @FXML private TableView<Producto> TablaProducto;
	    @FXML private TableView<Usuario> tabla_usuarios;
	    
	    //Columnas para la tabla producto
	    @FXML private TableColumn<Producto, Integer> Id;
	    @FXML private TableColumn<Producto, String> Prod;
	    @FXML private TableColumn<Producto, Double> Pre;
	    @FXML private TableColumn<Producto, String> Desc;
	    @FXML private TableColumn<Producto, String> Marc;
	    @FXML private TableColumn<Producto, String> Ur;
	    @FXML private TableColumn<Producto, Genero> Gene;
	    @FXML private TableColumn<Producto, String> col_editar;
	    @FXML private TableColumn<Producto, String> col_eliminar;  
	    
	    
	    //Columnas para la tabla de usuarios 
	    @FXML private TableColumn<Usuario, Integer> col_user_id;
	    @FXML private TableColumn<Usuario, String> col_user_nombres;
	    @FXML private TableColumn<Usuario, String> col_user_apellidos;
	    @FXML private TableColumn<Usuario, String> col_user_correo;
	    @FXML private TableColumn<Usuario, Rol> col_user_rol;
	    @FXML private TableColumn<Usuario, String> col_user_editar;
	    @FXML private TableColumn<Usuario, String> col_user_eliminar;
	    
	    @FXML private ComboBox<String> combo_genero;
	    @FXML private ComboBox<String> combo_filtro_usuario;
	    @FXML private TextField txt_producto;
	    @FXML private TextField txt_precio;
	    @FXML private TextField txt_url;
	    @FXML private TextField txt_marca;
	    @FXML private TextArea txt_descripcion;
	    @FXML private Label lbl_cantidad;
	    @FXML private Label lbl_total_usuarios;
	    @FXML private Label lbl_total_admi;
	    @FXML private Label lbl_total_emp;
	    @FXML private Label lbl_total_user;
	    @FXML private Button btn_agregar;
	    @FXML private Button btn_cancelar;
	    
	    
	    public static List<Producto> lista_panel = new ArrayList<Producto>();
	    public static List<Usuario> lista_panel_usuario = new ArrayList<Usuario>();
	    
	    private ArrayList<Button> vector_btn_editar = new ArrayList<>();
	    private ArrayList<Button> vector_btn_eliminar = new ArrayList<>();
	    private ArrayList<Button> vector_user_btn_editar = new ArrayList<>();
	    private ArrayList<Button> vector_user_btn_eliminar = new ArrayList<>();
	    
	    private int d, d_usuario;
	    private long id_general;
	    ControllerHelper ch;
	    
	    public void initialize(URL arg0, ResourceBundle arg1) 
	    {
	    	lista_panel = Main.lista_main;
	    	lista_panel_usuario = Main.lista_usuario_main;

	    	//Acciones para los productos 
	    	Llenar_Tabla();
	    	CargarCombo();
	    	Widgets_producto();
	    	Cargar_eventos_botones(lista_panel);
	   
	    	//Acciones para los usuarios 
	    	Llenar_tabla_usuario();
	    	Widgets_usuarios();
	    	Cargar_eventos_usuario_botones(lista_panel_usuario);
	    }
	    
	    public void Actualizar_usuarios()
	    {
	    	tabla_usuarios.getItems().removeAll(lista_panel_usuario);
	    	Llenar_tabla_usuario();
	    	Widgets_usuarios();
	    	Cargar_eventos_usuario_botones(lista_panel_usuario);
	    	combo_filtro_usuario.setValue("TODOS");
	    }
	    
	    private void Cargar_eventos_botones(List<Producto> lista)
	    {
	    	Button btn_editar, btn_eliminar;
	    
	    	while(d < lista.size())
	    	{
	    		//Agregando los eventos y estilos al boton editar del arryalist
	    		btn_editar = lista.get(d).getBtn_editar();
	    		btn_editar.setOnAction(this::handleButtonAction);
	    		btn_editar.getStyleClass().add("btn");
	    		btn_editar.getStyleClass().add("verde-oscuro");
	    		
	    		btn_eliminar = lista_panel.get(d).getBtn_eliminar();
	    		btn_eliminar.setOnAction(this::handleButtonAction);
	    		btn_eliminar.getStyleClass().add("btn");
	    		btn_eliminar.getStyleClass().add("rojo");
	    		
	    		vector_btn_editar.add(btn_editar); 
	    		vector_btn_eliminar.add(btn_eliminar);
	    		d++;
	    	}
	    	System.out.println(":::::::::::::::::::::::::::::::::");
	    	System.out.println("Dimension array: " + vector_btn_editar.size());
	    	System.out.println("Dimension de lista: " + lista_panel.size());
	    	System.out.println("d = " + d);
	    }
	    
	    private void Cargar_eventos_usuario_botones(List<Usuario> lista)
	    {
	    	Button btn_editar, btn_eliminar;
	    	
	    	while(d_usuario < lista.size())
	    	{
	    		//Agregando los eventos y estilos al boton editar del arryalist
	    		btn_editar = lista.get(d_usuario).getBtn_editar();
	    		btn_editar.setOnAction(this::handleButtonAction);
	    		btn_editar.getStyleClass().add("btn");
	    		btn_editar.getStyleClass().add("verde-oscuro");
	    		
	    		btn_eliminar = lista.get(d_usuario).getBtn_eliminar();
	    		btn_eliminar.setOnAction(this::handleButtonAction);
	    		btn_eliminar.getStyleClass().add("btn");
	    		btn_eliminar.getStyleClass().add("rojo");
	    		
	    		vector_user_btn_editar.add(btn_editar); 
	    		vector_user_btn_eliminar.add(btn_eliminar);
	    		
	    		vector_user_btn_eliminar.get(0).setDisable(true); 			//El primer boton del administrador bloqueado, para que no pueda eliminar esa cuenta
	    		d_usuario++;
	    	}
	    	System.out.println(":::::::::::::::::::::::::::::::::");
	    	System.out.println("Dimension array: " + vector_user_btn_editar.size());
	    	System.out.println("Dimension de lista: " + lista_panel_usuario.size());
	    	System.out.println("d = " + d_usuario);
	    }
	    
	    private void Llenar_Tabla() {
			
			Id.setCellValueFactory(new PropertyValueFactory<>("id"));
			Prod.setCellValueFactory(new PropertyValueFactory<>("nombre"));
			Pre.setCellValueFactory(new PropertyValueFactory<>("precio"));
			Desc.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
			Marc.setCellValueFactory(new PropertyValueFactory<>("marca"));
			Ur.setCellValueFactory(new PropertyValueFactory<>("urlimage"));
			Gene.setCellValueFactory(new PropertyValueFactory<>("generoObjetivo"));
			col_editar.setCellValueFactory(new PropertyValueFactory<>("btn_editar"));
			col_eliminar.setCellValueFactory(new PropertyValueFactory<>("btn_eliminar"));
			
			for(Producto p:lista_panel)
				if(p.getEstado().equals("A"))
					TablaProducto.getItems().add(p);
				
			//System.out.println("Productos agregados");
	    }
	    
	    private void Llenar_tabla_usuario()
	    {
	    	Crear_celdas_usuarios();
			
			for(Usuario i : lista_panel_usuario)
				if(i.getEstado().equals("A"))
					tabla_usuarios.getItems().add(i);
	    }
	    
	    private void CargarCombo(){
	    	
	    	ObservableList<String> options = 
	    		    FXCollections.observableArrayList (
	    		        "MASCULINO", "FEMENINO", "LGBTI", "UNISEX", "NINGUNO", "OTRO" );
	    	
	    		combo_genero.setItems(options);
	    		
	    		ObservableList<String> options2 = 
		    		    FXCollections.observableArrayList (
		    		        "ADMINISTRADOR", "EMPLEADOS", "USUARIO", "TODOS" );
		    	
	    		combo_filtro_usuario.setItems(options2);
	    		combo_filtro_usuario.setValue("TODOS");
	    }
	    
	    private Producto Crear_P() {
	    	
	    	Producto nuevo = null;
	    	
	    	long id = Generar_id();
	    	double precio = 0.0;
	    	String producto = txt_producto.getText();
	    	
	    	if(!txt_precio.getText().isEmpty())
	    		precio = Double.parseDouble(txt_precio.getText());
	    	
	    	String descripcion = txt_descripcion.getText();
	    	String marca = txt_marca.getText();
    		String url = txt_url.getText();
    		
    		if(url.isEmpty() || url == null)		//Validar si tiene una url, si no se asigna una imagen por defualt
    			url = "/image no found.png";
    		
    		String aux_gen = combo_genero.getValue();
    		
    		if(aux_gen == null || aux_gen.isEmpty())
    			aux_gen = "OTRO";
    		
    		switch(aux_gen){
	    		case "MASCULINO" :	nuevo = new Producto(id, producto, precio, descripcion, marca, url, Genero.MASCULINO,"A");	break;			
	    		case "FEMENINO" :	nuevo = new Producto(id, producto, precio, descripcion, marca, url, Genero.FEMENINO, "A");	break;
	    		case "LGBTI" :		nuevo = new Producto(id, producto, precio, descripcion, marca, url, Genero.LGBTI,"A");		break;		
	    		case "UNISEX" :		nuevo = new Producto(id, producto, precio, descripcion, marca, url, Genero.UNISEX,"A");		break;		
	    		case "NINGUNO" :	nuevo = new Producto(id, producto, precio, descripcion, marca, url, Genero.NINGUNO,"A");	break;		
	    		case "OTRO" :		nuevo = new Producto(id, producto, precio, descripcion, marca, url, Genero.OTRO,"A");		break;
    		}  
    		
	    	return nuevo;
	    }
	    
	    public void Guardar_Producto()
	    {
	    	//Modificar esta función para validar bien el ingreso de productos
	    	
	    	Alert alerta = new Alert(AlertType.INFORMATION);
	    	Producto p = Crear_P();
	    	
	       	alerta.setTitle("Productos");		alerta.setHeaderText(null);
	    	
	    	System.out.println("Id del producto: " + id_general);
	    	
	    	if(id_general == 0)	//Para registrar un nuevo producto
	    	{
	    		if(p.getNombre().isEmpty() || p.getNombre() == null)
	    		{
	    			alerta.setAlertType(AlertType.ERROR);
	    			alerta.setContentText("Ingrese un nombre válido");
	    		}
	    		else if(p.getPrecio() == 0.0)
	    		{
	    			alerta.setAlertType(AlertType.ERROR);
	    			alerta.setContentText("Necesita asignar un precio");
	    		}
	    		else 
	    		{
	    			if(p.getDescripcion().isEmpty() || p.getDescripcion() == null)
	    				p.setDescripcion("Sin descripcion");
	    			
	        		lista_panel.add(p);
	            	TablaProducto.getItems().add(p);
	            	//System.out.println("\nDimension boton: " + button.length + "\n");
	            	alerta.setContentText("Producto " + p.getNombre() + " agregado !");
	            	Limpiar_cajas();
	    		}
	    	}
	    	else
	    	{				//Para actualizar un producto existente
	    		if(Existe_id(id_general)) //Existe id
	        	{
	        		alerta.setAlertType(AlertType.INFORMATION);
	        		
	        		String nombre = txt_producto.getText();
	        		Double precio = Double.parseDouble(txt_precio.getText());
	        		String desc = txt_descripcion.getText();
	         		String marca = txt_marca.getText();
	         		String url = txt_url.getText();
	         		String aux_gen = combo_genero.getValue();
	        		
	        		for(Producto i: lista_panel)
	        		{
	        			if(i.getId() == id_general)
	        			{
	        				i.setNombre(nombre);
	        				i.setPrecio(precio);
	        				i.setDescripcion(desc);
	        				i.setMarca(marca);
	        				i.setUrlimage(url);
	        				
	        				switch(aux_gen)
	        				{
		        				case "MASCULINO" :	i.setGeneroObjetivo(Genero.MASCULINO);  	break;		
			            		case "FEMENINO" :	i.setGeneroObjetivo(Genero.FEMENINO); 		break;
			            		case "LGBTI" :		i.setGeneroObjetivo(Genero.LGBTI); 			break;
			            		case "UNISEX" :		i.setGeneroObjetivo(Genero.UNISEX); 		break;
			            		case "NINGUNO" :	i.setGeneroObjetivo(Genero.NINGUNO); 		break;
			            		case "OTRO" :		i.setGeneroObjetivo(Genero.OTRO); 			break;
	        				}
	        			}
	        		}
	        		id_general = 0;
	        		Actualizar_tabla() ;
	        		alerta.setContentText("Datos del producto actualizado !");
	        		Limpiar_cajas();
	        	}
	    	}
	    	
	    	Cargar_eventos_botones(lista_panel);
	    	
	    	alerta.showAndWait();
	    	
	    	Generar_id();
	    	Widgets_producto();
	    }
	    
	    public void Limpiar_cajas()
	    {
	    	id_general = 0;
	    	txt_producto.clear();
	    	txt_precio.clear();
	    	txt_descripcion.clear();
	    	txt_marca.clear();
	    	txt_url.clear();
	    	combo_genero.getSelectionModel().clearSelection();
	    	combo_genero.setPromptText("Elegir Opcion");
	    }
	    
	    private long Generar_id() 
	    {
	    	long id = lista_panel.size() + 1;
	    	return id;
	    }
	      
	    private void handleButtonAction(ActionEvent event)
	    {
	    	//Agregando las líneas de codigo para que edite el producto en el respectivo boton
	    	int a = 0, b = 0, a1 = 0, b1 = 0;
	    	
	    	while(a < lista_panel.size())
	    	{
	    		if(event.getSource() == vector_btn_editar.get(a))
	        	{
	    			System.out.println("Producto: " + lista_panel.get(a).getNombre());
	    			Producto aux = lista_panel.get(a);
	        		
	    			id_general = aux.getId();			//Pasa id_general del producto
	        		txt_producto.setText(aux.getNombre());
	            	txt_precio.setText(String.valueOf(aux.getPrecio()));
	            	txt_descripcion.setText(aux.getDescripcion());;
	            	txt_marca.setText(aux.getMarca());
	            	
	            	if(aux.getUrlimage().equals("/image no found.png"))
	            		txt_url.setText("");
	            	else
	            		txt_url.setText(aux.getUrlimage());
	            	
	            	Genero genero = aux.getGeneroObjetivo();
	            	combo_genero.setPromptText(genero.name());
	            	combo_genero.setValue(genero.name());
	        	}
	    		a++;
	    	}
	    	
	    	//Agregando las líneas para eliminar el producto
	    	while(b < lista_panel.size())
	    	{
	    		if(event.getSource() == vector_btn_eliminar.get(b))
	        	{
	    			System.out.println("Producto: " + lista_panel.get(b).getNombre());
	    			Producto aux = lista_panel.get(b);
	        		
	    			//Actualizando estado del producto
	    			for(Producto p : lista_panel)
	    			{
	    				if(p.getId() == aux.getId())
	    				{
	    					p.setEstado("I");
	    					System.out.println("Producto: " + p.getNombre() + " Eliminado !");
	    				}
	    			}				
	        	}
	    		b++;
	    	}
	    	
	    	//Acciones para editar un usuario empleado o administrador
	    	
	    	//Acciones para eliminar un usuario empleado o administrador
	    	while(b1 < lista_panel_usuario.size())
	    	{
	    		if(event.getSource() == vector_user_btn_eliminar.get(b1))
	        	{
	    			System.out.println("Usuario: " + lista_panel_usuario.get(b1).getNombres());
	    			Usuario aux = lista_panel_usuario.get(b1);
	        		
	    			//Actualizando estado del producto
	    			for(Usuario u : lista_panel_usuario)
	    			{
	    				if(u.getId() == aux.getId())
	    				{
	    					u.setEstado("I");
	    					System.out.println("Producto: " + u.getNombres() + " Eliminado !");
	    				}
	    			}				
	        	}
	    		b1++;
	    	}
			Contar_productos();
	    	Actualizar_tabla();
	    	Actualizar_usuarios();
	    }
	    
	    private void Actualizar_tabla() 
	    {
	    	TablaProducto.getItems().removeAll(lista_panel);
	    	Llenar_Tabla();
		}

	    private Boolean Existe_id(long id)
	    {
	    	Boolean existe = false; 
	    	
	    	for(int i = 0; i < lista_panel.size(); i++)
	    	{
	    		Producto aux = lista_panel.get(i);
	    		existe = (id == aux.getId()) ? true:false;
	    		
	    		if(existe) break;
	    	}
	    	return existe;
	    }
		
		public void CerrarSesion(ActionEvent event) {
			
			Main.lista_main = lista_panel;
			Main.lista_usuario_main = lista_panel_usuario;
			
			Stage stage = (Stage) ((Parent) event.getSource()).getScene().getWindow();		
			stage.close();
			
			ch.MostrarVista("/Viewintrotienda.fxml", "Lista de Productos", false);
		}	
		
		public void Cargar_tabla_opcion_combo()
		{
			String rol = combo_filtro_usuario.getSelectionModel().getSelectedItem();
			tabla_usuarios.getItems().removeAll(lista_panel_usuario);
						
			Crear_celdas_usuarios();
			
			if(rol.equals("ADMINISTRADOR"))
			{
				for(Usuario i: lista_panel_usuario)
					if(i.getRol().name().equals("ADMINISTRADOR") && i.getEstado().equals("A"))
						tabla_usuarios.getItems().add(i);
			}
			else
			if(rol.equals("USUARIO"))
			{
				for(Usuario i: lista_panel_usuario)
					if(i.getRol().name().equals(rol) && i.getEstado().equals("A"))
						tabla_usuarios.getItems().add(i);
			}
			else
			if(rol.equals("TODOS"))
				Llenar_tabla_usuario();
			else
			{
				for(Usuario i: lista_panel_usuario)
					if(!i.getRol().name().equals("USUARIO") && !i.getRol().name().equals("ADMINISTRADOR")&& i.getEstado().equals("A"))
						tabla_usuarios.getItems().add(i);
			}
		}
		
		private void Crear_celdas_usuarios()
		{
			col_user_id.setCellValueFactory(new PropertyValueFactory<>("id"));
	    	col_user_nombres.setCellValueFactory(new PropertyValueFactory<>("nombres"));
	    	col_user_apellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
	    	col_user_correo.setCellValueFactory(new PropertyValueFactory<>("correo"));
	    	col_user_rol.setCellValueFactory(new PropertyValueFactory<>("rol"));
	    	col_user_editar.setCellValueFactory(new PropertyValueFactory<>("btn_editar"));
			col_user_eliminar.setCellValueFactory(new PropertyValueFactory<>("btn_eliminar"));
			
		}
		
		private void Widgets_producto()
		{
			Contar_productos();
		}
		
		private void Widgets_usuarios()
		{
			Contar_usuarios();
		}
		
		private void Contar_productos()
		{
			int cantidad = 0; 
			
			for(Producto i: lista_panel)
				if(i.getEstado().equals("A"))
					cantidad++;
			
			lbl_cantidad.setText("Cantidad:    " + cantidad);
		}
		
		private void Contar_usuarios()
		{
			int cantidad = 0, admi = 0, emp = 0, user = 0;
			
			for(Usuario i: lista_panel_usuario)
			{
				if(i.getEstado().equals("A"))
				{
					cantidad++;
					
					if(i.getRol().name().equals("ADMINISTRADOR"))
						admi++;
					else if(i.getRol().name().equals("USUARIO"))
						user++;
					else
						emp++;
				}
			}
				
			lbl_total_usuarios.setText("Cantidad:     " + cantidad);
			lbl_total_admi.setText("Total    " + admi);
			lbl_total_emp.setText("Total    " + emp);
			lbl_total_user.setText("Total    " + user);
		}
		
		public void Vista_empleado(ActionEvent event)
		{
			ch.Mostrar_Vista_Modal("/ViewEmpleado.fxml", "Formulario Empleado",700,610);
		}
		
		public void Vista_usuario()
		{
			ControllerRegistro.control_boton = true;
			ch.Mostrar_Vista_Modal("/ViewRegistro.fxml", "Formulario Registro",609,668);
		}
}
