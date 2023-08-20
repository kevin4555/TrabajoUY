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
    }

	public static ManejadorSettings getInstance() {
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
	
	
	public TipoPublicacion obtenerTipoPublicacion(String nombre) {
        return ((TipoPublicacion) colTipoPublicaciones.get(nombre));
    }
	
	public Keyword obtenerKeywords(String nombre) {
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
	



