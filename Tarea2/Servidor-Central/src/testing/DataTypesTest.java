package testing;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import excepciones.DtOfertaNoExisteException;
import excepciones.OfertaLaboralYaExisteException;
import junit.framework.Assert;
import logica.DataTypes.DTCantidadTipoPublicacion;
import logica.DataTypes.DTEmpresa;
import logica.DataTypes.DTOfertaLaboral;
import logica.DataTypes.DTPaquetePublicacion;
import logica.DataTypes.DTPostulacion;
import logica.DataTypes.DTPostulante;
import logica.DataTypes.DTTipoPublicacion;
import logica.classes.CantidadTipoPublicacionRestante;
import logica.classes.Empresa;
import logica.classes.OfertaLaboral;
import logica.classes.PaquetePublicacion;
import logica.classes.Postulacion;
import logica.classes.Postulante;
import logica.classes.TipoPublicacion;
import logica.handlers.ManejadorOfertas;

public class DataTypesTest {
	
	/*private Date fechaDate;
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
	public void obtenerDTEmpresa() throws OfertaLaboralYaExisteException {
		TipoPublicacion tipoPublicacion = new TipoPublicacion("tipoPublicacion", "Descripcion", "23", 3, 33.2f,this.fechaDate);
		Empresa empresa = new Empresa("nicknameEmpresa1", "nombre1", "apellido1", "email1@test.com", "descripcion1", "sitioWeb1" );
		OfertaLaboral ofertaLaboral = new OfertaLaboral("Nombre", "Descripcion", "09:00", "19:00", 23.4f, "Ciudad", "Departamento", this.fechaDate, tipoPublicacion);
		empresa.agregarOferta(ofertaLaboral);
		DTEmpresa dtEmpresa = empresa.obtenerDTEmpresa();
		Assert.assertEquals(empresa.getNickname(), dtEmpresa.getNickname());
		Assert.assertEquals(empresa.getNombre(), dtEmpresa.getNombre());
		Assert.assertEquals(empresa.getApellido(), dtEmpresa.getApellido());
		Assert.assertEquals(empresa.getEmail(), dtEmpresa.getEmail());
		Assert.assertEquals(empresa.getDescripcion(), dtEmpresa.getDescripcion());
		Assert.assertEquals(empresa.getSitioWeb(), dtEmpresa.getSitioWeb());	
		Assert.assertEquals(empresa.getOfertasLaborales().size(), dtEmpresa.getOfertasLaborales().size());
	}
	
	@Test
	public void obtenerDTCantidadTipoPublicacion() {
		TipoPublicacion tipoPublicacion = new TipoPublicacion("tipoPublicacion", "Descripcion", "23", 3, 33.2f,this.fechaDate);
		CantidadTipoPublicacionRestante cantidadTipoPublicacion = new CantidadTipoPublicacionRestante(1, tipoPublicacion);
		DTCantidadTipoPublicacion dtCantidadTipoPublicacion = cantidadTipoPublicacion.obtenerDTCantidadTipoPublicacion();
		Assert.assertEquals(cantidadTipoPublicacion.getTipoPublicacion().getNombre(), dtCantidadTipoPublicacion.getNombreTipoPublicacion());
		Assert.assertEquals(cantidadTipoPublicacion.getCantidadRestante(), dtCantidadTipoPublicacion.getCantidad());
	}
	
	@Test
	public void obtenerDTPostulacion() {
		TipoPublicacion tipoPublicacion = new TipoPublicacion("tipoPublicacion", "Descripcion", "23", 3, 33.2f,this.fechaDate);
		OfertaLaboral ofertaLaboral = new OfertaLaboral("Nombre", "Descripcion", "09:00", "19:00", 23.4f, "Ciudad", "Departamento", this.fechaDate, tipoPublicacion);
		Postulante postulante = new Postulante("postulante1", "Postulante 1", "Apellido", "postulante@example.com", this.fechaDate, "Nacionalidad");
		Postulacion postulacion = new Postulacion("motivacion", this.fechaDate, "cvReducido", postulante, ofertaLaboral);
		DTPostulacion dtPostulacion = postulacion.obtenerDTPostulacion();
		Assert.assertEquals(postulacion.getPostulante().getNickname(), dtPostulacion.getUsuario().getNickname());
		Assert.assertEquals(postulacion.getDescrpMotivacion(), dtPostulacion.getDescripMotivacion());
		Assert.assertEquals(postulacion.getCvReducido(), dtPostulacion.getCvReducido());
		Assert.assertEquals(postulacion.getFechaPostulacion(), dtPostulacion.getFechaPostulacion());
	}
	
	@Test
	public void obtenerDTTipoPublicacion() {
		TipoPublicacion tipoPublicacion = new TipoPublicacion("tipoPublicacion", "Descripcion", "23", 3, 33.2f,this.fechaDate);
		DTTipoPublicacion dtTipo = tipoPublicacion.obtenerDTTipoPublicacion();
		Assert.assertEquals(tipoPublicacion.getCosto(), dtTipo.getCosto());
		Assert.assertEquals(tipoPublicacion.getDuracionDia(), dtTipo.getDuracionDia());
		Assert.assertEquals(tipoPublicacion.getDescripcion(), dtTipo.getDescripcion());
		Assert.assertEquals(tipoPublicacion.getExposicion(), dtTipo.getExposicion());
		Assert.assertEquals(tipoPublicacion.getNombre(), dtTipo.getNombre());
		Assert.assertEquals(tipoPublicacion.getFechaAlta(), dtTipo.getFechaAlta());
	}
	
	@Test
	public void obtenerDTPaquetetipoPublicacion() {
		ArrayList<CantidadTipoPublicacionRestante> cantidadTipo = new ArrayList<CantidadTipoPublicacionRestante>();
		TipoPublicacion tipoPublicacion = new TipoPublicacion("tipoPublicacion", "Descripcion", "23", 3, 33.2f,this.fechaDate);
		CantidadTipoPublicacionRestante cantidadTipoPublicacion = new CantidadTipoPublicacionRestante(1, tipoPublicacion);
		cantidadTipo.add(cantidadTipoPublicacion);
		PaquetePublicacion paquete = new PaquetePublicacion("nombre", "descripcion", 2, 20f, cantidadTipo);
		DTPaquetePublicacion dtPaquete = paquete.obtenerDTPaquete();
		Assert.assertEquals(paquete.getCosto(), dtPaquete.getCosto());
		Assert.assertEquals(paquete.getDescripcion(), dtPaquete.getDescripcion());
		Assert.assertEquals(paquete.getNombre(), dtPaquete.getNombre());
		Assert.assertEquals(paquete.getPeriodoValidez(), dtPaquete.getPeriodoValidez());
		Assert.assertEquals(paquete.getCantidadPublicaciones(), dtPaquete.getCantidadPublicaciones());
		
	}*/
	
}
