package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import excepciones.UsuarioEmailRepetido;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioYaExisteException;
import junit.framework.Assert;
import logica.classes.Empresa;
import logica.classes.Postulante;
import logica.classes.Usuario;
import logica.handlers.ManejadorUsuario;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ManejadorUsuarioTesting
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
	public void agregarEmpresaYPostulanteTest() throws UsuarioYaExisteException, UsuarioNoExisteException, UsuarioEmailRepetido 
	{
		Empresa empresa = new Empresa("empresa1", "Empresa 1", "Apellido", "empresa@example.com", "Descripción", "www.empresa.com");
		Postulante postulante = new Postulante("postulante1", "Postulante 1", "Apellido", "postulante@example.com", this.fechaDate, "Nacionalidad");
		ManejadorUsuario manejador = ManejadorUsuario.getInstance();
		manejador.agregarEmpresa(empresa);
		manejador.agregarPostulante(postulante);
	    Assert.assertEquals(empresa, manejador.obtenerEmpresa(empresa.getNickname()));
	    Assert.assertEquals(postulante, manejador.obtenerPostulante(postulante.getNickname()));
	    Assert.assertEquals(1, manejador.listarEmpresas().size());
	    Assert.assertEquals(1, manejador.listarPostulantes().size());
	    Assert.assertEquals(2, manejador.listarUsuarios().size());
	    
	}
}