package Produtos;

public class Marca {
	
	private long codigo; 
	private String marca; 
	private String estado;
	
	public Marca(long codigo, String marca, String estado) {
		super();
		this.codigo = codigo;
		this.marca = marca;
		this.estado = estado;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public long getCodigo() {
		return codigo;
	}

	@Override
	public String toString() {
		return marca;
	} 
	
}
