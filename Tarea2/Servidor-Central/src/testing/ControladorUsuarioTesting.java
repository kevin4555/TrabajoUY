package testing;

import excepciones.KeywordNoExisteException;
import excepciones.KeywordYaExisteException;
import excepciones.OfertaLaboralNoExisteException;
import excepciones.OfertaLaboralYaExisteException;
import excepciones.PaquetePublicacionNoExisteException;
import excepciones.PaquetePublicacionYaExisteException;
import excepciones.TipoPublicacionNoExisteException;
import excepciones.TipoPublicacionYaExisteException;
import excepciones.UsuarioEmailRepetidoException;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioNoExistePostulacion;
import excepciones.UsuarioYaExisteException;
import excepciones.UsuarioYaExistePostulacion;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import junit.framework.Assert;
import logica.classes.Empresa;
import logica.classes.Postulante;
import logica.classes.Usuario;
import logica.controllers.Fabrica;
import logica.datatypes.DtcantidadTipoPublicacion;
import logica.datatypes.DtOfertaLaboral;
import logica.datatypes.DtpaquetePublicacion;
import logica.datatypes.Dtpostulacion;
import logica.datatypes.Dtusuario;
import logica.datatypes.EstadoOferta;
import logica.handlers.ManejadorOfertas;
import logica.handlers.ManejadorPaquetes;
import logica.handlers.ManejadorSettings;
import logica.handlers.ManejadorUsuario;
import logica.interfaces.IcontroladorOferta;
import logica.interfaces.IcontroladorUsuario;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Clase Controlador Usuario Testing .
 */

public class ControladorUsuarioTesting {
  
  private static LocalDate fechaDate1;
  private static LocalDate fechaDate2;
  private static IcontroladorUsuario controladorUsuario;
  private static IcontroladorOferta controladorOferta;
  private static Postulante postulante1;
  private static Empresa empresa1;
  private static ManejadorUsuario manejadorUsuario;
  private static ManejadorOfertas manejadorOfertas;
  private static ManejadorSettings manejadorSettings;
  private static ManejadorPaquetes manejadorPaquetes;
  
  /**
   * Metodo setUp .
   */
  
  @BeforeClass
  public static void setUp() {
    controladorUsuario = Fabrica.getInstance().obtenerControladorUsuario();
    controladorOferta = Fabrica.getInstance().obtenerControladorOferta();
    manejadorUsuario = ManejadorUsuario.getInstance();
    manejadorOfertas = ManejadorOfertas.getInstance();
    manejadorSettings = ManejadorSettings.getInstance();
    manejadorPaquetes = ManejadorPaquetes.getInstance();
    
    fechaDate1 = LocalDate.parse("1988-11-10");
    fechaDate2 = LocalDate.parse("1988-09-02");
    
    postulante1 = new Postulante("NicknameTest", "NombreTest", "ApellidoTest", "EmailTest",
        fechaDate1, "NacionalidadTest", null, "1234");
    new Postulante("NicknameTest2", "NombreTest2", "ApellidoTest2", "EmailTest2",
        fechaDate2, "NacionalidadTest2", null, "1234");
    empresa1 = new Empresa("nicknameEmpresa1", "nombre1", "apellido1", "email1",
        "descripcion1", "sitioWeb1", null, "1234");
    new Empresa("nicknameEmpresa2", "nombre2", "apellido2", "email2",
        "descripcion2", "sitioWeb2", null, "1234");
    
  }
  
  /**
   * Metodo cleanUp .
   */
  
  @Before
  public void cleanUp() {
    manejadorUsuario.clean();
    manejadorOfertas.clean();
    manejadorSettings.clean();
    manejadorPaquetes.clean();
  }
  
  @Test
  public void listarUsuuariosListaVacia() {
    ArrayList<String> lista = controladorUsuario.listaDeUsuarios();
    Assert.assertTrue(lista.isEmpty());
  }
  
  @Test
  public void obtenerUsuarioTest() throws UsuarioYaExisteException, UsuarioNoExisteException,
      UsuarioEmailRepetidoException {
    controladorUsuario.altaPostulante("NicknameTest", "NombreTest", "ApellidoTest",
        "EmailTest", fechaDate1, "NacionalidadTest", null, "1234");
    Usuario usuarioResultado = controladorUsuario.obtenerUsuario("NicknameTest");
    Assert.assertEquals("NicknameTest", usuarioResultado.getNickname());
    Assert.assertEquals("NombreTest", usuarioResultado.getNombre());
    Assert.assertEquals("ApellidoTest", usuarioResultado.getApellido());
    Assert.assertEquals("EmailTest".toLowerCase(), usuarioResultado.getEmail());
  }
  
  @Test
  public void obtenerUsuarioManejadorVacio() throws UsuarioNoExisteException {
    try {
      controladorUsuario.obtenerUsuario("Carlitos");
    } catch (UsuarioNoExisteException e) {
      Assert.assertEquals("Usuario: Carlitos no existe", e.getMessage());
    }
  }
  
  @Test
  public void obtenerDtUsuario() throws UsuarioYaExisteException, UsuarioNoExisteException,
      UsuarioEmailRepetidoException {
    controladorUsuario.altaPostulante("NicknameTest", "NombreTest", "ApellidoTest",
        "EmailTest", fechaDate1, "NacionalidadTest", null, "1234");
    Dtusuario dtResultado = controladorUsuario.obtenerDtusuario("NicknameTest");
    Assert.assertEquals("NicknameTest", dtResultado.getNickname());
    Assert.assertEquals("NombreTest", dtResultado.getNombre());
    Assert.assertEquals("ApellidoTest", dtResultado.getApellido());
    Assert.assertEquals("EmailTest".toLowerCase(), dtResultado.getEmail());
  }
  
