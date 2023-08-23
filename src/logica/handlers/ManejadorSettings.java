package logica.handlers;

import java.util.HashMap;


import logica.classes.Keyword;
import logica.classes.TipoPublicacion;

import java.util.ArrayList;

public class ManejadorSettings {

	private static ManejadorSettings instancia = null;
	private HashMap<String, TipoPublicacion> colTipoPublicaciones;
	private HashMap<String, Keyword> colKeywords;

	private ManejadorSettings() {
		colTipoPublicaciones = new HashMap<String, TipoPublicacion>();
		colKeywords = new HashMap<String, Keyword>();
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

	public TipoPublicacion obtenerTipoPublicacion(String nombre)  {
		if (colTipoPublicaciones.get(nombre) != null) {
			return colTipoPublicaciones.get(nombre);
		} else {
			return null;
		}
	}
	
	public void addTipoPublicacion(TipoPublicacion tpoPublic) {
		colTipoPublicaciones.put(tpoPublic.getNombre(), tpoPublic);
	}

	public void addKeyword(Keyword keyword) { //throws KeywordYaExisteException {
		colKeywords.put(keyword.getNombre(), keyword);
		/*if (colKeywords.get(nombre) == null) {
			Keyword keyword = new Keyword(nombre);
			colKeywords.put(nombre, keyword);
		} else {
			throw new KeywordYaExisteException("Keyword " + nombre + " ya existe");
		}*/
	}

	public Keyword obtenerKeyword(String nombre) {
			return colKeywords.get(nombre);
	}

	public ArrayList<String> listarKeywords() {
		ArrayList<String> listKewords = new ArrayList<String>();
		for (String key : colKeywords.keySet()) {
			listKewords.add(key);
		}
		return listKewords;
	}
}
