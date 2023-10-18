package testing;

import excepciones.KeywordNoExisteException;
import excepciones.KeywordYaExisteException;
import excepciones.OfertaLaboralNoExisteException;
import excepciones.OfertaLaboralNoTienePaquete;
import excepciones.OfertaLaboralYaExisteException;
import excepciones.PaquetePublicacionNoExisteException;
import excepciones.PaquetePublicacionYaExisteException;
import excepciones.PaquetePublicacionYaFueComprado;
import excepciones.TipoDePublicacionYaFueIngresado;
import excepciones.TipoPublicacionNoExisteException;
import excepciones.TipoPublicacionYaExisteException;
import excepciones.UsuarioEmailRepetidoException;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioYaExisteException;
import excepciones.UsuarioYaExistePostulacion;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import logica.classes.CantidadTotalTipoPublicacion;
import logica.classes.Keyword;
import logica.classes.OfertaLaboral;
import logica.classes.PaquetePublicacion;
import logica.classes.TipoPublicacion;
import logica.controllers.ControladorOferta;
import logica.controllers.ControladorUsuario;
import logica.datatypes.DtOfertaLaboral;
import logica.datatypes.DtcantidadTipoPublicacion;
import logica.datatypes.DtpaquetePublicacion;
import logica.datatypes.Dtpostulacion;
import logica.datatypes.DttipoPublicacion;
import logica.datatypes.EstadoOferta;
import logica.handlers.ManejadorOfertas;
import logica.handlers.ManejadorPaquetes;
import logica.handlers.ManejadorSettings;
import logica.handlers.ManejadorUsuario;

/**
 * Clase Controlador oferta test .
 */

public class ControladorOfertaTest {
  private Date fechaDateParse;
  private Date fechaDateParseSecundaria;
  private LocalDate fechaDate;
  private LocalDate fechaDateSecundaria;
  private String fecha = "24/08/2023";
  private String fechaSecuandaria = "24/09/2023";
  private ControladorOferta controladorOferta;
  private ManejadorSettings manejadorSettings;
  private ManejadorOfertas manejadorOfertas;
  private ManejadorPaquetes manejadorPaquetes;
  private ControladorUsuario controladorUsuario;
  private ManejadorUsuario manejadorUsuario;
  
  /**
   * Metodo setUp .
   */
  @Before
  public void setUp() {
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    manejadorOfertas = ManejadorOfertas.getInstance();
    manejadorPaquetes = ManejadorPaquetes.getInstance();
    manejadorSettings = ManejadorSettings.getInstance();
    manejadorUsuario = ManejadorUsuario.getInstance();
    
    manejadorUsuario.clean();
    manejadorOfertas.clean();
    manejadorSettings.clean();
    manejadorPaquetes.clean();
    
    try {
      this.fechaDateParseSecundaria = dateFormat.parse(fechaSecuandaria);
      this.fechaDateParse = dateFormat.parse(fecha);
      fechaDate = fechaDateParse.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
      fechaDateSecundaria = fechaDateParseSecundaria.toInstant().atZone(ZoneId.systemDefault())
          .toLocalDate();
      
    } catch (ParseException e) {
      e.getMessage();
    }
  }
  
  @SuppressWarnings("deprecation")
  @Test
  public void testAltaTipoPublicacionValidacion()
      throws TipoPublicacionYaExisteException, TipoPublicacionNoExisteException {
    manejadorOfertas = ManejadorOfertas.getInstance();
    manejadorSettings = ManejadorSettings.getInstance();
    controladorOferta = new ControladorOferta();
    controladorOferta.altaTipoPublicacion("tipoTesting", "Uso para testing", "baja", 50, 500f,
        fechaDate);
    TipoPublicacion resultado = controladorOferta.obtenerTipoPublicacion("tipoTesting");
    Assert.assertEquals("tipoTesting", resultado.getNombre());
    Assert.assertEquals("Uso para testing", resultado.getDescripcion());
    Assert.assertEquals("baja", resultado.getExposicion());
    Assert.assertEquals(50, resultado.getDuracionDia());
    Assert.assertEquals(String.valueOf(500f), String.valueOf(resultado.getCosto()));
    Assert.assertEquals(fechaDate, resultado.getFechaAlta());
  }
  
  @Test
  public void testAltaKeywordyValidacion()
      throws KeywordYaExisteException, TipoPublicacionYaExisteException,
      KeywordNoExisteException, TipoPublicacionNoExisteException {
    manejadorOfertas = ManejadorOfertas.getInstance();
    manejadorSettings = ManejadorSettings.getInstance();
    controladorOferta = new ControladorOferta();
    controladorOferta.altaKeyword("KeywordTest");
  }
  
  @Test
  public void testListarKeywordyValidacion()
      throws KeywordYaExisteException, TipoPublicacionYaExisteException {
    manejadorOfertas = ManejadorOfertas.getInstance();
    manejadorSettings = ManejadorSettings.getInstance();
    controladorOferta = new ControladorOferta();
    controladorOferta.altaKeyword("KeywordTestPrimero");
    controladorOferta.altaKeyword("KeywordTestSegundo");
    
    List<String> resultado = controladorOferta.listarKeywords();
    Collections.sort(resultado);
    List<String> resultadoEsperado = new ArrayList<String>();
    resultadoEsperado.add("KeywordTestPrimero");
    resultadoEsperado.add("KeywordTestSegundo");
    Collections.sort(resultadoEsperado);
    
    Assert.assertEquals(resultadoEsperado, resultado);
  }
  
