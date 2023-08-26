package testing;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import excepciones.OfertaLaboralNoExisteException;
import excepciones.OfertaLaboralYaExisteException;
import excepciones.TipoPublicacionNoExisteException;
import excepciones.TipoPublicacionYaExisteException;
import excepciones.UsuarioEmailRepetidoException;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioYaExisteException;
import junit.framework.Assert;
import logica.DataTypes.DTUsuario;
import logica.classes.Empresa;
import logica.classes.Postulante;
import logica.classes.Usuario;
import logica.controllers.Fabrica;
import logica.handlers.ManejadorOfertas;
import logica.handlers.ManejadorSettings;
import logica.handlers.ManejadorUsuario;
import logica.interfaces.IControladorOferta;
import logica.interfaces.IControladorUsuario;

public class ControladorUsuarioTesting {
	
	private static Date fechaDate1;
	private static Date fechaDate2;
	private static IControladorUsuario controladorUsuario;
	private static IControladorOferta controladorOferta;
	private static Postulante postulante1;
	private static Postulante postulante2;
	private static Empresa empresa1;
	private static  Empresa empresa2;
	private static ManejadorUsuario manejadorUsuario;
	private static ManejadorOfertas manejadorOfertas;
	private static ManejadorSettings manejadorSettings;
	
	@BeforeClass
	public static void setUp()
	{
		controladorUsuario = Fabrica.getInstance().obtenerControladorUsuario();
		controladorOferta = Fabrica.getInstance().obtenerControladorOferta();
		manejadorUsuario = ManejadorUsuario.getInstance();
		manejadorOfertas = ManejadorOfertas.getInstance();
		manejadorSettings = ManejadorSettings.getInstance();
		String fecha1 = "02/09/1988";
		String fecha2 = "04/4/2023";
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try
		{
			fechaDate1 = dateFormat.parse(fecha1);
			fechaDate2 = dateFormat.parse(fecha2);
		}
		catch (ParseException e)
		{
			e.getMessage();
		}
		postulante1 = new Postulante("NicknameTest", "NombreTest", "ApellidoTest", "EmailTest", fechaDate1, "NacionalidadTest" );
		postulante2 = new Postulante("NicknameTest2", "NombreTest2", "ApellidoTest2", "EmailTest2", fechaDate2, "NacionalidadTest2" );
		empresa1 = new Empresa("nicknameEmpresa1", "nombre1", "apellido1", "email1", "descripcion1", "sitioWeb1" );
		empresa2 = new Empresa("nicknameEmpresa2", "nombre2", "apellido2", "email2", "descripcion2", "sitioWeb2");
		
	}
	
	@Before
	public void cleanUp() {
		manejadorUsuario.clean();
		manejadorOfertas.clean();
		manejadorSettings.clean();
	}
	
	@Test
	public void listarUsuuariosListaVacia() {
		ArrayList<String> lista = controladorUsuario.listaDeUsuarios();
		Assert.assertTrue(lista.isEmpty());
	}

	@Test
	public void obtenerUsuarioTest() throws UsuarioYaExisteException, UsuarioNoExisteException, UsuarioEmailRepetidoException {
		controladorUsuario.altaPostulante("NicknameTest", "NombreTest", "ApellidoTest", "EmailTest", fechaDate1, "NacionalidadTest" );
		Usuario usuarioResultado = controladorUsuario.obtenerUsuario("NicknameTest");
		Assert.assertEquals("NicknameTest", usuarioResultado.getNickname());
		Assert.assertEquals("NombreTest", usuarioResultado.getNombre());
		Assert.assertEquals("ApellidoTest", usuarioResultado.getApellido());
		Assert.assertEquals("EmailTest", usuarioResultado.getEmail());
	}
	
	@Test
	public void obtenerUsuarioManejadorVacio() throws UsuarioNoExisteException {		
		try{
			controladorUsuario.obtenerUsuario("Carlitos");
		}catch(UsuarioNoExisteException e)
		{
			Assert.assertEquals("Usuario: Carlitos no existe", e.getMessage());
		}
	}
	
