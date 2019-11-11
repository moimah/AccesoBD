package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.ResourceBundle;
import Model.RegistrarResidenciasProcModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.ComboBox;

import javafx.scene.control.RadioButton;

import javafx.scene.layout.VBox;
import other.classes.ManageResidenciasEscolares;
import other.classes.Residencia;
import other.classes.Universidad;

public class RegistrarResidenciaProcController implements Initializable {
	@FXML
	private VBox root;
	@FXML
	private ComboBox cmbUniversidad;
	@FXML
	private TextField txtNombreResidencia;
	@FXML
	private TextField txtPrecioMensual;
	@FXML
	private Button btnRegistrar;
	@FXML
	private RadioButton rdComedorSi;
	@FXML
	private ToggleGroup groupComedor;
	@FXML
	private RadioButton rdComedorNo;
	
	//Others
		private RegistrarResidenciasProcModel model = new RegistrarResidenciasProcModel();
		private RootControllerA rootControllerA; 
		private ManageResidenciasEscolares mre = new ManageResidenciasEscolares();
		private String codUniversidad; 
		private ObservableList<String> observableUniversidades;
	
	//Constructors
	 public RegistrarResidenciaProcController() throws IOException {
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/RegistrarResidenciaProcView.fxml"));
			loader.setController(this);
			loader.load();		 
	}
	
	 //Inyectors
		public void injectRootAController(RootControllerA rootControllerA) {
			this.rootControllerA = rootControllerA;
		}
	
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			
			//Bindeos	
			model.listaComboProperty().bindBidirectional(cmbUniversidad.itemsProperty());
			model.universidadSeleccionadaProperty().bind(cmbUniversidad.getSelectionModel().selectedItemProperty());
			model.txtNombreResidenciaProperty().bindBidirectional(txtNombreResidencia.textProperty());
			model.txtPrecioMensualProperty().bindBidirectional(txtPrecioMensual.textProperty());
				
			//Listeners propios
			model.txtNombreResidenciaProperty().addListener((o, ov, nv)->onActivarButtonRegistrar(nv)); //Activar boton conectar
			model.txtPrecioMensualProperty().addListener((o, ov, nv)->onActivarButtonRegistrar(nv)); //Activar boton conectar
			model.universidadSeleccionadaProperty().addListener((o, ov, nv)->onActivarButtonRegistrar(nv)); //Activar boton conectar
			cmbUniversidad.focusedProperty().addListener((o, ov, nv)->onFocusComboUniversidades(nv));
			
			//TextFormatters
			textFieldFormmater(this.txtPrecioMensual); // Permite solo la entrada de valores numericos
			
		}
		

		private void onFocusComboUniversidades(Boolean nv) {		
			establecerConexion();
			obtenerListaUniversidades();
		}

		private void onActivarButtonRegistrar(String nv) {
			
			try {			
				if(model.getTxtNombreResidencia().length()>0 && model.getTxtPrecioMensual().length()>0 && model.getUniversidadSeleccionada().length()>0) {
					btnRegistrar.setDisable(false);
					//Añadido para meter codUniversidad
					codUniversidad = mre.buscarCodigoUniversidad(model.getUniversidadSeleccionada());                
				}else {
					btnRegistrar.setDisable(true);
				}			
			} catch (Exception e) {
				//Falsa alarma salta al no haber nada seleccionado en el combo
				//No es necesario manejar esta excepción
			}
			
		}

		// Event Listener on Button[#btnRegistrar].onAction
		@FXML
		public void clickBtnRegistrar(ActionEvent event) {
			boolean comedor = rdComedorSi.isSelected();		
			Residencia r = new Residencia();
			
			try {
				r.setCodUniversidad(codUniversidad);
				r.setComedor(comedor);
				r.setNombreResidencia(model.getTxtNombreResidencia());
				r.setPrecioMensual(Integer.parseInt(model.getTxtPrecioMensual()));
				
				if(mre.procedimientoInsertar(r)) {
					Alert alerta = new Alert(AlertType.INFORMATION);
					alerta.setTitle("Acceso BD");
					alerta.setHeaderText("Enhorabuena");
					alerta.setContentText("Se ha registrado la residencia ");
					alerta.showAndWait();
					model.setTxtNombreResidencia("");
					model.setTxtPrecioMensual("");
					model.setUniversidadSeleccionada("");					
				   
				}else {
						Alert alerta = new Alert(AlertType.ERROR);
						alerta.setTitle("Acceso BD");
						alerta.setHeaderText("Problema en la inserción");
						alerta.setContentText("No se ha insertado la residencia, asegurate de que la universidad exista");
						alerta.showAndWait();
						model.setTxtNombreResidencia("");
						model.setTxtPrecioMensual("");
						model.setUniversidadSeleccionada("");
					 
							
				}
				
			} catch (Exception e) {
				// Falsa alarma 
			}
			
			
			
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
			model.setListaCombo(observableUniversidades);
			
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