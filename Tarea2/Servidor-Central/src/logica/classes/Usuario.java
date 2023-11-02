package logica.classes;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import logica.datatypes.DtUsuario;

/**
 * Clase Usuario.
 */
public abstract class Usuario {
  protected String nickname;
  protected String nombre;
  protected String apellido;
  protected String email;
  private BufferedImage imagen;
  private String contrasenia;
  private Set<String> seguidos;
  private Set<String> seguidores;

  /**
   * Constuctor clase Usuario.
   */
  public Usuario(
        String nickname,
          String nombre,
          String apellido,
          String email,
          BufferedImage imagen,
          String contrasenia) {
    super();
    this.nickname = nickname;
    this.nombre = nombre;
    this.apellido = apellido;
    this.email = email;
    this.contrasenia = contrasenia;
    this.imagen = imagen;
    this.seguidos = new HashSet<String>();
    this.seguidores = new HashSet<String>();
  }

  public String getNickname() {
    return this.nickname;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getEmail() {
    return email;
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public BufferedImage getImagen() {
    return imagen;
  }

  public void setImagen(BufferedImage imagen) {
    this.imagen = imagen;
  }

  public String getContrasenia() {
    return contrasenia;
  }

  public void setContrasenia(String contrasenia) {
    this.contrasenia = contrasenia;
  }

  public abstract List<String> listarNombreOfertasUsuario();

  public abstract DtUsuario obtenerDtusuario()
        throws IOException;

  public void seguir(String nicknameSeguido) {
    this.seguidos.add(nicknameSeguido);
  }

  public void agregarSeguidor(String nicknameSeguidor) {
    this.seguidores.add(nicknameSeguidor);
  }

  public Boolean estaSeguidoPor(String nicknameSeguidor) {
    return this.seguidores.contains(nicknameSeguidor);
  }

  public void dejarDeSeguir(String nicknameSeguido) {
    this.seguidos.remove(nicknameSeguido);
  }

  public void removerSeguidor(String nicknameSeguidor) {
    this.seguidores.remove(nicknameSeguidor);
  }

  public Set<String> getSeguidos() {
    return seguidos;
  }

  public Set<String> getSeguidores() {
    return seguidores;
  }

}