  @Test
  public void altaPostulanteTest() throws UsuarioYaExisteException, UsuarioNoExisteException,
      UsuarioEmailRepetidoException {
    
    controladorUsuario.altaPostulante("NicknameTest", "NombreTest", "ApellidoTest",
        "EmailTest", fechaDate1, "NacionalidadTest", null, "1234");
    Postulante postulanteResultado = controladorUsuario.obtenerPostulante("NicknameTest");
    Assert.assertEquals(postulante1.getNickname(), postulanteResultado.getNickname());
    Assert.assertEquals(postulante1.getNombre(), postulanteResultado.getNombre());
    Assert.assertEquals(postulante1.getApellido(), postulanteResultado.getApellido());
    Assert.assertEquals(postulante1.getEmail().toLowerCase(), postulanteResultado.getEmail());
    Assert.assertEquals(postulante1.getFechaNacimiento().toString(),
        postulanteResultado.getFechaNacimiento().toString());
    Assert.assertEquals(postulante1.getNacionalidad(), postulanteResultado.getNacionalidad());
  }
  
  @Test
  public void listarUsuariosUsuarioUnicoTest()
      throws UsuarioYaExisteException, UsuarioEmailRepetidoException {
    controladorUsuario.altaPostulante("NicknameTest", "NombreTest", "ApellidoTest",
        "EmailTest", fechaDate1, "NacionalidadTest", null, "1234");
    ArrayList<String> listaEsperada = new ArrayList<String>();
    listaEsperada.add("NicknameTest");
    ArrayList<String> listaResultado = controladorUsuario.listaDeUsuarios();
    Assert.assertEquals(listaEsperada, listaResultado);
    
  }
  
  @Test
  public void listarUsuariosTest()
      throws UsuarioYaExisteException, UsuarioEmailRepetidoException {
    controladorUsuario.altaPostulante("NicknameTest", "NombreTest", "ApellidoTest",
        "EmailTest", fechaDate1, "NacionalidadTest", null, "1234");
    controladorUsuario.altaPostulante("NicknameTest2", "NombreTest2", "ApellidoTest2",
        "EmailTest2", fechaDate2, "NacionalidadTest2", null, "1234");
    ArrayList<String> listaResultado = controladorUsuario.listaDeUsuarios();
    Collections.sort(listaResultado);
    ArrayList<String> listaEsperada = new ArrayList<String>();
    listaEsperada.add("NicknameTest");
    listaEsperada.add("NicknameTest2");
    Collections.sort(listaEsperada);
    Assert.assertEquals(listaEsperada, listaResultado);
  }
  
  @Test
  public void obtenerEmpresaTest() throws UsuarioYaExisteException,
      UsuarioEmailRepetidoException, UsuarioNoExisteException {
    controladorUsuario.altaEmpresa("nicknameEmpresa1", "nombre1", "apellido1", "email1",
        "descripcion1", "sitioWeb1", null, "1234");
    Empresa empresaResultado = controladorUsuario.obtenerEmpresa("nicknameEmpresa1");
    Assert.assertEquals(empresa1.getNickname(), empresaResultado.getNickname());
    Assert.assertEquals(empresa1.getNombre(), empresaResultado.getNombre());
    Assert.assertEquals(empresa1.getApellido(), empresaResultado.getApellido());
    Assert.assertEquals(empresa1.getEmail().toLowerCase(), empresaResultado.getEmail());
    Assert.assertEquals(empresa1.getDescripcion(), empresaResultado.getDescripcion());
    Assert.assertEquals(empresa1.getSitioWeb().toLowerCase(), empresaResultado.getSitioWeb());
    
  }
  
  @Test
  public void listarEmpresasTest()
      throws UsuarioYaExisteException, UsuarioEmailRepetidoException {
    controladorUsuario.altaEmpresa("nicknameEmpresa1", "nombre1", "apellido1", "email1",
        "descripcion1", "sitioWeb1", null, "1234");
    controladorUsuario.altaEmpresa("nicknameEmpresa2", "nombre2", "apellido2", "email2",
        "descripcion2", "sitioWeb2", null, "1234");
    ArrayList<String> listaResultado = controladorUsuario.listarEmpresas();
    ArrayList<String> listaEsperada = new ArrayList<String>();
    listaEsperada.add("nicknameEmpresa1");
    listaEsperada.add("nicknameEmpresa2");
    Collections.sort(listaResultado);
    Collections.sort(listaEsperada);
    Assert.assertEquals(listaEsperada, listaResultado);
    
  }
  
  @Test
  public void listarPostulantesTest()
      throws UsuarioYaExisteException, UsuarioEmailRepetidoException {
    controladorUsuario.altaPostulante("NicknameTest", "NombreTest", "ApellidoTest",
        "EmailTest", fechaDate1, "NacionalidadTest", null, "1234");
    controladorUsuario.altaPostulante("NicknameTest2", "NombreTest2", "ApellidoTest2",
        "EmailTest2", fechaDate2, "NacionalidadTest2", null, "1234");
    ArrayList<String> listaResultado = controladorUsuario.listarPostulantes();
    Collections.sort(listaResultado);
    ArrayList<String> listaEsperada = new ArrayList<String>();
    listaEsperada.add("NicknameTest");
    listaEsperada.add("NicknameTest2");
    Collections.sort(listaEsperada);
    Assert.assertEquals(listaEsperada, listaResultado);
  }
  
  @Test
  public void editarDatosBasicosDeUsuarioTest() throws UsuarioYaExisteException,
      UsuarioNoExisteException, UsuarioEmailRepetidoException {
    controladorUsuario.altaPostulante("NicknameTest", "NombreTest", "ApellidoTest",
        "EmailTest", fechaDate1, "NacionalidadTest", null, "1234");
    Dtusuario dtUsuario = postulante1.obtenerDtusuario();
    controladorUsuario.editarDatosBasicos(dtUsuario, "nombreNuevo", "apellidoNuevo");
    Usuario resultado = controladorUsuario.obtenerUsuario("NicknameTest");
    Assert.assertEquals("nombreNuevo", resultado.getNombre());
    Assert.assertEquals("apellidoNuevo", resultado.getApellido());
    
  }
  
