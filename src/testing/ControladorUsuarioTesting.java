package testing;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
/*
import java.sql.Date;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioYaExisteException;
import junit.framework.Assert;
import logica.classes.Postulante;
import logica.classes.Usuario;
import logica.controllers.Fabrica;
import logica.interfaces.IControladorUsuario;

public class ControladorUsuarioTesting {
	
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
	public void listarUsuuariosListaVacia() {
		IControladorUsuario controladorUsuario = Fabrica.getInstance().obtenerControladorUsuario();
		ArrayList<String> lista = controladorUsuario.listaDeUsuarios();
		//Assert.assertTrue(lista.isEmpty());
	}

	
	@Test
	public void obtenerUsuarioManejadorVacio() throws UsuarioNoExisteException {
		IControladorUsuario controladorUsuario = Fabrica.getInstance().obtenerControladorUsuario();
		
		try{
			controladorUsuario.obtenerUsuario("Carlitos");
		}catch(UsuarioNoExisteException e)
		{
			Assert.assertEquals("El usuario Carlitos no existe", e.getMessage());
		}

	}
	
	@Test
	public void altaPostulanteTest() throws UsuarioYaExisteException, UsuarioNoExisteException {
		
		Postulante postulanteEsperado = new Postulante("NicknameTest", "NombreTest", "ApellidoTest", "EmailTest", Date.valueOf("1988-9-2"), "NacionalidadTest" );
		IControladorUsuario controladorUsuario = Fabrica.getInstance().obtenerControladorUsuario();
		controladorUsuario.vaciarManejadorUsuario();
		controladorUsuario.altaPostulante("NicknameTest", "NombreTest", "ApellidoTest", "EmailTest", Date.valueOf("1988-9-2"), "NacionalidadTest" );
		Postulante postulanteResultado = controladorUsuario.obtenerPostulante("NicknameTest");
		Assert.assertEquals(postulanteEsperado.getNickname(), postulanteResultado.getNickname());
		Assert.assertEquals(postulanteEsperado.getNombre(), postulanteResultado.getNombre());
		Assert.assertEquals(postulanteEsperado.getApellido(), postulanteResultado.getApellido());
		Assert.assertEquals(postulanteEsperado.getEmail(), postulanteResultado.getEmail());
		Assert.assertEquals(postulanteEsperado.getFechaNacimiento().toString(),postulanteResultado.getFechaNacimiento().toString());
		Assert.assertEquals(postulanteEsperado.getNacionalidad(), postulanteResultado.getNacionalidad());
	}
	
	@Test
	public void listarUsuariosUsuarioUnico() throws UsuarioYaExisteException {
		IControladorUsuario controladorUsuario = Fabrica.getInstance().obtenerControladorUsuario();
		controladorUsuario.vaciarManejadorUsuario();
		controladorUsuario.altaPostulante("NicknameTest", "NombreTest", "ApellidoTest", "EmailTest", Date.valueOf("1988-9-2"), "NacionalidadTest" );
		ArrayList<String> listaEsperada = new ArrayList<String>();
		listaEsperada.add("NicknameTest");
		ArrayList<String> listaResultado = controladorUsuario.listaDeUsuarios();
		Assert.assertEquals(listaEsperada, listaResultado);
	}
	
	@Test
	public void listarUsuuariosListaVacia() {
		IControladorUsuario controladorUsuario = Fabrica.getInstance().obtenerControladorUsuario();
		controladorUsuario.vaciarManejadorUsuario();
		ArrayList<String> listaResultado = controladorUsuario.listaDeUsuarios();
		ArrayList<String> listaEsperada = new ArrayList<String>();
		Assert.assertTrue(listaResultado.isEmpty());
		//Assert.assertEquals(listaEsperada, listaResultado);
		
	}
	
	@Test
	public void obtenerUsuarioManejadorVacio() throws UsuarioNoExisteException {
		IControladorUsuario controladorUsuario = Fabrica.getInstance().obtenerControladorUsuario();
		Usuario usuario = controladorUsuario.obtenerUsuario("NicknameTest");
		Assert.fail();
	}
}
	
*/