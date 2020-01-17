package application;




import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.ChoiceBox;

public class MainController implements Initializable {
	

	
	//login
	@FXML
	private TextField usuario;
	@FXML
	private PasswordField contrasena;
	@FXML
	private Button entrar;
	@FXML
	private Label estado;

	
	//ingresar
	@FXML
	private TextField nombre_cliente;
	@FXML
	private TextField rut_cliente;
	@FXML
	private TextField direccion_cliente;
	@FXML
	private TextField contacto_cliente;
	@FXML
	private TextField patente;
	@FXML
	private TextField modelo;
	@FXML
	private TextField chasis;
	@FXML
	private TextField cilindrada;
	@FXML
	private TextField marca;
	@FXML
	private TextField ano;
	@FXML
	private TextField color;
	@FXML
	private TextField combustible;
	@FXML
	private TextArea detalle;
	@FXML
	private TextArea fallo;
	@FXML
	private TextField rut_trabajador;
	@FXML
	private TextField nombre_trabajador;
	@FXML
	private TextField direccion_trabajador;
	@FXML
	private TextField contacto_trabajador;
	
	//tienda 
	@FXML
	private TextField nombre_tienda;
	@FXML
	private TextField id_tienda;
	@FXML
	private TextField contacto_tienda;
	@FXML
	private TextField direccion_tienda;


	private Connection con = null;
	private PreparedStatement pst= null;
	private PreparedStatement pst2=null;

	public void ing_screen(MouseEvent event) throws Exception {
		Parent load = FXMLLoader.load(getClass().getResource("/application/ingresar.fxml"));
		Scene ingresar = new Scene(load);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(ingresar);
		window.show();
	}
	public void mod_screen(MouseEvent event) throws Exception {
		Parent load = FXMLLoader.load(getClass().getResource("/application/modificar.fxml"));
		Scene ingresar = new Scene(load);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(ingresar);
		window.show();
	}
	public void con_screen(MouseEvent event) throws Exception {
		Parent load = FXMLLoader.load(getClass().getResource("/application/consultar.fxml"));
		Scene ingresar = new Scene(load);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(ingresar);
		window.show();
	}
	public void volver(ActionEvent event) throws Exception{
		Parent load = FXMLLoader.load(getClass().getResource("/application/menu.fxml"));
		Scene ingresar = new Scene(load);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(ingresar);
		window.show();
	}
	public void ing_trab_screen(ActionEvent event) throws Exception{
		Parent load = FXMLLoader.load(getClass().getResource("/application/ing_trabajadores.fxml"));
		Scene ingresar = new Scene(load);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(ingresar);
		window.show();
	}
	public void ing_cli_screen(ActionEvent event) throws Exception{
		Parent load = FXMLLoader.load(getClass().getResource("/application/ing_clientes.fxml"));
		Scene ingresar = new Scene(load);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(ingresar);
		window.show();
	}
	public void ing_tienda_screen(ActionEvent event) throws Exception{
		Parent load = FXMLLoader.load(getClass().getResource("/application/ing_tienda.fxml"));
		Scene ingresar = new Scene(load);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(ingresar);
		window.show();
	}
	
	public void lbl_glow(MouseEvent event) throws Exception{
		//En construccion
	}
	
	public void verify(ActionEvent event) throws Exception {
		Stage PrimaryStage = new Stage();
		if (usuario.getText().equals("Alan") && contrasena.getText().equals("1234")) {
			estado.setTextFill(Color.GREEN);
			estado.setText("Bienvenid@");
			
			Parent root = FXMLLoader.load(getClass().getResource("/application/menu.fxml"));
			Scene scene =  new Scene(root);
			PrimaryStage.setTitle("Black Wheels");
			PrimaryStage.setScene(scene);
			PrimaryStage.setResizable(false);
			PrimaryStage.show();
		}
		else {
			estado.setTextFill(Color.RED);
			estado.setText("ERROR: Usuario o contraseña incorrecta");
			
		}
	}
	
	
	@Override
	public void initialize(URL url,ResourceBundle rb) {
		con = conpckg.ConnectionClass.JTN() ;
		
	}
	
	public static boolean isTextFieldEmpty(TextField txt) {
		if(txt.getText().isEmpty())
			return true;
		return false;
	}
	
	public void workertodb(ActionEvent event) throws SQLException {
		String sql = "INSERT INTO Mecanico(Rut,Nombre,Direccion,Numero_de_telefono) VALUES(?,?,?,?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, rut_trabajador.getText());
			pst.setString(2, nombre_trabajador.getText());
			pst.setString(3, direccion_trabajador.getText());
			pst.setString(4, contacto_trabajador.getText());
			
			int i = pst.executeUpdate();
			if(i==1)
				System.out.println("Datos ingresados con exito");
	
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally {
		pst.close();
	}
				
}
	public void storetodb(ActionEvent event) throws SQLException {
		String sql = "INSERT INTO Tienda(CodTienda,Nombre,Numero_de_telefono,Direccion) VALUES (?,?,?,?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, id_tienda.getText());
			pst.setString(2, nombre_tienda.getText());
			pst.setString(3, contacto_tienda.getText());
			pst.setString(4, direccion_tienda.getText());
			
			int i= pst.executeUpdate();
			if(i==1)
				System.out.println("Datos ingresados con exito");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			pst.close();
		}
	}
	
	
	public void cartodb() throws SQLException {
		String sql = "INSERT INTO Vehiculo(Patente,Ano,Numero_de_chasis,Cilindrada,Tipo_de_combustible) VALUES(?,?,?,?,?)";
		pst = con.prepareStatement(sql);
		pst.setString(1,patente.getText());
		pst.setString(2, ano.getText());
		pst.setString(3, chasis.getText());
		pst.setString(4, cilindrada.getText());
		pst.setString(5, combustible.getText());
		
	}
	
	public void problemtodb() throws SQLException {
		String sql = "INSERT INTO Utiliza(Tipo_fallo) VALUES (?)";
		pst = con.prepareStatement(sql);
		sql = "INSERT INTO DetalleUtiliza(Descripcion) VALUES (?)";
		pst2 = con.prepareStatement(sql);
		pst.setString(1, fallo.getText());
		pst2.setString(1, detalle.getText());
		
		
	}
	
	public void clienttodb() throws SQLException {
		String sql = "INSERT INTO Cliente(Rut,Nombre,Direccion,Numero_de_contacto) VALUES(?,?,?,?)";
		
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, rut_cliente.getText());
			pst.setString(2, nombre_cliente.getText());
			pst.setString(3, direccion_cliente.getText());
			pst.setString(4, contacto_cliente.getText());
			
			
			int i = pst.executeUpdate();
			if(i==1)
				System.out.println("Datos ingresados con exito");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			pst.close();
		}
		
	}
	public void ingreso_cliente(ActionEvent event) throws SQLException{
		clienttodb();
		cartodb();
		problemtodb();
		System.out.println("Datos ingresados con exito");
		
	}
	
}	
