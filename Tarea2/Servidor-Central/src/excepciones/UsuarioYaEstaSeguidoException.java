package excepciones;

@SuppressWarnings("serial")
public class UsuarioYaEstaSeguidoException extends Exception {
  public UsuarioYaEstaSeguidoException(String message) {
    super(message);
  }
}