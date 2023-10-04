package logica.datatypes;

import java.time.LocalDate;
import java.util.List;

public class DtCompraPaquete {
  private LocalDate fechaCompra;
  private LocalDate fechaVencimiento;
  private List<DtCantidadTipoPublicacionRestante> publicacionesRestantes;
  private DtpaquetePublicacion paquete;
  public DtCompraPaquete(LocalDate fechaCompra, LocalDate fechaVencimiento,
      List<DtCantidadTipoPublicacionRestante> publicacionesRestantes,
      DtpaquetePublicacion paquete) {
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
  public DtpaquetePublicacion getPaquete() {
    return paquete;
  }
  
}
