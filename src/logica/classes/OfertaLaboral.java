package logica.classes;


import java.util.ArrayList;
import java.util.Date;

import excepciones.DtOfertaNoExisteException;
import logica.DataTypes.DTOfertaLaboral;
import logica.DataTypes.DTPaquetePublicacion;
import logica.DataTypes.DTPostulacion;

public class OfertaLaboral {
	private String nombre;
	private Empresa empresa;
	private CompraPaquete compraPaquete;
	private String descripcion;
	private String ciudad;
	private String departamento;
	private String horarioInicial;
	private String horarioFinal;
	private float remuneracion;
	private Date fechaAlta;
	private ArrayList<Keyword> listaKeywords;
	private TipoPublicacion tipoPublicacion;
	private ArrayList<Postulacion> postulaciones;
	
	public OfertaLaboral(String nombre,String descripcion,String horarioInicial, String horarioFinal, float remuneracion,String ciudad,String departamento, Date fechaAlta,TipoPublicacion tipoPublicacion, Empresa empresa, CompraPaquete compraPaquete) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.ciudad = ciudad;
		this.departamento = departamento;
		this.horarioInicial = horarioInicial;
		this.horarioFinal = horarioFinal;
		this.remuneracion = remuneracion;
		this.fechaAlta = fechaAlta;
		this.empresa = empresa;
		this.compraPaquete = compraPaquete;
		this.tipoPublicacion = tipoPublicacion;
		this.postulaciones = new ArrayList<Postulacion>();
		this.listaKeywords = new ArrayList<Keyword>();
	}

	public OfertaLaboral(String nombre,String descripcion,String horarioInicial, String horarioFinal, float remuneracion,String ciudad,String departamento, Date fechaAlta,TipoPublicacion tipoPublicacion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.ciudad = ciudad;
		this.departamento = departamento;
		this.horarioInicial = horarioInicial;
		this.horarioFinal = horarioFinal;
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
	
	
	public ArrayList<DTPostulacion> obtenerDTPostulacion()
	{
		ArrayList<DTPostulacion> listaDTPostulaciones = new ArrayList<DTPostulacion>();
		for(Postulacion iter : postulaciones)
		{
			listaDTPostulaciones.add(iter.obtenerDTPostulacion());
		}
		return listaDTPostulaciones;
	}
	
	public DTOfertaLaboral obtenerDTOfertaLaboral() {
		DTOfertaLaboral dtOfertaLaboral = new DTOfertaLaboral(this.getNombre(), this.getDescripcion(), this.getCiudad(),this.getDepartamento(), this.getHorarioInicial(), this.getHorarioFinal(), this.getRemunaracion(), this.getFechaAlta(), this.obtenerDTPostulacion());
		return dtOfertaLaboral;
	}

	public String getHorarioFinal() {
		return horarioFinal;
	}

	public void setHorarioFinal(String horarioFinal) {
		this.horarioFinal = horarioFinal;
	}

	public String getHorarioInicial() {
		return horarioInicial;
	}

	public void setHorarioInicial(String horarioInicial) {
		this.horarioInicial = horarioInicial;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public CompraPaquete getCompraPaquete() {
		return compraPaquete;
	}
	
}
