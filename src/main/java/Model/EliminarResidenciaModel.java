package Model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EliminarResidenciaModel {

	private ListProperty<String> listaCombo = new SimpleListProperty<String>(FXCollections.observableArrayList());
	private StringProperty ResidenciaSeleccionada = new SimpleStringProperty();
	public final ListProperty<String> listaComboProperty() {
		return this.listaCombo;
	}
	
	public final ObservableList<String> getListaCombo() {
		return this.listaComboProperty().get();
	}
	
	public final void setListaCombo(final ObservableList<String> listaCombo) {
		this.listaComboProperty().set(listaCombo);
	}
	
	public final StringProperty ResidenciaSeleccionadaProperty() {
		return this.ResidenciaSeleccionada;
	}
	
	public final String getResidenciaSeleccionada() {
		return this.ResidenciaSeleccionadaProperty().get();
	}
	
	public final void setResidenciaSeleccionada(final String ResidenciaSeleccionada) {
		this.ResidenciaSeleccionadaProperty().set(ResidenciaSeleccionada);
	}
	
	
	
}
