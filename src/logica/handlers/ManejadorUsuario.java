package logica.handlers;

import java.util.ArrayList;
import java.util.HashMap;

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
	
	public Empresa obtenerEmpresa(String nickEmpresa) {
		return ((Empresa) colEmpresas.get(nickEmpresa));
	}
	
	public Postulante obtenerPostulante(String nickPostulante) {
		return ((Postulante) colPostulantes.get(nickPostulante));
	}
	
	public void agregarPostulante(Postulante postulante) {
		colPostulantes.put(postulante.getNickname(), postulante);
		colUsuarios.put(postulante.getNickname(), postulante);
	}
	
	public void agregarEmpresa(Empresa empresa) {
		colEmpresas.put(empresa.getNickname(), empresa);
		colUsuarios.put(empresa.getNickname(), empresa);
	}
	
	public ArrayList<String> listarPostulantes()
	{
		return null;
	}
}