  @Test
  public void testAltaOfertaLaboralyValidacion()
      throws OfertaLaboralYaExisteException, OfertaLaboralNoExisteException,
      TipoPublicacionNoExisteException, KeywordNoExisteException, UsuarioNoExisteException,
      UsuarioYaExisteException, UsuarioEmailRepetidoException,
      TipoPublicacionYaExisteException, KeywordYaExisteException {
    manejadorOfertas = ManejadorOfertas.getInstance();
    manejadorSettings = ManejadorSettings.getInstance();
    controladorUsuario = new ControladorUsuario();
    controladorOferta = new ControladorOferta();
    controladorOferta.altaTipoPublicacion("tipoTesting", "Uso para testing", "baja", 50, 500f,
        fechaDate);
    controladorUsuario.altaEmpresa("nicknameEmpresa1", "nombre1", "apellido1",
        "email1@test.com", "descripcion1", "sitioWeb1", null, "nuevaContraseña");
    
    List<String> listaKeyword = new ArrayList<String>();
    listaKeyword.add("Keyword1");
    listaKeyword.add("Keyword2");
    Collections.sort(listaKeyword);
    controladorOferta.altaKeyword("Keyword1");
    controladorOferta.altaKeyword("Keyword2");
    
    controladorOferta.altaOfertaLaboral("test", "descipcionTest", "09:00", "15:00", 500f,
        "Montevideo", "Montevideo", fechaDate, "tipoTesting", "nicknameEmpresa1", listaKeyword,
        null, null);
    
    OfertaLaboral resultado = controladorOferta.obtenerOfertaLaboral("test");
    
    List<Keyword> listaKeywords = resultado.getKeywords();
    List<String> listaCasteada = new ArrayList<String>();
    for (Keyword unidad : listaKeywords) {
      listaCasteada.add(unidad.getNombre());
    }
    Collections.sort(listaCasteada);
    
    TipoPublicacion resultadoOfertaLaboral = manejadorSettings
        .obtenerTipoPublicacion("tipoTesting");
    
    Assert.assertEquals("test", resultado.getNombre());
    Assert.assertEquals("descipcionTest", resultado.getDescripcion());
    Assert.assertEquals("09:00", resultado.getHorarioInicial());
    Assert.assertEquals("15:00", resultado.getHorarioFinal());
    Assert.assertEquals("Montevideo", resultado.getCiudad());
    Assert.assertEquals("Montevideo", resultado.getDepartamento());
    Assert.assertEquals(fechaDate, resultado.getFechaAlta());
    Assert.assertEquals(resultadoOfertaLaboral, resultado.getTipoPublicacion());
    Assert.assertEquals(listaCasteada, listaKeyword);
    
  }
  
  @Test
  public void testObtenerDtOfertaLaboralyValidacion()
      throws OfertaLaboralYaExisteException, OfertaLaboralNoExisteException,
      UsuarioYaExisteException, UsuarioEmailRepetidoException, KeywordYaExisteException,
      TipoPublicacionYaExisteException, TipoPublicacionNoExisteException,
      KeywordNoExisteException, UsuarioNoExisteException, IOException {
    manejadorOfertas = ManejadorOfertas.getInstance();
    manejadorSettings = ManejadorSettings.getInstance();
    controladorUsuario = new ControladorUsuario();
    controladorOferta = new ControladorOferta();
    
    controladorOferta.altaTipoPublicacion("tipoTesting", "Uso para testing", "baja", 50, 500f,
        fechaDate);
    controladorUsuario.altaEmpresa("nicknameEmpresa1", "nombre1", "apellido1",
        "email1@test.com", "descripcion1", "sitioWeb1", null, "nuevaContraseña");
    
    List<String> listaKeyword = new ArrayList<String>();
    listaKeyword.add("Keyword1");
    listaKeyword.add("Keyword2");
    Collections.sort(listaKeyword);
    controladorOferta.altaKeyword("Keyword1");
    controladorOferta.altaKeyword("Keyword2");
    
    controladorOferta.altaOfertaLaboral("test", "descipcionTest", "09:00", "15:00", 500f,
        "Montevideo", "Montevideo", fechaDate, "tipoTesting", "nicknameEmpresa1", listaKeyword,
        null, null);
    
    DtOfertaLaboral resultado = controladorOferta.obtenerDtOfertaLaboral("test");
    
    Assert.assertEquals("test", resultado.getNombre());
    Assert.assertEquals("descipcionTest", resultado.getDescripcion());
    Assert.assertEquals("09:00", resultado.getHorarioInicio());
    Assert.assertEquals("15:00", resultado.getHorarioFinal());
    Assert.assertEquals(String.valueOf(500f), String.valueOf(resultado.getRemuneracion()));
    Assert.assertEquals("Montevideo", resultado.getCiudad());
    Assert.assertEquals("Montevideo", resultado.getDepartamento());
    Assert.assertEquals(fechaDate, resultado.getFechaAlta());
  }
  
  @Test
  public void testListadoTipoPublicacion() throws TipoPublicacionYaExisteException {
    manejadorOfertas = ManejadorOfertas.getInstance();
    manejadorSettings = ManejadorSettings.getInstance();
    controladorOferta = new ControladorOferta();
    controladorOferta.altaTipoPublicacion("tipoTestingPrimero", "Uso para testing", "baja", 50,
        500f, fechaDate);
    controladorOferta.altaTipoPublicacion("tipoTestingSegundo", "Uso para testing", "baja", 50,
        500f, fechaDate);
    List<String> resultadoListado = controladorOferta.listarTipoDePublicaciones();
    Collections.sort(resultadoListado);
    List<String> resultadoEsperado = new ArrayList<String>();
    resultadoEsperado.add("tipoTestingPrimero");
    resultadoEsperado.add("tipoTestingSegundo");
    Collections.sort(resultadoEsperado);
    Assert.assertEquals(resultadoEsperado, resultadoListado);
    
  }
  
  @Test
  public void testListarPaquetes() throws PaquetePublicacionYaExisteException {
    manejadorOfertas = ManejadorOfertas.getInstance();
    manejadorSettings = ManejadorSettings.getInstance();
    manejadorPaquetes = ManejadorPaquetes.getInstance();
    controladorOferta = new ControladorOferta();
    List<CantidadTotalTipoPublicacion> arrayPrueba = new ArrayList<CantidadTotalTipoPublicacion>();
    List<CantidadTotalTipoPublicacion> arrayPruebaSegundo = 
        new ArrayList<CantidadTotalTipoPublicacion>();
    PaquetePublicacion paquetePruebaPrimero = new PaquetePublicacion("nombrePruebaPrimero",
        "descripcionPrueba", 20, 50f, null, arrayPruebaSegundo, fechaDate);
    
    PaquetePublicacion paquetePrueba = new PaquetePublicacion("nombrePrueba",
        "descripcionPrueba", 20, 50f, null, arrayPrueba, fechaDate);
    manejadorPaquetes.agregarPaquete(paquetePrueba);
    manejadorPaquetes.agregarPaquete(paquetePruebaPrimero);
    List<String> resultadoPaquete = controladorOferta.listarPaquetes();
    Collections.sort(resultadoPaquete);
    List<String> resultadoEsperado = new ArrayList<String>();
    resultadoEsperado.add("nombrePruebaPrimero");
    resultadoEsperado.add("nombrePrueba");
    Collections.sort(resultadoEsperado);
    Assert.assertEquals(resultadoEsperado, resultadoPaquete);
    
  }
  
