package logica;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

public class ManejadorSettings {
	
	private static ManejadorSettings instancia = null;
	private Map<String, TipoPublicacion> nomTipoPublicacion;
	private Map<String, Keyword> nomKeywords;
	
	private ManejadorSettings() {
		nomTipoPublicacion = new HashMap<String, TipoPublicacion>();
    }

	public static ManejadorSettings getinstance() {
        if (instancia == null)
            instancia = new ManejadorSettings();
        return instancia;
	}
	
	public ArrayList <String> listarTipoDePublicaciones() {
		ArrayList <String> nomTiposPublic = new ArrayList<String>();
		for (Map.Entry<String, TipoPublicacion> tposPublic: nomTipoPublicacion.entrySet()) {
			nomTiposPublic.add(tposPublic.getKey());
		}
		return nomTiposPublic;
	}
	
	
	public TipoPublicacion obtenerTipoPublicacion(String nombre) {
        return ((TipoPublicacion) nomTipoPublicacion.get(nombre));
    }
	
	public Keyword obtenerKeywords(String nombre) {
        return ((Keyword) nomKeywords.get(nombre));
    }
	
	
    
	
	
	
	
}
	



/*
 * 
 * 
 * for (Map.Entry<Integer, String> entry : datos.entrySet()) {
    System.out.println("clave=" + entry.getKey() + ", valor=" + entry.getValue());
}
 * 


+obtenerKeywords(nomKey:string):Keyword
 */
