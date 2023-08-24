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
	
	public abstract Empresa obtenerEmpresa(String nicknameEmpresa) throws UsuarioNoExisteException;
	
	public abstract ArrayList<String> listarEmpresas();
	
	public abstract ArrayList<String> listaDeUsuarios();
	
	public abstract void editarDatosBasicos(DTUsuario usuario) throws UsuarioNoExisteException;
	
	public abstract ArrayList<String> obtenerOfertasEmpresa(String nicknameEmpresa) throws UsuarioNoExisteException;
	
	public abstract ArrayList<String> listarPostulantes();
	
	public abstract void registrarPostulacion(String cvReducido,String motivacion, Date fechaPostulacion, String nickname, String nomOferta) throws UsuarioNoExisteException, OfertaLaboralNoExisteException;
	
	public abstract void altaPostulante(String nickname, String nombre, String apellido, String email, Date fechaNac, String nacionalidad) throws UsuarioYaExisteException;
	
	public abstract void altaEmpresa(String nickname, String nombre, String apellido, String email, String descripcion, String link) throws UsuarioYaExisteException;
	
	public abstract DTUsuario obtenerDTUsuario(String nickname) throws UsuarioNoExisteException;
	
	public abstract Usuario obtenerUsuario(String nickname) throws UsuarioNoExisteException;
	
	public abstract ArrayList<String> listaOfertasUsuario(String nickname) throws UsuarioNoExisteException;
	
	public abstract Postulante obtenerPostulante(String nickname) throws UsuarioNoExisteException;

}
