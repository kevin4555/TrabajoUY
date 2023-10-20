package testing;

import excepciones.OfertaLaboralYaExisteException;
import excepciones.PaquetePublicacionYaFueComprado;
import excepciones.TipoDePublicacionYaFueIngresado;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import logica.classes.CantidadTipoPublicacionRestante;
import logica.classes.CantidadTotalTipoPublicacion;
import logica.classes.CompraPaquete;
import logica.classes.Empresa;
import logica.classes.Keyword;
import logica.classes.OfertaLaboral;
import logica.classes.PaquetePublicacion;
import logica.classes.Postulacion;
import logica.classes.Postulante;
import logica.classes.TipoPublicacion;
import logica.datatypes.DtOfertaLaboral;
import logica.datatypes.Dtempresa;
import logica.datatypes.DtpaquetePublicacion;
import logica.datatypes.Dtpostulacion;
import logica.datatypes.DttipoPublicacion;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Clase ClasesTest.
 */

public class ClasesTest {
  
  private Date fechaDateParse;
  private LocalDate fechaDate;
  private String fecha = "24/08/2023";
  
  /**
   * Metodo setUp.
   */
  
  @Before
  public void setUp() {
    DateFormat dateFormat = new SimpleDateFormat(
        "dd/MM/yyyy");
    try {
      this.fechaDateParse = dateFormat.parse(fecha);
      fechaDate = fechaDateParse.toInstant()
          .atZone(ZoneId.systemDefault()).toLocalDate();
      
    } catch (ParseException e) {
      e.getMessage();
    }
  }
  
  @Test
  public void cantidadTipoPublicacionRestanteClase() {
    TipoPublicacion tipoPublicacion = new TipoPublicacion(
        "tipoTesting", "Uso para testing", "baja", 50, 500f,
        fechaDate);
    CantidadTipoPublicacionRestante cantidadTipoPublicacionRestante = 
        new CantidadTipoPublicacionRestante(
        5, tipoPublicacion);
    Assert.assertEquals(
        cantidadTipoPublicacionRestante.getCantidad(), 5);
    Assert.assertEquals(cantidadTipoPublicacionRestante
        .getTipoPublicacion(), tipoPublicacion);
    
  }
  
  @Test
  public void paquetePublicacionClase()
      throws PaquetePublicacionYaFueComprado,
      TipoDePublicacionYaFueIngresado, IOException {
    TipoPublicacion tipoPublicacionInicial = new TipoPublicacion(
        "tipoTestingInicial", "Uso para testing", "baja",
        50, 500f, fechaDate);
    CantidadTotalTipoPublicacion tipoTestingInicialTotal = new CantidadTotalTipoPublicacion(
        5, tipoPublicacionInicial);
    List<CantidadTotalTipoPublicacion> arrayPrueba = new ArrayList<CantidadTotalTipoPublicacion>();
    arrayPrueba.add(tipoTestingInicialTotal);
    List<CantidadTotalTipoPublicacion> arrayPrueba2 = new ArrayList<CantidadTotalTipoPublicacion>();
    PaquetePublicacion paquetePublicacion = new PaquetePublicacion(
        "nombrePruebaPrimero", "descripcionPrueba", 20, 50f,
        null, arrayPrueba, fechaDate);
    
    paquetePublicacion.setNombre("nuevoNombre");
    paquetePublicacion.setDescripcion("nuevaDescripcion");
    paquetePublicacion.setImagen(null);
    paquetePublicacion
        .setCantidadTotalTipoPublicaciones(arrayPrueba2);
    paquetePublicacion.setPeriodoValidez(12);
    TipoPublicacion tipoPublicacion = new TipoPublicacion(
        "tipoTesting", "Uso para testing", "baja", 50, 500f,
        fechaDate);
    paquetePublicacion
        .agregarTipoPublicacion(tipoPublicacion, 5);
    paquetePublicacion
        .agregarTipoPublicacion(tipoPublicacionInicial, 2);
    try {
      paquetePublicacion.agregarTipoPublicacion(
          tipoPublicacionInicial, 2);
    } catch (TipoDePublicacionYaFueIngresado e) {
      Assert.assertEquals("El tipo de publicación "
          + tipoPublicacionInicial.getNombre()
          + " ya fue ingresado", e.getMessage());
    }
    
    paquetePublicacion.setEstaComprado(true);
    Assert.assertEquals(
        paquetePublicacion.getEstaComprado(), true);
    try {
      paquetePublicacion.agregarTipoPublicacion(
          tipoPublicacionInicial, 2);
    } catch (PaquetePublicacionYaFueComprado e) {
      Assert.assertEquals(
          "El paquete " + paquetePublicacion.getNombre()
              + " ya fue comprado",
          e.getMessage());
    }
    
    // Calculo el costo para despues validarlo contra
    // el .getCosto()
    List<CantidadTotalTipoPublicacion> cantidadTotalTipoPublicacions = paquetePublicacion
        .obtenerCantidadTotalTipoPublicaciones();
    float cantidadTotal = 0;
    float costo = 0;
    for (CantidadTotalTipoPublicacion cantidadTotalTipoPublicacion 
        : cantidadTotalTipoPublicacions) {
      cantidadTotal = cantidadTotal
          + (cantidadTotalTipoPublicacion.getCantidadTotal()
              * cantidadTotalTipoPublicacion
                  .getTipoPublicacion().getCosto());
    }
    costo = cantidadTotal * ((100 - 50f) / 100);
    
    DtpaquetePublicacion dtpaquetePublicacion = paquetePublicacion
        .obtenerDtPaquete();
    
    Assert.assertEquals(dtpaquetePublicacion.getNombre(),
        paquetePublicacion.getNombre());
    Assert.assertEquals(
        dtpaquetePublicacion.getDescripcion(),
        paquetePublicacion.getDescripcion());
    Assert.assertEquals(dtpaquetePublicacion.getFechaAlta(),
        paquetePublicacion.getFechaAlta());
    
    Assert.assertEquals(String.valueOf(costo),
        String.valueOf(paquetePublicacion.getCosto()));
    Assert.assertEquals(
        String.valueOf(paquetePublicacion.getDescuento()),
        String.valueOf(50f));
    Assert.assertEquals(paquetePublicacion.getNombre(),
        "nuevoNombre");
    Assert.assertEquals(paquetePublicacion.getDescripcion(),
        "nuevaDescripcion");
    Assert.assertEquals(paquetePublicacion.getImagen(),
        null);
    Assert.assertEquals(
        paquetePublicacion.getPeriodoValidez(), 12);
    List<String> resultadoList = paquetePublicacion
        .obtenerNombresTipoPublicaciones();
    Assert.assertEquals(resultadoList.get(0),
        "tipoTesting");
    Assert.assertEquals(resultadoList.get(1),
        "tipoTestingInicial");
  }
  
