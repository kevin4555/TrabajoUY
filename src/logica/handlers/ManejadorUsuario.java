package logica.handlers;

import java.util.ArrayList;
import java.util.HashMap;

import excepciones.UsuarioNoExisteUsuarioException;
import excepciones.UsuarioYaExisteUsuarioException;
import logica.classes.Empresa;
import logica.classes.Postulante;
import logica.classes.Usuario;

public class ManejadorUsuario {
	private static ManejadorUsuario instancia = null;
	private HashMap<String, Usuario> colUsuarios;
	private HashMap<String, Postulante> colPostulantes;
	private HashMap<String, Empresa> colEmpresas;
	
	private ManejadorUsuario() {
		colUsuarios = new HashMap<String, Usuario>();
		colPostulantes = new HashMap<String, Postulante>();
		colEmpresas = new HashMap<String, Empresa>();
	}
	public static ManejadorUsuario getInstance() {
        if (instancia == null)
            instancia = new ManejadorUsuario();
        return instancia;
	}
	
	public ArrayList<String> listarEmpresas() {
		ArrayList<String> listEmpresa = new ArrayList<String>();
		for(String empr : colEmpresas.keySet()) {
			listEmpresa.add(empr);
		}
		return listEmpresa;	
	}
	
	public Empresa obtenerEmpresa(String nickEmpresa) throws UsuarioNoExisteUsuarioException {
		if (!colEmpresas.containsKey(nickEmpresa)) {
			throw new UsuarioNoExisteUsuarioException("No existe empresa con nickname: " + nickEmpresa);
		}
		return ((Empresa) colEmpresas.get(nickEmpresa));
	}
	
	public Postulante obtenerPostulante(String nickPostulante) throws UsuarioNoExisteUsuarioException {
		if (!colPostulantes.containsKey(nickPostulante)) {
			throw new UsuarioNoExisteUsuarioException("No existe postulante con nickname: " + nickPostulante);
		}
		return ((Postulante) colPostulantes.get(nickPostulante));
	}
	
	public void agregarPostulante(Postulante postulante) throws UsuarioYaExisteUsuarioException {
		if(!colUsuarios.containsKey(postulante.getNickname())) {
			colPostulantes.put(postulante.getNickname(), postulante);
			colUsuarios.put(postulante.getNickname(), postulante);
		}
		else {
			throw new UsuarioYaExisteUsuarioException("Ya existe usuario con nickname: " + postulante.getNickname());
		}
	}
	
	public void agregarEmpresa(Empresa empresa) throws UsuarioYaExisteUsuarioException {
		if (!colUsuarios.containsKey(empresa.getNickname())) {
			throw new UsuarioYaExisteUsuarioException("Ya existe usuario con nickname: " + empresa.getNickname());
		}
		else {
			colEmpresas.put(empresa.getNickname(), empresa);
			colUsuarios.put(empresa.getNickname(), empresa);
		}
	}
	
	public ArrayList<String> listarUsuarios(){
		ArrayList<String> listaUsuarios = new ArrayList<String>();
		for(String nickUsuario : colUsuarios.keySet()) {
			listaUsuarios.add(nickUsuario);
		}
		return listaUsuarios;
	}
	
	public Usuario obtenerUsuario(String nicknameUsuario) throws UsuarioNoExisteUsuarioException {
		if(!colUsuarios.containsKey(nicknameUsuario)) {
			throw new UsuarioNoExisteUsuarioException("No existe usuario con nickname: " + nicknameUsuario);
		}
		return ((Usuario)colUsuarios.get(nicknameUsuario));
	}
	
	public ArrayList<String> listarPostulanes(){
		ArrayList<String> listaPostulantes = new ArrayList<String>();
		for(String nickPostulante : colPostulantes.keySet()) {
			listaPostulantes.add(nickPostulante);
		}
		return listaPostulantes;
	}
}
