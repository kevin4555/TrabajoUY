package testing;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import excepciones.UsuarioEmailRepetidoException;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioYaExisteException;
import junit.framework.Assert;
import logica.classes.Empresa;
import logica.classes.Postulante;
import logica.classes.Usuario;
import logica.handlers.ManejadorUsuario;
import logica.interfaces.IcontroladorOferta;
import logica.interfaces.IcontroladorUsuario;

public class ManejadorUsuarioTesting{
  
	private static LocalDate fechaDate1;
	private static LocalDate fechaDate2;
	private static IcontroladorUsuario controladorUsuario;
	private static IcontroladorOferta controladorOferta;
	private static Postulante postulante1;
	private static Postulante postulante2;
	private static Empresa empresa1;
	private static  Empresa empresa2;
	private ManejadorUsuario manejadorUsuario;
	
	@BeforeClass
	public static void setUp()
	{		
		fechaDate1 = LocalDate.parse("1988-11-10");
	    fechaDate2 = LocalDate.parse("1988-09-02");
	    postulante1 = new Postulante("NicknameTest", "NombreTest", "ApellidoTest", "email1@test",
	            fechaDate1, "NacionalidadTest", null, "1234");
	    postulante2 = new Postulante("NicknameTest2", "NombreTest2", "ApellidoTest2", "email12@test",
	            fechaDate1, "NacionalidadTest", null, "1234");
		empresa1 = new Empresa("nicknameEmpresa1", "nombre1", "apellido1", "email1@empresa1",
		        "descripcion1", "sitioWeb1", null, "1234");
		empresa2 = new Empresa("nicknameEmpresa2", "nombre2", "apellido2", "email1@empresa2",
		        "descripcion2", "sitioWeb2", null, "1234");
		
	}
	
	@Before
	public void cleanUp() {
		manejadorUsuario = ManejadorUsuario.getInstance();
		manejadorUsuario.clean();
	}
	
	@Test
	public void agregarEmpresaYPostulanteTest() throws UsuarioYaExisteException, UsuarioEmailRepetidoException {
		manejadorUsuario = ManejadorUsuario.getInstance();
		manejadorUsuario.agregarEmpresa(empresa1);
		manejadorUsuario.agregarPostulante(postulante1);
		manejadorUsuario.agregarEmpresa(empresa2);
		manejadorUsuario.agregarPostulante(postulante2);
		ArrayList<String> resultado = (ArrayList<String>) manejadorUsuario.listarUsuarios();
		Collections.sort(resultado);
		ArrayList<String> esperado = new ArrayList<String>();
		esperado.add(empresa1.getNickname());
		esperado.add(empresa2.getNickname());
		esperado.add(postulante1.getNickname());
		esperado.add(postulante2.getNickname());
		Collections.sort(esperado);
		Assert.assertEquals(esperado, resultado);
	}
	
	@Test
	public void listarEmrpesasTest() throws UsuarioYaExisteException, UsuarioEmailRepetidoException {
		manejadorUsuario = ManejadorUsuario.getInstance();
		manejadorUsuario.agregarEmpresa(empresa1);
		manejadorUsuario.agregarEmpresa(empresa2);
		ArrayList<String> resultado = (ArrayList<String>) manejadorUsuario.listarEmpresas();
		Collections.sort(resultado);
		ArrayList<String> esperado = new ArrayList<String>();
		esperado.add("nicknameEmpresa1");
		esperado.add("nicknameEmpresa2");
		Collections.sort(esperado);
		Assert.assertEquals(esperado, resultado);
	}
	
	@Test
	public void listarPostulantesTest() throws UsuarioYaExisteException, UsuarioEmailRepetidoException {
		manejadorUsuario = ManejadorUsuario.getInstance();
		manejadorUsuario.agregarPostulante(postulante1);
		manejadorUsuario.agregarPostulante(postulante2);
		ArrayList<String> resultado = (ArrayList<String>) manejadorUsuario.listarPostulantes();
		Collections.sort(resultado);
		ArrayList<String> esperado = new ArrayList<String>();
		esperado.add("NicknameTest");
		esperado.add("NicknameTest2");
		Collections.sort(esperado);
		Assert.assertEquals(esperado, resultado);
		
	}
	
	@Test
	public void obtenerUsuarioTest() throws UsuarioYaExisteException, UsuarioEmailRepetidoException, UsuarioNoExisteException {
		manejadorUsuario = ManejadorUsuario.getInstance();
		manejadorUsuario.agregarPostulante(postulante1);
		manejadorUsuario.agregarEmpresa(empresa1);
		Usuario usuario1 = manejadorUsuario.obtenerUsuario("NicknameTest");
		Usuario usuario2 = manejadorUsuario.obtenerUsuario("nicknameEmpresa1");
		Assert.assertEquals(postulante1, usuario1);
		Assert.assertEquals(empresa1, usuario2);
		
	}
	
	@Test
	public void obtenerUsuarioException() {
		boolean exception = false;
		manejadorUsuario = ManejadorUsuario.getInstance();
		try {
			manejadorUsuario.agregarPostulante(postulante1);
			Usuario usuario = manejadorUsuario.obtenerUsuario("Pepe");
			
		} catch (UsuarioYaExisteException e) {
			
		} catch (UsuarioEmailRepetidoException e) {
			
		} catch (UsuarioNoExisteException e) {
			exception = true;
		}
		Assert.assertTrue(exception);
	}
	
