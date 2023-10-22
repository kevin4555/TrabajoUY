package excepciones;

@SuppressWarnings("serial")
public class UsuarioNoEstaSeguidoException extends Exception {
  public UsuarioNoEstaSeguidoException(String message) {
    super(message);
  }
}