  @Test
  public void testRegistrarPaquetes() throws PaquetePublicacionYaExisteException,
      TipoPublicacionYaExisteException, TipoPublicacionNoExisteException {
    manejadorOfertas = ManejadorOfertas.getInstance();
    manejadorSettings = ManejadorSettings.getInstance();
    manejadorPaquetes = ManejadorPaquetes.getInstance();
    controladorOferta = new ControladorOferta();
    TipoPublicacion publicacionPrueba = new TipoPublicacion("tipoPublicacionPrueba",
        "descripcionPrueba", "alta", 20, 50f, fechaDate);
    manejadorSettings.addTipoPublicacion(publicacionPrueba);
    DtcantidadTipoPublicacion nuevoDt = new DtcantidadTipoPublicacion("tipoPublicacionPrueba",
        5);
    List<DtcantidadTipoPublicacion> arrayDtCantidad = new ArrayList<DtcantidadTipoPublicacion>();
    arrayDtCantidad.add(nuevoDt);
    controladorOferta.registrarPaquete("paquetePrueba", "descripcionPrueba", 20, 50f, null,
        fechaDate, arrayDtCantidad);
    List<String> nombrePaquete = controladorOferta.listarPaquetes();
    Assert.assertEquals("paquetePrueba", nombrePaquete.get(0));
    
  }
  
  @Test
  public void agregarTipoPublicacionAlPaquete()
      throws TipoPublicacionNoExisteException, PaquetePublicacionNoExisteException,
      PaquetePublicacionYaExisteException, TipoPublicacionYaExisteException,
      PaquetePublicacionYaFueComprado, TipoDePublicacionYaFueIngresado {
    manejadorOfertas = ManejadorOfertas.getInstance();
    manejadorSettings = ManejadorSettings.getInstance();
    manejadorPaquetes = ManejadorPaquetes.getInstance();
    controladorOferta = new ControladorOferta();
    
    TipoPublicacion publicacionPrueba = new TipoPublicacion("tipoPublicacionPrueba",
        "descripcionPrueba", "alta", 20, 50f, fechaDate);
    manejadorSettings.addTipoPublicacion(publicacionPrueba);
    DtcantidadTipoPublicacion nuevoDt = new DtcantidadTipoPublicacion("tipoPublicacionPrueba",
        5);
    List<DtcantidadTipoPublicacion> arrayDtCantidad = new ArrayList<DtcantidadTipoPublicacion>();
    arrayDtCantidad.add(nuevoDt);
    controladorOferta.registrarPaquete("paquetePrueba", "descripcionPrueba", 20, 50f, null,
        fechaDate, arrayDtCantidad);
    TipoPublicacion publicacionPruebaSegunda = new TipoPublicacion(
        "tipoPublicacionPruebaSeguda", "descripcionPrueba", "alta", 20, 50f, fechaDate);
    manejadorSettings.addTipoPublicacion(publicacionPruebaSegunda);
    controladorOferta.agregarTipoPublicacionAlPaquete(50, "tipoPublicacionPruebaSeguda",
        "paquetePrueba");
    PaquetePublicacion encontrarPaquete = manejadorPaquetes.obtenerPaquete("paquetePrueba");
    List<CantidadTotalTipoPublicacion> coleccionCantidadTipo = encontrarPaquete
        .obtenerCantidadTotalTipoPublicaciones();
    boolean encontre = false;
    String resultado = "";
    for (int i = 0; (i < coleccionCantidadTipo.size()) && (!encontre); i++) {
      if (coleccionCantidadTipo.get(i).getTipoPublicacion()
          .getNombre() == "tipoPublicacionPruebaSeguda") {
        encontre = true;
        resultado = "tipoPublicacionPruebaSeguda";
      }
    }
    Assert.assertEquals("tipoPublicacionPruebaSeguda", resultado);
  }
  
  @Test
  public void testObtenerOfertasEmpresa()
      throws UsuarioYaExisteException, UsuarioEmailRepetidoException,
      OfertaLaboralYaExisteException, OfertaLaboralNoExisteException, UsuarioNoExisteException,
      TipoPublicacionYaExisteException, KeywordYaExisteException,
      TipoPublicacionNoExisteException, KeywordNoExisteException {
    manejadorOfertas = ManejadorOfertas.getInstance();
    manejadorSettings = ManejadorSettings.getInstance();
    manejadorPaquetes = ManejadorPaquetes.getInstance();
    manejadorUsuario = ManejadorUsuario.getInstance();
    controladorUsuario = new ControladorUsuario();
    controladorOferta = new ControladorOferta();
    
    controladorOferta.altaTipoPublicacion("tipoTesting", "Uso para testing", "baja", 50, 500f,
        fechaDate);
    controladorUsuario.altaEmpresa("nicknameEmpresa1", "nombre1", "apellido1",
        "email1@test.com", "descripcion1", "sitioWeb1", null, "nuevaContraseña");
    
    List<String> listaKeyword = new ArrayList<String>();
    listaKeyword.add("Keyword1");
    listaKeyword.add("Keyword2");
    Collections.sort(listaKeyword);
    controladorOferta.altaKeyword("Keyword1");
    controladorOferta.altaKeyword("Keyword2");
    
    controladorOferta.altaOfertaLaboral("test", "descipcionTest", "09:00", "15:00", 500f,
        "Montevideo", "Montevideo", fechaDate, "tipoTesting", "nicknameEmpresa1", listaKeyword,
        null, null);
    
    List<String> resultado = controladorOferta.obtenerOfertasEmpresa("nicknameEmpresa1");
    Assert.assertEquals("test", resultado.get(0));
    
  }
  
  @Test
  public void testRegistrarPostulacion()
      throws OfertaLaboralYaExisteException, OfertaLaboralNoExisteException,
      UsuarioYaExisteException, UsuarioNoExisteException, UsuarioEmailRepetidoException,
      KeywordYaExisteException, TipoPublicacionYaExisteException,
      TipoPublicacionNoExisteException, KeywordNoExisteException, UsuarioYaExistePostulacion {
    manejadorOfertas = ManejadorOfertas.getInstance();
    manejadorSettings = ManejadorSettings.getInstance();
    manejadorPaquetes = ManejadorPaquetes.getInstance();
    manejadorUsuario = ManejadorUsuario.getInstance();
    controladorOferta = new ControladorOferta();
    controladorUsuario = new ControladorUsuario();
    controladorOferta.altaTipoPublicacion("tipoTesting", "Uso para testing", "baja", 50, 500f,
        fechaDate);
    controladorUsuario.altaEmpresa("nicknameEmpresa1", "nombre1", "apellido1",
        "email1@test.com", "descripcion1", "sitioWeb1", null, "nuevaContraseña");
    
    List<String> listaKeyword = new ArrayList<String>();
    listaKeyword.add("Keyword1");
    listaKeyword.add("Keyword2");
    Collections.sort(listaKeyword);
    controladorOferta.altaKeyword("Keyword1");
    controladorOferta.altaKeyword("Keyword2");
    
    controladorOferta.altaOfertaLaboral("test", "descipcionTest", "09:00", "15:00", 500f,
        "Montevideo", "Montevideo", fechaDate, "tipoTesting", "nicknameEmpresa1", listaKeyword,
        null, null);
    
    controladorUsuario.altaPostulante("nicknameTesting", "nombreTesting", "apellidoTesting",
        "email", fechaDate, "Montevideo", null, "nuevaContraseña");
    
    controladorUsuario.registrarPostulacion("cvReducidoString", "MotivacionTesting", fechaDate,
        "nicknameTesting", "test");
    
    List<String> resultado = controladorUsuario.listaOfertasUsuario("nicknameTesting");
    
    Assert.assertEquals("test", resultado.get(0));
    
  }
  
