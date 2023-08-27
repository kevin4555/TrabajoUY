package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.isArray;

import java.io.InterruptedIOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import excepciones.KeywordNoExisteException;
import excepciones.KeywordYaExisteException;
import excepciones.OfertaLaboralNoExisteException;
import excepciones.OfertaLaboralYaExisteException;
import excepciones.PaquetePublicacionNoExisteException;
import excepciones.PaquetePublicacionYaExisteException;
import excepciones.TipoPublicacionNoExisteException;
import excepciones.TipoPublicacionYaExisteException;
import excepciones.UsuarioEmailRepetidoException;
import excepciones.UsuarioEmailRepetidoException;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioYaExisteException;
import junit.framework.Assert;
import logica.DataTypes.DTCantidadTipoPublicacion;
import logica.DataTypes.DTOfertaLaboral;
import logica.classes.CantidadTipoPublicacion;
import logica.classes.Empresa;
import logica.classes.Keyword;
import logica.classes.OfertaLaboral;
import logica.classes.PaquetePublicacion;
import logica.classes.Postulante;
import logica.classes.TipoPublicacion;
import logica.controllers.ControladorOferta;
import logica.controllers.ControladorUsuario;
import logica.handlers.ManejadorOfertas;
import logica.handlers.ManejadorPaquetes;
import logica.handlers.ManejadorSettings;
import logica.handlers.ManejadorUsuario;

public class ControladorOfertaTest {
	private Date fechaDate;
	private String fecha = "17/06/1999";
	private ControladorOferta controladorOferta;
	private ManejadorSettings manejadorSettings;
	private ManejadorOfertas manejadorOfertas;
	private ManejadorPaquetes manejadorPaquetes;
	private ControladorUsuario controladorUsuario;
	private ManejadorUsuario manejadorUsuario;

	@Before
	public void setUp() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		manejadorOfertas = ManejadorOfertas.getInstance();
		manejadorPaquetes = ManejadorPaquetes.getInstance();
		manejadorSettings = ManejadorSettings.getInstance();
		manejadorUsuario = ManejadorUsuario.getInstance();

		manejadorUsuario.clean();
		manejadorOfertas.clean();
		manejadorSettings.clean();
		manejadorPaquetes.clean();

