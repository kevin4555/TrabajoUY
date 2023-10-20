package logica.classes;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
  private List<Postulacion> postulaciones;
  
  /**
   * Constructor.
   */
  
  public Postulante(String nickname, String nombre,
      String apellido, String email,
      LocalDate fechaNacimiento, String nacionalidad,
      BufferedImage imagen, String contrasenia) {
    super(nickname, nombre, apellido, email, imagen,
        contrasenia);
    setFechaNacimiento(fechaNacimiento);
    setNacionalidad(nacionalidad);
    this.postulaciones = new ArrayList<Postulacion>();
  }
  
  public LocalDate getFechaNacimiento() {
    return fechaNacimiento;
  }
  
  public void setFechaNacimiento(
      LocalDate fechaNacimiento) {
    this.fechaNacimiento = fechaNacimiento;
  }
  
  public String getNacionalidad() {
    return nacionalidad;
  }
  
  public void setNacionalidad(String nacionalidad) {
    this.nacionalidad = nacionalidad;
  }
  
  public List<Postulacion> getPostulaciones() {
    return postulaciones;
  }
  
  /**
   * Metodo obtenerDtpostulante.
   */
  
  public Dtpostulante obtenerDtpostulante()
      throws IOException {
    return new Dtpostulante(this.nickname, this.nombre,
        this.apellido, this.email, this.getImagen(),
        this.getContrasenia(), this.obtenerDtofertas(),
        this.fechaNacimiento, this.nacionalidad);
  }
  
  public void agregarPostulacion(Postulacion postulacion) {
    this.postulaciones.add(postulacion);
  }
  
  @Override
  public List<String> listarNombreOfertasUsuario() {
    List<String> listaOfertas = new ArrayList<String>();
    for (Postulacion postulacion : postulaciones) {
      listaOfertas
          .add(postulacion.getNombreOfertaLaboral());
    }
    return listaOfertas;
  }
  
  @Override
  public Dtusuario obtenerDtusuario() throws IOException {
    
    return this.obtenerDtpostulante();
  }
  
  /**
   * Obtener DTPostulacion.
   */
  
  public Dtpostulacion obtenerDtpostulacion(
      String nombreOferta) {
    for (Postulacion postulacion : postulaciones) {
      if (postulacion.getNombreOfertaLaboral()
          .equals(nombreOferta)) {
        return postulacion.obtenerDtpostulacion();
      }
    }
    return null;
  }
  
  /**
   * Obtener DTOfertaLaboral.
   */
  
  public List<DtOfertaLaboral> obtenerDtofertas()
      throws IOException {
    List<DtOfertaLaboral> listaResultado = new ArrayList<DtOfertaLaboral>();
    for (Postulacion postulacion : postulaciones) {
      listaResultado.add(postulacion.getOfertaLaboral()
          .obtenerDtOfertaLaboral());
    }
    return listaResultado;
  }
  
  /**
   * Obtener DTPostulaciones .
   */
  
  public List<Dtpostulacion> obtenerDtpostulaciones() {
    List<Dtpostulacion> listaResultado = new ArrayList<Dtpostulacion>();
    for (Postulacion postulacion : postulaciones) {
      listaResultado
          .add(postulacion.obtenerDtpostulacion());
    }
    return listaResultado;
  }
  
  /**
   * Metodo para saber si esta postulado un
   * postulante .
   */
  
  public Boolean estaPostulado(String nombreOferta) {
    
    for (Postulacion postulacion : postulaciones) {
      if (postulacion.getOfertaLaboral().getNombre()
          .equals(nombreOferta)) {
        return true;
      }
    }
    return false;
    
  }
}
