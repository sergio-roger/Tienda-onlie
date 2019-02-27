package Produtos;

import java.time.LocalDate;

public class Envio {
	
	private long codigo_guia;
	private String destinatario;
	private String direccion;
	private LocalDate fecha_envio;
	
	public Envio(long codigo_guia, String destinatario, String direccion, LocalDate fecha_envio) {
		super();
		this.codigo_guia = codigo_guia;
		this.destinatario = destinatario;
		this.direccion = direccion;
		this.fecha_envio = fecha_envio;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public LocalDate getFecha_envio() {
		return fecha_envio;
	}

	public void setFecha_envio(LocalDate fecha_envio) {
		this.fecha_envio = fecha_envio;
	}

	public long getCodigo_guia() {
		return codigo_guia;
	}

	@Override
	public String toString() {
		return "Envio [codigo_guia=" + codigo_guia + ", destinatario=" + destinatario + ", direccion=" + direccion
				+ "]";
	}
	
}
