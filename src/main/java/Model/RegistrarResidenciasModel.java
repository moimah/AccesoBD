package Model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class RegistrarResidenciasModel {
	

	
	private ListProperty<String> listaCombo = new SimpleListProperty<String>(FXCollections.observableArrayList());
	private StringProperty universidadSeleccionada = new SimpleStringProperty();
	private StringProperty txtNombreResidencia = new SimpleStringProperty();
	private StringProperty txtPrecioMensual = new SimpleStringProperty();
	//Aqui faltaria una property para el groupComedor
	public final ListProperty<String> listaComboProperty() {
		return this.listaCombo;
	}
	
	public final ObservableList<String> getListaCombo() {
		return this.listaComboProperty().get();
	}
	
	public final void setListaCombo(final ObservableList<String> listaCombo) {
		this.listaComboProperty().set(listaCombo);
	}
	
	public final StringProperty universidadSeleccionadaProperty() {
		return this.universidadSeleccionada;
	}
	
	public final String getUniversidadSeleccionada() {
		return this.universidadSeleccionadaProperty().get();
	}
	
	public final void setUniversidadSeleccionada(final String universidadSeleccionada) {
		this.universidadSeleccionadaProperty().set(universidadSeleccionada);
	}
	
	public final StringProperty txtNombreResidenciaProperty() {
		return this.txtNombreResidencia;
	}
	
	public final String getTxtNombreResidencia() {
		return this.txtNombreResidenciaProperty().get();
	}
	
	public final void setTxtNombreResidencia(final String txtNombreResidencia) {
		this.txtNombreResidenciaProperty().set(txtNombreResidencia);
	}
	
	public final StringProperty txtPrecioMensualProperty() {
		return this.txtPrecioMensual;
	}
	
	public final String getTxtPrecioMensual() {
		return this.txtPrecioMensualProperty().get();
	}
	
	public final void setTxtPrecioMensual(final String txtPrecioMensual) {
		this.txtPrecioMensualProperty().set(txtPrecioMensual);
	}
	
	

}
