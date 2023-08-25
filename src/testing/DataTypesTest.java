package testing;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import excepciones.DtOfertaNoExisteException;
import excepciones.OfertaLaboralYaExisteException;
import junit.framework.Assert;
import logica.DataTypes.DTPostulante;
import logica.classes.OfertaLaboral;
import logica.classes.Postulante;
import logica.classes.TipoPublicacion;
import logica.handlers.ManejadorOfertas;

public class DataTypesTest {
	
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
	public void obtenerDTPostulante()
	{
		Postulante postulante = new Postulante("postulante1", "Postulante 1", "Apellido", "postulante@example.com", this.fechaDate, "Nacionalidad");
		DTPostulante dtPosutlante = postulante.obtenerDTPostulante();
	}
	
	@Test
	public void obtenerDTOfertaLaboral() throws OfertaLaboralYaExisteException
	{
		ManejadorOfertas manejadorOfertas = ManejadorOfertas.getInstance();
		TipoPublicacion tipoPublicacion = new TipoPublicacion("tipoPublicacion", "Descripcion", "23", 3, 33.2f,this.fechaDate);
		OfertaLaboral ofertaLaboral = new OfertaLaboral("Nombre", "Descripcion", "09:00", "19:00", 23.4f, "Ciudad", "Departamento", this.fechaDate, tipoPublicacion);
		manejadorOfertas.agregarOferta(ofertaLaboral);
		
		//Falta la parte de crear el datatype pero no me dio
		
		try
		{
			manejadorOfertas.obtenerDTOfertaLaboral("OtroNombre");
		}catch(DtOfertaNoExisteException e)
		{
			Assert.assertEquals("No existe la oferta solicitada", e.getMessage());
		}
	}
}
