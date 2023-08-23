package logica.classes;

import java.util.ArrayList;
import java.util.Date;

import logica.DataTypes.DTPostulante;
import logica.DataTypes.DTUsuario;

public class Postulante extends Usuario{
	private Date fechaNacimiento;
	private String nacionalidad;
	private ArrayList<Postulacion> postulaciones;
	
	
	public Postulante() {}
	
	public Postulante(String nickname, String nombre, 
			String apellido, String email, Date fechaNacimiento, String nacionalidad) {
		super(nickname, nombre, apellido, email);
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
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

	public void setPostulaciones(ArrayList<Postulacion> postulaciones) {
		this.postulaciones = postulaciones;
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
	
	
}
