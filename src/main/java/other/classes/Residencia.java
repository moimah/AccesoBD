package other.classes;

public class Residencia {
	
	int codResidencia; 
	String nombreResidencia; 
	String codUniversidad;
	int precioMensual; 
	boolean comedor;
	
	public Residencia() {
		
	}
	
	
	
	public Residencia(int codResidencia, String nombreResidencia, String codUniversidad, int precioMensual,	boolean comedor) {	
		super();
		this.codResidencia = codResidencia;
		this.nombreResidencia = nombreResidencia;
		this.codUniversidad = codUniversidad;
		this.precioMensual = precioMensual;
		this.comedor = comedor;
	}



	public int getCodResidencia() {
		return codResidencia;
	}



	public void setCodResidencia(int codResidencia) {
		this.codResidencia = codResidencia;
	}



	public String getNombreResidencia() {
		return nombreResidencia;
	}



	public void setNombreResidencia(String nombreResidencia) {
		this.nombreResidencia = nombreResidencia;
	}



	public String getCodUniversidad() {
		return codUniversidad;
	}



	public void setCodUniversidad(String codUniversidad) {
		this.codUniversidad = codUniversidad;
	}



	public int getPrecioMensual() {
		return precioMensual;
	}



	public void setPrecioMensual(int precioMensual) {
		this.precioMensual = precioMensual;
	}



	public boolean isComedor() {
		return comedor;
	}



	public void setComedor(boolean comedor) {
		this.comedor = comedor;
	} 
	
	
	
	

}

