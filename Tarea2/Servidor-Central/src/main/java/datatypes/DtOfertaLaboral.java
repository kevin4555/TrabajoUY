package main.java.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlTransient;
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
public class DtOfertaLaboral
      implements
      Serializable {
  private static final long serialVersionUID =
        -8050558380197586821L;
  private String nombre;
  private String descripcion;
  private String ciudad;
  private String departamento;
  private String horarioInicio;
  private String horarioFinal;
  private Float remuneracion;
  @XmlTransient
  private LocalDate fechaAlta;
  @XmlTransient
  private LocalDate fechaResolucion;
  private List<DtPostulacion> postulaciones;
  private EstadoOferta estadoOferta;
  @XmlTransient
  private BufferedImage imagen;
  private String imagenBase64;
  private DtPaquetePublicacion paqueteAsociado;
  private List<String> keywords;
  private Boolean estaVencida;
  private String nombreTipoPublicacion;
  private String empresa;
  private int visitas;
  private String exposicion;
  @XmlTransient
  private LocalDate fechaFinalizacion;
  private String fechaAltaString;
  private String fechaResolucionString;
  private String fechaFinalizacionString;
  private String fechaSeleccionString;
  private int cantidadFavoritos;

  /**
   * Contructor.
   */
  public DtOfertaLaboral(
        String nombre,
          String descripcion,
          String ciudad,
          String departamento,
          String horarioInicial,
          String horarioFinal,
          Float remuneracion,
          LocalDate fechaAlta,
          List<DtPostulacion> postulaciones,
          LocalDate fechaResolucion,
          EstadoOferta estado,
          BufferedImage imagen,
          DtPaquetePublicacion paquete,
          List<String> keywords,
          Boolean estaVencida,
          String nombreTipoPublicacion,
          String empresa,
          int visitas,
          String exposicion,
          LocalDate fechaFinalizacion,
          LocalDate fechaSeleccion,
          int cantidadFavoritos)
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
      ByteArrayOutputStream baos =
            new ByteArrayOutputStream();
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
    this.fechaAltaString = fechaAlta.toString();
    this.fechaResolucionString = null;
    this.fechaFinalizacionString = null;
    this.fechaSeleccionString = null;
    if (fechaResolucion != null) {
      this.fechaResolucionString =
            fechaResolucion.toString();
    }
    if (fechaFinalizacion != null) {
      this.fechaFinalizacionString =
            fechaFinalizacion.toString();
    }
    if (fechaSeleccion != null) {
      this.fechaSeleccionString =
            fechaSeleccion.toString();
    }
    this.cantidadFavoritos = cantidadFavoritos;
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

  public void
        setFechaFinalizacion(LocalDate fechaFinalizacion) {
    this.fechaFinalizacion = fechaFinalizacion;
  }

  public String getFechaAltaString() {
    return fechaAltaString;
  }

  public void setFechaAltaString(String fechaAltaString) {
    this.fechaAltaString = fechaAltaString;
  }

  public String getFechaResolucionString() {
    return fechaResolucionString;
  }

  public void setFechaResolucionString(
        String fechaResolucionString) {
    this.fechaResolucionString = fechaResolucionString;
  }

  public String getFechaFinalizacionString() {
    return fechaFinalizacionString;
  }

  public void setFechaFinalizacionString(
        String fechaFinalizacionString) {
    this.fechaFinalizacionString = fechaFinalizacionString;
  }

  public String getFechaSeleccionString() {
    return fechaSeleccionString;
  }

  public void setFechaSeleccionString(String fechaSeleccionString) {
    this.fechaSeleccionString = fechaSeleccionString;
  }

  public int getCantidadFavoritos() {
    return cantidadFavoritos;
  }

  public void setCantidadFavoritos(int cantidadFavoritos) {
    this.cantidadFavoritos = cantidadFavoritos;
  }

}