package test.java;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import main.java.classes.Empresa;
import main.java.classes.OfertaLaboral;
import main.java.classes.TipoPublicacion;
import main.java.excepciones.OfertaLaboralNoExisteException;
import main.java.excepciones.OfertaLaboralYaExisteException;
import main.java.excepciones.TipoPublicacionNoExisteException;
import main.java.excepciones.TipoPublicacionYaExisteException;
import main.java.handlers.ManejadorOfertas;

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
    empresa1 = new Empresa("nicknameEmpresa1", "nombre1",
          "apellido1", "email1@empresa1", "descripcion1",
          "sitioWeb1", null, "1234");
    fechaDate = LocalDate.parse(fecha);
    manejadorOfertas = ManejadorOfertas.getInstance();
    manejadorOfertas.clean();
  }

  @Test
  public void testAgregaryobtenerOferta()
        throws OfertaLaboralYaExisteException,
        OfertaLaboralNoExisteException,
        TipoPublicacionYaExisteException,
        TipoPublicacionNoExisteException {
    manejadorOfertas = ManejadorOfertas.getInstance();
    TipoPublicacion tipoCreadoPublicacion =
          new TipoPublicacion(
                "tipoTesting", "Uso para testing", "baja",
                50, 500f,
                fechaDate);
    OfertaLaboral oferta = new OfertaLaboral("nombre",
          "Descipcion para testing", "09:00", "15:00", 10F,
          "Montevideo", "Montevideo", fechaDate,
          tipoCreadoPublicacion, null, empresa1);
    manejadorOfertas.agregarOferta(oferta);
    OfertaLaboral obtenida = manejadorOfertas
          .obtenerOfertaLaboral("nombre");
    assertEquals(oferta, obtenida);

  }

  @Test
  public void testAgregarOfertaDuplicada()
        throws OfertaLaboralYaExisteException,
        TipoPublicacionNoExisteException,
        TipoPublicacionYaExisteException {
    manejadorOfertas = ManejadorOfertas.getInstance();
    TipoPublicacion tipoCreadoPublicacion =
          new TipoPublicacion(
                "tipoTesting", "Uso para testing", "baja",
                50, 500f,
                fechaDate);
    OfertaLaboral oferta = new OfertaLaboral(
          "nombreTesting", "Descipcion para testing",
          "09:00",
          "15:00", 10F, "Montevideo", "Montevideo",
          fechaDate,
          tipoCreadoPublicacion, null, empresa1);
    manejadorOfertas.agregarOferta(oferta);
    try {
      manejadorOfertas.agregarOferta(oferta);
    } catch (OfertaLaboralYaExisteException e) {
      assertEquals(
            "La oferta laboral que desea ingresar ya existe",
            e.getMessage());
    }
  }

  @Test
  public void testObtenerOfertaNoExistente()
        throws OfertaLaboralNoExisteException {
    manejadorOfertas = ManejadorOfertas.getInstance();
    try {
      manejadorOfertas
            .obtenerOfertaLaboral("OfertaNoExistente");
    } catch (OfertaLaboralNoExisteException e) {
      assertEquals("No existe la oferta solicitada",
            e.getMessage());
    }
  }

  @Test
  public void testObtenerListaOfertasLaboralesycomprar()
        throws OfertaLaboralYaExisteException {
    manejadorOfertas = ManejadorOfertas.getInstance();
    TipoPublicacion tipoPublicacion = new TipoPublicacion(
          "tipoTesting", "Uso para testing", "baja", 50,
          500f,
          fechaDate);
    OfertaLaboral ofertaLaboral = new OfertaLaboral(
          "nombreTesting", "descripcionTesting", "09:00",
          "15:00", 100f, "ciudadTesting",
          "departamentoTesting", fechaDate, tipoPublicacion,
          null, empresa1);
    TipoPublicacion tipoCreadoPublicacion =
          new TipoPublicacion(
                "tipoTesting", "Uso para testing", "baja",
                50, 500f,
                fechaDate);
    OfertaLaboral oferta = new OfertaLaboral("nombre",
          "Descipcion para testing", "09:00", "15:00", 10F,
          "Montevideo", "Montevideo", fechaDate,
          tipoCreadoPublicacion, null, empresa1);
    manejadorOfertas.agregarOferta(ofertaLaboral);
    manejadorOfertas.agregarOferta(oferta);
    List<String> resultado =
          (ArrayList<String>) manejadorOfertas
                .listarOfertasLaborales();
    Collections.sort(resultado);
    List<String> resultadoEsperado =
          new ArrayList<String>();
    resultadoEsperado.add("nombreTesting");
    resultadoEsperado.add("nombre");
    Collections.sort(resultadoEsperado);
    assertEquals(resultadoEsperado, resultado);
  }

}
