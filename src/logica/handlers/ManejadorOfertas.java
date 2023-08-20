package logica.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import excepciones.ColeccionTipoPublicacionEsVaciaException;
import excepciones.DtOfertaNoExisteException;
import excepciones.OfertaLaboralNoExisteException;
import excepciones.OfertaLaboralYaExisteException;
import logica.classes.OfertaLaboral;
import logica.classes.TipoPublicacion;

public class ManejadorOfertas {

	private static ManejadorOfertas instancia = null;

	private HashMap<String, TipoPublicacion> coleccionTipoPublicacion;

	private HashMap<String, OfertaLaboral> coleccionOfertaLaboral;

	public static ManejadorOfertas getinstance() {
		if (instancia == null) {
			instancia = new ManejadorOfertas();
		}
		return instancia;
	}

	public ArrayList<String> listarTiposDePublicaciones() throws ColeccionTipoPublicacionEsVaciaException {
		if (!coleccionTipoPublicacion.isEmpty()) {
			ArrayList<String> resultado = new ArrayList<String>();
			for (Map.Entry<String, TipoPublicacion> entry : this.coleccionTipoPublicacion.entrySet()) {
				resultado.add(entry.getKey());
			}
			return resultado;
		} else {
			throw new ColeccionTipoPublicacionEsVaciaException("El listado de tipos de publicaciones es vacio");
		}
	}

	public void agregarOferta(OfertaLaboral ofertaLaboral) throws OfertaLaboralYaExisteException {
		if (!coleccionOfertaLaboral.containsKey(ofertaLaboral.getNombre())) {
			coleccionOfertaLaboral.put(ofertaLaboral.getNombre(), ofertaLaboral);
		} else {
			throw new OfertaLaboralYaExisteException("La oferta laboral que desea ingresar ya existe");
		}
	}

	/*public DTOferta obtenerDTOfertaLaboral(String nombreOferta) throws DtOfertaNoExisteException {
		OfertaLaboral encontrado = coleccionOfertaLaboral.get(nombreOferta);
		
		if (!coleccionOfertaLaboral.containsKey(nombreOferta)) {
			throw new DtOfertaNoExisteException("No existe la oferta solicitada");
		} else {
			return coleccionOfertaLaboral.get(nombreOferta).getDtOferta();
		}
	}*/

	public OfertaLaboral obtenerOfertaLaboral(String nomOferta) throws OfertaLaboralNoExisteException {
		
		if (coleccionOfertaLaboral.containsKey(nomOferta)) {
			return coleccionOfertaLaboral.get(nomOferta);
		} else {
			throw new OfertaLaboralNoExisteException("No existe la oferta solicitada");
		}
	}

}