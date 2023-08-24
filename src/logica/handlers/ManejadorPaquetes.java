package logica.handlers;

import java.util.ArrayList;
import java.util.HashMap;

import excepciones.PaquetePublicacionNoExisteException;
import excepciones.PaquetePublicacionYaExisteException;
import logica.classes.PaquetePublicacion;

public class ManejadorPaquetes {
	private static ManejadorPaquetes instancia = null;
	private HashMap<String, PaquetePublicacion> colPaquetes;

	public static ManejadorPaquetes getInstance() 
	{
		if (instancia == null)
			instancia = new ManejadorPaquetes();
		return instancia;
	}

	public PaquetePublicacion obtenerPaquete(String nomPaquete) throws PaquetePublicacionNoExisteException 
	{
		if(colPaquetes.isEmpty())
		{
		return colPaquetes.get(nomPaquete);
		}
		else
		{
			throw new PaquetePublicacionNoExisteException("El paquete" + nomPaquete + " no existe");
		}
	}

	public ArrayList<String> listarPaquetes() 
	{
		ArrayList<String> listaPaquetes = new ArrayList<String>();
		for (String key : colPaquetes.keySet()) 
		{
			listaPaquetes.add(key);
		}
		return listaPaquetes;
	}

	public void agregarPaquete(PaquetePublicacion paquete) throws PaquetePublicacionYaExisteException 
	{
		if (colPaquetes.get(paquete.getNombre()) != null) 
		{
			colPaquetes.put(paquete.getNombre(), paquete);
		} 
		else 
		{
			throw new PaquetePublicacionYaExisteException("El paquete" + paquete.getNombre() + "ya existe");
		}
	}

	public void eliminarPaquete(String nomPaquete) throws PaquetePublicacionNoExisteException 
	{
		if (colPaquetes.get(nomPaquete) != null) 
		{
			colPaquetes.remove(nomPaquete);
		} 
		else 
		{
			throw new PaquetePublicacionNoExisteException("El paquete" + nomPaquete + "no existe");
		}
	}
}
