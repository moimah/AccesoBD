package view;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.layout.VBox;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class VisualizarEstanciasProcViewController {
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

	// Event Listener on Button[#btnBuscar].onAction
	@FXML
	public void cickBtnBuscar(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#btnTiempoHospedado].onAction
	@FXML
	public void clickBtnTiempoHospedado(ActionEvent event) {
		// TODO Autogenerated
	}
}