  @Test
  public void tipoPublicacionClase() {
    TipoPublicacion tipoPublicacion = new TipoPublicacion(
        "tipoTesting", "Uso para testing", "baja", 50, 500f,
        fechaDate);
    DttipoPublicacion dttipoPublicacion = tipoPublicacion
        .obtenerDttipoPublicacion();
    Assert.assertEquals(dttipoPublicacion.getDescripcion(),
        tipoPublicacion.getDescripcion());
    Assert.assertEquals(dttipoPublicacion.getExposicion(),
        tipoPublicacion.getExposicion());
    Assert.assertEquals(dttipoPublicacion.getNombre(),
        tipoPublicacion.getNombre());
    Assert.assertEquals(
        String.valueOf(dttipoPublicacion.getCosto()),
        String.valueOf(tipoPublicacion.getCosto()));
    Assert.assertEquals(dttipoPublicacion.getDuracionDia(),
        tipoPublicacion.getDuracionDia());
    Assert.assertEquals(dttipoPublicacion.getFechaAlta(),
        tipoPublicacion.getFechaAlta());
  }
  
  @Test
  public void postulanteClase() throws IOException {
    Postulante postulante = new Postulante("NicknameTest",
        "NombreTest", "ApellidoTest", "EmailTest",
        fechaDate, "NacionalidadTest", null, "1234");
    
    Empresa empresa = new Empresa("nicknameEmpresa1",
        "nombre1", "apellido1", "email1@test.com",
        "descripcion1", "sitioWeb1", null,
        "nuevaContraseña");
    
    TipoPublicacion tipoPublicacion = new TipoPublicacion(
        "tipoTesting", "Uso para testing", "baja", 50, 500f,
        fechaDate);
    
    List<String> listaKeyword = new ArrayList<String>();
    listaKeyword.add("Keyword1");
    
    OfertaLaboral ofertaLaboral = new OfertaLaboral("test",
        "descipcionTest", "09:00", "15:00", 500f,
        "Montevideo", "Montevideo", fechaDate,
        tipoPublicacion, null, empresa);
    
    Postulacion postulacion = new Postulacion(
        "DescripcionPostulacion", fechaDate,
        "cvReducidoPostulacion", postulante, ofertaLaboral);
    
    Dtpostulacion dtpostulacion = postulacion
        .obtenerDtpostulacion();
    
    Assert.assertEquals(dtpostulacion.getNombreOferta(),
        postulacion.getNombreOfertaLaboral());
    
    postulante.agregarPostulacion(postulacion);
    
    List<DtOfertaLaboral> dtOfertaLaboral = postulante
        .obtenerDtofertas();
    List<Postulacion> postulacionList = postulante
        .getPostulaciones();
    
    Assert.assertEquals(dtOfertaLaboral.get(0).getNombre(),
        ofertaLaboral.getNombre());
    Assert.assertEquals(
        dtOfertaLaboral.get(0).getEstadoOferta(),
        ofertaLaboral.getEstado());
    
    Assert.assertEquals(
        postulacionList.get(0).getCvReducido(),
        postulacion.getCvReducido());
    Assert.assertEquals(
        postulacionList.get(0).getNombreOfertaLaboral(),
        postulacion.getNombreOfertaLaboral());
    Assert.assertEquals(
        postulacionList.get(0).getDescrpMotivacion(),
        postulacion.getDescrpMotivacion());
    Assert.assertEquals(
        postulacionList.get(0).getPostulante(),
        postulacion.getPostulante());
  }
  
