package logica.interfaces;

import java.text.ParseException;

import excepciones.UsuarioYaExisteException;

public interface IControladorDatosDePrueba {

	public abstract void cargarDatosDePrueba() throws ParseException, UsuarioYaExisteException;
}
