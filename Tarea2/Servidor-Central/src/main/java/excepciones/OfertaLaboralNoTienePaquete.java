package main.java.excepciones;

/**
 * Excepción OfertaLaboralNoTienePaquete.
 */

@SuppressWarnings("serial")
public class OfertaLaboralNoTienePaquete extends Exception {

  public OfertaLaboralNoTienePaquete(String message) {
    super(message);
  }
}
