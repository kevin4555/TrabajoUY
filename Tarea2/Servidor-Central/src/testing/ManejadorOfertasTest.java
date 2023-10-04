package testing;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

import excepciones.DtOfertaNoExisteException;
import excepciones.OfertaLaboralNoExisteException;
import excepciones.OfertaLaboralYaExisteException;
import excepciones.TipoPublicacionNoExisteException;
import excepciones.TipoPublicacionYaExisteException;
import junit.framework.Assert;
import logica.classes.Empresa;
import logica.classes.OfertaLaboral;
import logica.classes.TipoPublicacion;
import logica.datatypes.DtOfertaLaboral;
import logica.datatypes.Dtpostulacion;
import logica.datatypes.EstadoOferta;
import logica.handlers.ManejadorOfertas;

public class ManejadorOfertasTest {
  
  private LocalDate fechaDate;
  private String fecha = "1988-11-10";
  private Empresa empresa1;
  private ManejadorOfertas manejadorOfertas;
  
  @Before
  public void setUp() {
    empresa1 = new Empresa("nicknameEmpresa1", "nombre1", "apellido1", "email1@empresa1",
        "descripcion1", "sitioWeb1", null, "1234");
    fechaDate = LocalDate.parse(fecha);
    manejadorOfertas = ManejadorOfertas.getInstance();
    manejadorOfertas.clean();
  }
  
  @Test
  public void testAgregarYObtenerOferta()
      throws OfertaLaboralYaExisteException, OfertaLaboralNoExisteException,
      TipoPublicacionYaExisteException, TipoPublicacionNoExisteException {
    manejadorOfertas = ManejadorOfertas.getInstance();
    TipoPublicacion tipoCreadoPublicacion = new TipoPublicacion("tipoTesting",
        "Uso para testing", "baja", 50, 500f, fechaDate);
    OfertaLaboral oferta = new OfertaLaboral("nombre", "Descipcion para testing", "09:00",
        "15:00", 10F, "Montevideo", "Montevideo", fechaDate, tipoCreadoPublicacion, null, empresa1);
    manejadorOfertas.agregarOferta(oferta);
    OfertaLaboral obtenida = manejadorOfertas.obtenerOfertaLaboral("nombre");
    assertEquals(oferta, obtenida);
    
  }
  
  @Test()
  public void testAgregarOfertaDuplicada() throws OfertaLaboralYaExisteException,
      TipoPublicacionNoExisteException, TipoPublicacionYaExisteException {
    manejadorOfertas = ManejadorOfertas.getInstance();
    TipoPublicacion tipoCreadoPublicacion = new TipoPublicacion("tipoTesting",
        "Uso para testing", "baja", 50, 500f, fechaDate);
    OfertaLaboral oferta = new OfertaLaboral("nombreTesting", "Descipcion para testing",
        "09:00", "15:00", 10F, "Montevideo", "Montevideo", fechaDate, tipoCreadoPublicacion,
        null, empresa1);
    manejadorOfertas.agregarOferta(oferta);
    try {
      manejadorOfertas.agregarOferta(oferta);
    } catch (OfertaLaboralYaExisteException e) {
      Assert.assertEquals("La oferta laboral que desea ingresar ya existe", e.getMessage());
    }
  }
  
  @Test
  public void testObtenerOfertaNoExistente() throws OfertaLaboralNoExisteException {
    manejadorOfertas = ManejadorOfertas.getInstance();
    try {
      manejadorOfertas.obtenerOfertaLaboral("OfertaNoExistente");
    } catch (OfertaLaboralNoExisteException e) {
      Assert.assertEquals("No existe la oferta solicitada", e.getMessage());
    }
  }
  
  @Test
  public void testObtenerDTOfertaInexistente() throws DtOfertaNoExisteException {
    manejadorOfertas = ManejadorOfertas.getInstance();
    
    try {
      manejadorOfertas.obtenerDtofertaLaboral("OfertaInexistente");
    } catch (DtOfertaNoExisteException e) {
      Assert.assertEquals("No existe la oferta solicitada", e.getMessage());
    }
  }
  
