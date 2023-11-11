package main.java.classes;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import main.java.datatypes.DtOfertaLaboral;
import main.java.datatypes.DtPaquetePublicacion;
import main.java.datatypes.DtPostulacion;
import main.java.datatypes.EstadoOferta;

/**
 * Clase Oferta laboral .
 */

public class OfertaLaboral {
  private String nombre;
  private String descripcion;
  private String ciudad;
  private String departamento;
  private String horarioInicial;
  private String horarioFinal;
  private float remuneracion;
  private LocalDate fechaAlta;
  private LocalDate fechaResolucion;
  private BufferedImage imagen;
  private EstadoOferta estado;
  private List<Keyword> listaKeywords;
  private TipoPublicacion tipoPublicacion;
  private List<Postulacion> postulaciones;
  private CompraPaquete compraPaquete;
  private Empresa empresa;
  private int cantidadVisitas;
  private LocalDate fechaFinalizacion;
  private LocalDate fechaSeleccion;

  /**
   * Constructor .
   */

  public OfertaLaboral(
        String nombre,
          String descripcion,
          String horarioInicial,
          String horarioFinal,
          float remuneracion,
          String ciudad,
          String departamento,
          LocalDate fechaAlta,
          TipoPublicacion tipoPublicacion,
          BufferedImage imagen,
          Empresa empresa) {
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.ciudad = ciudad;
    this.departamento = departamento;
    this.horarioInicial = horarioInicial;
    this.horarioFinal = horarioFinal;
    this.remuneracion = remuneracion;
    this.fechaAlta = fechaAlta;
    this.tipoPublicacion = tipoPublicacion;
    this.postulaciones = new ArrayList<Postulacion>();
    this.listaKeywords = new ArrayList<Keyword>();
    this.estado = EstadoOferta.INGRESADA;
    this.imagen = imagen;
    this.compraPaquete = null;
    this.fechaResolucion = null;
    this.empresa = empresa;
    this.cantidadVisitas = 0;
    this.fechaFinalizacion = null;
    this.fechaSeleccion = null;

  }

