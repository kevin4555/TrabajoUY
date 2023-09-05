package logica.DataTypes;

import java.util.ArrayList;
import java.util.Date;

public class DTOfertaLaboral {
	private String nombre;
	private String descripcion;
	private String ciudad;
	private String departamento;
	private String horarioInicio;
	private String horarioFinal;
	private Float remuneracion;
	private Date fechaAlta;
	private ArrayList<DTPostulacion> postulaciones;
	
	public DTOfertaLaboral(String nombre, String descripcion, String ciudad, String departamento, String horarioInicial, String horarioFinal, Float remuneracion, Date fechaAlta, ArrayList<DTPostulacion> postulaciones)
	{
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.ciudad = ciudad;
		this.departamento = departamento;
		this.horarioInicio = horarioInicial;
		this.horarioFinal = horarioFinal;
		this.remuneracion = remuneracion;
		this.fechaAlta = fechaAlta;
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
	
	public Float getRemuneracion() {
		return remuneracion;
	}
	
	public Date getFechaAlta() {
		return fechaAlta;
	}


	public ArrayList<DTPostulacion> getPostulaciones() {
		return postulaciones;
	}

	public String getHorarioInicio() {
		return horarioInicio;
	}

	public String getHorarioFinal() {
		return horarioFinal;
	}
}