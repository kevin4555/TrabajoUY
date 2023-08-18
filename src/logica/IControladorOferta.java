package logica;

import java.sql.Date;
import java.util.ArrayList;

import excepciones.TipoPublicacionNoExisteException;

public interface IControladorOferta {
	
	//public abstract void altaDatosPaquete(String nombre, String descripcion, int periodoValDias,Double descuento, Date fechaAlta);
	
	public abstract ArrayList<String> listarTipoDePublicaciones() throws TipoPublicacionNoExisteException;
	
	public abstract void altaOfertaLaboral(String nombre, String descrip, Date horaInicio, Date horaFin, double remuneracion, String ciudad, String departamento, Date fechaAlta, ArrayList<String> keywords);
	
	

	

}

/*
 * +ingresarDatosPaquete(nombre : String, descripcion: String, periodoValDias : Int, descuento: Float, fechaAlta: DTFecha)
+registrarPaquete() 
+obtenerOfertasEmpresa(nicknameEmpresa: String): Set(String)
+seleccionarOfertaLaboral(nomOferta: String): DTOferta
+listarPostulantes(): Set(string)
+seleccionarPostulante(nickname : String)
+registrarPostulacion();
+listarTipoDePublicaciones(): Set(String)
+seleccionarEmpresa(nickname: String)
+seleccionarTipoPublicacion(nomTipoPublicacion: String)
+listarKeywords(): Set(String)
+seleccionarKeyword(nomKeyword: String): Set(String)
+altaOfertaLaboral(nombre: String, descrip: String, horaInicio: DTHora, horaFin: DTHora, remuneracion: Float, ciudad: String, departamento: String, fechaAlta: DTFecha, keywords: Set(String))
+confirmarAltaOfertaLaboral()
+ingresarDatosTipoPublicacion(nombre: String, descripcion: String, exposicion: String, duracion: int, costo: Foat, fechaPub: DTFecha)
+confirmarAltaPublicacion()
+listarPaquetes(): Set(String)
+agregarTipoPublicacionAlPaquete(cantIncluida: Int)
*/
