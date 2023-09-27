package logica.interfaces;

import java.util.Date;
import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.util.ArrayList;

import excepciones.OfertaLaboralNoExisteException;
import excepciones.PaquetePublicacionNoExisteException;
import excepciones.UsuarioEmailRepetidoException;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioNoExistePostulacion;
import excepciones.UsuarioYaExisteException;
import excepciones.UsuarioYaExistePostulacion;
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
	
	public  void registrarPostulacion(String cvReducido,String motivacion, LocalDate fechaPostulacion, String nickname, String nomOferta) throws UsuarioNoExisteException, OfertaLaboralNoExisteException, UsuarioYaExistePostulacion;
	
	public  void altaPostulante(String nickname, String nombre, String apellido, String email, LocalDate fechaNac, String nacionalidad, BufferedImage imagen, String constrasenia) throws UsuarioYaExisteException, UsuarioEmailRepetidoException;
	
	public  void altaEmpresa(String nickname, String nombre, String apellido, String email, String descripcion, String link, BufferedImage imagen, String contrasenia) throws UsuarioYaExisteException, UsuarioEmailRepetidoException;
	
	public  Postulante obtenerPostulante(String nomPostulante) throws UsuarioNoExisteException;
	
	public  DTUsuario obtenerDTUsuario(String nickname) throws UsuarioNoExisteException;

	public ArrayList<String> listaOfertasUsuario(String nicknameUsuario) throws UsuarioNoExisteException;
	
	public Usuario obtenerUsuario(String nickname) throws UsuarioNoExisteException;

	ArrayList<String> listarPostulantes();
	
	public void editarPostulante(String nickname, String nombre, String apellido, LocalDate fechaNacimiento, String nacionalidad, BufferedImage imagen, String contrasenia) throws UsuarioNoExisteException;
	
	public void editarEmpresa(String nickname, String nombre, String apellido, String sitioWeb, String descripcion, BufferedImage imagen, String contrasenia ) throws UsuarioNoExisteException;
	
	public DTPostulacion obtenerDTPostulacion(String nicknamePostulante, String nombreOferta) throws UsuarioNoExisteException, UsuarioNoExistePostulacion;

	ArrayList<DTOfertaLaboral> obtenerDTOfertasIngresadasDeEmpresa(String nicknameEmpresa) throws UsuarioNoExisteException;

	ArrayList<DTOfertaLaboral> obtenerDTOfertasConfirmadasDeEmpresa(String nicknameEmpresa) throws UsuarioNoExisteException;

	ArrayList<DTOfertaLaboral> obtenerDTOfertasRechazadasDeEmpresa(String nicknameEmpresa) throws UsuarioNoExisteException;

	Boolean confirmarContrasenia(String clave, String contrasenia) throws UsuarioNoExisteException;

	ArrayList<DTUsuario> obtenerDTUsuarios();

	ArrayList<DTPaquetePublicacion> obtenerDTPaquetesDeEmpresa(String nicknameEmpresa) throws UsuarioNoExisteException;

	ArrayList<DTPostulacion> obtenerDTPostulacionesDePostulante(String nicknamePostulante) throws UsuarioNoExisteException;

	ArrayList<String> listarPaquetesNoCompradosDeEmpresa(String nicknameEmpresa) throws UsuarioNoExisteException;

	void comprarPaquete(String nicknameEmpresa, String nombrePaquete, LocalDate fechaCompra)
			throws UsuarioNoExisteException, PaquetePublicacionNoExisteException;
}