	@Test
	public void obtenerDtUsuario() throws UsuarioYaExisteException, UsuarioNoExisteException, UsuarioEmailRepetidoException {
		controladorUsuario.altaPostulante("NicknameTest", "NombreTest", "ApellidoTest", "EmailTest", fechaDate1, "NacionalidadTest" );
		DTUsuario dtResultado = controladorUsuario.obtenerDTUsuario("NicknameTest");
		Assert.assertEquals("NicknameTest", dtResultado.getNickname());
		Assert.assertEquals("NombreTest", dtResultado.getNombre());
		Assert.assertEquals("ApellidoTest", dtResultado.getApellido());
		Assert.assertEquals("EmailTest", dtResultado.getEmail());
	}
	
	@Test
	public void altaPostulanteTest() throws UsuarioYaExisteException, UsuarioNoExisteException, UsuarioEmailRepetidoException {
		
		controladorUsuario.altaPostulante("NicknameTest", "NombreTest", "ApellidoTest", "EmailTest", fechaDate1, "NacionalidadTest" );
		Postulante postulanteResultado = controladorUsuario.obtenerPostulante("NicknameTest");
		Assert.assertEquals(postulante1.getNickname(), postulanteResultado.getNickname());
		Assert.assertEquals(postulante1.getNombre(), postulanteResultado.getNombre());
		Assert.assertEquals(postulante1.getApellido(), postulanteResultado.getApellido());
		Assert.assertEquals(postulante1.getEmail(), postulanteResultado.getEmail());
		Assert.assertEquals(postulante1.getFechaNacimiento().toString(),postulanteResultado.getFechaNacimiento().toString());
		Assert.assertEquals(postulante1.getNacionalidad(), postulanteResultado.getNacionalidad());
	}
	
	@Test
	public void listarUsuariosUsuarioUnicoTest() throws UsuarioYaExisteException, UsuarioEmailRepetidoException {
		controladorUsuario.altaPostulante("NicknameTest", "NombreTest", "ApellidoTest", "EmailTest", fechaDate1, "NacionalidadTest" );
		ArrayList<String> listaEsperada = new ArrayList<String>();
		listaEsperada.add("NicknameTest");
		ArrayList<String> listaResultado = controladorUsuario.listaDeUsuarios();
		Assert.assertEquals(listaEsperada, listaResultado);

	}
	
	@Test
	public void listarUsuariosTest() throws UsuarioYaExisteException, UsuarioEmailRepetidoException {
		controladorUsuario.altaPostulante("NicknameTest", "NombreTest", "ApellidoTest", "EmailTest", fechaDate1, "NacionalidadTest");
		controladorUsuario.altaPostulante("NicknameTest2", "NombreTest2", "ApellidoTest2", "EmailTest2", fechaDate2, "NacionalidadTest2");
		ArrayList<String> listaResultado = controladorUsuario.listaDeUsuarios();
		Collections.sort(listaResultado);
		ArrayList<String> listaEsperada = new ArrayList<String>();
		listaEsperada.add("NicknameTest");
		listaEsperada.add("NicknameTest2");
		Collections.sort(listaEsperada);
		Assert.assertEquals(listaEsperada, listaResultado);
	}
	
	@Test
	public void obtenerEmpresaTest() throws UsuarioYaExisteException, UsuarioEmailRepetidoException, UsuarioNoExisteException {
		controladorUsuario.altaEmpresa("nicknameEmpresa1", "nombre1", "apellido1", "email1", "descripcion1", "sitioWeb1");
		Empresa empresaResultado = controladorUsuario.obtenerEmpresa("nicknameEmpresa1");
		Assert.assertEquals(empresa1.getNickname(), empresaResultado.getNickname());
		Assert.assertEquals(empresa1.getNombre(), empresaResultado.getNombre());
		Assert.assertEquals(empresa1.getApellido(), empresaResultado.getApellido());
		Assert.assertEquals(empresa1.getEmail(), empresaResultado.getEmail());
		Assert.assertEquals(empresa1.getDescripcion(), empresaResultado.getDescripcion());
		Assert.assertEquals(empresa1.getSitioWeb(), empresaResultado.getSitioWeb());
		
		
	}
	