  @Test
  public void testKeywordNoExiste() {
    controladorOferta = new ControladorOferta();
    try {
      controladorOferta.obtenerKeywords("KeywordNoExiste");
    } catch (KeywordNoExisteException e) {
      // TODO Auto-generated catch block
      Assert.assertEquals("La keyword KeywordNoExiste no existe", e.getMessage());
    } catch (TipoPublicacionNoExisteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
  }
  
  @Test
  public void testListarTipoPublicacionDePaquete()
      throws TipoPublicacionYaExisteException, TipoPublicacionNoExisteException,
      PaquetePublicacionNoExisteException, PaquetePublicacionYaExisteException {
    manejadorOfertas = ManejadorOfertas.getInstance();
    manejadorPaquetes = ManejadorPaquetes.getInstance();
    controladorOferta = new ControladorOferta();
    List<CantidadTotalTipoPublicacion> arrayPruebaSegundo = 
        new ArrayList<CantidadTotalTipoPublicacion>();
    controladorOferta.altaTipoPublicacion("tipoTesting", "Uso para testing", "baja", 50, 500f,
        fechaDate);
    TipoPublicacion tipoEncontrado = controladorOferta.obtenerTipoPublicacion("tipoTesting");
    CantidadTotalTipoPublicacion nuevoCantidadTotalTipoPublicacion = 
        new CantidadTotalTipoPublicacion(
        2, tipoEncontrado);
    arrayPruebaSegundo.add(nuevoCantidadTotalTipoPublicacion);
    PaquetePublicacion paquetePruebaPrimero = new PaquetePublicacion("nombrePruebaPrimero",
        "descripcionPrueba", 20, 50f, null, arrayPruebaSegundo, fechaDate);
    manejadorPaquetes.agregarPaquete(paquetePruebaPrimero);
    List<String> resultadoEsperado = new ArrayList<String>();
    resultadoEsperado.add("tipoTesting");
    List<String> resultadoEncontrado = controladorOferta
        .listarTipoPublicacionDePaquete("nombrePruebaPrimero");
    Assert.assertEquals(resultadoEsperado, resultadoEncontrado);
  }
  
  @Test
  public void testObtenerPaquetePublicacion()
      throws TipoPublicacionYaExisteException, TipoPublicacionNoExisteException,
      PaquetePublicacionYaExisteException, PaquetePublicacionNoExisteException {
    manejadorOfertas = ManejadorOfertas.getInstance();
    manejadorPaquetes = ManejadorPaquetes.getInstance();
    controladorOferta = new ControladorOferta();
    
    List<CantidadTotalTipoPublicacion> arrayPruebaSegundo = 
        new ArrayList<CantidadTotalTipoPublicacion>();
    controladorOferta.altaTipoPublicacion("tipoTesting", "Uso para testing", "baja", 50, 500f,
        fechaDate);
    TipoPublicacion tipoEncontrado = controladorOferta.obtenerTipoPublicacion("tipoTesting");
    CantidadTotalTipoPublicacion nuevoCantidadTotalTipoPublicacion = 
        new CantidadTotalTipoPublicacion(
        2, tipoEncontrado);
    arrayPruebaSegundo.add(nuevoCantidadTotalTipoPublicacion);
    PaquetePublicacion paquetePruebaPrimero = new PaquetePublicacion("nombrePruebaPrimero",
        "descripcionPrueba", 20, 50f, null, arrayPruebaSegundo, fechaDate);
    manejadorPaquetes.agregarPaquete(paquetePruebaPrimero);
    PaquetePublicacion paqueteEncontrado = controladorOferta
        .obtenerPaquetePublicacion("nombrePruebaPrimero");
    Assert.assertEquals(paquetePruebaPrimero, paqueteEncontrado);
  }
  
  @SuppressWarnings("deprecation")
  @Test
  public void testObtenerDtPaquetePublicacion() throws TipoPublicacionYaExisteException,
      UsuarioYaExisteException, UsuarioEmailRepetidoException,
      PaquetePublicacionYaExisteException, TipoPublicacionNoExisteException,
      KeywordYaExisteException, OfertaLaboralYaExisteException, KeywordNoExisteException,
      UsuarioNoExisteException, OfertaLaboralNoExisteException, OfertaLaboralNoTienePaquete,
      PaquetePublicacionNoExisteException, IOException {
    manejadorOfertas = ManejadorOfertas.getInstance();
    manejadorPaquetes = ManejadorPaquetes.getInstance();
    controladorOferta = new ControladorOferta();
    controladorUsuario = new ControladorUsuario();
    
    List<CantidadTotalTipoPublicacion> arrayPruebaSegundo = 
        new ArrayList<CantidadTotalTipoPublicacion>();
    controladorOferta.altaTipoPublicacion("tipoTesting", "Uso para testing", "baja", 50, 500f,
        fechaDate);
    TipoPublicacion tipoEncontrado = controladorOferta.obtenerTipoPublicacion("tipoTesting");
    CantidadTotalTipoPublicacion nuevoCantidadTotalTipoPublicacion = 
        new CantidadTotalTipoPublicacion(
        2, tipoEncontrado);
    arrayPruebaSegundo.add(nuevoCantidadTotalTipoPublicacion);
    PaquetePublicacion paquetePruebaPrimero = new PaquetePublicacion("nombrePruebaPrimero",
        "descripcionPrueba", 20, 50f, null, arrayPruebaSegundo, fechaDate);
    manejadorPaquetes.agregarPaquete(paquetePruebaPrimero);
    
    controladorUsuario.altaEmpresa("nicknameEmpresa1", "nombre1", "apellido1",
        "email1@test.com", "descripcion1", "sitioWeb1", null, "nuevaContraseña");
    controladorUsuario.comprarPaquete("nicknameEmpresa1", "nombrePruebaPrimero",
        fechaDateSecundaria);
    
    List<String> listaKeyword = new ArrayList<String>();
    listaKeyword.add("Keyword1");
    controladorOferta.altaKeyword("Keyword1");
    
    controladorOferta.altaOfertaLaboral("test", "descipcionTest", "09:00", "15:00", 500f,
        "Montevideo", "Montevideo", fechaDate, "tipoTesting", "nicknameEmpresa1", listaKeyword,
        null, "nombrePruebaPrimero");
    DtpaquetePublicacion paquetePublicacionDt = controladorOferta
        .obtenerDtPaquetePublicacion("test");
    List<DtcantidadTipoPublicacion> resultado = new ArrayList<DtcantidadTipoPublicacion>();
    
    for (CantidadTotalTipoPublicacion cantidadTotalTipoPublicacion : arrayPruebaSegundo) {
      DtcantidadTipoPublicacion dtCantidadTipoPublicacion = cantidadTotalTipoPublicacion
          .obtenerDtcantidadTipoPublicacion();
      resultado.add(dtCantidadTipoPublicacion);
    }
    
    Assert.assertEquals(String.valueOf(paquetePublicacionDt.getCosto()),
        String.valueOf(paquetePruebaPrimero.getCosto()));
    Assert.assertEquals(String.valueOf(paquetePublicacionDt.getDescuento()),
        String.valueOf(paquetePruebaPrimero.getDescuento()));
    Assert.assertEquals(paquetePublicacionDt.getPeriodoValidez(), 20);
    Assert.assertEquals(paquetePublicacionDt.getDescripcion(), "descripcionPrueba");
    Assert.assertEquals(paquetePublicacionDt.getNombre(), "nombrePruebaPrimero");
    Assert.assertEquals(
        paquetePublicacionDt.getCantidadTipoPublicaciones().get(0).getNombreTipoPublicacion(),
        resultado.get(0).getNombreTipoPublicacion());
    Assert.assertEquals(
        paquetePublicacionDt.getCantidadTipoPublicaciones().get(0).getCantidad(),
        resultado.get(0).getCantidad());
    Assert.assertEquals(paquetePublicacionDt.getImagen(), null);
  }
  
  @Test
  public void testEstaCompradoPorPaqueteOferta() throws TipoPublicacionYaExisteException,
      TipoPublicacionNoExisteException, UsuarioYaExisteException,
      UsuarioEmailRepetidoException, KeywordYaExisteException, OfertaLaboralYaExisteException,
      KeywordNoExisteException, UsuarioNoExisteException, OfertaLaboralNoExisteException {
    manejadorOfertas = ManejadorOfertas.getInstance();
    manejadorPaquetes = ManejadorPaquetes.getInstance();
    controladorOferta = new ControladorOferta();
    controladorUsuario = new ControladorUsuario();
    
    controladorOferta.altaTipoPublicacion("tipoTesting", "Uso para testing", "baja", 50, 500f,
        fechaDate);
    controladorUsuario.altaEmpresa("nicknameEmpresa1", "nombre1", "apellido1",
        "email1@test.com", "descripcion1", "sitioWeb1", null, "nuevaContraseña");
    List<String> listaKeyword = new ArrayList<String>();
    listaKeyword.add("Keyword1");
    controladorOferta.altaKeyword("Keyword1");
    controladorOferta.altaOfertaLaboral("test", "descipcionTest", "09:00", "15:00", 500f,
        "Montevideo", "Montevideo", fechaDate, "tipoTesting", "nicknameEmpresa1", listaKeyword,
        null, null);
    
    Assert.assertEquals(controladorOferta.estaCompradoPorPaqueteOferta("test"), false);
  }
  
  @Test
  public void testObtenerKeywordsDeOfertaLaboral() throws OfertaLaboralNoExisteException,
      TipoPublicacionYaExisteException, TipoPublicacionNoExisteException,
      UsuarioYaExisteException, UsuarioEmailRepetidoException, KeywordYaExisteException,
      OfertaLaboralYaExisteException, KeywordNoExisteException, UsuarioNoExisteException {
    manejadorOfertas = ManejadorOfertas.getInstance();
    manejadorPaquetes = ManejadorPaquetes.getInstance();
    controladorOferta = new ControladorOferta();
    controladorUsuario = new ControladorUsuario();
    
    controladorOferta.altaTipoPublicacion("tipoTesting", "Uso para testing", "baja", 50, 500f,
        fechaDate);
    controladorUsuario.altaEmpresa("nicknameEmpresa1", "nombre1", "apellido1",
        "email1@test.com", "descripcion1", "sitioWeb1", null, "nuevaContraseña");
    List<String> listaKeyword = new ArrayList<String>();
    listaKeyword.add("Keyword1");
    controladorOferta.altaKeyword("Keyword1");
    controladorOferta.altaOfertaLaboral("test", "descipcionTest", "09:00", "15:00", 500f,
        "Montevideo", "Montevideo", fechaDate, "tipoTesting", "nicknameEmpresa1", listaKeyword,
        null, null);
    
    Assert.assertEquals(controladorOferta.obtenerKeywordsDeOfertaLaboral("test").get(0),
        "Keyword1");
  }
  
  @Test
  public void testNoEstaPostualdo() throws TipoPublicacionYaExisteException,
      TipoPublicacionNoExisteException, UsuarioYaExisteException,
      UsuarioEmailRepetidoException, KeywordYaExisteException, OfertaLaboralYaExisteException,
      KeywordNoExisteException, UsuarioNoExisteException, OfertaLaboralNoExisteException {
    manejadorOfertas = ManejadorOfertas.getInstance();
    manejadorPaquetes = ManejadorPaquetes.getInstance();
    controladorOferta = new ControladorOferta();
    controladorUsuario = new ControladorUsuario();
    
    controladorOferta.altaTipoPublicacion("tipoTesting", "Uso para testing", "baja", 50, 500f,
        fechaDate);
    controladorUsuario.altaEmpresa("nicknameEmpresa1", "nombre1", "apellido1",
        "email1@test.com", "descripcion1", "sitioWeb1", null, "nuevaContraseña");
    List<String> listaKeyword = new ArrayList<String>();
    listaKeyword.add("Keyword1");
    controladorOferta.altaKeyword("Keyword1");
    controladorOferta.altaOfertaLaboral("test", "descipcionTest", "09:00", "15:00", 500f,
        "Montevideo", "Montevideo", fechaDate, "tipoTesting", "nicknameEmpresa1", listaKeyword,
        null, null);
    
    controladorUsuario.altaPostulante("nicknameTesting", "nombreTesting", "apellidoTesting",
        "email", fechaDate, "Montevideo", null, "nuevaContraseña");
    
    Assert.assertEquals(controladorOferta.estaPostulado("nicknameTesting", "test"), false);
  }
  
  @Test
  public void testSiEstaPostualdo()
      throws TipoPublicacionYaExisteException, TipoPublicacionNoExisteException,
      UsuarioYaExisteException, UsuarioEmailRepetidoException, KeywordYaExisteException,
      OfertaLaboralYaExisteException, KeywordNoExisteException, UsuarioNoExisteException,
      OfertaLaboralNoExisteException, UsuarioYaExistePostulacion {
    manejadorOfertas = ManejadorOfertas.getInstance();
    manejadorPaquetes = ManejadorPaquetes.getInstance();
    controladorOferta = new ControladorOferta();
    controladorUsuario = new ControladorUsuario();
    
    controladorOferta.altaTipoPublicacion("tipoTesting", "Uso para testing", "baja", 50, 500f,
        fechaDate);
    controladorUsuario.altaEmpresa("nicknameEmpresa1", "nombre1", "apellido1",
        "email1@test.com", "descripcion1", "sitioWeb1", null, "nuevaContraseña");
    List<String> listaKeyword = new ArrayList<String>();
    listaKeyword.add("Keyword1");
    controladorOferta.altaKeyword("Keyword1");
    controladorOferta.altaOfertaLaboral("test", "descipcionTest", "09:00", "15:00", 500f,
        "Montevideo", "Montevideo", fechaDate, "tipoTesting", "nicknameEmpresa1", listaKeyword,
        null, null);
    
    controladorUsuario.altaPostulante("nicknameTesting", "nombreTesting", "apellidoTesting",
        "email", fechaDate, "Montevideo", null, "nuevaContraseña");
    
    controladorUsuario.registrarPostulacion("cvReducido", "motivacion", fechaDate,
        "nicknameTesting", "test");
    
    Assert.assertEquals(controladorOferta.estaPostulado("nicknameTesting", "test"), true);
  }
  
  @Test
  public void testObtenerDtofertasPorKeyword()
      throws TipoPublicacionYaExisteException, UsuarioYaExisteException,
      UsuarioEmailRepetidoException, TipoPublicacionNoExisteException,
      KeywordYaExisteException, OfertaLaboralYaExisteException, KeywordNoExisteException,
      UsuarioNoExisteException, IOException {
    
    manejadorOfertas = ManejadorOfertas.getInstance();
    manejadorPaquetes = ManejadorPaquetes.getInstance();
    controladorOferta = new ControladorOferta();
    controladorUsuario = new ControladorUsuario();
    
    controladorOferta.altaTipoPublicacion("tipoTesting", "Uso para testing", "baja", 50, 500f,
        fechaDate);
    controladorUsuario.altaEmpresa("nicknameEmpresa1", "nombre1", "apellido1",
        "email1@test.com", "descripcion1", "sitioWeb1", null, "nuevaContraseña");
    List<String> listaKeyword = new ArrayList<String>();
    listaKeyword.add("Keyword1");
    controladorOferta.altaKeyword("Keyword1");
    controladorOferta.altaOfertaLaboral("test", "descipcionTest", "09:00", "15:00", 500f,
        "Montevideo", "Montevideo", fechaDate, "tipoTesting", "nicknameEmpresa1", listaKeyword,
        null, null);
    
    List<DtOfertaLaboral> resultadoOfertaLaboralDt = controladorOferta
        .obtenerDtofertasPorKeyword("Keyword1");
    
    Assert.assertEquals(resultadoOfertaLaboralDt.get(0).getNombre(), "test");
    Assert.assertEquals(String.valueOf(resultadoOfertaLaboralDt.get(0).getRemuneracion()),
        String.valueOf(500f));
    
  }
  
  @Test
  public void testListarDtPaquetes() throws TipoPublicacionYaExisteException,
      PaquetePublicacionYaExisteException, TipoPublicacionNoExisteException, IOException {
    manejadorOfertas = ManejadorOfertas.getInstance();
    manejadorSettings = ManejadorSettings.getInstance();
    manejadorPaquetes = ManejadorPaquetes.getInstance();
    controladorOferta = new ControladorOferta();
    TipoPublicacion publicacionPrueba = new TipoPublicacion("tipoPublicacionPrueba",
        "descripcionPrueba", "alta", 20, 50f, fechaDate);
    manejadorSettings.addTipoPublicacion(publicacionPrueba);
    DtcantidadTipoPublicacion nuevoDt = new DtcantidadTipoPublicacion("tipoPublicacionPrueba",
        5);
    List<DtcantidadTipoPublicacion> arrayDtCantidad = new ArrayList<DtcantidadTipoPublicacion>();
    arrayDtCantidad.add(nuevoDt);
    controladorOferta.registrarPaquete("paquetePrueba", "descripcionPrueba", 20, 50f, null,
        fechaDate, arrayDtCantidad);
    controladorOferta.registrarPaquete("paquetePruebaSegundo", "descripcionPruebaSegundo", 20,
        50f, null, fechaDate, arrayDtCantidad);
    
    List<DtpaquetePublicacion> listaDtpaquetePublicaciones = controladorOferta
        .listarDtpaquetes();
    
    Assert.assertEquals(listaDtpaquetePublicaciones.get(0).getNombre(), "paquetePrueba");
    Assert.assertEquals(listaDtpaquetePublicaciones.get(1).getNombre(),
        "paquetePruebaSegundo");
    
  }
  
  @Test
  public void testListarPaquetesNoComprados() throws PaquetePublicacionYaExisteException,
      TipoPublicacionYaExisteException, TipoPublicacionNoExisteException {
    manejadorOfertas = ManejadorOfertas.getInstance();
    manejadorSettings = ManejadorSettings.getInstance();
    manejadorPaquetes = ManejadorPaquetes.getInstance();
    controladorOferta = new ControladorOferta();
    TipoPublicacion publicacionPrueba = new TipoPublicacion("tipoPublicacionPrueba",
        "descripcionPrueba", "alta", 20, 50f, fechaDate);
    manejadorSettings.addTipoPublicacion(publicacionPrueba);
    DtcantidadTipoPublicacion nuevoDt = new DtcantidadTipoPublicacion("tipoPublicacionPrueba",
        5);
    List<DtcantidadTipoPublicacion> arrayDtCantidad = new ArrayList<DtcantidadTipoPublicacion>();
    arrayDtCantidad.add(nuevoDt);
    controladorOferta.registrarPaquete("paquetePrueba", "descripcionPrueba", 20, 50f, null,
        fechaDate, arrayDtCantidad);
    
    List<String> listaPaqueteNoComprado = controladorOferta.listarPaquetesNoComprados();
    
    Assert.assertEquals(listaPaqueteNoComprado.get(0), "paquetePrueba");
    
  }
  
  @Test
  public void testObtenerDtTipoDePublicacion()
      throws TipoPublicacionYaExisteException, TipoPublicacionNoExisteException {
    manejadorOfertas = ManejadorOfertas.getInstance();
    manejadorSettings = ManejadorSettings.getInstance();
    manejadorPaquetes = ManejadorPaquetes.getInstance();
    controladorOferta = new ControladorOferta();
    TipoPublicacion publicacionPrueba = new TipoPublicacion("tipoPublicacionPrueba",
        "descripcionPrueba", "alta", 20, 50f, fechaDate);
    manejadorSettings.addTipoPublicacion(publicacionPrueba);
    
    DttipoPublicacion dttipoPublicacion = controladorOferta
        .obtenerDttipoPublicacion("tipoPublicacionPrueba");
    
    Assert.assertEquals(dttipoPublicacion.getNombre(), "tipoPublicacionPrueba");
    Assert.assertEquals(dttipoPublicacion.getDescripcion(), "descripcionPrueba");
    Assert.assertEquals(dttipoPublicacion.getExposicion(), "alta");
  }
  
  @Test
  public void testObtenerDtPaquete()
      throws TipoPublicacionYaExisteException, PaquetePublicacionYaExisteException,
      TipoPublicacionNoExisteException, PaquetePublicacionNoExisteException, IOException {
    manejadorOfertas = ManejadorOfertas.getInstance();
    manejadorSettings = ManejadorSettings.getInstance();
    manejadorPaquetes = ManejadorPaquetes.getInstance();
    controladorOferta = new ControladorOferta();
    TipoPublicacion publicacionPrueba = new TipoPublicacion("tipoPublicacionPrueba",
        "descripcionPrueba", "alta", 20, 50f, fechaDate);
    manejadorSettings.addTipoPublicacion(publicacionPrueba);
    DtcantidadTipoPublicacion nuevoDt = new DtcantidadTipoPublicacion("tipoPublicacionPrueba",
        5);
    List<DtcantidadTipoPublicacion> arrayDtCantidad = new ArrayList<DtcantidadTipoPublicacion>();
    arrayDtCantidad.add(nuevoDt);
    controladorOferta.registrarPaquete("paquetePrueba", "descripcionPrueba", 20, 50f, null,
        fechaDate, arrayDtCantidad);
    
    DtpaquetePublicacion dtpaquetePublicacion = controladorOferta
        .obtenerDtpaquete("paquetePrueba");
    
    Assert.assertEquals(dtpaquetePublicacion.getNombre(), "paquetePrueba");
    Assert.assertEquals(dtpaquetePublicacion.getDescripcion(), "descripcionPrueba");
    Assert.assertEquals(dtpaquetePublicacion.getPeriodoValidez(), 20);
    
  }
  
  @Test
  public void testObtenerDtPaquetePublicacionExcepcion()
      throws TipoPublicacionYaExisteException, PaquetePublicacionYaExisteException,
      TipoPublicacionNoExisteException, UsuarioYaExisteException,
      UsuarioEmailRepetidoException, KeywordYaExisteException, OfertaLaboralYaExisteException,
      KeywordNoExisteException, UsuarioNoExisteException, IOException {
    manejadorOfertas = ManejadorOfertas.getInstance();
    manejadorSettings = ManejadorSettings.getInstance();
    controladorUsuario = new ControladorUsuario();
    controladorOferta = new ControladorOferta();
    controladorOferta.altaTipoPublicacion("tipoTesting", "Uso para testing", "baja", 50, 500f,
        fechaDate);
    controladorUsuario.altaEmpresa("nicknameEmpresa1", "nombre1", "apellido1",
        "email1@test.com", "descripcion1", "sitioWeb1", null, "nuevaContraseña");
    
    List<String> listaKeyword = new ArrayList<String>();
    listaKeyword.add("Keyword1");
    listaKeyword.add("Keyword2");
    Collections.sort(listaKeyword);
    controladorOferta.altaKeyword("Keyword1");
    controladorOferta.altaKeyword("Keyword2");
    
    controladorOferta.altaOfertaLaboral("test", "descipcionTest", "09:00", "15:00", 500f,
        "Montevideo", "Montevideo", fechaDate, "tipoTesting", "nicknameEmpresa1", listaKeyword,
        null, null);
    
    try {
      controladorOferta.obtenerDtPaquetePublicacion("test");
    } catch (OfertaLaboralNoExisteException | OfertaLaboralNoTienePaquete e) {
      Assert.assertEquals("La oferta: test no fue comprada por paquete", e.getMessage());
    }
    
  }
  
  @Test
  public void testObtenerKeywords() throws KeywordYaExisteException, KeywordNoExisteException,
      TipoPublicacionNoExisteException {
    manejadorSettings = ManejadorSettings.getInstance();
    manejadorSettings = ManejadorSettings.getInstance();
    controladorOferta = new ControladorOferta();
    controladorOferta.altaKeyword("KeywordTest");
    Keyword keyword = controladorOferta.obtenerKeywords("KeywordTest");
    Assert.assertEquals(keyword.getNombre(), "KeywordTest");
  }
  
  @Test
  public void testObtenerDtPostulacionesDeOferta()
      throws TipoPublicacionYaExisteException, UsuarioYaExisteException,
      UsuarioEmailRepetidoException, KeywordYaExisteException, OfertaLaboralYaExisteException,
      TipoPublicacionNoExisteException, KeywordNoExisteException, UsuarioNoExisteException,
      OfertaLaboralNoExisteException, UsuarioYaExistePostulacion {
    manejadorOfertas = ManejadorOfertas.getInstance();
    manejadorSettings = ManejadorSettings.getInstance();
    controladorUsuario = new ControladorUsuario();
    controladorOferta = new ControladorOferta();
    controladorOferta.altaTipoPublicacion("tipoTesting", "Uso para testing", "baja", 50, 500f,
        fechaDate);
    controladorUsuario.altaEmpresa("nicknameEmpresa1", "nombre1", "apellido1",
        "email1@test.com", "descripcion1", "sitioWeb1", null, "nuevaContraseña");
    
    List<String> listaKeyword = new ArrayList<String>();
    listaKeyword.add("Keyword1");
    controladorOferta.altaKeyword("Keyword1");
    
    controladorOferta.altaOfertaLaboral("test", "descipcionTest", "09:00", "15:00", 500f,
        "Montevideo", "Montevideo", fechaDate, "tipoTesting", "nicknameEmpresa1", listaKeyword,
        null, null);
    
    controladorUsuario.altaPostulante("maro", "María", "Rodríguez", "marrod@gmail.com",
        fechaDate, "Uruguaya", null, "1234");
    controladorUsuario.registrarPostulacion(
        "Ingeniero en Sistemas, experiencia en desarrollo web y"
            + " aplicaciones móviles. Conocimientos en JavaScript y React.",
        "Me entusiasma la posibilidad de trabajar en proyectos "
            + "desafiantes y seguir creciendo como profesional en el campo de la tecnología.",
        fechaDate, "maro", "test");
    
    List<Dtpostulacion> dtPostulacion = controladorOferta
        .obtenerDtPostulacionesDeOferta("test");
    
    Assert.assertEquals(dtPostulacion.get(0).getCvReducido(),
        "Ingeniero en Sistemas, experiencia en desarrollo web y"
            + " aplicaciones móviles. Conocimientos en JavaScript y React.");
    Assert.assertEquals(dtPostulacion.get(0).getDescripMotivacion(),
        "Me entusiasma la posibilidad de trabajar en proyectos "
            + "desafiantes y seguir creciendo como profesional en el campo de la tecnología.");
    Assert.assertEquals(dtPostulacion.get(0).getnicknamePostulante(), "maro");
    
  }
  
  @Test
  public void testAceptarRechazarOfertaLaboral()
      throws UsuarioYaExisteException, UsuarioEmailRepetidoException,
      TipoPublicacionYaExisteException, KeywordYaExisteException,
      OfertaLaboralYaExisteException, TipoPublicacionNoExisteException,
      KeywordNoExisteException, UsuarioNoExisteException, OfertaLaboralNoExisteException {
    manejadorOfertas = ManejadorOfertas.getInstance();
    manejadorSettings = ManejadorSettings.getInstance();
    controladorUsuario = new ControladorUsuario();
    controladorOferta = new ControladorOferta();
    controladorOferta.altaTipoPublicacion("tipoTesting", "Uso para testing", "baja", 50, 500f,
        fechaDate);
    controladorUsuario.altaEmpresa("nicknameEmpresa1", "nombre1", "apellido1",
        "email1@test.com", "descripcion1", "sitioWeb1", null, "nuevaContraseña");
    
    List<String> listaKeyword = new ArrayList<String>();
    listaKeyword.add("Keyword1");
    controladorOferta.altaKeyword("Keyword1");
    
    controladorOferta.altaOfertaLaboral("test", "descipcionTest", "09:00", "15:00", 500f,
        "Montevideo", "Montevideo", fechaDate, "tipoTesting", "nicknameEmpresa1", listaKeyword,
        null, null);
    controladorOferta.aceptarRechazarOfertaLaboral("test", EstadoOferta.CONFIRMADA, fechaDate);
    
    OfertaLaboral obtenerOfertaLaboral = controladorOferta.obtenerOfertaLaboral("test");
    
    Assert.assertEquals(obtenerOfertaLaboral.getEstado(), EstadoOferta.CONFIRMADA);
    
  }
  
  @Test
  public void testObtenerDtOfertasConfirmadas()
      throws TipoPublicacionYaExisteException, UsuarioYaExisteException,
      UsuarioEmailRepetidoException, KeywordYaExisteException, OfertaLaboralYaExisteException,
      TipoPublicacionNoExisteException, KeywordNoExisteException, UsuarioNoExisteException,
      OfertaLaboralNoExisteException, IOException {
    manejadorOfertas = ManejadorOfertas.getInstance();
    manejadorSettings = ManejadorSettings.getInstance();
    controladorUsuario = new ControladorUsuario();
    controladorOferta = new ControladorOferta();
    controladorOferta.altaTipoPublicacion("tipoTesting", "Uso para testing", "baja", 50, 500f,
        fechaDate);
    controladorUsuario.altaEmpresa("nicknameEmpresa1", "nombre1", "apellido1",
        "email1@test.com", "descripcion1", "sitioWeb1", null, "nuevaContraseña");
    
    List<String> listaKeyword = new ArrayList<String>();
    listaKeyword.add("Keyword1");
    controladorOferta.altaKeyword("Keyword1");
    
    controladorOferta.altaOfertaLaboral("test", "descipcionTest", "09:00", "15:00", 500f,
        "Montevideo", "Montevideo", fechaDate, "tipoTesting", "nicknameEmpresa1", listaKeyword,
        null, null);
    controladorOferta.aceptarRechazarOfertaLaboral("test", EstadoOferta.CONFIRMADA, fechaDate);
    List<DtOfertaLaboral> dtOfertasConfirmadas = controladorOferta
        .obtenerDtOfertasConfirmadas();
    Assert.assertEquals(dtOfertasConfirmadas.get(0).getEstadoOferta(),
        EstadoOferta.CONFIRMADA);
    Assert.assertEquals(dtOfertasConfirmadas.get(0).getNombre(), "test");
  }
  
  @Test
  public void testEstaCompradoPaqueteFalse()
      throws TipoPublicacionYaExisteException, PaquetePublicacionYaExisteException,
      TipoPublicacionNoExisteException, PaquetePublicacionNoExisteException {
    manejadorOfertas = ManejadorOfertas.getInstance();
    manejadorSettings = ManejadorSettings.getInstance();
    manejadorPaquetes = ManejadorPaquetes.getInstance();
    controladorOferta = new ControladorOferta();
    TipoPublicacion publicacionPrueba = new TipoPublicacion("tipoPublicacionPrueba",
        "descripcionPrueba", "alta", 20, 50f, fechaDate);
    manejadorSettings.addTipoPublicacion(publicacionPrueba);
    DtcantidadTipoPublicacion nuevoDt = new DtcantidadTipoPublicacion("tipoPublicacionPrueba",
        5);
    List<DtcantidadTipoPublicacion> arrayDtCantidad = new ArrayList<DtcantidadTipoPublicacion>();
    arrayDtCantidad.add(nuevoDt);
    controladorOferta.registrarPaquete("paquetePrueba", "descripcionPrueba", 20, 50f, null,
        fechaDate, arrayDtCantidad);
    
    Assert.assertTrue(!controladorOferta.estaCompradoPaquete("paquetePrueba"));
  }
  
  @Test
  public void testEstaCompradoPaqueteTrue() throws TipoPublicacionYaExisteException,
      PaquetePublicacionYaExisteException, TipoPublicacionNoExisteException,
      UsuarioYaExisteException, UsuarioEmailRepetidoException, UsuarioNoExisteException,
      PaquetePublicacionNoExisteException {
    manejadorOfertas = ManejadorOfertas.getInstance();
    manejadorSettings = ManejadorSettings.getInstance();
    manejadorPaquetes = ManejadorPaquetes.getInstance();
    controladorOferta = new ControladorOferta();
    controladorUsuario = new ControladorUsuario();
    TipoPublicacion publicacionPrueba = new TipoPublicacion("tipoPublicacionPrueba",
        "descripcionPrueba", "alta", 20, 50f, fechaDate);
    manejadorSettings.addTipoPublicacion(publicacionPrueba);
    DtcantidadTipoPublicacion nuevoDt = new DtcantidadTipoPublicacion("tipoPublicacionPrueba",
        5);
    List<DtcantidadTipoPublicacion> arrayDtCantidad = new ArrayList<DtcantidadTipoPublicacion>();
    arrayDtCantidad.add(nuevoDt);
    controladorOferta.registrarPaquete("paquetePrueba", "descripcionPrueba", 20, 50f, null,
        fechaDate, arrayDtCantidad);
    
    controladorUsuario.altaEmpresa("nicknameEmpresa1", "nombre1", "apellido1",
        "email1@test.com", "descripcion1", "sitioWeb1", null, "nuevaContraseña");
    
    controladorUsuario.comprarPaquete("nicknameEmpresa1", "paquetePrueba",
        fechaDateSecundaria);
    
    Assert.assertTrue(controladorOferta.estaCompradoPaquete("paquetePrueba"));
  }
}