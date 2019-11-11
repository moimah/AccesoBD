package Model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ModificarResidenciaModel {
	
	
	private ListProperty<String> listaComboResidencia = new SimpleListProperty<String>(FXCollections.observableArrayList());
	private StringProperty residenciaSeleccionada = new SimpleStringProperty();
	private ListProperty<String> listaComboUniversidad = new SimpleListProperty<String>(FXCollections.observableArrayList());
	private StringProperty universidadSeleccionada = new SimpleStringProperty();
	private StringProperty txtNombreResidencia = new SimpleStringProperty();
	private StringProperty txtPrecioMensual = new SimpleStringProperty();
	public final ListProperty<String> listaComboResidenciaProperty() {
		return this.listaComboResidencia;
	}
	
	public final ObservableList<String> getListaComboResidencia() {
		return this.listaComboResidenciaProperty().get();
	}
	
	public final void setListaComboResidencia(final ObservableList<String> listaComboResidencia) {
		this.listaComboResidenciaProperty().set(listaComboResidencia);
	}
	
	public final StringProperty residenciaSeleccionadaProperty() {
		return this.residenciaSeleccionada;
	}
	
	public final String getResidenciaSeleccionada() {
		return this.residenciaSeleccionadaProperty().get();
	}
	
	public final void setResidenciaSeleccionada(final String residenciaSeleccionada) {
		this.residenciaSeleccionadaProperty().set(residenciaSeleccionada);
	}
	
	public final ListProperty<String> listaComboUniversidadProperty() {
		return this.listaComboUniversidad;
	}
	
	public final ObservableList<String> getListaComboUniversidad() {
		return this.listaComboUniversidadProperty().get();
	}
	
	public final void setListaComboUniversidad(final ObservableList<String> listaComboUniversidad) {
		this.listaComboUniversidadProperty().set(listaComboUniversidad);
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
