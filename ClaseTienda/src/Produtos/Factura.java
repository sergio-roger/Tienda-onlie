package Produtos;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;

import Autentificacion.Usuario;
import Produtos.Carrito.Objeto_Carrito;
import application.Main;

public class Factura {
	
	public static long ultimo_mumero;
	private String ruc; 
	private LocalDate fecha_emision;
	private long  numero;
	private Empresa empresa;
	private Usuario usuario; 
	private Carrito carrito;
	private Envio envio;
	private Double total;
	
	public Factura()
	{
		fecha_emision = LocalDate.now();
		empresa = Main.empresa;
		this.numero = Main.lista_factura.size() + 1;
	}
	
	public Factura(long numero, Usuario usuario, Carrito carrito, Envio envio)
	{
		this.numero = numero; 
		this.fecha_emision = LocalDate.now();
		this.usuario = usuario;
		this.carrito = carrito; 
		this.envio = envio;
	}
	
	public long getNumero() {
		return numero;
	}

	public LocalDate getFecha_emision() {
		return fecha_emision;
	}
	
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Carrito getCarrito() {
		return carrito;
	}

	public void setCarrito(Carrito carrito) {
		this.carrito = carrito;
	}
	
	public Envio getEnvio() {
		return envio;
	}

	public void setEnvio(Envio envio) {
		this.envio = envio;
	}

	public Double getSubtotal()
	{
		Double total = 0.0; 
		
		for(Objeto_Carrito i: this.carrito.getListObjetoCarrito())
			total += i.getMonto();
		
		return total;
	}
	
	public Double getIva()
	{
		Double iva; 
		NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setMaximumFractionDigits(2);
		
		numberFormat.setRoundingMode(RoundingMode.DOWN);
		
		iva = getSubtotal() * 0.12;
		String truncado = numberFormat.format(iva);
		
		iva = Pasar_doble(truncado);
		
		return iva;
	}
	
	public Double getTotal_fac()
	{
		Double total = 0.0; 
		
		total = getSubtotal() + getIva();
		
		return total;
	}
	
	private Double Pasar_doble(String numero)
	{
		char c; String dato = "";
		
		for (int n = 0; n <numero.length (); n++)
		{
			c = numero.charAt (n);
			
			if(c == ',')
				c = '.';
			
			dato +=String.valueOf(c);
		}
		return Double.parseDouble(dato);
	}
	
	public void Generar_pdf(Carrito c)
	{
		class PdfGenerator
		{
			private String cabecera;
			private String contenido; 
			private String pie;
			
			public PdfGenerator(String cabecera, String pie)
			{
				this.cabecera = cabecera; 
				this.pie = pie;
			}
			
			public void Generar_pdf(String contenido)
			{
				cabecera = empresa.getNombre() + "\n" + empresa.getRuc() + empresa.getDireccion();
				System.out.println(numero);
				System.out.println(cabecera);
			}
		}
		
		PdfGenerator generador = new PdfGenerator(empresa.getNombre(), empresa.getDireccion() + empresa.getTelefono());
		
		generador.Generar_pdf(c.toString());
	}

}
