package logica;

import java.sql.Date;
import excepciones.TipoPublicacionNoExisteException;


import java.util.ArrayList;


public class ControladorOferta implements IControladorOferta {
	


	public ArrayList<String> listarTipoDePublicaciones() throws TipoPublicacionNoExisteException {
		ManejadorSettings ms = ManejadorSettings.getinstance();
		ArrayList<String> nomTposPublic = ms.listarTipoDePublicaciones();
		if (nomTposPublic != null) {
			return nomTposPublic;
		}
		else {
			throw new TipoPublicacionNoExisteException("No existen tipos de publicaciones registrados");
		}
	}

	public void altaOfertaLaboral(String nombre, String descrip, Date horaInicio, Date horaFin, double remuneracion,
			String ciudad, String departamento, Date fechaAlta, ArrayList<String> keywords) {
		
		
	}

}
