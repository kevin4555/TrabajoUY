package test.java;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import main.java.classes.Empresa;
import main.java.classes.OfertaLaboral;
import main.java.classes.Postulante;
import main.java.classes.Usuario;
import main.java.controllers.Fabrica;
import main.java.datatypes.DtCantidadTipoPublicacion;
import main.java.datatypes.DtCantidadTipoPublicacionRestante;
import main.java.datatypes.DtCompraPaquete;
import main.java.datatypes.DtDatosPdf;
import main.java.datatypes.DtEmpresa;
import main.java.datatypes.DtOfertaLaboral;
import main.java.datatypes.DtPaquetePublicacion;
import main.java.datatypes.DtPostulacion;
import main.java.datatypes.DtPostulante;
import main.java.datatypes.DtTipoPublicacion;
import main.java.datatypes.DtUsuario;
import main.java.datatypes.EstadoOferta;
import main.java.excepciones.KeywordNoExisteException;
import main.java.excepciones.KeywordYaExisteException;
import main.java.excepciones.OfertaLaboralNoExisteException;
import main.java.excepciones.OfertaLaboralNoSePuedeFinalizar;
import main.java.excepciones.OfertaLaboralYaExisteException;
import main.java.excepciones.PaquetePublicacionNoExisteException;
import main.java.excepciones.PaquetePublicacionYaExisteException;
import main.java.excepciones.PostulanteNoEsOfertaFavoritaException;
import main.java.excepciones.PostulanteYaEsOfertaFavoritaException;
import main.java.excepciones.TipoPublicacionNoExisteException;
import main.java.excepciones.TipoPublicacionYaExisteException;
import main.java.excepciones.UsuarioEmailRepetidoException;
import main.java.excepciones.UsuarioNoEstaSeguidoException;
import main.java.excepciones.UsuarioNoExisteException;
import main.java.excepciones.UsuarioNoExistePostulacion;
import main.java.excepciones.UsuarioYaEstaSeguidoException;
import main.java.excepciones.UsuarioYaExisteException;
import main.java.excepciones.UsuarioYaExistePostulacion;
import main.java.handlers.ManejadorOfertas;
import main.java.handlers.ManejadorPaquetes;
import main.java.handlers.ManejadorSettings;
import main.java.handlers.ManejadorUsuario;
import main.java.interfaces.IcontroladorOferta;
import main.java.interfaces.IcontroladorUsuario;
import org.junit.Assert;
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
    controladorUsuario = Fabrica.getInstance()
          .obtenerControladorUsuario();
    controladorOferta = Fabrica.getInstance()
          .obtenerControladorOferta();
    manejadorUsuario = ManejadorUsuario.getInstance();
    manejadorOfertas = ManejadorOfertas.getInstance();
    manejadorSettings = ManejadorSettings.getInstance();
    manejadorPaquetes = ManejadorPaquetes.getInstance();

    fechaDate1 = LocalDate.parse("1988-11-10");
    fechaDate2 = LocalDate.parse("1988-09-02");

    postulante1 = new Postulante("NicknameTest",
          "NombreTest", "ApellidoTest", "EmailTest",
          fechaDate1, "NacionalidadTest", null, "1234");
    new Postulante("NicknameTest2", "NombreTest2",
          "ApellidoTest2", "EmailTest2", fechaDate2,
          "NacionalidadTest2", null, "1234");
    empresa1 = new Empresa("nicknameEmpresa1", "nombre1",
          "apellido1", "email1", "descripcion1",
          "sitioWeb1",
          null, "1234");
    new Empresa("nicknameEmpresa2", "nombre2", "apellido2",
          "email2", "descripcion2", "sitioWeb2", null,
          "1234");

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
    List<String> lista = controladorUsuario
          .listaDeUsuarios();
    Assert.assertTrue(lista.isEmpty());
  }

  @Test
  public void obtenerUsuarioTest()
        throws UsuarioYaExisteException,
        UsuarioNoExisteException,
        UsuarioEmailRepetidoException {
    controladorUsuario.altaPostulante("NicknameTest",
          "NombreTest", "ApellidoTest", "EmailTest",
          fechaDate1, "NacionalidadTest", null, "1234");
    Usuario usuarioResultado = controladorUsuario
          .obtenerUsuario("NicknameTest");
    Assert.assertEquals("NicknameTest",
          usuarioResultado.getNickname());
    Assert.assertEquals("NombreTest",
          usuarioResultado.getNombre());
    Assert.assertEquals("ApellidoTest",
          usuarioResultado.getApellido());
    Assert.assertEquals("EmailTest".toLowerCase(),
          usuarioResultado.getEmail());
  }

  @Test
  public void obtenerUsuarioManejadorVacio()
        throws UsuarioNoExisteException {
    try {
      controladorUsuario.obtenerUsuario("Carlitos");
    } catch (UsuarioNoExisteException e) {
      Assert.assertEquals("Usuario: Carlitos no existe",
            e.getMessage());
    }
  }

  @Test
  public void obtenerDtUsuario()
        throws UsuarioYaExisteException,
        UsuarioNoExisteException,
        UsuarioEmailRepetidoException, IOException {
    controladorUsuario.altaPostulante("NicknameTest",
          "NombreTest", "ApellidoTest", "EmailTest",
          fechaDate1, "NacionalidadTest", null, "1234");
    DtUsuario dtResultado = controladorUsuario
          .obtenerDtUsuario("NicknameTest");
    Assert.assertEquals("NicknameTest",
          dtResultado.getNickname());
    Assert.assertEquals("NombreTest",
          dtResultado.getNombre());
    Assert.assertEquals("ApellidoTest",
          dtResultado.getApellido());
    Assert.assertEquals("EmailTest".toLowerCase(),
          dtResultado.getEmail());
    Assert.assertTrue(
          dtResultado.getOfertasColeccion().isEmpty());;
  }

  @Test
  public void altaPostulanteTest()
        throws UsuarioYaExisteException,
        UsuarioNoExisteException,
        UsuarioEmailRepetidoException {

    controladorUsuario.altaPostulante("NicknameTest",
          "NombreTest", "ApellidoTest", "EmailTest",
          fechaDate1, "NacionalidadTest", null, "1234");
    Postulante postulanteResultado = controladorUsuario
          .obtenerPostulante("NicknameTest");
    Assert.assertEquals(postulante1.getNickname(),
          postulanteResultado.getNickname());
    Assert.assertEquals(postulante1.getNombre(),
          postulanteResultado.getNombre());
    Assert.assertEquals(postulante1.getApellido(),
          postulanteResultado.getApellido());
    Assert.assertEquals(
          postulante1.getEmail().toLowerCase(),
          postulanteResultado.getEmail());
    Assert.assertEquals(
          postulante1.getFechaNacimiento().toString(),
          postulanteResultado.getFechaNacimiento()
                .toString());
    Assert.assertEquals(postulante1.getNacionalidad(),
          postulanteResultado.getNacionalidad());
  }

  @Test
  public void listarUsuariosUsuarioUnicoTest()
        throws UsuarioYaExisteException,
        UsuarioEmailRepetidoException {
    controladorUsuario.altaPostulante("NicknameTest",
          "NombreTest", "ApellidoTest", "EmailTest",
          fechaDate1, "NacionalidadTest", null, "1234");
    List<String> listaEsperada = new ArrayList<String>();
    listaEsperada.add("NicknameTest");
    List<String> listaResultado = controladorUsuario
          .listaDeUsuarios();
    Assert.assertEquals(listaEsperada, listaResultado);

  }

  @Test
  public void listarUsuariosTest()
        throws UsuarioYaExisteException,
        UsuarioEmailRepetidoException {
    controladorUsuario.altaPostulante("NicknameTest",
          "NombreTest", "ApellidoTest", "EmailTest",
          fechaDate1, "NacionalidadTest", null, "1234");
    controladorUsuario.altaPostulante("NicknameTest2",
          "NombreTest2", "ApellidoTest2", "EmailTest2",
          fechaDate2, "NacionalidadTest2", null, "1234");
    List<String> listaResultado = controladorUsuario
          .listaDeUsuarios();
    Collections.sort(listaResultado);
    List<String> listaEsperada = new ArrayList<String>();
    listaEsperada.add("NicknameTest");
    listaEsperada.add("NicknameTest2");
    Collections.sort(listaEsperada);
    Assert.assertEquals(listaEsperada, listaResultado);
  }

  @Test
  public void obtenerEmpresaTest()
        throws UsuarioYaExisteException,
        UsuarioEmailRepetidoException,
        UsuarioNoExisteException {
    controladorUsuario.altaEmpresa("nicknameEmpresa1",
          "nombre1", "apellido1", "email1", "descripcion1",
          "sitioWeb1", null, "1234");
    Empresa empresaResultado = controladorUsuario
          .obtenerEmpresa("nicknameEmpresa1");
    Assert.assertEquals(empresa1.getNickname(),
          empresaResultado.getNickname());
    Assert.assertEquals(empresa1.getNombre(),
          empresaResultado.getNombre());
    Assert.assertEquals(empresa1.getApellido(),
          empresaResultado.getApellido());
    Assert.assertEquals(empresa1.getEmail().toLowerCase(),
          empresaResultado.getEmail());
    Assert.assertEquals(empresa1.getDescripcion(),
          empresaResultado.getDescripcion());
    Assert.assertEquals(
          empresa1.getSitioWeb().toLowerCase(),
          empresaResultado.getSitioWeb());

  }

  @Test
  public void listarEmpresasTest()
        throws UsuarioYaExisteException,
        UsuarioEmailRepetidoException {
    controladorUsuario.altaEmpresa("nicknameEmpresa1",
          "nombre1", "apellido1", "email1", "descripcion1",
          "sitioWeb1", null, "1234");
    controladorUsuario.altaEmpresa("nicknameEmpresa2",
          "nombre2", "apellido2", "email2", "descripcion2",
          "sitioWeb2", null, "1234");
    List<String> listaResultado = controladorUsuario
          .listarEmpresas();
    List<String> listaEsperada = new ArrayList<String>();
    listaEsperada.add("nicknameEmpresa1");
    listaEsperada.add("nicknameEmpresa2");
    Collections.sort(listaResultado);
    Collections.sort(listaEsperada);
    Assert.assertEquals(listaEsperada, listaResultado);

  }

  @Test
  public void listarPostulantesTest()
        throws UsuarioYaExisteException,
        UsuarioEmailRepetidoException {
    controladorUsuario.altaPostulante("NicknameTest",
          "NombreTest", "ApellidoTest", "EmailTest",
          fechaDate1, "NacionalidadTest", null, "1234");
    controladorUsuario.altaPostulante("NicknameTest2",
          "NombreTest2", "ApellidoTest2", "EmailTest2",
          fechaDate2, "NacionalidadTest2", null, "1234");
    List<String> listaResultado = controladorUsuario
          .listarPostulantes();
    Collections.sort(listaResultado);
    List<String> listaEsperada = new ArrayList<String>();
    listaEsperada.add("NicknameTest");
    listaEsperada.add("NicknameTest2");
    Collections.sort(listaEsperada);
    Assert.assertEquals(listaEsperada, listaResultado);
  }

  @Test
  public void editarDatosBasicosDeUsuarioTest()
        throws UsuarioYaExisteException,
        UsuarioNoExisteException,
        UsuarioEmailRepetidoException, IOException {
    controladorUsuario.altaPostulante("NicknameTest",
          "NombreTest", "ApellidoTest", "EmailTest",
          fechaDate1, "NacionalidadTest", null, "1234");
    DtUsuario dtUsuario = postulante1.obtenerDtusuario();
    controladorUsuario.editarDatosBasicos(dtUsuario,
          "nombreNuevo", "apellidoNuevo");
    Usuario resultado = controladorUsuario
          .obtenerUsuario("NicknameTest");
    Assert.assertEquals("nombreNuevo",
          resultado.getNombre());
    Assert.assertEquals("apellidoNuevo",
          resultado.getApellido());

  }

  @Test
  public void listarOfertasDeEmpresaTest()
        throws UsuarioYaExisteException,
        UsuarioEmailRepetidoException,
        OfertaLaboralYaExisteException,
        UsuarioNoExisteException,
        OfertaLaboralNoExisteException,
        TipoPublicacionNoExisteException,
        TipoPublicacionYaExisteException,
        KeywordNoExisteException, KeywordYaExisteException {
    controladorUsuario.altaEmpresa("EcoTech", "Sophia",
          "Johnson", "info@EcoTech.com",
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
          "http://www.EcoTechInnovations.com", null,
          "1234");
    controladorOferta.altaTipoPublicacion("Premium",
          "Obtén máxima visibilidad", "1", 30, 4000f,
          fechaDate2);
    controladorOferta.altaKeyword("keyword");
    List<String> keywords = new ArrayList<String>();
    keywords.add("keyword");
    controladorOferta.altaOfertaLaboral("nombre",
          "descripcion", "09:00", "18:00", 90000f,
          "Montevideo", "Montevideo", fechaDate1, "Premium",
          "EcoTech", keywords, null, null);
    List<String> resultado = controladorUsuario
          .obtenerOfertasEmpresaUsuario("EcoTech");
    List<String> esperado = new ArrayList<String>();
    esperado.add("nombre");
    Assert.assertEquals(esperado, resultado);
  }

  @Test
  public void listarOfertasDeEmpresaVaciaTest()
        throws UsuarioYaExisteException,
        UsuarioEmailRepetidoException,
        UsuarioNoExisteException {
    controladorUsuario.altaEmpresa("EcoTech", "Sophia",
          "Johnson", "info@EcoTech.com",
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
          "http://www.EcoTechInnovations.com", null,
          "1234");
    List<String> resultado = controladorUsuario
          .obtenerOfertasEmpresaUsuario("EcoTech");
    List<String> esperado = new ArrayList<String>();
    Assert.assertEquals(esperado, resultado);
  }

  @Test
  public void registrarPostulacionTest()
        throws UsuarioNoExisteException,
        OfertaLaboralNoExisteException,
        UsuarioYaExisteException,
        UsuarioEmailRepetidoException,
        TipoPublicacionYaExisteException,
        OfertaLaboralYaExisteException,
        TipoPublicacionNoExisteException,
        KeywordNoExisteException, KeywordYaExisteException,
        UsuarioYaExistePostulacion {
    controladorUsuario.altaEmpresa("EcoTech", "Sophia",
          "Johnson", "info@EcoTech.com",
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
          "http://www.EcoTechInnovations.com", null,
          "1234");
    controladorOferta.altaKeyword("keyword");
    List<String> keywords = new ArrayList<String>();
    keywords.add("keyword");
    controladorOferta.altaTipoPublicacion("Premium",
          "Obtén máxima visibilidad", "1", 30, 4000f,
          fechaDate2);
    controladorOferta.altaOfertaLaboral(
          "Desarrollador Frontend",
          "Únete a nuestro equipo de desarrollo frontend y crea"
                + " experiencias de usuario excepcionales.",
          "09:00", "18:00", 90000f, "Montevideo",
          "Montevideo", fechaDate1, "Premium", "EcoTech",
          keywords, null, null);
    controladorUsuario.altaPostulante("maro", "María",
          "Rodríguez", "marrod@gmail.com", fechaDate2,
          "Uruguaya", null, "1234");
    controladorUsuario.registrarPostulacion(
          "Ingeniero en Sistemas, experiencia en desarrollo web y"
                + " aplicaciones móviles. Conocimientos en JavaScript y React.",
          "Me entusiasma la posibilidad de trabajar en proyectos "
                + "desafiantes y seguir creciendo como profesional en el campo de la tecnología.",
          fechaDate1, "maro", "Desarrollador Frontend",
          null);
    List<String> resultado = controladorUsuario
          .listaOfertasUsuario("maro");
    List<String> esperado = new ArrayList<String>();
    esperado.add("Desarrollador Frontend");
    Assert.assertEquals(esperado, resultado);

  }

  @Test
  public void editarPostulanteTest()
        throws UsuarioYaExisteException,
        UsuarioEmailRepetidoException,
        UsuarioNoExisteException {
    controladorUsuario.altaPostulante("NicknameTest",
          "NombreTest", "ApellidoTest", "EmailTest",
          fechaDate1, "NacionalidadTest", null, "1234");
    LocalDate fechaNueva = LocalDate.parse("2023-09-24");
    controladorUsuario.editarPostulante("NicknameTest",
          "nombreNuevo", "apellidoNuevo", fechaNueva,
          "nacionalidadNueva", null, "contrasenia");
    Postulante postulanteResultado = controladorUsuario
          .obtenerPostulante("NicknameTest");
    Assert.assertEquals("NicknameTest",
          postulanteResultado.getNickname());
    Assert.assertEquals("nombreNuevo",
          postulanteResultado.getNombre());
    Assert.assertEquals("apellidoNuevo",
          postulanteResultado.getApellido());
    Assert.assertEquals(fechaNueva,
          postulanteResultado.getFechaNacimiento());
    Assert.assertEquals("nacionalidadNueva",
          postulanteResultado.getNacionalidad());
    Assert.assertEquals("contrasenia",
          postulanteResultado.getContrasenia());
  }

  @Test
  public void editarEmrpesaTest()
        throws UsuarioYaExisteException,
        UsuarioEmailRepetidoException,
        UsuarioNoExisteException {
    controladorUsuario.altaEmpresa("EcoTech", "Sophia",
          "Johnson", "info@EcoTech.com",
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
          "http://www.EcoTechInnovations.com", null,
          "1234");
    controladorUsuario.editarEmpresa("EcoTech",
          "nombreNuevo", "apellidoNuevo", "sitioWebNuevo",
          "descripcionNueva", null, "contrasenia");
    Empresa empresaResultado = controladorUsuario
          .obtenerEmpresa("EcoTech");
    Assert.assertEquals("EcoTech",
          empresaResultado.getNickname());
    Assert.assertEquals("nombreNuevo",
          empresaResultado.getNombre());
    Assert.assertEquals("apellidoNuevo",
          empresaResultado.getApellido());
    Assert.assertEquals("sitioWebNuevo".toLowerCase(),
          empresaResultado.getSitioWeb());
    Assert.assertEquals("descripcionNueva",
          empresaResultado.getDescripcion());
    Assert.assertEquals("contrasenia",
          empresaResultado.getContrasenia());

  }

  @Test
  public void obtenerDtPostulacionTest()
        throws UsuarioYaExisteException,
        UsuarioEmailRepetidoException,
        KeywordYaExisteException,
        TipoPublicacionYaExisteException,
        OfertaLaboralYaExisteException,
        TipoPublicacionNoExisteException,
        KeywordNoExisteException, UsuarioNoExisteException,
        OfertaLaboralNoExisteException,
        UsuarioNoExistePostulacion,
        UsuarioYaExistePostulacion {
    controladorUsuario.altaEmpresa("EcoTech", "Sophia",
          "Johnson", "info@EcoTech.com",
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
          "http://www.EcoTechInnovations.com", null,
          "1234");
    controladorOferta.altaKeyword("keyword");
    List<String> keywords = new ArrayList<String>();
    keywords.add("keyword");
    controladorOferta.altaTipoPublicacion("Premium",
          "Obtén máxima visibilidad", "1", 30, 4000f,
          fechaDate2);
    controladorOferta.altaOfertaLaboral(
          "Desarrollador Frontend",
          "Únete a nuestro equipo de desarrollo frontend y crea "
                + "experiencias de usuario excepcionales.",
          "09:00", "18:00", 90000f, "Montevideo",
          "Montevideo", fechaDate1, "Premium", "EcoTech",
          keywords, null, null);
    controladorUsuario.altaPostulante("maro", "María",
          "Rodríguez", "marrod@gmail.com", fechaDate2,
          "Uruguaya", null, "1234");
    controladorUsuario.registrarPostulacion(
          "Ingeniero en Sistemas, experiencia en desarrollo web y "
                + "aplicaciones móviles. Conocimientos en JavaScript y React.",
          "Me entusiasma la posibilidad de trabajar en proyectos "
                + "desafiantes y seguir creciendo como profesional en el campo de la tecnología.",
          fechaDate1, "maro", "Desarrollador Frontend",
          null);
    DtPostulacion postulacionResultado = controladorUsuario
          .obtenerDtPostulacion("maro",
                "Desarrollador Frontend");
    Assert.assertEquals("maro",
          postulacionResultado.getnicknamePostulante());
    Assert.assertEquals(
          "Me entusiasma la posibilidad de trabajar en proyectos "
                + "desafiantes y seguir creciendo como profesional en el campo de la tecnología.",
          postulacionResultado.getDescripMotivacion());
    Assert.assertEquals(fechaDate1,
          postulacionResultado.getFechaPostulacion());
    Assert.assertEquals(
          "Ingeniero en Sistemas, experiencia en desarrollo web y "
                + "aplicaciones móviles. Conocimientos en JavaScript y React.",
          postulacionResultado.getCvReducido());

  }

  @Test
  public void obtenerDtOfertasIngresadasTest()
        throws UsuarioYaExisteException,
        UsuarioEmailRepetidoException,
        KeywordYaExisteException,
        TipoPublicacionYaExisteException,
        OfertaLaboralYaExisteException,
        TipoPublicacionNoExisteException,
        KeywordNoExisteException, UsuarioNoExisteException,
        IOException {
    controladorUsuario.altaEmpresa("EcoTech", "Sophia",
          "Johnson", "info@EcoTech.com",
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
          "http://www.EcoTechInnovations.com", null,
          "1234");
    controladorOferta.altaKeyword("keyword");
    List<String> keywords = new ArrayList<String>();
    keywords.add("keyword");
    controladorOferta.altaTipoPublicacion("Premium",
          "Obtén máxima visibilidad", "1", 30, 4000f,
          fechaDate2);
    controladorOferta.altaOfertaLaboral(
          "Desarrollador Frontend",
          "Únete a nuestro equipo de desarrollo frontend y crea "
                + "experiencias de usuario excepcionales.",
          "09:00", "18:00", 90000f, "Montevideo",
          "Montevideo", fechaDate1, "Premium", "EcoTech",
          keywords, null, null);
    List<DtOfertaLaboral> listaResultado =
          controladorUsuario
                .obtenerDtOfertasIngresadasDeEmpresa(
                      "EcoTech");
    Assert.assertEquals(1, listaResultado.size());
    DtOfertaLaboral oferta = listaResultado.get(0);
    Assert.assertEquals("Desarrollador Frontend",
          oferta.getNombre());
    Assert.assertEquals(
          "Únete a nuestro equipo de desarrollo frontend y crea "
                + "experiencias de usuario excepcionales.",
          oferta.getDescripcion());
    Assert.assertEquals("09:00", oferta.getHorarioInicio());
    Assert.assertEquals("18:00", oferta.getHorarioFinal());
    Assert.assertEquals(String.valueOf(90000f),
          String.valueOf(oferta.getRemuneracion()));
    Assert.assertEquals("Montevideo",
          oferta.getDepartamento());
    Assert.assertEquals("Montevideo", oferta.getCiudad());
    Assert.assertEquals("EcoTech", oferta.getEmpresa());
    Assert.assertEquals(keywords.get(0),
          oferta.getKeywords().get(0));
    Assert.assertEquals(null, oferta.getImagen());

  }

  @Test
  public void obtenerDtOfertasConfirmadasTest()
        throws UsuarioYaExisteException,
        UsuarioEmailRepetidoException,
        KeywordYaExisteException,
        OfertaLaboralYaExisteException,
        TipoPublicacionNoExisteException,
        KeywordNoExisteException, UsuarioNoExisteException,
        TipoPublicacionYaExisteException,
        OfertaLaboralNoExisteException, IOException {
    controladorUsuario.altaEmpresa("EcoTech", "Sophia",
          "Johnson", "info@EcoTech.com",
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
          "http://www.EcoTechInnovations.com", null,
          "1234");
    controladorOferta.altaKeyword("keyword");
    List<String> keywords = new ArrayList<String>();
    keywords.add("keyword");
    controladorOferta.altaTipoPublicacion("Premium",
          "Obtén máxima visibilidad", "1", 30, 4000f,
          fechaDate2);
    controladorOferta.altaOfertaLaboral(
          "Desarrollador Frontend",
          "Únete a nuestro equipo de desarrollo frontend y crea "
                + "experiencias de usuario excepcionales.",
          "09:00", "18:00", 90000f, "Montevideo",
          "Montevideo", fechaDate1, "Premium", "EcoTech",
          keywords, null, null);
    controladorOferta.aceptarRechazarOfertaLaboral(
          "Desarrollador Frontend", EstadoOferta.CONFIRMADA,
          fechaDate1);
    List<DtOfertaLaboral> listaResultado =
          controladorUsuario
                .obtenerDtOfertasConfirmadasDeEmpresa(
                      "EcoTech");
    Assert.assertEquals(1, listaResultado.size());
    DtOfertaLaboral oferta = listaResultado.get(0);
    Assert.assertEquals("Desarrollador Frontend",
          oferta.getNombre());
    Assert.assertEquals(
          "Únete a nuestro equipo de desarrollo frontend y crea "
                + "experiencias de usuario excepcionales.",
          oferta.getDescripcion());
    Assert.assertEquals("09:00", oferta.getHorarioInicio());
    Assert.assertEquals("18:00", oferta.getHorarioFinal());
    Assert.assertEquals(String.valueOf(90000f),
          String.valueOf(oferta.getRemuneracion()));
    Assert.assertEquals("Montevideo",
          oferta.getDepartamento());
    Assert.assertEquals("Montevideo", oferta.getCiudad());

  }

  @Test
  public void obtenerDtOfertasRechazadasTest()
        throws UsuarioYaExisteException,
        UsuarioEmailRepetidoException,
        KeywordYaExisteException,
        TipoPublicacionYaExisteException,
        OfertaLaboralYaExisteException,
        TipoPublicacionNoExisteException,
        KeywordNoExisteException, UsuarioNoExisteException,
        OfertaLaboralNoExisteException, IOException {
    controladorUsuario.altaEmpresa("EcoTech", "Sophia",
          "Johnson", "info@EcoTech.com",
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
          "http://www.EcoTechInnovations.com", null,
          "1234");
    controladorOferta.altaKeyword("keyword");
    List<String> keywords = new ArrayList<String>();
    keywords.add("keyword");
    controladorOferta.altaTipoPublicacion("Premium",
          "Obtén máxima visibilidad", "1", 30, 4000f,
          fechaDate2);
    controladorOferta.altaOfertaLaboral(
          "Desarrollador Frontend",
          "Únete a nuestro equipo de desarrollo frontend y crea experiencias"
                + " de usuario excepcionales.",
          "09:00", "18:00", 90000f, "Montevideo",
          "Montevideo", fechaDate1, "Premium", "EcoTech",
          keywords, null, null);
    controladorOferta.aceptarRechazarOfertaLaboral(
          "Desarrollador Frontend", EstadoOferta.RECHAZADA,
          fechaDate1);
    List<DtOfertaLaboral> listaResultado =
          controladorUsuario
                .obtenerDtOfertasRechazadasDeEmpresa(
                      "EcoTech");
    Assert.assertEquals(1, listaResultado.size());
    DtOfertaLaboral oferta = listaResultado.get(0);
    Assert.assertEquals("Desarrollador Frontend",
          oferta.getNombre());
    Assert.assertEquals(
          "Únete a nuestro equipo de desarrollo frontend y crea experiencias "
                + "de usuario excepcionales.",
          oferta.getDescripcion());
    Assert.assertEquals("09:00", oferta.getHorarioInicio());
    Assert.assertEquals("18:00", oferta.getHorarioFinal());
    Assert.assertEquals(String.valueOf(90000f),
          String.valueOf(oferta.getRemuneracion()));
    Assert.assertEquals("Montevideo",
          oferta.getDepartamento());
    Assert.assertEquals("Montevideo", oferta.getCiudad());
  }

  @Test
  public void confirmarContraseniaTest()
        throws UsuarioYaExisteException,
        UsuarioEmailRepetidoException,
        UsuarioNoExisteException {
    controladorUsuario.altaEmpresa("EcoTech", "Sophia",
          "Johnson", "info@EcoTech.com",
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
          "http://www.EcoTechInnovations.com", null,
          "1234");

    Assert.assertTrue(controladorUsuario
          .confirmarContrasenia("EcoTech", "1234"));
    Assert
          .assertTrue(
                controladorUsuario.confirmarContrasenia(
                      "info@EcoTech.com".toLowerCase(),
                      "1234"));
    Assert.assertFalse(controladorUsuario
          .confirmarContrasenia("EcoTech", "pepe"));
    Assert.assertFalse(
          controladorUsuario.confirmarContrasenia(
                "info@EcoTech.com".toLowerCase(), "pepe"));

  }

  @Test
  public void obtenerDtUsuariosTest()
        throws UsuarioYaExisteException,
        UsuarioEmailRepetidoException,
        UsuarioNoExisteException, IOException {
    controladorUsuario.altaEmpresa("EcoTech", "Sophia",
          "Johnson", "info@EcoTech.com",
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
          "http://www.EcoTechInnovations.com", null,
          "1234");
    controladorUsuario.altaPostulante("maro", "María",
          "Rodríguez", "marrod@gmail.com", fechaDate2,
          "Uruguaya", null, "1234");
    List<DtUsuario> listaRes = controladorUsuario
          .obtenerDtUsuarios();
    DtPostulante postulante =
          (DtPostulante) controladorUsuario
                .obtenerDtUsuario("maro");
    DtEmpresa empresa = (DtEmpresa) controladorUsuario
          .obtenerDtUsuario("EcoTech");
    for (DtUsuario usuario : listaRes) {
      if (usuario.getNickname() == empresa.getNickname()) {
        DtEmpresa dtempresa = (DtEmpresa) usuario;
        Assert.assertEquals(dtempresa.getNickname(),
              empresa.getNickname());
        Assert.assertEquals(dtempresa.getNombre(),
              empresa.getNombre());
        Assert.assertEquals(dtempresa.getApellido(),
              empresa.getApellido());
        Assert.assertEquals(dtempresa.getEmail(),
              empresa.getEmail());
        Assert.assertEquals(dtempresa.getContrasenia(),
              empresa.getContrasenia());
        Assert.assertEquals(dtempresa.getDescripcion(),
              empresa.getDescripcion());
        Assert.assertEquals(dtempresa.getSitioWeb(),
              empresa.getSitioWeb());
        Assert.assertEquals(
              dtempresa.getOfertasColeccion().size(),
              empresa.getOfertasColeccion().size());
        Assert.assertEquals(
              dtempresa.getOfertasLaborales().size(),
              empresa.getOfertasLaborales().size());
        Assert.assertEquals(dtempresa.getImagen(),
              empresa.getImagen());

      } else if (usuario.getNickname() == postulante
            .getNickname()) {
        DtPostulante dtpostulante = (DtPostulante) usuario;
        Assert.assertEquals(dtpostulante.getNickname(),
              postulante.getNickname());
        Assert.assertEquals(dtpostulante.getNombre(),
              postulante.getNombre());
        Assert.assertEquals(dtpostulante.getApellido(),
              postulante.getApellido());
        Assert.assertEquals(dtpostulante.getEmail(),
              postulante.getEmail());
        Assert.assertEquals(dtpostulante.getContrasenia(),
              postulante.getContrasenia());
        Assert.assertEquals(
              dtpostulante.getFechaNacimiento(),
              postulante.getFechaNacimiento());
        Assert.assertEquals(dtpostulante.getNacionalidad(),
              postulante.getNacionalidad());
        Assert.assertEquals(
              dtpostulante.getOfertasColeccion().size(),
              postulante.getOfertasColeccion().size());
      }
    }
  }

  @Test
  public void comprarPaqueteTest()
        throws UsuarioYaExisteException,
        UsuarioEmailRepetidoException,
        TipoPublicacionYaExisteException,
        PaquetePublicacionYaExisteException,
        TipoPublicacionNoExisteException,
        UsuarioNoExisteException,
        PaquetePublicacionNoExisteException, IOException {
    controladorUsuario.altaEmpresa("EcoTech", "Sophia",
          "Johnson", "info@EcoTech.com",
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
          "http://www.EcoTechInnovations.com", null,
          "1234");
    controladorOferta.altaTipoPublicacion("Premium",
          "Obtén máxima visibilidad", "1", 30, 4000f,
          fechaDate2);
    DtCantidadTipoPublicacion dtCantidadTipo =
          new DtCantidadTipoPublicacion(
                "Premium", 2);
    List<DtCantidadTipoPublicacion> listaCantidad =
          new ArrayList<DtCantidadTipoPublicacion>();
    listaCantidad.add(dtCantidadTipo);
    controladorOferta.registrarPaquete("Paquete",
          "Descripcion", 10, 20f, null, fechaDate1,
          listaCantidad);
    controladorUsuario.comprarPaquete("EcoTech", "Paquete",
          fechaDate2);
    List<DtPaquetePublicacion> listaRes = controladorUsuario
          .obtenerDtPaquetesDeEmpresa("EcoTech");
    DtPaquetePublicacion paquete = listaRes.get(0);
    Assert.assertEquals("Paquete", paquete.getNombre());
    Assert.assertEquals("Descripcion",
          paquete.getDescripcion());
    Assert.assertEquals(10, paquete.getPeriodoValidez());
    Assert.assertEquals(String.valueOf(20f),
          String.valueOf(paquete.getDescuento()));
    Assert.assertEquals(fechaDate1, paquete.getFechaAlta());

  }

  @Test
  public void obtenerPostulacionesTest()
        throws UsuarioYaExisteException,
        UsuarioEmailRepetidoException,
        KeywordYaExisteException,
        TipoPublicacionYaExisteException,
        OfertaLaboralYaExisteException,
        TipoPublicacionNoExisteException,
        KeywordNoExisteException, UsuarioNoExisteException,
        OfertaLaboralNoExisteException,
        UsuarioYaExistePostulacion {
    controladorUsuario.altaEmpresa("EcoTech", "Sophia",
          "Johnson", "info@EcoTech.com",
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
          "http://www.EcoTechInnovations.com", null,
          "1234");
    controladorOferta.altaKeyword("keyword");
    List<String> keywords = new ArrayList<String>();
    keywords.add("keyword");
    controladorOferta.altaTipoPublicacion("Premium",
          "Obtén máxima visibilidad", "1", 30, 4000f,
          fechaDate2);
    controladorOferta.altaOfertaLaboral(
          "Desarrollador Frontend",
          "Únete a nuestro equipo de desarrollo frontend y crea "
                + "experiencias de usuario excepcionales.",
          "09:00", "18:00", 90000f, "Montevideo",
          "Montevideo", fechaDate1, "Premium", "EcoTech",
          keywords, null, null);
    controladorUsuario.altaPostulante("maro", "María",
          "Rodríguez", "marrod@gmail.com", fechaDate2,
          "Uruguaya", null, "1234");
    controladorUsuario.registrarPostulacion(
          "Ingeniero en Sistemas, experiencia en desarrollo web y "
                + "aplicaciones móviles. Conocimientos en JavaScript y React.",
          "Me entusiasma la posibilidad de trabajar en proyectos "
                + "desafiantes y seguir creciendo como profesional en "
                + "el campo de la tecnología.",
          fechaDate1, "maro", "Desarrollador Frontend",
          null);
    List<DtPostulacion> listaRes = controladorUsuario
          .obtenerDtPostulacionesDePostulante("maro");
    DtPostulacion postulacion = listaRes.get(0);
    Assert.assertEquals("maro",
          postulacion.getnicknamePostulante());
    Assert.assertEquals(fechaDate1,
          postulacion.getFechaPostulacion());
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
  public void listarPaquetesNoCompradosTest()
        throws UsuarioYaExisteException,
        UsuarioEmailRepetidoException,
        TipoPublicacionYaExisteException,
        PaquetePublicacionYaExisteException,
        TipoPublicacionNoExisteException,
        UsuarioNoExisteException,
        PaquetePublicacionNoExisteException, IOException {
    controladorUsuario.altaEmpresa("EcoTech", "Sophia",
          "Johnson", "info@EcoTech.com",
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
          "http://www.EcoTechInnovations.com", null,
          "1234");
    controladorOferta.altaTipoPublicacion("Premium",
          "Obtén máxima visibilidad", "1", 30, 4000f,
          fechaDate2);
    DtCantidadTipoPublicacion dtCantidadTipo =
          new DtCantidadTipoPublicacion(
                "Premium", 2);
    List<DtCantidadTipoPublicacion> listaCantidad =
          new ArrayList<DtCantidadTipoPublicacion>();
    listaCantidad.add(dtCantidadTipo);
    controladorOferta.registrarPaquete("Paquete",
          "Descripcion", 10, 20f, null, fechaDate1,
          listaCantidad);
    controladorUsuario.comprarPaquete("EcoTech", "Paquete",
          fechaDate2);
    controladorOferta.registrarPaquete("Paquete2",
          "Descripcion", 10, 20f, null, fechaDate1,
          listaCantidad);
    controladorOferta.registrarPaquete("Paquete3",
          "Descripcion", 10, 20f, null, fechaDate1,
          listaCantidad);
    List<String> listaEsperada = new ArrayList<String>();
    listaEsperada.add("Paquete2");
    listaEsperada.add("Paquete3");
    Collections.sort(listaEsperada);
    List<String> listaRes = controladorUsuario
          .listarPaquetesNoCompradosDeEmpresa("EcoTech");
    Collections.sort(listaRes);
    Assert.assertEquals(listaEsperada, listaRes);

    List<DtCompraPaquete> listaCompra =
          (ArrayList<DtCompraPaquete>) controladorUsuario
                .obtenerDtCompraPaqueteDeEmpresa("EcoTech");
    DtCompraPaquete dtcompra = listaCompra.get(0);
    Assert.assertEquals(dtcompra.getPaquete().getNombre(),
          "Paquete");
    Assert.assertEquals(dtcompra.getFechaCompra(),
          fechaDate2);
    Assert.assertEquals(dtcompra.getPublicacionesRestantes()
          .get(0).getCantidad(), 2);
    LocalDate fechaVencimiento = LocalDate.of(
          fechaDate2.getYear(), fechaDate2.getMonthValue(),
          fechaDate2.getDayOfMonth()).plusDays(10);
    Assert.assertEquals(dtcompra.getFechaVencimiento(),
          fechaVencimiento);
    DtCantidadTipoPublicacionRestante dtCantidadRestante =
          dtcompra
                .getPublicacionesRestantes().get(0);
    DtTipoPublicacion dttipo = dtCantidadRestante
          .getTipoPublicacion();
    Assert.assertEquals(String.valueOf(dttipo.getCosto()),
          String.valueOf(4000f));
    Assert.assertEquals(dttipo.getDescripcion(),
          "Obtén máxima visibilidad");
    Assert.assertEquals(dttipo.getDuracionDia(), 30);
    Assert.assertEquals(dttipo.getExposicion(), "1");
    Assert.assertEquals(dttipo.getFechaAlta(), fechaDate2);

  }

  @Test
  public void postulacionesTest()
        throws UsuarioYaExisteException,
        UsuarioEmailRepetidoException,
        KeywordYaExisteException,
        TipoPublicacionYaExisteException,
        OfertaLaboralYaExisteException,
        TipoPublicacionNoExisteException,
        KeywordNoExisteException, UsuarioNoExisteException,
        OfertaLaboralNoExisteException,
        UsuarioYaExistePostulacion {
    controladorUsuario.altaEmpresa("EcoTech", "Sophia",
          "Johnson", "info@EcoTech.com",
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
          "http://www.EcoTechInnovations.com", null,
          "1234");
    controladorOferta.altaKeyword("keyword");
    List<String> keywords = new ArrayList<String>();
    keywords.add("keyword");
    controladorOferta.altaTipoPublicacion("Premium",
          "Obtén máxima visibilidad", "1", 30, 4000f,
          fechaDate2);
    controladorOferta.altaOfertaLaboral(
          "Desarrollador Frontend",
          "Únete a nuestro equipo de desarrollo frontend y crea "
                + "experiencias de usuario excepcionales.",
          "09:00", "18:00", 90000f, "Montevideo",
          "Montevideo", fechaDate1, "Premium", "EcoTech",
          keywords, null, null);
    controladorUsuario.altaPostulante("maro", "María",
          "Rodríguez", "marrod@gmail.com", fechaDate2,
          "Uruguaya", null, "1234");
    Boolean noHayPostulacion = false;
    Boolean postulacionRepetida = false;
    try {

      DtPostulacion dtpostulacion = controladorUsuario
            .obtenerDtPostulacion("maro",
                  "Desarrollador Frontend");
    } catch (UsuarioNoExistePostulacion e) {
      noHayPostulacion = true;
    }
    controladorUsuario.registrarPostulacion(
          "Ingeniero en Sistemas, experiencia en desarrollo web y "
                + "aplicaciones móviles. Conocimientos en JavaScript y React.",
          "Me entusiasma la posibilidad de trabajar en proyectos "
                + "desafiantes y seguir creciendo como profesional en "
                + "el campo de la tecnología.",
          fechaDate1, "maro", "Desarrollador Frontend",
          null);

    try {
      controladorUsuario.registrarPostulacion(
            "Ingeniero en Sistemas, experiencia en desarrollo web y "
                  + "aplicaciones móviles. Conocimientos en JavaScript y React.",
            "Me entusiasma la posibilidad de trabajar en proyectos "
                  + "desafiantes y seguir creciendo como profesional en "
                  + "el campo de la tecnología.",
            fechaDate1, "maro", "Desarrollador Frontend",
            null);
    } catch (UsuarioYaExistePostulacion e) {
      postulacionRepetida = true;
    }
    Assert.assertTrue(noHayPostulacion);
    Assert.assertTrue(postulacionRepetida);

  }

  @Test
  public void testObtenerDtOfertasFinalizadasDeEmpresa()
        throws UsuarioNoExisteException, IOException,
        UsuarioYaExisteException,
        UsuarioEmailRepetidoException,
        KeywordYaExisteException,
        TipoPublicacionYaExisteException,
        OfertaLaboralYaExisteException,
        TipoPublicacionNoExisteException,
        KeywordNoExisteException,
        OfertaLaboralNoExisteException,
        OfertaLaboralNoSePuedeFinalizar {
    controladorUsuario.altaEmpresa("EcoTech", "Sophia",
          "Johnson", "info@EcoTech.com",
          "EcoTech Innovations es una empresa líder en soluciones ",
          "http://www.EcoTechInnovations.com", null,
          "1234");
    controladorOferta.altaKeyword("keyword");
    List<String> keywords = new ArrayList<String>();
    keywords.add("keyword");
    controladorOferta.altaTipoPublicacion("Premium",
          "Obtén máxima visibilidad", "1", 30, 4000f,
          fechaDate2);
    controladorOferta.altaOfertaLaboral(
          "Desarrollador Frontend",
          "Únete a nuestro equipo de desarrollo",
          "09:00", "18:00", 90000f, "Montevideo",
          "Montevideo", fechaDate1, "Premium", "EcoTech",
          keywords, null, null);

    controladorOferta.altaOfertaLaboral(
          "Desarrollador Frontend secundaria",
          "Únete a nuestro equipo de desarrollo",
          "09:00", "18:00", 90000f, "Montevideo",
          "Montevideo", fechaDate1, "Premium", "EcoTech",
          keywords, null, null);

    controladorOferta.aceptarRechazarOfertaLaboral(
          "Desarrollador Frontend", EstadoOferta.CONFIRMADA,
          fechaDate1);

    controladorOferta
          .finalizarOferta("Desarrollador Frontend");

    OfertaLaboral resultado = controladorOferta
          .obtenerOfertaLaboral("Desarrollador Frontend");

    List<DtOfertaLaboral> listOfertasFinalizadas =
          controladorUsuario
                .obtenerDtOfertasFinalizadasDeEmpresa(
                      "EcoTech");

    Assert.assertEquals(listOfertasFinalizadas.size(), 1);
    Assert.assertEquals(
          listOfertasFinalizadas.get(0).getNombre(),
          "Desarrollador Frontend");

  }

  @Test
  public void testAgregarSeguidor()
        throws UsuarioYaExisteException,
        UsuarioEmailRepetidoException,
        TipoPublicacionYaExisteException,
        KeywordYaExisteException, UsuarioNoExisteException {
    controladorUsuario.altaEmpresa("EcoTech", "Sophia",
          "Johnson", "info@EcoTech.com",
          "EcoTech Innovations es una empresa líder en soluciones ",
          "http://www.EcoTechInnovations.com", null,
          "1234");
    controladorOferta.altaKeyword("keyword");
    List<String> keywords = new ArrayList<String>();
    keywords.add("keyword");
    controladorOferta.altaTipoPublicacion("Premium",
          "Obtén máxima visibilidad", "1", 30, 4000f,
          fechaDate2);
    controladorUsuario.altaEmpresa("EcoTechSecunadria",
          "Sophia",
          "Johnson", "info@EcoTechSecundaria.com",
          "EcoTech Innovations es una empresa líder en soluciones ",
          "http://www.EcoTechInnovations.com", null,
          "1234");

    try {
      controladorUsuario.agregarSeguidor("EcoTech",
            "EcoTechSecunadria");
    } catch (UsuarioNoExisteException
          | UsuarioYaEstaSeguidoException e) {
      // TODO Auto-generated catch block
    }
    Usuario ecoTech =
          controladorUsuario.obtenerUsuario("EcoTech");

    Usuario ecoTechSecundaria =
          controladorUsuario
                .obtenerUsuario("EcoTechSecunadria");

    Set<String> seguidos = ecoTechSecundaria.getSeguidos();

    Set<String> seguidores = ecoTech.getSeguidores();

    Assert.assertEquals(
          seguidos.size(), 1);

    Assert.assertEquals(
          seguidos.contains("EcoTech"), true);

    Assert.assertEquals(
          seguidores.size(), 1);

    Assert.assertEquals(
          seguidores.contains("EcoTechSecunadria"), true);

    try {
      controladorUsuario.agregarSeguidor("EcoTech",
            "EcoTechSecunadria");
    } catch (UsuarioNoExisteException e) {
      // TODO Auto-generated catch block
    } catch (UsuarioYaEstaSeguidoException e) {
      Assert.assertEquals(
            "El usuario EcoTech ya está seguido por EcoTechSecunadria",
            e.getMessage());
    }
  }

  @Test
  public void testDejarDeSeguir()
        throws UsuarioYaExisteException,
        UsuarioEmailRepetidoException,
        KeywordYaExisteException,
        TipoPublicacionYaExisteException,
        UsuarioNoExisteException,
        UsuarioYaEstaSeguidoException,
        UsuarioNoEstaSeguidoException {
    controladorUsuario.altaEmpresa("EcoTech", "Sophia",
          "Johnson", "info@EcoTech.com",
          "EcoTech Innovations es una empresa líder en soluciones ",
          "http://www.EcoTechInnovations.com", null,
          "1234");
    controladorOferta.altaKeyword("keyword");
    List<String> keywords = new ArrayList<String>();
    keywords.add("keyword");
    controladorOferta.altaTipoPublicacion("Premium",
          "Obtén máxima visibilidad", "1", 30, 4000f,
          fechaDate2);
    controladorUsuario.altaEmpresa("EcoTechSecunadria",
          "Sophia",
          "Johnson", "info@EcoTechSecundaria.com",
          "EcoTech Innovations es una empresa líder en soluciones ",
          "http://www.EcoTechInnovations.com", null,
          "1234");

    try {
      controladorUsuario.dejarDeSeguir("EcoTech",
            "EcoTechSecunadria");
    } catch (UsuarioNoExisteException e) {
      // TODO Auto-generated catch block
    } catch (UsuarioNoEstaSeguidoException e) {
      Assert.assertEquals(e.getMessage(),
            "El usuario EcoTech no está seguido por EcoTechSecunadria");
    }

    controladorUsuario.agregarSeguidor("EcoTech",
          "EcoTechSecunadria");

    Usuario ecoTech =
          controladorUsuario.obtenerUsuario("EcoTech");

    Usuario ecoTechSecundaria =
          controladorUsuario
                .obtenerUsuario("EcoTechSecunadria");

    Set<String> seguidos = ecoTechSecundaria.getSeguidos();

    Set<String> seguidores = ecoTech.getSeguidores();

    Assert.assertEquals(
          seguidos.size(), 1);

    Assert.assertEquals(
          seguidos.contains("EcoTech"), true);

    Assert.assertEquals(
          seguidores.size(), 1);

    Assert.assertEquals(
          seguidores.contains("EcoTechSecunadria"), true);

    controladorUsuario.dejarDeSeguir("EcoTech",
          "EcoTechSecunadria");

    seguidos = ecoTechSecundaria.getSeguidos();

    seguidores = ecoTech.getSeguidores();

    Assert.assertEquals(
          seguidos.size(), 0);

    Assert.assertEquals(
          seguidos.contains("EcoTech"), false);

    Assert.assertEquals(
          seguidores.size(), 0);

    Assert.assertEquals(
          seguidores.contains("EcoTechSecunadria"), false);

  }

  @Test
  public void testAgregarOfertaFavorita()
        throws UsuarioYaExisteException,
        UsuarioEmailRepetidoException,
        TipoPublicacionYaExisteException,
        KeywordYaExisteException,
        OfertaLaboralYaExisteException,
        TipoPublicacionNoExisteException,
        KeywordNoExisteException, UsuarioNoExisteException {
    controladorOferta.altaTipoPublicacion("tipoTesting",
          "Uso para testing", "baja", 50, 500f, fechaDate1);
    controladorUsuario.altaEmpresa("nicknameEmpresa1",
          "nombre1", "apellido1", "email1@test.com",
          "descripcion1", "sitioWeb1", null,
          "nuevaContraseña");

    List<String> listaKeyword = new ArrayList<String>();
    listaKeyword.add("Keyword1");
    listaKeyword.add("Keyword2");
    Collections.sort(listaKeyword);
    controladorOferta.altaKeyword("Keyword1");
    controladorOferta.altaKeyword("Keyword2");

    controladorOferta.altaOfertaLaboral("test",
          "descipcionTest", "09:00", "15:00", 500f,
          "Montevideo", "Montevideo", fechaDate1,
          "tipoTesting", "nicknameEmpresa1", listaKeyword,
          null, null);

    controladorUsuario.altaPostulante("NicknameTest",
          "NombreTest", "ApellidoTest", "EmailTest",
          fechaDate1, "NacionalidadTest", null, "1234");

    try {
      controladorUsuario.agregarOfertaFavorita(
            "NicknameTest",
            "test");
    } catch (UsuarioNoExisteException
          | PostulanteYaEsOfertaFavoritaException
          | OfertaLaboralNoExisteException e) {
      // TODO Auto-generated catch block
    }

    Postulante postulante = (Postulante) controladorUsuario
          .obtenerUsuario("NicknameTest");

    Assert.assertEquals(postulante.esOfertaFavorita("test"),
          true);

    try {
      controladorUsuario.agregarOfertaFavorita(
            "NicknameTest",
            "test");
    } catch (UsuarioNoExisteException e) {
      // TODO Auto-generated catch block
    } catch (PostulanteYaEsOfertaFavoritaException e) {
      Assert.assertEquals(
            "El postulante NicknameTest ya tiene como favorita la oferta test",
            e.getMessage());
    } catch (OfertaLaboralNoExisteException e) {
      // TODO Auto-generated catch block
    }

    try {
      controladorUsuario.agregarOfertaFavorita(
            "NicknameTest",
            "testFinal");
    } catch (UsuarioNoExisteException e) {
      // TODO Auto-generated catch block
    } catch (PostulanteYaEsOfertaFavoritaException e) {
      // TODO Auto-generated catch block
    } catch (OfertaLaboralNoExisteException e) {
      Assert.assertEquals(e.getMessage(),
            "La oferta laboral testFinal no existe");
    }

  }

  @Test
  public void testRemoverOfertaFavorita()
        throws TipoPublicacionYaExisteException,
        UsuarioYaExisteException,
        UsuarioEmailRepetidoException,
        KeywordYaExisteException,
        OfertaLaboralYaExisteException,
        TipoPublicacionNoExisteException,
        KeywordNoExisteException, UsuarioNoExisteException,
        PostulanteNoEsOfertaFavoritaException,
        OfertaLaboralNoExisteException {
    controladorOferta.altaTipoPublicacion("tipoTesting",
          "Uso para testing", "baja", 50, 500f, fechaDate1);
    controladorUsuario.altaEmpresa("nicknameEmpresa1",
          "nombre1", "apellido1", "email1@test.com",
          "descripcion1", "sitioWeb1", null,
          "nuevaContraseña");

    List<String> listaKeyword = new ArrayList<String>();
    listaKeyword.add("Keyword1");
    listaKeyword.add("Keyword2");
    Collections.sort(listaKeyword);
    controladorOferta.altaKeyword("Keyword1");
    controladorOferta.altaKeyword("Keyword2");

    controladorOferta.altaOfertaLaboral("test",
          "descipcionTest", "09:00", "15:00", 500f,
          "Montevideo", "Montevideo", fechaDate1,
          "tipoTesting", "nicknameEmpresa1", listaKeyword,
          null, null);

    controladorUsuario.altaPostulante("NicknameTest",
          "NombreTest", "ApellidoTest", "EmailTest",
          fechaDate1, "NacionalidadTest", null, "1234");

    try {
      controladorUsuario.removerOfertaFavorita(
            "NicknameTest",
            "testFinal");
    } catch (UsuarioNoExisteException e) {
      // TODO Auto-generated catch block
    } catch (PostulanteNoEsOfertaFavoritaException e) {
      Assert.assertEquals(
            "La oferta testFinal no es una oferta favorita de NicknameTest",
            e.getMessage());
    }

    Postulante postulante = (Postulante) controladorUsuario
          .obtenerUsuario("NicknameTest");

    try {
      controladorUsuario.agregarOfertaFavorita(
            "NicknameTest",
            "test");
    } catch (UsuarioNoExisteException e) {
      // TODO Auto-generated catch block

    } catch (PostulanteYaEsOfertaFavoritaException e) {
      // TODO Auto-generated catch block

    } catch (OfertaLaboralNoExisteException e) {
      // TODO Auto-generated catch block

    }

    Assert.assertEquals(postulante.esOfertaFavorita("test"),
          true);

    controladorUsuario.removerOfertaFavorita(
          "NicknameTest",
          "test");

    Assert.assertEquals(postulante.esOfertaFavorita("test"),
          false);
  }

  @Test
  public void testObtenerDatosPdf()
        throws TipoPublicacionYaExisteException,
        UsuarioYaExisteException,
        UsuarioEmailRepetidoException,
        KeywordYaExisteException,
        OfertaLaboralYaExisteException,
        TipoPublicacionNoExisteException,
        KeywordNoExisteException, UsuarioNoExisteException,
        UsuarioYaExistePostulacion,
        OfertaLaboralNoExisteException {

    controladorOferta.altaTipoPublicacion("tipoTesting",
          "Uso para testing", "baja", 50, 500f, fechaDate1);
    controladorUsuario.altaEmpresa("nicknameEmpresa1",
          "nombre1", "apellido1", "email1@test.com",
          "descripcion1", "sitioWeb1", null,
          "nuevaContraseña");

    List<String> listaKeyword = new ArrayList<String>();
    listaKeyword.add("Keyword1");
    listaKeyword.add("Keyword2");
    Collections.sort(listaKeyword);
    controladorOferta.altaKeyword("Keyword1");
    controladorOferta.altaKeyword("Keyword2");

    controladorOferta.altaOfertaLaboral("test",
          "descipcionTest", "09:00", "15:00", 500f,
          "Montevideo", "Montevideo", fechaDate1,
          "tipoTesting", "nicknameEmpresa1", listaKeyword,
          null, null);

    controladorUsuario.altaPostulante("a",
          "NombreTest", "ApellidoTest", "EmailTesta",
          fechaDate1, "NacionalidadTest", null, "1234");

    controladorUsuario.altaPostulante("b",
          "NombreTest", "ApellidoTest", "EmailTestb",
          fechaDate1, "NacionalidadTest", null, "1234");

    controladorUsuario.altaPostulante("c",
          "NombreTest", "ApellidoTest", "EmailTestc",
          fechaDate1, "NacionalidadTest", null, "1234");

    try {
      controladorUsuario.obtenerDatosPdf("a",
            "test");
    } catch (OfertaLaboralNoExisteException e) {
      // TODO Auto-generated catch block
    } catch (UsuarioNoExisteException e) {
      Assert.assertEquals(e.getMessage(),
            "el postulante no está postulado a la oferta test");
    }

    controladorUsuario.registrarPostulacion("cvReducido",
          "motivacion", fechaDate1,
          "b", "test", "linkVideo");

    controladorUsuario.registrarPostulacion("cvReducido",
          "motivacion", fechaDate1,
          "c", "test", "linkVideo");

    controladorUsuario.registrarPostulacion("cvReducido",
          "motivacion", fechaDate1,
          "a", "test", "linkVideo");

    try {
      controladorUsuario.obtenerDatosPdf("a",
            "test");
    } catch (OfertaLaboralNoExisteException e) {
      Assert.assertEquals(e.getMessage(),
            "no se ha realizado la seleccion para la oferta laboral test");
    } catch (UsuarioNoExisteException e) {
      // TODO Auto-generated catch block
    }

    List<String> postulantes = new ArrayList<String>();
    postulantes.add("a");
    postulantes.add("b");
    postulantes.add("c");

    controladorOferta.ordenarPostulaciones("test",
          postulantes);

    DtDatosPdf dtDatosPdfa =
          controladorUsuario.obtenerDatosPdf("a",
                "test");

    DtDatosPdf dtDatosPdfb =
          controladorUsuario.obtenerDatosPdf("b",
                "test");

    OfertaLaboral test =
          controladorOferta.obtenerOfertaLaboral("test");

    Assert.assertEquals(dtDatosPdfa.getFechaPostulacion(),
          fechaDate1.toString());
    Assert.assertEquals(dtDatosPdfa.getFechaResolucion(),
          test.getFechaSeleccion().toString());
    Assert.assertEquals(dtDatosPdfa.getNombreEmpresa(),
          "nicknameEmpresa1");
    Assert.assertEquals(dtDatosPdfa.getNombreOferta(),
          "test");
    Assert.assertEquals(dtDatosPdfa.getNombrePostulante(),
          "NombreTest ApellidoTest");
    Assert.assertEquals(dtDatosPdfa.getPosicion(), 1);

    Assert.assertEquals(dtDatosPdfb.getPosicion(), 2);

  }

  @Test
  public void testBuscarEmpresas()
        throws UsuarioYaExisteException,
        UsuarioEmailRepetidoException, IOException {
    controladorUsuario.altaEmpresa("nicknameEmpresa1",
          "nombre1", "apellido1", "email1@test1.com",
          "descripcion1", "sitioWeb1", null,
          "nuevaContraseña");

    controladorUsuario.altaEmpresa("nicknameEmpresa2",
          "nombre2", "apellido1", "email1@test2.com",
          "descripcion2", "sitioWeb1", null,
          "nuevaContraseña");

    List<DtEmpresa> resultadoEsperado =
          controladorUsuario.buscarEmpresas("nombre");
    Assert.assertEquals(resultadoEsperado.size(), 2);

    resultadoEsperado =
          controladorUsuario.buscarEmpresas("nombre1");
    Assert.assertEquals(resultadoEsperado.size(), 1);

    resultadoEsperado =
          controladorUsuario.buscarEmpresas("descripcion");
    Assert.assertEquals(resultadoEsperado.size(), 2);

    resultadoEsperado =
          controladorUsuario.buscarEmpresas("descripcion1");
    Assert.assertEquals(resultadoEsperado.size(), 1);

  }

}
