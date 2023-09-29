package logica.DataTypes;

import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.util.ArrayList;


public class DTOfertaLaboral {
	private String nombre;
	private String descripcion;
	private String ciudad;
	private String departamento;
	private String horarioInicio;
	private String horarioFinal;
	private Float remuneracion;
	private LocalDate fechaAlta;
	private LocalDate fechaResolucion;
	private ArrayList<DTPostulacion> postulaciones;
	private EstadoOferta estadoOferta;
	private BufferedImage imagen;
	private DTPaquetePublicacion paqueteAsociado;
	private ArrayList<String> keywords;
	private Boolean estaVencida;
	private String nombreTipoPublicacion;
	
	
	
	
	public DTOfertaLaboral(String nombre, String descripcion, String ciudad, String departamento,
			String horarioInicial, String horarioFinal, Float remuneracion, LocalDate fechaAlta,
			ArrayList<DTPostulacion> postulaciones, LocalDate fechaResolucion, EstadoOferta estado, 
			BufferedImage imagen, DTPaquetePublicacion paquete, ArrayList<String> keywords,
			Boolean estaVencida, String nombreTipoPublicacion)
	{
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.ciudad = ciudad;
		this.departamento = departamento;
		this.horarioInicio = horarioInicial;
		this.horarioFinal = horarioFinal;
		this.remuneracion = remuneracion;
		this.fechaAlta = fechaAlta;
		this.fechaResolucion = fechaResolucion;
		this.postulaciones = postulaciones;
		this.estadoOferta = estado;
		this.imagen = imagen;
		this.paqueteAsociado = paquete;
		this.keywords = keywords;
		this.estaVencida = estaVencida;
		this.nombreTipoPublicacion = nombreTipoPublicacion;
		
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
	
	public LocalDate getFechaAlta() {
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

	public ArrayList<String> getKeywords() {
		return keywords;
	}

	public EstadoOferta getEstadoOferta() {
		return estadoOferta;
	}

	public BufferedImage getImagen() {
		return imagen;
	}

	public DTPaquetePublicacion getPaqueteAsociado() {
		return paqueteAsociado;
	}

	public LocalDate getFechaResolucion() {
		return fechaResolucion;
	}

	public Boolean getEstaVencida() {
		return estaVencida;
	}

	public String getNombreTipoPublicacion() {
		return nombreTipoPublicacion;
	}
	

}