		try {
			this.fechaDate = dateFormat.parse(fecha);
		} catch (ParseException e) {
			e.getMessage();
		}
	}

	@Test
	public void testAltaTipoPublicacionValidacion()
			throws TipoPublicacionYaExisteException, TipoPublicacionNoExisteException {
		manejadorOfertas = ManejadorOfertas.getInstance();
		manejadorSettings = ManejadorSettings.getInstance();
		controladorOferta = new ControladorOferta();
		controladorOferta.altaTipoPublicacion("tipoTesting", "Uso para testing", "baja", 50, 500f, fechaDate);
		TipoPublicacion resultado = controladorOferta.obtenerTipoPublicacion("tipoTesting");
		Assert.assertEquals("tipoTesting", resultado.getNombre());
		Assert.assertEquals("Uso para testing", resultado.getDescripcion());
		Assert.assertEquals("baja", resultado.getExposicion());
		Assert.assertEquals(50, resultado.getDuracionDia());
		Assert.assertEquals(500f, resultado.getCosto());
		Assert.assertEquals(fechaDate, resultado.getFechaAlta());
	}

	@Test
	public void testAltaKeywordyValidacion() throws KeywordYaExisteException, TipoPublicacionYaExisteException,
			KeywordNoExisteException, TipoPublicacionNoExisteException {
		manejadorOfertas = ManejadorOfertas.getInstance();
		manejadorSettings = ManejadorSettings.getInstance();
		controladorOferta = new ControladorOferta();
		controladorOferta.altaKeyword("KeywordTest");
		Keyword resultadoKeyword = controladorOferta.obtenerKeywords("KeywordTest");
	}

	@Test
	public void testListarKeywordyValidacion() throws KeywordYaExisteException, TipoPublicacionYaExisteException {
		manejadorOfertas = ManejadorOfertas.getInstance();
		manejadorSettings = ManejadorSettings.getInstance();
		controladorOferta = new ControladorOferta();
		controladorOferta.altaKeyword("KeywordTestPrimero");
		controladorOferta.altaKeyword("KeywordTestSegundo");

		ArrayList<String> resultado = controladorOferta.listarKeywords();
		Collections.sort(resultado);
		ArrayList<String> resultadoEsperado = new ArrayList<String>();
		resultadoEsperado.add("KeywordTestPrimero");
		resultadoEsperado.add("KeywordTestSegundo");
		Collections.sort(resultadoEsperado);

		Assert.assertEquals(resultadoEsperado, resultado);
	}

	@Test
	public void testAltaOFertaLaboralyValidacion()
			throws OfertaLaboralYaExisteException, OfertaLaboralNoExisteException, TipoPublicacionNoExisteException,
			KeywordNoExisteException, UsuarioNoExisteException, UsuarioYaExisteException, UsuarioEmailRepetidoException,
			TipoPublicacionYaExisteException, KeywordYaExisteException {
		manejadorOfertas = ManejadorOfertas.getInstance();
		manejadorSettings = ManejadorSettings.getInstance();
		controladorUsuario = new ControladorUsuario();
		controladorOferta = new ControladorOferta();
		controladorOferta.altaTipoPublicacion("tipoTesting", "Uso para testing", "baja", 50, 500f, fechaDate);
		controladorUsuario.altaEmpresa("nicknameEmpresa1", "nombre1", "apellido1", "email1@test.com", "descripcion1",
				"sitioWeb1");

		ArrayList<String> listaKeyword = new ArrayList<String>();
		listaKeyword.add("Keyword1");
		listaKeyword.add("Keyword2");
		Collections.sort(listaKeyword);
		controladorOferta.altaKeyword("Keyword1");
		controladorOferta.altaKeyword("Keyword2");

		controladorOferta.altaOfertaLaboral("test", "descipcionTest", "09:00", "15:00", 500f, "Montevideo",
				"Montevideo", fechaDate, "tipoTesting", "nicknameEmpresa1", listaKeyword);

		OfertaLaboral resultado = controladorOferta.obtenerOfertaLaboral("test");

		ArrayList<Keyword> listaKeywords = resultado.getKw();
		ArrayList<String> listaCasteada = new ArrayList<String>();
		for (Keyword unidad : listaKeywords) {
			listaCasteada.add(unidad.getNombre());
		}
		Collections.sort(listaCasteada);

		TipoPublicacion resultadoOfertaLaboral = manejadorSettings.obtenerTipoPublicacion("tipoTesting");

		Assert.assertEquals("test", resultado.getNombre());
		Assert.assertEquals("descipcionTest", resultado.getDescripcion());
		Assert.assertEquals("09:00", resultado.getHorarioInicial());
		Assert.assertEquals("15:00", resultado.getHorarioFinal());
		Assert.assertEquals("Montevideo", resultado.getCiudad());
		Assert.assertEquals("Montevideo", resultado.getDepartamento());
		Assert.assertEquals(fechaDate, resultado.getFechaAlta());
		Assert.assertEquals(resultadoOfertaLaboral, resultado.getTipoPublicacion());
		Assert.assertEquals(listaCasteada, listaKeyword);

	}

	@Test
	public void testObtenerDtOfertaLaboralyValidacion()
			throws OfertaLaboralYaExisteException, OfertaLaboralNoExisteException, UsuarioYaExisteException,
			UsuarioEmailRepetidoException, KeywordYaExisteException, TipoPublicacionYaExisteException,
			TipoPublicacionNoExisteException, KeywordNoExisteException, UsuarioNoExisteException {
		manejadorOfertas = ManejadorOfertas.getInstance();
		manejadorSettings = ManejadorSettings.getInstance();
		controladorUsuario = new ControladorUsuario();
		controladorOferta = new ControladorOferta();

		controladorOferta.altaTipoPublicacion("tipoTesting", "Uso para testing", "baja", 50, 500f, fechaDate);
		controladorUsuario.altaEmpresa("nicknameEmpresa1", "nombre1", "apellido1", "email1@test.com", "descripcion1",
				"sitioWeb1");

		ArrayList<String> listaKeyword = new ArrayList<String>();
		listaKeyword.add("Keyword1");
		listaKeyword.add("Keyword2");
		Collections.sort(listaKeyword);
		controladorOferta.altaKeyword("Keyword1");
		controladorOferta.altaKeyword("Keyword2");

		controladorOferta.altaOfertaLaboral("test", "descipcionTest", "09:00", "15:00", 500f, "Montevideo",
				"Montevideo", fechaDate, "tipoTesting", "nicknameEmpresa1", listaKeyword);

		DTOfertaLaboral resultado = controladorOferta.obtenerDtOfertaLaboral("test");

		Assert.assertEquals("test", resultado.getNombre());
		Assert.assertEquals("descipcionTest", resultado.getDescripcion());
		Assert.assertEquals("09:00", resultado.getHorarioInicio());
		Assert.assertEquals("15:00", resultado.getHorarioFinal());
		Assert.assertEquals(500f, resultado.getRemuneracion());
		Assert.assertEquals("Montevideo", resultado.getCiudad());
		Assert.assertEquals("Montevideo", resultado.getDepartamento());
		Assert.assertEquals(fechaDate, resultado.getFechaAlta());
	}

	@Test
	public void testListadoTipoPublicacion() throws TipoPublicacionYaExisteException {
		manejadorOfertas = ManejadorOfertas.getInstance();
		manejadorSettings = ManejadorSettings.getInstance();
		controladorOferta = new ControladorOferta();
		controladorOferta.altaTipoPublicacion("tipoTestingPrimero", "Uso para testing", "baja", 50, 500f, fechaDate);
		controladorOferta.altaTipoPublicacion("tipoTestingSegundo", "Uso para testing", "baja", 50, 500f, fechaDate);
		ArrayList<String> resultadoListado = controladorOferta.listarTipoDePublicaciones();
		Collections.sort(resultadoListado);
		ArrayList<String> resultadoEsperado = new ArrayList<String>();
		resultadoEsperado.add("tipoTestingPrimero");
		resultadoEsperado.add("tipoTestingSegundo");
		Collections.sort(resultadoEsperado);
		Assert.assertEquals(resultadoEsperado, resultadoListado);

	}

	@Test
	public void testListarPaquetes() throws PaquetePublicacionYaExisteException {
		manejadorOfertas = ManejadorOfertas.getInstance();
		manejadorSettings = ManejadorSettings.getInstance();
		manejadorPaquetes = ManejadorPaquetes.getInstance();
		controladorOferta = new ControladorOferta();
		ArrayList<CantidadTipoPublicacion> arrayPrueba = new ArrayList<CantidadTipoPublicacion>();
		ArrayList<CantidadTipoPublicacion> arrayPruebaSegundo = new ArrayList<CantidadTipoPublicacion>();
		PaquetePublicacion paquetePruebaPrimero = new PaquetePublicacion("nombrePruebaPrimero", "descripcionPrueba", 20,
				50f, arrayPruebaSegundo);
		PaquetePublicacion paquetePrueba = new PaquetePublicacion("nombrePrueba", "descripcionPrueba", 20, 50f,
				arrayPrueba);
		manejadorPaquetes.agregarPaquete(paquetePrueba);
		manejadorPaquetes.agregarPaquete(paquetePruebaPrimero);
		ArrayList<String> resultadoPaquete = controladorOferta.listarPaquetes();
		Collections.sort(resultadoPaquete);
		ArrayList<String> resultadoEsperado = new ArrayList<String>();
		resultadoEsperado.add("nombrePruebaPrimero");
		resultadoEsperado.add("nombrePrueba");
		Collections.sort(resultadoEsperado);
		Assert.assertEquals(resultadoEsperado, resultadoPaquete);

	}

	@Test
	public void testRegistrarPaquetes() throws PaquetePublicacionYaExisteException, TipoPublicacionYaExisteException,
			TipoPublicacionNoExisteException {
		manejadorOfertas = ManejadorOfertas.getInstance();
		manejadorSettings = ManejadorSettings.getInstance();
		manejadorPaquetes = ManejadorPaquetes.getInstance();
		controladorOferta = new ControladorOferta();
		TipoPublicacion publicacionPrueba = new TipoPublicacion("tipoPublicacionPrueba", "descripcionPrueba", "alta",
				20, 50f, fechaDate);
		manejadorSettings.addTipoPublicacion(publicacionPrueba);
		DTCantidadTipoPublicacion nuevoDt = new DTCantidadTipoPublicacion("tipoPublicacionPrueba", 5);
		ArrayList<DTCantidadTipoPublicacion> arrayDtCantidad = new ArrayList<DTCantidadTipoPublicacion>();
		arrayDtCantidad.add(nuevoDt);
		controladorOferta.registrarPaquete("paquetePrueba", "descripcionPrueba", 20, 50f, fechaDate, arrayDtCantidad);
		ArrayList<String> nombrePaquete = controladorOferta.listarPaquetes();
		Assert.assertEquals("paquetePrueba", nombrePaquete.get(0));

	}

	@Test
	public void agregarTipoPublicacionAlPaquete() throws TipoPublicacionNoExisteException,
			PaquetePublicacionNoExisteException, PaquetePublicacionYaExisteException, TipoPublicacionYaExisteException {
		manejadorOfertas = ManejadorOfertas.getInstance();
		manejadorSettings = ManejadorSettings.getInstance();
		manejadorPaquetes = ManejadorPaquetes.getInstance();
		controladorOferta = new ControladorOferta();

		TipoPublicacion publicacionPrueba = new TipoPublicacion("tipoPublicacionPrueba", "descripcionPrueba", "alta",
				20, 50f, fechaDate);
		manejadorSettings.addTipoPublicacion(publicacionPrueba);
		DTCantidadTipoPublicacion nuevoDt = new DTCantidadTipoPublicacion("tipoPublicacionPrueba", 5);
		ArrayList<DTCantidadTipoPublicacion> arrayDtCantidad = new ArrayList<DTCantidadTipoPublicacion>();
		arrayDtCantidad.add(nuevoDt);
		controladorOferta.registrarPaquete("paquetePrueba", "descripcionPrueba", 20, 50f, fechaDate, arrayDtCantidad);
		TipoPublicacion publicacionPruebaSegunda = new TipoPublicacion("tipoPublicacionPruebaSeguda",
				"descripcionPrueba", "alta", 20, 50f, fechaDate);
		manejadorSettings.addTipoPublicacion(publicacionPruebaSegunda);
		controladorOferta.agregarTipoPublicacionAlPaquete(50, "tipoPublicacionPruebaSeguda", "paquetePrueba");
		PaquetePublicacion encontrarPaquete = manejadorPaquetes.obtenerPaquete("paquetePrueba");
		ArrayList<CantidadTipoPublicacion> coleccionCantidadTipo = encontrarPaquete.getCantidadTipoPublicaciones();
		boolean encontre = false;
		String resultado = "";
		for (int i = 0; (i < coleccionCantidadTipo.size()) && (!encontre); i++) {
			if (coleccionCantidadTipo.get(i).getTipoPublicacion().getNombre() == "tipoPublicacionPruebaSeguda") {
				encontre = true;
				resultado = "tipoPublicacionPruebaSeguda";
			}
		}
		Assert.assertEquals("tipoPublicacionPruebaSeguda", resultado);
	}

	@Test
	public void testObtenerOfertasEmpresa()
			throws UsuarioYaExisteException, UsuarioEmailRepetidoException, OfertaLaboralYaExisteException,
			OfertaLaboralNoExisteException, UsuarioNoExisteException, TipoPublicacionYaExisteException,
			KeywordYaExisteException, TipoPublicacionNoExisteException, KeywordNoExisteException {
		manejadorOfertas = ManejadorOfertas.getInstance();
		manejadorSettings = ManejadorSettings.getInstance();
		manejadorPaquetes = ManejadorPaquetes.getInstance();
		manejadorUsuario = ManejadorUsuario.getInstance();
		controladorUsuario = new ControladorUsuario();
		controladorOferta = new ControladorOferta();

		controladorOferta.altaTipoPublicacion("tipoTesting", "Uso para testing", "baja", 50, 500f, fechaDate);
		controladorUsuario.altaEmpresa("nicknameEmpresa1", "nombre1", "apellido1", "email1@test.com", "descripcion1",
				"sitioWeb1");

		ArrayList<String> listaKeyword = new ArrayList<String>();
		listaKeyword.add("Keyword1");
		listaKeyword.add("Keyword2");
		Collections.sort(listaKeyword);
		controladorOferta.altaKeyword("Keyword1");
		controladorOferta.altaKeyword("Keyword2");

		controladorOferta.altaOfertaLaboral("test", "descipcionTest", "09:00", "15:00", 500f, "Montevideo",
				"Montevideo", fechaDate, "tipoTesting", "nicknameEmpresa1", listaKeyword);

		ArrayList<String> resultado = controladorOferta.obtenerOfertasEmpresa("nicknameEmpresa1");
		Assert.assertEquals("test", resultado.get(0));

	}

	@Test
	public void testRegistrarPostulacion() throws OfertaLaboralYaExisteException, OfertaLaboralNoExisteException,
			UsuarioYaExisteException, UsuarioNoExisteException, UsuarioEmailRepetidoException, KeywordYaExisteException,
			TipoPublicacionYaExisteException, TipoPublicacionNoExisteException, KeywordNoExisteException {
		manejadorOfertas = ManejadorOfertas.getInstance();
		manejadorSettings = ManejadorSettings.getInstance();
		manejadorPaquetes = ManejadorPaquetes.getInstance();
		manejadorUsuario = ManejadorUsuario.getInstance();
		controladorOferta = new ControladorOferta();
		controladorUsuario = new ControladorUsuario();
		controladorOferta.altaTipoPublicacion("tipoTesting", "Uso para testing", "baja", 50, 500f, fechaDate);
		controladorUsuario.altaEmpresa("nicknameEmpresa1", "nombre1", "apellido1", "email1@test.com", "descripcion1",
				"sitioWeb1");

		ArrayList<String> listaKeyword = new ArrayList<String>();
		listaKeyword.add("Keyword1");
		listaKeyword.add("Keyword2");
		Collections.sort(listaKeyword);
		controladorOferta.altaKeyword("Keyword1");
		controladorOferta.altaKeyword("Keyword2");

		controladorOferta.altaOfertaLaboral("test", "descipcionTest", "09:00", "15:00", 500f, "Montevideo",
				"Montevideo", fechaDate, "tipoTesting", "nicknameEmpresa1", listaKeyword);

		controladorUsuario.altaPostulante("nicknameTesting", "nombreTesting", "apellidoTesting", "email", fechaDate,
				"Montevideo");

		controladorUsuario.registrarPostulacion("cvReducidoString", "MotivacionTesting", fechaDate, "nicknameTesting",
				"test");

		ArrayList<String> resultado = controladorUsuario.listaOfertasUsuario("nicknameTesting");

		Assert.assertEquals("test", resultado.get(0));

	}

	@Test
	public void testKeywordNoExiste() {
		controladorOferta = new ControladorOferta();
		try {
			controladorOferta.obtenerKeywords("KeywordNoExiste");
		} catch (KeywordNoExisteException e) {
			// TODO Auto-generated catch block
			Assert.assertEquals("La keyword KeywordNoExiste no existe", e.getMessage());
		} catch (TipoPublicacionNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}