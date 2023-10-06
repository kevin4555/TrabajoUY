package logica.handlers;

import excepciones.DtOfertaNoExisteException;
import excepciones.OfertaLaboralNoExisteException;
import excepciones.OfertaLaboralYaExisteException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import logica.classes.OfertaLaboral;
import logica.datatypes.DtOfertaLaboral;

/**
 * Clase Manejador de ofertas.
 */

public class ManejadorOfertas {
  
  private static ManejadorOfertas instancia = null;
  
  // private HashMap<String, TipoPublicacion>
  // coleccionTipoPublicacion;
  private Map<String, OfertaLaboral> coleccionOfertaLaboral;
  
  private ManejadorOfertas() {
    // coleccionTipoPublicacion = new HashMap<String,
    // TipoPublicacion>();
    coleccionOfertaLaboral = new HashMap<String, OfertaLaboral>();
  }
  
  /**
   * Obtener instancia Manejador de ofertas.
   */
  
  public static ManejadorOfertas getInstance() {
    if (instancia == null) {
      instancia = new ManejadorOfertas();
    }
    return instancia;
  }
  
  /**
   * Metodo agregar oferta laboral .
   */
  
  public void agregarOferta(OfertaLaboral ofertaLaboral)
      throws OfertaLaboralYaExisteException {
    if (!coleccionOfertaLaboral.containsKey(ofertaLaboral.getNombre())) {
      coleccionOfertaLaboral.put(ofertaLaboral.getNombre(), ofertaLaboral);
    } else {
      throw new OfertaLaboralYaExisteException(
          "La oferta laboral que desea ingresar ya existe");
    }
  }
  
  /**
   * Obtener DTOfertaLaboral .
   */
  
  public DtOfertaLaboral obtenerDtofertaLaboral(String nombreOferta)
      throws DtOfertaNoExisteException, IOException {
    if (!coleccionOfertaLaboral.containsKey(nombreOferta)) {
      throw new DtOfertaNoExisteException("No existe la oferta solicitada");
    } else {
      return coleccionOfertaLaboral.get(nombreOferta).obtenerDtOfertaLaboral();
    }
  }
  
  /**
   * Obtener oferta laboral .
   */
  
  public OfertaLaboral obtenerOfertaLaboral(String nomOferta)
      throws OfertaLaboralNoExisteException {
    if (coleccionOfertaLaboral.containsKey(nomOferta)) {
      return coleccionOfertaLaboral.get(nomOferta);
    } else {
      throw new OfertaLaboralNoExisteException("No existe la oferta solicitada");
    }
  }
  
  /**
   * Obtener lista de ofertas laborales .
   */
  
  public List<String> listarOfertasLaborales() {
    List<String> resultado = new ArrayList<String>();
    for (Map.Entry<String, OfertaLaboral> entry : this.coleccionOfertaLaboral.entrySet()) {
      resultado.add(entry.getKey());
    }
    return resultado;
  }
  
  public void clean() {
    instancia = null;
  }
  
  /**
   * Obtener DTOfertasLaboralesConfirmadas .
   */
  
  public List<DtOfertaLaboral> obtenerDtofertasConfirmadas() throws IOException {
    List<DtOfertaLaboral> listaResultado = new ArrayList<DtOfertaLaboral>();
    for (OfertaLaboral oferta : coleccionOfertaLaboral.values()) {
      listaResultado.add(oferta.obtenerDtOfertaLaboral());
    }
    return listaResultado;
  }
  
  /**
   * Obtener obtenerDTOfertasPorKeyword .
   */
  
  public List<DtOfertaLaboral> obtenerDtofertasPorKeyword(String keyword) throws IOException {
    List<DtOfertaLaboral> listaResultado = new ArrayList<DtOfertaLaboral>();
    for (OfertaLaboral oferta : coleccionOfertaLaboral.values()) {
      if (oferta.tieneKeyword(keyword)) {
        listaResultado.add(oferta.obtenerDtOfertaLaboral());
      }
    }
    return listaResultado;
  }
}
