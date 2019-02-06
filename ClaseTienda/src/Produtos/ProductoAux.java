package Produtos;

public class ProductoAux {

	private Integer cantidad; 
	private String nombre; 
	private Double precio_unitario;
	private Double total;
	
	
	public ProductoAux(int cantidad, String nombre, double precio_unitario, double total) {
		super();
		this.cantidad = cantidad;
		this.nombre = nombre;
		this.precio_unitario = precio_unitario;
		this.total = total;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPrecio_unitario() {
		return precio_unitario;
	}

	public void setPrecio_unitario(Double precio_unitario) {
		this.precio_unitario = precio_unitario;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	} 
	
}