  @Test
  public void listarOfertasDeEmpresaTest() throws UsuarioYaExisteException,
      UsuarioEmailRepetidoException, OfertaLaboralYaExisteException, UsuarioNoExisteException,
      OfertaLaboralNoExisteException, TipoPublicacionNoExisteException,
      TipoPublicacionYaExisteException, KeywordNoExisteException, KeywordYaExisteException {
    controladorUsuario.altaEmpresa("EcoTech", "Sophia", "Johnson", "info@EcoTech.com",
        "EcoTech Innovations es una empresa líder en soluciones tecnológicas sostenibles."
        + " Nuestro enfoque se centra en desarrollar y comercializar productos y "
        + "servicios que aborden los desafíos ambientales más apremiantes de nuestro"
        + " tiempo. Desde sistemas de energía renovable y dispositivos de "
        + "monitorización ambiental hasta soluciones de gestión de residuos "
        + "inteligentes, nuestra misión es proporcionar herramientas que "
        + "permitan a las empresas y comunidades adoptar prácticas más "
        + "ecológicas sin comprometer la eficiencia. Creemos en la convergencia"
        + " armoniosa entre la tecnología la naturaleza, y trabajamos "
        + "incansablemente para impulsar un futuro más limpio y sostenible.",
        "http://www.EcoTechInnovations.com", null, "1234");
    controladorOferta.altaTipoPublicacion("Premium", "Obtén máxima visibilidad", "1", 30,
        4000f, fechaDate2);
    controladorOferta.altaKeyword("keyword");
    ArrayList<String> keywords = new ArrayList<String>();
    keywords.add("keyword");
    controladorOferta.altaOfertaLaboral("nombre", "descripcion", "09:00", "18:00", 90000f,
        "Montevideo", "Montevideo", fechaDate1, "Premium", "EcoTech", keywords, null, null);
    ArrayList<String> resultado = controladorUsuario.obtenerOfertasEmpresa("EcoTech");
    ArrayList<String> esperado = new ArrayList<String>();
    esperado.add("nombre");
    Assert.assertEquals(esperado, resultado);
  }
  
  @Test
  public void listarOfertasDeEmpresaVaciaTest() throws UsuarioYaExisteException,
      UsuarioEmailRepetidoException, UsuarioNoExisteException {
    controladorUsuario.altaEmpresa("EcoTech", "Sophia", "Johnson", "info@EcoTech.com",
        "EcoTech Innovations es una empresa líder en soluciones "
        + "tecnológicas sostenibles. Nuestro enfoque se centra en"
        + " desarrollar y comercializar productos y servicios que"
        + " aborden los desafíos ambientales más apremiantes de nuestro"
        + " tiempo. Desde sistemas de energía renovable y dispositivos"
        + " de monitorización ambiental hasta soluciones de gestión de "
        + "residuos inteligentes, nuestra misión es proporcionar herramientas"
        + " que permitan a las empresas y comunidades adoptar prácticas más "
        + "ecológicas sin comprometer la eficiencia. Creemos en la convergencia "
        + "armoniosa entre la tecnología la naturaleza, y trabajamos "
        + "incansablemente para impulsar un futuro más limpio y sostenible.",
        "http://www.EcoTechInnovations.com", null, "1234");
    ArrayList<String> resultado = controladorUsuario.obtenerOfertasEmpresa("EcoTech");
    ArrayList<String> esperado = new ArrayList<String>();
    Assert.assertEquals(esperado, resultado);
  }
  
  @Test
  public void registrarPostulacionTest() throws UsuarioNoExisteException,
      OfertaLaboralNoExisteException, UsuarioYaExisteException, UsuarioEmailRepetidoException,
      TipoPublicacionYaExisteException, OfertaLaboralYaExisteException,
      TipoPublicacionNoExisteException, KeywordNoExisteException, KeywordYaExisteException,
      UsuarioYaExistePostulacion {
    controladorUsuario.altaEmpresa("EcoTech", "Sophia", "Johnson", "info@EcoTech.com",
        "EcoTech Innovations es una empresa líder en soluciones"
        + " tecnológicas sostenibles. Nuestro enfoque se centra"
        + " en desarrollar y comercializar productos y servicios"
        + " que aborden los desafíos ambientales más apremiantes"
        + " de nuestro tiempo. Desde sistemas de energía renovable"
        + " y dispositivos de monitorización ambiental hasta soluciones"
        + " de gestión de residuos inteligentes, nuestra misión es"
        + " proporcionar herramientas que permitan a las empresas y"
        + " comunidades adoptar prácticas más ecológicas sin comprometer"
        + " la eficiencia. Creemos en la convergencia armoniosa entre la"
        + " tecnología la naturaleza, y trabajamos incansablemente para"
        + " impulsar un futuro más limpio y sostenible.",
        "http://www.EcoTechInnovations.com", null, "1234");
    controladorOferta.altaKeyword("keyword");
    ArrayList<String> keywords = new ArrayList<String>();
    keywords.add("keyword");
    controladorOferta.altaTipoPublicacion("Premium", "Obtén máxima visibilidad", "1", 30,
        4000f, fechaDate2);
    controladorOferta.altaOfertaLaboral("Desarrollador Frontend",
        "Únete a nuestro equipo de desarrollo frontend y crea"
        + " experiencias de usuario excepcionales.",
        "09:00", "18:00", 90000f, "Montevideo", "Montevideo", fechaDate1, "Premium", "EcoTech",
        keywords, null, null);
    controladorUsuario.altaPostulante("maro", "María", "Rodríguez", "marrod@gmail.com",
        fechaDate2, "Uruguaya", null, "1234");
    controladorUsuario.registrarPostulacion(
        "Ingeniero en Sistemas, experiencia en desarrollo web y"
        + " aplicaciones móviles. Conocimientos en JavaScript y React.",
        "Me entusiasma la posibilidad de trabajar en proyectos "
        + "desafiantes y seguir creciendo como profesional en el campo de la tecnología.",
        fechaDate1, "maro", "Desarrollador Frontend");
    ArrayList<String> resultado = controladorUsuario.listaOfertasUsuario("maro");
    ArrayList<String> esperado = new ArrayList<String>();
    esperado.add("Desarrollador Frontend");
    Assert.assertEquals(esperado, resultado);
    
  }
  
