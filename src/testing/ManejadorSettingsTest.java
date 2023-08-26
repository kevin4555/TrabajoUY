package testing;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import excepciones.KeywordNoExisteException;
import excepciones.KeywordYaExisteException;
import excepciones.TipoPublicacionNoExisteException;
import excepciones.TipoPublicacionYaExisteException;
import junit.framework.Assert;
import logica.classes.Keyword;
import logica.classes.TipoPublicacion;
import logica.handlers.ManejadorSettings;

public class ManejadorSettingsTest 
{
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
	public void pruebasGeneralesSettings() throws TipoPublicacionYaExisteException, KeywordYaExisteException, KeywordNoExisteException, TipoPublicacionNoExisteException
	{
		ManejadorSettings manejadorSettings = ManejadorSettings.getInstance();
		
		TipoPublicacion tipoPublicacion = new TipoPublicacion("tipoPublicacion", "Descripcion", "23", 3, 33.2f,this.fechaDate);
		Keyword keyword = new Keyword("It");

		manejadorSettings.addTipoPublicacion(tipoPublicacion);
		manejadorSettings.addKeyword(keyword);
		
		Assert.assertEquals(1, (manejadorSettings.listarTipoDePublicaciones()).size());
		Assert.assertEquals(tipoPublicacion, manejadorSettings.obtenerTipoPublicacion(tipoPublicacion.getNombre()));
		
		Assert.assertEquals(keyword, manejadorSettings.obtenerKeyword(keyword.getNombre()));
		Assert.assertEquals(1, manejadorSettings.listarKeywords().size());
		
		try {
			manejadorSettings.addKeyword(keyword);
		}catch(KeywordYaExisteException e)
		{
			Assert.assertEquals("La keyword It ya existe", e.getMessage());
		}
		
		try {
			manejadorSettings.obtenerKeyword("Ol");
		}catch(KeywordNoExisteException e)
		{
			Assert.assertEquals("La keyword Ol no existe", e.getMessage());
		}
		
		try {
			manejadorSettings.addTipoPublicacion(tipoPublicacion);
		}catch(TipoPublicacionYaExisteException e)
		{
			Assert.assertEquals("El tipo publicacion tipoPublicacion ya existe", e.getMessage());
		}
		
		try {
			manejadorSettings.obtenerTipoPublicacion("UnaNoEsta");
		}catch(TipoPublicacionNoExisteException e)
		{
			Assert.assertEquals("El tipo de publicación UnaNoEsta no existe", e.getMessage());
		}
		manejadorSettings.clean();
	}
	
}
