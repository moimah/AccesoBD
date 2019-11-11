package Model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import other.classes.ResidenciaAmpliada;

public class VisualizarResidenciaModel {
	
	private ListProperty<ResidenciaAmpliada>  listaResidenciasAmpliada = new SimpleListProperty<ResidenciaAmpliada>(FXCollections.observableArrayList());

	public final ListProperty<ResidenciaAmpliada> listaResidenciasAmpliadaProperty() {
		return this.listaResidenciasAmpliada;
	}
	

	public final ObservableList<ResidenciaAmpliada> getListaResidenciasAmpliada() {
		return this.listaResidenciasAmpliadaProperty().get();
	}
	

	public final void setListaResidenciasAmpliada(final ObservableList<ResidenciaAmpliada> listaResidenciasAmpliada) {
		this.listaResidenciasAmpliadaProperty().set(listaResidenciasAmpliada);
	}
	

	
	
	
	
	
}
