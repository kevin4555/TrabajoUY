package logica.controllers;

import java.sql.Date;
import java.util.ArrayList;

import excepciones.ColeccionEmpresaEsVaciaException;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioYaExisteException;
import logica.DataTypes.DTUsuario;
import logica.classes.Empresa;
import logica.classes.OfertaLaboral;
import logica.classes.Postulacion;
import logica.classes.Postulante;
import logica.classes.Usuario;
import logica.handlers.ManejadorUsuario;
import logica.interfaces.IControladorOferta;
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
		ManejadorUsuario manejadorUsuarios = ManejadorUsuario.getInstance();
		return manejadorUsuarios.listarUsuarios();
	}

	@Override
	public void editarDatosBasicos(DTUsuario dtusuario) throws UsuarioNoExisteException {
		ManejadorUsuario manejadorUsuario = ManejadorUsuario.getInstance();
		Usuario usuario = manejadorUsuario.obtenerUsuario(dtusuario.getNickname());
		usuario.setApellido(dtusuario.getApellido());
		usuario.setNombre(dtusuario.getNombre());
		//si se necesitan cambiar mas datos hay que hacer alguna magia para distinguir la empresa del postulante
		
	}

	@Override
	public ArrayList<String> obtenerOfertasEmpresa(String nicknameEmpresa) throws UsuarioNoExisteException {
		ManejadorUsuario manejadorUsuarios = ManejadorUsuario.getInstance();
		Empresa empr = manejadorUsuarios.obtenerEmpresa(nicknameEmpresa);
		if(empr != null)
		{
		return empr.obtenerNombresOfertas();
		}
		else
		{
			throw new UsuarioNoExisteException("La empresa seleccionada no contiene ofertas");
		}
	}

	@Override
	public ArrayList<String> listarPostulantes() {
		ManejadorUsuario manejadorUsuarios = ManejadorUsuario.getInstance();
		return manejadorUsuarios.listarPostulantes();
	}
	
	@Override
	public void registrarPostulacion(String cvReducido, String motivacion, Date fechaPostulacion, String nickname,
			String nomOferta) throws UsuarioNoExisteException {
		ManejadorUsuario manejadorUsuario = ManejadorUsuario.getInstance();
		Postulante postulante = manejadorUsuario.obtenerPostulante(nickname);
		Fabrica fabrica = Fabrica.getInstance();
		IControladorOferta controladorOferta = fabrica.obtenerControladorOferta();
		OfertaLaboral oferta = controladorOferta.obtenerOfertaLaboral(nomOferta);
		Postulacion postulacion = new Postulacion(motivacion, fechaPostulacion, cvReducido, postulante,oferta);
		postulante.agregarPostulacion(postulacion);
		oferta.agregarPostulacion(postulacion);
		
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
			String link) throws UsuarioYaExisteException {
		ManejadorUsuario manejadorUsuarios = ManejadorUsuario.getInstance();
		Empresa empresa = new Empresa(nickname, nombre, apellido, email, descripcion, link);
		manejadorUsuarios.agregarEmpresa(empresa);
		
	}

}