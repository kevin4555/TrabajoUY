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

	public void altaOfertaLaboral(String nombre, String descrip, Date horaInicio, Date horaFin, double remuneracion, String ciudad, String departamento, Date fechaAlta, ArrayList<String> keywords, String nomTpoPublic, String nicknameEmpresa) {
		ControladorUsuario cu = new ControladorUsuario();
		Empresa emp = cu.obtenerEmpresa(nicknameEmpresa);
		
		ManejadorSettings ms = ManejadorSettings.getinstance();
		TipoPublicacion tp = ms.obtenerTipoPublicacion(nomTpoPublic);
		if (tp != null) {
			OfertaLaboral ol = new OfertaLaboral(nombre, descrip, ciudad, departamento, horaInicio, horaFin, remuneracion, fechaAlta);
			for (String kw: keywords) {
				Keyword key = ms.obtenerKeywords(kw);
				if (key != null) {
					ol.getKw().put(kw, key);
				}	
			}
			ol.setTp(tp);
			emp.agregarOferta(ol);
			//paso 8
		}
		
		
	}

}
