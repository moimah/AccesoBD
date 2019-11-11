package App_main;

import Controller.ConexionController;
import Controller.RootControllerA;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RootAapp extends Application {

	private RootControllerA controller;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		controller = new RootControllerA();		
		Scene scene = new Scene(controller.getView(), 800, 600);		
		primaryStage.setTitle("HolaMundo con FXML");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);

	}

}
