package logica.webservices;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;

import excepciones.KeywordNoExisteException;
import excepciones.KeywordYaExisteException;
import excepciones.OfertaLaboralNoExisteException;
import excepciones.OfertaLaboralNoSePuedeFinalizar;
import excepciones.OfertaLaboralNoTienePaquete;
import excepciones.OfertaLaboralYaExisteException;
import excepciones.PaquetePublicacionNoExisteException;
import excepciones.PaquetePublicacionYaExisteException;
import excepciones.PaquetePublicacionYaFueComprado;
import excepciones.PostulanteNoEsOfertaFavoritaException;
import excepciones.PostulanteYaEsOfertaFavoritaException;
import excepciones.TipoDePublicacionYaFueIngresado;
import excepciones.TipoPublicacionNoExisteException;
import excepciones.TipoPublicacionYaExisteException;
import excepciones.UsuarioEmailRepetidoException;
import excepciones.UsuarioNoEstaSeguidoException;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioNoExistePostulacion;
import excepciones.UsuarioYaEstaSeguidoException;
import excepciones.UsuarioYaExisteException;
import excepciones.UsuarioYaExistePostulacion;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;
import logica.controllers.Fabrica;
import logica.datatypes.DtCantidadTipoPublicacion;
import logica.datatypes.DtCompraPaquete;
import logica.datatypes.DtDatosPdf;
import logica.datatypes.DtEmpresa;
import logica.datatypes.DtOfertaLaboral;
import logica.datatypes.DtPaquetePublicacion;
import logica.datatypes.DtPostulacion;
import logica.datatypes.DtTipoPublicacion;
import logica.datatypes.DtUsuario;
import logica.interfaces.IcontroladorOferta;
import logica.interfaces.IcontroladorUsuario;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class Publicador {
	private Fabrica factory = Fabrica.getInstance();
	private IcontroladorOferta controladorOferta = factory.obtenerControladorOferta();
	private IcontroladorUsuario controladorUsuario = factory.obtenerControladorUsuario();
	private Endpoint endpoint = null;

	private String UrlBase = "http://localhost:8085"; // ToDo : Cambiar por lo que es te en el archivo de propiedades

	// Constructor
	public Publicador() {
	}
	// Operaciones las cuales quiero publicar

	@WebMethod(exclude = true)
	public void publicar() {
		endpoint = Endpoint.publish(UrlBase + "/webservices", this);
	}

	@WebMethod(exclude = true)
	public Endpoint getEndpoint() {
		return endpoint;
	}

	@WebMethod
	public String[] listarTipoDePublicaciones() {
		return controladorOferta.listarTipoDePublicaciones().toArray(new String[0]);
	}

 @WebMethod
 public void altaOfertaLaboral(String nombre, String descripcion, String horarioInicial,
     String horarioFinal, float remuneracion, String ciudad, String departamento,
     String fechaAlta, String nomTipoPublicacion, String nicknameEmpresa,
     String[] listakeywords, String imagen, String nombrePaquete)
     throws OfertaLaboralYaExisteException, TipoPublicacionNoExisteException,
     KeywordNoExisteException, UsuarioNoExisteException {
   
   LocalDate fechaAltaLocalDate = LocalDate.parse(fechaAlta);
   List<String> listaDeElementosKeywords = new ArrayList<>();
   for (String elemento : listakeywords) {
     listaDeElementosKeywords.add(elemento);
   }
   BufferedImage imagenBufferedImage = null;
   if (!imagen.equals("")) {
     imagenBufferedImage = base64StringToImage(imagen);
   }
   if (!nombrePaquete.equals("")) {
     nombrePaquete = null;
   }
   
   controladorOferta.altaOfertaLaboral(nombre, descripcion, horarioInicial, horarioFinal,
       remuneracion, ciudad, departamento, fechaAltaLocalDate, nomTipoPublicacion,
       nicknameEmpresa, listaDeElementosKeywords,	imagenBufferedImage, nombrePaquete);
	}

	@WebMethod
	public void agregarTipoPublicacionAlPaquete(int cantIncluida, String nomTipoPublicacion, String nomTipoPaquete)
			throws TipoPublicacionNoExisteException, PaquetePublicacionNoExisteException,
			PaquetePublicacionYaFueComprado, TipoDePublicacionYaFueIngresado {
		controladorOferta.agregarTipoPublicacionAlPaquete(cantIncluida, nomTipoPublicacion, nomTipoPaquete);
	}

	@WebMethod
	public String[] listarPaquetes() {
		return controladorOferta.listarPaquetes().toArray(new String[0]);
	}

	@WebMethod
	public void altaTipoPublicacion(String nombre, String descripcion, String exposicion, int duracion, float costo,
			String fechaPub) throws TipoPublicacionYaExisteException {
		LocalDate fecha = LocalDate.parse(fechaPub);
		controladorOferta.altaTipoPublicacion(nombre, descripcion, exposicion, duracion, costo, fecha);
	}

	@WebMethod
	public void altaKeyword(String nomKeyword) throws KeywordYaExisteException {
		controladorOferta.altaKeyword(nomKeyword);
	}

	@WebMethod
	public String[] listarKeywords() {
		return controladorOferta.listarKeywords().toArray(new String[0]);
	}

	@WebMethod
	public DtOfertaLaboral obtenerDtOfertaLaboral(String nomOferta) throws OfertaLaboralNoExisteException, IOException {
		return controladorOferta.obtenerDtOfertaLaboral(nomOferta);
	}

	@WebMethod
	public String[] obtenerOfertasEmpresa(String nicknameEmpresa) throws UsuarioNoExisteException {
		return controladorOferta.obtenerOfertasEmpresa(nicknameEmpresa).toArray(new String[0]);
	}

	@WebMethod
	public void registrarPaquete(String nombre, String descripcion, int periodoValDias, float descuento,
			BufferedImage imagen, String fechaAlta, DtCantidadTipoPublicacion[] cantidadTipoPublicacion)
			throws PaquetePublicacionYaExisteException, TipoPublicacionYaExisteException,
			TipoPublicacionNoExisteException {
		List<DtCantidadTipoPublicacion> listaDeDtcantidadTipoPublicacion = new ArrayList<DtCantidadTipoPublicacion>();
		for (DtCantidadTipoPublicacion elemento : cantidadTipoPublicacion) {
			listaDeDtcantidadTipoPublicacion.add(elemento);
		}
		LocalDate fecha = LocalDate.parse(fechaAlta);
		controladorOferta.registrarPaquete(nombre, descripcion, periodoValDias, descuento, imagen, fecha,
				listaDeDtcantidadTipoPublicacion);
	}

	@WebMethod
	public boolean estaPostulado(String postulante, String nomOfertaLaboral)
			throws UsuarioNoExisteException, OfertaLaboralNoExisteException {
		return controladorOferta.estaPostulado(postulante, nomOfertaLaboral);
	}

	@WebMethod
	public String[] obtenerKeywordsDeOfertaLaboral(String nomOfertaLab) throws OfertaLaboralNoExisteException {
		return controladorOferta.obtenerKeywordsDeOfertaLaboral(nomOfertaLab).toArray(new String[0]);
	}

	

	@WebMethod
	public DtOfertaLaboral[] obtenerDtOfertasConfirmadas() throws IOException {
		return controladorOferta.obtenerDtOfertasConfirmadas().toArray(new DtOfertaLaboral[0]);
	}

	@WebMethod
	public DtOfertaLaboral[] obtenerDtOfertasPorKeyword(String keyword) throws IOException {
		return controladorOferta.obtenerDtOfertasPorKeyword(keyword).toArray(new DtOfertaLaboral[0]);
	}

	@WebMethod
	public DtPostulacion[] obtenerDtPostulacionesDeOferta(String nombreOferta) throws OfertaLaboralNoExisteException {
		return controladorOferta.obtenerDtPostulacionesDeOferta(nombreOferta).toArray(new DtPostulacion[0]);
	}

	@WebMethod
	public boolean estaCompradoPorPaqueteOferta(String nombreOferta) throws OfertaLaboralNoExisteException {
		return controladorOferta.estaCompradoPorPaqueteOferta(nombreOferta);
	}

	@WebMethod
	public DtPaquetePublicacion obtenerDtPaquetePublicacion(String nombreOferta)
			throws OfertaLaboralNoExisteException, OfertaLaboralNoTienePaquete, IOException {
		return controladorOferta.obtenerDtPaquetePublicacion(nombreOferta);
	}

	@WebMethod
	public String[] listarTipoPublicacionDePaquete(String nombrePaquete) throws PaquetePublicacionNoExisteException {
		return controladorOferta.listarTipoDePublicaciones().toArray(new String[0]);
	}

	@WebMethod
	public DtPaquetePublicacion obtenerDtpaquete(String nombrePaquete)
			throws PaquetePublicacionNoExisteException, IOException {
		return controladorOferta.obtenerDtPaquete(nombrePaquete);
	}

	@WebMethod
	public DtTipoPublicacion obtenerDtTipoPublicacion(String nombreTipo) throws TipoPublicacionNoExisteException {
		return controladorOferta.obtenerDtTipoPublicacion(nombreTipo);
	}

	@WebMethod
	public String[] listarPaquetesNoComprados() {
		return controladorOferta.listarPaquetesNoComprados().toArray(new String[0]);
	}

	@WebMethod
	public DtPaquetePublicacion[] listarDtPaquetes() throws IOException {
		return controladorOferta.listarDtPaquetes().toArray(new DtPaquetePublicacion[0]);
	}

	@WebMethod
	public Boolean estaCompradoPaquete(String nombrePaquete) throws PaquetePublicacionNoExisteException {
		return controladorOferta.estaCompradoPaquete(nombrePaquete);
	}
	
	@WebMethod
	public String[] listarEmpresas() {
		return controladorUsuario.listarEmpresas().toArray(new String[0]);
	}

	@WebMethod
	public String[] listaDeUsuarios() {
		return controladorUsuario.listaDeUsuarios().toArray(new String[0]);
	}

	@WebMethod
	public String[] obtenerOfertasEmpresaUsuario(String nicknameEmpresa) throws UsuarioNoExisteException {
		return controladorUsuario.obtenerOfertasEmpresaUsuario(nicknameEmpresa).toArray(new String[0]);
	}

	@WebMethod
	public String[] listarPostulantes() {
		return controladorUsuario.listarPostulantes().toArray(new String[0]);
	}

	@WebMethod
	public void registrarPostulacion(String cvReducido, String motivacion, String fechaPostulacion, String nickname,
			String nomOferta, String linkVideo)
			throws UsuarioNoExisteException, OfertaLaboralNoExisteException, UsuarioYaExistePostulacion {
		LocalDate fecha = LocalDate.parse(fechaPostulacion);
		controladorUsuario.registrarPostulacion(cvReducido, motivacion, fecha, nickname, nomOferta, linkVideo);
	}

	@WebMethod
	public void altaPostulante(String nickname, String nombre, String apellido, String email, String fechaNac,
			String nacionalidad, String imagen, String constrasenia)
			throws UsuarioYaExisteException, UsuarioEmailRepetidoException {
		LocalDate fecha = LocalDate.parse(fechaNac);

		controladorUsuario.altaPostulante(nickname, nombre, apellido, email, fecha, nacionalidad,
				base64StringToImage(imagen), constrasenia);
	}

	@WebMethod
	public void altaEmpresa(String nickname, String nombre, String apellido, String email, String descripcion,
			String link, String imagen, String contrasenia)
			throws UsuarioYaExisteException, UsuarioEmailRepetidoException {

		BufferedImage imagenBufferImage = base64StringToImage(imagen);
		controladorUsuario.altaEmpresa(nickname, nombre, apellido, email, descripcion, link, imagenBufferImage,
				contrasenia);
	}
	
	@WebMethod
	public DtUsuario obtenerDtUsuario(String nickname) throws UsuarioNoExisteException, IOException {
		return controladorUsuario.obtenerDtUsuario(nickname);
	}

	@WebMethod
	public String[] listaOfertasUsuario(String nickname) throws UsuarioNoExisteException {
		return controladorUsuario.listaOfertasUsuario(nickname).toArray(new String[0]);
	}

	@WebMethod
	public void editarPostulante(String nickname, String nombre, String apellido, String fechaNacimiento,
			String nacionalidad, String imagen, String contrasenia) throws UsuarioNoExisteException {
		LocalDate fecha = LocalDate.parse(fechaNacimiento);
		BufferedImage imagenBufferImage = base64StringToImage(imagen);
		controladorUsuario.editarPostulante(nickname, nombre, apellido, fecha, nacionalidad, imagenBufferImage, contrasenia);
	}

	@WebMethod
	public void editarEmpresa(String nickname, String nombre, String apellido, String sitioWeb, String descripcion,
	    String imagen, String contrasenia) throws UsuarioNoExisteException {
	  BufferedImage imagenBufferImage = base64StringToImage(imagen);
		controladorUsuario.editarEmpresa(nickname, nombre, apellido, sitioWeb, descripcion, imagenBufferImage, contrasenia);
	}

	@WebMethod
	public DtPostulacion obtenerDtPostulacion(String nicknamePostulante, String nombreOferta)
			throws UsuarioNoExisteException, UsuarioNoExistePostulacion {
		return controladorUsuario.obtenerDtPostulacion(nicknamePostulante, nombreOferta);
	}

	@WebMethod
	public DtOfertaLaboral[] obtenerDtOfertasIngresadasDeEmpresa(String nicknameEmpresa)
			throws UsuarioNoExisteException, IOException {
		return controladorUsuario.obtenerDtOfertasIngresadasDeEmpresa(nicknameEmpresa).toArray(new DtOfertaLaboral[0]);
	}

	@WebMethod
	public DtOfertaLaboral[] obtenerDtOfertasConfirmadasDeEmpresa(String nicknameEmpresa)
			throws UsuarioNoExisteException, IOException {
		return controladorUsuario.obtenerDtOfertasConfirmadasDeEmpresa(nicknameEmpresa).toArray(new DtOfertaLaboral[0]);
	}

	@WebMethod
	public DtOfertaLaboral[] obtenerDtOfertasRechazadasDeEmpresa(String nicknameEmpresa)
			throws UsuarioNoExisteException, IOException {
		return controladorUsuario.obtenerDtOfertasRechazadasDeEmpresa(nicknameEmpresa).toArray(new DtOfertaLaboral[0]);
	}

	@WebMethod
	public Boolean confirmarContrasenia(String clave, String contrasenia) throws UsuarioNoExisteException {
		return controladorUsuario.confirmarContrasenia(clave, contrasenia);
	}

	@WebMethod
	public DtUsuario[] obtenerDtUsuarios() throws IOException {
		return controladorUsuario.obtenerDtUsuarios().toArray(new DtUsuario[0]);
	}

	@WebMethod
	public void comprarPaquete(String nicknameEmpresa, String nombrePaquete, String fechaCompra)
			throws UsuarioNoExisteException, PaquetePublicacionNoExisteException {
		LocalDate fecha = LocalDate.parse(fechaCompra);
		controladorUsuario.comprarPaquete(nicknameEmpresa, nombrePaquete, fecha);
	}

	@WebMethod
	public DtPaquetePublicacion[] obtenerDtPaquetesDeEmpresa(String nicknameEmpresa)
			throws UsuarioNoExisteException, IOException {
		return controladorUsuario.obtenerDtPaquetesDeEmpresa(nicknameEmpresa).toArray(new DtPaquetePublicacion[0]);
	}

	@WebMethod
	public DtPostulacion[] obtenerDtPostulacionesDePostulante(String nicknamePostulante)
			throws UsuarioNoExisteException {
		return controladorUsuario.obtenerDtPostulacionesDePostulante(nicknamePostulante).toArray(new DtPostulacion[0]);
	}

	@WebMethod
	public String[] listarPaquetesNoCompradosDeEmpresa(String nicknameEmpresa) throws UsuarioNoExisteException {
		return controladorUsuario.listarPaquetesNoCompradosDeEmpresa(nicknameEmpresa).toArray(new String[0]);
	}

	@WebMethod
	public DtCompraPaquete[] obtenerDtCompraPaqueteDeEmpresa(String nicknameEmpresa)
			throws UsuarioNoExisteException, IOException {
		return controladorUsuario.obtenerDtCompraPaqueteDeEmpresa(nicknameEmpresa).toArray(new DtCompraPaquete[0]);
	}
	
	@WebMethod
	public DtEmpresa[] buscarEmpresas(String parametro) throws IOException {
	  return controladorUsuario.buscarEmpresas(parametro).toArray(new DtEmpresa[0]);
	}
	
	@WebMethod
 public DtOfertaLaboral[] buscarOfertas(String parametro) throws IOException{
	  return controladorOferta.buscarOfertas(parametro).toArray(new DtOfertaLaboral[0]);
	}
	
	public static BufferedImage base64StringToImage(String imageString) {
		try {
			byte[] imageBytes = Base64.getDecoder().decode(imageString);
			ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
			return ImageIO.read(bis);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@WebMethod
	public void agregarSeguidor(String nicknameUsuario, String nicknameSeguidor)
		      throws UsuarioNoExisteException, UsuarioNoEstaSeguidoException, UsuarioYaEstaSeguidoException{
		controladorUsuario.agregarSeguidor(nicknameUsuario, nicknameSeguidor);
	}
	
	@WebMethod
	public void dejarDeSeguir(String nicknameUsuario, String nicknameSeguidor)
		      throws UsuarioNoExisteException, UsuarioNoEstaSeguidoException {
		controladorUsuario.dejarDeSeguir(nicknameUsuario, nicknameSeguidor);
	}
	
	@WebMethod
 public void finalizarOferta(String nombreOferta) throws OfertaLaboralNoExisteException, OfertaLaboralNoSePuedeFinalizar {
  controladorOferta.finalizarOferta(nombreOferta);
 }
	
 @WebMethod
 public DtDatosPdf obtenerDatosPdf(String nicknamePostulante, String nombreOferta)
     throws OfertaLaboralNoExisteException, UsuarioNoExisteException {
   return controladorUsuario.obtenerDatosPdf(nicknamePostulante, nombreOferta);
 }
 
 @WebMethod
 public void agregarVisitaAoferta(String nombreOferta) throws OfertaLaboralNoExisteException {
   controladorOferta.agregarVisitaOferta(nombreOferta);
 }
 
 @WebMethod
 public void agregarOfertaFavorita(String nicknamePostulante, String nombreOferta) throws UsuarioNoExisteException, PostulanteYaEsOfertaFavoritaException, OfertaLaboralNoExisteException {
	 controladorUsuario.agregarOfertaFavorita(nicknamePostulante, nombreOferta);
 }
 @WebMethod
 public void removerOfertaFavorita(String nicknamePostulante, String nombreOferta) throws UsuarioNoExisteException, PostulanteNoEsOfertaFavoritaException {
	 controladorUsuario.removerOfertaFavorita(nicknamePostulante, nombreOferta);
 }
}