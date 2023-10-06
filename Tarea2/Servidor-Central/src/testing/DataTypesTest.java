package testing;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import logica.datatypes.Dtpostulacion;
import org.junit.Before;
import org.junit.Test;

/**
 * Clase DataTypesTest.
 */

public class DataTypesTest {
  
  private Date fechaDateParse;
  private Date fechaDateParseSecundaria;
  private LocalDate fechaDate;
  private LocalDate fechaDateSecundaria;
  private String fecha = "24/08/2023";
  private String fechaSecuandaria = "24/09/2023";
  
  /**
   * Metodo setUp.
   */
  
  @Before
  public void setUp() {
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    
    try {
      this.fechaDateParseSecundaria = dateFormat.parse(fechaSecuandaria);
      this.fechaDateParse = dateFormat.parse(fecha);
      fechaDate = fechaDateParse.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
      fechaDateSecundaria = fechaDateParseSecundaria.toInstant().atZone(ZoneId.systemDefault())
          .toLocalDate();
    } catch (Exception e) {
      e.getMessage();
    }
    
  }
  
  
  
}
