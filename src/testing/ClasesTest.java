package testing;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;
import logica.classes.CantidadTipoPublicacion;
import logica.classes.CompraPaquete;
import logica.classes.Empresa;
import logica.classes.Keyword;
import logica.classes.PaquetePublicacion;
import logica.classes.TipoPublicacion;

public class ClasesTest {

	private Date fechaDate;
	private Date fechaDateSegunda;
	private String fecha = "17/06/1999";
	private String fechaSegunda = "17/06/2000";

	@Before
	public void setUp() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			this.fechaDate = dateFormat.parse(fecha);
			this.fechaDateSegunda = dateFormat.parse(fechaSegunda);
		} catch (ParseException e) {
			e.getMessage();
		}
	}

	@Test
	public void setCantidadRestanteClaseCantidadTipoPublicacion() {
		TipoPublicacion nuevoTipoPublicacion = new TipoPublicacion("nombreTest", "descipcionTest", "exposicionTest", 12,
				12f, fechaDate);
		CantidadTipoPublicacion cantidadTipoPublicacion = new CantidadTipoPublicacion(5, nuevoTipoPublicacion);
		cantidadTipoPublicacion.setCantidadRestante(5);
		Assert.assertEquals(5, cantidadTipoPublicacion.getCantidadRestante());
	}

	@Test
	public void setPaquetePublicacionClaseCantidadTipoPublicacion() {
		ArrayList<CantidadTipoPublicacion> nuevoCantidadTipoPublicacion = new ArrayList<CantidadTipoPublicacion>();
		PaquetePublicacion nuevoPaquetePublicacion = new PaquetePublicacion("nombreTest", "descipcionTest", 5, 50f,
				nuevoCantidadTipoPublicacion);
		TipoPublicacion nuevoTipoPublicacion = new TipoPublicacion("nombreTest", "descipcionTest", "exposicionTest", 12,
				12f, fechaDate);
		CantidadTipoPublicacion cantidadTipoPublicacion = new CantidadTipoPublicacion(5, nuevoTipoPublicacion);
		cantidadTipoPublicacion.setPaquetePublicacion(nuevoPaquetePublicacion);
		PaquetePublicacion resultadoPaquetePublicacion = cantidadTipoPublicacion.getPaquetePublicacion();
		Assert.assertEquals(nuevoPaquetePublicacion, resultadoPaquetePublicacion);

	}

	@Test
	public void setTipoPublicacionClaseCantidadTipoPublicacion() {
		TipoPublicacion nuevoTipoPublicacion = new TipoPublicacion("nombreTest", "descipcionTest", "exposicionTest", 12,
				12f, fechaDate);
		CantidadTipoPublicacion cantidadTipoPublicacion = new CantidadTipoPublicacion(5, nuevoTipoPublicacion);
		TipoPublicacion tipoPublicacionPrueba = new TipoPublicacion("nombrePrueba", "descipcionTest", "exposicionTest",
				12, 12f, fechaDate);

		cantidadTipoPublicacion.setTipoPublicacion(tipoPublicacionPrueba);

		Assert.assertEquals(tipoPublicacionPrueba, cantidadTipoPublicacion.getTipoPublicacion());

	}

	@Test
	public void setClaseEmpresa() {
		Empresa empresa1 = new Empresa("nicknameEmpresa1", "nombre1", "apellido1", "email1@test.com", "descripcion1",
				"sitioWeb1");

		empresa1.setApellido("ApellidoTest");
		empresa1.setDescripcion("DescripcionPrueba");
		empresa1.setNombre("nuevoNombre");
		empresa1.setSitioWeb("nuevoSitioWeb");

		Assert.assertEquals("ApellidoTest", empresa1.getApellido());
		Assert.assertEquals("DescripcionPrueba", empresa1.getDescripcion());
		Assert.assertEquals("nuevoNombre", empresa1.getNombre());
		Assert.assertEquals("nuevoSitioWeb", empresa1.getSitioWeb());
	}

	@Test
	public void setClaseKeyword() {
		Keyword nuevoKeyword = new Keyword("KeywordPrueba");
		nuevoKeyword.setNombre("KeywordTest");
		Assert.assertEquals("KeywordTest", nuevoKeyword.getNombre());
	}
	

}