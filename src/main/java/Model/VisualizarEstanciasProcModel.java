package Model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import other.classes.Alojamiento;


public class VisualizarEstanciasProcModel {	
	
	private ListProperty<Alojamiento>  listaEstancias = new SimpleListProperty<Alojamiento>(FXCollections.observableArrayList());
	private StringProperty txtDni = new SimpleStringProperty();
	private StringProperty lblTiempoHospedado = new SimpleStringProperty();
	public final ListProperty<Alojamiento> listaEstanciasProperty() {
		return this.listaEstancias;
	}
	
	public final ObservableList<Alojamiento> getListaEstancias() {
		return this.listaEstanciasProperty().get();
	}
	
	public final void setListaEstancias(final ObservableList<Alojamiento> listaEstancias) {
		this.listaEstanciasProperty().set(listaEstancias);
	}
	
	public final StringProperty txtDniProperty() {
		return this.txtDni;
	}
	
	public final String getTxtDni() {
		return this.txtDniProperty().get();
	}
	
	public final void setTxtDni(final String txtDni) {
		this.txtDniProperty().set(txtDni);
	}
	
	public final StringProperty lblTiempoHospedadoProperty() {
		return this.lblTiempoHospedado;
	}
	
	public final String getLblTiempoHospedado() {
		return this.lblTiempoHospedadoProperty().get();
	}
	
	public final void setLblTiempoHospedado(final String lblTiempoHospedado) {
		this.lblTiempoHospedadoProperty().set(lblTiempoHospedado);
	}
	

	

}
