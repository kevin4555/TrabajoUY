package main.java.excepciones;

/**
 * Excepcion UsuarioNoEstaSeguidoException.
 */

@SuppressWarnings("serial")
public class UsuarioNoEstaSeguidoException
      extends Exception {
  public UsuarioNoEstaSeguidoException(String message) {
    super(message);
  }
}
