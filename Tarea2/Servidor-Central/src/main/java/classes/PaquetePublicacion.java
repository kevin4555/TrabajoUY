package main.java.classes;

import main.java.excepciones.PaquetePublicacionYaFueComprado;
import main.java.excepciones.TipoDePublicacionYaFueIngresado;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import main.java.datatypes.DtCantidadTipoPublicacion;
import main.java.datatypes.DtPaquetePublicacion;

/**
 * Clase Paquete publicacion .
 */

public class PaquetePublicacion {
  private String nombre;
  private String descripcion;
  private int periodoValidez;
  private float descuento;
  private float costo;
  private BufferedImage imagen;
  private List<
        CantidadTotalTipoPublicacion> cantidadTipoPublicaciones;
  private Boolean estaComprado;
  private LocalDate fechaAlta;

  /**
   * Constructor .
   */

  public PaquetePublicacion(
        String nombre,
          String descripcion,
          int periodoValidez,
          float descuento,
          BufferedImage imagen,
          List<CantidadTotalTipoPublicacion> cantidadTipo,
          LocalDate fechaAlta) {
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.periodoValidez = periodoValidez;
    this.descuento = descuento;
    this.imagen = imagen;
    this.cantidadTipoPublicaciones = cantidadTipo;
    this.estaComprado = false;
    this.fechaAlta = fechaAlta;
    this.setCosto();
  }

  public String getNombre() {
    return nombre;
  }

  private void setCosto() {
    float costoTotal = 0;
    for (CantidadTotalTipoPublicacion cantidadTipoPublicacion : cantidadTipoPublicaciones) {
      costoTotal += cantidadTipoPublicacion
            .obtenerCostoTotalPublicaciones();
    }
    this.costo = costoTotal * ((100 - descuento) / 100);
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public LocalDate getFechaAlta() {
    return this.fechaAlta;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public int getPeriodoValidez() {
    return periodoValidez;
  }

  public void setPeriodoValidez(int periodoValidez) {
    this.periodoValidez = periodoValidez;
  }

  public float getDescuento() {
    return descuento;
  }

  public BufferedImage getImagen() {
    return imagen;
  }

  public void setImagen(BufferedImage imagen) {
    this.imagen = imagen;
  }

  public float getCosto() {
    return this.costo;
  }

  public void setEstaComprado(Boolean estaComprado) {
    this.estaComprado = estaComprado;
  }

  public Boolean getEstaComprado() {
    return estaComprado;
  }

  public void setCantidadTotalTipoPublicaciones(List<
        CantidadTotalTipoPublicacion> cantidadTipoPublicaciones) {
    this.cantidadTipoPublicaciones =
          cantidadTipoPublicaciones;
  }

  /**
   * Metodo obtener DTPaquete .
   */

  public DtPaquetePublicacion obtenerDtPaquete()
        throws IOException {
    List<DtCantidadTipoPublicacion> listDtcantidad =
          new ArrayList<DtCantidadTipoPublicacion>();
    for (CantidadTotalTipoPublicacion cantidad : cantidadTipoPublicaciones) {
      listDtcantidad.add(
            cantidad.obtenerDtcantidadTipoPublicacion());
    }
    return new DtPaquetePublicacion(nombre, descripcion,
          periodoValidez, descuento, costo, imagen,
          listDtcantidad,
          fechaAlta);
  }

  public List<CantidadTotalTipoPublicacion>
        obtenerCantidadTotalTipoPublicaciones() {
    return cantidadTipoPublicaciones;
  }

  /**
   * Metodo obtener nombres de tipo de publicaciones .
   */

  public List<String> obtenerNombresTipoPublicaciones() {
    List<String> listaResultado = new ArrayList<String>();
    for (CantidadTotalTipoPublicacion cantidad : cantidadTipoPublicaciones) {
      listaResultado
            .add(cantidad.getTipoPublicacion().getNombre());
    }
    return listaResultado;
  }

  /**
   * Metodo agregar tipo de publicacion .
   */

  public void agregarTipoPublicacion(
        TipoPublicacion tipoPublicacion, int cantidad)
        throws PaquetePublicacionYaFueComprado,
        TipoDePublicacionYaFueIngresado {
    if (!estaComprado) {
      boolean existeEnColeccion = false;
      for (CantidadTotalTipoPublicacion cantidadTotal : cantidadTipoPublicaciones) {
        if (cantidadTotal.getTipoPublicacion().getNombre()
              == tipoPublicacion.getNombre()) {
          existeEnColeccion = true;
          break;
        }
      }
      if (!existeEnColeccion) {
        CantidadTotalTipoPublicacion nuevoCantidadTotalTipoPublicacion =
              new CantidadTotalTipoPublicacion(
                    cantidad, tipoPublicacion);
        cantidadTipoPublicaciones
              .add(nuevoCantidadTotalTipoPublicacion);
        setCosto();
      } else {
        throw new TipoDePublicacionYaFueIngresado(
              "El tipo de publicación "
                    + tipoPublicacion.getNombre()
                    + " ya fue ingresado");
      }
    } else {
      throw new PaquetePublicacionYaFueComprado(
            "El paquete " + getNombre()
                  + " ya fue comprado");
    }
  }

}
