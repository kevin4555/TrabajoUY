package logica.controllers;

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
import java.util.ArrayList;
import java.util.List;
import logica.classes.CompraPaquete;
import logica.classes.Empresa;
import logica.classes.OfertaLaboral;
import logica.classes.PaquetePublicacion;
import logica.classes.Postulacion;
import logica.classes.Postulante;
import logica.classes.Usuario;
import logica.datatypes.DtCompraPaquete;
import logica.datatypes.DtOfertaLaboral;
import logica.datatypes.DtEmpresa;
import logica.datatypes.DtPaquetePublicacion;
import logica.datatypes.DtPostulacion;
import logica.datatypes.DtUsuario;
import logica.handlers.ManejadorUsuario;
import logica.interfaces.IcontroladorOferta;
import logica.interfaces.IcontroladorUsuario;

/**
 * Clase controlador oferta.
 */
public class ControladorUsuario
    implements IcontroladorUsuario {
  
  @Override
  public Empresa obtenerEmpresa(String nicknameEmpresa)
      throws UsuarioNoExisteException {
    ManejadorUsuario manejUsu = ManejadorUsuario
        .getInstance();
    Empresa emp = manejUsu.obtenerEmpresa(nicknameEmpresa);
    return emp;
  }
  
  @Override
  public List<String> listarEmpresas() {
    ManejadorUsuario manejUsu = ManejadorUsuario
        .getInstance();
    List<String> nomEmpresas = manejUsu.listarEmpresas();
    return nomEmpresas;
  }
  
  @Override
  public List<String> listaDeUsuarios() {
    ManejadorUsuario manejadorUsuarios = ManejadorUsuario
        .getInstance();
    return manejadorUsuarios.listarUsuarios();
  }
  
  @Override
  public void editarDatosBasicos(DtUsuario dtusuario,
      String nombreNuevo, String apellidoNuevo)
      throws UsuarioNoExisteException {
    ManejadorUsuario manejadorUsuario = ManejadorUsuario
        .getInstance();
    Usuario usuario = manejadorUsuario
        .obtenerUsuario(dtusuario.getNickname());
    usuario.setApellido(apellidoNuevo);
    usuario.setNombre(nombreNuevo);
    // si se necesitan cambiar mas datos hay que hacer
    // alguna magia para distinguir
    // la empresa del postulante
  }
  
  @Override
  public List<String> obtenerOfertasEmpresa(
      String nicknameEmpresa)
      throws UsuarioNoExisteException {
    ManejadorUsuario manejadorUsuarios = ManejadorUsuario
        .getInstance();
    Empresa empr = manejadorUsuarios
        .obtenerEmpresa(nicknameEmpresa);
    return empr.obtenerNombresOfertas();
    
  }
  
  @Override
  public List<String> listarPostulantes() {
    ManejadorUsuario manejadorUsuarios = ManejadorUsuario
        .getInstance();
    return manejadorUsuarios.listarPostulantes();
  }
  
  @Override
  public void registrarPostulacion(String cvReducido,
      String motivacion, LocalDate fechaPostulacion,
      String nickname, String nomOferta, String linkVideo)
      throws UsuarioNoExisteException,
      OfertaLaboralNoExisteException,
      UsuarioYaExistePostulacion {
    ManejadorUsuario manejadorUsuario = ManejadorUsuario
        .getInstance();
    Postulante postulante = manejadorUsuario
        .obtenerPostulante(nickname);
    if (postulante.estaPostulado(nomOferta)) {
      throw new UsuarioYaExistePostulacion(
          "el usuario ya esta postulado a " + nomOferta);
    }
    Fabrica fabrica = Fabrica.getInstance();
    IcontroladorOferta controladorOferta = fabrica
        .obtenerControladorOferta();
    
    OfertaLaboral oferta = controladorOferta
        .obtenerOfertaLaboral(nomOferta);
    Postulacion postulacion = new Postulacion(motivacion,
        fechaPostulacion, cvReducido, postulante, oferta, linkVideo);
    postulante.agregarPostulacion(postulacion);
    oferta.agregarPostulacion(postulacion);
    
  }
  
  @Override
  public void altaPostulante(String nickname, String nombre,
      String apellido, String email, LocalDate fechaNac,
      String nacionalidad, BufferedImage imagen,
      String constrasenia) throws UsuarioYaExisteException,
      UsuarioEmailRepetidoException {
    ManejadorUsuario manejadorUsuario = ManejadorUsuario
        .getInstance();
    Postulante postulante = new Postulante(nickname, nombre,
        apellido, email.toLowerCase(), fechaNac,
        nacionalidad, imagen, constrasenia);
    manejadorUsuario.agregarPostulante(postulante);
  }
  
  @Override
  public void altaEmpresa(String nickname, String nombre,
      String apellido, String email, String descripcion,
      String link, BufferedImage imagen, String contrasenia)
      throws UsuarioYaExisteException,
      UsuarioEmailRepetidoException {
    ManejadorUsuario manejadorUsuarios = ManejadorUsuario
        .getInstance();
    Empresa empresa = new Empresa(nickname, nombre,
        apellido, email.toLowerCase(), descripcion,
        link.toLowerCase(), imagen, contrasenia);
    manejadorUsuarios.agregarEmpresa(empresa);
    
  }
  
  @Override
  public Usuario obtenerUsuario(String nickname)
      throws UsuarioNoExisteException {
    ManejadorUsuario manejadorUsuario = ManejadorUsuario
        .getInstance();
    Usuario usuario = manejadorUsuario
        .obtenerUsuario(nickname);
    return usuario;
  }
  
  @Override
  public DtUsuario obtenerDtUsuario(String nickname)
      throws UsuarioNoExisteException, IOException {
    Usuario usuario = this.obtenerUsuario(nickname);
    return usuario.obtenerDtusuario();
  }
  
  @Override
  public List<String> listaOfertasUsuario(String nickname)
      throws UsuarioNoExisteException {
    Usuario usuario = this.obtenerUsuario(nickname);
    return usuario.listarNombreOfertasUsuario();
  }
  
  @Override
  public Postulante obtenerPostulante(String nomPostulante)
      throws UsuarioNoExisteException {
    ManejadorUsuario manejadorUsuario = ManejadorUsuario
        .getInstance();
    return manejadorUsuario
        .obtenerPostulante(nomPostulante);
  }
  
  @Override
  public void editarPostulante(String nickname,
      String nombre, String apellido,
      LocalDate fechaNacimiento, String nacionalidad,
      BufferedImage imagen, String contrasenia)
      throws UsuarioNoExisteException {
    Postulante postulante = ManejadorUsuario.getInstance()
        .obtenerPostulante(nickname);
    postulante.setNombre(nombre);
    postulante.setApellido(apellido);
    postulante.setFechaNacimiento(fechaNacimiento);
    postulante.setNacionalidad(nacionalidad);
    postulante.setImagen(imagen);
    postulante.setContrasenia(contrasenia);
  }
  
  @Override
  public void editarEmpresa(String nickname, String nombre,
      String apellido, String sitioWeb, String descripcion,
      BufferedImage imagen, String contrasenia)
      throws UsuarioNoExisteException {
    
    Empresa empresa = ManejadorUsuario.getInstance()
        .obtenerEmpresa(nickname);
    empresa.setNombre(nombre);
    empresa.setApellido(apellido);
    empresa.setSitioWeb(sitioWeb.toLowerCase());
    empresa.setDescripcion(descripcion);
    empresa.setImagen(imagen);
    empresa.setContrasenia(contrasenia);
  }
  
  @Override
  public DtPostulacion obtenerDtPostulacion(
      String nicknamePostulante, String nombreOferta)
      throws UsuarioNoExisteException,
      UsuarioNoExistePostulacion {
    Postulante postulante = ManejadorUsuario.getInstance()
        .obtenerPostulante(nicknamePostulante);
    if (!postulante.estaPostulado(nombreOferta)) {
      throw new UsuarioNoExistePostulacion(
          "el usuario no tiene postulacion a "
              + nombreOferta);
    }
    return postulante.obtenerDtpostulacion(nombreOferta);
  }
  
  @Override
  public List<DtOfertaLaboral> obtenerDtOfertasIngresadasDeEmpresa(
      String nicknameEmpresa)
      throws UsuarioNoExisteException, IOException {
    Empresa empresa = ManejadorUsuario.getInstance()
        .obtenerEmpresa(nicknameEmpresa);
    return empresa.obtenerDtofertasIngresadas();
  }
  
  @Override
  public List<DtOfertaLaboral> obtenerDtOfertasConfirmadasDeEmpresa(
      String nicknameEmpresa)
      throws UsuarioNoExisteException, IOException {
    Empresa empresa = ManejadorUsuario.getInstance()
        .obtenerEmpresa(nicknameEmpresa);
    return empresa.obtenerDtofertasConfirmadas();
  }
  
  @Override
  public List<DtOfertaLaboral> obtenerDtOfertasRechazadasDeEmpresa(
      String nicknameEmpresa)
      throws UsuarioNoExisteException, IOException {
    Empresa empresa = ManejadorUsuario.getInstance()
        .obtenerEmpresa(nicknameEmpresa);
    return empresa.obtenerDtofertasRechazadas();
  }
  
  @Override
  public List<DtOfertaLaboral> obtenerDtOfertasFinalizadasDeEmpresa(String nicknameEmpresa)
      throws UsuarioNoExisteException, IOException {
    Empresa empresa = ManejadorUsuario.getInstance().obtenerEmpresa(nicknameEmpresa);
    return empresa.obtenerDtofertasFinalizadas();
    //agregar persistencia
    
  }
  
  @Override
  public Boolean confirmarContrasenia(String clave,
      String contrasenia) throws UsuarioNoExisteException {
    Usuario usuario = ManejadorUsuario.getInstance()
        .obtenerUsuario(clave);
    return contrasenia.equals(usuario.getContrasenia());
  }
  
  @Override
  public List<DtUsuario> obtenerDtUsuarios()
      throws IOException {
    return ManejadorUsuario.getInstance()
        .obtenerDtusuarios();
  }
  
  @Override
  public void comprarPaquete(String nicknameEmpresa,
      String nombrePaquete, LocalDate fechaCompra)
      throws UsuarioNoExisteException,
      PaquetePublicacionNoExisteException {
    Empresa empresa = ManejadorUsuario.getInstance()
        .obtenerEmpresa(nicknameEmpresa);
    IcontroladorOferta controladorOferta = Fabrica
        .getInstance().obtenerControladorOferta();
    PaquetePublicacion paquete = controladorOferta
        .obtenerPaquetePublicacion(nombrePaquete);
    CompraPaquete compra = new CompraPaquete(fechaCompra,
        paquete);
    empresa.comprarPaquete(compra);
    paquete.setEstaComprado(true);
    
  }
  
  @Override
  public List<DtPaquetePublicacion> obtenerDtPaquetesDeEmpresa(
      String nicknameEmpresa)
      throws UsuarioNoExisteException, IOException {
    Empresa empresa = ManejadorUsuario.getInstance()
        .obtenerEmpresa(nicknameEmpresa);
    return empresa.obtenerDtpaquetes();
    
  }
  
  @Override
  public List<DtPostulacion> obtenerDtPostulacionesDePostulante(
      String nicknamePostulante)
      throws UsuarioNoExisteException {
    Postulante postulante = ManejadorUsuario.getInstance()
        .obtenerPostulante(nicknamePostulante);
    return postulante.obtenerDtpostulaciones();
  }
  
  @Override
  public List<String> listarPaquetesNoCompradosDeEmpresa(
      String nicknameEmpresa)
      throws UsuarioNoExisteException {
    Empresa empresa = ManejadorUsuario.getInstance()
        .obtenerEmpresa(nicknameEmpresa);
    List<String> paquetesComprados = empresa
        .listarPaquetesComprados();
    IcontroladorOferta controladorOfertas = Fabrica
        .getInstance().obtenerControladorOferta();
    List<String> listaResultado = controladorOfertas
        .listarPaquetes();
    for (String paquete : paquetesComprados) {
      listaResultado.remove(paquete);
    }
    return listaResultado;
  }
  
  @Override
  public List<DtCompraPaquete> obtenerDtCompraPaqueteDeEmpresa(
      String nicknameEmpresa)
      throws UsuarioNoExisteException, IOException {
    Empresa empresa = ManejadorUsuario.getInstance()
        .obtenerEmpresa(nicknameEmpresa);
    return empresa.obtenerDtCompraPaquetes();
  }
  
  @Override
  public void agregarSeguidor(String nicknameUsuario, String nicknameSeguidor)
      throws UsuarioNoExisteException, UsuarioYaEstaSeguidoException {
    Usuario usuario = ManejadorUsuario.getInstance().obtenerUsuario(nicknameUsuario);
    if (usuario.estaSeguidoPor(nicknameSeguidor)) {
      throw new UsuarioYaEstaSeguidoException(
          "El usuario " + nicknameUsuario + " ya está seguido por " + nicknameSeguidor);
    }
    Usuario seguidor = ManejadorUsuario.getInstance().obtenerUsuario(nicknameSeguidor);
    usuario.agregarSeguidor(nicknameSeguidor);
    seguidor.seguir(nicknameUsuario);
  }
  
  @Override
  public void dejarDeSeguir(String nicknameUsuario, String nicknameSeguidor)
      throws UsuarioNoExisteException, UsuarioNoEstaSeguidoException {
    Usuario usuario = ManejadorUsuario.getInstance().obtenerUsuario(nicknameUsuario);
    if (!usuario.estaSeguidoPor(nicknameSeguidor)) {
      throw new UsuarioNoEstaSeguidoException(
          "El usuario " + nicknameUsuario + " no está seguido por " + nicknameSeguidor);
    }
    Usuario seguidor = ManejadorUsuario.getInstance().obtenerUsuario(nicknameSeguidor);
    usuario.removerSeguidor(nicknameSeguidor);
    seguidor.dejarDeSeguir(nicknameUsuario);
  }
  
  @Override
  public void agregarOfertaFavorita(String nicknamePostulante, String nombreOferta)
      throws UsuarioNoExisteException, PostulanteYaEsOfertaFavoritaException,
      OfertaLaboralNoExisteException {
    Postulante postulante = ManejadorUsuario.getInstance()
        .obtenerPostulante(nicknamePostulante);
    if (postulante.esOfertaFavorita(nombreOferta)) {
      throw new PostulanteYaEsOfertaFavoritaException("El postulante " + nicknamePostulante
          + " ya tiene como favorita la oferta " + nombreOferta);
    }
    IcontroladorOferta controladorOferta = Fabrica.getInstance().obtenerControladorOferta();
    if (!controladorOferta.existeOfertaLaboral(nombreOferta)) {
      throw new OfertaLaboralNoExisteException(
          "La oferta laboral " + nombreOferta + " no existe");
    }
    postulante.agregarOfertaFavorita(nombreOferta);
  }
  
  @Override
  public void removerOfertaFavorita(String nicknamePsotulante, String nombreOferta)
      throws UsuarioNoExisteException, PostulanteNoEsOfertaFavoritaException {
    Postulante postulante = ManejadorUsuario.getInstance()
        .obtenerPostulante(nicknamePsotulante);
    if (!postulante.esOfertaFavorita(nombreOferta)) {
      throw new PostulanteNoEsOfertaFavoritaException(
          "La oferta " + nombreOferta + " no es una oferta favorita de " + nicknamePsotulante);
    }
    postulante.removerOfertaFavorita(nombreOferta);
  }
  
  @Override
  public List<DtEmpresa> buscarEmpresas(String parametro) throws IOException{
    List<DtEmpresa> listaResultado = new ArrayList<DtEmpresa>();
    List<DtEmpresa> dtempresas = ManejadorUsuario.getInstance().obtenerDtEmpresas();
    for(DtEmpresa empresa : dtempresas) {
      if(empresa.getNombre().contains(parametro) || empresa.getDescripcion().contains(parametro)) {
        listaResultado.add(empresa);
      }
    }
    //agregar orden cuando quede aclarado
    return listaResultado;
  }
}
