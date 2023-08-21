package logica.handlers;

import java.util.HashMap;

import excepciones.KeywordNoExisteException;
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

	public static ManejadorSettings getinstance() {
        if (instancia == null)
            instancia = new ManejadorSettings();
        return instancia;
	}
	
	public ArrayList <String> listarTipoDePublicaciones() {
		ArrayList <String> listTipoPublicaciones = new ArrayList<String>();
		for (String key : colTipoPublicaciones.keySet() ) {
			listTipoPublicaciones.add(key);
		}
		return listTipoPublicaciones;		
	}
	
	
	public TipoPublicacion obtenerTipoPublicacion(String nombre) throws TipoPublicacionNoExiste {
		if(!colTipoPublicaciones.containsKey(nombre)) {
			throw new TipoPublicacionNoExiste("No existe Tipo Publicacion con nombre: " + nombre);
		}
        return ((TipoPublicacion) colTipoPublicaciones.get(nombre));
    }
	
	public Keyword obtenerKeywords(String nombre) throws KeywordNoExisteException {
		if(!colKeywords.containsKey(nombre)) {
			throw new KeywordNoExisteException("No existe keyword con nombre: " + nombre);
		}
        return ((Keyword) colKeywords.get(nombre));
    }
	
	public ArrayList<String> listarKeywords(){
		ArrayList<String> listKewords = new ArrayList<String>();
		for (String key : colKeywords.keySet()) {
			listKewords.add(key);
		}
		return listKewords;
	}
	
	
	
}
	



