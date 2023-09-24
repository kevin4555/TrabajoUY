package logica.controllers;

import java.util.Date;
import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.util.ArrayList;

import excepciones.OfertaLaboralNoExisteException;
import excepciones.PaquetePublicacionNoExisteException;
import excepciones.UsuarioEmailRepetidoException;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioYaExisteException;
import logica.DataTypes.DTOfertaLaboral;
import logica.DataTypes.DTPaquetePublicacion;
import logica.DataTypes.DTPostulacion;
import logica.DataTypes.DTUsuario;
import logica.classes.CompraPaquete;
import logica.classes.Empresa;
import logica.classes.OfertaLaboral;
import logica.classes.PaquetePublicacion;
import logica.classes.Postulacion;
import logica.classes.Postulante;
import logica.classes.Usuario;
import logica.handlers.ManejadorUsuario;
import logica.interfaces.IControladorOferta;
import logica.interfaces.IControladorUsuario;

public class ControladorUsuario implements IControladorUsuario {

	@Override
	public Empresa obtenerEmpresa(String nicknameEmpresa) throws UsuarioNoExisteException {
		ManejadorUsuario manejUsu = ManejadorUsuario.getInstance();
		Empresa emp = manejUsu.obtenerEmpresa(nicknameEmpresa);
		return emp;
	}

	@Override
	public ArrayList<String> listarEmpresas() {
		ManejadorUsuario manejUsu = ManejadorUsuario.getInstance();
		ArrayList<String> nomEmpresas = manejUsu.listarEmpresas();
		return nomEmpresas;
	}

	@Override
	public ArrayList<String> listaDeUsuarios() {
		ManejadorUsuario manejadorUsuarios = ManejadorUsuario.getInstance();
		return manejadorUsuarios.listarUsuarios();
	}

	@Override
	public void editarDatosBasicos(DTUsuario dtusuario, String nombreNuevo, String apellidoNuevo) throws UsuarioNoExisteException {
		ManejadorUsuario manejadorUsuario = ManejadorUsuario.getInstance();
		Usuario usuario = manejadorUsuario.obtenerUsuario(dtusuario.getNickname());
		usuario.setApellido(apellidoNuevo);
		usuario.setNombre(nombreNuevo);
		// si se necesitan cambiar mas datos hay que hacer alguna magia para distinguir
		// la empresa del postulante
	}

	@Override
	public ArrayList<String> obtenerOfertasEmpresa(String nicknameEmpresa) throws UsuarioNoExisteException {
		ManejadorUsuario manejadorUsuarios = ManejadorUsuario.getInstance();
		Empresa empr = manejadorUsuarios.obtenerEmpresa(nicknameEmpresa);
		return empr.obtenerNombresOfertas();

	}

	@Override
	public ArrayList<String> listarPostulantes() {
		ManejadorUsuario manejadorUsuarios = ManejadorUsuario.getInstance();
		return manejadorUsuarios.listarPostulantes();
	}

	@Override
	public void registrarPostulacion(String cvReducido, String motivacion, LocalDate fechaPostulacion, String nickname,
			String nomOferta) throws UsuarioNoExisteException, OfertaLaboralNoExisteException {
		ManejadorUsuario manejadorUsuario = ManejadorUsuario.getInstance();
		Postulante postulante = manejadorUsuario.obtenerPostulante(nickname);
		Fabrica fabrica = Fabrica.getInstance();
		IControladorOferta controladorOferta = fabrica.obtenerControladorOferta();
		OfertaLaboral oferta = controladorOferta.obtenerOfertaLaboral(nomOferta);
		Postulacion postulacion = new Postulacion(motivacion, fechaPostulacion, cvReducido, postulante, oferta);
		postulante.agregarPostulacion(postulacion);
		oferta.agregarPostulacion(postulacion);

	}

	@Override
	public void altaPostulante(String nickname, String nombre, String apellido, String email, LocalDate fechaNac,
			String nacionalidad, BufferedImage imagen, String constrasenia) throws UsuarioYaExisteException, UsuarioEmailRepetidoException {
		ManejadorUsuario manejadorUsuario = ManejadorUsuario.getInstance();
		Postulante postulante = new Postulante(nickname, nombre, apellido, email.toLowerCase(), fechaNac, nacionalidad, imagen, constrasenia);
		manejadorUsuario.agregarPostulante(postulante);
	}

	@Override
	public void altaEmpresa(String nickname, String nombre, String apellido, String email, String descripcion,
			String link, BufferedImage imagen, String contrasenia) throws UsuarioYaExisteException, UsuarioEmailRepetidoException {
		ManejadorUsuario manejadorUsuarios = ManejadorUsuario.getInstance();
		Empresa empresa = new Empresa(nickname, nombre, apellido, email.toLowerCase(), descripcion, link.toLowerCase(), imagen, contrasenia);
		manejadorUsuarios.agregarEmpresa(empresa);

	}
	
	@Override
	public Usuario obtenerUsuario(String nickname) throws UsuarioNoExisteException {
		ManejadorUsuario manejadorUsuario = ManejadorUsuario.getInstance();
		Usuario usuario = manejadorUsuario.obtenerUsuario(nickname);
		return usuario;
	}

	@Override
	public DTUsuario obtenerDTUsuario(String nickname) throws UsuarioNoExisteException {
		Usuario usuario = this.obtenerUsuario(nickname);
		return usuario.obtenerDTUsuario();
	}
	
