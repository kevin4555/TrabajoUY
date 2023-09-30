package logica.classes;

import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.util.ArrayList;
import logica.datatypes.DtOfertaLaboral;
import logica.datatypes.Dtpostulacion;
import logica.datatypes.Dtpostulante;
import logica.datatypes.Dtusuario;

/**
 * Clase Postulante .
 */

public class Postulante extends Usuario {
  private LocalDate fechaNacimiento;
  private String nacionalidad;
  private ArrayList<Postulacion> postulaciones;
  
  /**
   * Constructor.
   */
  
  public Postulante(String nickname, String nombre, String apellido,
      String email, LocalDate fechaNacimiento,
      String nacionalidad, BufferedImage imagen, String contrasenia) {
    super(nickname, nombre, apellido, email, imagen, contrasenia);
    setFechaNacimiento(fechaNacimiento);
    setNacionalidad(nacionalidad);
    this.postulaciones = new ArrayList<Postulacion>();
  }
  
  public LocalDate getFechaNacimiento() {
    return fechaNacimiento;
  }
  
  public void setFechaNacimiento(LocalDate fechaNacimiento) {
    this.fechaNacimiento = fechaNacimiento;
  }
  
  public String getNacionalidad() {
    return nacionalidad;
  }
  
  public void setNacionalidad(String nacionalidad) {
    this.nacionalidad = nacionalidad;
  }
  
  public ArrayList<Postulacion> getPostulaciones() {
    return postulaciones;
  }
  
  public Dtpostulante obtenerDtpostulante() {
    return new Dtpostulante(this.nickname, this.nombre, this.apellido, this.email, this.getImagen(),
        this.getContrasenia(), this.obtenerDtofertas(), this.fechaNacimiento, this.nacionalidad);
  }
  
  public void agregarPostulacion(Postulacion postulacion) {
    this.postulaciones.add(postulacion);
  }
  
  @Override
  public ArrayList<String> listarOfertasUsuario() {
    ArrayList<String> listaOfertas = new ArrayList<String>();
    for (Postulacion postulacion : postulaciones) {
      listaOfertas.add(postulacion.getNombreOfertaLaboral());
    }
    return listaOfertas;
  }
  
  @Override
  public Dtusuario obtenerDtusuario() {
    
    return this.obtenerDtpostulante();
  }
  /**
   * Obtener DTPostulacion.
   */
  
  public Dtpostulacion obtenerDtpostulacion(String nombreOferta) {
    Postulacion postulacionAoferta = null;
    for (Postulacion postulacion : postulaciones) {
      if (postulacion.getNombreOfertaLaboral() == nombreOferta) {
        postulacionAoferta = postulacion;
      }
    }
    return postulacionAoferta.obtenerDtpostulacion();
  }
  /**
   * Obtener DTOfertaLaboral.
   */
  
  public ArrayList<DtOfertaLaboral> obtenerDtofertas() {
    ArrayList<DtOfertaLaboral> listaResultado = new ArrayList<DtOfertaLaboral>();
    for (Postulacion postulacion : postulaciones) {
      listaResultado.add(postulacion.getOfertaLaboral().obtenerDtOfertaLaboral());
    }
    return listaResultado;
  }
  
  /**
   * Obtener DTPostulaciones .
   */
  
  public ArrayList<Dtpostulacion> obtenerDtpostulaciones() {
    ArrayList<Dtpostulacion> listaResultado = new ArrayList<Dtpostulacion>();
    for (Postulacion postulacion : postulaciones) {
      listaResultado.add(postulacion.obtenerDtpostulacion());
    }
    return listaResultado;
  }
  /**
   * Metodo para saber si esta postulado un postulante .
   */
  
  public Boolean estaPostulado(String nombreOferta) {
    Boolean resultado = false;
    for (Postulacion postulacion : postulaciones) {
      if (postulacion.getOfertaLaboral().getNombre() == nombreOferta) {
        resultado = true;
        break;
      }
    }
    return resultado;
    
  }
}
