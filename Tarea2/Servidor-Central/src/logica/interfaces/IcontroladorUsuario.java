package logica.interfaces;

import excepciones.OfertaLaboralNoExisteException;
import excepciones.PaquetePublicacionNoExisteException;
import excepciones.PostulanteNoEsOfertaFavoritaException;
import excepciones.PostulanteYaEsOfertaFavoritaException;
import excepciones.UsuarioEmailRepetidoException;
import excepciones.UsuarioNoEstaSeguidoException;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioNoExistePostulacion;
import excepciones.UsuarioYaEstaSeguidoException;
import excepciones.UsuarioYaExisteException;
import excepciones.UsuarioYaExistePostulacion;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import logica.classes.Empresa;
import logica.classes.Postulante;
import logica.classes.Usuario;
import logica.datatypes.DtCompraPaquete;
import logica.datatypes.DtOfertaLaboral;
import logica.datatypes.DtpaquetePublicacion;
import logica.datatypes.Dtpostulacion;
import logica.datatypes.Dtusuario;

/**
 * Interface ControladorUsuario .
 */

public interface IcontroladorUsuario {
  
  public Empresa obtenerEmpresa(String nicknameEmpresa)
      throws UsuarioNoExisteException;
  
  public List<String> listarEmpresas();
  
  public List<String> listaDeUsuarios();
  
  public void editarDatosBasicos(Dtusuario usuario,
      String nombreNuevo, String apellidoNuevo)
      throws UsuarioNoExisteException;
  
  public List<String> obtenerOfertasEmpresa(
      String nicknameEmpresa)
      throws UsuarioNoExisteException;
  
  public void registrarPostulacion(String cvReducido,
      String motivacion, LocalDate fechaPostulacion,
      String nickname, String nomOferta, String linkVideo)
      throws UsuarioNoExisteException,
      OfertaLaboralNoExisteException,
      UsuarioYaExistePostulacion;
  
  public void altaPostulante(String nickname, String nombre,
      String apellido, String email, LocalDate fechaNac,
      String nacionalidad, BufferedImage imagen,
      String constrasenia) throws UsuarioYaExisteException,
      UsuarioEmailRepetidoException;
  
  public void altaEmpresa(String nickname, String nombre,
      String apellido, String email, String descripcion,
      String link, BufferedImage imagen, String contrasenia)
      throws UsuarioYaExisteException,
      UsuarioEmailRepetidoException;
  
  public Postulante obtenerPostulante(String nomPostulante)
      throws UsuarioNoExisteException;
  
  public Dtusuario obtenerDtusuario(String nickname)
      throws UsuarioNoExisteException, IOException;
  
  public List<String> listaOfertasUsuario(
      String nicknameUsuario)
      throws UsuarioNoExisteException;
  
  public Usuario obtenerUsuario(String nickname)
      throws UsuarioNoExisteException;
  
  List<String> listarPostulantes();
  
  public void editarPostulante(String nickname,
      String nombre, String apellido,
      LocalDate fechaNacimiento, String nacionalidad,
      BufferedImage imagen, String contrasenia)
      throws UsuarioNoExisteException;
  
  public void editarEmpresa(String nickname, String nombre,
      String apellido, String sitioWeb, String descripcion,
      BufferedImage imagen, String contrasenia)
      throws UsuarioNoExisteException;
  
  public Dtpostulacion obtenerDtpostulacion(
      String nicknamePostulante, String nombreOferta)
      throws UsuarioNoExisteException,
      UsuarioNoExistePostulacion;
  
  List<DtOfertaLaboral> obtenerDtofertasIngresadasDeEmpresa(
      String nicknameEmpresa)
      throws UsuarioNoExisteException, IOException;
  
  List<DtOfertaLaboral> obtenerDtofertasConfirmadasDeEmpresa(
      String nicknameEmpresa)
      throws UsuarioNoExisteException, IOException;
  
  List<DtOfertaLaboral> obtenerDtofertasRechazadasDeEmpresa(
      String nicknameEmpresa)
      throws UsuarioNoExisteException, IOException;
  
  Boolean confirmarContrasenia(String clave,
      String contrasenia) throws UsuarioNoExisteException;
  
  List<Dtusuario> obtenerDtusuarios() throws IOException;
  
  List<DtpaquetePublicacion> obtenerDtpaquetesDeEmpresa(
      String nicknameEmpresa)
      throws UsuarioNoExisteException, IOException;
  
  List<Dtpostulacion> obtenerDtpostulacionesDePostulante(
      String nicknamePostulante)
      throws UsuarioNoExisteException;
  
  List<String> listarPaquetesNoCompradosDeEmpresa(
      String nicknameEmpresa)
      throws UsuarioNoExisteException;
  
  void comprarPaquete(String nicknameEmpresa,
      String nombrePaquete, LocalDate fechaCompra)
      throws UsuarioNoExisteException,
      PaquetePublicacionNoExisteException;
  
  List<DtCompraPaquete> obtenerDtCompraPaqueteDeEmpresa(
      String nicknameEmpresa)
      throws UsuarioNoExisteException, IOException;

  void agregarSeguidor(String nicknameUsuario, String nicknameSeguidor)
      throws UsuarioNoExisteException, UsuarioYaEstaSeguidoException;
  
  void dejarDeSeguir(String nicknameUsuario, String nicknameSeguidor)
      throws UsuarioNoExisteException, UsuarioNoEstaSeguidoException;
  
  void agregarOfertaFavorita(String nicknamePostulante, String nombreOferta)
      throws UsuarioNoExisteException, PostulanteYaEsOfertaFavoritaException,
      OfertaLaboralNoExisteException;

  void removerOfertaFavorita(String nicknamePsotulante, String nombreOferta)
      throws UsuarioNoExisteException, PostulanteNoEsOfertaFavoritaException;

  List<DtOfertaLaboral> obtenerDtofertasFinalizadasDeEmpresa(String nicknameEmpresa)
      throws UsuarioNoExisteException, IOException;
}
