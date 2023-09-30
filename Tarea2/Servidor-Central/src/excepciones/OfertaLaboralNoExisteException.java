package excepciones;

/**
 * Excepción OfertaLaboralNoExisteException.
 */

@SuppressWarnings("serial")
public class OfertaLaboralNoExisteException extends Exception {
  
  public OfertaLaboralNoExisteException(String string) {
    super(string);
  }
  
}
