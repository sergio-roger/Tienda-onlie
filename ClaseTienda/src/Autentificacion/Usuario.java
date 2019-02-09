package Autentificacion;

import javafx.scene.control.Button;

public class Usuario {
	
	private long id; 
	private String cedula; 
	private String nombres;
	private String apellidos; 
	private String direccion; 
	private String celular; 
	private String correo;
	private String url_foto;
	private Rol rol;
	private String estado;
	private Autentificar autentificar;
	protected Button btn_editar;
	protected Button btn_eliminar;
	
	//Constructor para el empleado
	public Usuario(long id, String cedula, String nombres, String apellidos, String direccion, String correo, String celular, Rol rol,
			String estado) {
		
		super();
		this.id = id;
		this.cedula = cedula;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.celular = celular;
		this.correo = correo;
		this.rol = rol;
		this.estado = estado;
	    this.btn_editar = new Button("Editar");
	    this.btn_eliminar = new Button("Eliminar");
	}
	
	//Constructor para el usuario
	public Usuario(long id, String nombres, String apellidos, String correo, Rol rol, String estado) {
		super();
		this.id = id;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.rol = rol;
		this.correo = correo;
		this.estado = estado;
	    this.btn_editar = new Button("Editar");
	    this.btn_eliminar = new Button("Eliminar");
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getUrl_foto() {
		return url_foto;
	}

	public void setUrl_foto(String url_foto) {
		this.url_foto = url_foto;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Autentificar getAutentificar() {
		return autentificar;
	}

	public void setAutentificar(Autentificar autentificar) {
		this.autentificar = autentificar;
	}

	public long getId() {
		return id;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
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
}
