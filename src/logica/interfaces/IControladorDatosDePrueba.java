package logica.interfaces;

import java.text.ParseException;

import excepciones.KeywordYaExisteException;
import excepciones.TipoPublicacionYaExisteException;
import excepciones.UsuarioYaExisteException;

public interface IControladorDatosDePrueba {

	public abstract void cargarDatosDePrueba() throws ParseException, UsuarioYaExisteException, NumberFormatException, TipoPublicacionYaExisteException, KeywordYaExisteException, Exception;
}
