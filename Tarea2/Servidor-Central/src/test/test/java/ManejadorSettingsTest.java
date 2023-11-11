package test.java;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;
import main.java.classes.Keyword;
import main.java.classes.TipoPublicacion;
import main.java.excepciones.KeywordNoExisteException;
import main.java.excepciones.KeywordYaExisteException;
import main.java.excepciones.TipoPublicacionNoExisteException;
import main.java.excepciones.TipoPublicacionYaExisteException;
import main.java.handlers.ManejadorSettings;

/**
 * Clase ManejadorSettingsTest.
 */

public class ManejadorSettingsTest {

  private LocalDate fechaDate;
  private String fecha = "1988-11-10";

  /**
   * Metodo setUp.
   */

  @Before
  public void setUp() {
    ManejadorSettings manejadorSettings = ManejadorSettings
          .getInstance();
    manejadorSettings.clean();
    fechaDate = LocalDate.parse(fecha);
  }

  @Test
  public void pruebasGeneralesSettings()
        throws TipoPublicacionYaExisteException,
        KeywordYaExisteException, KeywordNoExisteException,
        TipoPublicacionNoExisteException {
    ManejadorSettings manejadorSettings = ManejadorSettings
          .getInstance();

    TipoPublicacion tipoPublicacion = new TipoPublicacion(
          "tipoPublicacion", "Descripcion", "23", 3, 33.2f,
          this.fechaDate);
    Keyword keyword = new Keyword("It");

    manejadorSettings.addTipoPublicacion(tipoPublicacion);
    manejadorSettings.addKeyword(keyword);

    Assert.assertEquals(1,
          (manejadorSettings.listarTipoDePublicaciones())
                .size());
    Assert.assertEquals(tipoPublicacion,
          manejadorSettings.obtenerTipoPublicacion(
                tipoPublicacion.getNombre()));

    Assert.assertEquals(keyword, manejadorSettings
          .obtenerKeyword(keyword.getNombre()));
    Assert.assertEquals(1,
          manejadorSettings.listarKeywords().size());

    try {
      manejadorSettings.addKeyword(keyword);
    } catch (KeywordYaExisteException e) {
      Assert.assertEquals("La keyword It ya existe",
            e.getMessage());
    }

    try {
      manejadorSettings.obtenerKeyword("Ol");
    } catch (KeywordNoExisteException e) {
      Assert.assertEquals("La keyword Ol no existe",
            e.getMessage());
    }

    try {
      manejadorSettings.addTipoPublicacion(tipoPublicacion);
    } catch (TipoPublicacionYaExisteException e) {
      Assert.assertEquals(
            "El tipo publicacion tipoPublicacion ya existe",
            e.getMessage());
    }

    try {
      manejadorSettings.obtenerTipoPublicacion("UnaNoEsta");
    } catch (TipoPublicacionNoExisteException e) {
      Assert.assertEquals(
            "El tipo de publicación UnaNoEsta no existe",
            e.getMessage());
    }
    manejadorSettings.clean();
  }

}
