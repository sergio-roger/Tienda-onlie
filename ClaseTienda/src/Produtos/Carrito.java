package Produtos;

import java.util.ArrayList;
import java.util.List;

import Autentificacion.Usuario;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

public class Carrito {
	
	private long id; 
	private SimpleDoubleProperty total  = new SimpleDoubleProperty();
	private List<Objeto_Carrito> listaobjetocarrito = new ArrayList<Objeto_Carrito>();
	private Usuario cliente;
	
	public Carrito(long id)
	{
		super(); 
		this.id = id;
		//this.listaobjetocarrito = listaobjetocarrito;
	}
	
	public void Facturar()
	{
		Factura f = new Factura();
		
		f.Generar_pdf(this);
	}
	
	private class Envio	//Clase anidada no estatica
	{
		private String direccion; 
	}
	
	public static class Objeto_Carrito{		//Clase anidada no estática 

		private Producto p; 
		private SimpleStringProperty nombre; 
		private SimpleDoubleProperty precio;
		private SimpleIntegerProperty cantidad;
		private SimpleDoubleProperty monto;
		private Button btn_eliminar;

		public Objeto_Carrito(Producto p, Integer cantidad) {
			super();
			
			this.p = p;
			this.cantidad = new  SimpleIntegerProperty(cantidad);
			this.nombre = new  SimpleStringProperty(p.getNombre());
			this.precio = new  SimpleDoubleProperty(p.getPrecio());
			this.monto = new  SimpleDoubleProperty();
			NumberBinding multiplicacionPrecioCantidad = Bindings.multiply(this.getCantidadProperty(), this.getPrecio());
			this.monto.bind(multiplicacionPrecioCantidad);
			this.btn_eliminar = new Button("  X  ");
		
		}
		public Producto getP() {
			return p;
		}
		
		public void setP(Producto p) {
			this.p = p;
		}
		
		public Button getBtn_eliminar() {
			return btn_eliminar;
		}
		public void setBtn_eliminar(Button btn_eliminar) {
			this.btn_eliminar = btn_eliminar;
		}
		
		public String getNombre() {
			return nombre.get();
		}

		public Double getMonto() {
			return monto.get();
		}
		
		public int getCantidad() {
			return cantidad.get();
		}
		
		public SimpleStringProperty getNombreProperty() {
			return nombre;
		}
		
		public void setNombre(SimpleStringProperty nombre) {
			this.nombre = nombre;
		}
		
		public Double getPrecio() {
			return precio.get();
		}
		
		public void setPrecio(SimpleDoubleProperty precio) {
			this.precio = precio;
		}
		
		public SimpleIntegerProperty getCantidadProperty() {
			return cantidad;
		}
		
		public void setCantidad(SimpleIntegerProperty cantidad) {
			this.cantidad = cantidad;
		}
		
		public SimpleDoubleProperty getMontoProperty() {
			return monto;
		}
		
		public void setMonto(SimpleDoubleProperty monto) {
			this.monto = monto;
		}
	}

	public SimpleDoubleProperty calcular_toal()
	{
		Double total_calculado = 0.0; 
	
		for(Objeto_Carrito o: listaobjetocarrito)
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
	
	public List<Objeto_Carrito> getListObjetoCarrito(){
		return this.listaobjetocarrito;
	}

	public SimpleDoubleProperty getTotal() {
		return total;
	}

	public List<Objeto_Carrito> getListaobjetocarrito() {
		return listaobjetocarrito;
	}

	public void setListaobjetocarrito(List<Objeto_Carrito> listaobjetocarrito) {
		this.listaobjetocarrito = listaobjetocarrito;
	}
	
	
}
