package logica;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import excepciones.ColeccionTipoPublicacionEsVaciaException;
import excepciones.DtOfertaNoExisteException;
import excepciones.OfertaLaboralNoExisteException;
import excepciones.OfertaLaboralYaExisteException;

public class ManejadorOfertas {

	private static ManejadorOfertas instancia = null;

	private HashMap<String, TipoPublicacion> coleccionTipoPublicacion;

	//private HashMap<String, DTOfertaLaboral> coleccionDTOfertaLaboral;

	private HashMap<String, OfertaLaboral> coleccionOfertaLaboral;

	public static ManejadorOfertas getinstance() {
		if (instancia == null)
			instancia = new ManejadorOfertas();
		return instancia;
	}

	public Set<String> listarTiposDePublicaciones() throws ColeccionTipoPublicacionEsVaciaException{
		if(!coleccionTipoPublicacion.isEmpty()) {
		Set<String> resultado = null;
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
	 * public DTOferta obtenerDTOfertaLaboral(String nombreOferta) throws
	 * DtOfertaNoExisteException { DTOferta resultado =
	 * coleccionDTOfertaLaboral.get(nombreOferta); if(resultado == null) { throw new
	 * DtOfertaNoExisteException("No existe la oferta solicitada"); } else { return
	 * resultado; } }
	 */


	public OfertaLaboral obtenerOfertaLaboral(String nomOferta) throws OfertaLaboralNoExisteException{
		OfertaLaboral resultado = coleccionOfertaLaboral.get(nomOferta);
		if(resultado != null) {
			return resultado;
		} else {
			throw new OfertaLaboralNoExisteException("No existe la oferta solicitada");
		}
	}

}
