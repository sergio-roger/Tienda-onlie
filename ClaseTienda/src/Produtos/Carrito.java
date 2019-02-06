package Produtos;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleDoubleProperty;

public class Carrito {
	
	private long id; 
	private SimpleDoubleProperty total  = new SimpleDoubleProperty();
	private List<ObjetoCarrito> listaobjetocarrito = new ArrayList<ObjetoCarrito>();
	
	public Carrito(long id)
	{
		super(); 
		this.id = id;
		//this.listaobjetocarrito = listaobjetocarrito;
	}
	
	public SimpleDoubleProperty calcular_toal()
	{
		
		Double total_calculado = 0.0; 
		
		for(ObjetoCarrito o: listaobjetocarrito)
		{
			total_calculado = Double.sum(total_calculado, o.getMonto());
		}
		this.total = new SimpleDoubleProperty(total_calculado);
		return this.total;
		
	}
	
	public long getId()
	{
		return this.id;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}
	
	public List<ObjetoCarrito> getListObjetoCarrito(){
		return this.listaobjetocarrito;
	}

	public SimpleDoubleProperty getTotal() {
		return total;
	}

	public List<ObjetoCarrito> getListaobjetocarrito() {
		return listaobjetocarrito;
	}

	public void setListaobjetocarrito(List<ObjetoCarrito> listaobjetocarrito) {
		this.listaobjetocarrito = listaobjetocarrito;
	}
	
	
}
