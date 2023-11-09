package main.java.excepciones;

/**
 * Excepcion OfertaLaboralNoSePuedeFinalizar.
 */

@SuppressWarnings("serial")
public class OfertaLaboralNoSePuedeFinalizar
      extends Exception {
  public OfertaLaboralNoSePuedeFinalizar(String message) {
    super(message);
  }
}
