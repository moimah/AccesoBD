package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;

public class RootControllerA implements Initializable{
	
	@FXML
	private TabPane root;
	@FXML
	private Tab tabVisualizarResidencias;
	@FXML
	private Tab tabRegistrarResidencias;
	@FXML
	private Tab tabEliminarResidencia;
	@FXML
	private Tab tabModificarResidencia;
	@FXML
	private Tab tabRegistrarProc;
	@FXML
	private Tab tabVisualizarEstanciasProc;
	@FXML
	private Tab tabCantidadResidenciasProc;
	
	//Connection atributtes	
		private String uriBD;
		private String user;
		private String password;
		char typeConex;
	

	
	//Aqui subcontrollers
	private VisualizarResidenciasController subcontrollerVisualizarResidencias; 
	private RegistrarResidenciaController subcontrollerRegistrarResidencia; 
	private EliminarResidenciaController subcontrollerEliminarResidencia; 
	private ModificarResidenciaController subcontrollerModificarResidencia;
	private VisualizarEstanciasProcController subcontrollerVisualizarEstanciasProc;
	private RegistrarResidenciaProcController subcontrollerRegistrarResidenciaProc;
	private CantidadResidenciasProcController subcontrollerCantidadResidenciasProc;
	
	
	//Constructor
	public RootControllerA(char typeConex) throws IOException {
		this.typeConex = typeConex; 		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/RootAView.fxml"));
		loader.setController(this);
		loader.load();
	}
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {

			//Inicializar los subcontrollers
			 subcontrollerVisualizarResidencias = new VisualizarResidenciasController();	
			 subcontrollerRegistrarResidencia = new RegistrarResidenciaController();
			 subcontrollerEliminarResidencia = new EliminarResidenciaController();
			 subcontrollerModificarResidencia = new ModificarResidenciaController();
			 subcontrollerVisualizarEstanciasProc = new VisualizarEstanciasProcController();
			 subcontrollerRegistrarResidenciaProc = new RegistrarResidenciaProcController();
			 subcontrollerCantidadResidenciasProc = new CantidadResidenciasProcController();
			
		} catch (Exception e) {
			System.out.println("Problema al cargar las vistas");
		}
		
		//Obtenemos los views y los configuramos
		VBox viewVisualizarResidencias = subcontrollerVisualizarResidencias.getView();
		viewVisualizarResidencias.setAlignment(Pos.CENTER);
		
		VBox viewRegistrarResidencias = subcontrollerRegistrarResidencia.getView();
		viewRegistrarResidencias.setAlignment(Pos.CENTER);
		
		VBox viewEliminarResidencias = subcontrollerEliminarResidencia.getView();
		viewEliminarResidencias.setAlignment(Pos.CENTER);
		
		VBox viewModificarResidencias = subcontrollerModificarResidencia.getView();
		viewModificarResidencias.setAlignment(Pos.CENTER);
		
		VBox viewVisualizarEstanciasProc = subcontrollerVisualizarEstanciasProc.getView();
		viewVisualizarEstanciasProc.setAlignment(Pos.CENTER);
		
		VBox viewRegistrarResidenciasProc = subcontrollerRegistrarResidenciaProc.getView();
		viewRegistrarResidenciasProc.setAlignment(Pos.CENTER);
		
		VBox viewCantidadResidenciasProc = subcontrollerCantidadResidenciasProc.getView();
		viewCantidadResidenciasProc.setAlignment(Pos.CENTER);
		
		//Añadir views a los tabs
		tabVisualizarResidencias.setContent(viewVisualizarResidencias);
		tabRegistrarResidencias.setContent(viewRegistrarResidencias);
		tabEliminarResidencia.setContent(viewEliminarResidencias);
		tabModificarResidencia.setContent(viewModificarResidencias);
		
		if(typeConex != 'c') {			
			tabVisualizarEstanciasProc.setContent(viewVisualizarEstanciasProc);
			tabRegistrarProc.setContent(viewRegistrarResidenciasProc);
			tabCantidadResidenciasProc.setContent(viewCantidadResidenciasProc);
		}else {
			tabVisualizarEstanciasProc.setDisable(true);
			tabRegistrarProc.setDisable(true);
			tabCantidadResidenciasProc.setDisable(true);
		}
		
		
		
		//Inyeccion de mainController a los subcontrollers
		
		subcontrollerVisualizarResidencias.injectRootAController(this); 
		subcontrollerRegistrarResidencia.injectRootAController(this);
		subcontrollerEliminarResidencia.injectRootControllerA(this);
		subcontrollerModificarResidencia.injectRootControllerA(this);
		subcontrollerVisualizarEstanciasProc.injectRootController(this);
		subcontrollerRegistrarResidenciaProc.injectRootAController(this);
		subcontrollerCantidadResidenciasProc.injectRootAContoller(this);
		
	}
	
	public void setParametrosConexion(String uriBD, String user, String password, char typeConex) {
		this.uriBD = uriBD;
		this.user = user; 
		this.password = password; 
		this.typeConex = typeConex; 
	}
	
	

	public VisualizarResidenciasController getSubcontrollerVisualizarResidencias() {
		return subcontrollerVisualizarResidencias;
	}
	
	
	
		
	
	public RegistrarResidenciaController getSubcontrollerRegistrarResidencia() {
		return subcontrollerRegistrarResidencia;
	}



	public EliminarResidenciaController getSubcontrollerEliminarResidencia() {
		return subcontrollerEliminarResidencia;
	}



	public ModificarResidenciaController getSubcontrollerModificarResidencia() {
		return subcontrollerModificarResidencia;
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
	
	
	
	
	public TabPane getView() {
		return root;
	}
	
	
	

		

}