  @Test
  public void testObtenerDTYCompararlo()
      throws OfertaLaboralYaExisteException, DtOfertaNoExisteException {
    manejadorOfertas = ManejadorOfertas.getInstance();
    ArrayList<Dtpostulacion> arrayDT = new ArrayList<Dtpostulacion>();
    DtOfertaLaboral dtOfertaLaboral = new DtOfertaLaboral("nombreTesting",
        "descripcionTesting", "ciudadTesting", "departamentoTesting", "09:00", "15:00", 100f,
        fechaDate, arrayDT, fechaDate, EstadoOferta.CONFIRMADA, null, null, null, empresa1.getNickname());
    TipoPublicacion tipoCreadoPublicacion = new TipoPublicacion("tipoTesting",
        "Uso para testing", "baja", 50, 500f, fechaDate);
    OfertaLaboral ofertaLaboral = new OfertaLaboral("nombreTesting", "descripcionTesting",
        "09:00", "15:00", 100f, "ciudadTesting", "departamentoTesting", fechaDate,
        tipoCreadoPublicacion, null, empresa1);
    manejadorOfertas.agregarOferta(ofertaLaboral);
    DtOfertaLaboral resultaDtOfertaLaboral = manejadorOfertas
        .obtenerDtofertaLaboral("nombreTesting");
    Assert.assertEquals(dtOfertaLaboral.getNombre(), resultaDtOfertaLaboral.getNombre());
    Assert.assertEquals(dtOfertaLaboral.getCiudad(), resultaDtOfertaLaboral.getCiudad());
    Assert.assertEquals(dtOfertaLaboral.getDepartamento(),
        resultaDtOfertaLaboral.getDepartamento());
    Assert.assertEquals(dtOfertaLaboral.getHorarioInicio(),
        resultaDtOfertaLaboral.getHorarioInicio());
    Assert.assertEquals(dtOfertaLaboral.getHorarioFinal(),
        resultaDtOfertaLaboral.getHorarioFinal());
    Assert.assertEquals(dtOfertaLaboral.getRemuneracion(),
        resultaDtOfertaLaboral.getRemuneracion());
    Assert.assertEquals(dtOfertaLaboral.getFechaAlta(), resultaDtOfertaLaboral.getFechaAlta());
    Assert.assertEquals(dtOfertaLaboral.getPostulaciones(),
        resultaDtOfertaLaboral.getPostulaciones());
    Assert.assertEquals(dtOfertaLaboral.getDescripcion(),
        resultaDtOfertaLaboral.getDescripcion());
  }
  
  @Test
  public void testObtenerListaOfertasLaboralesYComprar()
      throws OfertaLaboralYaExisteException {
    manejadorOfertas = ManejadorOfertas.getInstance();
    TipoPublicacion tipoPublicacion = new TipoPublicacion("tipoTesting", "Uso para testing",
        "baja", 50, 500f, fechaDate);
    OfertaLaboral ofertaLaboral = new OfertaLaboral("nombreTesting", "descripcionTesting",
        "09:00", "15:00", 100f, "ciudadTesting", "departamentoTesting", fechaDate,
        tipoPublicacion, null, empresa1);
    TipoPublicacion tipoCreadoPublicacion = new TipoPublicacion("tipoTesting",
        "Uso para testing", "baja", 50, 500f, fechaDate);
    OfertaLaboral oferta = new OfertaLaboral("nombre", "Descipcion para testing", "09:00",
        "15:00", 10F, "Montevideo", "Montevideo", fechaDate, tipoCreadoPublicacion, null, empresa1);
    manejadorOfertas.agregarOferta(ofertaLaboral);
    manejadorOfertas.agregarOferta(oferta);
    ArrayList<String> resultado = (ArrayList<String>) manejadorOfertas
        .listarOfertasLaborales();
    Collections.sort(resultado);
    ArrayList<String> resultadoEsperado = new ArrayList<String>();
    resultadoEsperado.add("nombreTesting");
    resultadoEsperado.add("nombre");
    Collections.sort(resultadoEsperado);
    Assert.assertEquals(resultadoEsperado, resultado);
  }
  
}
