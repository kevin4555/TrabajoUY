package logica.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import excepciones.DtOfertaNoExisteException;
import excepciones.OfertaLaboralNoExisteException;
import excepciones.OfertaLaboralYaExisteException;
import logica.DataTypes.DTOfertaLaboral;
import logica.classes.Keyword;
import logica.classes.OfertaLaboral;
import logica.classes.TipoPublicacion;

public class ManejadorOfertas {

	private static ManejadorOfertas instancia = null;

	//private HashMap<String, TipoPublicacion> coleccionTipoPublicacion;
	private HashMap<String, OfertaLaboral> coleccionOfertaLaboral;
	
	private ManejadorOfertas() {
		//coleccionTipoPublicacion = new HashMap<String, TipoPublicacion>();
		coleccionOfertaLaboral = new HashMap<String, OfertaLaboral>();
	}

	public static ManejadorOfertas getInstance() {
		if (instancia == null) {
			instancia = new ManejadorOfertas();
		}
		return instancia;
	}

	/*public ArrayList<String> listarTiposDePublicaciones() 
	{
		ArrayList<String> resultado = new ArrayList<String>();
		for (Map.Entry<String, TipoPublicacion> entry : this.coleccionTipoPublicacion.entrySet()) 
			{
				resultado.add(entry.getKey());
			}
		return resultado;
	}*/

	public void agregarOferta(OfertaLaboral ofertaLaboral) throws OfertaLaboralYaExisteException 
	{
		if (!coleccionOfertaLaboral.containsKey(ofertaLaboral.getNombre())) 
		{
			coleccionOfertaLaboral.put(ofertaLaboral.getNombre(), ofertaLaboral);
		} 
		else 
		{
			throw new OfertaLaboralYaExisteException("La oferta laboral que desea ingresar ya existe");
		}
	}

	public DTOfertaLaboral obtenerDTOfertaLaboral(String nombreOferta) throws DtOfertaNoExisteException 
	{
		if (!coleccionOfertaLaboral.containsKey(nombreOferta)) 
		{
			throw new DtOfertaNoExisteException("No existe la oferta solicitada");
		} 
		else
		{
			return coleccionOfertaLaboral.get(nombreOferta).obtenerDTOfertaLaboral();
		}
	}

	public OfertaLaboral obtenerOfertaLaboral(String nomOferta) throws OfertaLaboralNoExisteException 
		{
		if (coleccionOfertaLaboral.containsKey(nomOferta)) 
		{
			return coleccionOfertaLaboral.get(nomOferta);
		}
		else 
		{
			throw new OfertaLaboralNoExisteException("No existe la oferta solicitada");
		}
	}
	
	public ArrayList<String> listarOfertasLaborales() 
	{
		ArrayList<String> resultado = new ArrayList<String>();
		for (Map.Entry<String, OfertaLaboral> entry : this.coleccionOfertaLaboral.entrySet()) 
			{
				resultado.add(entry.getKey());
			}
		return resultado;
	}
	
	public void clean() {
		instancia = null;
	}
	
}
