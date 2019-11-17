package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Model.VisualizarEstanciasProcModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Label;

import javafx.scene.layout.VBox;
import other.classes.Alojamiento;
import other.classes.ManageResidenciasEscolares;
import other.classes.ResidenciaAmpliada;
import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class VisualizarEstanciasProcController implements Initializable {
	@FXML
	private VBox root;
	@FXML
	private TableView tableEstancias;
	@FXML
	private TableColumn tcNombreResidencia;
	@FXML
	private TableColumn tcNombreUniversidad;
	@FXML
	private TableColumn tcFechaInicio;
	@FXML
	private TableColumn tcFechaFin;
	@FXML
	private TableColumn tcPrecioPagado;
	@FXML
	private TextField txtDni;
	@FXML
	private Button btnBuscar;
	@FXML
	private Button btnTiempoHospedado;
	@FXML
	private Label lblTiempoHospedado;
	
	//Others
	private VisualizarEstanciasProcModel model = new VisualizarEstanciasProcModel();
	private RootControllerA rootControllerA;
	private ManageResidenciasEscolares mre = new ManageResidenciasEscolares();
	private ObservableList<Alojamiento> observableListaAlojamiento;
	
	//Constructor
	public VisualizarEstanciasProcController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/VisualizarEstanciasProcView.fxml"));
		loader.setController(this);
		loader.load();	
		
	}
	
	//Inyectors
	
	public void injectRootController(RootControllerA rootControllerA) {
		this.rootControllerA = rootControllerA; 
	}
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		model.listaEstanciasProperty().bind(tableEstancias.itemsProperty());		
		//TODO Bideos columnas
		tcNombreResidencia.setCellValueFactory(new PropertyValueFactory<Alojamiento, String>("nombreResidencia")); //Dar valor a la columna de la tabla
		tcNombreUniversidad.setCellValueFactory(new PropertyValueFactory<Alojamiento, String>("nombreUniversidad")); //Dar valor a la columna de la tabla
		tcFechaInicio.setCellValueFactory(new PropertyValueFactory<Alojamiento, String>("fechaInicio")); //Dar valor a la columna de la tabla
		tcFechaFin.setCellValueFactory(new PropertyValueFactory<Alojamiento, String>("fechaFin")); //Dar valor a la columna de la tabla
		tcPrecioPagado.setCellValueFactory(new PropertyValueFactory<Alojamiento, Integer>("precioPagado")); //Dar valor a la columna de la tabla		
		model.txtDniProperty().bindBidirectional(txtDni.textProperty());
		model.lblTiempoHospedadoProperty().bindBidirectional(lblTiempoHospedado.textProperty());
		//ListenersPropios
		
		
	}	

	// Event Listener on Button[#btnBuscar].onAction
	@FXML
	public void cickBtnBuscar(ActionEvent event) {
		
	try {		
		   establecerConexion();
			ArrayList<Alojamiento> lista = mre.procedimientoVisualizarEstancias(model.getTxtDni());	
			if(lista.size()>0) {
				tableEstancias.setVisible(true);
				observableListaAlojamiento = FXCollections.observableArrayList(lista);
				this.tableEstancias.setItems(observableListaAlojamiento);
				model.setLblTiempoHospedado("");
			}else {
				tableEstancias.setVisible(false);
				model.setLblTiempoHospedado("");
				Alert alerta = new Alert(AlertType.ERROR);
				alerta.setTitle("Acceso BD");
				alerta.setHeaderText("No se han encontrado coincidencias");
				alerta.setContentText("Revisa que el DNI sea correcto");
				alerta.showAndWait();
			}
		} catch (Exception e) {
			System.out.println("Error al actualizar la tabla");
		}
		
		
		
		
	}
	// Event Listener on Button[#btnTiempoHospedado].onAction
	@FXML
	public void clickBtnTiempoHospedado(ActionEvent event) {
			
		try {
			establecerConexion();
			int tiempoHospedado = 0;
			tiempoHospedado = mre.funcionTiempoHospedado(model.getTxtDni());
			if(tiempoHospedado>0) {
				model.setLblTiempoHospedado("Meses hospedado " +  String.valueOf(tiempoHospedado));
	     	}else {
	     		model.setLblTiempoHospedado("");
				tableEstancias.setVisible(false);
	     		Alert alerta = new Alert(AlertType.ERROR);
				alerta.setTitle("Acceso BD");
				alerta.setHeaderText("No se han encontrado coincidencias");
				alerta.setContentText("Revisa que el DNI sea correcto");
				alerta.showAndWait();  				
				
	     	}			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	
	public void establecerConexion() {
		mre.abrirConexion(rootControllerA.getUriBD(), rootControllerA.getUser(), rootControllerA.getPassword(), rootControllerA.getTypeConex());
	}
	
	public VBox getView() {
		return root;
	}


	
	
	

}
