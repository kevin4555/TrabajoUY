package logica.interfaces;

import java.util.Date;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import excepciones.OfertaLaboralNoExisteException;
import excepciones.UsuarioEmailRepetidoException;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioYaExisteException;
import logica.DataTypes.DTOfertaLaboral;
import logica.DataTypes.DTPaquetePublicacion;
import logica.DataTypes.DTPostulacion;
import logica.DataTypes.DTUsuario;
import logica.classes.Empresa;
import logica.classes.Postulante;
import logica.classes.Usuario;

public interface IControladorUsuario {
	
	public  Empresa obtenerEmpresa(String nicknameEmpresa) throws UsuarioNoExisteException;
	
	public  ArrayList<String> listarEmpresas();
	
	public  ArrayList<String> listaDeUsuarios();
	
	public  void editarDatosBasicos(DTUsuario usuario, String nombreNuevo, String apellidoNuevo) throws UsuarioNoExisteException;
	
	public  ArrayList<String> obtenerOfertasEmpresa(String nicknameEmpresa) throws UsuarioNoExisteException;
	
	public  void registrarPostulacion(String cvReducido,String motivacion, Date fechaPostulacion, String nickname, String nomOferta) throws UsuarioNoExisteException, OfertaLaboralNoExisteException;
	
	public  void altaPostulante(String nickname, String nombre, String apellido, String email, Date fechaNac, String nacionalidad, BufferedImage imagen, String constrasenia) throws UsuarioYaExisteException, UsuarioEmailRepetidoException;
	
	public  void altaEmpresa(String nickname, String nombre, String apellido, String email, String descripcion, String link, BufferedImage imagen, String contrasenia) throws UsuarioYaExisteException, UsuarioEmailRepetidoException;
	
	public  Postulante obtenerPostulante(String nomPostulante) throws UsuarioNoExisteException;
	
	public  DTUsuario obtenerDTUsuario(String nickname) throws UsuarioNoExisteException;

	public ArrayList<String> listaOfertasUsuario(String nicknameUsuario) throws UsuarioNoExisteException;
	
	public Usuario obtenerUsuario(String nickname) throws UsuarioNoExisteException;

	ArrayList<String> listarPostulantes();
	
	public void editarPostulante(String nickname, String nombre, String apellido, Date fechaNacimiento, String nacionalidad, BufferedImage imagen, String contrasenia) throws UsuarioNoExisteException;
	
	public void editarEmpresa(String nickname, String nombre, String apellido, String sitioWeb, String descripcion, BufferedImage imagen, String contrasenia ) throws UsuarioNoExisteException;
	
	public DTPostulacion obtenerDTPostulacion(String nicknamePostulante, String nombreOferta) throws UsuarioNoExisteException;

	ArrayList<DTOfertaLaboral> obtenerDTOfertasIngresadasDeEmpresa(String nicknameEmpresa) throws UsuarioNoExisteException;

	ArrayList<DTOfertaLaboral> obtenerDTOfertasConfirmadasDeEmpresa(String nicknameEmpresa) throws UsuarioNoExisteException;

	ArrayList<DTOfertaLaboral> obtenerDTOfertasRechazadasDeEmpresa(String nicknameEmpresa) throws UsuarioNoExisteException;

	Boolean confirmarContrasenia(String clave, String contrasenia) throws UsuarioNoExisteException;

	ArrayList<DTUsuario> obtenerDTUsuarios();

	DTPostulacion obtenerDTPostulacionDePostulanteAOferta(String nicknamePostulante, String nombreOferta) throws UsuarioNoExisteException;

	ArrayList<DTPaquetePublicacion> obtenerDTPaquetesDeEmpresa(String nicknameEmpresa) throws UsuarioNoExisteException;

	ArrayList<DTPostulacion> obtenerDTPostulacionesDePostulante(String nicknamePostulante) throws UsuarioNoExisteException;

	ArrayList<String> listarPaquetesNoCompradosDeEmpresa(String nicknameEmpresa) throws UsuarioNoExisteException;
}
