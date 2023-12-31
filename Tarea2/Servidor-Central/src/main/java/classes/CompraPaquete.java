package main.java.classes;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import main.java.datatypes.DtCantidadTipoPublicacionRestante;
import main.java.datatypes.DtCompraPaquete;
import main.java.datatypes.DtPaquetePublicacion;

/**
 * Clase CompraPaquete.
 */
public class CompraPaquete {
  private LocalDate fechaVencimiento;
  private LocalDate fechaCompra;
  private List<
        CantidadTipoPublicacionRestante> cantidadesRestantes;
  private PaquetePublicacion paquete;

  /**
   * Constructor.
   */
  public CompraPaquete(
        LocalDate fechaCompra,
          PaquetePublicacion paquete) {
    this.fechaCompra = fechaCompra;
    LocalDate copiaFechaCompra = LocalDate
          .of(
                fechaCompra.getYear(),
                fechaCompra.getMonth(),
                fechaCompra
                      .getDayOfMonth());
    this.fechaVencimiento = copiaFechaCompra
          .plusDays(paquete
                .getPeriodoValidez());
    this.paquete = paquete;
    List<CantidadTotalTipoPublicacion> listaCantidadtotal =
          this.paquete
                .obtenerCantidadTotalTipoPublicaciones();
    this.cantidadesRestantes =
          new ArrayList<CantidadTipoPublicacionRestante>();
    for (CantidadTotalTipoPublicacion cantidad : listaCantidadtotal) {
      this.cantidadesRestantes
            .add(
                  new CantidadTipoPublicacionRestante(
                        cantidad
                              .getCantidadTotal(),
                        cantidad
                              .getTipoPublicacion()));
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

  public DtPaquetePublicacion obtenerDtpaquete()
        throws IOException {
    return this.paquete
          .obtenerDtPaquete();
  }

  /**
   * Disminuir cantidad tipo de publicación.
   */
  public void gastarTipoPublicacion(
        String nombreTipoPublicacion) {
    for (CantidadTipoPublicacionRestante cantidad : cantidadesRestantes) {
      if (nombreTipoPublicacion
            .equals(cantidad
                  .getNombreTipoPublicacion())) {
        cantidad.reducirCantidad();
        break;
      }
    }
  }

  /**
   * Metodo obtenerDtCompraPaquete.
   */
  public DtCompraPaquete obtenerDtCompraPaquete()
        throws IOException {
    List<DtCantidadTipoPublicacionRestante> dtCantidadesRestantes =
          new ArrayList<
                DtCantidadTipoPublicacionRestante>();
    for (CantidadTipoPublicacionRestante cantidad : cantidadesRestantes) {
      dtCantidadesRestantes
            .add(cantidad
                  .obtenerDtTipoCantidadRestante());
    }
    DtPaquetePublicacion dtpaquete = paquete
          .obtenerDtPaquete();
    return new DtCompraPaquete(
          fechaCompra,
          fechaVencimiento,
          dtCantidadesRestantes,
          dtpaquete);
  }
}
