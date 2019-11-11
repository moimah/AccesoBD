package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Model.CantidadResidenciasProcModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.ComboBox;

import javafx.scene.layout.VBox;
import other.classes.ManageResidenciasEscolares;
import other.classes.Universidad;

public class CantidadResidenciasProcController implements Initializable {
	@FXML
	private VBox root;
	@FXML
	private TextField txtPrecioMaximo;
	@FXML
	private ComboBox cmbUniversidad;
	@FXML
	private Button btnBuscar;
	@FXML
	private Label lblNumeroUniversidades;
	@FXML
	private Label lblPrecioInferior;
	
	//Others
	private CantidadResidenciasProcModel model = new CantidadResidenciasProcModel();
	private RootControllerA rootControllerA;
	private ObservableList<String> observableUniversidades;
	private ManageResidenciasEscolares mre = new ManageResidenciasEscolares();
	

	public CantidadResidenciasProcController() throws IOException {
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CantidadResidenciasProcView.fxml"));
			loader.setController(this);
			loader.load();	
	}
	
	//Inyectors
	
	public void injectRootAContoller(RootControllerA rootControllerA) {
		this.rootControllerA = rootControllerA;
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//Bindeos
		model.listaComboUniversidadProperty().bindBidirectional(cmbUniversidad.itemsProperty());
		model.universidadSeleccionadaProperty().bind(cmbUniversidad.getSelectionModel().selectedItemProperty());
		model.txtPrecioMaximoProperty().bindBidirectional(txtPrecioMaximo.textProperty());
		model.lblPrecioInferiorProperty().bindBidirectional(lblPrecioInferior.textProperty());
		model.lblNumeroUniversidadesProperty().bindBidirectional(lblNumeroUniversidades.textProperty());
		
		//Listeners propios
	  	model.universidadSeleccionadaProperty().addListener((o, ov, nv)->onActivarButtonBuscar(nv)); //Activar boton buscar
	  	model.txtPrecioMaximoProperty().addListener((o, ov, nv)->onActivarButtonBuscar(nv)); //Activar boton buscar
		cmbUniversidad.focusedProperty().addListener((o, ov, nv)->onFocusComboUniversidades(nv));
		
		
	}
	// Event Listener on Button[#btnBuscar].onAction
	@FXML
	public void clickBtnRegistrar(ActionEvent event) {
		try {
			int[] recibir = new int[2];
			recibir = mre.procedimientoCantidadResidenciasPrecio(model.getUniversidadSeleccionada(), Integer.parseInt(model.getTxtPrecioMaximo()));
			int a = recibir[0];
			if(recibir[0]>0) {
			String numeroResidencias =String.valueOf(recibir[0]);
			String precioInferior = String.valueOf(recibir[1]);			
			model.setLblNumeroUniversidades("El número de residencias de la universidad " + model.getUniversidadSeleccionada() + " es " + numeroResidencias);
			model.setLblPrecioInferior("El número de residencias a un precio inferior a " + model.getTxtPrecioMaximo() + " es " + precioInferior);
			}else {
				Alert alerta = new Alert(AlertType.ERROR);
				alerta.setTitle("Acceso BD");
				alerta.setHeaderText("No se han encontrado resultados");
				alerta.setContentText("Prueba con otra universidad");
				alerta.showAndWait();
				model.setTxtPrecioMaximo("");
				model.setUniversidadSeleccionada("");
			}
		} catch (Exception e) {
			System.out.println("Error al ejecutar el procedimiento");
		}
		
		
	}
	
	private void onFocusComboUniversidades(Boolean nv) {		
		establecerConexion();
		obtenerListaUniversidades();
	}
	
	public void establecerConexion() {
		mre.abrirConexion(rootControllerA.getUriBD(), rootControllerA.getUser(), rootControllerA.getPassword(), rootControllerA.getTypeConex());
	}
	
	public void obtenerListaUniversidades() {
		
		ArrayList<Universidad> listaU = mre.listarUniversidades();
		System.out.println(listaU.size());
		ArrayList<String> listaUniversidades = new ArrayList<String>();
		
		for(int i=0; i<listaU.size();i++) {
			listaUniversidades.add(listaU.get(i).getNombreUniversidad());
		}
		
		observableUniversidades = FXCollections.observableArrayList(listaUniversidades);		
		model.setListaComboUniversidad(observableUniversidades);
		
	}
	
	private void onActivarButtonBuscar(String nv) {
		
		try {			
			if(model.getTxtPrecioMaximo().length()>0 && model.getUniversidadSeleccionada().length()>0) {
				btnBuscar.setDisable(false);				               
			}else {
				btnBuscar.setDisable(true);
			}			
		} catch (Exception e) {
			//Falsa alarma salta al no haber nada seleccionado en el combo
			//No es necesario manejar esta excepción
		}
		
	}


	public VBox getView() {
		return root;
	}


	
	
	

}
