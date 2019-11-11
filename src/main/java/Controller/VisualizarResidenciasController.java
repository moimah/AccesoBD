package Controller;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import App_main.AppConexion;
import Model.VisualizarResidenciaModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import other.classes.ManageResidenciasEscolares;
import other.classes.ResidenciaAmpliada;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;

public class VisualizarResidenciasController implements Initializable {
	@FXML
	private VBox root;
	@FXML
	private TableView tableResidencias;
	@FXML
	private TableColumn tcCodigoResidencia;
	@FXML
	private TableColumn tcNombreResidencia;
	@FXML
	private TableColumn tcCodigoUniversidad;
	@FXML
	private TableColumn tcNombreUniversidad;
	@FXML
	private TableColumn tcPrecioMensual;
	@FXML
	private TableColumn tcComedor;
	
	//Others	
	private VisualizarResidenciaModel model = new VisualizarResidenciaModel();
	private RootControllerA rootControllerA;
	private ManageResidenciasEscolares mre; 
	private ObservableList<ResidenciaAmpliada> observableListaResidenciaAmpliada;
	
	//Connection atributtes	
	private String uriBD;
	private String user;
	private String password;
	char typeConex;
	

		
	//Constructor
	public VisualizarResidenciasController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/VisualizarResidenciasView.fxml"));
		loader.setController(this);
		loader.load();		
	}
	
	//Inyectors
			
	public void injectRootAController(RootControllerA rootAcontroller) {
		this.rootControllerA = rootAcontroller;
		System.out.println("Inyectandome");
	} 

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Bindeos
		model.listaResidenciasAmpliadaProperty().bind(tableResidencias.itemsProperty());
		tcCodigoResidencia.setCellValueFactory(new PropertyValueFactory<ResidenciaAmpliada, Integer>("codResidencia")); //Dar valor a la columna de la tabla
		tcNombreResidencia.setCellValueFactory(new PropertyValueFactory<ResidenciaAmpliada, String>("nombreResidencia")); //Dar valor a la columna de la tabla
		tcCodigoUniversidad.setCellValueFactory(new PropertyValueFactory<ResidenciaAmpliada, String>("codUniversidad")); //Dar valor a la columna de la tabla
		tcNombreUniversidad.setCellValueFactory(new PropertyValueFactory<ResidenciaAmpliada, String>("nombreUniversidad")); //Dar valor a la columna de la tabla
		tcPrecioMensual.setCellValueFactory(new PropertyValueFactory<ResidenciaAmpliada, Integer>("precioMensual")); //Dar valor a la columna de la tabla
		tcComedor.setCellValueFactory(new PropertyValueFactory<ResidenciaAmpliada, Boolean>("comedor")); //Dar valor a la columna de la tabla
		//ListenersPropios
		tableResidencias.focusedProperty().addListener((o, ov, nv)->OnFocusTableListener(nv));
		tableResidencias.isFocused();
		
	
	}
	


	private void OnFocusTableListener(Boolean nv) {
			conectar();
		try {			
			
			ArrayList<ResidenciaAmpliada> lista = mre.listarResidenciasAmpliada();
			System.out.println(lista);
			observableListaResidenciaAmpliada = FXCollections.observableArrayList(lista);
			this.tableResidencias.setItems(observableListaResidenciaAmpliada);
		} catch (Exception e) {
			System.out.println("Error al actualizar la tabla");
		}
	
	
	}
	
	public void conectar() {
		this.uriBD = rootControllerA.getUriBD();
		this.password = rootControllerA.getPassword();
		this.typeConex = rootControllerA.getTypeConex();
		this.user = rootControllerA.getUser();
		mre = new ManageResidenciasEscolares();
		mre.abrirConexion(uriBD, user, password, typeConex);
	}
	

	public String getUriBD() {
		return uriBD;
	}

	public void setUriBD(String uriBD) {
		this.uriBD = uriBD;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public char getTypeConex() {
		return typeConex;
	}

	public void setTypeConex(char typeConex) {
		this.typeConex = typeConex;
	}

	public VBox getView() {
		return root;
	}
	
	
	
		

}