  public String getNombre() {
    return nombre;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public String getCiudad() {
    return ciudad;
  }

  public void setCiudad(String ciudad) {
    this.ciudad = ciudad;
  }

  public String getDepartamento() {
    return departamento;
  }

  public void setDepartamento(String departamento) {
    this.departamento = departamento;
  }

  public float getRemunaracion() {
    return remuneracion;
  }

  public void setRemunaracion(float remuneracion) {
    this.remuneracion = remuneracion;
  }

  public LocalDate getFechaAlta() {
    return fechaAlta;
  }

  public void setFechaAlta(LocalDate fechaAlta) {
    this.fechaAlta = fechaAlta;
  }

  public List<Keyword> getKeywords() {
    return listaKeywords;
  }

  public void setKeyword(List<Keyword> keywords) {
    this.listaKeywords = keywords;
  }

  public TipoPublicacion getTipoPublicacion() {
    return tipoPublicacion;
  }

  public void setTipoPublicacion(
        TipoPublicacion tipoPublicacion) {
    this.tipoPublicacion = tipoPublicacion;
  }

  public LocalDate getFechaSeleccion() {
    return fechaSeleccion;
  }

  public void agregarKeyword(Keyword keyword) {
    listaKeywords.add(keyword);
  }

  public void agregarPostulacion(Postulacion postulacion) {
    this.postulaciones.add(postulacion);
  }

  public List<Postulacion> getPostulacion() {
    return postulaciones;
  }

  /**
   * Metodo obtener DTPostulacion .
   */

  public List<DtPostulacion> obtenerDtPostulacion() {
    List<DtPostulacion> listaDtpostulaciones =
          new ArrayList<DtPostulacion>();
    for (Postulacion iter : postulaciones) {
      listaDtpostulaciones.add(iter.obtenerDtpostulacion());
    }
    return listaDtpostulaciones;
  }

  /**
   * Metodo obtener DTOfertaLaboral .
   */

  public DtOfertaLaboral obtenerDtOfertaLaboral()
        throws IOException {
    DtPaquetePublicacion paquete = null;
    if (compraPaquete != null) {
      paquete = compraPaquete.obtenerDtpaquete();
    }

    List<String> keywords = new ArrayList<String>();
    for (Keyword keyword : listaKeywords) {
      keywords.add(keyword.getNombre());
    }
    Boolean estaVencida = null;
    if (estado == EstadoOferta.CONFIRMADA) {
      estaVencida = this.estaVencida();
    }

    DtOfertaLaboral dtOfertaLaboral = new DtOfertaLaboral(
          this.getNombre(),
          this.getDescripcion(), this.getCiudad(),
          this.getDepartamento(),
          this.getHorarioInicial(), this.getHorarioFinal(),
          this.getRemunaracion(),
          this.getFechaAlta(), this.obtenerDtPostulacion(),
          fechaResolucion, estado, imagen,
          paquete, keywords, estaVencida,
          this.tipoPublicacion.getNombre(),
          this.empresa.getNickname(), this.cantidadVisitas,
          tipoPublicacion.getExposicion(),
          this.fechaFinalizacion,
          this.fechaSeleccion);
    return dtOfertaLaboral;
  }

  public String getHorarioFinal() {
    return horarioFinal;
  }

  public void setHorarioFinal(String horarioFinal) {
    this.horarioFinal = horarioFinal;
  }

  public String getHorarioInicial() {
    return horarioInicial;
  }

  public void setHorarioInicial(String horarioInicial) {
    this.horarioInicial = horarioInicial;
  }

  public Boolean estaCompradaPorPaquete() {
    return compraPaquete != null;
  }

  public EstadoOferta getEstado() {
    return estado;
  }

  public Empresa getEmpresa() {
    return empresa;
  }

  public CompraPaquete getCompraPaquete() {
    return compraPaquete;
  }

  public void setCompraPaquete(
        CompraPaquete compraPaquete) {
    this.compraPaquete = compraPaquete;
  }

  public void resolucionOferta(EstadoOferta estado,
        LocalDate fechaResolucion) {
    this.estado = estado;
    this.fechaResolucion = fechaResolucion;
  }

  /**
   * Metodo para saber si tiene Keyword .
   */

  public Boolean tieneKeyword(String keywordBuscada) {
    Boolean resultado = false;
    for (Keyword keyword : listaKeywords) {
      if (keywordBuscada.equals(keyword.getNombre())) {
        resultado = true;
        break;
      }
    }
    return resultado;
  }

  public DtPaquetePublicacion obtenerDtpaquete()
        throws IOException {
    return compraPaquete.obtenerDtpaquete();
  }

  /**
   * Metodo para saber si la oferta esta vencida .
   */

  public Boolean estaVencida() {
    LocalDate fechaActual = LocalDate.now();
    LocalDate fechaVencimiento = LocalDate
          .of(fechaResolucion.getYear(),
                fechaResolucion.getMonthValue(),
                fechaResolucion.getDayOfMonth())
          .plusDays(tipoPublicacion.getDuracionDia());
    return fechaActual.isAfter(fechaVencimiento);
  }

  public void agregarVisita() {
    this.cantidadVisitas++;
  }

  public int getCantidadVisitas() {
    return cantidadVisitas;
  }

  public void finalizarOferta() {
    this.estado = EstadoOferta.FINALIZADA;
    this.fechaFinalizacion = LocalDate.now();
  }

  /**
   * Metodo ordenarPostulaciones.
   */

  public void ordenarPostulaciones(
        List<String> nicknamesPostulantes) {
    this.fechaSeleccion = LocalDate.now();
    List<Postulacion> listaOrdenada =
          new ArrayList<Postulacion>();
    for (String nickname : nicknamesPostulantes) {
      for (Postulacion postulacion : postulaciones) {
        if (postulacion.getPostulante().getNickname()
              .equals(nickname)) {
          listaOrdenada.add(postulacion);
        }
      }
    }
    this.postulaciones = listaOrdenada;
  }

  /**
   * Metodo obtenerPosicion.
   */

  public int obtenerPosicion(String nicknamePostulante) {
    int posicion = 1;
    for (Postulacion postulacion : postulaciones) {
      if (nicknamePostulante.equals(
            postulacion.getPostulante().getNickname())) {
        break;
      }
      posicion++;
    }
    return posicion;
  }
}
