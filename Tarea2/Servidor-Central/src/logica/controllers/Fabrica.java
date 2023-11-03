package logica.controllers;

import logica.interfaces.IcontroladorOferta;
import logica.interfaces.IcontroladorUsuario;

/**
 * Clase Fabrica.
 */

public class Fabrica {
  private static Fabrica instancia = null;

  private Fabrica() {
  }

  /**
   * Constructor.
   */

  public static Fabrica getInstance() {
    if (instancia == null) {
      instancia = new Fabrica();
    }
    return instancia;
  }

  public IcontroladorUsuario obtenerControladorUsuario() {
    return new ControladorUsuario();
  }

  public IcontroladorOferta obtenerControladorOferta() {
    return new ControladorOferta();
  }
}
