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

import excepciones.UsuarioYaExisteException;
import logica.interfaces.IControladorDatosDePrueba;
import logica.interfaces.IControladorOferta;
import logica.interfaces.IControladorUsuario;

public class ControladorDatosDePrueba implements IControladorDatosDePrueba {

	@Override
	public void cargarDatosDePrueba() throws ParseException, UsuarioYaExisteException {
		cleanDatosActuales();
		Fabrica fabrica = Fabrica.getInstance();
		IControladorUsuario iControladorUsuario = fabrica.obtenerControladorUsuario();
		IControladorOferta iControladorOferta = fabrica.obtenerControladorOferta();
		String separador = ";";

		Map<String, ArrayList<String>> mapUsuarios = createCSVMap(createCSVPath("Usuarios"), separador);
		Map<String, ArrayList<String>> mapEmpresas = createCSVMap(createCSVPath("Usuarios-Empresas"), separador);
		Map<String, ArrayList<String>> mapPostulantes = createCSVMap(createCSVPath("Usuarios-Postulantes"), separador);
		for (String id : mapUsuarios.keySet()) {
			ArrayList<String> datosUsuario = mapUsuarios.get(id);
			String nickName = datosUsuario.get(1);
			String nombre = datosUsuario.get(2);
			String apellido = datosUsuario.get(3); 
			String email = datosUsuario.get(4); 
			if (datosUsuario.get(0).equals("P")) {
				ArrayList<String> datosPostulante = mapPostulantes.get(id);
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date fechaNacimiento = dateFormat.parse(datosPostulante.get(0));
				String nacionalidad = datosPostulante.get(1);
				iControladorUsuario.altaPostulante(nickName, nombre, apellido, email, fechaNacimiento, nacionalidad);
			}else {
				
			}
		}
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
                    continue; // Omitir la primera línea
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
		return  basePath + fileName +".csv";
	}

}
