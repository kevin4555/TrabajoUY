package testing;

import static org.junit.Assert.assertEquals;

import java.security.PublicKey;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import excepciones.DtOfertaNoExisteException;
import excepciones.OfertaLaboralNoExisteException;
import excepciones.OfertaLaboralYaExisteException;
import excepciones.TipoPublicacionNoExisteException;
import excepciones.TipoPublicacionYaExisteException;
import junit.framework.Assert;
import logica.DataTypes.DTOfertaLaboral;
import logica.DataTypes.DTPostulacion;
import logica.classes.OfertaLaboral;
import logica.classes.TipoPublicacion;
import logica.handlers.ManejadorOfertas;

public class ManejadorOfertasTest {

	private Date fechaDate;
	private String fecha = "17/06/1999";

	private ManejadorOfertas manejadorOfertas;

	@Before
	public void setUp() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			this.fechaDate = dateFormat.parse(fecha);
		} catch (ParseException e) {
			e.getMessage();
		}
	}

	@Test
	public void testAgregarYObtenerOferta() throws OfertaLaboralYaExisteException, OfertaLaboralNoExisteException,
			TipoPublicacionYaExisteException, TipoPublicacionNoExisteException {
		manejadorOfertas = ManejadorOfertas.getInstance();
		TipoPublicacion tipoCreadoPublicacion = new TipoPublicacion("tipoTesting", "Uso para testing", "baja", 50, 500f,
				fechaDate);
		OfertaLaboral oferta = new OfertaLaboral("nombre", "Descipcion para testing", "09:00", "15:00", 10F,
				"Montevideo", "Montevideo", fechaDate, tipoCreadoPublicacion);
		manejadorOfertas.agregarOferta(oferta);
		OfertaLaboral obtenida = manejadorOfertas.obtenerOfertaLaboral("nombre");
		assertEquals(oferta, obtenida);
		manejadorOfertas.clean();

	}

	@Test()
	public void testAgregarOfertaDuplicada()
			throws OfertaLaboralYaExisteException, TipoPublicacionNoExisteException, TipoPublicacionYaExisteException {
		manejadorOfertas = ManejadorOfertas.getInstance();
		TipoPublicacion tipoCreadoPublicacion = new TipoPublicacion("tipoTesting", "Uso para testing", "baja", 50, 500f,
				fechaDate);
		OfertaLaboral oferta = new OfertaLaboral("nombreTesting", "Descipcion para testing", "09:00", "15:00", 10F,
				"Montevideo", "Montevideo", fechaDate, tipoCreadoPublicacion);
		manejadorOfertas.agregarOferta(oferta);
		try {
			manejadorOfertas.agregarOferta(oferta);
		} catch (OfertaLaboralYaExisteException e) {
			Assert.assertEquals("La oferta laboral que desea ingresar ya existe", e.getMessage());
		}
		manejadorOfertas.clean();
	}

	@Test
	public void testObtenerOfertaNoExistente() throws OfertaLaboralNoExisteException {
		manejadorOfertas = ManejadorOfertas.getInstance();
		try {
			manejadorOfertas.obtenerOfertaLaboral("OfertaNoExistente");
		} catch (OfertaLaboralNoExisteException e) {
			Assert.assertEquals("No existe la oferta solicitada", e.getMessage());
		}
		manejadorOfertas.clean();
	}

	@Test
	public void testObtenerDTOfertaInexistente() throws DtOfertaNoExisteException {
		manejadorOfertas = ManejadorOfertas.getInstance();

		try {
			manejadorOfertas.obtenerDTOfertaLaboral("OfertaInexistente");
		} catch (DtOfertaNoExisteException e) {
			Assert.assertEquals("No existe la oferta solicitada", e.getMessage());
		}
		manejadorOfertas.clean();
	}

	@Test
	public void testObtenerDTYCompararlo() throws OfertaLaboralYaExisteException, DtOfertaNoExisteException {
		manejadorOfertas = ManejadorOfertas.getInstance();
		ArrayList<DTPostulacion> arrayDT = new ArrayList<DTPostulacion>();
		DTOfertaLaboral dtOfertaLaboral = new DTOfertaLaboral("nombreTesting", "descripcionTesting", "ciudadTesting",
				"departamentoTesting", "09:00", "15:00", 100f, fechaDate, arrayDT);
		TipoPublicacion tipoCreadoPublicacion = new TipoPublicacion("tipoTesting", "Uso para testing", "baja", 50, 500f,
				fechaDate);
		OfertaLaboral ofertaLaboral = new OfertaLaboral("nombreTesting", "descripcionTesting", "09:00", "15:00", 100f,
				"ciudadTesting", "departamentoTesting", fechaDate, tipoCreadoPublicacion);
		manejadorOfertas.agregarOferta(ofertaLaboral);
		DTOfertaLaboral resultaDtOfertaLaboral = manejadorOfertas.obtenerDTOfertaLaboral("nombreTesting");
		Assert.assertEquals(dtOfertaLaboral.getNombre(), resultaDtOfertaLaboral.getNombre());
		Assert.assertEquals(dtOfertaLaboral.getCiudad(), resultaDtOfertaLaboral.getCiudad());
		Assert.assertEquals(dtOfertaLaboral.getDepartamento(), resultaDtOfertaLaboral.getDepartamento());
		Assert.assertEquals(dtOfertaLaboral.getHorarioInicio(), resultaDtOfertaLaboral.getHorarioInicio());
		Assert.assertEquals(dtOfertaLaboral.getHorarioFinal(), resultaDtOfertaLaboral.getHorarioFinal());
		Assert.assertEquals(dtOfertaLaboral.getRemuneracion(), resultaDtOfertaLaboral.getRemuneracion());
		Assert.assertEquals(dtOfertaLaboral.getFechaAlta(), resultaDtOfertaLaboral.getFechaAlta());
		Assert.assertEquals(dtOfertaLaboral.getPostulaciones(), resultaDtOfertaLaboral.getPostulaciones());
		Assert.assertEquals(dtOfertaLaboral.getDescripcion(), resultaDtOfertaLaboral.getDescripcion());
		manejadorOfertas.clean();
	}

	@Test
	public void testObtenerListaOfertasLaboralesYComprar() throws OfertaLaboralYaExisteException {
		manejadorOfertas = ManejadorOfertas.getInstance();
		TipoPublicacion tipoPublicacion = new TipoPublicacion("tipoTesting", "Uso para testing", "baja", 50, 500f,
				fechaDate);
		OfertaLaboral ofertaLaboral = new OfertaLaboral("nombreTesting", "descripcionTesting", "09:00", "15:00", 100f,
				"ciudadTesting", "departamentoTesting", fechaDate, tipoPublicacion);
		TipoPublicacion tipoCreadoPublicacion = new TipoPublicacion("tipoTesting", "Uso para testing", "baja", 50, 500f,
				fechaDate);
		OfertaLaboral oferta = new OfertaLaboral("nombre", "Descipcion para testing", "09:00", "15:00", 10F,
				"Montevideo", "Montevideo", fechaDate, tipoCreadoPublicacion);
		manejadorOfertas.agregarOferta(ofertaLaboral);
		manejadorOfertas.agregarOferta(oferta);
		ArrayList<String> resultado = manejadorOfertas.listarOfertasLaborales();
		Collections.sort(resultado);
		ArrayList<String> resultadoEsperado = new ArrayList<String>();
		resultadoEsperado.add("nombreTesting");
		resultadoEsperado.add("nombre");
		Collections.sort(resultadoEsperado);
		Assert.assertEquals(resultadoEsperado, resultado);
		manejadorOfertas.clean();
	}

}
