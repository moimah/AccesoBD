package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import App_main.RootAapp;
import Model.EliminarResidenciaModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import other.classes.ManageResidenciasEscolares;
import other.classes.Residencia;
import other.classes.ResidenciaAmpliada;
import other.classes.Universidad;

public class EliminarResidenciaController implements Initializable {
	@FXML
	private VBox root;
	@FXML
	private ComboBox cmbResidencia;
	@FXML
	private Button btnEliminar;
	
	//Others
	private EliminarResidenciaModel model = new EliminarResidenciaModel();
	private RootControllerA rootControllerA; 
	private ManageResidenciasEscolares mre = new ManageResidenciasEscolares();
	private ObservableList<String> observableResidencias;
	private ComboBox copyCmbResidencia = cmbResidencia;
	

	
	//Constructor
	public EliminarResidenciaController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EliminarResidenciaView.fxml"));
		loader.setController(this);
		loader.load();	
		
	}
	
	//Inyectors	
	public void injectRootControllerA(RootControllerA rootControllerA) {
		this.rootControllerA = rootControllerA;
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Bindings
		model.listaComboProperty().bindBidirectional(cmbResidencia.itemsProperty());
		model.ResidenciaSeleccionadaProperty().bind(cmbResidencia.getSelectionModel().selectedItemProperty());
		
		//Listeners propios
		model.ResidenciaSeleccionadaProperty().addListener((o, ov, nv)->onActivarButtonRegistrar(nv)); //Activar boton conectar
		cmbResidencia.focusedProperty().addListener((o, ov, nv)->onFocusComboResidencia(nv));
		
	}
	
	
	private void onFocusComboResidencia(Boolean nv) {
		establecerConexion();
		obtenerListaResidencias();
	}
	
	public void establecerConexion() {
		mre.abrirConexion(rootControllerA.getUriBD(), rootControllerA.getUser(), rootControllerA.getPassword(), rootControllerA.getTypeConex());
	
	}
	
	private void onActivarButtonRegistrar(String nv) {

		try {			
			if (model.getResidenciaSeleccionada().length() > 0) {
				btnEliminar.setDisable(false);
				
			} else {
				btnEliminar.setDisable(true);
			}
		} catch (Exception e) {
			// Falsa alarma salta al no haber nada seleccionado en el combo
			// No es necesario manejar esta excepción
		}

	}
	
	
	public void obtenerListaResidencias() {
			
		ArrayList<ResidenciaAmpliada> listaR = mre.listarResidenciasAmpliada();	
			System.out.println("ListaR" + listaR.size());
		ArrayList<String> listaResidencias = new ArrayList<String>();
		
		for(int i=0; i<listaR.size();i++) {
			listaResidencias.add(listaR.get(i).getNombreResidencia());
			System.out.println(listaResidencias.get(i));
		}
		
		observableResidencias = FXCollections.observableArrayList(listaResidencias);		
		model.setListaCombo(observableResidencias);		
		
	}
	

	// Event Listener on Button[#btnEliminar].onAction
	@FXML
	public void clickBtnRegistrar(ActionEvent event) {
		
		Residencia r = new Residencia(); 
		r.setNombreResidencia(model.getResidenciaSeleccionada());
		
		if(mre.eliminarResidencia(r)) {
			Alert alerta = new Alert(AlertType.INFORMATION);
			alerta.setTitle("Acceso BD");
			alerta.setHeaderText("Enhorabuena");
			alerta.setContentText("Se ha eliminado la residencia ");
			alerta.showAndWait();
			obtenerListaResidencias(); //Actualizamos el combo eliminando la residencia			
			btnEliminar.setDisable(true);
		}else {
		    Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("Acceso BD");
			alerta.setHeaderText("Problema en el delete");
			alerta.setContentText("No se ha eliminado la residencia");
			alerta.showAndWait();
		}
			
		
	}
	
	

	public ObservableList<String> getObservableResidencias() {
		return observableResidencias;
	}

	
	public VBox getView() {
		return root;
	}

	

}
