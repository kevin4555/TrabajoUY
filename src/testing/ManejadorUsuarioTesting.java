package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import excepciones.UsuarioEmailRepetido;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioYaExisteException;
import junit.framework.Assert;
import logica.classes.Empresa;
import logica.classes.Postulante;
import logica.classes.Usuario;
import logica.controllers.Fabrica;
import logica.handlers.ManejadorOfertas;
import logica.handlers.ManejadorSettings;
import logica.handlers.ManejadorUsuario;
import logica.interfaces.IControladorOferta;
import logica.interfaces.IControladorUsuario;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ManejadorUsuarioTesting
{
	private static Date fechaDate1;
	private static Date fechaDate2;
	private static IControladorUsuario controladorUsuario;
	private static IControladorOferta controladorOferta;
	private static Postulante postulante1;
	private static Postulante postulante2;
	private static Empresa empresa1;
	private static  Empresa empresa2;
	private ManejadorUsuario manejadorUsuario;
	
	@BeforeClass
	public static void setUp()
	{		
		String fecha1 = "02/09/1988";
		String fecha2 = "04/4/2023";
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try
		{
			fechaDate1 = dateFormat.parse(fecha1);
			fechaDate2 = dateFormat.parse(fecha2);
		}
		catch (ParseException e)
		{
			e.getMessage();
		}
		postulante1 = new Postulante("NicknameTest", "NombreTest", "ApellidoTest", "EmailTest@test.com", fechaDate1, "NacionalidadTest" );
		postulante2 = new Postulante("NicknameTest2", "NombreTest2", "ApellidoTest2", "EmailTest2@test.com", fechaDate2, "NacionalidadTest2" );
		empresa1 = new Empresa("nicknameEmpresa1", "nombre1", "apellido1", "email1@test.com", "descripcion1", "sitioWeb1" );
		empresa2 = new Empresa("nicknameEmpresa2", "nombre2", "apellido2", "email2@test.com", "descripcion2", "sitioWeb2");
		
	}
	
	@Before
	public void cleanUp() {
		manejadorUsuario = ManejadorUsuario.getInstance();
		manejadorUsuario.clean();
	}
	
	@Test
	public void agregarEmpresaYPostulanteTest() throws UsuarioYaExisteException, UsuarioEmailRepetido {
		manejadorUsuario = ManejadorUsuario.getInstance();
		manejadorUsuario.agregarEmpresa(empresa1);
		manejadorUsuario.agregarPostulante(postulante1);
		manejadorUsuario.agregarEmpresa(empresa2);
		manejadorUsuario.agregarPostulante(postulante2);
		ArrayList<String> resultado = manejadorUsuario.listarUsuarios();
		Collections.sort(resultado);
		ArrayList<String> esperado = new ArrayList<String>();
		esperado.add(empresa1.getNickname());
		esperado.add(empresa2.getNickname());
		esperado.add(postulante1.getNickname());
		esperado.add(postulante2.getNickname());
		Collections.sort(esperado);
		Assert.assertEquals(esperado, resultado);
		
	    
	}
}