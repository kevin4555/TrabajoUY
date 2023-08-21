package logica.controllers;

import java.sql.Date;
import java.util.ArrayList;

import excepciones.ColeccionEmpresaEsVaciaException;
import excepciones.UsuarioNoExisteException;
import logica.DataTypes.DTUsuario;
import logica.classes.Empresa;
import logica.classes.Postulante;
import logica.handlers.ManejadorUsuario;
import logica.interfaces.IControladorUsuario;


public class ControladorUsuario implements IControladorUsuario {


	public Empresa obtenerEmpresa(String nicknameEmpresa) throws UsuarioNoExisteException {
		ManejadorUsuario manejUsu = ManejadorUsuario.getInstance();
		Empresa emp = manejUsu.obtenerEmpresa(nicknameEmpresa);
		if (emp == null) 
			throw new UsuarioNoExisteException("La empresa " + nicknameEmpresa + " no existe");
		else {
			return emp;
		}
	}

	public ArrayList<String> listarEmpresas() throws ColeccionEmpresaEsVaciaException{
		ManejadorUsuario manejUsu = ManejadorUsuario.getInstance();
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
		ManejadorUsuario manejadorUsuario = ManejadorUsuario.getInstance();
		Postulante postulante = new Postulante(nickname, nombre, apellido, email, fechaNac, nacionalidad);
		try {
			manejadorUsuario.agregarPostulante(postulante);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		
		
	}

	@Override
	public void altaEmpresa(String nickname, String nombre, String apellido, String email, String descripcion,
			String link) {
		// TODO Auto-generated method stub
		
	}

}