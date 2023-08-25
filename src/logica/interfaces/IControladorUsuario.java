package logica.interfaces;

import java.util.Date;
import java.util.ArrayList;

import excepciones.OfertaLaboralNoExisteException;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioYaExisteException;
import logica.DataTypes.DTUsuario;
import logica.classes.Empresa;
import logica.classes.Postulante;
import logica.classes.Usuario;

public interface IControladorUsuario {

	public Empresa obtenerEmpresa(String nicknameEmpresa) throws UsuarioNoExisteException;

	public ArrayList<String> listarEmpresas();

	public ArrayList<String> listaDeUsuarios();

	public void editarDatosBasicos(DTUsuario usuario) throws UsuarioNoExisteException;

	public ArrayList<String> obtenerOfertasEmpresa(String nicknameEmpresa) throws UsuarioNoExisteException;

	public ArrayList<String> listarPostulantes();

	public void registrarPostulacion(String cvReducido, String motivacion, Date fechaPostulacion, String nickname,
			String nomOferta) throws UsuarioNoExisteException, OfertaLaboralNoExisteException;

	public void altaPostulante(String nickname, String nombre, String apellido, String email, Date fechaNac,
			String nacionalidad) throws UsuarioYaExisteException;

	public void altaEmpresa(String nickname, String nombre, String apellido, String email, String descripcion,
			String link) throws UsuarioYaExisteException;

	public Postulante obtenerPostulante(String nomPostulante) throws UsuarioNoExisteException;

	public DTUsuario obtenerDTUsuario(String nickname) throws UsuarioNoExisteException;

	public Empresa obtenerEmpresa(String nicknameEmpresa) throws UsuarioNoExisteException;

	public ArrayList<String> listarEmpresas();

	public ArrayList<String> listaDeUsuarios();

	public void editarDatosBasicos(DTUsuario usuario) throws UsuarioNoExisteException;

	public ArrayList<String> obtenerOfertasEmpresa(String nicknameEmpresa) throws UsuarioNoExisteException;

	public ArrayList<String> listarPostulantes();

	public void registrarPostulacion(String cvReducido, String motivacion, Date fechaPostulacion, String nickname,
			String nomOferta) throws UsuarioNoExisteException, OfertaLaboralNoExisteException;

	public void altaPostulante(String nickname, String nombre, String apellido, String email, Date fechaNac,
			String nacionalidad) throws UsuarioYaExisteException;

	public void altaEmpresa(String nickname, String nombre, String apellido, String email, String descripcion,
			String link) throws UsuarioYaExisteException;

	public Postulante obtenerPostulante(String nomPostulante) throws UsuarioNoExisteException;

	public DTUsuario obtenerDTUsuario(String nickname) throws UsuarioNoExisteException;

	public ArrayList<String> listaOfertasUsuario(String nickname) throws UsuarioNoExisteException;

	public Usuario obtenerUsuario(String nickname) throws UsuarioNoExisteException;
}
