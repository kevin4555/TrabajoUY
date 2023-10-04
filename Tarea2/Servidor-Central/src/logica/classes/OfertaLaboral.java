package logica.classes;

import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import logica.datatypes.DtOfertaLaboral;
import logica.datatypes.DtpaquetePublicacion;
import logica.datatypes.Dtpostulacion;
import logica.datatypes.EstadoOferta;

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
  
  /**
   * Constructor .
   */
  
  public OfertaLaboral(String nombre, String descripcion, String horarioInicial,
      String horarioFinal, float remuneracion, String ciudad, String departamento,
      LocalDate fechaAlta, TipoPublicacion tipoPublicacion, BufferedImage imagen, Empresa empresa) {
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
    
  }
  
  public String getNombre() {
    return nombre;
  }
  
  public void setNombre(String nombre) {
    this.nombre = nombre;
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
  
  public void setKeword(List<Keyword> keywords) {
    this.listaKeywords = keywords;
  }
  
  public TipoPublicacion getTipoPublicacion() {
    return tipoPublicacion;
  }
  
  public void setTipoPublicacion(TipoPublicacion tipoPublicacion) {
    this.tipoPublicacion = tipoPublicacion;
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
  
  public List<Dtpostulacion> obtenerDtPostulacion() {
    List<Dtpostulacion> listaDtpostulaciones = new ArrayList<Dtpostulacion>();
    for (Postulacion iter : postulaciones) {
      listaDtpostulaciones.add(iter.obtenerDtpostulacion());
    }
    return listaDtpostulaciones;
  }
  
  /**
   * Metodo obtener DTOfertaLaboral .
   */
  
  public DtOfertaLaboral obtenerDtOfertaLaboral() {
    DtpaquetePublicacion paquete = null;
    if (compraPaquete != null) {
      paquete = compraPaquete.obtenerDtpaquete();
    }
    
    List<String> keywords = new ArrayList<String>();
    for (Keyword keyword : listaKeywords) {
      keywords.add(keyword.getNombre());
    }
    
    DtOfertaLaboral dtOfertaLaboral = new DtOfertaLaboral(this.getNombre(),
        this.getDescripcion(), this.getCiudad(), this.getDepartamento(),
        this.getHorarioInicial(), this.getHorarioFinal(), this.getRemunaracion(),
        this.getFechaAlta(), this.obtenerDtPostulacion(), fechaResolucion, estado, imagen,
        paquete, keywords, this.empresa.getNickname());
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
  
  public CompraPaquete getCompraPaquete() {
    return compraPaquete;
  }
  
  public void setCompraPaquete(CompraPaquete compraPaquete) {
    this.compraPaquete = compraPaquete;
  }
  
  public void resolucionOferta(EstadoOferta estado, LocalDate fechaResolucion) {
    this.estado = estado;
    this.fechaResolucion = fechaResolucion;
  }
  
  /**
   * Metodo para saber si tiene Keyword .
   */
  
  public Boolean tieneKeyword(String keywordBuscada) {
    Boolean resultado = false;
    for (Keyword keyword : listaKeywords) {
      if (keyword.getNombre() == keywordBuscada) {
        resultado = true;
        break;
      }
    }
    return resultado;
  }
  
  public DtpaquetePublicacion obtenerDtpaquete() {
    return compraPaquete.obtenerDtpaquete();
  }
  
  /**
   * Metodo para saber si la oferta esta vencida .
   */
  
  public Boolean estaVencida() {
    LocalDate fechaActual = LocalDate.now();
    LocalDate fechaVencimiento = LocalDate.of(fechaResolucion.getYear(),
        fechaResolucion.getMonthValue(), fechaResolucion.getDayOfMonth())
        .plusDays(tipoPublicacion.getDuracionDia());
    return fechaActual.isAfter(fechaVencimiento);
  }
}
