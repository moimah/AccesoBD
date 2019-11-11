package other.classes;

public class ResidenciaAmpliada {
	
	int codResidencia; 
	String nombreResidencia; 
	String codUniversidad;
	String nombreUniversidad; 
	int precioMensual; 
	boolean comedor;
	
	public ResidenciaAmpliada() {
		
	}
	
	
	
	public ResidenciaAmpliada(int codResidencia, String nombreResidencia, String codUniversidad,
			String nombreUniversidad, int precioMensual, boolean comedor) {
		super();
		this.codResidencia = codResidencia;
		this.nombreResidencia = nombreResidencia;
		this.codUniversidad = codUniversidad;
		this.nombreUniversidad = nombreUniversidad;
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
	public String getNombreUniversidad() {
		return nombreUniversidad;
	}
	public void setNombreUniversidad(String nombreUniversidad) {
		this.nombreUniversidad = nombreUniversidad;
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

