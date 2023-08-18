package logica;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ManejadorSettings {
	
	private static ManejadorSettings instancia = null;
	private Map<String, TipoPublicacion> nomTipoPublicacion;
	
	private ManejadorSettings() {
		nomTipoPublicacion = new HashMap<String, TipoPublicacion>();
    }

	public static ManejadorSettings getinstance() {
        if (instancia == null)
            instancia = new ManejadorSettings();
        return instancia;
	}
	
	
    
	
	
	
	
}
	



/*
 * 
 * 
 * for (Map.Entry<Integer, String> entry : datos.entrySet()) {
    System.out.println("clave=" + entry.getKey() + ", valor=" + entry.getValue());
}
 * 
 * +getInstance():ManejadorSettings
+listarTipoDePublicaciones():Set(string)
+obtenerTipoPublicacion(nomTipoPublicacion:string):TipoPublicacion
+obtenerKeywords(nomKey:string):Keyword
 */
