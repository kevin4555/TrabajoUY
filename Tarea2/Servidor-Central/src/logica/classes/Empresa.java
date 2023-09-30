package logica.classes;

import excepciones.OfertaLaboralYaExisteException;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import logica.datatypes.DtOfertaLaboral;
import logica.datatypes.Dtempresa;
import logica.datatypes.DtpaquetePublicacion;
import logica.datatypes.Dtusuario;
import logica.datatypes.EstadoOferta;

/**
 * Clase Empresa.
 */

public class Empresa extends Usuario {
  private String descripcion;
  private String sitioWeb;
  private ArrayList<OfertaLaboral> ofertasLaborales;
  private ArrayList<CompraPaquete> compraPaquetes;
  
  /**
   * Constructor.
   */
  
  public Empresa(String nickname, String nombre, String apellido, String email,
      String descripcion, String sitioWeb, BufferedImage imagen, String contrasenia) {
    super(nickname, nombre, apellido, email, imagen, contrasenia);
    setDescripcion(descripcion);
    setSitioWeb(sitioWeb);
    this.ofertasLaborales = new ArrayList<OfertaLaboral>();
    this.compraPaquetes = new ArrayList<CompraPaquete>();
  }
  
  public String getDescripcion() {
    return descripcion;
  }
  
  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }
  
  public String getSitioWeb() {
    return sitioWeb;
  }
  
  public void setSitioWeb(String sitioWeb) {
    this.sitioWeb = sitioWeb;
  }
  
  /**
   * Metodo para agregar oferta laboral.
   */
  
  public void agregarOferta(OfertaLaboral ol) throws OfertaLaboralYaExisteException {
    if (ofertasLaborales.indexOf(ol) != -1) {
      throw new OfertaLaboralYaExisteException("La Oferta Laboral " + ol.getNombre()
          + " ya esta asociada a la Empresa " + this.nickname);
    }
    this.ofertasLaborales.add(ol);
  }
  
  /**
   * Metodo obtener nombre ofertas laborales.
   */
  
  public ArrayList<String> obtenerNombresOfertas() {
    ArrayList<String> ofertas = new ArrayList<String>();
    for (OfertaLaboral oferta : this.ofertasLaborales) {
      ofertas.add(oferta.getNombre());
    }
    return ofertas;
  }
  
  public ArrayList<OfertaLaboral> getOfertasLaborales() {
    return ofertasLaborales;
  }
  
  /**
   * Obtener DTEmpresa.
   */
  
  public Dtempresa obtenerDtempresa() {
    
    ArrayList<DtOfertaLaboral> listaDtofertas = new ArrayList<DtOfertaLaboral>();
    for (OfertaLaboral oferta : ofertasLaborales) {
      listaDtofertas.add(oferta.obtenerDtOfertaLaboral());
    }
    Dtempresa resultado = new Dtempresa(this.nickname, this.nombre, this.apellido, this.email,
        this.getImagen(), this.getContrasenia(), listaDtofertas, this.descripcion,
        this.sitioWeb);
    return resultado;
  }
  
  @Override
  public ArrayList<String> listarOfertasUsuario() {
    return this.obtenerNombresOfertas();
  }
  
  @Override
  public Dtusuario obtenerDtusuario() {
    return this.obtenerDtempresa();
  }
  
  /**
   * Obtener DTOfertaLaboral.
   */
  
  public ArrayList<DtOfertaLaboral> obtenerDtofertasIngresadas() {
    ArrayList<DtOfertaLaboral> listaResultado = new ArrayList<DtOfertaLaboral>();
    for (OfertaLaboral oferta : ofertasLaborales) {
      if (oferta.getEstado() == EstadoOferta.INGRESADA) {
        listaResultado.add(oferta.obtenerDtOfertaLaboral());
      }
    }
    return listaResultado;
  }
  
  /**
   * Obtener DTOfertaLaboralConfirmadas.
   */
  
  public ArrayList<DtOfertaLaboral> obtenerDtofertasConfirmadas() {
    ArrayList<DtOfertaLaboral> listaResultado = new ArrayList<DtOfertaLaboral>();
    for (OfertaLaboral oferta : ofertasLaborales) {
      if (oferta.getEstado() == EstadoOferta.CONFIRMADA) {
        listaResultado.add(oferta.obtenerDtOfertaLaboral());
      }
    }
    return listaResultado;
  }
  
  /**
   * Obtener DTOfertaLaboralRechazadas.
   */
  
  public ArrayList<DtOfertaLaboral> obtenerDtofertasRechazadas() {
    ArrayList<DtOfertaLaboral> listaResultado = new ArrayList<DtOfertaLaboral>();
    for (OfertaLaboral oferta : ofertasLaborales) {
      if (oferta.getEstado() == EstadoOferta.RECHAZADA) {
        listaResultado.add(oferta.obtenerDtOfertaLaboral());
      }
    }
    return listaResultado;
  }
  
  public void comprarPaquete(CompraPaquete compraPaquete) {
    compraPaquetes.add(compraPaquete);
  }
  
  /**
   * Verifica si el paquete esta comprado.
   */
  
  public Boolean estaCompradoPaquete(String nombrePaquete) {
    Boolean resultado = false;
    for (CompraPaquete compraPaquete : compraPaquetes) {
      if (compraPaquete.obtenerNombrePaquete() == nombrePaquete) {
        resultado = true;
        break;
      }
    }
    return resultado;
  }
  
  /**
   * Obtener DTPaquetePublicacion.
   */
  
  public ArrayList<DtpaquetePublicacion> obtenerDtpaquetes() {
    ArrayList<DtpaquetePublicacion> listaReultado = new ArrayList<DtpaquetePublicacion>();
    for (CompraPaquete compraPaquete : compraPaquetes) {
      listaReultado.add(compraPaquete.obtenerDtpaquete());
    }
    return listaReultado;
  }
  
  /**
   * Obtener listado de paquetes comprados.
   */
  
  public ArrayList<String> listarPaquetesComprados() {
    ArrayList<String> listaResultado = new ArrayList<String>();
    for (CompraPaquete compraPaquete : compraPaquetes) {
      listaResultado.add(compraPaquete.obtenerNombrePaquete());
    }
    return listaResultado;
  }
  
  /**
   * Comprar una oferta por paquete.
   */
  
  public void comprarOfertaPorPaquete(String nombrePaquete, String nomTipoPublicacion,
      OfertaLaboral oferta) {
    for (CompraPaquete compra : compraPaquetes) {
      if (compra.obtenerNombrePaquete() == nombrePaquete) {
        compra.gastarTipoPublicacion(nomTipoPublicacion);
        oferta.setCompraPaquete(compra);
        break;
      }
    }
    
  }
  
}
