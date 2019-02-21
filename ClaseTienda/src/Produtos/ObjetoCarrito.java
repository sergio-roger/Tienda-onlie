package Produtos;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ObjetoCarrito {
	
	private Producto p; 
	private SimpleStringProperty nombre; 
	private SimpleDoubleProperty precio;
	private SimpleIntegerProperty cantidad;
	private SimpleDoubleProperty monto;
	
	

	public ObjetoCarrito(Producto p, Integer cantidad) {
		super();
		
		this.p = p;
		this.cantidad = new  SimpleIntegerProperty(cantidad);
		this.nombre = new  SimpleStringProperty(p.getNombre());
		this.precio = new  SimpleDoubleProperty(p.getPrecio());
		this.monto = new  SimpleDoubleProperty();
		NumberBinding multiplicacionPrecioCantidad = Bindings.multiply(this.getCantidadProperty(), this.getPrecio());
		this.monto.bind(multiplicacionPrecioCantidad);
	
	}
	public Producto getP() {
		return p;
	}
	
	public void setP(Producto p) {
		this.p = p;
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