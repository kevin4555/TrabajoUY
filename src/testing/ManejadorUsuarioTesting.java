package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioYaExisteException;
import junit.framework.Assert;
import logica.classes.Empresa;
import logica.classes.Postulante;
import logica.handlers.ManejadorUsuario;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ManejadorUsuarioTesting
{
	private ManejadorUsuario manejador;
	private Date fechaDate;
	private Empresa empresa;
	private Postulante postulante;
	private String fecha = "17/06/1999";
	
	@Before
	public void setUp()
	{
		this.manejador = ManejadorUsuario.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try
		{
			this.fechaDate = dateFormat.parse(fecha);
		}
		catch (ParseException e)
		{
			e.getMessage();
		}
        this.empresa = new Empresa("empresa1", "Empresa 1", "Apellido", "empresa@example.com", "Descripción", "www.empresa.com");
        this.postulante = new Postulante("postulante1", "Postulante 1", "Apellido", "postulante@example.com", this.fechaDate, "Nacionalidad");
	}
	
    @Test
    public void testAgregarEmpresaYObtenerEmpresa() throws UsuarioYaExisteException, UsuarioNoExisteException {
        manejador.agregarEmpresa(empresa);

        assertEquals(empresa, manejador.obtenerEmpresa("empresa1"));
    }



    @Test(expected = UsuarioYaExisteException.class)
    public void testAgregarEmpresaExistente() throws UsuarioYaExisteException {
        manejador.agregarEmpresa(empresa);
        manejador.agregarEmpresa(empresa);
    }

    @Test
    public void testAgregarPostulanteYObtenerPostulante() throws UsuarioYaExisteException, UsuarioNoExisteException {
        manejador.agregarPostulante(postulante);

        assertEquals(postulante, manejador.obtenerPostulante("postulante1"));
    }

    @Test(expected = UsuarioYaExisteException.class)
    public void testAgregarPostulanteExistente() throws UsuarioYaExisteException {
        manejador.agregarPostulante(postulante);
        manejador.agregarPostulante(postulante);
    }

    @Test
    public void testListarEmpresas() throws UsuarioYaExisteException {
        manejador.agregarEmpresa(empresa);

        ArrayList<String> empresas = manejador.listarEmpresas();
        assertTrue(empresas.contains("empresa1"));
    }

    @Test
    public void testListarPostulantes() throws UsuarioYaExisteException {
        manejador.agregarPostulante(postulante);

        ArrayList<String> postulantes = manejador.listarPostulantes();
        assertTrue(postulantes.contains("postulante1"));
    }

    @Test
    public void testListarUsuarios() throws UsuarioYaExisteException {
        manejador.agregarEmpresa(empresa);
        manejador.agregarPostulante(postulante);

        ArrayList<String> usuarios = manejador.listarUsuarios();
        assertTrue(usuarios.contains("empresa1"));
        assertTrue(usuarios.contains("postulante1"));
    }

    @Test
    public void testObtenerUsuario() throws UsuarioYaExisteException, UsuarioNoExisteException {
        manejador.agregarEmpresa(empresa);

        assertEquals(empresa, manejador.obtenerUsuario("empresa1"));
    }

}

