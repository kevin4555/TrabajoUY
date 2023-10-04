package testing;

import excepciones.PaquetePublicacionYaFueComprado;
import excepciones.TipoDePublicacionYaFueIngresado;
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
import logica.classes.PaquetePublicacion;
import logica.classes.TipoPublicacion;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClasesTest {
  
  private Date fechaDateParse;
  private Date fechaDateParseSecundaria;
  private LocalDate fechaDate;
  private LocalDate fechaDateSecundaria;
  private String fecha = "24/08/2023";
  private String fechaSecuandaria = "24/09/2023";
  
  @Before
  public void setUp() {
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
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
  
  @Test
  public void cantidadTipoPublicacionRestanteClase() {
    TipoPublicacion tipoPublicacion = new TipoPublicacion("tipoTesting", "Uso para testing",
        "baja", 50, 500f, fechaDate);
    CantidadTipoPublicacionRestante cantidadTipoPublicacionRestante = new CantidadTipoPublicacionRestante(
        5, tipoPublicacion);
    Assert.assertEquals(cantidadTipoPublicacionRestante.getCantidad(), 5);
    Assert.assertEquals(cantidadTipoPublicacionRestante.getTipoPublicacion(), tipoPublicacion);
    
  }
  
  @Test
  public void paquetePublicacionClase()
      throws PaquetePublicacionYaFueComprado, TipoDePublicacionYaFueIngresado {
    TipoPublicacion tipoPublicacionInicial = new TipoPublicacion("tipoTestingInicial",
        "Uso para testing", "baja", 50, 500f, fechaDate);
    CantidadTotalTipoPublicacion tipoTestingInicialTotal = new CantidadTotalTipoPublicacion(5,
        tipoPublicacionInicial);
    List<CantidadTotalTipoPublicacion> arrayPrueba = new ArrayList<CantidadTotalTipoPublicacion>();
    arrayPrueba.add(tipoTestingInicialTotal);
    List<CantidadTotalTipoPublicacion> arrayPrueba2 = new ArrayList<CantidadTotalTipoPublicacion>();
    PaquetePublicacion paquetePublicacion = new PaquetePublicacion("nombrePruebaPrimero",
        "descripcionPrueba", 20, 50f, null, arrayPrueba, fechaDate);
    
    paquetePublicacion.setNombre("nuevoNombre");
    paquetePublicacion.setDescripcion("nuevaDescripcion");
    paquetePublicacion.setImagen(null);
    paquetePublicacion.setCantidadTotalTipoPublicaciones(arrayPrueba2);
    paquetePublicacion.setPeriodoValidez(12);
    TipoPublicacion tipoPublicacion = new TipoPublicacion("tipoTesting", "Uso para testing",
        "baja", 50, 500f, fechaDate);
    paquetePublicacion.agregarTipoPublicacion(tipoPublicacion, 5);
    List<String> resultadoList = paquetePublicacion.obtenerNombresTipoPublicaciones();
    
    Assert.assertEquals(paquetePublicacion.getNombre(), "nuevoNombre");
    Assert.assertEquals(paquetePublicacion.getDescripcion(), "nuevaDescripcion");
    Assert.assertEquals(paquetePublicacion.getImagen(), null);
    Assert.assertEquals(paquetePublicacion.getPeriodoValidez(), 12);
    Assert.assertEquals(resultadoList.get(0), "tipoTesting");
  }
  
}