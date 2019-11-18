package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.ResourceBundle;

import App_main.AppConexion;
import App_main.RootAapp;
import Model.ConexionModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.ComboBox;

import javafx.scene.control.PasswordField;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import other.classes.ManageResidenciasEscolares;

public class ConexionController implements Initializable {
	
	private ConexionModel model = new ConexionModel();
	
	
	@FXML
	private VBox root;
	@FXML
	private ComboBox<String> cmbTipo;
	@FXML
	private TextField txtUri;
	@FXML
	private TextField txtUser;
	@FXML
	private Button btnConectar;
	@FXML
	private PasswordField txtPassword;
	
	private ObservableList<String> listCombo;	
	
	//Others atributtes	
	private ArrayList<String> listaTiposBD;	
	private ManageResidenciasEscolares mre; 
	
	//Connection atributtes	
	private String uriBD;
	private String user;
	private String password;
	char typeConex;
	
	
	//Para recibir inyectcion	
	private AppConexion app;

	
	
	//Constructor
	
	public ConexionController() throws IOException {		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/conexionView.fxml"));
		loader.setController(this);
		loader.load();
	}
	
	
	//Inyectors
	
	public void injectApp(AppConexion app){ //Inyector from AppConexion
        this.app = app;
    }
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//Bindeos		
		model.txtUriProperty().bindBidirectional(txtUri.textProperty());
		model.txtUserProperty().bindBidirectional(txtUser.textProperty());
		model.txtPasswordProperty().bindBidirectional(txtPassword.textProperty());
		model.listaComboProperty().bindBidirectional(cmbTipo.itemsProperty());
		model.bdSeleccionadaProperty().bind(cmbTipo.getSelectionModel().selectedItemProperty());
		//Listeners propios
		model.txtUserProperty().addListener((o, ov, nv)->onActivarButtonConectar(nv)); //Activar boton conectar
		model.txtUriProperty().addListener((o, ov, nv)->onActivarButtonConectar(nv)); //Activar boton conectar
		model.bdSeleccionadaProperty().addListener((o, ov, nv)->onActivarButtonConectar(nv)); //Activar boton conectar
		
		//Acciones
		addBDslista(); //Añadir elementos a la lista de BD del combo
				
		
	}
	
	
	private void onActivarButtonConectar(String nv) {
		try {
			if(model.getTxtUser().length()>0 && model.getTxtUri().length()>0 && model.getBdSeleccionada().length()>0) {
				btnConectar.setDisable(false);
			}else {
				btnConectar.setDisable(true);
			}			
		} catch (Exception e) {
			//Falsa alarma salta al no haber nada seleccionado en el combo
			//No es necesario manejar esta excepción
		}
		
	}


	// Event Listener on Button[#btnConectar].onAction
	@FXML
	public void clickBtnConectar(ActionEvent event) {
		
		 typeConex = 'x';
		
		boolean conexionOk = false;
		
		switch (model.getBdSeleccionada()) {
		case "Mysql":
			typeConex = 'a';	
			System.out.println("BD seleccionada: Mysql");
			break;
		case "SQL server":
			typeConex = 'b';	
			System.out.println("BD seleccionada SQL Server");
			break;
		case "Base":
			typeConex = 'c';	
			System.out.println("Bd seleccionada Base");
			break;

		default:
			break;
		}
		
		
		try {
		ManageResidenciasEscolares mne = new ManageResidenciasEscolares(); //Instanciamos el objeto manejador de la bd
		 conexionOk = mne.abrirConexion(model.getTxtUri(), model.getTxtUser(), model.getTxtPassword(), typeConex); //Abrimos la conexion
		 this.uriBD = model.getTxtUri();
		 this.user = model.getTxtUser();
		 this.password = model.getTxtPassword();
		 this.typeConex = this.typeConex;
		 
		 if(conexionOk==false) {
			 Alert alerta = new Alert(AlertType.WARNING);
				alerta.setTitle("Acceso BD");
				alerta.setHeaderText("Problema de conexión");
				alerta.setContentText("No se ha establecido la conexión,\nrevisa que los campos sean correctos");
				alerta.showAndWait();
		 }else {
			 //TODO aqui abrir panel principal "1" si la bd es mysql/oracle "2" si es base
			 	RootControllerA controller = new RootControllerA();		
			 	controller.setParametrosConexion(uriBD, user, password, typeConex);
			 
				Scene scene = new Scene(controller.getView(), 1000, 600);
				Stage stage = new Stage();			
				stage.setTitle("HolaMundo con FXML");
				stage.setScene(scene);
				stage.setMinHeight(600);
				stage.setMinWidth(1000);
				stage.show();
				app.getPrimaryStage().close(); //Prueba para cerrar el primaryStage
				
				 
			 
		 }
		
		
		} catch (Exception e) {
			System.out.println("Problema con la conexión");
		}		
	}
	
	public void addBDslista() {
		listaTiposBD = new ArrayList<String>();
		listaTiposBD.add("Mysql");
		listaTiposBD.add("SQL server");
		listaTiposBD.add("Base");
		listCombo = FXCollections.observableArrayList(listaTiposBD);
		model.setListaCombo(listCombo);
				
		
	}

	
	
	public ManageResidenciasEscolares getManageResidencisEscolares() {
		return mre;
	}
	
	
	


	public String getUriBD() {
		return uriBD;
	}


	public String getUser() {
		return user;
	}


	public String getPassword() {
		return password;
	}


	public char getTypeConex() {
		return typeConex;
	}


	public VBox getView() {
		return root;
	}
	
		

	
}