	@Override
	public ArrayList<String> listaOfertasUsuario(String nickname) throws UsuarioNoExisteException {
		Usuario usuario = this.obtenerUsuario(nickname);
		return usuario.listarOfertasUsuario();
	}

	@Override
	public Postulante obtenerPostulante(String nomPostulante) throws UsuarioNoExisteException {
	   ManejadorUsuario manejadorUsuario = ManejadorUsuario.getInstance();
	   return manejadorUsuario.obtenerPostulante(nomPostulante);
	}
	
	@Override
	public void editarPostulante(String nickname, String nombre, String apellido, LocalDate fechaNacimiento, String nacionalidad, BufferedImage imagen, String contrasenia) throws UsuarioNoExisteException {
		Postulante postulante = ManejadorUsuario.getInstance().obtenerPostulante(nickname);
		postulante.setNombre(nombre);
		postulante.setApellido(apellido);
		postulante.setFechaNacimiento(fechaNacimiento);
		postulante.setNacionalidad(nacionalidad);
		postulante.setImagen(imagen);
		postulante.setContrasenia(contrasenia);
	}
	
	@Override
	public void editarEmpresa(String nickname, String nombre, String apellido, String sitioWeb, String descripcion, BufferedImage imagen, String contrasenia) throws UsuarioNoExisteException {
		
		Empresa empresa = ManejadorUsuario.getInstance().obtenerEmpresa(nickname);
		empresa.setNombre(nombre);
		empresa.setApellido(apellido);
		empresa.setSitioWeb(sitioWeb.toLowerCase());
		empresa.setDescripcion(descripcion);
		empresa.setImagen(imagen);
		empresa.setContrasenia(contrasenia);
	}
	
	@Override
	public DTPostulacion obtenerDTPostulacion(String nicknamePostulante, String nombreOferta) throws UsuarioNoExisteException {
		Postulante postulante = ManejadorUsuario.getInstance().obtenerPostulante(nicknamePostulante);
		return postulante.obtenerDTPostulacion(nombreOferta);
	}
	
	@Override
	public ArrayList<DTOfertaLaboral> obtenerDTOfertasIngresadasDeEmpresa(String nicknameEmpresa) throws UsuarioNoExisteException{
		Empresa empresa = ManejadorUsuario.getInstance().obtenerEmpresa(nicknameEmpresa);
		return empresa.obtenerDTOfertasIngresadas();
	}
	
	@Override
	public ArrayList<DTOfertaLaboral> obtenerDTOfertasConfirmadasDeEmpresa(String nicknameEmpresa) throws UsuarioNoExisteException{
		Empresa empresa = ManejadorUsuario.getInstance().obtenerEmpresa(nicknameEmpresa);
		return empresa.obtenerDTOfertasConfirmadas();
	}

	@Override
	public ArrayList<DTOfertaLaboral> obtenerDTOfertasRechazadasDeEmpresa(String nicknameEmpresa) throws UsuarioNoExisteException{
		Empresa empresa = ManejadorUsuario.getInstance().obtenerEmpresa(nicknameEmpresa);
		return empresa.obtenerDTOfertasRechazadas();
	}
	
	@Override
	public Boolean confirmarContrasenia(String clave, String contrasenia) throws UsuarioNoExisteException {
		Usuario usuario = ManejadorUsuario.getInstance().obtenerUsuario(clave);
		return contrasenia == usuario.getContrasenia();
	}
	
	@Override
	public ArrayList<DTUsuario> obtenerDTUsuarios(){
		return ManejadorUsuario.getInstance().obtenerDTUsuarios();
	}
	
	@Override
	public void comprarPaquete(String nicknameEmpresa, String nombrePaquete, LocalDate fechaCompra) throws UsuarioNoExisteException, PaquetePublicacionNoExisteException {
		Empresa empresa = ManejadorUsuario.getInstance().obtenerEmpresa(nicknameEmpresa);
		IControladorOferta controladorOferta = Fabrica.getInstance().obtenerControladorOferta();
		PaquetePublicacion paquete = controladorOferta.obtenerPaquetePublicacion(nombrePaquete);
		CompraPaquete compra = new CompraPaquete(fechaCompra, paquete);
		empresa.comprarPaquete(compra);
		
	}
	
	@Override
	public ArrayList<DTPaquetePublicacion> obtenerDTPaquetesDeEmpresa(String nicknameEmpresa) throws UsuarioNoExisteException{
		Empresa empresa = ManejadorUsuario.getInstance().obtenerEmpresa(nicknameEmpresa);
		return empresa.obtenerDTPaquetes();
		
	}
	
	@Override
	public ArrayList<DTPostulacion> obtenerDTPostulacionesDePostulante(String nicknamePostulante) throws UsuarioNoExisteException{
		Postulante postulante = ManejadorUsuario.getInstance().obtenerPostulante(nicknamePostulante);
		return postulante.obtenerDTPostulaciones();
	}
	
	@Override
	public ArrayList<String> listarPaquetesNoCompradosDeEmpresa(String nicknameEmpresa) throws UsuarioNoExisteException{
		Empresa empresa = ManejadorUsuario.getInstance().obtenerEmpresa(nicknameEmpresa);
		ArrayList<String> paquetesComprados = empresa.listarPaquetesComprados();
		IControladorOferta controladorOfertas = Fabrica.getInstance().obtenerControladorOferta();
		ArrayList<String> listaResultado = controladorOfertas.listarPaquetes();
		for(String paquete : paquetesComprados) {
			listaResultado.remove(paquete);
		}
		return listaResultado;
	}
}
