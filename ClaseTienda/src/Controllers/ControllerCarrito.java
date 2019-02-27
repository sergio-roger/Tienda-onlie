package Controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sound.midi.Soundbank;

import Produtos.Carrito;
import Produtos.ObjetoCarrito;
import Produtos.Producto;
import Produtos.ProductoAux;
import application.Main;
import Produtos.Carrito.Objeto_Carrito;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;

public class ControllerCarrito {
	
	@FXML private TableView<Carrito.Objeto_Carrito> tabla_carrito;
    @FXML private Button btn_finalizar;
    
    private int dim_button = 0;
	private static List<Producto> lista;
	private List<Carrito.Objeto_Carrito> lista_carrito;
	private ArrayList<Button> vector_btn_eliminar = new ArrayList<>();
	public static Carrito c; 
	
	public void initialize()
	{
		lista = ControllerListaProducto.listaProductosCarrito;
		Carrito_defecto(lista);
	}
	
	private void Carrito_defecto(List<Producto> productos)
	{
		if(lista == null || lista.isEmpty())
		{
			btn_finalizar.setVisible(false);
		}
		else
		{
			Cargar_lista_original(lista);
			Refrescar_tabla(lista);	
			btn_finalizar.setVisible(true);
		}
	}
	
	private void Refrescar_tabla(List<Producto> productos)
	{
		Map<Producto, Integer> mapConsolidarListaCarrito = ConsolidadProductosCarrito(productos);
		c =  Construir_carrito(mapConsolidarListaCarrito);
		Cargar_tabla_carrito(c);
		lista_carrito = c.getListaobjetocarrito();	//Añadiendo la lista del objeto carrito a una lista carrito
		Cargar_eventos_botones( c.getListaobjetocarrito());
	}
	
	private void Cargar_lista_original(List<Producto> productos)
	{
		System.out.println();
		
		for(Producto i: productos)
			System.out.println(i.getNombre());
	}
	
	private void Cargar_carrito() {
		
		double total = 0; 
		int cantidad = 0;
		
		TableColumn<ProductoAux, Integer> col_cantidad = new TableColumn<>("Cantidad");
		TableColumn<ProductoAux, String> col_nombre = new TableColumn<>("Producto        ");
		TableColumn<ProductoAux, Double> col_unitario = new TableColumn<>("P. Unitario");
		TableColumn<ProductoAux, Double> col_total = new TableColumn<>("Total");
		
		//tabla_carrito.getColumns().addAll(col_cantidad, col_nombre, col_unitario, col_total);
		
		col_cantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
		col_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		col_unitario.setCellValueFactory(new PropertyValueFactory<>("precio_unitario"));
		col_total.setCellValueFactory(new PropertyValueFactory<>("total"));
		
		
		System.out.println("-------------------------------------------------------------\n");
		
		Set<Producto> quipu = new HashSet<Producto>(lista);
		
        for (Producto key : quipu) 
        {
        	cantidad = Collections.frequency(lista, key); 
            System.out.println(key + " : " + cantidad);
            ProductoAux aux = new ProductoAux(cantidad, key.getNombre(), key.getPrecio(), cantidad * key.getPrecio());
            
        	//tabla_carrito.getItems().add(aux);
        	
        	double aux_t = aux.getCantidad() * aux.getPrecio_unitario(); 
        	total = total + aux_t;
        }
        System.out.println();
		System.out.println("-------------------------------------------------------------\n");
		
		}
	
	private Map<Producto,Integer> ConsolidadProductosCarrito(List<Producto> listacarrito)
	{
		Map<Producto,Integer> mapconsolidad = new HashMap<Producto, Integer>();
		
		
		System.out.println("*********** Mapa consolidado");
		
		for( Producto p: listacarrito)
		{
			if(mapconsolidad.containsKey(p))
			{
				Integer cont_actual_produto = mapconsolidad.get(p);
				Integer cont_nuevo_producto = cont_actual_produto + 1; 
				mapconsolidad.put(p, cont_nuevo_producto);
				System.out.println("Map tiene cantidad " + cont_nuevo_producto + " del objeto" + p);
			}
			else
			{
				mapconsolidad.put(p, 1);
				System.out.println("Map tiene cantidad " + 1 + " del objeto " + p);
			}
		}
		System.out.println(mapconsolidad);		return mapconsolidad;
	}
	
	private void Cargar_eventos_botones(List<Carrito.Objeto_Carrito> lista_c)
	{
		Button btn_eliminar;
		dim_button = 0;
		
		//System.out.println("Dimension de botones: " + lista.size());
		
		while(dim_button < lista_c.size())
		{
			btn_eliminar = lista_c.get(dim_button).getBtn_eliminar();
    		btn_eliminar.setOnAction(this::handleButtonAction);
    		btn_eliminar.getStyleClass().add("btn");
    		btn_eliminar.getStyleClass().add("rojo");
    		
    		vector_btn_eliminar.add(btn_eliminar);
    		dim_button++;
		}
		System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::");
		System.out.println("Dimension del array button: " + vector_btn_eliminar.size());
		System.out.println("Dimension lista original: " + lista.size());
		System.out.println("Dimension lista carrito: " + lista_c.size());
	}
	
