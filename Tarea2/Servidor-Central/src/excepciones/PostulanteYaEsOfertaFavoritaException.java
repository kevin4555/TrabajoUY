package excepciones;

/**
 * Excepcion PostulanteYaEsOfertaFavoritaException.
 */

@SuppressWarnings("serial")
public class PostulanteYaEsOfertaFavoritaException
      extends Exception {
  public PostulanteYaEsOfertaFavoritaException(
        String message) {
    super(message);
  }
}
