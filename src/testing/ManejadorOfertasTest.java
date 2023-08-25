package testing;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import excepciones.OfertaLaboralNoExisteException;
import excepciones.OfertaLaboralYaExisteException;
import junit.framework.Assert;
import logica.classes.OfertaLaboral;
import logica.classes.TipoPublicacion;
import logica.handlers.ManejadorOfertas;

public class ManejadorOfertasTest {
	
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
	public void agregarObtenerListarOfertasTipoPublicacion() throws OfertaLaboralYaExisteException, OfertaLaboralNoExisteException
	{
		ManejadorOfertas manejadorOfertas = ManejadorOfertas.getInstance();
		TipoPublicacion tipoPublicacion = new TipoPublicacion("tipoPublicacion", "Descripcion", "23", 3, 33.2f,this.fechaDate);
		OfertaLaboral ofertaLaboral = new OfertaLaboral("Nombre", "Descripcion", "09:00", "19:00", 23.4f, "Ciudad", "Departamento", this.fechaDate, tipoPublicacion);
		manejadorOfertas.agregarOferta(ofertaLaboral);
		Assert.assertEquals(ofertaLaboral, manejadorOfertas.obtenerOfertaLaboral(ofertaLaboral.getNombre()));
		Assert.assertEquals(1, manejadorOfertas.listarOfertasLaborales().size());
		
		try {
			manejadorOfertas.agregarOferta(ofertaLaboral);

		}catch(OfertaLaboralYaExisteException e)
		{
			Assert.assertSame("La oferta laboral que desea ingresar ya existe", e.getMessage());
		}
		try {
			manejadorOfertas.obtenerOfertaLaboral("OtroNombre");
		}
		catch(OfertaLaboralNoExisteException e)
		{
			Assert.assertSame("No existe la oferta solicitada", e.getMessage());
		}
		
	}
	
}
