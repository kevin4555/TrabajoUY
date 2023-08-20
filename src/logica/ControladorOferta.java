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
			String ciudad, String departamento, Date fechaAlta, ArrayList<String> keywords, String nomTpoPublic, String nicknameEmpresa) throws TipoPublicacionNoExisteException  {
		// chequear si existe ofertalaboral
		ControladorUsuario cu = new ControladorUsuario();
		Empresa emp = cu.obtenerEmpresa(nicknameEmpresa);
		if (emp == null) {
			//throw new TipoPublicacionNoExisteException("La empresa " + nicknameEmpresa + " no existe");
		}
		ManejadorSettings ms = ManejadorSettings.getinstance();
		TipoPublicacion tp = ms.obtenerTipoPublicacion(nomTpoPublic);
		if (tp == null) 
			throw new TipoPublicacionNoExisteException("El tipo de publicacion " + nomTpoPublic + " no existe");
		OfertaLaboral ol = new OfertaLaboral(nombre, descrip, ciudad, departamento, horaInicio, horaFin, remuneracion, fechaAlta);
		for (String kw: keywords) {
			Keyword key = ms.obtenerKeywords(kw);
			if (key == null)
				//throw new TipoPublicacionNoExisteException("La keyword " + kw + " no existe");
			ol.getKw().put(kw, key);
		}
		ol.setTp(tp);
		emp.agregarOferta(ol);
		/*ManejadorOfertas mo = ManejadorOfertas.getinstance();
		mo.agregarOferta(ol);*/
	}

}