	@Test
	public void obtenerEmpresaYPostulanteTest() throws UsuarioYaExisteException, UsuarioEmailRepetidoException, UsuarioNoExisteException {
		manejadorUsuario = ManejadorUsuario.getInstance();
		manejadorUsuario.agregarPostulante(postulante1);
		manejadorUsuario.agregarEmpresa(empresa1);
		Postulante postulante = manejadorUsuario.obtenerPostulante(postulante1.getNickname());
		Empresa empresa = manejadorUsuario.obtenerEmpresa(empresa1.getNickname());
		Assert.assertEquals(postulante1, postulante);
		Assert.assertEquals(empresa1, empresa);
	}
	
	@Test
	public void obtenerEmpresaException() {
		boolean exception = false;
		manejadorUsuario = ManejadorUsuario.getInstance();
		try {
			manejadorUsuario.agregarEmpresa(empresa1);
			Empresa empresa = manejadorUsuario.obtenerEmpresa("Pepe");
		} catch (UsuarioYaExisteException e) {
			
		} catch (UsuarioEmailRepetidoException e) {
			
		} catch (UsuarioNoExisteException e) {
			exception = true;
		}
		Assert.assertTrue(exception);
	}
	
	@Test
	public void obtenerPostulanteException() {
		boolean exception = false;
		manejadorUsuario = ManejadorUsuario.getInstance();
		try {
			manejadorUsuario.agregarPostulante(postulante1);
			Postulante postulante = manejadorUsuario.obtenerPostulante("Pepe");
		} catch (UsuarioYaExisteException e) {
			
		} catch (UsuarioEmailRepetidoException e) {
			
		} catch (UsuarioNoExisteException e) {
			exception = true;
		}
		Assert.assertTrue(exception);
	}
	
	@Test
	public void agregarEpresaEmailRepetidoTest() {
		manejadorUsuario = ManejadorUsuario.getInstance();
		boolean exception = false;
		try {
			 
			manejadorUsuario.agregarEmpresa(empresa1);
			Empresa empresa = new Empresa("nicknameEmpresa3", "nombre1", "apellido1", "email1@empresa1",
			        "descripcion1", "sitioWeb1", null, "1234");
			manejadorUsuario.agregarEmpresa(empresa);
		} catch (UsuarioYaExisteException e) {
			// TODO Auto-generated catch block
			
		} catch (UsuarioEmailRepetidoException e) {
			exception = true;
			
		}
		Assert.assertTrue(exception);
	}
	
	@Test
	public void agregarPostulanteEmailRepetido() {
		manejadorUsuario = ManejadorUsuario.getInstance();
		boolean exception = false;
		try {
			 
			manejadorUsuario.agregarPostulante(postulante1);
			Postulante postulante = new Postulante("NicknameTest3", "NombreTest", "ApellidoTest", "email1@test",
		            fechaDate1, "NacionalidadTest", null, "1234");
			manejadorUsuario.agregarPostulante(postulante);
		} catch (UsuarioYaExisteException e) {
			// TODO Auto-generated catch block
			
		} catch (UsuarioEmailRepetidoException e) {
			exception = true;
		}
		Assert.assertTrue(exception);
	}
	
	@Test
	public void postulanteYEmpresaEmailRepetido() {
		manejadorUsuario = ManejadorUsuario.getInstance();
		boolean exception = false;
		try {
			 
			manejadorUsuario.agregarPostulante(postulante1);
			Empresa empresa = new Empresa("nicknameEmpresa3", "nombre1", "apellido1", "email1@test",
			        "descripcion1", "sitioWeb1", null, "1234");
			manejadorUsuario.agregarEmpresa(empresa);
		} catch (UsuarioYaExisteException e) {
			// TODO Auto-generated catch block
			
		} catch (UsuarioEmailRepetidoException e) {
			exception = true;
		}
		Assert.assertTrue(exception);
	}
	
	@Test
	public void postulanteNickRepetido() {
		manejadorUsuario = ManejadorUsuario.getInstance();
		boolean exception = false;
		try {
			 
			manejadorUsuario.agregarPostulante(postulante1);
			Postulante postulante = new Postulante("NicknameTest", "NombreTest", "ApellidoTest", "email13@test",
		            fechaDate1, "NacionalidadTest", null, "1234");
			manejadorUsuario.agregarPostulante(postulante);
		} catch (UsuarioYaExisteException e) {
			exception = true;
			
		} catch (UsuarioEmailRepetidoException e) {
			
		}
		Assert.assertTrue(exception);
	}
	
	@Test
	public void empresaNickRepetido() {
		manejadorUsuario = ManejadorUsuario.getInstance();
		boolean exception = false;
		try {
			 
			manejadorUsuario.agregarEmpresa(empresa1);
			Empresa empresa = new Empresa("nicknameEmpresa1", "nombre1", "apellido1", "email18@empresa1",
			        "descripcion1", "sitioWeb1", null, "1234");
			manejadorUsuario.agregarEmpresa(empresa);
		} catch (UsuarioYaExisteException e) {
			exception = true;
			
		} catch (UsuarioEmailRepetidoException e) {
			
		}
		Assert.assertTrue(exception);
	}
	
	@Test
	public void empresaYPostulanteNickRepetido() {
		manejadorUsuario = ManejadorUsuario.getInstance();
		boolean exception = false;
		try {
			 
			manejadorUsuario.agregarEmpresa(empresa1);
			Postulante postulante = new Postulante("nicknameEmpresa1", "NombreTest", "ApellidoTest", "email13@test",
		            fechaDate1, "NacionalidadTest", null, "1234");
			manejadorUsuario.agregarPostulante(postulante);
		} catch (UsuarioYaExisteException e) {
			exception = true;
			
		} catch (UsuarioEmailRepetidoException e) {
			
		}
		Assert.assertTrue(exception);
	}
}