  @Test
  public void compraPaqueteClase() {
    TipoPublicacion tipoPublicacionInicial = new TipoPublicacion(
        "tipoTestingInicial", "Uso para testing", "baja",
        50, 500f, fechaDate);
    CantidadTotalTipoPublicacion tipoTestingInicialTotal = new CantidadTotalTipoPublicacion(
        5, tipoPublicacionInicial);
    List<CantidadTotalTipoPublicacion> arrayPrueba = new ArrayList<CantidadTotalTipoPublicacion>();
    arrayPrueba.add(tipoTestingInicialTotal);
    List<CantidadTotalTipoPublicacion> arrayPrueba2 = new ArrayList<CantidadTotalTipoPublicacion>();
    PaquetePublicacion paquetePublicacion = new PaquetePublicacion(
        "nombrePruebaPrimero", "descripcionPrueba", 20, 50f,
        null, arrayPrueba, fechaDate);
    
    CompraPaquete compraPaquete = new CompraPaquete(
        fechaDate, paquetePublicacion);
    
    LocalDate copiaFechaCompra = LocalDate.of(
        fechaDate.getYear(), fechaDate.getMonth(),
        fechaDate.getDayOfMonth());
    LocalDate fechaVencimiento = copiaFechaCompra
        .plusDays(paquetePublicacion.getPeriodoValidez());
    
    Assert.assertEquals(compraPaquete.getFechaCompra(),
        fechaDate);
    Assert.assertEquals(compraPaquete.getFechaVencimiento(),
        fechaVencimiento);
  }
  
  @Test
  public void empresaClase()
      throws OfertaLaboralYaExisteException, IOException {
    Empresa empresa = new Empresa("nicknameEmpresa1",
        "nombre1", "apellido1", "email1", "descripcion1",
        "sitioWeb1", null, "1234");
    TipoPublicacion tipoPublicacion = new TipoPublicacion(
        "tipoTesting", "Uso para testing", "baja", 50, 500f,
        fechaDate);
    
    List<String> listaKeyword = new ArrayList<String>();
    listaKeyword.add("Keyword1");
    
    OfertaLaboral ofertaLaboral = new OfertaLaboral("test",
        "descipcionTest", "09:00", "15:00", 500f,
        "Montevideo", "Montevideo", fechaDate,
        tipoPublicacion, null, empresa);
    
    empresa.agregarOferta(ofertaLaboral);
    
    try {
      empresa.agregarOferta(ofertaLaboral);
    } catch (OfertaLaboralYaExisteException e) {
      Assert.assertEquals(e.getMessage(),
          "La Oferta Laboral " + ofertaLaboral.getNombre()
              + " ya esta asociada a la Empresa "
              + empresa.getNickname());
      
    }
    
    Dtempresa dtEmpresa = empresa.obtenerDtempresa();
    
    Assert.assertEquals(dtEmpresa.getNickname(),
        empresa.getNickname());
    Assert.assertEquals(dtEmpresa.getEmail(),
        empresa.getEmail());
    
    List<String> nombreOferta = empresa
        .listarNombreOfertasUsuario();
    Assert.assertEquals(nombreOferta.get(0),
        ofertaLaboral.getNombre());
    
    CantidadTotalTipoPublicacion tipoTestingInicialTotal = new CantidadTotalTipoPublicacion(
        5, tipoPublicacion);
    List<CantidadTotalTipoPublicacion> arrayPrueba = new ArrayList<CantidadTotalTipoPublicacion>();
    arrayPrueba.add(tipoTestingInicialTotal);
    
    PaquetePublicacion paquetePublicacion = new PaquetePublicacion(
        "nombrePruebaPrimero", "descripcionPrueba", 20, 50f,
        null, arrayPrueba, fechaDate);
    
    CompraPaquete compraPaquete = new CompraPaquete(
        fechaDate, paquetePublicacion);
    
    empresa.comprarPaquete(compraPaquete);
    
    Assert.assertEquals(empresa.estaCompradoPaquete(
        paquetePublicacion.getNombre()), true);
    
  }
  
