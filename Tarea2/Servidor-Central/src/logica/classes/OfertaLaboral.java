package logica.classes;

import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.util.ArrayList;
import logica.DataTypes.DTOfertaLaboral;
import logica.DataTypes.DTPaquetePublicacion;
import logica.DataTypes.DTPostulacion;
import logica.DataTypes.EstadoOferta;

public class OfertaLaboral {
	private String nombre;
	private String descripcion;
	private String ciudad;
	private String departamento;
	private String horarioInicial;
	private String horarioFinal;
	private float remuneracion;
	private LocalDate fechaAlta;
	private LocalDate fechaResolucion;
	private BufferedImage imagen;
	private EstadoOferta estado;
	private ArrayList<Keyword> listaKeywords;
	private TipoPublicacion tipoPublicacion;
	private ArrayList<Postulacion> postulaciones;
	private CompraPaquete compraPaquete;
	
	public OfertaLaboral(String nombre,String descripcion,String horarioInicial, String horarioFinal, float remuneracion, String ciudad, String departamento, LocalDate fechaAlta, TipoPublicacion tipoPublicacion, BufferedImage imagen) {
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
		this.estado = EstadoOferta.INGRESADA;
		this.imagen = imagen;
		this.compraPaquete = null;
		this.fechaResolucion = null;
		
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

	public LocalDate getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public ArrayList<Keyword> getKeywords() {
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
	
	public Boolean estaCompradaPorPaquete() {
		return compraPaquete != null;
	}
	
	public EstadoOferta getEstado() {
		return estado;
	}

	public CompraPaquete getCompraPaquete() {
		return compraPaquete;
	}

	public void setCompraPaquete(CompraPaquete compraPaquete) {
		this.compraPaquete = compraPaquete;
	}
	
	public void resolucionOferta(EstadoOferta estado, LocalDate fechaResolucion) {
		this.estado = estado;
		this.fechaResolucion = fechaResolucion;
	}
	
	public Boolean tieneKeyword(String keywordBuscada) {
		Boolean resultado = false;
		for(Keyword keyword : listaKeywords) {
			if(keyword.getNombre() == keywordBuscada) {
				resultado = true;
				break;
			}
		}
		return resultado;
	}
	
	public DTPaquetePublicacion obtenerDTPaquete() {
		return compraPaquete.obtenerDTPaquete();
	}
}
