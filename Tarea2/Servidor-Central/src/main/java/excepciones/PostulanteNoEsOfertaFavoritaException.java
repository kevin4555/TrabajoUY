package main.java.excepciones;

/**
 * Excepcion PostulanteNoEsOfertaFavoritaException.
 */

@SuppressWarnings("serial")
public class PostulanteNoEsOfertaFavoritaException
      extends Exception {
  public PostulanteNoEsOfertaFavoritaException(
        String message) {
    super(message);
  }
}
