package main.java.excepciones;

/**
 * Excepcion UsuarioYaEstaSeguidoException.
 */

@SuppressWarnings("serial")
public class UsuarioYaEstaSeguidoException
      extends Exception {
  public UsuarioYaEstaSeguidoException(String message) {
    super(message);
  }
}
