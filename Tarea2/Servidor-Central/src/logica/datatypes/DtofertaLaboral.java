package logica.datatypes;

import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Clase DTOfertaLaboral.
 */

public class DtOfertaLaboral {
  private String nombre;
  private String descripcion;
  private String ciudad;
  private String departamento;
  private String horarioInicio;
  private String horarioFinal;
  private Float remuneracion;
  private LocalDate fechaAlta;
  private LocalDate fechaResolucion;
  private ArrayList<Dtpostulacion> postulaciones;
  private EstadoOferta estadoOferta;
  private BufferedImage imagen;
  private DtpaquetePublicacion paqueteAsociado;
  private ArrayList<String> keywords;
  
  /**
   * Contructor.
   */
  
  public DtOfertaLaboral(String nombre, String descripcion, 
      String ciudad, String departamento, String horarioInicial, String horarioFinal, 
      Float remuneracion, LocalDate fechaAlta, ArrayList<Dtpostulacion> postulaciones,
      LocalDate fechaResolucion, EstadoOferta estado, BufferedImage imagen, 
      DtpaquetePublicacion paquete,
      ArrayList<String> keywords) {
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
  
  public ArrayList<Dtpostulacion> getPostulaciones() {
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
  
  public DtpaquetePublicacion getPaqueteAsociado() {
    return paqueteAsociado;
  }
  
  public LocalDate getFechaResolucion() {
    return fechaResolucion;
  }
  
}