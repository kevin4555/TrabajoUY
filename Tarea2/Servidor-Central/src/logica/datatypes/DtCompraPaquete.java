package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlList;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * Clase DtCompraPaquete.
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class DtCompraPaquete implements Serializable {
  
  public void setFechaCompra(LocalDate fechaCompra) {
    this.fechaCompra = fechaCompra;
  }
  
  public DtCompraPaquete() {
  }
  
  public void setFechaVencimiento(
      LocalDate fechaVencimiento) {
    this.fechaVencimiento = fechaVencimiento;
  }
  
  public void setPublicacionesRestantes(
      List<DtCantidadTipoPublicacionRestante> publicacionesRestantes) {
    this.publicacionesRestantes = publicacionesRestantes;
  }
  
  public void setPaquete(DtPaquetePublicacion paquete) {
    this.paquete = paquete;
  }
  
  private LocalDate fechaCompra;
  private LocalDate fechaVencimiento;
  
  @XmlJavaTypeAdapter(ListAdapter.class)
  private List<DtCantidadTipoPublicacionRestante> publicacionesRestantes;
  private DtPaquetePublicacion paquete;
  
  /**
   * Constructor.
   */
  
  public DtCompraPaquete(LocalDate fechaCompra,
      LocalDate fechaVencimiento,
      List<DtCantidadTipoPublicacionRestante> publicacionesRestantes,
      DtPaquetePublicacion paquete) {
    this.fechaCompra = fechaCompra;
    this.fechaVencimiento = fechaVencimiento;
    this.publicacionesRestantes = publicacionesRestantes;
    this.paquete = paquete;
  }
  
  public LocalDate getFechaCompra() {
    return fechaCompra;
  }
  
  public LocalDate getFechaVencimiento() {
    return fechaVencimiento;
  }
  
  public List<DtCantidadTipoPublicacionRestante> getPublicacionesRestantes() {
    return publicacionesRestantes;
  }
  
  public DtPaquetePublicacion getPaquete() {
    return paquete;
  }
  
}
