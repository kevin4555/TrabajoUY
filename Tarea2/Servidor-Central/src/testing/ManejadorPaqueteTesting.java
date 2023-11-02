package testing;

import excepciones.PaquetePublicacionNoExisteException;
import excepciones.PaquetePublicacionYaExisteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import logica.classes.CantidadTotalTipoPublicacion;
import logica.classes.PaquetePublicacion;
import logica.classes.TipoPublicacion;
import logica.handlers.ManejadorPaquetes;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Clase ManejadorPaqueteTesting.
 */

public class ManejadorPaqueteTesting {

  private static ManejadorPaquetes manejadorPaquete;
  private static LocalDate fechaDate1;
  private static LocalDate fechaDate2;

  public static void setUp() {
    fechaDate1 = LocalDate.parse("1988-11-10");
    fechaDate2 = LocalDate.parse("1988-09-02");
  }

  @Before
  public void cleanUp() {
    manejadorPaquete = ManejadorPaquetes.getInstance();
    manejadorPaquete.clean();
  }

  @Test
  public void agregarPaqueteTest()
        throws PaquetePublicacionYaExisteException,
        PaquetePublicacionNoExisteException {
    manejadorPaquete = ManejadorPaquetes.getInstance();
    TipoPublicacion tipoPublicacion = new TipoPublicacion(
          "nombreTipo", "descripcionTipo", "exposicionTipo",
          2, 200f, fechaDate1);
    CantidadTotalTipoPublicacion cantidadTipoPublicacion =
          new CantidadTotalTipoPublicacion(
                4, tipoPublicacion);
    List<CantidadTotalTipoPublicacion> listaCantidadTipoP =
          new ArrayList<CantidadTotalTipoPublicacion>();
    listaCantidadTipoP.add(cantidadTipoPublicacion);
    PaquetePublicacion paquete = new PaquetePublicacion(
          "nombrePaquete", "descripcionPaquete", 5, 20f,
          null,
          listaCantidadTipoP, fechaDate1);
    manejadorPaquete.agregarPaquete(paquete);
    PaquetePublicacion resultado = manejadorPaquete
          .obtenerPaquete("nombrePaquete");
    Assert.assertEquals(paquete, resultado);
  }

  @Test
  public void listarPaquetesTesting()
        throws PaquetePublicacionYaExisteException {
    manejadorPaquete = ManejadorPaquetes.getInstance();
    TipoPublicacion tipoPublicacion1 = new TipoPublicacion(
          "nombreTipo1", "descripcionTipo1",
          "exposicionTipo1", 2, 200f, fechaDate1);
    TipoPublicacion tipoPublicacion2 = new TipoPublicacion(
          "nombreTipo2", "descripcionTipo2",
          "exposicionTipo2", 3, 20f, fechaDate2);
    CantidadTotalTipoPublicacion cantidadTipoPublicacion1 =
          new CantidadTotalTipoPublicacion(
                4, tipoPublicacion1);
    CantidadTotalTipoPublicacion cantidadTipoPublicacion2 =
          new CantidadTotalTipoPublicacion(
                3, tipoPublicacion2);
    List<CantidadTotalTipoPublicacion> listaCantidadTipoP1 =
          new ArrayList<CantidadTotalTipoPublicacion>();
    List<CantidadTotalTipoPublicacion> listaCantidadTipoP2 =
          new ArrayList<CantidadTotalTipoPublicacion>();
    listaCantidadTipoP1.add(cantidadTipoPublicacion1);
    listaCantidadTipoP2.add(cantidadTipoPublicacion2);
    PaquetePublicacion paquete1 = new PaquetePublicacion(
          "nombrePaquete1", "descripcionPaquete1", 5, 20f,
          null, listaCantidadTipoP1, fechaDate1);
    PaquetePublicacion paquete2 = new PaquetePublicacion(
          "nombrePaquete2", "descripcionPaquete2", 5, 20f,
          null, listaCantidadTipoP2, fechaDate2);
    manejadorPaquete.agregarPaquete(paquete1);
    manejadorPaquete.agregarPaquete(paquete2);
    List<String> listaResultado =
          (ArrayList<String>) manejadorPaquete
                .listarPaquetes();
    Collections.sort(listaResultado);
    List<String> listaEsperada = new ArrayList<String>();
    listaEsperada.add("nombrePaquete1");
    listaEsperada.add("nombrePaquete2");
    Assert.assertEquals(listaResultado, listaEsperada);

  }

  @Test
  public void eliminarPaqueteTest()
        throws PaquetePublicacionYaExisteException,
        PaquetePublicacionNoExisteException {
    manejadorPaquete = ManejadorPaquetes.getInstance();
    TipoPublicacion tipoPublicacion1 = new TipoPublicacion(
          "nombreTipo1", "descripcionTipo1",
          "exposicionTipo1", 2, 200f, fechaDate1);
    CantidadTotalTipoPublicacion cantidadTipoPublicacion1 =
          new CantidadTotalTipoPublicacion(
                4, tipoPublicacion1);
    List<CantidadTotalTipoPublicacion> listaCantidadTipoP1 =
          new ArrayList<CantidadTotalTipoPublicacion>();
    listaCantidadTipoP1.add(cantidadTipoPublicacion1);
    PaquetePublicacion paquete1 = new PaquetePublicacion(
          "nombrePaquete1", "descripcionPaquete1", 5, 20f,
          null, listaCantidadTipoP1, fechaDate1);
    manejadorPaquete.agregarPaquete(paquete1);
    manejadorPaquete.eliminarPaquete("nombrePaquete1");
    List<String> listaResultado =
          (ArrayList<String>) manejadorPaquete
                .listarPaquetes();
    Assert.assertTrue(listaResultado.isEmpty());
  }

