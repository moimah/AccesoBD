package Model;


import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class ConexionModel {

	private ListProperty<String> listaCombo = new SimpleListProperty<String>(FXCollections.observableArrayList());
	private StringProperty bdSeleccionada = new SimpleStringProperty();
	private StringProperty txtUri = new SimpleStringProperty();
	private StringProperty txtUser = new SimpleStringProperty();
	private StringProperty txtPassword = new SimpleStringProperty();
	public final ListProperty<String> listaComboProperty() {
		return this.listaCombo;
	}
	
	
	
	public final ObservableList<String> getListaCombo() {
		return this.listaComboProperty().get();
	}
	
	public final void setListaCombo(final ObservableList<String> listaCombo) {
		this.listaComboProperty().set(listaCombo);
	}
	
	public final StringProperty txtUriProperty() {
		return this.txtUri;
	}
	
	public final String getTxtUri() {
		return this.txtUriProperty().get();
	}
	
	public final void setTxtUri(final String txtUri) {
		this.txtUriProperty().set(txtUri);
	}
	
	public final StringProperty txtUserProperty() {
		return this.txtUser;
	}
	
	public final String getTxtUser() {
		return this.txtUserProperty().get();
	}
	
	public final void setTxtUser(final String txtUser) {
		this.txtUserProperty().set(txtUser);
	}
	
	public final StringProperty txtPasswordProperty() {
		return this.txtPassword;
	}
	
	public final String getTxtPassword() {
		return this.txtPasswordProperty().get();
	}
	
	public final void setTxtPassword(final String txtPassword) {
		this.txtPasswordProperty().set(txtPassword);
	}



	public final StringProperty bdSeleccionadaProperty() {
		return this.bdSeleccionada;
	}
	



	public final String getBdSeleccionada() {
		return this.bdSeleccionadaProperty().get();
	}
	



	public final void setBdSeleccionada(final String bdSeleccionada) {
		this.bdSeleccionadaProperty().set(bdSeleccionada);
	}
	
	
	
	
	
	
}
