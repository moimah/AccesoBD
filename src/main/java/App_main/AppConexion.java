package App_main;

import Controller.ConexionController;
import Controller.VisualizarResidenciasController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppConexion extends Application {

	private ConexionController controller;
	//Tests
		
	private Stage primaryStage = new Stage(); //Lo añadimos como atributo para poder inyectarlo en el controller 
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		controller = new ConexionController();		
		Scene scene = new Scene(controller.getView(), 800, 600);		
		this.primaryStage.setTitle("AccesoBD - Moises Abreu");
		this.primaryStage.setScene(scene);
		this.primaryStage.show();		
		controller.injectApp(this); //Inyectamos el APP en el controller

	}
	
	
	//Añadido para inyeccion
	public Stage getPrimaryStage() {
		return primaryStage;
	}



	public static void main(String[] args) {
		launch(args);

	}

}
