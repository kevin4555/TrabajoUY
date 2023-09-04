package logica.classes;

import java.util.ArrayList;
import java.util.Date;

import logica.DataTypes.DTPostulacion;
import logica.DataTypes.DTPostulante;
import logica.DataTypes.DTUsuario;

public class Postulante extends Usuario{
	private Date fechaNacimiento;
	private String nacionalidad;
	private ArrayList<Postulacion> postulaciones;
	
	
	public Postulante(String nickname, String nombre, String apellido, String email, Date fechaNacimiento, String nacionalidad) {
		super(nickname, nombre, apellido, email);
		setFechaNacimiento(fechaNacimiento);
		setNacionalidad(nacionalidad);
		this.postulaciones = new ArrayList<Postulacion>();
	}
	
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public String getNacionalidad() {
		return nacionalidad;
	}
	
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public ArrayList<Postulacion> getPostulaciones() {
		return postulaciones;
	}
	
	public DTPostulante obtenerDTPostulante() {
		return new DTPostulante(this.nickname, this.nombre, this.apellido, this.email,
				this.fechaNacimiento, this.nacionalidad);
	}
	
	public void agregarPostulacion(Postulacion postulacion) {
		this.postulaciones.add(postulacion);
	}
	
	@Override
	public ArrayList<String> listarOfertasUsuario(){
		ArrayList<String> listaOfertas = new ArrayList<String>();
		for(Postulacion postulacion : postulaciones ) {
			listaOfertas.add(postulacion.getNombreOfertaLaboral());
		}
		return listaOfertas;
	}

	@Override
	public DTUsuario obtenerDTUsuario() {
		
		return this.obtenerDTPostulante();
	}
	
	public DTPostulacion obtenerDTPostulacion(String nombreOferta) {
		Postulacion postulacionAOferta = null;
		for(Postulacion postulacion : postulaciones) {
			if (postulacion.getNombreOfertaLaboral() == nombreOferta) {
				postulacionAOferta = postulacion;
			}
		}
		return postulacionAOferta.obtenerDTPostulacion();
	}
	
}
