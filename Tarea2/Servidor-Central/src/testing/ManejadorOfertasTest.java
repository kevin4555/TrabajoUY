package testing;

import static org.junit.Assert.assertEquals;

import excepciones.DtOfertaNoExisteException;
import excepciones.OfertaLaboralNoExisteException;
import excepciones.OfertaLaboralYaExisteException;
import excepciones.TipoPublicacionNoExisteException;
import excepciones.TipoPublicacionYaExisteException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import logica.classes.Empresa;
import logica.classes.OfertaLaboral;
import logica.classes.TipoPublicacion;
import logica.datatypes.DtOfertaLaboral;
import logica.datatypes.Dtpostulacion;
import logica.datatypes.EstadoOferta;
import logica.handlers.ManejadorOfertas;
import org.junit.Before;
import org.junit.Test;

/**
 * Clase ManejadorOfertasTest.
 */

public class ManejadorOfertasTest {
  
  private LocalDate fechaDate;
  private String fecha = "1988-11-10";
  private Empresa empresa1;
  private ManejadorOfertas manejadorOfertas;
  
  /**
   * Metodo setUp.
   */
  
  @Before
  public void setUp() {
    empresa1 = new Empresa("nicknameEmpresa1", "nombre1", "apellido1", "email1@empresa1",
        "descripcion1", "sitioWeb1", null, "1234");
    fechaDate = LocalDate.parse(fecha);
    manejadorOfertas = ManejadorOfertas.getInstance();
    manejadorOfertas.clean();
  }
  
  @Test
  public void testAgregaryobtenerOferta()
      throws OfertaLaboralYaExisteException, OfertaLaboralNoExisteException,
      TipoPublicacionYaExisteException, TipoPublicacionNoExisteException {
    manejadorOfertas = ManejadorOfertas.getInstance();
    TipoPublicacion tipoCreadoPublicacion = new TipoPublicacion("tipoTesting",
        "Uso para testing", "baja", 50, 500f, fechaDate);
    OfertaLaboral oferta = new OfertaLaboral("nombre", "Descipcion para testing", "09:00",
        "15:00", 10F, "Montevideo", "Montevideo", fechaDate, tipoCreadoPublicacion, null,
        empresa1);
    manejadorOfertas.agregarOferta(oferta);
    OfertaLaboral obtenida = manejadorOfertas.obtenerOfertaLaboral("nombre");
    assertEquals(oferta, obtenida);
    
  }
  
  @Test
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
      assertEquals("La oferta laboral que desea ingresar ya existe", e.getMessage());
    }
  }
  
  @Test
  public void testObtenerOfertaNoExistente() throws OfertaLaboralNoExisteException {
    manejadorOfertas = ManejadorOfertas.getInstance();
    try {
      manejadorOfertas.obtenerOfertaLaboral("OfertaNoExistente");
    } catch (OfertaLaboralNoExisteException e) {
      assertEquals("No existe la oferta solicitada", e.getMessage());
    }
  }
  
  @Test
  public void testObtenerDtOfertaInexistente() throws DtOfertaNoExisteException, IOException {
    manejadorOfertas = ManejadorOfertas.getInstance();
    
    try {
      manejadorOfertas.obtenerDtofertaLaboral("OfertaInexistente");
    } catch (DtOfertaNoExisteException e) {
      assertEquals("No existe la oferta solicitada", e.getMessage());
    }
  }
  
  @Test
  public void testObtenerDtycompararlo()
      throws OfertaLaboralYaExisteException, DtOfertaNoExisteException, IOException {
    manejadorOfertas = ManejadorOfertas.getInstance();
    List<Dtpostulacion> arrayDt = new ArrayList<Dtpostulacion>();
    DtOfertaLaboral dtOfertaLaboral = new DtOfertaLaboral("nombreTesting",
        "descripcionTesting", "ciudadTesting", "departamentoTesting", "09:00", "15:00", 100f,
        fechaDate, arrayDt, fechaDate, EstadoOferta.CONFIRMADA, null, null, null, false,
        "tipoTesting", empresa1.getNickname());
    TipoPublicacion tipoCreadoPublicacion = new TipoPublicacion("tipoTesting",
        "Uso para testing", "baja", 50, 500f, fechaDate);
    OfertaLaboral ofertaLaboral = new OfertaLaboral("nombreTesting", "descripcionTesting",
        "09:00", "15:00", 100f, "ciudadTesting", "departamentoTesting", fechaDate,
        tipoCreadoPublicacion, null, empresa1);
    manejadorOfertas.agregarOferta(ofertaLaboral);
    DtOfertaLaboral resultaDtOfertaLaboral = manejadorOfertas
        .obtenerDtofertaLaboral("nombreTesting");
    assertEquals(dtOfertaLaboral.getNombre(), resultaDtOfertaLaboral.getNombre());
    assertEquals(dtOfertaLaboral.getCiudad(), resultaDtOfertaLaboral.getCiudad());
    assertEquals(dtOfertaLaboral.getDepartamento(), resultaDtOfertaLaboral.getDepartamento());
    assertEquals(dtOfertaLaboral.getHorarioInicio(),
        resultaDtOfertaLaboral.getHorarioInicio());
    assertEquals(dtOfertaLaboral.getHorarioFinal(), resultaDtOfertaLaboral.getHorarioFinal());
    assertEquals(dtOfertaLaboral.getRemuneracion(), resultaDtOfertaLaboral.getRemuneracion());
    assertEquals(dtOfertaLaboral.getFechaAlta(), resultaDtOfertaLaboral.getFechaAlta());
    assertEquals(dtOfertaLaboral.getPostulaciones(),
        resultaDtOfertaLaboral.getPostulaciones());
    assertEquals(dtOfertaLaboral.getDescripcion(), resultaDtOfertaLaboral.getDescripcion());
  }
  
  @Test
  public void testObtenerListaOfertasLaboralesycomprar()
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
        "15:00", 10F, "Montevideo", "Montevideo", fechaDate, tipoCreadoPublicacion, null,
        empresa1);
    manejadorOfertas.agregarOferta(ofertaLaboral);
    manejadorOfertas.agregarOferta(oferta);
    List<String> resultado = (ArrayList<String>) manejadorOfertas
        .listarOfertasLaborales();
    Collections.sort(resultado);
    List<String> resultadoEsperado = new ArrayList<String>();
    resultadoEsperado.add("nombreTesting");
    resultadoEsperado.add("nombre");
    Collections.sort(resultadoEsperado);
    assertEquals(resultadoEsperado, resultado);
  }
  
}
