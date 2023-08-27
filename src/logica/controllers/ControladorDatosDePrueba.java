package logica.controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import excepciones.KeywordNoExisteException;
import excepciones.KeywordYaExisteException;
import excepciones.OfertaLaboralNoExisteException;
import excepciones.PaquetePublicacionNoExisteException;
import excepciones.PaquetePublicacionYaExisteException;
import excepciones.TipoPublicacionNoExisteException;
import excepciones.TipoPublicacionYaExisteException;
import excepciones.UsuarioEmailRepetidoException;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioYaExisteException;
import logica.classes.CompraPaquete;
import logica.classes.Empresa;
import logica.classes.Keyword;
import logica.classes.PaquetePublicacion;
import logica.classes.TipoPublicacion;
import logica.interfaces.IControladorDatosDePrueba;
import logica.interfaces.IControladorOferta;
import logica.interfaces.IControladorUsuario;

public class ControladorDatosDePrueba implements IControladorDatosDePrueba {

	@Override
	public void cargarDatosDePrueba() throws Exception {
		cleanDatosActuales();
		String separador = ";";
		Map<String, ArrayList<String>> mapUsuarios = createCSVMap(createCSVPath("Usuarios"), separador);
		Map<String, ArrayList<String>> mapEmpresas = createCSVMap(createCSVPath("Usuarios-Empresas"), separador);
		Map<String, ArrayList<String>> mapPostulantes = createCSVMap(createCSVPath("Usuarios-Postulantes"), separador);
		Map<String, ArrayList<String>> mapTipoPublicacion = createCSVMap(createCSVPath("TipoPublicacion"), separador);
		Map<String, ArrayList<String>> mapKeywords = createCSVMap(createCSVPath("Keywords"), separador);
		Map<String, ArrayList<String>> mapPaquetes = createCSVMap(createCSVPath("Paquetes"), separador);
		Map<String, ArrayList<String>> mapTipoPublicacionPaquetes = createCSVMap(
				createCSVPath("TiposPublicacionPaquetes"), separador);
		Map<String, ArrayList<String>> mapEmpresasPaquetes = createCSVMap(createCSVPath("EmpresasPaquetes"), separador);
		Map<String, ArrayList<String>> mapOfertasLaborales = createCSVMap(createCSVPath("OfertasLaborales"), separador);
		Map<String, ArrayList<String>> mapPostulaciones = createCSVMap(createCSVPath("Postulaciones"), separador);
		Map<String, ArrayList<String>> mapOfertasLaboralesKeywords = createCSVMap(
				createCSVPath("OfertasLaboralesKeywords"), separador);
		cargarUsuarios(mapUsuarios, mapEmpresas, mapPostulantes);
		System.out.print("OK" + " cargarUsuarios\n");
		cargarTipoPublicacion(mapTipoPublicacion);
		System.out.print("OK" + " cargarTipoPublicacion\n");
		cargarKeywords(mapKeywords);
		System.out.print("OK" + " cargarKeywords\n");
		cargarPaquetesyCantidadTipoPublicacion(mapPaquetes, mapTipoPublicacionPaquetes, mapTipoPublicacion);
		System.out.print("OK" + " cargarPaquetesyCantidadTipoPublicacion\n");
		cargarEmpresaPaquete(mapUsuarios, mapPaquetes, mapEmpresasPaquetes);
		System.out.print("OK" + " cargarEmpresaPaquete\n");
		cargarOfertasLaborales(mapUsuarios, mapOfertasLaborales, mapTipoPublicacion);
		System.out.print("OK" + " cargarOfertasLaborales\n");
		cargarOfertasLaboralesKeywords(mapOfertasLaboralesKeywords, mapKeywords, mapOfertasLaborales);
		System.out.print("OK" + " cargarOfertasLaboralesKeywords\n");
		cargarPostulaciones(mapPostulaciones, mapUsuarios, mapOfertasLaborales);
		System.out.print("OK" + " cargarPostulaciones\n");
	}

	private void cleanDatosActuales() {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorUsuario controladorUsuario = fabrica.obtenerControladorUsuario();
		IControladorOferta controladorOferta = fabrica.obtenerControladorOferta();
	}

