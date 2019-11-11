package Controller;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Model.ModificarResidenciaModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import other.classes.ManageResidenciasEscolares;
import other.classes.Residencia;
import other.classes.ResidenciaAmpliada;
import other.classes.Universidad;

public class ModificarResidenciaController implements Initializable{
	@FXML
	private VBox root;
	@FXML
	private ComboBox cmbResidencia;
	@FXML
	private Button btnModificar;
	@FXML
	private Button btnOk;
	@FXML
	private ComboBox cmbUniversidad;
	@FXML
	private TextField txtNombreResidencia;
	@FXML
	private TextField txtPrecioMensual;
	@FXML
	private RadioButton rdComedorSi;
	@FXML
	private ToggleGroup groupComedor;
	@FXML
	private RadioButton rdComedorNo;
		
	//Others
	private ModificarResidenciaModel model = new ModificarResidenciaModel();
	private RootControllerA rootControllerA; 
	private ObservableList<String> observableResidencias;
	private ObservableList<String> observableUniversidades;
	private ManageResidenciasEscolares mre = new ManageResidenciasEscolares();
	private String codUniversidad;
	private int codResidencia;
	
	

	//Constructor
	public ModificarResidenciaController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ModificarResidenciaView.fxml"));
		loader.setController(this);
		loader.load();
	}
	
	//Inyectors	
		public void injectRootControllerA(RootControllerA rootControllerA) {
			this.rootControllerA = rootControllerA;
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Bindeos
		model.listaComboResidenciaProperty().bindBidirectional(cmbResidencia.itemsProperty());	
		model.residenciaSeleccionadaProperty().bind(cmbResidencia.getSelectionModel().selectedItemProperty());
		model.listaComboUniversidadProperty().bindBidirectional(cmbUniversidad.itemsProperty());
		model.universidadSeleccionadaProperty().bind(cmbUniversidad.getSelectionModel().selectedItemProperty());
		model.txtNombreResidenciaProperty().bindBidirectional(txtNombreResidencia.textProperty());
		model.txtPrecioMensualProperty().bindBidirectional(txtPrecioMensual.textProperty());
				
		//Listeners propios
		model.residenciaSeleccionadaProperty().addListener((o, ov, nv)->onActivarButtonModificar(nv)); //Activar boton modificar
		model.txtNombreResidenciaProperty().addListener((o, ov, nv)->onActivarButtonModificar(nv)); //Activar boton Modificar
		model.txtPrecioMensualProperty().addListener((o, ov, nv)->onActivarButtonModificar(nv)); //Activar boton Modificar
		cmbResidencia.focusedProperty().addListener((o, ov, nv)->onFocusComboResidencia(nv));
		cmbUniversidad.focusedProperty().addListener((o, ov, nv)->onFocusComboUniversidad(nv));
		
		//TextFormatters
		textFieldFormmater(this.txtPrecioMensual); // Permite solo la entrada de valores numericos
				
	}
	
	private void onFocusComboResidencia(Boolean nv) {
		establecerConexion();
		obtenerListaResidencias();
		obtenerListaUniversidades();
		obtenerDatosResidencia();
	}
	
	private void onFocusComboUniversidad(Boolean nv) {
		establecerConexion(); //Por si acaso
		obtenerListaUniversidades();
		
	}


	private void onActivarButtonModificar(String nv) {
		
		try {
			if(model.getTxtNombreResidencia().length()>0 && model.getTxtPrecioMensual().length()>0 && model.getResidenciaSeleccionada().length()>0 ) {
				btnModificar.setDisable(false);
			}else {
				btnModificar.setDisable(true);
			}	
		
		} catch (Exception e) {
			// Falsa alarma salta al no haber nada seleccionado en el combo
			// No es necesario manejar esta excepción
		}
					
	}
	
	public void obtenerDatosResidencia() {		
		
		
		Residencia r = mre.buscarResidencia(model.getResidenciaSeleccionada());
		this.codUniversidad = r.getCodUniversidad();
		this.codResidencia = r.getCodResidencia(); //Aqui obtenemos el codigo Inicial de recidencia para poder manipularlo luego	
		model.setTxtNombreResidencia(r.getNombreResidencia()); //Establece el nombre de la residencia seleccionada
		model.setTxtPrecioMensual(String.valueOf(r.getPrecioMensual())); //Establece el precio de la residencia seleccionada
		
		if(r.isComedor()) { //Aqui establecemos el radio seleccionado
			this.rdComedorSi.setSelected(true);
			this.rdComedorNo.setSelected(false);
		}else {
			this.rdComedorNo.setSelected(true);
			this.rdComedorSi.setSelected(false);
		}
		
		String nombreUniversidad = mre.buscarNombreUniversidad(r.getCodUniversidad());	
		cmbUniversidad.getSelectionModel().select(nombreUniversidad);
					
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
		model.setListaComboResidencia(observableResidencias);	
		
	}
	
	
