package logica.datatypes;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import javax.imageio.ImageIO;

/**
 * Clase DTPaquetePublicacion.
 */

public class DtpaquetePublicacion {
  private String nombre;
  private String descripcion;
  private int periodoValidez;
  private float descuento;
  private float costo;
  private BufferedImage imagen;
  private String imagenBase64;
  private List<DtcantidadTipoPublicacion> cantidadTipoPublicaciones;
  private LocalDate fechaAlta;
  
  /**
   * Contructor.
   */
  
  public DtpaquetePublicacion(String nombre, String descripcion, int periodoValidez,
      float descuento, float costo, BufferedImage imagen,
      List<DtcantidadTipoPublicacion> cantidadTipoPublicaciones, LocalDate fechaAlta)
      throws IOException {
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
      this.imagenBase64 = Base64.getEncoder().encodeToString(baos.toByteArray());
    }
    this.cantidadTipoPublicaciones = cantidadTipoPublicaciones;
    this.fechaAlta = fechaAlta;
  }
  
  public BufferedImage getImagen() {
    return imagen;
  }
  
  public List<DtcantidadTipoPublicacion> getCantidadTipoPublicaciones() {
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
