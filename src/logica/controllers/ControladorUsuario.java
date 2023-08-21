package logica.controllers;

import java.sql.Date;
import java.util.ArrayList;

import excepciones.ColeccionEmpresaEsVaciaException;
import excepciones.UsuarioNoExisteUsuarioException;
import logica.DataTypes.DTUsuario;
import logica.classes.Empresa;
import logica.handlers.ManejadorUsuario;
import logica.interfaces.IControladorUsuario;


public class ControladorUsuario implements IControladorUsuario {


	public Empresa obtenerEmpresa(String nicknameEmpresa) throws UsuarioNoExisteUsuarioException {
		ManejadorUsuario manejUsu = ManejadorUsuario.getinstance();
		Empresa emp = manejUsu.obtenerEmpresa(nicknameEmpresa);
		if (emp == null) 
			throw new UsuarioNoExisteUsuarioException("La empresa " + nicknameEmpresa + " no existe");
		else {
			return emp;
		}
	}

	public ArrayList<String> listarEmpresas() throws ColeccionEmpresaEsVaciaException{
		ManejadorUsuario manejUsu = ManejadorUsuario.getinstance();
		ArrayList<String> nomEmpresas = manejUsu.listarEmpresas();
		if (nomEmpresas != null) {
			return nomEmpresas;
		}
		else {
			throw new ColeccionEmpresaEsVaciaException("No existen tipos de publicaciones registrados");
		}
	}

	@Override
	public ArrayList<String> listaDeUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void editarDatosBasicos(DTUsuario usuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<String> obtenerOfertasEmpresa(String nicknameEmpresa) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> listarPostulantes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registrarPostulacion(String cvReducido, String motivacion, Date fechaPostulacion, String nickname,
			String nomOferta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void altaPostulante(String nickname, String nombre, String apellido, String email, Date fechaNac,
			String nacionalidad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void altaEmpresa(String nickname, String nombre, String apellido, String email, String descripcion,
			String link) {
		// TODO Auto-generated method stub
		
	}

}