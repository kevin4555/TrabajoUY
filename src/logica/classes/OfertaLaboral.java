package logica.classes;

import java.sql.Date;
import java.util.Map;

public class OfertaLaboral {
	private String nombre;
	private String descripcion;
	private String ciudad;
	private String departamento;
	private Date horaInicio;
	private Date horaFin;
	private double remunaracion;
	private Date fechaAlta;
	private Map<String, Keyword> kw;
	private TipoPublicacion tp;
	private CompraPaquete cp;
	
	public OfertaLaboral(String nombre,String descripcion,String ciudad,String departamento,Date horaInicio,Date horaFin,double remunaracion,Date fechaAlta) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.ciudad = ciudad;
		this.departamento = departamento;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.remunaracion = remunaracion;
		this.fechaAlta = fechaAlta;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public Date getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}
	public Date getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(Date horaFin) {
		this.horaFin = horaFin;
	}
	public double getRemunaracion() {
		return remunaracion;
	}
	public void setRemunaracion(double remunaracion) {
		this.remunaracion = remunaracion;
	}
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Map<String, Keyword> getKw() {
		return kw;
	}

	public void setKw(Map<String, Keyword> kw) {
		this.kw = kw;
	}

	public TipoPublicacion getTp() {
		return tp;
	}

	public void setTp(TipoPublicacion tp) {
		this.tp = tp;
	}

	public CompraPaquete getCp() {
		return cp;
	}

	public void setCp(CompraPaquete cp) {
		this.cp = cp;
	}
}
