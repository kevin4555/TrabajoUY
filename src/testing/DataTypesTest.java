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
import logica.DataTypes.DTOfertaLaboral;
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
		Assert.assertEquals(postulante.getNickname(), dtPosutlante.getNickname());
		Assert.assertEquals(postulante.getNombre(), dtPosutlante.getNombre());
		Assert.assertEquals(postulante.getApellido(), dtPosutlante.getApellido());
		Assert.assertEquals(postulante.getEmail(), dtPosutlante.getEmail());
		Assert.assertEquals(postulante.getNacionalidad(), dtPosutlante.getNacionalidad());
		Assert.assertEquals(postulante.getFechaNacimiento(), dtPosutlante.getFechaNacimiento());
	}
	
	@Test
	public void obtenerDTOfertaLaboral() throws OfertaLaboralYaExisteException
	{
		TipoPublicacion tipoPublicacion = new TipoPublicacion("tipoPublicacion", "Descripcion", "23", 3, 33.2f,this.fechaDate);
		OfertaLaboral ofertaLaboral = new OfertaLaboral("Nombre", "Descripcion", "09:00", "19:00", 23.4f, "Ciudad", "Departamento", this.fechaDate, tipoPublicacion);
		DTOfertaLaboral dtOfertaLaboral = ofertaLaboral.obtenerDTOfertaLaboral();
		Assert.assertEquals(ofertaLaboral.getNombre(), dtOfertaLaboral.getNombre());
		Assert.assertEquals(ofertaLaboral.getRemunaracion(), dtOfertaLaboral.getRemuneracion());
		Assert.assertEquals(ofertaLaboral.getCiudad(), dtOfertaLaboral.getCiudad());
		Assert.assertEquals(ofertaLaboral.getDepartamento(), dtOfertaLaboral.getDepartamento());
		Assert.assertEquals(ofertaLaboral.getDescripcion(), dtOfertaLaboral.getDescripcion());
		Assert.assertEquals(ofertaLaboral.getHorarioInicial(), dtOfertaLaboral.getHorarioInicio());
		Assert.assertEquals(ofertaLaboral.getHorarioFinal(), dtOfertaLaboral.getHorarioFinal());
		Assert.assertEquals(ofertaLaboral.getFechaAlta(), dtOfertaLaboral.getFechaAlta());
		Assert.assertEquals(ofertaLaboral.getPostulacion().size(), dtOfertaLaboral.getPostulaciones().size());
	}
	
	@Test
	public void obtenerDTEmpresa() {
		
	}
}
