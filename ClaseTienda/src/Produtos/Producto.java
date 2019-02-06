package Produtos;

import javafx.scene.control.Button;

public class Producto {
	
	protected long id; 
	protected double precio; 
	protected String nombre, marca, descripcion;
	protected Genero generoObjetivo;
	protected String urlimage;
	protected Button btn_editar;
	protected Button btn_eliminar;
	protected String estado;
	
	public Producto(long id, String nombre, double precio) {
		
		this.id = id; 
		this.nombre = nombre; 
		this.precio = precio;
	}
	
	public Producto(long id, double precio, String nombre, String descripcion, String urlimage) {
		super();
		this.id = id;
		this.precio = precio;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.urlimage = urlimage;
	}
	
	public Producto(long id, String nombre, double precio, String descripcion, String marca, String urlimage, Genero generoObjetivo, String estado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.descripcion = descripcion;
		this.marca = marca;
	    this.urlimage = urlimage;
	    this.estado = estado;
	    this.generoObjetivo = generoObjetivo;
	    this.btn_editar = new Button("Editar");
	    this.btn_eliminar = new Button("Eliminar");
	}
	
	
	public long getId() {
		return id;
	}

	public String getUrlimage() {
		return urlimage;
	}

	public void setUrlimage(String urlimage) {
		this.urlimage = urlimage;
	}
	
	public static final double tasaimpuesto = 0.12;
	

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Genero getGeneroObjetivo() {
		return generoObjetivo;
	}

	public void setGeneroObjetivo(Genero generoObjetivo) {
		this.generoObjetivo = generoObjetivo;
	}
	
	public Button getBtn_editar() {
		return btn_editar;
	}

	public void setBtn_editar(Button btn_editar) {
		this.btn_editar = btn_editar;
	}

	public Button getBtn_eliminar() {
		return btn_eliminar;
	}

	public void setBtn_eliminar(Button btn_eliminar) {
		this.btn_eliminar = btn_eliminar;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {		
		return this.nombre;
	}
	
}
