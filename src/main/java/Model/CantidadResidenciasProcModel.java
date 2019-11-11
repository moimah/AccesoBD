package Model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CantidadResidenciasProcModel {
	
	private ListProperty<String> listaComboUniversidad = new SimpleListProperty<String>(FXCollections.observableArrayList());
	private StringProperty universidadSeleccionada = new SimpleStringProperty();
	private StringProperty txtPrecioMaximo = new SimpleStringProperty();
	private StringProperty lblNumeroUniversidades = new SimpleStringProperty();
	private StringProperty lblPrecioInferior = new SimpleStringProperty();
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
	
	public final StringProperty txtPrecioMaximoProperty() {
		return this.txtPrecioMaximo;
	}
	
	public final String getTxtPrecioMaximo() {
		return this.txtPrecioMaximoProperty().get();
	}
	
	public final void setTxtPrecioMaximo(final String txtPrecioMaximo) {
		this.txtPrecioMaximoProperty().set(txtPrecioMaximo);
	}
	
	public final StringProperty lblNumeroUniversidadesProperty() {
		return this.lblNumeroUniversidades;
	}
	
	public final String getLblNumeroUniversidades() {
		return this.lblNumeroUniversidadesProperty().get();
	}
	
	public final void setLblNumeroUniversidades(final String lblNumeroUniversidades) {
		this.lblNumeroUniversidadesProperty().set(lblNumeroUniversidades);
	}
	
	public final StringProperty lblPrecioInferiorProperty() {
		return this.lblPrecioInferior;
	}
	
	public final String getLblPrecioInferior() {
		return this.lblPrecioInferiorProperty().get();
	}
	
	public final void setLblPrecioInferior(final String lblPrecioInferior) {
		this.lblPrecioInferiorProperty().set(lblPrecioInferior);
	}
	
	
	
	

}
