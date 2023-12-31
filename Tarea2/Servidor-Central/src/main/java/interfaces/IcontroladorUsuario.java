package main.java.interfaces;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import main.java.classes.Empresa;
import main.java.classes.Postulante;
import main.java.classes.Usuario;
import main.java.datatypes.DtCompraPaquete;
import main.java.datatypes.DtDatosPdf;
import main.java.datatypes.DtEmpresa;
import main.java.datatypes.DtOfertaLaboral;
import main.java.datatypes.DtPaquetePublicacion;
import main.java.datatypes.DtPostulacion;
import main.java.datatypes.DtUsuario;
import main.java.excepciones.OfertaLaboralNoExisteException;
import main.java.excepciones.PaquetePublicacionNoExisteException;
import main.java.excepciones.PostulanteNoEsOfertaFavoritaException;
import main.java.excepciones.PostulanteYaEsOfertaFavoritaException;
import main.java.excepciones.UsuarioEmailRepetidoException;
import main.java.excepciones.UsuarioNoEstaSeguidoException;
import main.java.excepciones.UsuarioNoExisteException;
import main.java.excepciones.UsuarioNoExistePostulacion;
import main.java.excepciones.UsuarioYaEstaSeguidoException;
import main.java.excepciones.UsuarioYaExisteException;
import main.java.excepciones.UsuarioYaExistePostulacion;

/**
 * Interface ControladorUsuario .
 */

public interface IcontroladorUsuario {

  public Empresa obtenerEmpresa(String nicknameEmpresa)
        throws UsuarioNoExisteException;

  public List<String> listarEmpresas();

  public List<String> listaDeUsuarios();

  public void editarDatosBasicos(DtUsuario usuario,
        String nombreNuevo, String apellidoNuevo)
        throws UsuarioNoExisteException;

  public List<String> obtenerOfertasEmpresaUsuario(
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
        String contrasenia) throws UsuarioYaExisteException,
        UsuarioEmailRepetidoException;

  public void altaEmpresa(String nickname, String nombre,
        String apellido, String email, String descripcion,
        String link, BufferedImage imagen,
        String contrasenia)
        throws UsuarioYaExisteException,
        UsuarioEmailRepetidoException;

  public Postulante obtenerPostulante(String nomPostulante)
        throws UsuarioNoExisteException;

  public DtUsuario obtenerDtUsuario(String nickname)
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
        String apellido, String sitioWeb,
        String descripcion,
        BufferedImage imagen, String contrasenia)
        throws UsuarioNoExisteException;

  public DtPostulacion obtenerDtPostulacion(
        String nicknamePostulante, String nombreOferta)
        throws UsuarioNoExisteException,
        UsuarioNoExistePostulacion;

  List<DtOfertaLaboral> obtenerDtOfertasIngresadasDeEmpresa(
        String nicknameEmpresa)
        throws UsuarioNoExisteException, IOException;

  List<DtOfertaLaboral>
        obtenerDtOfertasConfirmadasDeEmpresa(
              String nicknameEmpresa)
              throws UsuarioNoExisteException, IOException;

  List<DtOfertaLaboral> obtenerDtOfertasRechazadasDeEmpresa(
        String nicknameEmpresa)
        throws UsuarioNoExisteException, IOException;

  Boolean confirmarContrasenia(String clave,
        String contrasenia) throws UsuarioNoExisteException;

  List<DtUsuario> obtenerDtUsuarios() throws IOException;

  List<DtPaquetePublicacion> obtenerDtPaquetesDeEmpresa(
        String nicknameEmpresa)
        throws UsuarioNoExisteException, IOException;

  List<DtPostulacion> obtenerDtPostulacionesDePostulante(
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

  void agregarSeguidor(String nicknameUsuario,
        String nicknameSeguidor)
        throws UsuarioNoExisteException,
        UsuarioYaEstaSeguidoException;

  void dejarDeSeguir(String nicknameUsuario,
        String nicknameSeguidor)
        throws UsuarioNoExisteException,
        UsuarioNoEstaSeguidoException;

  void agregarOfertaFavorita(String nicknamePostulante,
        String nombreOferta)
        throws UsuarioNoExisteException,
        PostulanteYaEsOfertaFavoritaException,
        OfertaLaboralNoExisteException;

  void removerOfertaFavorita(String nicknamePsotulante,
        String nombreOferta)
        throws UsuarioNoExisteException,
        PostulanteNoEsOfertaFavoritaException, OfertaLaboralNoExisteException;

  List<DtOfertaLaboral>
        obtenerDtOfertasFinalizadasDeEmpresa(
              String nicknameEmpresa)
              throws UsuarioNoExisteException, IOException;

  List<DtEmpresa> buscarEmpresas(String parametro)
        throws IOException;

  public DtDatosPdf obtenerDatosPdf(
        String nicknamePostulante, String nombreOferta)
        throws OfertaLaboralNoExisteException,
        UsuarioNoExisteException;

}
