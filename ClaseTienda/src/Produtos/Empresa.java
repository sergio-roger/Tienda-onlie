package Produtos;

public class Empresa {
	
	private String ruc; 
	private String nombre;
	private String direccion;
	private String representate_legal;
	private String telefono;
	private String email;
	
	public Empresa(String ruc, String direccion, String nombre) {
		super();
		this.ruc = ruc;
		this.direccion = direccion;
		this.nombre = nombre;
	}
	
	public Empresa(String ruc, String nombre, String direccion, String representate_legal, String telefono,
			String email) {
		super();
		this.ruc = ruc;
		this.nombre = nombre;
		this.direccion = direccion;
		this.representate_legal = representate_legal;
		this.telefono = telefono;
		this.email = email;
	}

	public String getRepresentate_legal() {
		return representate_legal;
	}

	public void setRepresentate_legal(String representate_legal) {
		this.representate_legal = representate_legal;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRuc() {
		return ruc;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Empresa [ruc=" + ruc + ", representate_legal=" + representate_legal + ", direccion=" + direccion
				+ ", telefono=" + telefono + ", nombre=" + nombre + "]";
	}
	
}
