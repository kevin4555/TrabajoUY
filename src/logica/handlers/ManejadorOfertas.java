package logica.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import excepciones.ColeccionTipoPublicacionEsVaciaException;
import excepciones.OfertaLaboralNoExisteException;
import excepciones.OfertaLaboralYaExisteException;
import logica.classes.OfertaLaboral;
import logica.classes.TipoPublicacion;

public class ManejadorOfertas {

	private static ManejadorOfertas instancia = null;

	private HashMap<String, TipoPublicacion> coleccionTipoPublicacion;

	private HashMap<String, OfertaLaboral> coleccionOfertaLaboral;

	public static ManejadorOfertas getInstance() {
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
		if (coleccionOfertaLaboral.get(ofertaLaboral.getNombre()) == null) {
			coleccionOfertaLaboral.put(ofertaLaboral.getNombre(), ofertaLaboral);
		} else {
			throw new OfertaLaboralYaExisteException("La oferta laboral que desea ingresar ya existe");
		}
	}
	/*
	public DTOferta obtenerDTOfertaLaboral(String nombreOferta) throws DtOfertaNoExisteException {
		DTOferta resultado = coleccionOfertaLaboral.get(nombreOferta).getDT
		if (resultado == null) {
			throw new DtOfertaNoExisteException("No existe la oferta solicitada");
		} else {
			return resultado;
		}
	}*/

	public OfertaLaboral obtenerOfertaLaboral(String nomOferta) throws OfertaLaboralNoExisteException {
		OfertaLaboral resultado = coleccionOfertaLaboral.get(nomOferta);
		if (resultado != null) {
			return resultado;
		} else {
			throw new OfertaLaboralNoExisteException("No existe la oferta solicitada");
		}
	}
}
