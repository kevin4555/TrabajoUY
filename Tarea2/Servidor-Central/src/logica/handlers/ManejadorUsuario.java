package logica.handlers;

import excepciones.UsuarioEmailRepetidoException;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioYaExisteException;
import java.util.ArrayList;
import java.util.HashMap;
import logica.classes.Empresa;
import logica.classes.Postulante;
import logica.classes.Usuario;
import logica.datatypes.Dtusuario;

/**
 * Clase Manejador usuario .
 */

public class ManejadorUsuario {
  private static ManejadorUsuario instancia = null;
  private HashMap<String, Usuario> colUsuarios;
  private HashMap<String, Postulante> colPostulantes;
  private HashMap<String, Empresa> colEmpresas;
  private HashMap<String, Usuario> usuariosEmail;
  
  private ManejadorUsuario() {
    colUsuarios = new HashMap<String, Usuario>();
    colPostulantes = new HashMap<String, Postulante>();
    colEmpresas = new HashMap<String, Empresa>();
    usuariosEmail = new HashMap<String, Usuario>();
  }
  
  /**
   * Obtener instancia Manejador usuario .
   */
  
  public static ManejadorUsuario getInstance() {
    if (instancia == null) {
      instancia = new ManejadorUsuario();
    }
    return instancia;
  }
  
  /**
   * Obtener lista de empresas .
   */
  
  public ArrayList<String> listarEmpresas() {
    ArrayList<String> listEmpresa = new ArrayList<String>();
    for (String empr : colEmpresas.keySet()) {
      listEmpresa.add(empr);
    }
    return listEmpresa;
  }
  
  /**
   * Obtener empresa .
   */
  
  public Empresa obtenerEmpresa(String nickEmpresa) throws UsuarioNoExisteException {
    if (colEmpresas.containsKey(nickEmpresa)) {
      return colEmpresas.get(nickEmpresa);
    } else {
      throw new UsuarioNoExisteException("Empresa " + nickEmpresa + " no existe");
    }
  }
  
  /**
   * Agregar empresa .
   */
  
  public void agregarEmpresa(Empresa empresa)
      throws UsuarioYaExisteException, UsuarioEmailRepetidoException {
    if (!colUsuarios.containsKey(empresa.getNickname())
        && !usuariosEmail.containsKey(empresa.getEmail())
        && !usuariosEmail.containsKey(empresa.getNickname())) {
      colEmpresas.put(empresa.getNickname(), empresa);
      colUsuarios.put(empresa.getNickname(), empresa);
      usuariosEmail.put(empresa.getEmail(), empresa);
    } else if (colUsuarios.containsKey(empresa.getNickname())
        || usuariosEmail.containsKey(empresa.getNickname())) {
      throw new UsuarioYaExisteException("Empresa " + empresa.getNickname() + " ya existe");
    } else if (usuariosEmail.containsKey(empresa.getEmail())) {
      throw new UsuarioEmailRepetidoException(
          "El email: " + empresa.getEmail() + " ya existe");
    }
  }
  
  /**
   * Obtener postulante .
   */
  
  public Postulante obtenerPostulante(String nickPostulante) throws UsuarioNoExisteException {
    if (colPostulantes.containsKey(nickPostulante)) {
      return colPostulantes.get(nickPostulante);
    } else {
      throw new UsuarioNoExisteException("Postulante " + nickPostulante + " no existe");
    }
  }
  
  /**
   * Agregar postulante .
   */
  
  public void agregarPostulante(Postulante postulante)
      throws UsuarioYaExisteException, UsuarioEmailRepetidoException {
    if (!colUsuarios.containsKey(postulante.getNickname())
        && !usuariosEmail.containsKey(postulante.getEmail())
        && !usuariosEmail.containsKey(postulante.getNickname())) {
      colPostulantes.put(postulante.getNickname(), postulante);
      colUsuarios.put(postulante.getNickname(), postulante);
      usuariosEmail.put(postulante.getEmail(), postulante);
    } else if (colUsuarios.containsKey(postulante.getNickname())
        || usuariosEmail.containsKey(postulante.getNickname())) {
      throw new UsuarioYaExisteException(
          "Postulante " + postulante.getNickname() + " ya existe");
    } else if (usuariosEmail.containsKey(postulante.getEmail())) {
      throw new UsuarioEmailRepetidoException(
          "El email: " + postulante.getEmail() + " ya existe");
    }
  }
  
  /**
   * Lista de postulantes .
   */
  
  public ArrayList<String> listarPostulantes() {
    ArrayList<String> postulantes = new ArrayList<String>();
    for (String key : colPostulantes.keySet()) {
      postulantes.add(key);
    }
    return postulantes;
  }
  
  /**
   * Lista de usuarios .
   */
  
  public ArrayList<String> listarUsuarios() {
    ArrayList<String> listaUsuarios = new ArrayList<String>();
    for (String nickUsuario : colUsuarios.keySet()) {
      listaUsuarios.add(nickUsuario);
    }
    return listaUsuarios;
  }
  
  /**
   * Obtener usuario .
   */
  
  public Usuario obtenerUsuario(String clave) throws UsuarioNoExisteException {
    if (colUsuarios.containsKey(clave)) {
      return colUsuarios.get(clave);
    } else if (usuariosEmail.containsKey(clave)) {
      return usuariosEmail.get(clave);
    } else {
      throw new UsuarioNoExisteException("Usuario: " + clave + " no existe");
    }
  }
  
  public void clean() {
    instancia = null;
  }
  
  /**
   * Obtener lista de DTUsuario .
   */
  
  public ArrayList<Dtusuario> obtenerDtusuarios() {
    ArrayList<Dtusuario> listaResultado = new ArrayList<Dtusuario>();
    for (Usuario usuario : colUsuarios.values()) {
      if (usuario instanceof Postulante) {
        Postulante postulante = (Postulante) usuario;
        listaResultado.add(postulante.obtenerDtpostulante());
      }
      if (usuario instanceof Empresa) {
        Empresa empresa = (Empresa) usuario;
        listaResultado.add(empresa.obtenerDtempresa());
      }
    }
    return listaResultado;
  }
}
