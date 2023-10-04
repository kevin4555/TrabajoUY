package logica.classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import logica.datatypes.DtCantidadTipoPublicacionRestante;
import logica.datatypes.DtCompraPaquete;
import logica.datatypes.DtpaquetePublicacion;

/**
 * Clase CompraPaquete.
 */

public class CompraPaquete {
  private LocalDate fechaVencimiento;
  private LocalDate fechaCompra;
  private List<CantidadTipoPublicacionRestante> cantidadesRestantes;
  private PaquetePublicacion paquete;
  
  /**
   * Constructor.
   */
  
  public CompraPaquete(LocalDate fechaCompra, PaquetePublicacion paquete) {
    this.fechaCompra = fechaCompra;
    LocalDate copiaFechaCompra = LocalDate.of(fechaCompra.getYear(), fechaCompra.getMonth(),
        fechaCompra.getDayOfMonth());
    this.fechaVencimiento = copiaFechaCompra.plusDays(paquete.getPeriodoValidez());
    this.paquete = paquete;
    
    List<CantidadTotalTipoPublicacion> listaCantidadtotal = 
        this.paquete.obtenerCantidadTotalTipoPublicaciones();
    this.cantidadesRestantes = new ArrayList<CantidadTipoPublicacionRestante>();
    for (CantidadTotalTipoPublicacion cantidad : listaCantidadtotal) {
      this.cantidadesRestantes
          .add(new CantidadTipoPublicacionRestante(
              cantidad.getCantidadTotal(), cantidad.getTipoPublicacion()));
    }
    
  }
  
  public LocalDate getFechaVencimiento() {
    return fechaVencimiento;
  }
  
  public LocalDate getFechaCompra() {
    return fechaCompra;
  }
  
  public String obtenerNombrePaquete() {
    return this.paquete.getNombre();
  }
  
  public DtpaquetePublicacion obtenerDtpaquete() {
    return this.paquete.obtenerDtPaquete();
  }
  
  /**
   * Disminuir cantidad tipo de publicación.
   */
  
  public void gastarTipoPublicacion(String nombreTipoPublicacion) {
    for (CantidadTipoPublicacionRestante cantidad : cantidadesRestantes) {
      if (cantidad.getNombreTipoPublicacion() == nombreTipoPublicacion) {
        cantidad.reducirCantidad();
        break;
      }
    }
  }
  
  public DtCompraPaquete obtenerDtCompraPaquete() {
    ArrayList<DtCantidadTipoPublicacionRestante> dtCantidadesRestantes = new ArrayList<DtCantidadTipoPublicacionRestante>();
    for (CantidadTipoPublicacionRestante cantidad : cantidadesRestantes) {
      dtCantidadesRestantes.add(cantidad.obtenerDtTipoCantidadRestante());
    }
    DtpaquetePublicacion dtpaquete = paquete.obtenerDtPaquete();
    return new DtCompraPaquete(fechaCompra, fechaVencimiento, dtCantidadesRestantes,
        dtpaquete);
  }
}
