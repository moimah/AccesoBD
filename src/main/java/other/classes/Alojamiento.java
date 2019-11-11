package other.classes;

import java.sql.Date;

public class Alojamiento {

	private String nombreResidencia;
	private String nombreUniversidad;
	private Date fechaInicio; 
	private Date fechaFin; 
	private int precioPagado;
	
	public Alojamiento() {
		super();
	}

	public Alojamiento(String nombreResidencia, String nombreUniversidad, Date fechaInicio, Date fechaFin,
			int precioPagado) {
		super();
		this.nombreResidencia = nombreResidencia;
		this.nombreUniversidad = nombreUniversidad;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.precioPagado = precioPagado;
	}

	public String getNombreResidencia() {
		return nombreResidencia;
	}

	public void setNombreResidencia(String nombreResidencia) {
		this.nombreResidencia = nombreResidencia;
	}

	public String getNombreUniversidad() {
		return nombreUniversidad;
	}

	public void setNombreUniversidad(String nombreUniversidad) {
		this.nombreUniversidad = nombreUniversidad;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public int getPrecioPagado() {
		return precioPagado;
	}

	public void setPrecioPagado(int precioPagado) {
		this.precioPagado = precioPagado;
	}
	
	
}