	private void handleButtonAction(ActionEvent event)
	{
		int a = 0; Carrito car_aux = new Carrito(0);
		Producto aux = null;
		List<Producto> lista_aux = new ArrayList<>();
		
		//Acciones para eliminar un producto de la lista carrito 
    	while(a < vector_btn_eliminar.size())
    	{
    		if(event.getSource() == vector_btn_eliminar.get(a))
    		{
    			System.out.println("Butonn " + (a + 1) + " presionado");
    			aux = lista_carrito.get(a).getP();
    			int can = lista_carrito.get(a).getCantidad();
    			
    			//Eliminar todos los productos de la lista original 
    			System.out.println("Producto: " + aux.toString());
    			System.out.println("Cantidad: " + can);
    			
    			for(int i = 0; i < lista.size(); i++)
    			{
    				if(lista.get(i).equals(aux))
    					continue;
    				else
    					lista_aux.add(lista.get(i));
    			}
    			lista.clear();
    			
    			for(Producto i: lista_aux)
    				lista.add(i);
    			
    			vector_btn_eliminar.clear();
    			Refrescar_tabla(lista);
    		}
    		a++;
    	}
	}
	
	@SuppressWarnings("unchecked")
	private void Cargar_tabla_carrito(Carrito c)
	{	
			//tabla_carrito.getColumns().removeAll(c.getListaobjetocarrito());
			tabla_carrito.getColumns().clear();
			
			ObservableList<Carrito.Objeto_Carrito> lista_compra_observable = FXCollections.observableArrayList(c.getListObjetoCarrito());
			
			TableColumn<Produtos.Carrito.Objeto_Carrito, Integer> col_cantidad = new TableColumn<>("Cantidad");
			TableColumn<Produtos.Carrito.Objeto_Carrito, String> col_nombre = new TableColumn<>("Producto        ");
			TableColumn<Produtos.Carrito.Objeto_Carrito, Double> col_unitario = new TableColumn<>("P. Unitario");
			TableColumn<Produtos.Carrito.Objeto_Carrito, Double> col_total = new TableColumn<>("Total");
			TableColumn<Produtos.Carrito.Objeto_Carrito, String> btn_eliminar = new TableColumn<>("Eliminar");
			
			tabla_carrito.getColumns().addAll(col_cantidad, col_nombre, col_unitario, col_total, btn_eliminar);
			
			col_cantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));	col_cantidad.setEditable(true);
			
			col_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));			col_nombre.setEditable(false);
			col_unitario.setCellValueFactory(new PropertyValueFactory<>("precio"));			col_unitario.setEditable(false);
			col_total.setCellValueFactory(new PropertyValueFactory<>("monto"));				col_total.setEditable(false);
			btn_eliminar.setCellValueFactory(new PropertyValueFactory<>("btn_eliminar"));	btn_eliminar.setEditable(false);
			
			col_cantidad.setCellFactory(TextFieldTableCell.<Carrito.Objeto_Carrito, Integer>forTableColumn(new IntegerStringConverter()));
			col_cantidad.setOnEditCommit(
					
					new EventHandler<TableColumn.CellEditEvent<Produtos.Carrito.Objeto_Carrito,Integer>>() {
						
						@Override
						public void handle(CellEditEvent<Produtos.Carrito.Objeto_Carrito, Integer> event) {
							// TODO Auto-generated method stub
							Produtos.Carrito.Objeto_Carrito o = (Produtos.Carrito.Objeto_Carrito)event.getTableView().getItems().get(event.getTablePosition().getRow());
							
							o.setCantidad(new SimpleIntegerProperty(event.getNewValue().intValue()));
							o.setMonto(new SimpleDoubleProperty(o.getCantidad() * o.getPrecio()));
							
							//col_total.setCellValueFactory(new PropertyValueFactory<>("monto")); 
							
							System.out.println("Monto es: " + o.getMontoProperty());		
						}
					}
			);
			// System.out.println(lista_compra_observable);
			tabla_carrito.setItems(null);
			tabla_carrito.setItems(lista_compra_observable);
			//lista_carrito = lista_compra_observable;
	}
		
	private Carrito Construir_carrito(Map<Producto, Integer> mapa_consolidad_carrito)
	{	
		Carrito c = new Carrito(1);
		List<Carrito.Objeto_Carrito> lista_objeto_carrito = new ArrayList<Carrito.Objeto_Carrito>();
		
		for(Producto p: mapa_consolidad_carrito.keySet())
		{
			Carrito.Objeto_Carrito objeto_carrito = new Carrito.Objeto_Carrito(p, mapa_consolidad_carrito.get(p));
			lista_objeto_carrito.add(objeto_carrito);
		}
		
		System.out.println();
		System.out.println("Lista objeto carrito ** ");
		System.out.println();
		c.setListaobjetocarrito(lista_objeto_carrito);
		
		return c;
	}
	
	public void Finalizar_compra(ActionEvent event)
	{
		Main.carrito = c;
		
		ControllerHelper.Mostrar_Vista_Modal("/ViewEnvio.fxml", "Envio", 600, 400);
		
		Stage stage = (Stage) ((Parent) event.getSource()).getScene().getWindow();		
		stage.close();
	}
	
	public void Resetear_carrito()
	{
		lista.clear();
		tabla_carrito.setItems(null);
		System.out.println("Lista vacía");
		btn_finalizar.setVisible(false);
	}
	
	public void Volver_vista(ActionEvent event)
	{
		Stage stage = (Stage) ((Parent) event.getSource()).getScene().getWindow();		
		stage.close();
	}
}
