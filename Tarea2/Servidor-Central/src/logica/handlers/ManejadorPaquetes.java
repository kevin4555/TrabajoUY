package logica.handlers;

import excepciones.PaquetePublicacionNoExisteException;
import excepciones.PaquetePublicacionYaExisteException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import logica.classes.PaquetePublicacion;
import logica.datatypes.DtPaquetePublicacion;

/**
 * Clase Manejador Paquetes .
 */

public class ManejadorPaquetes {
  private static ManejadorPaquetes instancia = null;
  private Map<String, PaquetePublicacion> colPaquetes;
  
  private ManejadorPaquetes() {
    this.colPaquetes = new HashMap<String, PaquetePublicacion>();
  }
  
  /**
   * Obtener instancia .
   */
  
  public static ManejadorPaquetes getInstance() {
    if (instancia == null) {
      instancia = new ManejadorPaquetes();
    }
    return instancia;
  }
  
  /**
   * Metodo obtener paquete .
   */
  
  public PaquetePublicacion obtenerPaquete(
      String nomPaquete)
      throws PaquetePublicacionNoExisteException {
    if (colPaquetes.containsKey(nomPaquete)) {
      return colPaquetes.get(nomPaquete);
    } else {
      throw new PaquetePublicacionNoExisteException(
          "El paquete " + nomPaquete + " no existe");
    }
  }
  
  /**
   * Obtener lista de paquetes .
   */
  
  public List<String> listarPaquetes() {
    List<String> listaPaquetes = new ArrayList<String>();
    for (String key : colPaquetes.keySet()) {
      listaPaquetes.add(key);
    }
    return listaPaquetes;
  }
  
  /**
   * Agregar paquete .
   */
  
  public void agregarPaquete(PaquetePublicacion paquete)
      throws PaquetePublicacionYaExisteException {
    if (!colPaquetes.containsKey(paquete.getNombre())) {
      colPaquetes.put(paquete.getNombre(), paquete);
    } else {
      throw new PaquetePublicacionYaExisteException(
          "El paquete" + paquete.getNombre() + "ya existe");
    }
  }
  
  /**
   * Eliminar paquete .
   */
  
  public void eliminarPaquete(String nomPaquete)
      throws PaquetePublicacionNoExisteException {
    if (colPaquetes.get(nomPaquete) != null) {
      colPaquetes.remove(nomPaquete);
    } else {
      throw new PaquetePublicacionNoExisteException(
          "El paquete " + nomPaquete + "no existe");
    }
  }
  
  /**
   * Lista paquetes no comprados .
   */
  
  public List<String> listarPaquetesNoComprados() {
    List<String> listaResultado = new ArrayList<String>();
    for (PaquetePublicacion paquete : colPaquetes
        .values()) {
      if (!paquete.getEstaComprado()) {
        listaResultado.add(paquete.getNombre());
      }
    }
    return listaResultado;
  }
  
  public void clean() {
    instancia = null;
  }
  
  /**
   * Lista de DTPaquetesPublicacion .
   */
  
  public List<DtPaquetePublicacion> listarDtpaquetes()
      throws IOException {
    List<DtPaquetePublicacion> listaResultado = new ArrayList<DtPaquetePublicacion>();
    for (PaquetePublicacion paquete : colPaquetes
        .values()) {
      listaResultado.add(paquete.obtenerDtPaquete());
    }
    return listaResultado;
  }
}
