package logica.handlers;

import java.util.HashMap;

import excepciones.KeywordNoExisteException;
import excepciones.KeywordYaExisteException;
import excepciones.TipoPublicacionNoExiste;
import logica.classes.Keyword;
import logica.classes.TipoPublicacion;

import java.util.ArrayList;

public class ManejadorSettings {

	private static ManejadorSettings instancia = null;
	private HashMap<String, TipoPublicacion> colTipoPublicaciones;
	private HashMap<String, Keyword> colKeywords;

	private ManejadorSettings() {
		colTipoPublicaciones = new HashMap<String, TipoPublicacion>();
	}

	public static ManejadorSettings getInstance() {
		if (instancia == null)
			instancia = new ManejadorSettings();
		return instancia;
	}

	public ArrayList<String> listarTipoDePublicaciones() {
		ArrayList<String> listTipoPublicaciones = new ArrayList<String>();
		for (String key : colTipoPublicaciones.keySet()) {
			listTipoPublicaciones.add(key);
		}
		return listTipoPublicaciones;
	}

	public TipoPublicacion obtenerTipoPublicacion(String nombre) throws TipoPublicacionNoExiste {
		if (colTipoPublicaciones.get(nombre) != null) {
			return colTipoPublicaciones.get(nombre);
		} else {
			throw new TipoPublicacionNoExiste("TipoPublicacion " + nombre + " no existe");
		}
	}

	public void addKeyword(String nombre) throws KeywordYaExisteException {
		if (colKeywords.get(nombre) == null) {
			Keyword keyword = new Keyword(nombre);
			colKeywords.put(nombre, keyword);
		} else {
			throw new KeywordYaExisteException("Keyword " + nombre + " ya existe");
		}
	}

	public Keyword obtenerKeyword(String nombre) throws KeywordNoExisteException {
		if (colKeywords.get(nombre) != null) {
			return colKeywords.get(nombre);
		} else {
			throw new KeywordNoExisteException("Keyword " + nombre + " no existe");
		}
	}

	public ArrayList<String> listarKeywords() {
		ArrayList<String> listKewords = new ArrayList<String>();
		for (String key : colKeywords.keySet()) {
			listKewords.add(key);
		}
		return listKewords;
	}
}