  @Test
  public void editarPostulanteTest() throws UsuarioYaExisteException,
      UsuarioEmailRepetidoException, UsuarioNoExisteException {
    controladorUsuario.altaPostulante("NicknameTest", "NombreTest", "ApellidoTest",
        "EmailTest", fechaDate1, "NacionalidadTest", null, "1234");
    LocalDate fechaNueva = LocalDate.parse("2023-09-24");
    controladorUsuario.editarPostulante("NicknameTest", "nombreNuevo", "apellidoNuevo",
        fechaNueva, "nacionalidadNueva", null, "contrasenia");
    Postulante postulanteResultado = controladorUsuario.obtenerPostulante("NicknameTest");
    Assert.assertEquals("NicknameTest", postulanteResultado.getNickname());
    Assert.assertEquals("nombreNuevo", postulanteResultado.getNombre());
    Assert.assertEquals("apellidoNuevo", postulanteResultado.getApellido());
    Assert.assertEquals(fechaNueva, postulanteResultado.getFechaNacimiento());
    Assert.assertEquals("nacionalidadNueva", postulanteResultado.getNacionalidad());
    Assert.assertEquals("contrasenia", postulanteResultado.getContrasenia());
  }
  
  @Test
  public void editarEmrpesaTest() throws UsuarioYaExisteException,
      UsuarioEmailRepetidoException, UsuarioNoExisteException {
    controladorUsuario.altaEmpresa("EcoTech", "Sophia", "Johnson", "info@EcoTech.com",
        "EcoTech Innovations es una empresa líder en soluciones "
        + "tecnológicas sostenibles. Nuestro enfoque se centra en "
        + "desarrollar y comercializar productos y servicios que "
        + "aborden los desafíos ambientales más apremiantes de "
        + "nuestro tiempo. Desde sistemas de energía renovable y "
        + "dispositivos de monitorización ambiental hasta "
        + "soluciones de gestión de residuos inteligentes, "
        + "nuestra misión es proporcionar herramientas que permitan"
        + " a las empresas y comunidades adoptar prácticas más ecológicas"
        + " sin comprometer la eficiencia. Creemos en la convergencia"
        + " armoniosa entre la tecnología la naturaleza, y trabajamos"
        + " incansablemente para impulsar un futuro más limpio y sostenible.",
        "http://www.EcoTechInnovations.com", null, "1234");
    controladorUsuario.editarEmpresa("EcoTech", "nombreNuevo", "apellidoNuevo",
        "sitioWebNuevo", "descripcionNueva", null, "contrasenia");
    Empresa empresaResultado = controladorUsuario.obtenerEmpresa("EcoTech");
    Assert.assertEquals("EcoTech", empresaResultado.getNickname());
    Assert.assertEquals("nombreNuevo", empresaResultado.getNombre());
    Assert.assertEquals("apellidoNuevo", empresaResultado.getApellido());
    Assert.assertEquals("sitioWebNuevo".toLowerCase(), empresaResultado.getSitioWeb());
    Assert.assertEquals("descripcionNueva", empresaResultado.getDescripcion());
    Assert.assertEquals("contrasenia", empresaResultado.getContrasenia());
    
  }
  
  @Test
  public void obtenerDtPostulacionTest()
      throws UsuarioYaExisteException, UsuarioEmailRepetidoException, KeywordYaExisteException,
      TipoPublicacionYaExisteException, OfertaLaboralYaExisteException,
      TipoPublicacionNoExisteException, KeywordNoExisteException, UsuarioNoExisteException,
      OfertaLaboralNoExisteException, UsuarioNoExistePostulacion, UsuarioYaExistePostulacion {
    controladorUsuario.altaEmpresa("EcoTech", "Sophia", "Johnson", "info@EcoTech.com",
        "EcoTech Innovations es una empresa líder en soluciones "
        + "tecnológicas sostenibles. Nuestro enfoque se centra "
        + "en desarrollar y comercializar productos y servicios "
        + "que aborden los desafíos ambientales más apremiantes "
        + "de nuestro tiempo. Desde sistemas de energía renovable"
        + " y dispositivos de monitorización ambiental hasta "
        + "soluciones de gestión de residuos inteligentes, "
        + "nuestra misión es proporcionar herramientas que permitan"
        + " a las empresas y comunidades adoptar prácticas más "
        + "ecológicas sin comprometer la eficiencia. Creemos en la"
        + " convergencia armoniosa entre la tecnología la naturaleza,"
        + " y trabajamos incansablemente para impulsar un futuro más "
        + "limpio y sostenible.",
        "http://www.EcoTechInnovations.com", null, "1234");
    controladorOferta.altaKeyword("keyword");
    ArrayList<String> keywords = new ArrayList<String>();
    keywords.add("keyword");
    controladorOferta.altaTipoPublicacion("Premium", "Obtén máxima visibilidad", "1", 30,
        4000f, fechaDate2);
    controladorOferta.altaOfertaLaboral("Desarrollador Frontend",
        "Únete a nuestro equipo de desarrollo frontend y crea "
        + "experiencias de usuario excepcionales.",
        "09:00", "18:00", 90000f, "Montevideo", "Montevideo", fechaDate1, "Premium", "EcoTech",
        keywords, null, null);
    controladorUsuario.altaPostulante("maro", "María", "Rodríguez", "marrod@gmail.com",
        fechaDate2, "Uruguaya", null, "1234");
    controladorUsuario.registrarPostulacion(
        "Ingeniero en Sistemas, experiencia en desarrollo web y "
        + "aplicaciones móviles. Conocimientos en JavaScript y React.",
        "Me entusiasma la posibilidad de trabajar en proyectos "
        + "desafiantes y seguir creciendo como profesional en el campo de la tecnología.",
        fechaDate1, "maro", "Desarrollador Frontend");
    Dtpostulacion postulacionResultado = controladorUsuario.obtenerDtpostulacion("maro",
        "Desarrollador Frontend");
    Assert.assertEquals("maro", postulacionResultado.getnicknamePostulante());
    Assert.assertEquals(
        "Me entusiasma la posibilidad de trabajar en proyectos "
        + "desafiantes y seguir creciendo como profesional en el campo de la tecnología.",
        postulacionResultado.getDescripMotivacion());
    Assert.assertEquals(fechaDate1, postulacionResultado.getFechaPostulacion());
    Assert.assertEquals(
        "Ingeniero en Sistemas, experiencia en desarrollo web y "
        + "aplicaciones móviles. Conocimientos en JavaScript y React.",
        postulacionResultado.getCvReducido());
    
  }
  
