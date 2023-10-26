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
 * Clase DTPaquetePublicacion.
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class DtPaquetePublicacion implements Serializable {

  private static final long serialVersionUID = 7096923820607804084L;
  private String nombre;
  private String descripcion;
  private int periodoValidez;
  private float descuento;
  private float costo;
  private BufferedImage imagen;
  
  public DtPaquetePublicacion() {
  }
  
  private String imagenBase64;
  
  @XmlJavaTypeAdapter(ListAdapter.class)
  private List<DtCantidadTipoPublicacion> cantidadTipoPublicaciones;
  
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
  
  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }
  
  public void setPeriodoValidez(int periodoValidez) {
    this.periodoValidez = periodoValidez;
  }
  
  public void setDescuento(float descuento) {
    this.descuento = descuento;
  }
  
  public void setCosto(float costo) {
    this.costo = costo;
  }
  
  public void setImagen(BufferedImage imagen) {
    this.imagen = imagen;
  }
  
  public void setImagenBase64(String imagenBase64) {
    this.imagenBase64 = imagenBase64;
  }
  
  public void setCantidadTipoPublicaciones(
      List<DtCantidadTipoPublicacion> cantidadTipoPublicaciones) {
    this.cantidadTipoPublicaciones = cantidadTipoPublicaciones;
  }
  
  public void setFechaAlta(LocalDate fechaAlta) {
    this.fechaAlta = fechaAlta;
  }
  
  private LocalDate fechaAlta;
  
  /**
   * Contructor.
   */
  
  public DtPaquetePublicacion(String nombre,
      String descripcion, int periodoValidez,
      float descuento, float costo, BufferedImage imagen,
      List<DtCantidadTipoPublicacion> cantidadTipoPublicaciones,
      LocalDate fechaAlta) throws IOException {
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.periodoValidez = periodoValidez;
    this.descuento = descuento;
    this.costo = costo;
    this.imagen = imagen;
    this.imagenBase64 = null;
    if (imagen != null) {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      ImageIO.write(this.imagen, "png", baos);
      this.imagenBase64 = Base64.getEncoder()
          .encodeToString(baos.toByteArray());
    }
    this.cantidadTipoPublicaciones = cantidadTipoPublicaciones;
    this.fechaAlta = fechaAlta;
  }
  
  public BufferedImage getImagen() {
    return imagen;
  }
  
  public List<DtCantidadTipoPublicacion> getCantidadTipoPublicaciones() {
    return cantidadTipoPublicaciones;
  }
  
  public String getNombre() {
    return nombre;
  }
  
  public String getDescripcion() {
    return descripcion;
  }
  
  public int getPeriodoValidez() {
    return periodoValidez;
  }
  
  public float getDescuento() {
    return descuento;
  }
  
  public float getCosto() {
    return costo;
  }
  
  public LocalDate getFechaAlta() {
    return fechaAlta;
  }
  
  public String getImagenBase64() {
    return imagenBase64;
  }
  
}