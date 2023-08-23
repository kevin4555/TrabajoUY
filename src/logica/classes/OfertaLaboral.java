package logica.classes;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Map;

import excepciones.DtOfertaNoExisteException;
import logica.DataTypes.DTOfertaLaboral;
import logica.DataTypes.DTPaquetePublicacion;
import logica.DataTypes.DTPostulacion;
import logica.handlers.ManejadorOfertas;
import logica.handlers.ManejadorSettings;

public class OfertaLaboral {
	private String nombre;
	private String descripcion;
	private String ciudad;
	private String departamento;
	private String horario;
	private Float remuneracion;
	private Date fechaAlta;
	private ArrayList<Keyword> listaKeywords;
	private TipoPublicacion tipoPublicacion;
	private ArrayList<Postulacion> postulaciones;
	
	public OfertaLaboral(String nombre,String descripcion,String horario, float remuneracion,String ciudad,String departamento, Date fechaAlta,TipoPublicacion tipoPublicacion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.ciudad = ciudad;
		this.departamento = departamento;
		this.setHorario(horario);
		this.remuneracion = remuneracion;
		this.fechaAlta = fechaAlta;
		this.tipoPublicacion = tipoPublicacion;
		this.postulaciones = new ArrayList<Postulacion>();
		this.listaKeywords = new ArrayList<Keyword>();
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
	public float getRemunaracion() {
		return remuneracion;
	}
	public void setRemunaracion(float remuneracion) {
		this.remuneracion = remuneracion;
	}
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public ArrayList<Keyword> getKw() {
		return listaKeywords;
	}

	public void setKeword(ArrayList<Keyword> kw) {
		this.listaKeywords = kw;
	}

	public TipoPublicacion getTipoPublicacion() {
		return tipoPublicacion;
	}

	public void setTipoPublicacion(TipoPublicacion tp) {
		this.tipoPublicacion = tp;
	}

		
	public void agregarKeyword(Keyword keyword) {
		listaKeywords.add(keyword);
	}
	

	public void agregarPostulacion(Postulacion postulacion) {
		this.postulaciones.add(postulacion);
	}

	public ArrayList<Postulacion> getPostulacion() {
		return postulaciones;
	}
	
	@SuppressWarnings("null")
	public ArrayList<DTPostulacion> obtenerDTPostulacion()
	{
		ArrayList<DTPostulacion> listaDTPostulaciones = null;
		for(Postulacion iter : postulaciones)
		{
			listaDTPostulaciones.add(iter.obtenerDTPostulacion());
		}
		return listaDTPostulaciones;
	}
	
	public DTOfertaLaboral obtenerDTOfertaLaboral() {
		CantidadTipoPublicacion cantidadTipoPublicacion = this.tipoPublicacion.getCantidadTipoPublicacion();
		PaquetePublicacion paquetePublicacion = cantidadTipoPublicacion.getPaquetePublicacion();
		DTPaquetePublicacion dtPaquetePublicacion = new DTPaquetePublicacion(this.getNombre(), this.getDescripcion(), cantidadTipoPublicacion.getCantidadRestante(), paquetePublicacion.getPeriodoValidez(), paquetePublicacion.getDescuento(), paquetePublicacion.getCosto());
		DTOfertaLaboral dtOfertaLaboral = new DTOfertaLaboral(this.getNombre(), this.getDescripcion(), this.getCiudad(), this.getHorario(), this.getRemunaracion(), this.getFechaAlta(), dtPaquetePublicacion, this.obtenerDTPostulacion());
		return dtOfertaLaboral;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}
	
}