  @Test
  public void obtenerDtOfertasIngresadasTest()
      throws UsuarioYaExisteException, UsuarioEmailRepetidoException, KeywordYaExisteException,
      TipoPublicacionYaExisteException, OfertaLaboralYaExisteException,
      TipoPublicacionNoExisteException, KeywordNoExisteException, UsuarioNoExisteException {
    controladorUsuario.altaEmpresa("EcoTech", "Sophia", "Johnson", "info@EcoTech.com",
        "EcoTech Innovations es una empresa líder en soluciones "
        + "tecnológicas sostenibles. Nuestro enfoque se centra "
        + "en desarrollar y comercializar productos y servicios "
        + "que aborden los desafíos ambientales más apremiantes "
        + "de nuestro tiempo. Desde sistemas de energía renovable "
        + "y dispositivos de monitorización ambiental hasta "
        + "soluciones de gestión de residuos inteligentes, "
        + "nuestra misión es proporcionar herramientas que permitan"
        + " a las empresas y comunidades adoptar prácticas más "
        + "ecológicas sin comprometer la eficiencia. Creemos en "
        + "la convergencia armoniosa entre la tecnología la naturaleza,"
        + " y trabajamos incansablemente para impulsar un futuro "
        + "más limpio y sostenible.",
        "http://www.EcoTechInnovations.com", null, "1234");
    controladorOferta.altaKeyword("keyword");
    ArrayList<String> keywords = new ArrayList<String>();
    keywords.add("keyword");
    controladorOferta.altaTipoPublicacion("Premium", "Obtén máxima visibilidad", "1", 30,
        4000f, fechaDate2);
    controladorOferta.altaOfertaLaboral("Desarrollador Frontend",
        "Únete a nuestro equipo de desarrollo frontend y crea "
        + "experiencias de usuario excepcionales.",
        "09:00", "18:00", 90000f, "Montevideo", "Montevideo", fechaDate1, "Premium", "EcoTech",
        keywords, null, null);
    ArrayList<DtOfertaLaboral> listaResultado = controladorUsuario
        .obtenerDtofertasIngresadasDeEmpresa("EcoTech");
    Assert.assertEquals(1, listaResultado.size());
    DtOfertaLaboral oferta = listaResultado.get(0);
    Assert.assertEquals("Desarrollador Frontend", oferta.getNombre());
    Assert.assertEquals(
        "Únete a nuestro equipo de desarrollo frontend y crea "
        + "experiencias de usuario excepcionales.",
        oferta.getDescripcion());
    Assert.assertEquals("09:00", oferta.getHorarioInicio());
    Assert.assertEquals("18:00", oferta.getHorarioFinal());
    Assert.assertEquals(90000f, oferta.getRemuneracion());
    Assert.assertEquals("Montevideo", oferta.getDepartamento());
    Assert.assertEquals("Montevideo", oferta.getCiudad());
    
  }
  
