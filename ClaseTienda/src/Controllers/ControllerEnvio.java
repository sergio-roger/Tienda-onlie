package Controllers;

import java.time.LocalDate;

import Autentificacion.Usuario;
import Produtos.Envio;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ControllerEnvio {


    @FXML private Label lbl_guia;
    @FXML private TextField txt_destinatario;
    @FXML private DatePicker fecha_envio;
    @FXML private TextArea txt_direccion;
    @FXML private RadioButton rdb_midireccion;
    @FXML private RadioButton rdb_otradireccion;
    
    private ToggleGroup grupo = new ToggleGroup();
    private Usuario actual = null;
    public static Envio env = null;
    
	public void initialize()
	{
		actual = ControllerListaProducto.usuario_actual;
		lbl_guia.setText(String.valueOf(id_envio()));
		
		RadioDefault();
	}
    
	private long id_envio()
	{
		long id = Main.lista_envio_main.size() + 1;
		return id;
	}
	
	private void RadioDefault(){
		 rdb_midireccion.setToggleGroup(grupo);
		 rdb_otradireccion.setToggleGroup(grupo);
		 
		 rdb_midireccion.setUserData("mio");
		 rdb_otradireccion.setUserData("otro");
		 
		 rdb_otradireccion.setSelected(true);
		 rdb_midireccion.setSelected(false);
	}
	
	public void Siguiente(ActionEvent event)
	{
		long id = Long.parseLong(lbl_guia.getText());
		String destinatario, direccion; 	LocalDate f_e = null;
		Boolean fecha = false;
		
		Alert alerta  = new Alert(AlertType.ERROR); 
		alerta.setHeaderText(null);
		
		if(txt_destinatario.getText() == null | txt_destinatario.getText().isEmpty())
			destinatario = "";
		else
			destinatario = txt_destinatario.getText();
		
		if(txt_direccion.getText() == null || txt_direccion.getText().isEmpty())
			direccion = "";
		else
			direccion = txt_direccion.getText();
		
		if(fecha_envio.getValue() == null)
			fecha = false;
		else
		{
			fecha = true;
			f_e = fecha_envio.getValue();
		}
		
		if(destinatario.equals(""))
		{
			alerta.setContentText("Igrese un destinatario");
			alerta.showAndWait();
		}
		else
		if(direccion.equals(""))
		{
			alerta.setContentText("Igrese una direccion !");
			alerta.showAndWait();
		}
		else
		if(!fecha)
		{
			alerta.setContentText("Igrese una fecha !");
			alerta.showAndWait();
		}
		else
		{
			env  = new Envio(id, destinatario, direccion, f_e);
			Main.lista_envio_main.add(env);
			ControllerHelper.Mostrar_Vista_Modal("/ViewFactura.fxml", "Store Online",616, 705);
			System.out.println(env.toString());
			
			Stage stage = (Stage) ((Parent) event.getSource()).getScene().getWindow();		
			stage.close();
		}
		
	}
	
	public void Borrar()
	{
		if(grupo.getSelectedToggle().isSelected())
		{	
			if(grupo.getSelectedToggle().getUserData().equals("mio"))
			{
				fecha_envio.setValue(null);
				fecha_envio.getEditor().clear();
			}
			else
			{
				txt_destinatario.clear();
				txt_direccion.clear();
				fecha_envio.setValue(null);
				fecha_envio.getEditor().clear();
			}
		}
	}
	
	public void Volver_vista(ActionEvent event)
	{
		actual = null;
		
		Stage stage = (Stage) ((Parent) event.getSource()).getScene().getWindow();		
		stage.close();
	}
	
	public void Mi_direccion()
	{
		txt_destinatario.setEditable(false);
		txt_direccion.setEditable(false);
		fecha_envio.setEditable(false);
		
		if(!txt_destinatario.equals(""))
			txt_destinatario.setText(actual.getNombres() + " " + actual.getApellidos());
		
		if(!txt_direccion.equals(""))
			txt_direccion.setText(actual.getDireccion());
	}
	
	public void Otra_direccion()
	{
		txt_destinatario.setEditable(true);
		txt_direccion.setEditable(true);
		fecha_envio.setEditable(true);
		
		txt_destinatario.clear();
		txt_direccion.clear();
	}
}