  @Test
  public void agregarPaqueteRepetidoTest() {
    boolean exception = false;
    manejadorPaquete = ManejadorPaquetes.getInstance();
    TipoPublicacion tipoPublicacion1 = new TipoPublicacion(
          "nombreTipo1", "descripcionTipo1",
          "exposicionTipo1", 2, 200f, fechaDate1);
    TipoPublicacion tipoPublicacion2 = new TipoPublicacion(
          "nombreTipo2", "descripcionTipo2",
          "exposicionTipo2", 3, 20f, fechaDate2);
    CantidadTotalTipoPublicacion cantidadTipoPublicacion1 =
          new CantidadTotalTipoPublicacion(
                4, tipoPublicacion1);
    CantidadTotalTipoPublicacion cantidadTipoPublicacion2 =
          new CantidadTotalTipoPublicacion(
                3, tipoPublicacion2);
    List<CantidadTotalTipoPublicacion> listaCantidadTipoP1 =
          new ArrayList<CantidadTotalTipoPublicacion>();
    List<CantidadTotalTipoPublicacion> listaCantidadTipoP2 =
          new ArrayList<CantidadTotalTipoPublicacion>();
    listaCantidadTipoP1.add(cantidadTipoPublicacion1);
    listaCantidadTipoP2.add(cantidadTipoPublicacion2);
    PaquetePublicacion paquete1 = new PaquetePublicacion(
          "nombrePaquete1", "descripcionPaquete1", 5, 20f,
          null, listaCantidadTipoP1, fechaDate1);
    PaquetePublicacion paquete2 = new PaquetePublicacion(
          "nombrePaquete1", "descripcionPaquete2", 5, 20f,
          null, listaCantidadTipoP2, fechaDate2);
    try {
      manejadorPaquete.agregarPaquete(paquete1);
      manejadorPaquete.agregarPaquete(paquete2);
    } catch (PaquetePublicacionYaExisteException e) {
      exception = true;
    }
    Assert.assertTrue(exception);
  }

  @Test
  public void obtenerPaqueteInexistenteTest() {
    boolean exception = false;
    manejadorPaquete = ManejadorPaquetes.getInstance();
    TipoPublicacion tipoPublicacion1 = new TipoPublicacion(
          "nombreTipo1", "descripcionTipo1",
          "exposicionTipo1", 2, 200f, fechaDate1);
    CantidadTotalTipoPublicacion cantidadTipoPublicacion1 =
          new CantidadTotalTipoPublicacion(
                4, tipoPublicacion1);
    List<CantidadTotalTipoPublicacion> listaCantidadTipoP1 =
          new ArrayList<CantidadTotalTipoPublicacion>();
    listaCantidadTipoP1.add(cantidadTipoPublicacion1);
    PaquetePublicacion paquete1 = new PaquetePublicacion(
          "nombrePaquete1", "descripcionPaquete1", 5, 20f,
          null, listaCantidadTipoP1, fechaDate1);
    try {
      manejadorPaquete.agregarPaquete(paquete1);
      PaquetePublicacion resultado = manejadorPaquete
            .obtenerPaquete("Pepe");
    } catch (PaquetePublicacionYaExisteException e) {
      e.getMessage();
    } catch (PaquetePublicacionNoExisteException e) {
      exception = true;
    }
    Assert.assertTrue(exception);
  }

  @Test
  public void eliminarPaqueteInexistente() {
    boolean exception = false;
    manejadorPaquete = ManejadorPaquetes.getInstance();
    TipoPublicacion tipoPublicacion1 = new TipoPublicacion(
          "nombreTipo1", "descripcionTipo1",
          "exposicionTipo1", 2, 200f, fechaDate1);
    CantidadTotalTipoPublicacion cantidadTipoPublicacion1 =
          new CantidadTotalTipoPublicacion(
                4, tipoPublicacion1);
    List<CantidadTotalTipoPublicacion> listaCantidadTipoP1 =
          new ArrayList<CantidadTotalTipoPublicacion>();
    listaCantidadTipoP1.add(cantidadTipoPublicacion1);
    PaquetePublicacion paquete1 = new PaquetePublicacion(
          "nombrePaquete1", "descripcionPaquete1", 5, 20f,
          null, listaCantidadTipoP1, fechaDate1);
    try {
      manejadorPaquete.agregarPaquete(paquete1);
      manejadorPaquete.eliminarPaquete("Pepe");
    } catch (PaquetePublicacionYaExisteException e) {
      e.getMessage();
    } catch (PaquetePublicacionNoExisteException e) {
      exception = true;
    }
    Assert.assertTrue(exception);
  }
}