  @Test
  public void obtenerDtOfertasConfirmadasTest() throws UsuarioYaExisteException,
      UsuarioEmailRepetidoException, KeywordYaExisteException, OfertaLaboralYaExisteException,
      TipoPublicacionNoExisteException, KeywordNoExisteException, UsuarioNoExisteException,
      TipoPublicacionYaExisteException, OfertaLaboralNoExisteException {
    controladorUsuario.altaEmpresa("EcoTech", "Sophia", "Johnson", "info@EcoTech.com",
        "EcoTech Innovations es una empresa líder en soluciones "
        + "tecnológicas sostenibles. Nuestro enfoque se centra en "
        + "desarrollar y comercializar productos y servicios que "
        + "aborden los desafíos ambientales más apremiantes de "
        + "nuestro tiempo. Desde sistemas de energía renovable y "
        + "dispositivos de monitorización ambiental hasta soluciones "
        + "de gestión de residuos inteligentes, nuestra misión es "
        + "proporcionar herramientas que permitan a las empresas y "
        + "comunidades adoptar prácticas más ecológicas sin "
        + "comprometer la eficiencia. Creemos en la convergencia "
        + "armoniosa entre la tecnología la naturaleza, y "
        + "trabajamos incansablemente para impulsar un futuro "
        + "más limpio y sostenible.",
        "http://www.EcoTechInnovations.com", null, "1234");
    controladorOferta.altaKeyword("keyword");
    ArrayList<String> keywords = new ArrayList<String>();
    keywords.add("keyword");
    controladorOferta.altaTipoPublicacion("Premium", "Obtén máxima visibilidad", "1", 30,
        4000f, fechaDate2);
    controladorOferta.altaOfertaLaboral("Desarrollador Frontend",
        "Únete a nuestro equipo de desarrollo frontend y crea "
        + "experiencias de usuario excepcionales.",
        "09:00", "18:00", 90000f, "Montevideo", "Montevideo", fechaDate1, "Premium", "EcoTech",
        keywords, null, null);
    controladorOferta.aceptarRechazarOfertaLaboral("Desarrollador Frontend",
        EstadoOferta.CONFIRMADA, fechaDate1);
    ArrayList<DtOfertaLaboral> listaResultado = controladorUsuario
        .obtenerDtofertasConfirmadasDeEmpresa("EcoTech");
    Assert.assertEquals(1, listaResultado.size());
    DtOfertaLaboral oferta = listaResultado.get(0);
    Assert.assertEquals("Desarrollador Frontend", oferta.getNombre());
    Assert.assertEquals(
        "Únete a nuestro equipo de desarrollo frontend y crea "
        + "experiencias de usuario excepcionales.",
        oferta.getDescripcion());
    Assert.assertEquals("09:00", oferta.getHorarioInicio());
    Assert.assertEquals("18:00", oferta.getHorarioFinal());
    Assert.assertEquals(90000f, oferta.getRemuneracion());
    Assert.assertEquals("Montevideo", oferta.getDepartamento());
    Assert.assertEquals("Montevideo", oferta.getCiudad());
    
  }
  
  @Test
  public void obtenerDtOfertasRechazadasTest()
      throws UsuarioYaExisteException, UsuarioEmailRepetidoException, KeywordYaExisteException,
      TipoPublicacionYaExisteException, OfertaLaboralYaExisteException,
      TipoPublicacionNoExisteException, KeywordNoExisteException, UsuarioNoExisteException,
      OfertaLaboralNoExisteException {
    controladorUsuario.altaEmpresa("EcoTech", "Sophia", "Johnson", "info@EcoTech.com",
        "EcoTech Innovations es una empresa líder en soluciones "
        + "tecnológicas sostenibles. Nuestro enfoque se centra en "
        + "desarrollar y comercializar productos y servicios que "
        + "aborden los desafíos ambientales más apremiantes de nuestro tiempo. "
        + "Desde sistemas de energía renovable y dispositivos de monitorización "
        + "ambiental hasta soluciones de gestión de residuos inteligentes, "
        + "nuestra misión es proporcionar herramientas que permitan a las "
        + "empresas y comunidades adoptar prácticas más ecológicas sin "
        + "comprometer la eficiencia. Creemos en la convergencia armoniosa "
        + "entre la tecnología la naturaleza, y trabajamos incansablemente "
        + "para impulsar un futuro más limpio y sostenible.",
        "http://www.EcoTechInnovations.com", null, "1234");
    controladorOferta.altaKeyword("keyword");
    ArrayList<String> keywords = new ArrayList<String>();
    keywords.add("keyword");
    controladorOferta.altaTipoPublicacion("Premium", "Obtén máxima visibilidad", "1", 30,
        4000f, fechaDate2);
    controladorOferta.altaOfertaLaboral("Desarrollador Frontend",
        "Únete a nuestro equipo de desarrollo frontend y crea experiencias"
        + " de usuario excepcionales.",
        "09:00", "18:00", 90000f, "Montevideo", "Montevideo", fechaDate1, "Premium", "EcoTech",
        keywords, null, null);
    controladorOferta.aceptarRechazarOfertaLaboral("Desarrollador Frontend",
        EstadoOferta.RECHAZADA, fechaDate1);
    ArrayList<DtOfertaLaboral> listaResultado = controladorUsuario
        .obtenerDtofertasRechazadasDeEmpresa("EcoTech");
    Assert.assertEquals(1, listaResultado.size());
    DtOfertaLaboral oferta = listaResultado.get(0);
    Assert.assertEquals("Desarrollador Frontend", oferta.getNombre());
    Assert.assertEquals(
        "Únete a nuestro equipo de desarrollo frontend y crea experiencias "
        + "de usuario excepcionales.",
        oferta.getDescripcion());
    Assert.assertEquals("09:00", oferta.getHorarioInicio());
    Assert.assertEquals("18:00", oferta.getHorarioFinal());
    Assert.assertEquals(90000f, oferta.getRemuneracion());
    Assert.assertEquals("Montevideo", oferta.getDepartamento());
    Assert.assertEquals("Montevideo", oferta.getCiudad());
  }
  
  @Test
  public void confirmarContraseniaTest() throws UsuarioYaExisteException,
      UsuarioEmailRepetidoException, UsuarioNoExisteException {
    controladorUsuario.altaEmpresa("EcoTech", "Sophia", "Johnson", "info@EcoTech.com",
        "EcoTech Innovations es una empresa líder en soluciones "
        + "tecnológicas sostenibles. Nuestro enfoque se centra en "
        + "desarrollar y comercializar productos y servicios que "
        + "aborden los desafíos ambientales más apremiantes de "
        + "nuestro tiempo. Desde sistemas de energía renovable y "
        + "dispositivos de monitorización ambiental hasta soluciones "
        + "de gestión de residuos inteligentes, nuestra misión es "
        + "proporcionar herramientas que permitan a las empresas y "
        + "comunidades adoptar prácticas más ecológicas sin "
        + "comprometer la eficiencia. Creemos en la convergencia "
        + "armoniosa entre la tecnología la naturaleza, y trabajamos "
        + "incansablemente para impulsar un futuro más "
        + "limpio y sostenible.",
        "http://www.EcoTechInnovations.com", null, "1234");
    
    Assert.assertTrue(controladorUsuario.confirmarContrasenia("EcoTech", "1234"));
    Assert.assertTrue(
        controladorUsuario.confirmarContrasenia("info@EcoTech.com".toLowerCase(), "1234"));
    Assert.assertFalse(controladorUsuario.confirmarContrasenia("EcoTech", "pepe"));
    Assert.assertFalse(
        controladorUsuario.confirmarContrasenia("info@EcoTech.com".toLowerCase(), "pepe"));
    
  }
  
