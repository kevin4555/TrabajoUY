package logica.handlers;

import excepciones.KeywordNoExisteException;
import excepciones.KeywordYaExisteException;
import excepciones.TipoPublicacionNoExisteException;
import excepciones.TipoPublicacionYaExisteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import logica.classes.Keyword;
import logica.classes.TipoPublicacion;

/**
 * Clase Manejador Settings .
 */

public class ManejadorSettings {

  private static ManejadorSettings instancia = null;
  private Map<String, TipoPublicacion> colTipoPublicaciones;
  private Map<String, Keyword> colKeywords;

  private ManejadorSettings() {
    colTipoPublicaciones =
          new HashMap<String, TipoPublicacion>();
    colKeywords = new HashMap<String, Keyword>();
  }

  /**
   * Obtener instancia de Manejador Settings .
   */

  public static ManejadorSettings getInstance() {
    if (instancia == null) {
      instancia = new ManejadorSettings();
    }
    return instancia;
  }

  /**
   * Lista de tipos de publicaciones .
   */

  public List<String> listarTipoDePublicaciones() {
    List<String> listTipoPublicaciones =
          new ArrayList<String>();
    for (String key : colTipoPublicaciones.keySet()) {
      listTipoPublicaciones.add(key);
    }
    return listTipoPublicaciones;
  }

  /**
   * Obtener tipo de publicacion .
   */

  public TipoPublicacion obtenerTipoPublicacion(
        String nombre)
        throws TipoPublicacionNoExisteException {
    if (colTipoPublicaciones.containsKey(nombre)) {
      return colTipoPublicaciones.get(nombre);
    } else {
      throw new TipoPublicacionNoExisteException(
            "El tipo de publicación " + nombre
                  + " no existe");
    }
  }

  /**
   * Agregar tipo de publicacion .
   */

  public void addTipoPublicacion(TipoPublicacion tpoPublic)
        throws TipoPublicacionYaExisteException {
    if (!colTipoPublicaciones
          .containsKey(tpoPublic.getNombre())) {
      colTipoPublicaciones.put(tpoPublic.getNombre(),
            tpoPublic);
    } else {
      throw new TipoPublicacionYaExisteException(
            "El tipo publicacion " + tpoPublic.getNombre()
                  + " ya existe");
    }
  }

  /**
   * Agregar keyword .
   */

  public void addKeyword(Keyword keyword)
        throws KeywordYaExisteException {
    if (!colKeywords.containsKey(keyword.getNombre())) {
      colKeywords.put(keyword.getNombre(), keyword);
    } else {
      throw new KeywordYaExisteException("La keyword "
            + keyword.getNombre() + " ya existe");
    }
  }

  /**
   * Obtener keyword .
   */

  public Keyword obtenerKeyword(String nombre)
        throws KeywordNoExisteException {
    if (colKeywords.containsKey(nombre)) {
      return colKeywords.get(nombre);
    } else {
      throw new KeywordNoExisteException(
            "La keyword " + nombre + " no existe");
    }
  }

  /**
   * Obtener lista de keywords .
   */

  public List<String> listarKeywords() {
    List<String> listKewords = new ArrayList<String>();
    for (String key : colKeywords.keySet()) {
      listKewords.add(key);
    }
    return listKewords;
  }

  public void clean() {
    instancia = null;
  }

}