	@Test
	public void listarEmpresasTest() throws UsuarioYaExisteException, UsuarioEmailRepetidoException {
		controladorUsuario.altaEmpresa("nicknameEmpresa1", "nombre1", "apellido1", "email1", "descripcion1", "sitioWeb1");
		controladorUsuario.altaEmpresa("nicknameEmpresa2", "nombre2", "apellido2", "email2", "descripcion2", "sitioWeb2");
		ArrayList<String> listaResultado = controladorUsuario.listarEmpresas();
		ArrayList<String> listaEsperada = new ArrayList<String>();
		listaEsperada.add("nicknameEmpresa1");
		listaEsperada.add("nicknameEmpresa2");		
		Collections.sort(listaResultado);
		Collections.sort(listaEsperada);
		Assert.assertEquals(listaEsperada, listaResultado);
		
	}
	
	@Test
	public void listarPostulantesTest() throws UsuarioYaExisteException, UsuarioEmailRepetidoException {
		controladorUsuario.altaPostulante("NicknameTest", "NombreTest", "ApellidoTest", "EmailTest", fechaDate1, "NacionalidadTest");
		controladorUsuario.altaPostulante("NicknameTest2", "NombreTest2", "ApellidoTest2", "EmailTest2", fechaDate2, "NacionalidadTest2");
		ArrayList<String> listaResultado = controladorUsuario.listarPostulantes();
		Collections.sort(listaResultado);
		ArrayList<String> listaEsperada = new ArrayList<String>();
		listaEsperada.add("NicknameTest");
		listaEsperada.add("NicknameTest2");
		Collections.sort(listaEsperada);
		Assert.assertEquals(listaEsperada, listaResultado);
	}
	
	@Test
	public void editarDatosBasicosDeUsuarioTest() throws UsuarioYaExisteException, UsuarioNoExisteException, UsuarioEmailRepetidoException {
		controladorUsuario.altaPostulante("NicknameTest", "NombreTest", "ApellidoTest", "EmailTest", fechaDate1, "NacionalidadTest");
		DTUsuario dtUsuario = postulante1.obtenerDTUsuario();
		controladorUsuario.editarDatosBasicos(dtUsuario, "nombreNuevo", "apellidoNuevo");
		Usuario resultado = controladorUsuario.obtenerUsuario("NicknameTest");
		Assert.assertEquals("nombreNuevo", resultado.getNombre());
		Assert.assertEquals("apellidoNuevo", resultado.getApellido());
		
	}
	
	@Test
	public void listarOfertasDeEmpresaTest() throws UsuarioYaExisteException, UsuarioEmailRepetidoException, OfertaLaboralYaExisteException, UsuarioNoExisteException, OfertaLaboralNoExisteException, TipoPublicacionNoExisteException, TipoPublicacionYaExisteException {
		controladorUsuario.altaEmpresa("EcoTech", "Sophia", "Johnson", "info@EcoTech.com", 
				"EcoTech Innovations es una empresa líder en soluciones tecnológicas sostenibles. Nuestro enfoque se centra en desarrollar y comercializar productos y servicios que aborden los desafíos ambientales más apremiantes de nuestro tiempo. Desde sistemas de energía renovable y dispositivos de monitorización ambiental hasta soluciones de gestión de residuos inteligentes, nuestra misión es proporcionar herramientas que permitan a las empresas y comunidades adoptar prácticas más ecológicas sin comprometer la eficiencia. Creemos en la convergencia armoniosa entre la tecnología la naturaleza, y trabajamos incansablemente para impulsar un futuro más limpio y sostenible.",
				"http://www.EcoTechInnovations.com");
		controladorOferta.altaTipoPublicacion("Premium", "Obtén máxima visibilidad", "1", 30, 4000f, fechaDate2);
		controladorOferta.altaOfertaLaboral("Desarrollador Frontend", "Únete a nuestro equipo de desarrollo frontend y crea experiencias de usuario excepcionales.", "09:00", "18:00", 90000f, "Montevideo", "Montevideo", fechaDate1,controladorOferta.obtenerTipoPublicacion("Premium"));
		controladorUsuario.obtenerEmpresa("EcoTech").agregarOferta(controladorOferta.obtenerOfertaLaboral("Desarrollador Frontend"));
		ArrayList<String> resultado = controladorUsuario.obtenerOfertasEmpresa("EcoTech");
		ArrayList<String> esperado = new ArrayList<String>();
		esperado.add("Desarrollador Frontend");
		Assert.assertEquals(esperado, resultado);
	}
	