  @Test
  public void obtenerDtUsuariosTest() throws UsuarioYaExisteException,
      UsuarioEmailRepetidoException, UsuarioNoExisteException {
    controladorUsuario.altaEmpresa("EcoTech", "Sophia", "Johnson", "info@EcoTech.com",
        "EcoTech Innovations es una empresa líder en soluciones "
        + "tecnológicas sostenibles. Nuestro enfoque se centra "
        + "en desarrollar y comercializar productos y servicios "
        + "que aborden los desafíos ambientales más apremiantes "
        + "de nuestro tiempo. Desde sistemas de energía renovable "
        + "y dispositivos de monitorización ambiental hasta "
        + "soluciones de gestión de residuos inteligentes, "
        + "nuestra misión es proporcionar herramientas que "
        + "permitan a las empresas y comunidades adoptar "
        + "prácticas más ecológicas sin comprometer la eficiencia. "
        + "Creemos en la convergencia armoniosa entre la tecnología "
        + "la naturaleza, y trabajamos incansablemente para impulsar "
        + "un futuro más limpio y sostenible.",
        "http://www.EcoTechInnovations.com", null, "1234");
    controladorUsuario.altaPostulante("maro", "María", "Rodríguez", "marrod@gmail.com",
        fechaDate2, "Uruguaya", null, "1234");
    ArrayList<Dtusuario> listaRes = controladorUsuario.obtenerDtusuarios();
    Dtusuario postulante = controladorUsuario.obtenerDtusuario("maro");
    Dtusuario empresa = controladorUsuario.obtenerDtusuario("EcoTech");
    for (Dtusuario usuario : listaRes) {
      if (usuario.getNickname() == empresa.getNickname()) {
        Assert.assertEquals(usuario.getNickname(), empresa.getNickname());
        Assert.assertEquals(usuario.getNombre(), empresa.getNombre());
        Assert.assertEquals(usuario.getApellido(), empresa.getApellido());
        Assert.assertEquals(usuario.getEmail(), empresa.getEmail());
        Assert.assertEquals(usuario.getContrasenia(), empresa.getContrasenia());
      } else if (usuario.getNickname() == postulante.getNickname()) {
        Assert.assertEquals(usuario.getNickname(), postulante.getNickname());
        Assert.assertEquals(usuario.getNombre(), postulante.getNombre());
        Assert.assertEquals(usuario.getApellido(), postulante.getApellido());
        Assert.assertEquals(usuario.getEmail(), postulante.getEmail());
        Assert.assertEquals(usuario.getContrasenia(), postulante.getContrasenia());
      }
    }
  }
  
  @Test
  public void comprarPaqueteTest() throws UsuarioYaExisteException,
      UsuarioEmailRepetidoException, TipoPublicacionYaExisteException,
      PaquetePublicacionYaExisteException, TipoPublicacionNoExisteException,
      UsuarioNoExisteException, PaquetePublicacionNoExisteException {
    controladorUsuario.altaEmpresa("EcoTech", "Sophia", "Johnson", "info@EcoTech.com",
        "EcoTech Innovations es una empresa líder en soluciones "
        + "tecnológicas sostenibles. Nuestro enfoque se centra "
        + "en desarrollar y comercializar productos y servicios "
        + "que aborden los desafíos ambientales más apremiantes "
        + "de nuestro tiempo. Desde sistemas de energía renovable "
        + "y dispositivos de monitorización ambiental hasta soluciones "
        + "de gestión de residuos inteligentes, nuestra misión es "
        + "proporcionar herramientas que permitan a las empresas y "
        + "comunidades adoptar prácticas más ecológicas sin "
        + "comprometer la eficiencia. Creemos en la convergencia "
        + "armoniosa entre la tecnología la naturaleza, y trabajamos "
        + "incansablemente para impulsar un futuro "
        + "más limpio y sostenible.",
        "http://www.EcoTechInnovations.com", null, "1234");
    controladorOferta.altaTipoPublicacion("Premium", "Obtén máxima visibilidad", "1", 30,
        4000f, fechaDate2);
    DtcantidadTipoPublicacion dtCantidadTipo = 
        new DtcantidadTipoPublicacion("Premium", 2);
    ArrayList<DtcantidadTipoPublicacion> listaCantidad = new ArrayList<DtcantidadTipoPublicacion>();
    listaCantidad.add(dtCantidadTipo);
    controladorOferta.registrarPaquete("Paquete", "Descripcion", 10, 20f, null, fechaDate1,
        listaCantidad);
    controladorUsuario.comprarPaquete("EcoTech", "Paquete", fechaDate2);
    ArrayList<DtpaquetePublicacion> listaRes = controladorUsuario
        .obtenerDtpaquetesDeEmpresa("EcoTech");
    DtpaquetePublicacion paquete = listaRes.get(0);
    Assert.assertEquals("Paquete", paquete.getNombre());
    Assert.assertEquals("Descripcion", paquete.getDescripcion());
    Assert.assertEquals(10, paquete.getPeriodoValidez());
    Assert.assertEquals(20f, paquete.getDescuento());
    
  }
  
