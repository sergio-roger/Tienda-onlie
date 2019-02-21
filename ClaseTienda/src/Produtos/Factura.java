package Produtos;

import java.time.LocalDate;

import application.Main;

public class Factura {
	
	public static long ultimo_mumero;
	private String ruc; 
	private LocalDate fecha_emision;
	private long  numero;
	private Empresa empresa;
	
	public Factura()
	{
		fecha_emision = LocalDate.now();
		numero =  ultimo_mumero + 1; 
		ultimo_mumero++;
		empresa = Main.empresa;
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
