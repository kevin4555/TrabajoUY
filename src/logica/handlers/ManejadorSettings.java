package logica.handlers;

import java.util.HashMap;

import excepciones.KeywordNoExisteException;
import excepciones.KeywordYaExisteException;
import excepciones.TipoPublicacionNoExisteException;
import excepciones.TipoPublicacionYaExisteException;
import logica.classes.Keyword;
import logica.classes.TipoPublicacion;

import java.util.ArrayList;

public class ManejadorSettings {

	private static ManejadorSettings instancia = null;
	private HashMap<String, TipoPublicacion> colTipoPublicaciones;
	private HashMap<String, Keyword> colKeywords;

	private ManejadorSettings() 
	{
		colTipoPublicaciones = new HashMap<String, TipoPublicacion>();
		colKeywords = new HashMap<String, Keyword>();
	}
	
	public static ManejadorSettings getInstance() 
	{
		if (instancia == null)
			instancia = new ManejadorSettings();
		return instancia;
	}

	public ArrayList<String> listarTipoDePublicaciones() 
	{
		ArrayList<String> listTipoPublicaciones = new ArrayList<String>();
		for (String key : colTipoPublicaciones.keySet()) 
		{
			listTipoPublicaciones.add(key);
		}
		return listTipoPublicaciones;
	}

	public TipoPublicacion obtenerTipoPublicacion(String nombre) throws TipoPublicacionNoExisteException
	{
		if (colTipoPublicaciones.containsKey(nombre)) 
		{
			return colTipoPublicaciones.get(nombre);
		} 
		else 
		{
			throw new TipoPublicacionNoExisteException("El tipo de publicación " + nombre + " no existe");
		}
	}
	
	public void addTipoPublicacion(TipoPublicacion tpoPublic) throws TipoPublicacionYaExisteException 
	{
		if(!colTipoPublicaciones.containsKey(tpoPublic.getNombre()))
		{	
			colTipoPublicaciones.put(tpoPublic.getNombre(), tpoPublic);
		}
		else
		{
			throw new TipoPublicacionYaExisteException("El tipo publicacion " + tpoPublic.getNombre() +" ya existe");
		}
	}

	public void addKeyword(Keyword keyword) throws KeywordYaExisteException
	{
		if(!colKeywords.containsKey(keyword.getNombre()))
		{
		colKeywords.put(keyword.getNombre(), keyword);
		}
		else
		{
			throw new KeywordYaExisteException("La keyword " + keyword.getNombre() + " ya existe");
		}
	}

	public Keyword obtenerKeyword(String nombre)  throws KeywordNoExisteException
	{
		if(colKeywords.containsKey(nombre))
		{
			return colKeywords.get(nombre);
		}
		else
		{
			throw new KeywordNoExisteException("La keyword " + nombre + " no existe");
		}
	}

	public ArrayList<String> listarKeywords() 
	{
		ArrayList<String> listKewords = new ArrayList<String>();
		for (String key : colKeywords.keySet()) 
		{
			listKewords.add(key);
		}
		return listKewords;
	}
	
	public void clean() {
		instancia = null;
	}
		
}
