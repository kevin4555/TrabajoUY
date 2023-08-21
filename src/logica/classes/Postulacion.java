package logica.classes;

import java.sql.Date;

import excepciones.OfertaLaboralNoExisteException;

import java.util.ArrayList;
import logica.DataTypes.DTPostulacion;
import logica.handlers.ManejadorOfertas;

public class Postulacion {
	private String descripcionMotivacion;
	private Date fechaPostulacion;
	private String cvReducido;
	private ArrayList<OfertaLaboral> postulaciones;
	
	public Postulacion(String descripcionMotivacion, Date fechaPostulacion, String cvReducido)
	{
		this.setCvReducido(cvReducido);
		this.setDescripcionMotivacion(descripcionMotivacion);
		this.setFechaPostulacion(fechaPostulacion);
	}
	
	public DTPostulacion obtenerDtPostulacion()
	{
	return null;	
	}

	public String getDescripcionMotivacion() {
		return descripcionMotivacion;
	}

	public void setDescripcionMotivacion(String descripcionMotivacion) {
		this.descripcionMotivacion = descripcionMotivacion;
	}

	public Date getFechaPostulacion() {
		return fechaPostulacion;
	}

	public void setFechaPostulacion(Date fechaPostulacion) {
		this.fechaPostulacion = fechaPostulacion;
	}

	public String getCvReducido() {
		return cvReducido;
	}

	public void setCvReducido(String cvReducido) {
		this.cvReducido = cvReducido;
	}
	
	public void agregarOferta(String nomOferta)
	{
		ManejadorOfertas mofertas = ManejadorOfertas.getInstance();
		try {
			OfertaLaboral oferta = mofertas.obtenerOfertaLaboral(nomOferta);
			postulaciones.add(oferta);
		}catch(OfertaLaboralNoExisteException e)
		{
			System.out.println(e.getMessage());
		}
	}
}
