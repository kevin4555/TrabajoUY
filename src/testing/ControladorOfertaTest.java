package testing;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class ControladorOfertaTest {
	
	private Date fechaDate;
	private String fecha = "17/06/1999";
	
	@Before
	public void setUp()
	{
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try
		{
			this.fechaDate = dateFormat.parse(fecha);
		}
		catch (ParseException e)
		{
			e.getMessage();
		}
	}
	
	@Test
	public void listarPostulantes()
	{
		
	}
}