	@Test 
	public void listarOfertasDeEmpresaVaciaTest() throws UsuarioYaExisteException, UsuarioEmailRepetidoException, UsuarioNoExisteException {
		controladorUsuario.altaEmpresa("EcoTech", "Sophia", "Johnson", "info@EcoTech.com", 
				"EcoTech Innovations es una empresa líder en soluciones tecnológicas sostenibles. Nuestro enfoque se centra en desarrollar y comercializar productos y servicios que aborden los desafíos ambientales más apremiantes de nuestro tiempo. Desde sistemas de energía renovable y dispositivos de monitorización ambiental hasta soluciones de gestión de residuos inteligentes, nuestra misión es proporcionar herramientas que permitan a las empresas y comunidades adoptar prácticas más ecológicas sin comprometer la eficiencia. Creemos en la convergencia armoniosa entre la tecnología la naturaleza, y trabajamos incansablemente para impulsar un futuro más limpio y sostenible.",
				"http://www.EcoTechInnovations.com");
		ArrayList<String> resultado = controladorUsuario.obtenerOfertasEmpresa("EcoTech");
		ArrayList<String> esperado = new ArrayList<String>();
		Assert.assertEquals(esperado, resultado);
	}
	
	@Test
	public void registrarPostulacionTest() throws UsuarioNoExisteException, OfertaLaboralNoExisteException, UsuarioYaExisteException, UsuarioEmailRepetidoException, TipoPublicacionYaExisteException, OfertaLaboralYaExisteException, TipoPublicacionNoExisteException {
		controladorUsuario.altaEmpresa("EcoTech", "Sophia", "Johnson", "info@EcoTech.com", 
				"EcoTech Innovations es una empresa líder en soluciones tecnológicas sostenibles. Nuestro enfoque se centra en desarrollar y comercializar productos y servicios que aborden los desafíos ambientales más apremiantes de nuestro tiempo. Desde sistemas de energía renovable y dispositivos de monitorización ambiental hasta soluciones de gestión de residuos inteligentes, nuestra misión es proporcionar herramientas que permitan a las empresas y comunidades adoptar prácticas más ecológicas sin comprometer la eficiencia. Creemos en la convergencia armoniosa entre la tecnología la naturaleza, y trabajamos incansablemente para impulsar un futuro más limpio y sostenible.",
				"http://www.EcoTechInnovations.com");
		controladorOferta.altaTipoPublicacion("Premium", "Obtén máxima visibilidad", "1", 30, 4000f, fechaDate2);
		controladorOferta.altaOfertaLaboral("Desarrollador Frontend", "Únete a nuestro equipo de desarrollo frontend y crea experiencias de usuario excepcionales.", "09:00", "18:00", 90000f, "Montevideo", "Montevideo", fechaDate1,controladorOferta.obtenerTipoPublicacion("Premium"));
		controladorUsuario.obtenerEmpresa("EcoTech").agregarOferta(controladorOferta.obtenerOfertaLaboral("Desarrollador Frontend"));
		controladorUsuario.altaPostulante("maro", "María", "Rodríguez", "marrod@gmail.com", fechaDate2,"Uruguaya");
		controladorUsuario.registrarPostulacion(
				"Ingeniero en Sistemas, experiencia en desarrollo web y aplicaciones móviles. Conocimientos en JavaScript y React.",
				"Me entusiasma la posibilidad de trabajar en proyectos desafiantes y seguir creciendo como profesional en el campo de la tecnología.",
				fechaDate1, "maro", "Desarrollador Frontend");
		ArrayList<String> resultado = controladorUsuario.listaOfertasUsuario("maro");
		ArrayList<String> esperado = new ArrayList<String>();
		esperado.add("Desarrollador Frontend");
		Assert.assertEquals(esperado, resultado);
		
	}
	
	
}
	
