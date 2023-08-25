package logica.handlers;

import java.util.ArrayList;
import java.util.HashMap;

import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioYaExisteException;
import logica.classes.Empresa;
import logica.classes.Postulante;
import logica.classes.Usuario;

public class ManejadorUsuario {
	private static ManejadorUsuario instancia = null;
	private HashMap<String, Usuario> colUsuarios;
	private HashMap<String, Postulante> colPostulantes;
	private HashMap<String, Empresa> colEmpresas;
	private HashMap<String, Usuario> usuariosEmail;
	
	private ManejadorUsuario() {
		colUsuarios = new HashMap<String, Usuario>();
		colPostulantes = new HashMap<String, Postulante>();
		colEmpresas = new HashMap<String, Empresa>();
		
	}
	
	public static ManejadorUsuario getInstance() 
	{
        if (instancia == null)
            instancia = new ManejadorUsuario();
        return instancia;
	}
	
	public ArrayList<String> listarEmpresas() 
	{
		ArrayList<String> listEmpresa = new ArrayList<String>();
		for(String empr : colEmpresas.keySet()) 
		{
			listEmpresa.add(empr);
		}
		return listEmpresa;
	}
	
	public Empresa obtenerEmpresa(String nickEmpresa) throws UsuarioNoExisteException 
	{
		if(colEmpresas.containsKey(nickEmpresa)) 
		{
			return colEmpresas.get(nickEmpresa);
		}
		else 
		{
			throw new UsuarioNoExisteException("Empresa " + nickEmpresa + " no existe");
		}
	}
	
	

	@SuppressWarnings("unlikely-arg-type")
	public void agregarEmpresa(Empresa empresa) throws UsuarioYaExisteException 
	{
		if(!colEmpresas.containsKey(empresa)) 
		{
			colEmpresas.put(empresa.getNickname(), empresa);
			colUsuarios.put(empresa.getNickname(), empresa);
		}
		else 
		{
			throw new UsuarioYaExisteException("Empresa " + empresa.getNickname() + " ya existe");
		}
	}
	
	public Postulante obtenerPostulante(String nickPostulante) throws UsuarioNoExisteException 
	{
		if(colPostulantes.containsKey(nickPostulante)) 
		{
			return  colPostulantes.get(nickPostulante);
		}
		else 
		{
			throw new UsuarioNoExisteException("Postulante " + nickPostulante + " no existe");
		}
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public void agregarPostulante(Postulante postulante) throws UsuarioYaExisteException 
	{
		if(!colPostulantes.containsKey(postulante)) 
		{
			colPostulantes.put(postulante.getNickname(), postulante);
			colUsuarios.put(postulante.getNickname(), postulante);
		}
		else 
		{
			throw new UsuarioYaExisteException("Postulante " + postulante.getNickname() + " ya existe");
		}
	}
	
	public ArrayList<String> listarPostulantes()
	{
		ArrayList<String> postulantes = new ArrayList<String>();
		for (String key : colPostulantes.keySet()) 
		{
			postulantes.add(key);
		}
		return postulantes;
	}
	
	public ArrayList<String> listarUsuarios()
	{
		ArrayList<String> listaUsuarios = new ArrayList<String>();
		for(String nickUsuario : colUsuarios.keySet()) 
		{
			listaUsuarios.add(nickUsuario);
		}
		return listaUsuarios;
	}	
	
	public Usuario obtenerUsuario(String nicknameUsuario) throws UsuarioNoExisteException 
	{
		if(colUsuarios.containsKey(nicknameUsuario)) 
		{
			return colUsuarios.get(nicknameUsuario);
		}
		else
		{
			throw new UsuarioNoExisteException("Usuario: " + nicknameUsuario + " no existe");
		}
	}
	
	public void clean() {
		instancia = null;
	}
}
