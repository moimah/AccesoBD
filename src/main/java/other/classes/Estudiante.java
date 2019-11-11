package other.classes;

public class Estudiante {
	
	int codEstudiante;
	String nombreEstudiante;
	String dni;
	String telefono;
	
	public Estudiante() {
	}
	
		
	public Estudiante(int codEstudiante, String nombreEstudiante, String dni, String telefono) {		
		this.codEstudiante = codEstudiante;
		this.nombreEstudiante = nombreEstudiante;
		this.dni = dni;
		this.telefono = telefono;
	}
	public int getCodEstudiante() {
		return codEstudiante;
	}
	public void setCodEstudiante(int codEstudiante) {
		this.codEstudiante = codEstudiante;
	}
	public String getNombreEstudiante() {
		return nombreEstudiante;
	}
	public void setNombreEstudiante(String nombreEstudiante) {
		this.nombreEstudiante = nombreEstudiante;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	

}

