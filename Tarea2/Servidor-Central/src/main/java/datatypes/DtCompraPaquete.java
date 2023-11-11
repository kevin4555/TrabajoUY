package main.java.datatypes;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlTransient;

/**
 * Clase DtCompraPaquete.
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class DtCompraPaquete
      implements
      Serializable {

  private static final long serialVersionUID =
        7738489149194847484L;
  @XmlTransient
  private LocalDate fechaCompra;
  @XmlTransient
  private LocalDate fechaVencimiento;
  private List<
        DtCantidadTipoPublicacionRestante> publicacionesRestantes;
  private DtPaquetePublicacion paquete;
  private String fechaCompraString;
  private String fechaVencimientoString;

  /**
   * Constructor.
   */

  public DtCompraPaquete(
        LocalDate fechaCompra,
          LocalDate fechaVencimiento,
          List<DtCantidadTipoPublicacionRestante> publicacionesRestantes,
          DtPaquetePublicacion paquete) {
    this.fechaCompra = fechaCompra;
    this.fechaVencimiento = fechaVencimiento;
    this.publicacionesRestantes = publicacionesRestantes;
    this.paquete = paquete;
    this.fechaCompraString = fechaCompra.toString();
    this.fechaVencimientoString =
          fechaVencimiento.toString();
  }

  public LocalDate getFechaCompra() {
    return fechaCompra;
  }

  public LocalDate getFechaVencimiento() {
    return fechaVencimiento;
  }

  public List<DtCantidadTipoPublicacionRestante>
        getPublicacionesRestantes() {
    return publicacionesRestantes;
  }

  public DtPaquetePublicacion getPaquete() {
    return paquete;
  }

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

  public String getFechaCompraString() {
    return fechaCompraString;
  }

  public void
        setFechaCompraString(String fechaCompraString) {
    this.fechaCompraString = fechaCompraString;
  }

  public String getFechaVencimientoString() {
    return fechaVencimientoString;
  }

  public void setFechaVencimientoString(
        String fechaVencimientoString) {
    this.fechaVencimientoString = fechaVencimientoString;
  }

}
