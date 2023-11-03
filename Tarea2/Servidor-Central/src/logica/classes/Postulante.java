package logica.classes;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import logica.datatypes.DtOfertaLaboral;
import logica.datatypes.DtPostulacion;
import logica.datatypes.DtPostulante;
import logica.datatypes.DtUsuario;

/**
 * Clase Postulante .
 */

public class Postulante extends Usuario {
  private LocalDate fechaNacimiento;
  private String nacionalidad;
  private List<Postulacion> postulaciones;
  private Set<String> ofertasFavoritas;

  

/**
   * Constructor.
   */

  public Postulante(
        String nickname,
          String nombre,
          String apellido,
          String email,
          LocalDate fechaNacimiento,
          String nacionalidad,
          BufferedImage imagen,
          String contrasenia) {
    super(nickname, nombre, apellido, email, imagen,
          contrasenia);
    setFechaNacimiento(fechaNacimiento);
    setNacionalidad(nacionalidad);
    this.postulaciones = new ArrayList<Postulacion>();
    this.ofertasFavoritas = new HashSet<String>();
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

  public DtPostulante obtenerDtpostulante()
        throws IOException {
    List<String> seguidos = new ArrayList<String>();
    for (String nickname : this.getSeguidos()) {
      seguidos.add(nickname);
    }
    List<String> seguidores = new ArrayList<String>();
    for (String nickname : this.getSeguidores()) {
      seguidores.add(nickname);
    }
    List<String> ofetasFavoritas = new ArrayList<String>();
    for (String oferta : this.getOfertasFavoritas()) {
      ofetasFavoritas.add(oferta);
    }
    return new DtPostulante(this.nickname, this.nombre,
          this.apellido, this.email, this.getImagen(),
          this.getContrasenia(), this.obtenerDtofertas(),
          this.fechaNacimiento, this.nacionalidad, seguidos,
          seguidores,ofetasFavoritas);
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
  public DtUsuario obtenerDtusuario() throws IOException {

    return this.obtenerDtpostulante();
  }

  /**
   * Obtener DTPostulacion.
   */

  public DtPostulacion obtenerDtpostulacion(
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
    List<DtOfertaLaboral> listaResultado =
          new ArrayList<DtOfertaLaboral>();
    for (Postulacion postulacion : postulaciones) {
      listaResultado.add(postulacion.getOfertaLaboral()
            .obtenerDtOfertaLaboral());
    }
    return listaResultado;
  }

  /**
   * Obtener DTPostulaciones .
   */

  public List<DtPostulacion> obtenerDtpostulaciones() {
    List<DtPostulacion> listaResultado =
          new ArrayList<DtPostulacion>();
    for (Postulacion postulacion : postulaciones) {
      listaResultado
            .add(postulacion.obtenerDtpostulacion());
    }
    return listaResultado;
  }

  /**
   * Metodo para saber si esta postulado un postulante .
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

  public void agregarOfertaFavorita(String nombreOferta) {
    this.ofertasFavoritas.add(nombreOferta);
  }

  public void removerOfertaFavorita(String nombreOferta) {
    this.ofertasFavoritas.remove(nombreOferta);
  }

  public Boolean esOfertaFavorita(String nombreOferta) {
    return this.ofertasFavoritas.contains(nombreOferta);
  }
  public Set<String> getOfertasFavoritas() {
		return ofertasFavoritas;
	}
}