	private Map<String, ArrayList<String>> createCSVMap(String archivoCSV, String separador) {
		Map<String, ArrayList<String>> csvMap = new HashMap<>();

		try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
			String linea;
			boolean primeraLinea = true;
			while ((linea = br.readLine()) != null) {
				if (primeraLinea) {
					primeraLinea = false;
					continue;
				}

				String[] datos = linea.split(separador);
				String primerDato = datos[0];
				ArrayList<String> valores = new ArrayList<>();

				for (int i = 1; i < datos.length; i++) {
					valores.add(datos[i]);
				}

				csvMap.put(primerDato, valores);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return csvMap;
	}

	private String createCSVPath(String fileName) {
		String basePath = "resources\\DatosDePruebaCSV\\";
		return basePath + fileName + ".csv";
	}

	private void cargarUsuarios(Map<String, ArrayList<String>> mapUsuarios, Map<String, ArrayList<String>> mapEmpresas,
			Map<String, ArrayList<String>> mapPostulantes) throws ParseException, UsuarioYaExisteException, UsuarioEmailRepetidoException {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorUsuario iControladorUsuario = fabrica.obtenerControladorUsuario();

		for (String id : mapUsuarios.keySet()) {
			ArrayList<String> datosUsuario = mapUsuarios.get(id);
			String nickName = datosUsuario.get(1);
			String nombre = datosUsuario.get(2);
			String apellido = datosUsuario.get(3);
			String email = datosUsuario.get(4);
			if (datosUsuario.get(0).equals("P")) {
				ArrayList<String> datosPostulante = mapPostulantes.get(id);
				Date fechaNacimiento = stringToDate("dd/MM/yyyy", datosPostulante.get(0));
				String nacionalidad = datosPostulante.get(1);
				iControladorUsuario.altaPostulante(nickName, nombre, apellido, email, fechaNacimiento, nacionalidad);
			} else {
				ArrayList<String> datosEmpresa = mapEmpresas.get(id);
				String descripcion = datosEmpresa.get(0);
				String web = datosEmpresa.get(1);
				iControladorUsuario.altaEmpresa(nickName, nombre, apellido, email, descripcion, web);
			}
		}
	}

	private void cargarTipoPublicacion(Map<String, ArrayList<String>> mapTipoPublicacion)
			throws NumberFormatException, TipoPublicacionYaExisteException, ParseException {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorOferta iControladorOferta = fabrica.obtenerControladorOferta();

		for (String id : mapTipoPublicacion.keySet()) {
			ArrayList<String> datosTipoPublicacion = mapTipoPublicacion.get(id);
			String nombre = datosTipoPublicacion.get(0);
			String descripcion = datosTipoPublicacion.get(1);
			String exposicion = datosTipoPublicacion.get(2);
			String duracion = datosTipoPublicacion.get(3);
			String costo = datosTipoPublicacion.get(4);
			Date fechaAlta = stringToDate("dd/MM/yyyy", datosTipoPublicacion.get(5));
			iControladorOferta.altaTipoPublicacion(nombre, descripcion, exposicion, Integer.valueOf(duracion),
					Float.valueOf(costo), fechaAlta);
		}
	}

	private void cargarKeywords(Map<String, ArrayList<String>> mapKeywords) throws KeywordYaExisteException {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorOferta iControladorOferta = fabrica.obtenerControladorOferta();

		for (String id : mapKeywords.keySet()) {
			ArrayList<String> datosKeyword = mapKeywords.get(id);
			iControladorOferta.altaKeyword(datosKeyword.get(0));
		}
	}

	private void cargarPaquetesyCantidadTipoPublicacion(Map<String, ArrayList<String>> mapPaquetes,
			Map<String, ArrayList<String>> mapTipoPublicacionPaquetes,
			Map<String, ArrayList<String>> mapTipoPublicacion) throws ParseException, PaquetePublicacionYaExisteException, TipoPublicacionYaExisteException, TipoPublicacionNoExisteException, PaquetePublicacionNoExisteException {

		Fabrica fabrica = Fabrica.getInstance();
		IControladorOferta iControladorOferta = fabrica.obtenerControladorOferta();

		for (String id : mapPaquetes.keySet()) {
			ArrayList<String> datosPaquete = mapPaquetes.get(id);
			String nombre = datosPaquete.get(0);
			String descripcion = datosPaquete.get(1);
			int periodoValDias = Integer.valueOf(datosPaquete.get(2).substring(0, datosPaquete.get(2).indexOf(" ")));
			Float descuento = Float.valueOf(datosPaquete.get(3));
			Date fechaAlta = stringToDate("dd/MM/yyyy", datosPaquete.get(4));
			iControladorOferta.registrarPaquete(nombre, descripcion, periodoValDias, descuento, fechaAlta, null);
		}
		System.out.print("OK" + " cargarPaquetes\n");
		for (String id : mapTipoPublicacionPaquetes.keySet()) {
			ArrayList<String> datosTipoPublicacionPaquete = mapTipoPublicacionPaquetes.get(id);
			for (int i = 0; i < datosTipoPublicacionPaquete.size(); i++) {
				datosTipoPublicacionPaquete.set(i, datosTipoPublicacionPaquete.get(i).replaceAll(" ", ""));
			}
			String nombreTipoPublicacion = mapTipoPublicacion.get(datosTipoPublicacionPaquete.get(1)).get(0);
			String nombrePaquete = mapPaquetes.get(datosTipoPublicacionPaquete.get(0)).get(0);
			int cantidad = Integer.valueOf(datosTipoPublicacionPaquete.get(2));
			iControladorOferta.agregarTipoPublicacionAlPaquete(cantidad, nombreTipoPublicacion, nombrePaquete);
		}
	}

	private void cargarEmpresaPaquete(Map<String, ArrayList<String>> mapUsuarios,
			Map<String, ArrayList<String>> mapPaquetes, Map<String, ArrayList<String>> mapEmpresaPaquetes)
			throws Exception {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorUsuario iControladorUsuario = fabrica.obtenerControladorUsuario();
		IControladorOferta iControladorOferta = fabrica.obtenerControladorOferta();

		for (String id : mapEmpresaPaquetes.keySet()) {
			ArrayList<String> datosEmpresa = mapUsuarios.get(id);
			ArrayList<String> datosEmpresaPaquetes = mapEmpresaPaquetes.get(id);
			Empresa empresa = iControladorUsuario.obtenerEmpresa(datosEmpresa.get(1));
			String[] subcadenas = datosEmpresaPaquetes.get(0).split(", ");
			Date fechaCompra = stringToDate("dd/MM/yyyy", datosEmpresaPaquetes.get(1));
			for (String idPaquete : subcadenas) {
				PaquetePublicacion paquetePublicacion = iControladorOferta
						.obtenerPaquetePublicacion(mapPaquetes.get(idPaquete).get(0));
				CompraPaquete compraPaquete = new CompraPaquete(empresa, paquetePublicacion, fechaCompra);
				empresa.addCompraPaquete(compraPaquete);
				paquetePublicacion.addCompraPaquete(compraPaquete);
			}
		}

	}

	private void cargarOfertasLaborales(Map<String, ArrayList<String>> mapUsuarios,
			Map<String, ArrayList<String>> mapOfertasLaborales, Map<String, ArrayList<String>> mapTipoPublicacion)
			throws Exception {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorUsuario iControladorUsuario = fabrica.obtenerControladorUsuario();
		IControladorOferta iControladorOferta = fabrica.obtenerControladorOferta();

		for (String id : mapOfertasLaborales.keySet()) {
			ArrayList<String> datosOfertaLaboral = mapOfertasLaborales.get(id);
			ArrayList<String> datosEmpresa = mapUsuarios.get(datosOfertaLaboral.get(6));
			ArrayList<String> datosTipoPublicacion = mapTipoPublicacion.get(datosOfertaLaboral.get(7));
			Empresa empresa = iControladorUsuario.obtenerEmpresa(datosEmpresa.get(1));
			TipoPublicacion tipoPublicacion = iControladorOferta.obtenerTipoPublicacion(datosTipoPublicacion.get(0));
			String nombre = datosOfertaLaboral.get(0);
			String descripcion = datosOfertaLaboral.get(1);
			String departamento = datosOfertaLaboral.get(2);
			String ciudad = datosOfertaLaboral.get(3);
			String[] horario = datosOfertaLaboral.get(4).split(" - ");
			String horaInicio = horario[0];
			String horaFinal = horario[1];
			Float remoneracion = Float.valueOf(datosOfertaLaboral.get(5));
			Date fechaAlta = stringToDate("dd/MM/yyyy", datosOfertaLaboral.get(8));
			iControladorOferta.altaOfertaLaboral(nombre, descripcion, horaInicio, horaFinal, remoneracion, ciudad,
					departamento, fechaAlta, tipoPublicacion.getNombre(), empresa.getNickname(),null);
		}

	}

	private void cargarOfertasLaboralesKeywords(Map<String, ArrayList<String>> mapOfertasLaboralesKeywords,
			Map<String, ArrayList<String>> mapKeywords, Map<String, ArrayList<String>> mapOfertasLaborales)
			throws KeywordNoExisteException, OfertaLaboralNoExisteException, TipoPublicacionNoExisteException {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorUsuario iControladorUsuario = fabrica.obtenerControladorUsuario();
		IControladorOferta iControladorOferta = fabrica.obtenerControladorOferta();

		for (String idOferta : mapOfertasLaborales.keySet()) {
			String[] idKeywords = mapOfertasLaboralesKeywords.get(idOferta).get(0).split(", ");
			String nombreOfertaLaboral = mapOfertasLaborales.get(idOferta).get(0);
			for (String idKeyword : idKeywords) {
				Keyword keyword = iControladorOferta.obtenerKeywords(mapKeywords.get(idKeyword).get(0));
				iControladorOferta.obtenerOfertaLaboral(nombreOfertaLaboral).agregarKeyword(keyword);
			}
		}
	}

	private void cargarPostulaciones(Map<String, ArrayList<String>> mapPostulaciones,
			Map<String, ArrayList<String>> mapUsuarios, Map<String, ArrayList<String>> mapOfertasLaborales)
			throws ParseException, UsuarioNoExisteException, OfertaLaboralNoExisteException {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorOferta iControladorOferta = fabrica.obtenerControladorOferta();

		for (String idPostulacion : mapPostulaciones.keySet()) {
			ArrayList<String> datosPostulacion = mapPostulaciones.get(idPostulacion);

			String cvReducido = datosPostulacion.get(1);
			String motivacion = datosPostulacion.get(2);
			Date fechaPostulacion = stringToDate("dd/MM/yyyy", datosPostulacion.get(3));
			String nickname = mapUsuarios.get(datosPostulacion.get(0)).get(0);
			String nombreOferta = mapOfertasLaborales.get(datosPostulacion.get(4)).get(0);
			iControladorOferta.registrarPostulacion(cvReducido, motivacion, fechaPostulacion, nickname, nombreOferta);
		}
	}

	private Date stringToDate(String formato, String dateString) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
		return dateFormat.parse(dateString);
	}
}
