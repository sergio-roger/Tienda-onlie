package Controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry.Entry;

import Produtos.Carrito;
import Produtos.ObjetoCarrito;
import Produtos.Producto;
import Produtos.ProductoAux;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.NumberStringConverter;

public class ControllerCarrito {
	
	@FXML private TableView<ObjetoCarrito> tabla_carrito;

    @FXML private Label lbl_total;
	
	public static List<Producto> lista;
	
	public void initialize()
	{
		//Cargar_carrito();
		Map<Producto, Integer> mapConsolidarListaCarrito = ConsolidadProductosCarrito(lista);
		Carrito c =  Construir_carrito(mapConsolidarListaCarrito);
		Cargar_tabla_carrito(c);
	}
	
	private void Mockear_comprar() {
		
		List<Producto> listacompras = new ArrayList<Producto>();
	
		
		ObservableList<Producto> listaobservable = FXCollections.observableArrayList(listacompras);
		//listaproducto.setItems(listaobservable);
	}
	
	/* private void Cargar_carrito() {
		
		double total = 0; 
		int cantidad = 0;
		
		TableColumn<ProductoAux, Integer> col_cantidad = new TableColumn<>("Cantidad");
		TableColumn<ProductoAux, String> col_nombre = new TableColumn<>("Producto        ");
		TableColumn<ProductoAux, Double> col_unitario = new TableColumn<>("P. Unitario");
		TableColumn<ProductoAux, Double> col_total = new TableColumn<>("Total");
		
		tabla_carrito.getColumns().addAll(col_cantidad, col_nombre, col_unitario, col_total);
		
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
            
        	tabla_carrito.getItems().add(aux);
        	
        	double aux_t = aux.getCantidad() * aux.getPrecio_unitario(); 
        	total = total + aux_t;
        }
        
        lbl_total.setText("Total a pagar:  $" + String.valueOf(total));
		System.out.println("-------------------------------------------------------------\n");
		
		}
	*/
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
		
			System.out.println(mapconsolidad);
			
			return mapconsolidad;
		}
		
		private void Cargar_tabla_carrito(Carrito c)
		{
			//int edit_cant ;
			
			ObservableList<ObjetoCarrito> lista_compra_observable = FXCollections.observableArrayList(c.getListaobjetocarrito());
			
			TableColumn<ObjetoCarrito, Integer> col_cantidad = new TableColumn<>("Cantidad");
			TableColumn<ObjetoCarrito, String> col_nombre = new TableColumn<>("Producto        ");
			TableColumn<ObjetoCarrito, Double> col_unitario = new TableColumn<>("P. Unitario");
			TableColumn<ObjetoCarrito, Double> col_total = new TableColumn<>("Total");
			
			tabla_carrito.getColumns().addAll(col_cantidad, col_nombre, col_unitario, col_total);
			
			col_cantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));	col_cantidad.setEditable(true);
			
			col_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));		col_nombre.setEditable(false);
			col_unitario.setCellValueFactory(new PropertyValueFactory<>("precio"));		col_unitario.setEditable(false);
			col_total.setCellValueFactory(new PropertyValueFactory<>("monto"));			col_total.setEditable(false);
			
			col_cantidad.setCellFactory(TextFieldTableCell.<ObjetoCarrito, Integer>forTableColumn(new IntegerStringConverter()));
			
			col_cantidad.setOnEditCommit(
					
					new EventHandler<TableColumn.CellEditEvent<ObjetoCarrito,Integer>>() {
						
						@Override
						public void handle(CellEditEvent<ObjetoCarrito, Integer> event) {
							// TODO Auto-generated method stub
							ObjetoCarrito o = (ObjetoCarrito)event.getTableView().getItems().get(event.getTablePosition().getRow());
							
							o.setCantidad(new SimpleIntegerProperty(event.getNewValue().intValue()));
							o.setMonto(new SimpleDoubleProperty(o.getCantidad() * o.getPrecio()));
							System.out.println("Monot es: " + o.getMontoProperty());
						}
					
					}
			);
			
			
			// System.out.println(lista_compra_observable);
			tabla_carrito.setItems(lista_compra_observable);
		}
		
		private Carrito Construir_carrito(Map<Producto, Integer> mapa_consolidad_carrito)
		{
			
			Carrito c = new Carrito(1);
			List<ObjetoCarrito> lista_objeto_carrito = new ArrayList<ObjetoCarrito>();
			
			for(Producto p: mapa_consolidad_carrito.keySet())
			{
				ObjetoCarrito objeto_carrito = new ObjetoCarrito(p, mapa_consolidad_carrito.get(p));
				lista_objeto_carrito.add(objeto_carrito);
			}
			
			System.out.println();
			System.out.println("Lista objeto carrito ** ");
			System.out.println();
			c.setListaobjetocarrito(lista_objeto_carrito);
			return c;
		}
}