  @Test
  public void obtenerPostulacionesTest()
      throws UsuarioYaExisteException, UsuarioEmailRepetidoException, KeywordYaExisteException,
      TipoPublicacionYaExisteException, OfertaLaboralYaExisteException,
      TipoPublicacionNoExisteException, KeywordNoExisteException, UsuarioNoExisteException,
      OfertaLaboralNoExisteException, UsuarioYaExistePostulacion {
    controladorUsuario.altaEmpresa("EcoTech", "Sophia", "Johnson", "info@EcoTech.com",
        "EcoTech Innovations es una empresa líder en soluciones "
        + "tecnológicas sostenibles. Nuestro enfoque se centra "
        + "en desarrollar y comercializar productos y servicios "
        + "que aborden los desafíos ambientales más apremiantes "
        + "de nuestro tiempo. Desde sistemas de energía renovable "
        + "y dispositivos de monitorización ambiental hasta "
        + "soluciones de gestión de residuos inteligentes, "
        + "nuestra misión es proporcionar herramientas que "
        + "permitan a las empresas y comunidades adoptar "
        + "prácticas más ecológicas sin comprometer la eficiencia. "
        + "Creemos en la convergencia armoniosa entre la tecnología "
        + "la naturaleza, y trabajamos incansablemente para impulsar "
        + "un futuro más limpio y sostenible.",
        "http://www.EcoTechInnovations.com", null, "1234");
    controladorOferta.altaKeyword("keyword");
    ArrayList<String> keywords = new ArrayList<String>();
    keywords.add("keyword");
    controladorOferta.altaTipoPublicacion("Premium", "Obtén máxima visibilidad", "1", 30,
        4000f, fechaDate2);
    controladorOferta.altaOfertaLaboral("Desarrollador Frontend",
        "Únete a nuestro equipo de desarrollo frontend y crea "
        + "experiencias de usuario excepcionales.",
        "09:00", "18:00", 90000f, "Montevideo", "Montevideo", fechaDate1, "Premium", "EcoTech",
        keywords, null, null);
    controladorUsuario.altaPostulante("maro", "María", "Rodríguez", "marrod@gmail.com",
        fechaDate2, "Uruguaya", null, "1234");
    controladorUsuario.registrarPostulacion(
        "Ingeniero en Sistemas, experiencia en desarrollo web y "
        + "aplicaciones móviles. Conocimientos en JavaScript y React.",
        "Me entusiasma la posibilidad de trabajar en proyectos "
        + "desafiantes y seguir creciendo como profesional en "
        + "el campo de la tecnología.",
        fechaDate1, "maro", "Desarrollador Frontend");
    ArrayList<Dtpostulacion> listaRes = controladorUsuario
        .obtenerDtpostulacionesDePostulante("maro");
    Dtpostulacion postulacion = listaRes.get(0);
    Assert.assertEquals("maro", postulacion.getnicknamePostulante());
    Assert.assertEquals(fechaDate1, postulacion.getFechaPostulacion());
    Assert.assertEquals(
        "Ingeniero en Sistemas, experiencia en desarrollo web y "
        + "aplicaciones móviles. Conocimientos en JavaScript y React.",
        postulacion.getCvReducido());
    Assert.assertEquals(
        "Me entusiasma la posibilidad de trabajar en proyectos "
        + "desafiantes y seguir creciendo como profesional en "
        + "el campo de la tecnología.",
        postulacion.getDescripMotivacion());
    
  }
  
  @Test
  public void listarPaquetesNoCompradosTest() throws UsuarioYaExisteException,
      UsuarioEmailRepetidoException, TipoPublicacionYaExisteException,
      PaquetePublicacionYaExisteException, TipoPublicacionNoExisteException,
      UsuarioNoExisteException, PaquetePublicacionNoExisteException {
    controladorUsuario.altaEmpresa("EcoTech", "Sophia", "Johnson", "info@EcoTech.com",
        "EcoTech Innovations es una empresa líder en soluciones "
        + "tecnológicas sostenibles. Nuestro enfoque se centra en "
        + "desarrollar y comercializar productos y servicios que "
        + "aborden los desafíos ambientales más apremiantes de "
        + "nuestro tiempo. Desde sistemas de energía renovable "
        + "y dispositivos de monitorización ambiental hasta "
        + "soluciones de gestión de residuos inteligentes, "
        + "nuestra misión es proporcionar herramientas "
        + "que permitan a las empresas y comunidades "
        + "adoptar prácticas más ecológicas sin "
        + "comprometer la eficiencia. Creemos en la "
        + "convergencia armoniosa entre la tecnología "
        + "la naturaleza, y trabajamos incansablemente "
        + "para impulsar un futuro más limpio y sostenible.",
        "http://www.EcoTechInnovations.com", null, "1234");
    controladorOferta.altaTipoPublicacion("Premium", "Obtén máxima visibilidad", "1", 30,
        4000f, fechaDate2);
    DtcantidadTipoPublicacion dtCantidadTipo = 
        new DtcantidadTipoPublicacion("Premium", 2);
    ArrayList<DtcantidadTipoPublicacion> listaCantidad = new ArrayList<DtcantidadTipoPublicacion>();
    listaCantidad.add(dtCantidadTipo);
    controladorOferta.registrarPaquete("Paquete", "Descripcion", 10, 20f, null, fechaDate1,
        listaCantidad);
    controladorUsuario.comprarPaquete("EcoTech", "Paquete", fechaDate2);
    controladorOferta.registrarPaquete("Paquete2", "Descripcion", 10, 20f, null, fechaDate1,
        listaCantidad);
    controladorOferta.registrarPaquete("Paquete3", "Descripcion", 10, 20f, null, fechaDate1,
        listaCantidad);
    ArrayList<String> listaEsperada = new ArrayList<String>();
    listaEsperada.add("Paquete2");
    listaEsperada.add("Paquete3");
    Collections.sort(listaEsperada);
    ArrayList<String> listaRes = controladorUsuario
        .listarPaquetesNoCompradosDeEmpresa("EcoTech");
    Collections.sort(listaRes);
    Assert.assertEquals(listaEsperada, listaRes);
    
  }
}
