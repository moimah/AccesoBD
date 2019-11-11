package other.classes;

import java.sql.Date;

public class Estancia {
	
	protected int codEstudiante; 
	protected int codResidencia; 
	protected Date fechaIicio; 
    protected Date fechaFin;
	protected int precioPagado;
	
	public Estancia() {
	}
	
	public Estancia(int codEstudiante, int codResidencia, Date fechaIicio, Date fechaFin, int precioPagado) {	
		super();
		this.codEstudiante = codEstudiante;
		this.codResidencia = codResidencia;
		this.fechaIicio = fechaIicio;
		this.fechaFin = fechaFin;
		this.precioPagado = precioPagado;
	}

	public int getCodEstudiante() {
		return codEstudiante;
	}

	public void setCodEstudiante(int codEstudiante) {
		this.codEstudiante = codEstudiante;
	}

	public int getCodResidencia() {
		return codResidencia;
	}

	public void setCodResidencia(int codResidencia) {
		this.codResidencia = codResidencia;
	}

	public Date getFechaIicio() {
		return fechaIicio;
	}

	public void setFechaIicio(Date fechaIicio) {
		this.fechaIicio = fechaIicio;
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


