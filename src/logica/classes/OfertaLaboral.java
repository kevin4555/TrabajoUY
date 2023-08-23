package logica.classes;

import java.sql.Date;
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
	private Date horaInicio;
	private Date horaFin;
	private Float remuneracion;
	private Date fechaAlta;
	private ArrayList<Keyword> kewords;
	private TipoPublicacion tipoPublicacion;
	private CompraPaquete compraPaquete;
	private ArrayList<Postulacion> postulaciones;
	
	public OfertaLaboral(String nombre,String descripcion,String ciudad,String departamento,Date horaInicio,Date horaFin,Float remuneracion,Date fechaAlta, ArrayList<Keyword> keywords ,TipoPublicacion tipoPublicacion, CompraPaquete cp) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.ciudad = ciudad;
		this.departamento = departamento;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.remuneracion = remuneracion;
		this.fechaAlta = fechaAlta;
		this.kewords = keywords;
		this.tipoPublicacion = tipoPublicacion;
		this.compraPaquete = cp;
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
	public Float getRemunaracion() {
		return remuneracion;
	}
	public void setRemunaracion(Float remuneracion) {
		this.remuneracion = remuneracion;
	}
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public ArrayList<Keyword> getKw() {
		return kewords;
	}

	public void setKeword(ArrayList<Keyword> kw) {
		this.kewords = kw;
	}

	public TipoPublicacion getTipoPublicacion() {
		return tipoPublicacion;
	}

	public void setTipoPublicacion(TipoPublicacion tp) {
		this.tipoPublicacion = tp;
	}

	public CompraPaquete getCompraPaquete() {
		return compraPaquete;
	}

	public void setCompraPaquete(CompraPaquete cp) {
		this.compraPaquete = cp;
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
		DTOfertaLaboral dtOfertaLaboral = new DTOfertaLaboral(this.getNombre(), this.getDescripcion(), this.getCiudad(), this.getHoraInicio(), this.getHoraFin(), this.getRemunaracion(), this.getFechaAlta(), dtPaquetePublicacion, this.obtenerDTPostulacion());
		return dtOfertaLaboral;
	}
	
}
