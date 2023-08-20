package logica;

import java.util.ArrayList;
import java.sql.Date;

public class DTOferta {
	private String nombre;
	private String descripcion;
	private String ciudad;
	private String departamento;
	private Date horaInicio;
	private Date horaFin;
	private Float remuneracion;
	private Date fechaAlta;
	private ArrayList<DTPaquete> paquetes;
	private ArrayList<DTPostulacion> postulaciones;
	
	public DTOferta(String nombre, String descripcion, String ciudad, Date horaInicio, Date horaFin, Float remuneracion, Date fechaAlta, ArrayList<DTPaquete> paquetes, ArrayList<DTPostulacion> postulaciones)
	{
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.ciudad = ciudad;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.remuneracion = remuneracion;
		this.fechaAlta = fechaAlta;
		this.paquetes = paquetes;
		this.postulaciones = postulaciones;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public String getDepartamento() {
		return departamento;
	}
	
	public String getCiudad() {
		return ciudad;
	}
	
	public Date getHoraInicio() {
		return horaInicio;
	}
	
	public Date getHoraFin() {
		return horaFin;
	}
	
	public Float getRemuneracion() {
		return remuneracion;
	}
	
	public Date getFechaAlta() {
		return fechaAlta;
	}
}