  @Test
  public void ofertaLaboralClase() throws IOException {
    Empresa empresa = new Empresa("nicknameEmpresa1",
        "nombre1", "apellido1", "email1@test.com",
        "descripcion1", "sitioWeb1", null,
        "nuevaContraseña");
    
    TipoPublicacion tipoPublicacion = new TipoPublicacion(
        "tipoTesting", "Uso para testing", "baja", 50, 500f,
        fechaDate);
    
    List<String> listaKeyword = new ArrayList<String>();
    listaKeyword.add("Keyword1");
    
    OfertaLaboral ofertaLaboral = new OfertaLaboral("test",
        "descipcionTest", "09:00", "15:00", 500f,
        "Montevideo", "Montevideo", fechaDate,
        tipoPublicacion, null, empresa);
    
    List<Keyword> keywordsList = new ArrayList<>();
    Keyword keyword = new Keyword("Keyword1");
    keywordsList.add(keyword);
    
    ofertaLaboral.setDescripcion("nuevaDescripcion");
    Assert.assertEquals(ofertaLaboral.getDescripcion(),
        "nuevaDescripcion");
    ofertaLaboral.setCiudad("Soriano");
    Assert.assertEquals(ofertaLaboral.getCiudad(),
        "Soriano");
    ofertaLaboral.setDepartamento("Soriano");
    Assert.assertEquals(ofertaLaboral.getDepartamento(),
        "Soriano");
    ofertaLaboral.setRemunaracion(50f);
    Assert.assertEquals(
        String.valueOf(ofertaLaboral.getRemunaracion()),
        String.valueOf(50f));
    ofertaLaboral.setFechaAlta(fechaDate);
    Assert.assertEquals(ofertaLaboral.getFechaAlta(),
        fechaDate);
    ofertaLaboral.setKeyword(keywordsList);
    Assert.assertEquals(ofertaLaboral.getKeywords(),
        keywordsList);
    ofertaLaboral.setTipoPublicacion(tipoPublicacion);
    Assert.assertEquals(ofertaLaboral.getTipoPublicacion(),
        tipoPublicacion);
    Assert.assertEquals(ofertaLaboral.getPostulacion(),
        new ArrayList<Postulacion>());
    Assert.assertEquals(ofertaLaboral.getCompraPaquete(),
        null);
    ofertaLaboral.setHorarioFinal(fecha);
    Assert.assertEquals(ofertaLaboral.getHorarioFinal(),
        fecha);
    ofertaLaboral.setHorarioInicial(fecha);
    Assert.assertEquals(ofertaLaboral.getHorarioInicial(),
        fecha);
    
    CantidadTotalTipoPublicacion tipoTestingInicialTotal = new CantidadTotalTipoPublicacion(
        5, tipoPublicacion);
    List<CantidadTotalTipoPublicacion> arrayPrueba = new ArrayList<CantidadTotalTipoPublicacion>();
    arrayPrueba.add(tipoTestingInicialTotal);
    
    PaquetePublicacion paquetePublicacion = new PaquetePublicacion(
        "nombrePruebaPrimero", "descripcionPrueba", 20, 50f,
        null, arrayPrueba, fechaDate);
    
    CompraPaquete compraPaquete = new CompraPaquete(
        fechaDate, paquetePublicacion);
    
    empresa.comprarPaquete(compraPaquete);
    
    ofertaLaboral.setCompraPaquete(compraPaquete);
    
    DtOfertaLaboral dtOfertaLaboral = ofertaLaboral
        .obtenerDtOfertaLaboral();
    
    Assert.assertEquals(dtOfertaLaboral.getNombre(),
        ofertaLaboral.getNombre());
    Assert.assertEquals(dtOfertaLaboral.getDescripcion(),
        ofertaLaboral.getDescripcion());
    Assert.assertEquals(
        dtOfertaLaboral.getPaqueteAsociado().getNombre(),
        paquetePublicacion.getNombre());
    Assert.assertEquals(
        dtOfertaLaboral.getNombreTipoPublicacion(),
        ofertaLaboral.getTipoPublicacion().getNombre());
    
  }
  
}