package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import javax.imageio.ImageIO;

/**
 * Clase DTOfertaLaboral.
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class DtOfertaLaboral implements Serializable {

  private static final long serialVersionUID = -8050558380197586821L;
  private String nombre;
  private String descripcion;
  private String ciudad;
  private String departamento;
  private String horarioInicio;
  private String horarioFinal;
  private Float remuneracion;
  private LocalDate fechaAlta;
  private LocalDate fechaResolucion;
  @XmlJavaTypeAdapter(ListAdapter.class)
  private List<Dtpostulacion> postulaciones;
  private EstadoOferta estadoOferta;
  private BufferedImage imagen;
  private String imagenBase64;
  private DtpaquetePublicacion paqueteAsociado;
  @XmlJavaTypeAdapter(ListAdapter.class)
  private List<String> keywords;
  private Boolean estaVencida;
  private String nombreTipoPublicacion;
  private String empresa;
  private int visitas;
  private String exposicion;
  private LocalDate fechaFinalizacion;
  

  

  /**
   * Contructor.
   */
  
  public DtOfertaLaboral(String nombre, String descripcion,
      String ciudad, String departamento,
      String horarioInicial, String horarioFinal,
      Float remuneracion, LocalDate fechaAlta,
      List<DtPostulacion> postulaciones,
      LocalDate fechaResolucion, EstadoOferta estado,
      BufferedImage imagen, DtPaquetePublicacion paquete,
      List<String> keywords, Boolean estaVencida,
      String nombreTipoPublicacion, String empresa, int visitas, String exposicion, LocalDate fechaFinalizacion)
      throws IOException {
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
    
    if (imagen != null) {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      ImageIO.write(this.imagen, "png", baos);
      this.imagenBase64 = Base64.getEncoder()
          .encodeToString(baos.toByteArray());
    }
    this.paqueteAsociado = paquete;
    this.keywords = keywords;
    this.estaVencida = estaVencida;
    this.nombreTipoPublicacion = nombreTipoPublicacion;
    this.empresa = empresa;
    this.visitas = visitas;
    this.exposicion = exposicion;
    this.fechaFinalizacion = fechaFinalizacion;
  }
  
  public DtOfertaLaboral() {
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
  
  public List<DtPostulacion> getPostulaciones() {
    return postulaciones;
  }
  
  public String getHorarioInicio() {
    return horarioInicio;
  }
  
  public String getHorarioFinal() {
    return horarioFinal;
  }
  
  public List<String> getKeywords() {
    return keywords;
  }
  
  public EstadoOferta getEstadoOferta() {
    return estadoOferta;
  }
  
  public BufferedImage getImagen() {
    return imagen;
  }
  
  public DtPaquetePublicacion getPaqueteAsociado() {
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
  
  public String getEmpresa() {
    return empresa;
  }
  
  public String getImagenBase64() {
    return imagenBase64;
  }
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
  
  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }
  
  public void setCiudad(String ciudad) {
    this.ciudad = ciudad;
  }
  
  public void setDepartamento(String departamento) {
    this.departamento = departamento;
  }
  
  public void setHorarioInicio(String horarioInicio) {
    this.horarioInicio = horarioInicio;
  }
  
  public void setHorarioFinal(String horarioFinal) {
    this.horarioFinal = horarioFinal;
  }
  
  public void setRemuneracion(Float remuneracion) {
    this.remuneracion = remuneracion;
  }
  
  public void setFechaAlta(LocalDate fechaAlta) {
    this.fechaAlta = fechaAlta;
  }
  
  public void setFechaResolucion(
      LocalDate fechaResolucion) {
    this.fechaResolucion = fechaResolucion;
  }
  
  public void setPostulaciones(
      List<DtPostulacion> postulaciones) {
    this.postulaciones = postulaciones;
  }
  
  public void setEstadoOferta(EstadoOferta estadoOferta) {
    this.estadoOferta = estadoOferta;
  }
  
  public void setImagen(BufferedImage imagen) {
    this.imagen = imagen;
  }
  
  public void setImagenBase64(String imagenBase64) {
    this.imagenBase64 = imagenBase64;
  }
  
  public void setPaqueteAsociado(
      DtPaquetePublicacion paqueteAsociado) {
    this.paqueteAsociado = paqueteAsociado;
  }
  
  public void setKeywords(List<String> keywords) {
    this.keywords = keywords;
  }
  
  public void setEstaVencida(Boolean estaVencida) {
    this.estaVencida = estaVencida;
  }
  
  public void setNombreTipoPublicacion(
      String nombreTipoPublicacion) {
    this.nombreTipoPublicacion = nombreTipoPublicacion;
  }
  
  public void setEmpresa(String empresa) {
    this.empresa = empresa;
  }

  public int getVisitas() {
    return visitas;
  
  /**
   * Contructor.
   */
  
  public DtOfertaLaboral(String nombre, String descripcion,
      String ciudad, String departamento,
      String horarioInicial, String horarioFinal,
      Float remuneracion, LocalDate fechaAlta,
      List<Dtpostulacion> postulaciones,
      LocalDate fechaResolucion, EstadoOferta estado,
      BufferedImage imagen, DtpaquetePublicacion paquete,
      List<String> keywords, Boolean estaVencida,
      String nombreTipoPublicacion, String empresa)
      throws IOException {
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
    
    if (imagen != null) {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      ImageIO.write(this.imagen, "png", baos);
      this.imagenBase64 = Base64.getEncoder()
          .encodeToString(baos.toByteArray());
    }
    this.paqueteAsociado = paquete;
    this.keywords = keywords;
    this.estaVencida = estaVencida;
    this.nombreTipoPublicacion = nombreTipoPublicacion;
    this.empresa = empresa;
    
  }

  public void setVisitas(int visitas) {
    this.visitas = visitas;
  }

  public String getExposicion() {
    return exposicion;
  }

  public void setExposicion(String exposicion) {
    this.exposicion = exposicion;
  }

  public LocalDate getFechaFinalizacion() {
    return fechaFinalizacion;
  }

  public void setFechaFinalizacion(LocalDate fechaFinalizacion) {
    this.fechaFinalizacion = fechaFinalizacion;
  }
  
}