public void obtenerListaUniversidades() {
		
		ArrayList<Universidad> listaU = mre.listarUniversidades();
		System.out.println(listaU.size());
		ArrayList<String> listaUniversidades = new ArrayList<String>();
		
		for(int i=0; i<listaU.size();i++) {
			listaUniversidades.add(listaU.get(i).getNombreUniversidad());
		}
		
		observableUniversidades = FXCollections.observableArrayList(listaUniversidades);
		
		model.setListaComboUniversidad(observableUniversidades); //TODO arreglar el bindeo
		
	}
	
	
	public void establecerConexion() {
		mre.abrirConexion(rootControllerA.getUriBD(), rootControllerA.getUser(), rootControllerA.getPassword(), rootControllerA.getTypeConex());
	
	}
	
	// Event Listener on Button[#btnModificar].onAction
	@FXML
	public void clickBtnModificar(ActionEvent event) {		
	
		Residencia r = new Residencia(); 
		r.setCodResidencia(codResidencia);
		r.setCodUniversidad(codUniversidad);		
		System.out.println(r.getCodResidencia());	
		r.setPrecioMensual(Integer.parseInt(model.getTxtPrecioMensual()));
		r.setNombreResidencia(model.getTxtNombreResidencia());			
		if(rdComedorSi.isSelected()) {
			r.setComedor(true);
		}else {
			r.setComedor(false);
		}
	
		if(mre.actualizarResidencia(r)) {
			Alert alerta = new Alert(AlertType.INFORMATION);
			alerta.setTitle("Acceso BD");
			alerta.setHeaderText("Enhorabuena");
			alerta.setContentText("Se ha Actualizado la residencia ");
			alerta.showAndWait();
			obtenerListaResidencias(); //Actualizamos el combo eliminando la residencia			
			btnModificar.setDisable(true);
			model.setTxtNombreResidencia("");
			model.setTxtPrecioMensual("");
			cmbResidencia.getSelectionModel().clearSelection();
			cmbUniversidad.getSelectionModel().clearSelection();
			
		}else {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("Acceso BD");
			alerta.setHeaderText("Problema en el delete");
			alerta.setContentText("No se ha modificado la residencia");
			alerta.showAndWait();			
		}
		
		
	}
	
	/**
	* Metodo tipo TextFormater  
	* se encarga de que solo se puedan introducir
	* valores de coma flotante en los textfield
	* @param TextField txt
	*/ 
	
	public void textFieldFormmater(TextField txt) {
		DecimalFormat format = new DecimalFormat("#.0");
		txt.setTextFormatter(new TextFormatter<>(c -> {
			if (c.getControlNewText().isEmpty()) {
				return c;
			}
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = format.parse(c.getControlNewText(), parsePosition);

			if (object == null || parsePosition.getIndex() < c.getControlNewText().length()) {
				return null;
			} else {
				return c;
			}
		}));
	}


	public VBox getView() {
		return root;
	}


	
	
}
