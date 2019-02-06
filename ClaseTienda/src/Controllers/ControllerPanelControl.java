package Controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ControllerPanelControl implements Initializable{
	
    @FXML private TabPane panel_opciones;
    @FXML private TableView<Producto> TablaProducto;
    
    @FXML private TableColumn<Producto, Integer> Id;
    @FXML private TableColumn<Producto, String> Prod;
    @FXML private TableColumn<Producto, Double> Pre;
    @FXML private TableColumn<Producto, String> Desc;
    @FXML private TableColumn<Producto, String> Marc;
    @FXML private TableColumn<Producto, String> Ur;
    @FXML private TableColumn<Producto, Genero> Gene;
    @FXML private TableColumn<Producto, String> col_editar;
    @FXML private TableColumn<Producto, String> col_eliminar;  
    @FXML private ComboBox<String> combo_genero;
    @FXML private TextField txt_producto;
    @FXML private TextField txt_precio;
    @FXML private TextField txt_url;
    @FXML private TextField txt_marca;
    @FXML private TextArea txt_descripcion;
    @FXML private Button btn_agregar;
    @FXML private Button btn_cancelar;
    
    
    public static List<Producto> lista_panel = new ArrayList<Producto>();
    private Button []button;
    private Button []delete;
    private int d;
    private long id_general = 0;
    private int  cb = 0;
	ControllerHelper ch;
    
    public void initialize(URL arg0, ResourceBundle arg1) 
    {
    	lista_panel = Main.lista_main;
    	
    	for(Producto i: lista_panel) 
    		System.out.println(i.getNombre());
    	
    	Llenar_Tabla();
    	CargarCombo();
    	Cargar_eventos_botones();
    }
    
    private void Cargar_eventos_botones()
    {
    	d = lista_panel.size();
    	
    	button = new Button[d];
    	delete = new Button[d];
    	
    	for(int i = 0; i < button.length; i++)
    	{
    		button[i] = lista_panel.get(i).getBtn_editar();
    		button[i].setOnAction(this::handleButtonAction);
    		button[i].getStyleClass().add("btn");
    		button[i].getStyleClass().add("verde-oscuro");
    		
    		delete[i] = lista_panel.get(i).getBtn_eliminar();
    		delete[i].setOnAction(this::handleButtonAction);
    		delete[i].getStyleClass().add("btn");
    		delete[i].getStyleClass().add("rojo");
    	}
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
			
		System.out.println("Productos agregados");
    }
    
    private void CargarCombo(){
    	
    	ObservableList<String> options = 
    		    FXCollections.observableArrayList (
    		        "MASCULINO", "FEMENINO", "LGBTI", "UNISEX", "NINGUNO", "OTRO" );
    	
    		combo_genero.setItems(options);
    }
    
    private Producto Crear_P() {
    	Producto nuevo = null;
    	
    	if( txt_producto.getText().equals("") || txt_precio.getText().equals("")) {
    		System.out.println("Ingrese valores ");
    	}else {
    		
    		long id = Generar_id();
    		String producto = txt_producto.getText();
    		double precio = Double.parseDouble(txt_precio.getText());
    		String descripcion = txt_descripcion.getText();
    		String marca = txt_marca.getText();
    		String url = txt_url.getText();
    		
    		String aux_gen = combo_genero.getValue();
    		
    		switch(aux_gen){
    		case "MASCULINO" :	nuevo = new Producto(id, producto, precio, descripcion, marca, url, Genero.MASCULINO,"A");	break;
    				
    		case "FEMENINO" :	nuevo = new Producto(id, producto, precio, descripcion, marca, url, Genero.FEMENINO, "A");	break;
			
    		case "LGBTI" :		nuevo = new Producto(id, producto, precio, descripcion, marca, url, Genero.LGBTI,"A");		break;
				
    		case "UNISEX" :		nuevo = new Producto(id, producto, precio, descripcion, marca, url, Genero.UNISEX,"A");		break;
				
    		case "NINGUNO" :	nuevo = new Producto(id, producto, precio, descripcion, marca, url, Genero.NINGUNO,"A");	break;
				
    		case "OTRO" :		nuevo = new Producto(id, producto, precio, descripcion, marca, url, Genero.OTRO,"A");		break;
    		}
    	}   
    	id_general = 0;
    	return nuevo;
    }
    
    public void Guardar_Producto() {
    	
    	Alert alerta = new Alert(AlertType.INFORMATION);
    	
       	alerta.setTitle("Productos");
    	alerta.setHeaderText(null);
    	
    	if(id_general == 0)
    	{
    		if(txt_precio.getText().equals(""))
    		{
    			alerta.setAlertType(AlertType.ERROR);
    			alerta.setContentText("Ingrese un nombre válido");
    			txt_precio.setFocusTraversable(true);
    		}	
    		else {
    			Producto p = Crear_P();
  
        		lista_panel.add(p);
            	TablaProducto.getItems().add(p);
            	//System.out.println("\nDimension boton: " + button.length + "\n");
            	alerta.setContentText("Producto " + p.getNombre() + " agregado !");
    		}
    	}
    	else
    	{
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
        		TablaProducto.getItems().removeAll(lista_panel);
        		TablaProducto.getItems().addAll(lista_panel);
        		alerta.setContentText("Datos del producto actualizado !");
        	
        	}
    	}
    	
    	Limpiar_cajas();
    	alerta.showAndWait();
    	
    	Generar_id();
    	Cargar_eventos_botones();
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
    }
    
    private long Generar_id() 
    {
    	long id = lista_panel.size() + 1;
    	
    	return id;
    }
      
    private void handleButtonAction(ActionEvent event)
    {
    	
    	for(int i = 0; i < button.length; i++)
    	{
    		if(event.getSource() == button[i])
        	{
    			System.out.println("Producto: " + lista_panel.get(i).getNombre());
    			Producto aux = lista_panel.get(i);
        		
    			id_general = aux.getId();
        		txt_producto.setText(aux.getNombre());
            	txt_precio.setText(String.valueOf(aux.getPrecio()));
            	txt_descripcion.setText(aux.getDescripcion());;
            	txt_marca.setText(aux.getMarca());;
            	txt_url.setText(aux.getUrlimage());
            	
            	Genero genero = aux.getGeneroObjetivo();
            	combo_genero.setPromptText(genero.name());
            	combo_genero.setValue(genero.name());
            	//System.out.println(genero.name());
        	}
    	}
    	
    	//Acciones para el botón eliminar
    	int i = 0;
    	for(i = 0; i < lista_panel.size(); i++)
    	{
    		if(event.getSource() == delete[i])
    		{
    			//TablaProducto.getItems().remove(i);
    			//lista_panel.remove(i);
    			
    			lista_panel.get(i).setEstado("I");
    			System.out.println("Ha eliminado " + (TablaProducto.getItems().get(i).getNombre()));
    			break;
    		}
    	}
    	TablaProducto.getItems().removeAll(lista_panel);
    	Llenar_Tabla();
    	Actualizar_tabla();
    }
    
    private void Actualizar_tabla() 
    {
		//button = new Button[lista_panel.size()];
		//delete = new Button[lista_panel.size()];
		
		//System.out.println("Dimension de botones: " + button.length);
		System.out.println("Lista actual");
		
		for(Producto i:lista_panel)
			if(i.getEstado().equals("A"))
				System.out.println("Producto: "+ i.getNombre());
		
		
	}

	private Boolean Existe_id(long id)
    {
    	Boolean existe = false; 
    	
    	for(int i = 0; i < lista_panel.size(); i++)
    	{
    		Producto aux = lista_panel.get(i);
    		existe = (id==aux.getId()) ? true:false;
    		
    		if(existe) break;
    	}
    	return existe;
    }
	public void CerrarSesion(ActionEvent event) {
		
		Stage stage = (Stage) ((Parent) event.getSource()).getScene().getWindow();		
		stage.close();
		
		ch.MostrarVista("/Viewintrotienda.fxml", "Lista de Productos", false);
		//Scene scene = new Scene(root,640,360);
	}
	
}

