package other.classes;

public class Universidad {
	
	private String codUniversidad;
	private String nombreUniversidad;
	
	public Universidad() {
		
	}
	
	

	public Universidad(String codUniversidad, String nombreUniversidad) {
		super();
		this.codUniversidad = codUniversidad;
		this.nombreUniversidad = nombreUniversidad;
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
	
	
	

}
